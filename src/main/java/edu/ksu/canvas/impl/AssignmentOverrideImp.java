package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AssignmentOverrideReader;
import edu.ksu.canvas.interfaces.AssignmentOverrideWriter;
import edu.ksu.canvas.model.AssignmentOverride;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AssignmentOverrideImp extends BaseImpl<AssignmentOverride, AssignmentOverrideReader, AssignmentOverrideWriter> implements AssignmentOverrideReader, AssignmentOverrideWriter {
    private static final Logger LOG = Logger.getLogger(AssignmentOverrideImp.class);


    public AssignmentOverrideImp(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
    }

    @Override
    public Optional<AssignmentOverride> createAssignmentOverride(String courseId, AssignmentOverride assignmentOverride) throws IOException {
        if(assignmentOverride.getAssignment_id() == null) {
            throw new IllegalArgumentException("Assignment override must have an assignment ID");
        }
        LOG.debug("Creating an assignment override in course " + courseId + " for assignment " + assignmentOverride.getAssignment_id());
        String url = buildCanvasUrl("courses/" + courseId + "/assignments/" + assignmentOverride.getAssignment_id() + "/overrides", Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, wrapJsonObject(assignmentOverride));
        return responseParser.parseToObject(AssignmentOverride.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<AssignmentOverride>>(){}.getType();
    }

    @Override
    protected Class<AssignmentOverride> objectType() {
        return AssignmentOverride.class;
    }

}
