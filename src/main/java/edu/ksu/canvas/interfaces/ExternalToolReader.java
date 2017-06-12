package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.model.ExternalTool;
import edu.ksu.canvas.requestOptions.ListExternalToolsOptions;

public interface ExternalToolReader extends CanvasReader<ExternalTool, ExternalToolReader>{

    public Optional<ExternalTool> getExternalToolInCourse(String courseId, Integer toolId) throws IOException;
    public Optional<ExternalTool> getExternalToolInAccount(String accountId, Integer toolId) throws IOException;
    public List<ExternalTool> listExternalToolsInAccount(ListExternalToolsOptions options) throws IOException;
    public List<ExternalTool> listExternalToolsInCourse(ListExternalToolsOptions options) throws IOException;
    public List<ExternalTool> listExternalToolsInGroup(ListExternalToolsOptions options) throws IOException;
}
