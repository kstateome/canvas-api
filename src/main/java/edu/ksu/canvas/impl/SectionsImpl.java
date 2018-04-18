package edu.ksu.canvas.impl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.enums.SectionIncludes;
import edu.ksu.canvas.interfaces.SectionReader;
import edu.ksu.canvas.interfaces.SectionWriter;
import edu.ksu.canvas.model.Section;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SectionsImpl extends BaseImpl<Section, SectionReader, SectionWriter> implements SectionReader,
        SectionWriter {

    private static final Logger LOG = Logger.getLogger(SectionReader.class);

    public SectionsImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                        int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<Section> listCourseSections(String courseId, List<SectionIncludes> includes) throws IOException {
        LOG.debug("Looking up sections for course " + courseId);
        ImmutableMap<String, List<String>> parameters = ImmutableMap.<String,List<String>>builder()
                .put("include[]", includes.stream().map(Enum::toString).collect(Collectors.toList()))
                .build();
        String url = buildCanvasUrl("/courses/" + courseId + "/sections", parameters);
        return getListFromCanvas(url);
    }

    @Override
    public Optional<Section> getSingleSection(String sectionId) throws IOException {
        LOG.debug("getting section " + sectionId);
        String url = buildCanvasUrl("sections/" + sectionId, new HashMap<>());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(Section.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Section>>(){}.getType();
    }

    @Override
    protected Class<Section> objectType() {
        return Section.class;
    }

    @Override
    public Optional<Section> createSection(String courseId, Section section, Boolean enableSisReactivation)
            throws IOException {
        LOG.debug("creating section for course " + courseId);
        Map<String, List<String>> params = new HashMap<>();
        if(enableSisReactivation != null) {
            params.put("enable_sis_reactivation", Arrays.asList(Boolean.toString(enableSisReactivation)));
        }
        String url = buildCanvasUrl(String.format("/courses/%s/sections", courseId), params);
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, section.toJsonObject(serializeNulls));
        return responseParser.parseToObject(Section.class, response);
    }

    @Override
    public Optional<Section> updateSection(Section section) throws IOException {
        LOG.debug("updating section " + section.getId());
        String url = buildCanvasUrl("sections/" + section.getId(), Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, section.toJsonObject(serializeNulls));
        return responseParser.parseToObject(Section.class, response);
    }

    @Override
    public Optional<Section> deleteSection(String sectionId) throws IOException {
        LOG.debug("deleting section " + sectionId);
        String url = buildCanvasUrl("/sections/" + sectionId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
        return responseParser.parseToObject(Section.class, response);
    }

    @Override
    public Optional<Section> crosslist(String sectionId, String courseId) throws IOException {
        LOG.debug("crosslisting section " + sectionId + " to course " + courseId);
        String url = buildCanvasUrl("/sections/" + sectionId + "/crosslist/" + courseId, Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, new JsonObject());
        return responseParser.parseToObject(Section.class, response);
    }
}
