package edu.ksu.canvas.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.TabReader;
import edu.ksu.canvas.interfaces.TabWriter;
import edu.ksu.canvas.model.Tab;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.UpdateCourseTabOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TabImpl extends BaseImpl<Tab, TabReader, TabWriter> implements TabReader, TabWriter {

    private static final Logger LOG = LoggerFactory.getLogger(TabImpl.class);

    public TabImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
            int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize,
                serializeNulls);
    }

    @Override
    public List<Tab> listAvailableCourseTabs(String courseId, boolean includeExternalTools) throws IOException {
        LOG.debug("Retrieving tabs for course " + courseId);
        String url = buildCanvasUrl(String.format("courses/%s/tabs", courseId), Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<Tab> updateCourseTab(UpdateCourseTabOptions options) throws IOException {
        LOG.debug(String.format("Updating tab %s for course %s", options.getCourseId(), options.getTabId()));
        String url = buildCanvasUrl(String.format("courses/%s/tabs/%s", options.getCourseId(), options.getTabId()),
                Collections.emptyMap());
        Response response = canvasMessenger.putToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(Tab.class, response);
    }

    @Override
    protected Class<Tab> objectType() {
        return Tab.class;
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Tab>>(){}.getType();
    }
}
