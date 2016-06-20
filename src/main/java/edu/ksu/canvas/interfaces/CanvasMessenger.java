package edu.ksu.canvas.interfaces;

import com.google.gson.JsonObject;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.net.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface CanvasMessenger {
    List<Response> getFromCanvas(String oauthToken, String url) throws InvalidOauthTokenException, IOException;
    List<Response> getFromCanvas(String oauthToken, String url, Consumer<Response> consumer) throws InvalidOauthTokenException, IOException;
    //TODO: Should probably make this parameter list more sane
    Response sendToCanvas(String oauthToken, String url, Map<String, String> parameters) throws InvalidOauthTokenException, IOException;
    Response sendToJsonCanvas(String oauthToken, String url, JsonObject requestBody) throws InvalidOauthTokenException, IOException;
    Response deleteFromCanvas(String oauthToken, String url,Map<String,String> parameters) throws InvalidOauthTokenException, IOException;
    Response getSingleResponseFromCanvas(String oauthToken, String url) throws InvalidOauthTokenException, IOException;
    Response putToCanvas(String oauthToken, String url, Map<String, String> parameters) throws InvalidOauthTokenException, IOException;
}
