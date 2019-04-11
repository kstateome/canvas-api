package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import edu.ksu.canvas.model.Tab;

/**
 * Methods to read information about tabs, which can be applied to courses
 * or groups.
 * <p>
 * See https://canvas.colorado.edu/doc/api/tabs.html for the Canvas API
 * documentation.
 */
public interface TabReader extends CanvasReader<Tab, TabReader> {

    /**
     * Lists the navigation tabs for the given course.
     *
     * @param courseId identifies the course
     * @param includeExternalTools includes external tool tabs in the results
     * @return list of available tabs
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Tab> listAvailableCourseTabs(String courseId, boolean includeExternalTools) throws IOException;
}
