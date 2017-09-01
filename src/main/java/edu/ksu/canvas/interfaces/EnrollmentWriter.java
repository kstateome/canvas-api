package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Enrollment;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by prv on 10/8/15.
 */
public interface EnrollmentWriter extends CanvasWriter<Enrollment, EnrollmentWriter> {

     Optional<Enrollment> enrollUser(Enrollment enrollment) throws InvalidOauthTokenException, IOException;

     Optional<Enrollment> dropUser(String courseId, String enrollmentId) throws InvalidOauthTokenException, IOException;
}
