package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.GradingStandardReader;
import edu.ksu.canvas.interfaces.GradingStandardWriter;
import edu.ksu.canvas.model.GradingStandard;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GradingStandardImpl extends BaseImpl<GradingStandard, GradingStandardReader, GradingStandardWriter> implements GradingStandardReader, GradingStandardWriter{

    private static final Logger LOG = LoggerFactory.getLogger(GradingStandardImpl.class);

    public GradingStandardImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                            int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<GradingStandard> listGradingStandardsInCourse(String courseId) throws IOException {
        LOG.debug("listing grading standards in course " + courseId);
        String url = buildCanvasUrl(String.format("courses/%s/grading_standards", courseId), Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<GradingStandard> listGradingStandardsInAccount(String accountId) throws IOException {
        LOG.debug("listing grading standards in account " + accountId);
        String url = buildCanvasUrl(String.format("accounts/%s/grading_standards", accountId), Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<GradingStandard> getGradingStandardInCourse(String courseId, Long gradingStandardId) throws IOException {
        LOG.debug("getting grading standard " + gradingStandardId + " from course " + courseId);
        String url = buildCanvasUrl(String.format("courses/%s/grading_standards/%d", courseId, gradingStandardId), Collections.emptyMap());
        return getFromCanvas(url);
    }

    @Override
    public Optional<GradingStandard> getGradingStandardInAccount(String accountId, Long gradingStandardId) throws IOException {
        LOG.debug("getting grading standard " + gradingStandardId + " from account " + accountId);
        String url = buildCanvasUrl(String.format("accounts/%s/grading_standards/%d", accountId, gradingStandardId), Collections.emptyMap());
        return getFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<GradingStandard>>(){}.getType();
    }

    @Override
    protected Class<GradingStandard> objectType() {
        return GradingStandard.class;
    }
}
