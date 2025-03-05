package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Progress;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public interface ProgressReader extends CanvasReader<Progress, ProgressReader> {
    /**
     * Returns the progress of an asynchronous process by progress URL
     * @param url The URL to query for progress
     * @return A Progress object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Progress> getProgress(String url) throws IOException, URISyntaxException, ParseException;

    /**
     * Returns the progress of an asynchronous process by progress ID
     * @param progressId ID of the progress object to query
     * @return The requested Progress object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Progress> getProgress(Long progressId) throws IOException, URISyntaxException, ParseException;
}
