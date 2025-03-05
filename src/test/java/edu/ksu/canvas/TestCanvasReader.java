package edu.ksu.canvas;

import edu.ksu.canvas.interfaces.CanvasReader;
import edu.ksu.canvas.model.TestCanvasModel;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public interface TestCanvasReader extends CanvasReader<TestCanvasModel, TestCanvasReader> {
   List<TestCanvasModel> getTestModels(String url) throws IOException, URISyntaxException, ParseException;
   Optional<TestCanvasModel> getTestModel(String url) throws IOException, URISyntaxException, ParseException;
}
