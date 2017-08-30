package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.model.ExternalTool;
import edu.ksu.canvas.requestOptions.ListExternalToolsOptions;

public interface ExternalToolReader extends CanvasReader<ExternalTool, ExternalToolReader>{


    /**
     * Retrieve a specific external tool configuration in a given account
     * @param accountId The Canvas ID or sis_account_id:[SIS ID] for the account to query
     * @param toolId The Canvas ID of the tool to query
     * @return The requested tool's configuration
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<ExternalTool> getExternalToolInAccount(String accountId, Integer toolId) throws IOException;

    /**
     * Retrieve a specific external tool configuration in a given course
     * @param courseId The Canvas ID or sis_course_id:[SIS ID] for the course to query
     * @param toolId The Canvas ID of the tool to query
     * @return The requested tool's configuration
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<ExternalTool> getExternalToolInCourse(String courseId, Integer toolId) throws IOException;

    /**
     * Return a list of external tools registered to an account
     * @param options Parameters that alter which tools are returned by this call
     * @return List of external tools in the account
     * @throws IOException if there is an error communicating with Canvas
     */
    public List<ExternalTool> listExternalToolsInAccount(ListExternalToolsOptions options) throws IOException;

    /**
     * Return a list of external tools registered to a course
     * @param options Parameters that alter which tools are returned by this call
     * @return List of external tools in the course
     * @throws IOException if there is an error communicating with Canvas
     */
    public List<ExternalTool> listExternalToolsInCourse(ListExternalToolsOptions options) throws IOException;

    /**
     * Return a list of external tools registered to a group
     * @param options Parameters that alter which tools are returned by this call
     * @return List of external tools in the group
     * @throws IOException if there is an error communicating with Canvas
     */
    public List<ExternalTool> listExternalToolsInGroup(ListExternalToolsOptions options) throws IOException;
}
