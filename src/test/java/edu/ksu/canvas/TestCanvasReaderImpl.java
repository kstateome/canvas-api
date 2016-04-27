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

    public List<TestCanvasModel> getTestModels(String url) throws IOException {
        return getListFromCanvas(url);
    }

    public Optional<TestCanvasModel> getTestModel(String url) throws IOException {
        return getFromCanvas(url);
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
