package edu.ksu.canvas.impl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.enums.SectionIncludes;
import edu.ksu.canvas.interfaces.SectionReader;
import edu.ksu.canvas.interfaces.SectionWriter;
import edu.ksu.canvas.model.Section;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SectionsImpl extends BaseImpl<Section, SectionReader, SectionWriter> implements SectionReader {

    private static final Logger LOG = Logger.getLogger(SectionReader.class);

    public SectionsImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
    }

    @Override
    public List<Section> listCourseSections(String courseId, List<SectionIncludes> includes) throws IOException {
        LOG.debug("Looking up sections for course " + courseId);
        ImmutableMap<String, List<String>> parameters = ImmutableMap.<String,List<String>>builder()
                .put("include[]", includes.stream().map(Enum::name).collect(Collectors.toList()))
                .build();
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "/courses/" + courseId + "/sections", parameters);
        List<Response> response = canvasMessenger.getFromCanvas(oauthToken, url);
        return parseSections(response);
    }

    @Override
    public Section getSingleSection(String sectionId) throws IOException {
        LOG.debug("getting section " + sectionId);
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "sections/" + sectionId, new HashMap<>());
        LOG.debug("Final URL of API call: " + url);
        return getFromCanvas(url).orElseThrow(() -> new IllegalArgumentException("Failed to find section " + sectionId));
    }
    
    public List<Section> parseSections(List<Response> responses) {
        return responses
                .stream()
                .map(this::parse)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<Section> parse(Response response) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Type listType = new TypeToken<List<Section>>(){}.getType();
        return gson.fromJson(response.getContent(), listType);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Section>>(){}.getType();
    }

    @Override
    protected Class<Section> objectType() {
        return Section.class;
    }

}
