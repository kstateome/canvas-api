package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.requestOptions.GetSubmissionsOptions;

import java.io.IOException;
import java.util.List;

public interface SubmissionReader  extends CanvasReader<Submission, SubmissionReader> {
    /**
     * Retrieve a list of quiz submissions with user and quiz information optionally included
     *
     * @param options Options class containing required and optional parameters for this API call
     * @return List of quiz submissions in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Submission> getSubmissions(GetSubmissionsOptions options) throws IOException;
}
