package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.FeatureFlagReader;
import edu.ksu.canvas.interfaces.FeatureFlagWriter;
import edu.ksu.canvas.model.FeatureFlag;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FeatureFlagImpl extends BaseImpl<FeatureFlag, FeatureFlagReader, FeatureFlagWriter> implements FeatureFlagWriter, FeatureFlagReader {

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
    public FeatureFlagImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<FeatureFlag> updateCourseFeatureFlag(String courseId, String feature, FeatureFlag.State state) throws IOException {
        String url = buildCanvasUrl("courses/" + courseId + "/features/flags/" + feature, Collections.emptyMap());
        return updateFeatureFlag(url, state);
    }

    @Override
    public Optional<FeatureFlag> updateAccountFeatureFlag(String accountId, String feature, FeatureFlag.State state) throws IOException {
        String url = buildCanvasUrl("accounts/" + accountId + "/features/flags/" + feature, Collections.emptyMap());
        return updateFeatureFlag(url, state);
    }

    @Override
    public Optional<FeatureFlag> updateUserFeatureFlag(String userId, String feature, FeatureFlag.State state) throws IOException {
        String url = buildCanvasUrl("users/" + userId + "/features/flags/" + feature, Collections.emptyMap());
        return updateFeatureFlag(url, state);
    }

    private Optional<FeatureFlag> updateFeatureFlag(String url, FeatureFlag.State state) throws IOException {
        Map<String, List<String>> params = Collections.singletonMap("state", Collections.singletonList(state.name()));
        return getFeatureFlag(canvasMessenger.putToCanvas(oauthToken, url, params));
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<FeatureFlag>>(){}.getType();
    }

    @Override
    protected Class<FeatureFlag> objectType() {
        return FeatureFlag.class;
    }

    @Override
    public Optional<FeatureFlag> getCourseFeatureFlag(String courseId, String feature) throws IOException {
        String url = buildCanvasUrl("courses/"+ courseId+ "/features/flags/"+ feature, Collections.emptyMap());
        return getFeatureFlag(canvasMessenger.getSingleResponseFromCanvas(oauthToken, url));
    }

    @Override
    public Optional<FeatureFlag> getAccountFeatureFlag(String accountId, String feature) throws IOException {
        String url = buildCanvasUrl("accounts/"+ accountId+ "/features/flags/"+ feature, Collections.emptyMap());
        return getFeatureFlag(canvasMessenger.getSingleResponseFromCanvas(oauthToken, url));
    }

    @Override
    public Optional<FeatureFlag> getUserFeatureFlag(String userId, String feature) throws IOException {
        String url = buildCanvasUrl("users/"+ userId+ "/features/flags/"+ feature, Collections.emptyMap());
        return getFeatureFlag(canvasMessenger.getSingleResponseFromCanvas(oauthToken, url));
    }

    private Optional<FeatureFlag> getFeatureFlag(Response singleResponseFromCanvas) {
        Response response = singleResponseFromCanvas;
        return responseParser.parseToObject(FeatureFlag.class, response);
    }

}
