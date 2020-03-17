package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.Tab;
import edu.ksu.canvas.requestOptions.UpdateCourseTabOptions;

/**
 * Methods to modify tabs, which can be applied to courses or groups.
 * <p>
 * See https://canvas.colorado.edu/doc/api/tabs.html for the Canvas API
 * documentation.
 */
public interface TabWriter extends CanvasWriter<Tab, TabWriter> {

    /**
     * Modifies a tab.
     *
     * @param options options specifying the course, tab, and what to modify
     * @return modified tab object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Tab> updateCourseTab(UpdateCourseTabOptions options) throws IOException;
}
