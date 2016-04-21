package edu.ksu.canvas;

import edu.ksu.canvas.impl.CoursesImpl;
import edu.ksu.canvas.interfaces.CanvasBase;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.RestClientImpl;

public class CanvasApiFactory {

    public static final Integer CANVAS_API_VERSION = 1;
    private String canvasBaseUrl;

    public CanvasApiFactory(String canvasBaseUrl) {
        this.canvasBaseUrl = canvasBaseUrl;
    }

    public <T extends CanvasBase> T getApiClass(Class<T> type, String oauthToken) {
        RestClient restClient = new RestClientImpl();
        if(type.equals(CourseReader.class)) {
            return (T)new CoursesImpl(canvasBaseUrl, CANVAS_API_VERSION, oauthToken, restClient);
        }
        return null;
    }
}
