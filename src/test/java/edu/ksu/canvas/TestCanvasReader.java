package edu.ksu.canvas;

import edu.ksu.canvas.interfaces.CanvasReader;
import edu.ksu.canvas.model.TestCanvasModel;

import java.io.IOException;
import java.util.List;

public interface TestCanvasReader extends CanvasReader<TestCanvasModel, TestCanvasReader> {
   List<TestCanvasModel> getTestModels() throws IOException;
}
