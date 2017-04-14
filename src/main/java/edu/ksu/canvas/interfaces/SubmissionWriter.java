package edu.ksu.canvas.interfaces;


import edu.ksu.canvas.model.assignment.Progress;
import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.requestOptions.SubmissionOptions;

import java.io.IOException;
import java.util.Optional;

public interface SubmissionWriter extends CanvasWriter<Submission, SubmissionWriter> {

    /**
    * Submits multiple grades of an assignment by section id
    * @param options API options to be used to open a submission
    * @return The submission object created by Canvas
    * @throws IOException If there is an error talking to Canvas
    */
    Optional<Progress> sectionGradeSubmission(SubmissionOptions options)throws IOException;
}
