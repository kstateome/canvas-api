package edu.ksu.canvas.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.CanvasApiFactory;
import edu.ksu.canvas.interfaces.TabReader;
import edu.ksu.canvas.interfaces.TabWriter;
import edu.ksu.canvas.model.Tab;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.UpdateCourseTabOptions;
import org.apache.log4j.Logger;

public class TabImpl extends BaseImpl<Tab, TabReader, TabWriter> implements TabReader, TabWriter {

    private static final Logger LOG = Logger.getLogger(TabImpl.class);

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

    public static void main(String... args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: TabImpl <canvas_oauth_token>");
            System.exit(1);
        }

        CanvasApiFactory apiFactory = new CanvasApiFactory("https://cuboulder.beta.instructure.com");
        OauthToken token = new NonRefreshableOauthToken(args[0]);
/*
        TabReader tabReader = apiFactory.getReader(TabReader.class, token);
        List<Tab> tabs = tabReader.listAvailableCourseTabs("sis_course_id:109573-01-2187-B-100", false);
        for (Tab tab : tabs) {
            System.out.println(tab);
            System.out.println();
        }
*/
        TabWriter tabWriter = apiFactory.getWriter(TabWriter.class, token);
        UpdateCourseTabOptions options = new UpdateCourseTabOptions("sis_course_id:109748-01-2187-B-901", "files");
        options.hidden(true);
        Tab tab = tabWriter.updateCourseTab(options).get();
        System.err.println(tab.isHidden());
    }
}
