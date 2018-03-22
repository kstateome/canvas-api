package edu.ksu.canvas;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.impl.BaseImpl;
import edu.ksu.canvas.model.TestCanvasModel;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class TestCanvasReaderImpl extends BaseImpl<TestCanvasModel, TestCanvasReader, TestCanvasWriter> implements TestCanvasReader {

    public TestCanvasReaderImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, CanvasTestBase.DEFAULT_PAGINATION_PAGE_SIZE, false);
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
