package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
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

    /**
     * Get a list of pages in a course.
     * NOTE: Page objects returned by this method will not have a body!
     * The Canvas API does not return the body when calling this endpoint, allegedly for performance reasons
     * @param courseId Canvas ID of the course
     * @return List of Page objects (without a body)
     * @throws IOException
     */
    public List<Page> listPagesInCourse(String courseId) throws IOException;

    /**
     * Get a list of pages in a group.
     * NOTE: Page objects returned by this method will not have a body!
     * The Canvas API does not return the body when calling this endpoint, allegedly for performance reasons
     * @param groupId Canvas Group ID of the group
     * @return List of Page objects (without a body)
     * @throws IOException
     */
    public List<Page> listPagesInGroup(String groupId) throws IOException;
}
