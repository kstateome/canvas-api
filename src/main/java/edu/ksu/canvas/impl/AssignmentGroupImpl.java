package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AssignmentGroupReader;
import edu.ksu.canvas.interfaces.AssignmentGroupWriter;
import edu.ksu.canvas.model.assignment.AssignmentGroup;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
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
     */
    public AssignmentGroupImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
    }

    private static final Logger LOG = Logger.getLogger(AssignmentGroupImpl.class);

    @Override
    public List<AssignmentGroup> listAssignmentGroup(ListAssignmentGroupOptions options) throws IOException {
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/assignment_groups", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<AssignmentGroup> createAssignmentGroup(String courseId, AssignmentGroup assignmentGroup) throws IOException {
        if(StringUtils.isBlank(assignmentGroup.getName())) {
            throw new IllegalArgumentException("Assignment must have a name");
        }
        String url = buildCanvasUrl("courses/" + courseId + "/assignment_groups", Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, assignmentGroup.toPostMap());
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
