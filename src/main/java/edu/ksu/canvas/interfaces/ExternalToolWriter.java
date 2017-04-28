package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.ExternalTool;

public interface ExternalToolWriter extends CanvasWriter<ExternalTool, ExternalToolWriter>{

    /**
     * Create an External tool in the specified Canvas course
     * @param courseId Canvas ID or course_sis_id:[SIS ID] for the course to create the tool in
     * @param tool The tool to create. Must have name, privacy level, consumer key, shared secret and either url or domain
     * @return The newly created external tool with the Canvas assigned ID
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<ExternalTool> createExternalToolInCourse(String courseId, ExternalTool tool) throws IOException;

    /**
     * Create an External tool in the specified Canvas account
     * @param accountId Canvas ID or account_sis_id:[SIS ID] for the account to create the tool in
     * @param tool The tool to create. Must have name, privacy level, consumer key, shared secret and either url or domain
     * @return The newly created external tool with the Canvas assigned ID
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<ExternalTool> createExternalToolInAccount(String accountId, ExternalTool tool) throws IOException;
}
