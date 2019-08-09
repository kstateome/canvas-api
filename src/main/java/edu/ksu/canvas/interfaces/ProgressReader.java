package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Progress;

import java.io.IOException;
import java.util.Optional;

public interface ProgressReader extends CanvasReader<Progress, ProgressReader> {
    /**
     * Returns a content migration.
     * @param url The course ID for this API call
     * @return A Progress object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Progress> getProgress(String url) throws IOException;
}
