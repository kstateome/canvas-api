package edu.ksu.canvas;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.model.TestCanvasModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class BaseImplUTest extends CanvasTestBase {
    public static final String URL_FOR_FIRST_RESPONSE = "testModels/1";
    public static final String URL_FOR_SECOND_RESPONSE = "testModels/2";
    public static final String URL_FOR_SINGLE_OBJECT_RESPONSE = "testModels/model";
    private TestCanvasReader canvasReader;
    private boolean callbackWasCalled;

    @Before
    public void setup() {
        canvasReader = new TestCanvasReaderImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
        callbackWasCalled = false;
    }

    @Test
    public void callbackIsCalledWhenUsingWithCallback() throws IOException{
        fakeRestClient.addSuccessResponse(URL_FOR_FIRST_RESPONSE, "TestModels/TestModels1.json");
        Consumer<List<TestCanvasModel>> callback = modelList -> setCallbackWasCalled();
        canvasReader.withCallback(callback).getTestModels(URL_FOR_FIRST_RESPONSE);
        Assert.assertTrue("Expected callback to be called when list was retrieved", callbackWasCalled);
    }

    @Test
    public void callbackGetsParsedModel() throws IOException {
        fakeRestClient.addSuccessResponse(URL_FOR_FIRST_RESPONSE, "TestModels/TestModels1.json");
        Consumer<List<TestCanvasModel>> callback = modelList -> {
            setCallbackWasCalled();
            Assert.assertEquals("object1Field1", modelList.get(0).getField1());
            Assert.assertEquals("object1Field2", modelList.get(0).getField2());
            Assert.assertEquals("object2Field1", modelList.get(1).getField1());
            Assert.assertEquals("object2Field2", modelList.get(1).getField2());
        };
        canvasReader.withCallback(callback).getTestModels(URL_FOR_FIRST_RESPONSE);
        Assert.assertTrue("Expected callback to be called when list was retrieved", callbackWasCalled);
    }

    @Test
    public void callbackGets2ndObject() throws IOException {
        fakeRestClient.addSuccessResponse(URL_FOR_FIRST_RESPONSE, URL_FOR_SECOND_RESPONSE, "TestModels/TestModels1.json");
        fakeRestClient.addSuccessResponse(URL_FOR_SECOND_RESPONSE, "TestModels/TestModels2.json");
        Consumer<List<TestCanvasModel>> callback = modelList -> {
            if (secondTimeCallBackWasCalled()) {
                Assert.assertEquals("object3Field1", modelList.get(0).getField1());
                Assert.assertEquals("object3Field2", modelList.get(0).getField2());
                Assert.assertEquals("object4Field1", modelList.get(1).getField1());
                Assert.assertEquals("object4Field2", modelList.get(1).getField2());
            }
            setCallbackWasCalled();
        };
        canvasReader.withCallback(callback).getTestModels(URL_FOR_FIRST_RESPONSE);
        Assert.assertTrue("Expected callback to be called when list was retrieved", callbackWasCalled);
    }

    @Test
    public void jsonObjectInsteadOfJsonListFails() throws IOException {
        try {
            fakeRestClient.addSuccessResponse(URL_FOR_SINGLE_OBJECT_RESPONSE, "TestModels/TestModel.json");
            Consumer<List<TestCanvasModel>> callback = modelList -> setCallbackWasCalled();
            canvasReader.withCallback(callback).getTestModels(URL_FOR_SINGLE_OBJECT_RESPONSE);
        } catch (JsonSyntaxException e) {
            Assert.assertFalse("Expected callback to not be called upon invalid json", callbackWasCalled);
            return;
        }
        Assert.fail("Should have been exception upon json with single object");
    }

    @Test
    public void callbackIsNotSavedOnRepeatedCalls() throws IOException {
        fakeRestClient.addSuccessResponse(URL_FOR_FIRST_RESPONSE, "TestModels/TestModels1.json");
        Consumer<List<TestCanvasModel>> callback = modelList -> setCallbackWasCalled();
        canvasReader.withCallback(callback).getTestModels(URL_FOR_FIRST_RESPONSE);
        Assert.assertTrue(callbackWasCalled);
        callbackWasCalled = false;
        canvasReader.getTestModels(URL_FOR_FIRST_RESPONSE);
        Assert.assertFalse("Callback should not be saved for future calls", callbackWasCalled);
    }

    @Test
    public void getFromCanvasParsesObject() throws IOException {
        fakeRestClient.addSuccessResponse(URL_FOR_SINGLE_OBJECT_RESPONSE, "TestModels/TestModel.json");
        Optional<TestCanvasModel> modelOptional = canvasReader.getTestModel(URL_FOR_SINGLE_OBJECT_RESPONSE);
        TestCanvasModel model = modelOptional.orElseThrow(() -> new AssertionError("Expected getfromCanvas to return non-empty object"));
        Assert.assertEquals("field1", model.getField1());
        Assert.assertEquals("field2", model.getField2());
    }

    // I'm getting around the fact that Java won't let you capture non final variables in anonymous functions
    private void setCallbackWasCalled() {
        callbackWasCalled = true;
    }

    private boolean secondTimeCallBackWasCalled() {
        return callbackWasCalled;
    }
}


