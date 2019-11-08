package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.requestOptions.GetSubmissionsOptions;

import java.io.IOException;
import java.util.List;

public interface SubmissionReader  extends CanvasReader<Submission, SubmissionReader> {
    /**
     * Retrieve a list of assignment submissions with user and assignment information optionally included
     *
     * @param options Options class containing required and optional parameters for this API call
     * @return List of assignment submissions in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Submission> getCourseSubmissions(GetSubmissionsOptions options) throws IOException;
    /**
     * Retrieve a list of assignment submissions with user and assignment information optionally included
     *
     * @param options Options class containing required and optional parameters for this API call
     * @return List of assignment submissions in the section with the section ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Submission> getSectionSubmissions(GetSubmissionsOptions options) throws IOException;
}
