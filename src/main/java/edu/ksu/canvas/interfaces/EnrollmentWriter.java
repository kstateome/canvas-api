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

     Optional<Enrollment> enrollUser(Enrollment enrollment, boolean isSectionEnrollment) throws InvalidOauthTokenException, IOException;

     /**
      *
      * Drop a user enrolled in a course passing the UnEnrollOptions string in the task param.
      *
      * @param courseId - Canvas course identifier
      * @param enrollmentId - Canvas enrollment identifier
      * @param unEnrollOption - UnEnrollOption enum toString value of delete, conclude, inactivate or deactivate
      * @return Populated Enrollment Dropped when successful
      * @throws InvalidOauthTokenException
      * @throws IOException
      */
     Optional<Enrollment> dropUser(String courseId, String enrollmentId, String unEnrollOption) throws InvalidOauthTokenException, IOException;

     /**
      *
      *  Drop a user enrolled in a course using the Unenrollment option of DELETE.
      *
      * @param courseId - Canvas course identifier
      * @param enrollmentId enrollmentId - Canvas enrollment identifier
      * @return Populated Enrollment Dropped when successful
      * @throws InvalidOauthTokenException
      * @throws IOException
      */
     Optional<Enrollment> dropUser(String courseId, String enrollmentId) throws InvalidOauthTokenException, IOException;
}
