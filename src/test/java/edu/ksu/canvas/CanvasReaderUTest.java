package edu.ksu.canvas;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.impl.BaseImpl;
import edu.ksu.canvas.model.TestCanvasModel;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class CanvasReaderUTest extends CanvasTestBase {
    public static final String URL_FOR_ONE_MODEL = "testModels/TestModels";
    private TestCanvasReader canvasReader;
    private boolean callbackWasCalled;

    @Before
    public void setup() {
        canvasReader = new TestCanvasReader(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient);
        fakeRestClient.addSuccessResponse(URL_FOR_ONE_MODEL, "SampleJson/TestModels.json");
    }

    @Test
    public void callbackIsCalledWhenUsingWithCallback() {
        Consumer<List<TestCanvasModel>> callback = modelList -> setCallbackWasCalled();
        canvasReader.withCallback(callback).getTestModels();
        Assert.assertTrue("Expected callback to be called when list was retrieved", callbackWasCalled);
    }

    @Test
    public void callbackGetsParsedModel() {
        Consumer<List<TestCanvasModel>> callback = modelList -> {
            Assert.assertEquals("field1Object1", modelList.get(0).getField1());
            Assert.assertEquals("field1Object2", modelList.get(0).getField2());
            Assert.assertEquals("field2Object1", modelList.get(1).getField1());
            Assert.assertEquals("field2Obvcvcfject2", modelList.get(0).getField1());
        };

        canvasReader.withCallback(callback).getTestModels();
        Assert.assertTrue("Expected callback to be called when list was retrieved", callbackWasCalled);


    }

    // I'm getting around the fact that Java won't let you capture non final variables
    private void setCallbackWasCalled() {
        callbackWasCalled = true;
    }
}


class TestCanvasReader extends BaseImpl<TestCanvasModel> {

    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl The base URL of your canvas instance
     * @param apiVersion    The version of the Canvas API (currently 1)
     * @param oauthToken    OAuth token to use when executing API calls
     * @param restClient
     */
    public TestCanvasReader(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    public List<TestCanvasModel> getTestModels() {
        try {
            return getListFromCanvas(CanvasReaderUTest.URL_FOR_ONE_MODEL);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    protected List<TestCanvasModel> parseListResponse(Response response) {
        Type listType = new TypeToken<List<TestCanvasModel>>(){}.getType();
        return getDefaultGsonParser().fromJson(response.getContent(), listType);
    }

    @Override
    protected Optional<TestCanvasModel> parseObjectResponse(Response response) {
        return responseParser.parseToObject(TestCanvasModel.class, response);
    }

    @Override
    public TestCanvasReader withCallback(Consumer<List<TestCanvasModel>> consumer) {
        return (TestCanvasReader) super.withCallback(consumer);
    }
}