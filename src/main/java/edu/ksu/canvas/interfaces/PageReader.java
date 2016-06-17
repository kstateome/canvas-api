package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.Page;

public interface PageReader extends CanvasReader<Page, PageReader> {

    /**
     * Retrieve a specified page associated with a course.
     * @param courseId The Canvas course ID
     * @param pageName The unique URL identifier of a page within the course
     * @return The requested Page object
     * @throws IOException
     */
    public Optional<Page> getCoursePage(Integer courseId, String pageUrl) throws IOException;

    /**
     * Retrieve a specified page associated with a group.
     * @param groupId The Canvas group ID
     * @param pageName The unique URL identifier of a page within the group
     * @return The requested Page object
     * @throws IOException
     */
    public Optional<Page> getGroupPage(Integer groupId, String pageUrl) throws IOException;
}
