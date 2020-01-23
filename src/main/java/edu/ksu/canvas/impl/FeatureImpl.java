package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.FeatureReader;
import edu.ksu.canvas.interfaces.FeatureWriter;
import edu.ksu.canvas.model.Feature;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class FeatureImpl extends BaseImpl<Feature, FeatureReader, FeatureWriter> implements FeatureReader {

    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl      The base URL of your canvas instance
     * @param apiVersion         The version of the Canvas API (currently 1)
     * @param oauthToken         OAuth token to use when executing API calls
     * @param restClient         a RestClient implementation to use when talking to Canvas
     * @param connectTimeout     Timeout in seconds to use when connecting
     * @param readTimeout        Timeout in seconds to use when waiting for data to come back from an open connection
     * @param paginationPageSize How many objects to request per page on paginated requests
     * @param serializeNulls     Whether or not to include null fields in the serialized JSON. Defaults to false if null
     */
    public FeatureImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Feature>>(){}.getType();
    }

    @Override
    protected Class<Feature> objectType() {
        return Feature.class;
    }

    @Override
    public List<Feature> getCourseFeatures(String courseId) throws IOException {
        String url = buildCanvasUrl("courses/"+ courseId+ "/features", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<Feature> getAccountFeatures(String accountId) throws IOException {
        String url = buildCanvasUrl("accounts/"+ accountId+ "/features", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<Feature> getUserFeatures(String userId) throws IOException {
        String url = buildCanvasUrl("users/"+ userId+ "/features", Collections.emptyMap());
        return getListFromCanvas(url);
    }
}
