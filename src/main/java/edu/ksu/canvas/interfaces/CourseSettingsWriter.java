package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CourseSettings;

import java.io.IOException;
import java.util.Optional;

public interface CourseSettingsWriter extends CanvasWriter<CourseSettings, CourseSettingsWriter> {

    /**
     * Push modified course settings to Canvas.
     * <p>
     * Only fields with the @CanvasField annotation can be changed.
     * @param courseId Canvas ID (or sis_course_id:XXXX) of the course
     * @param settings New settings to push to Canvas
     * @return The updated course settings
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<CourseSettings> updateCourseSettings(String courseId, CourseSettings settings) throws IOException;
}
