package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.requestOptions.UnEnrollOptions;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by prv on 10/8/15.
 */
public interface EnrollmentWriter extends CanvasWriter<Enrollment, EnrollmentWriter> {

     /**
      * Enrolls a user in a course has been deprecated, use enrollUserInCourse or enrollUserInSection instead.
      * For backward compatibility, it will enroll a user in a course.
      *
      * @param  enrollment partially populated Enrollment object
      * @return optional enrollment
      * @throws IllegalArgumentException when the enrollment courseID is not set
      * @throws IOException runtime error
      * @throws InvalidOauthTokenException runtime error
      */
     @Deprecated
     Optional<Enrollment> enrollUser(Enrollment enrollment) throws InvalidOauthTokenException, IOException, IllegalArgumentException;

     /**
      * Enrolls a user in a course
      *
      * @param enrollment partially populated Enrollment object
      * @return optional enrollment
      * @throws IllegalArgumentException when the enrollment courseID is not set
      * @throws IOException runtime error
      * @throws InvalidOauthTokenException runtime error
      */
     Optional<Enrollment> enrollUserInCourse(Enrollment enrollment) throws InvalidOauthTokenException, IOException, IllegalArgumentException;

     /**
      * Enrolls a user in a section.
      *
      * @param enrollment partially populated Enrollment object
      * @return optional enrollment
      * @throws IllegalArgumentException when the enrollment courseSectionId is not set
      * @throws IOException runtime error
      * @throws InvalidOauthTokenException runtime error
      */
     Optional<Enrollment> enrollUserInSection(Enrollment enrollment) throws InvalidOauthTokenException, IOException, IllegalArgumentException;

     /**
      *
      * Drop a user enrolled in a course passing the UnEnrollOptions string in the task param.
      *
      * @param courseId - Canvas course identifier
      * @param enrollmentId - Canvas enrollment identifier
      * @param unEnrollOption - UnEnrollOption enum value of delete, conclude, inactivate or deactivate
      * @return Populated Enrollment Dropped when successful
      * @throws IOException runtime error
      * @throws InvalidOauthTokenException runtime error
      */
     Optional<Enrollment> dropUser(String courseId, String enrollmentId, UnEnrollOptions unEnrollOption) throws InvalidOauthTokenException, IOException;

     /**
      *
      *  Drop a user enrolled in a course using the Unenrollment option of DELETE.
      *
      * @param courseId - Canvas course identifier
      * @param enrollmentId enrollmentId - Canvas enrollment identifier
      * @return Populated Enrollment Dropped when successful
      * @throws IOException runtime error
      * @throws InvalidOauthTokenException runtime error
      */
     Optional<Enrollment> dropUser(String courseId, String enrollmentId) throws InvalidOauthTokenException, IOException;
}
