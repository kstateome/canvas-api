package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.requestOptions.GetSingleCourseOptions;
import edu.ksu.canvas.requestOptions.ListActiveCoursesInAccountOptions;
import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions;
import edu.ksu.canvas.requestOptions.ListUserCoursesOptions;

/**
 * Methods to read information from and about courses
 */
public interface CourseReader extends CanvasReader<Course, CourseReader> {

    /**
     * Returns the list of active courses for the current user 
     * @param options The object holding options for this API call
     * @return List of courses for the user matching any optional criteria
     * @throws IOException When there is an error communicating with Canvas
     */
     List<Course> listCurrentUserCourses(ListCurrentUserCoursesOptions options) throws IOException;

    /**
     * Returns the list of active courses for the a user
     * @param options The object holding options for this API call
     * @return List of courses for the user matching any optional criteria
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Course> listUserCourses(ListUserCoursesOptions options) throws  IOException;

    /**
     * Retrieve a specific course from Canvas by its Canvas ID number
     * @param options The object holding options for this API call
     * @return The course returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
     Optional<Course> getSingleCourse(GetSingleCourseOptions options) throws IOException;

     /**
      * Retrieve a list of all courses on a given account
      * @param options AccountCourseListOptions object representing params to this API call
      * @return List of courses in the account
      * @throws IOException When there is an error communicating with Canvas
      */
     List<Course> listActiveCoursesInAccount(ListActiveCoursesInAccountOptions options) throws IOException;
}
