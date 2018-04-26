package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.AssignmentOverrideReader;
import edu.ksu.canvas.interfaces.AssignmentOverrideWriter;
import edu.ksu.canvas.model.assignment.AssignmentOverride;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AssignmentOverrideImpl extends BaseImpl<AssignmentOverride, AssignmentOverrideReader, AssignmentOverrideWriter> implements AssignmentOverrideReader, AssignmentOverrideWriter {
    private static final Logger LOG = Logger.getLogger(AssignmentOverrideImpl.class);


    public AssignmentOverrideImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                                  int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<AssignmentOverride> createAssignmentOverride(String courseId, AssignmentOverride assignmentOverride) throws IOException {
        if(assignmentOverride.getAssignmentId() == null) {
            throw new IllegalArgumentException("Assignment override must have an assignment ID");
        }
        LOG.debug("Creating an assignment override in course " + courseId + " for assignment " + assignmentOverride.getAssignmentId());
        String url = buildCanvasUrl("courses/" + courseId + "/assignments/" + assignmentOverride.getAssignmentId() + "/overrides", Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, assignmentOverride.toJsonObject(serializeNulls));
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
