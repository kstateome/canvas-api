package edu.ksu.canvas;

import edu.ksu.canvas.interfaces.CanvasReader;
import edu.ksu.canvas.model.TestCanvasModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TestCanvasReader extends CanvasReader<TestCanvasModel, TestCanvasReader> {
   List<TestCanvasModel> getTestModels(String url) throws IOException;
   Optional<TestCanvasModel> getTestModel(String url) throws IOException;
}
