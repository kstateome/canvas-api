package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.requestOptions.EnrollUserOptions;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by prv on 10/8/15.
 */
public interface EnrollmentWriter extends CanvasWriter<Enrollment, EnrollmentWriter> {

     /**
      * Enrolls a user in a course using the defaults defined by Canvas.
      *
      * @param courseId Canvas course identifier
      * @param userId Canvas user identifier
      * @return populated Enrollment object that was created
      * @throws InvalidOauthTokenException
      * @throws IOException
      */
     Optional<Enrollment> enrollUser(String courseId, String userId) throws InvalidOauthTokenException, IOException;

     /**
      * Enrolls a user in a course, allowing all the options exposed by Canvas
      * to be changed.
      *
      * @param courseId Canvas course identifier
      * @param options the object holding options for the API call
      * @return populated Enrollment object that was created
      * @throws InvalidOauthTokenException
      * @throws IOException
      */
     Optional<Enrollment> enrollUser(String courseId, EnrollUserOptions options) throws InvalidOauthTokenException,
               IOException;

     Optional<Enrollment> dropUser(String courseId, String enrollmentId) throws InvalidOauthTokenException, IOException;
}
