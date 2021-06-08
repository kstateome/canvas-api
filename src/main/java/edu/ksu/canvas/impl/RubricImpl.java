package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.RubricReader;
import edu.ksu.canvas.interfaces.RubricWriter;
import edu.ksu.canvas.model.assignment.Rubric;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetRubricOptions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class RubricImpl extends BaseImpl<Rubric, RubricReader, RubricWriter> implements RubricReader, RubricWriter{
    private static final Logger LOG = LoggerFactory.getLogger(RubricImpl.class);

    public RubricImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                      int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<Rubric> getRubricInAccount(GetRubricOptions options) throws IOException {
        if(StringUtils.isBlank(options.getCanvasId()) || options.getRubricId() == null) {
            throw new IllegalArgumentException(("Account and rubric IDs must be supplied"));
        }
        LOG.debug("Retrieving rubric {} in account {}", options.getRubricId(), options.getCanvasId());
        String url = buildCanvasUrl(String.format("accounts/%s/rubrics/%d", options.getCanvasId(), options.getRubricId()), options.getOptionsMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(Rubric.class, response);
    }

    @Override
    public Optional<Rubric> getRubricInCourse(GetRubricOptions options) throws IOException {
        if(StringUtils.isBlank(options.getCanvasId()) || options.getRubricId() == null) {
            throw new IllegalArgumentException(("Course and rubric IDs must be supplied"));
        }
        LOG.debug("Retrieving rubric {} in course {}", options.getRubricId(), options.getCanvasId());
        String url = buildCanvasUrl(String.format("courses/%s/rubrics/%d", options.getCanvasId(), options.getRubricId()), options.getOptionsMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(Rubric.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Rubric>>(){}.getType();
    }

    @Override
    protected Class<Rubric> objectType() {
        return Rubric.class;
    }
}
