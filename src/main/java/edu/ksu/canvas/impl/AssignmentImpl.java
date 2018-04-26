package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.AssignmentReader;
import edu.ksu.canvas.interfaces.AssignmentWriter;
import edu.ksu.canvas.model.assignment.Assignment;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetSingleAssignmentOptions;
import edu.ksu.canvas.requestOptions.ListCourseAssignmentsOptions;
import edu.ksu.canvas.requestOptions.ListUserAssignmentOptions;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AssignmentImpl extends BaseImpl<Assignment, AssignmentReader, AssignmentWriter> implements AssignmentReader, AssignmentWriter{
    private static final Logger LOG = Logger.getLogger(AssignmentReader.class);

    public AssignmentImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                          int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<Assignment> listCourseAssignments(ListCourseAssignmentsOptions options) throws IOException {
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/assignments" , options.getOptionsMap());
        return getListFromCanvas(url);
    }

    public List<Assignment> listUserAssignments(ListUserAssignmentOptions options) throws IOException {
        String url = buildCanvasUrl("users/" + options.getUserId() + "/courses/" + options.getCourseId() + "/assignments", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<Assignment> getSingleAssignment(GetSingleAssignmentOptions options) throws IOException {
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/assignments/" + options.getAssignmentId(), options.getOptionsMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(Assignment.class, response);
    }

    @Override
    public Optional<Assignment> createAssignment(String courseId, Assignment assignment) throws IOException {
        if(StringUtils.isBlank(assignment.getName())) {
            throw new IllegalArgumentException("Assignment must have a name");
        }
        String url = buildCanvasUrl("courses/" + courseId + "/assignments", Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, assignment.toJsonObject(serializeNulls));
        return responseParser.parseToObject(Assignment.class, response);
    }

    @Override
    public Optional<Assignment> deleteAssignment(String courseId, String assignmentId) throws IOException {
        Map<String, List<String>> postParams = new HashMap<>();
        postParams.put("event", Collections.singletonList("delete"));
        String createdUrl = buildCanvasUrl("courses/" + courseId + "/assignments/" + assignmentId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, createdUrl, postParams);
        LOG.debug("response " + response.toString());
        if(response.getErrorHappened() || response.getResponseCode() != 200){
            LOG.debug("Failed to delete assignment, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(Assignment.class, response);
    }

    @Override
    public Optional<Assignment> editAssignment(String courseId, Assignment assignment) throws IOException {
        String url = buildCanvasUrl("courses/" + courseId + "/assignments/" + assignment.getId(), Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, assignment.toJsonObject(serializeNulls));
        return responseParser.parseToObject(Assignment.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Assignment>>(){}.getType();
    }

    @Override
    protected Class<Assignment> objectType() {
        return Assignment.class;
    }

}
