package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.enums.CourseIncludes;
import edu.ksu.canvas.enums.EnrollmentType;
import edu.ksu.canvas.enums.CourseState;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.requestmodel.AccountCourseListOptions;

/**
 * Methods to read information from and about courses
 */
public interface CourseReader extends CanvasReader<Course, CourseReader> {

    /**
     * Returns the list of active courses for the current user 
     * @param enrollmentType If present, only return the courses where the user is enrolled as this type
     * @param enrollmentRoleId If present, only return courses where the user is enrolled with the specified course-level role.
     * @param includes List of optional items to include in the returned courses
     * @param states If present, return only courses in the given state(s)
     * @return List of courses for the user matching any optional criteria
     * @throws IOException When there is an error communicating with Canvas
     */
     List<Course> listCourses(Optional<EnrollmentType> enrollmentType, Optional<Integer> enrollmentRoleId, List<CourseIncludes> includes, List<CourseState> states) throws IOException;

    /**
     * Retrieve a specific course from Canvas by its Canvas ID number
     * @param courseId The Canvas ID of the course
     * @param includes List of optional items to include in the returned course
     * @return The course returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
     Optional<Course> getSingleCourse(String courseId, List<CourseIncludes> includes) throws IOException;

     /**
      * Retrieve a list of all courses on a given account
      * @param options AccountCourseListOptions object representing params to this API call
      * @return List of courses in the account
      * @throws IOException When there is an error communicating with Canvas
      */
     List<Course> listActiveCoursesInAccount(AccountCourseListOptions options) throws IOException;
}
