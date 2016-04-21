package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Enrollment;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by prv on 10/8/15.
 */
public interface EnrollmentsWriter extends CanvasBase {

     Optional<Enrollment> enrollUser(String oauthToken, Integer course_Id, Integer userId) throws InvalidOauthTokenException, IOException;

     Optional<Enrollment> dropUser(String oauthToken, Integer course_id, Long enrollment_id) throws InvalidOauthTokenException, IOException;
}
