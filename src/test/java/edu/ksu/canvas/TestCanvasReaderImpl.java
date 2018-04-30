package edu.ksu.canvas;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.impl.BaseImpl;
import edu.ksu.canvas.model.TestCanvasModel;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class TestCanvasReaderImpl extends BaseImpl<TestCanvasModel, TestCanvasReader, TestCanvasWriter> implements TestCanvasReader {

    public TestCanvasReaderImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, CanvasTestBase.DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    public List<TestCanvasModel> getTestModels(String objectId) throws IOException {
        return getListFromCanvas(buildTestUrl(objectId));
    }

    public Optional<TestCanvasModel> getTestModel(String objectId) throws IOException {
        return getFromCanvas(buildTestUrl(objectId));
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<TestCanvasModel>>(){}.getType();
    }

    @Override
    protected Class<TestCanvasModel> objectType() {
        return TestCanvasModel.class;
    }

    @Override
    public String buildTestUrl(String id) {
        return buildCanvasUrl("testModels/" + id, Collections.emptyMap());
    }

}
