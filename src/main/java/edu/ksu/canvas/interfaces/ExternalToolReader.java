package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import edu.ksu.canvas.model.ExternalTool;
import edu.ksu.canvas.requestOptions.ListExternalToolsOptions;

public interface ExternalToolReader extends CanvasReader<ExternalTool, ExternalToolReader>{

    public List<ExternalTool> listExternalToolsInAccount(ListExternalToolsOptions options) throws IOException;
    public List<ExternalTool> listExternalToolsInCourse(ListExternalToolsOptions options) throws IOException;
    public List<ExternalTool> listExternalToolsInGroup(ListExternalToolsOptions options) throws IOException;
}
