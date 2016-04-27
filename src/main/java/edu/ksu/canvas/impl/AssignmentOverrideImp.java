package edu.ksu.canvas.impl;

import com.google.gson.JsonObject;
import edu.ksu.canvas.interfaces.AssignmentOverrideReader;
import edu.ksu.canvas.interfaces.AssignmentOverrideWriter;
import edu.ksu.canvas.model.AssignmentOverride;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import edu.ksu.canvas.exception.MessageUndeliverableException;
import edu.ksu.canvas.exception.OauthTokenRequiredException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AssignmentOverrideImp extends BaseImpl<AssignmentOverride, AssignmentOverrideReader> implements AssignmentOverrideReader, AssignmentOverrideWriter {
    private static final Logger LOG = Logger.getLogger(AssignmentOverrideImp.class);


    public AssignmentOverrideImp(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    @Override
    public Optional<AssignmentOverride> createAssignmentOverride(String courseId, String assignmentId, List<Integer> studentIds, String title) throws MessageUndeliverableException, IOException, OauthTokenRequiredException {
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion,
                "courses/" + courseId + "/assignments/" + assignmentId + "/overrides", Collections.emptyMap());
        JsonObject requestBody = new JsonObject();
        JsonObject override = new JsonObject();
        override.addProperty("title", title);
        override.add("student_ids", getDefaultGsonParser().toJsonTree(studentIds));
        requestBody.add("assignment_override", override);

        Response response = canvasMessenger.sendToJsonCanvas(oauthToken, url, requestBody);
        if(response.getErrorHappened() || response.getResponseCode() != 201){
            LOG.error("Error creating assignment override for course: " + courseId + " and assignment: " + assignmentId);
            LOG.debug(response.getContent());
            return null;
        }
        return responseParser.parseToObject(AssignmentOverride.class, response);
    }

    @Override
    protected Type listType() {
        return null;
    }

    @Override
    protected Class objectType() {
        return null;
    }

}
