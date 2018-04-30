package edu.ksu.canvas;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.model.TestCanvasModel;
import edu.ksu.canvas.net.FakeRestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class BaseImplUTest extends CanvasTestBase {
    public static final String TEST_OBJECT_ID_1 = "1";
    public static final String TEST_OBJECT_ID_2 = "2";
    public static final String TEST_OBJECT_ID_3 = "model";
    private TestCanvasReader canvasReader;
    private boolean callbackWasCalled;
    private FakeRestClient restClientSpy;

    @Before
    public void setup() {
        this.restClientSpy = Mockito.spy(fakeRestClient);
        canvasReader = new TestCanvasReaderImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, restClientSpy, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
        callbackWasCalled = false;
    }

    @Test
    public void callbackIsCalledWhenUsingWithCallback() throws IOException{
        fakeRestClient.addSuccessResponse(TEST_OBJECT_ID_1, "TestModels/TestModels1.json");
        Consumer<List<TestCanvasModel>> callback = modelList -> setCallbackWasCalled();
        canvasReader.withCallback(callback).getTestModels(TEST_OBJECT_ID_1);
        Assert.assertTrue("Expected callback to be called when list was retrieved", callbackWasCalled);
    }

    @Test
    public void callbackGetsParsedModel() throws IOException {
        String url = canvasReader.buildTestUrl(TEST_OBJECT_ID_1);
        fakeRestClient.addSuccessResponse(url, "TestModels/TestModels1.json");
        Consumer<List<TestCanvasModel>> callback = modelList -> {
            setCallbackWasCalled();
            Assert.assertEquals("object1Field1", modelList.get(0).getField1());
            Assert.assertEquals("object1Field2", modelList.get(0).getField2());
            Assert.assertEquals("object2Field1", modelList.get(1).getField1());
            Assert.assertEquals("object2Field2", modelList.get(1).getField2());
        };
        canvasReader.withCallback(callback).getTestModels(TEST_OBJECT_ID_1);
        Assert.assertTrue("Expected callback to be called when list was retrieved", callbackWasCalled);
    }

    @Test
    public void callbackGets2ndObject() throws IOException {
        String firstUrl = canvasReader.buildTestUrl(TEST_OBJECT_ID_1);
        String secondUrl = canvasReader.buildTestUrl(TEST_OBJECT_ID_2);
        fakeRestClient.addSuccessResponse(firstUrl, "TestModels/TestModels1.json");
        fakeRestClient.addSuccessResponse(secondUrl, "TestModels/TestModels2.json");
        Consumer<List<TestCanvasModel>> callback = modelList -> {
            if (secondTimeCallBackWasCalled()) {
                Assert.assertEquals("object3Field1", modelList.get(0).getField1());
                Assert.assertEquals("object3Field2", modelList.get(0).getField2());
                Assert.assertEquals("object4Field1", modelList.get(1).getField1());
                Assert.assertEquals("object4Field2", modelList.get(1).getField2());
            }
            setCallbackWasCalled();
        };
        canvasReader.withCallback(callback).getTestModels(TEST_OBJECT_ID_1);
        Assert.assertTrue("Expected callback to be called when list was retrieved", callbackWasCalled);
    }

    @Test
    public void jsonObjectInsteadOfJsonListFails() throws IOException {
        String url = canvasReader.buildTestUrl(TEST_OBJECT_ID_3);
        try {
            fakeRestClient.addSuccessResponse(url, "TestModels/TestModel.json");
            Consumer<List<TestCanvasModel>> callback = modelList -> setCallbackWasCalled();
            canvasReader.withCallback(callback).getTestModels(TEST_OBJECT_ID_3);
        } catch (JsonSyntaxException e) {
            Assert.assertFalse("Expected callback to not be called upon invalid json", callbackWasCalled);
            return;
        }
        Assert.fail("Should have been exception upon json with single object");
    }

    @Test
    public void callbackIsNotSavedOnRepeatedCalls() throws IOException {
        String url = canvasReader.buildTestUrl(TEST_OBJECT_ID_1);
        fakeRestClient.addSuccessResponse(url, "TestModels/TestModels1.json");
        Consumer<List<TestCanvasModel>> callback = modelList -> setCallbackWasCalled();
        canvasReader.withCallback(callback).getTestModels(TEST_OBJECT_ID_1);
        Assert.assertTrue(callbackWasCalled);
        callbackWasCalled = false;
        canvasReader.getTestModels(TEST_OBJECT_ID_1);
        Assert.assertFalse("Callback should not be saved for future calls", callbackWasCalled);
    }

    @Test
    public void getFromCanvasParsesObject() throws IOException {
        String url = canvasReader.buildTestUrl(TEST_OBJECT_ID_1);
        fakeRestClient.addSuccessResponse(url, "TestModels/TestModel.json");
        Optional<TestCanvasModel> modelOptional = canvasReader.getTestModel(TEST_OBJECT_ID_3);
        TestCanvasModel model = modelOptional.orElseThrow(() -> new AssertionError("Expected getfromCanvas to return non-empty object"));
        Assert.assertEquals("field1", model.getField1());
        Assert.assertEquals("field2", model.getField2());
    }


    /*
     * This test ensures that masquerade functionality is not trivially not thread safe. Meaning, this is the simple test shows that it isn't thread safe
     * but it does not prove thread safety.
     */
    @Test
    public void readAsCanvasUserIsNotObviouslyNotThreadsafe() throws IOException {
        String user1Url = canvasReader.buildTestUrl(TEST_OBJECT_ID_1) + "?as_user_id=user1";
        String user2Url = canvasReader.buildTestUrl(TEST_OBJECT_ID_1)+ "?as_user_id=user2";
        fakeRestClient.addSuccessResponse(user1Url, "TestModels/TestModel.json");
        // Since this test currently fails, this is needed because the second call to getTestModel() does not have the user parameter. It makes the test failure make more sense
        fakeRestClient.addSuccessResponse(canvasReader.buildTestUrl(TEST_OBJECT_ID_1), "TestModels/TestModel.json");
        fakeRestClient.addSuccessResponse(user2Url, "TestModels/TestModel.json");
        TestCanvasReader readerAsUser1 = canvasReader.readAsCanvasUser("user1");
        TestCanvasReader readerAsUser2 = canvasReader.readAsCanvasUser("user2");

        readerAsUser1.getTestModel(TEST_OBJECT_ID_1);
        readerAsUser2.getTestModel(TEST_OBJECT_ID_1);

        verify(restClientSpy).sendApiGet(eq(SOME_OAUTH_TOKEN), eq(user1Url), anyInt(), anyInt());
        verify(restClientSpy).sendApiGet(eq(SOME_OAUTH_TOKEN), eq(user2Url), anyInt(), anyInt());
    }

    @Test
    public void readAsCanvasUserAppendsParamCorrectly() throws IOException {
        String urlWithMasqueradeParam = canvasReader.buildTestUrl(TEST_OBJECT_ID_1) + "?as_user_id=user1";
        fakeRestClient.addSuccessResponse(urlWithMasqueradeParam, "TestModels/TestModel.json");
        canvasReader.readAsCanvasUser("user1").getTestModel(TEST_OBJECT_ID_1);

        verify(restClientSpy).sendApiGet(eq(SOME_OAUTH_TOKEN), eq(urlWithMasqueradeParam), anyInt(), anyInt());
    }


    // I'm getting around the fact that Java won't let you capture non final variables in anonymous functions
    private void setCallbackWasCalled() {
        callbackWasCalled = true;
    }

    private boolean secondTimeCallBackWasCalled() {
        return callbackWasCalled;
    }
}


