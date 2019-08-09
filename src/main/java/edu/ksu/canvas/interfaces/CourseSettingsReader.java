package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CourseSettings;

import java.io.IOException;
import java.util.Optional;

public interface CourseSettingsReader extends CanvasReader<CourseSettings, CourseSettingsReader> {

    /**
     * Returns course settings from Canvas
     * <p>
     * See <a href="https://canvas.instructure.com/doc/api/courses.html#method.courses.api_settings">Get course settings</a>
     * in the Canvas docs for details.
     * @param courseId The Canvas ID of the course or sis_course_id:SISID
     * @return The course settings from Canvas
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<CourseSettings> getCourseSettings(String courseId) throws IOException;
}
