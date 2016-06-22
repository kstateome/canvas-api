package edu.ksu.canvas.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.PageReader;
import edu.ksu.canvas.interfaces.PageWriter;
import edu.ksu.canvas.model.Page;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;

public class PageImpl extends BaseImpl<Page, PageReader, PageWriter> implements PageReader, PageWriter {
    private static final Logger LOG = Logger.getLogger(PageImpl.class);

    public PageImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient, int connectTimeout, int readTimeout) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout);
    }

    @Override
    public Optional<Page> getCoursePage(String courseId, String pageUrl) throws IOException {
        LOG.debug("retrieving page " + pageUrl + " for course " + courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/pages/" + pageUrl, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(Page.class, response);
    }

    @Override
    public Optional<Page> getGroupPage(String groupId, String pageUrl) throws IOException {
        LOG.debug("retrieving page " + pageUrl + " for group " + groupId);
        String url = buildCanvasUrl("groups/" + groupId + "/pages/" + pageUrl, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(Page.class, response);
    }

    @Override
    public Optional<Page> updateCoursePage(Page page, String courseId) throws Exception {
        LOG.debug("Updating page in course" + courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/pages/" + page.getUrl(), Collections.emptyMap());
        Response response = canvasMessenger.putToCanvas(oauthToken, url, page.toPostMap());
        return responseParser.parseToObject(Page.class, response);
    }

    @Override
    public List<Page> listPagesInCourse(String courseId) throws IOException {
        LOG.debug("fetching all pages for course " + courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/pages", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<Page> listPagesInGroup(String groupId) throws IOException {
        LOG.debug("fetching all pages for course " + groupId);
        String url = buildCanvasUrl("groups/" + groupId + "/pages", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Page>>(){}.getType();
    }

    @Override
    protected Class<Page> objectType() {
        return Page.class;
    }
}
