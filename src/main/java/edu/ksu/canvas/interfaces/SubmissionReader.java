package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.requestOptions.GetSubmissionsOptions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SubmissionReader  extends CanvasReader<Submission, SubmissionReader> {
    /**
     * Retrieve a list of assignment submissions from a course
     *
     * @param options Options class containing required and optional parameters for this API call
     * @return List of assignment submissions in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Submission> getCourseSubmissions(GetSubmissionsOptions options) throws IOException;

    /**
     * Retrieve a list of assignment submissions from a section
     *
     * @param options Options class containing required and optional parameters for this API call
     * @return List of assignment submissions in the section with the section ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Submission> getSectionSubmissions(GetSubmissionsOptions options) throws IOException;

    /**
     * Retrieve a single assignment submission from a course
     * @param options Options class containing required and optional parameters for this API call
     * @return The requested Submission object given the course, assignment and user
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Submission> getSingleCourseSubmission(GetSubmissionsOptions options) throws IOException;

    /**
     * Retrieve a single assignment submission from a section
     * @param options Options class containing required and optional parameters for this API call
     * @return The requested Submission object given the section, assignment and user
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Submission> getSingleSectionSubmission(GetSubmissionsOptions options) throws IOException;
}
