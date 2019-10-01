package edu.ksu.canvas.interfaces;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.requestOptions.ListCourseAssignmentSubmissionsOptions;

public interface SubmissionReader  extends CanvasReader<Submission, SubmissionReader> {

    /**
     * Retrieve a list of assignment submissions
     * @param options Options class containing required and optional parameters for this API call
     * @return List of submissions for the course/assignment combination
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Submission> listCourseAssignmentSubmissions(ListCourseAssignmentSubmissionsOptions options) throws IOException;

    Optional<Submission> getSingleSubmission(ListCourseAssignmentSubmissionsOptions options) throws IOException;

}
