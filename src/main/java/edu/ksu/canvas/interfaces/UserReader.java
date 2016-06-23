package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.enums.CourseIncludes;
import edu.ksu.canvas.enums.EnrollmentType;
import edu.ksu.canvas.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserReader extends CanvasReader<User, UserReader> {
    /**
     * Retrieve a list of users in a course
     * @param courseId Required. Course ID of course to query.
     * @param enrollmentTypes Optional list of enrollment types to include. An empty list will not restrict by enrollment type.
     * @param enrollmentRoleId Optional role ID which will restrict the returned users to only the given role
     * @param includes Optional list of extra information to include in the returned user objects
     * @return List of users in a course
     * @throws IOException When there is an error communicating with Canvas
     */
    List<User> getUsersInCourse(String courseId, List<EnrollmentType> enrollmentTypes, Optional<Integer> enrollmentRoleId, List<CourseIncludes> includes) throws IOException;
}
