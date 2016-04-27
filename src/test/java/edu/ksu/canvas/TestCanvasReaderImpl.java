package edu.ksu.canvas;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.impl.BaseImpl;
import edu.ksu.canvas.model.TestCanvasModel;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class TestCanvasReaderImpl extends BaseImpl<TestCanvasModel, TestCanvasReader> implements TestCanvasReader {

    public TestCanvasReaderImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    public List<TestCanvasModel> getTestModels() throws IOException {
        return getListFromCanvas(BaseImplUTest.URL_FOR_FIRST_RESPONSE);
    }

    public Optional<TestCanvasModel> getTestModel() throws IOException {
        return getFromCanvas(BaseImplUTest.URL_FOR_SINGLE_OBJECT_RESPONSE);
    }

    protected List<TestCanvasModel> parseListResponse(Response response) {
        Type listType = new TypeToken<List<TestCanvasModel>>(){}.getType();
        return getDefaultGsonParser().fromJson(response.getContent(), listType);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<TestCanvasModel>>(){}.getType();
    }

    @Override
    protected Class<TestCanvasModel> objectType() {
        return TestCanvasModel.class;
    }

}
