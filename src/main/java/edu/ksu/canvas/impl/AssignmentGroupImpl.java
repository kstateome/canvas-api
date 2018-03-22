package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AssignmentGroupReader;
import edu.ksu.canvas.interfaces.AssignmentGroupWriter;
import edu.ksu.canvas.model.assignment.AssignmentGroup;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.DeleteAssignmentGroupOptions;
import edu.ksu.canvas.requestOptions.GetAssignmentGroupOptions;
import edu.ksu.canvas.requestOptions.ListAssignmentGroupOptions;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AssignmentGroupImpl extends BaseImpl<AssignmentGroup, AssignmentGroupReader, AssignmentGroupWriter> implements AssignmentGroupReader, AssignmentGroupWriter {
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
    public AssignmentGroupImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                               int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    private static final Logger LOG = Logger.getLogger(AssignmentGroupImpl.class);

    @Override
    public Optional<AssignmentGroup> getAssignmentGroup(GetAssignmentGroupOptions options) throws IOException {
        LOG.debug("Fetching assignment group " + options.getAssignmentGroupId() + " from course " + options.getCourseId());
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/assignment_groups/" + options.getAssignmentGroupId(), options.getOptionsMap());
        return getFromCanvas(url);
    }

    @Override
    public List<AssignmentGroup> listAssignmentGroup(ListAssignmentGroupOptions options) throws IOException {
        LOG.debug("Listing assignment groups in course " + options.getCourseId());
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/assignment_groups", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<AssignmentGroup> createAssignmentGroup(String courseId, AssignmentGroup assignmentGroup) throws IOException {
        if(StringUtils.isBlank(courseId)) {
            throw new IllegalArgumentException("Must supply a course ID when creating an assignment group");
        }
        LOG.debug("Creating new assignment group in course " + courseId + ", group name: " + assignmentGroup.getName());
        String url = buildCanvasUrl("courses/" + courseId + "/assignment_groups", Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, assignmentGroup.toPostMap());
        return responseParser.parseToObject(AssignmentGroup.class, response);
    }

    @Override
    public Optional<AssignmentGroup> editAssignmentGroup(String courseId, AssignmentGroup assignmentGroup) throws IOException {
        if(StringUtils.isBlank(courseId) || assignmentGroup == null || assignmentGroup.getId() == null || assignmentGroup.getId() == 0l) {
            throw new IllegalArgumentException("Course ID and assignment group ID must be provided");
        }
        LOG.debug("Modifying assignment group " + assignmentGroup.getId() + " in course " + courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/assignment_groups/" + assignmentGroup.getId(), Collections.emptyMap());
        Response response = canvasMessenger.putToCanvas(oauthToken, url, assignmentGroup.toPostMap());
        return responseParser.parseToObject(AssignmentGroup.class, response);
    }

    @Override
    public Optional<AssignmentGroup> deleteAssignmentGroup(DeleteAssignmentGroupOptions options) throws IOException {
        LOG.debug("Deleting assignment group " + options.getAssignmentGroupId() + " from course " + options.getCourseId());
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/assignment_groups/" + options.getAssignmentGroupId(), options.getOptionsMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
        return responseParser.parseToObject(AssignmentGroup.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<AssignmentGroup>>(){}.getType();
    }

    @Override
    protected Class<AssignmentGroup> objectType() {
        return AssignmentGroup.class;
    }
}
