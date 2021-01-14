package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.Deposit;
import edu.ksu.canvas.requestOptions.DeleteCourseOptions;
import edu.ksu.canvas.requestOptions.UploadOptions;

import java.io.IOException;
import java.util.Optional;

public interface CourseWriter extends CanvasWriter<Course, CourseWriter> {
    /**
     * Create a new course in Canvas
     * @param accountId the account ID of the account under which to place this course
     * @param course A course object containing the information needed to create a course in Canvas
     * @return The newly created course
     * @throws IOException When there is an error communicating with Canvas
     */
     Optional<Course> createCourse(String accountId, Course course) throws IOException;

     /**
      * Update a course in Canvas
      * @param course A course object containing the information needed to update a course in Canvas
      * @return The newly updated course
      * @throws IOException When there is an error communicating with Canvas
      */
      Optional<Course> updateCourse(Course course) throws IOException;

    /**
     * Update a course in Canvas
     * @param id The ID of the course to update, this is useful when you want to update a course
     *           by it's SIS ID, for example with "sis_course_id:id-123"
     * @param course A course object containing the information needed to update a course in Canvas
     * @return The newly updated course
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Course> updateCourse(String id, Course course) throws IOException;


    /**
     * @param courseId The ID of the course you wish to delete
     * @return true if the course was deleted
     * @throws IOException When there is an error communicating with Canvas
     */
     Boolean deleteCourse(String courseId) throws IOException;

    /**
     * Delete or conclude a course in Canvas.
     *
     * @param options (specifying the ID and whether to delete or conclude the course)
     * @return true if the operation was successful
     * @throws IOException When there is an error communicating with Canvas
     */
     Boolean deleteCourse(DeleteCourseOptions options) throws IOException;

    /**
     * Start a file upload to a course.
     *
     * @param courseId The id of the course where the file upload will be done
     * @param options Upload file configuration
     * @throws IOException When there is an error communicating with Canvas
     * @return Deposit (This is part of the file upload workflow)
     */
     Optional<Deposit> uploadFile(String courseId, UploadOptions options) throws IOException;
}
