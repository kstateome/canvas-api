package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.net.Response;


import java.io.IOException;
import java.util.Map;

public interface CanvasMessenger {
    Response getFromCanvas(String oauthToken, String url) throws InvalidOauthTokenException, IOException;
    //TODO: Should probably make this parameter list more sane
    Response sendToCanvas(String oauthToken, String url, Map<String, String> parameters) throws InvalidOauthTokenException, IOException;
}
