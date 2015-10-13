package edu.ksu.canvas.impl;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.AssignmentReader;
import edu.ksu.canvas.interfaces.AssignmentWriter;
import edu.ksu.canvas.model.Assignment;
import edu.ksu.canvas.net.RestClient;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class AssignmentsImpl extends BaseImpl implements AssignmentReader, AssignmentWriter{
    private static final Logger LOG = Logger.getLogger(AssignmentReader.class);

    public AssignmentsImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    @Override
    public Optional<Assignment> getSingleCourse(String oauthToken, String courseId, String assignmentId) throws IOException {
        return null;
    }

    @Override
    public Optional<Assignment> createAssignment(String oauthToken, Assignment assignment) throws InvalidOauthTokenException, IOException {
        return null;
    }

    @Override
    public Boolean deleteAssigment(String oauthToken, String courseId, String assignmentId) throws InvalidOauthTokenException, IOException {
        return null;
    }

}
