package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.ExternalTool;

public interface ExternalToolWriter extends CanvasWriter<ExternalTool, ExternalToolWriter>{

    /**
     * Create an External tool in the specified Canvas course
     * @param courseId Canvas ID or sis_course_id:[SIS ID] for the course to create the tool in
     * @param tool The tool to create. Must have name, privacy level, consumer key, shared secret and either url or domain
     * @return The newly created external tool with the Canvas assigned ID
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<ExternalTool> createExternalToolInCourse(String courseId, ExternalTool tool) throws IOException;

    /**
     * Create an External tool in the specified Canvas account
     * @param accountId Canvas ID or sis_account_id:[SIS ID] for the account to create the tool in
     * @param tool The tool to create. Must have name, privacy level, consumer key, shared secret and either url or domain
     * @return The newly created external tool with the Canvas assigned ID
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<ExternalTool> createExternalToolInAccount(String accountId, ExternalTool tool) throws IOException;

    /**
     * Modify the settings of an external tool associated with a course
     * @param courseId Canvas ID or sis_course_id:[SIS ID] for the course
     * @param tool The tool with settings to be modified. Tool ID must be present
     * @return The updated tool object
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<ExternalTool> editExternalToolInCourse(String courseId, ExternalTool tool) throws IOException;

    /**
     * Modify the settings of an external tool associated with an account
     * @param accountId Canvas ID or sis_account_id:[SIS ID] for the account that contains the tool
     * @param tool The tool with settings to be modified. Tool ID must be present
     * @return The updated tool object
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<ExternalTool> editExternalToolInAccount(String accountId, ExternalTool tool) throws IOException;

    /**
     * Delete an external tool from a course.
     * @param courseId Canvas course ID or sis_course_id:[SIS ID] for the course to delete the tool from
     * @param toolId The Canvas ID of the tool to delete
     * @return The deleted tool. Should have a workflow state of "deleted"
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<ExternalTool> deleteExternalToolInCourse(String courseId, Integer toolId) throws IOException;

    /**
     * Delete an external tool from an account.
     * @param accountId Canvas account ID or sis_account_id:[SIS ID] for the account to delete the tool from
     * @param toolId Canvas ID of the tool to delete
     * @return The deleted tool. Should have a workflow state of "deleted"
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<ExternalTool> deleteExternalToolInAccount(String accountId, Integer toolId) throws IOException;
}
