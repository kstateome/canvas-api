package edu.ksu.canvas.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableMap;

import edu.ksu.canvas.enums.SectionIncludes;
import edu.ksu.canvas.interfaces.SectionReader;
import edu.ksu.canvas.model.Section;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;

public class SectionsImpl extends BaseImpl implements SectionReader {

    public SectionsImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken) {
        super(canvasBaseUrl, apiVersion, oauthToken);
    }

    @Override
    public List<Section> listCourseSections(Integer courseId, List<SectionIncludes> includes) throws IOException {
        ImmutableMap<String, List<String>> parameters = ImmutableMap.<String,List<String>>builder()
                .put("include[]", includes.stream().map(Enum::name).collect(Collectors.toList()))
                .build();
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "/courses/" + courseId + "/sections", parameters);
        List<Section> sections = new ArrayList<>();
        while(StringUtils.isNotBlank(url)) {
            Response response = canvasMessenger.getFromCanvas(oauthToken, url);
            if (response.getErrorHappened() || response.getResponseCode() != 200) {
                return Collections.emptyList();
            }
            sections.addAll(responseParser.parseToList(Section.class, response));
            url = response.getNextLink();
        }
        return sections;
    }

}
