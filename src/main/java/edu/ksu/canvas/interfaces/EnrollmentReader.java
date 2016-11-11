package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.requestOptions.GetEnrollmentOptions;

import java.io.IOException;
import java.util.List;

public interface EnrollmentReader extends CanvasReader<Enrollment, EnrollmentReader> {
    /**
     * Retrieve a user's enrollments. Object ID in the options class must be a user ID.
     * @param options API options available to this call
     * @return List of the user's enrollments
     * @throws IOException When there is an error communicating with Canvas
     */
     List<Enrollment> getUserEnrollments(GetEnrollmentOptions options) throws IOException;

     /**
      * Retrieve enrollments in a given section. Object ID in the options class must be a section ID.
      * @param options API options available to this call
      * @return List of enrollments in a section
      * @throws IOException When there is an error communicating with Canvas
      */
     List<Enrollment> getSectionEnrollments(GetEnrollmentOptions options) throws IOException;

     /**
      * Retrieve enrollments in a given course. Object ID in the options class must be a course ID.
      * @param options API options available to this call
      * @return List of enrollments in a course
      * @throws IOException When there is an error communicating with Canvas
      */
     List<Enrollment> getCourseEnrollments(GetEnrollmentOptions options) throws IOException;

}
