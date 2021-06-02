package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Module;
import edu.ksu.canvas.requestOptions.ListModulesOptions;

import java.io.IOException;
import java.util.List;

public interface ModuleReader extends CanvasReader<Module, ModuleReader> {
    /**
     * Retrieve the list of modules in a course.
     *
     * @param options The object holding options for this API call
     * @return List of the course's modules
     * @throws IOException When there is an error communicating with Canvas
     */
    public List<Module> getModulesInCourse(ListModulesOptions options) throws IOException;

}
