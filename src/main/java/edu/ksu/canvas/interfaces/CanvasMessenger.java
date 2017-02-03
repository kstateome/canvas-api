package edu.ksu.canvas.interfaces;

import com.google.gson.JsonObject;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.oauth.OauthToken;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface CanvasMessenger {
    List<Response> getFromCanvas(OauthToken oauthToken, String url) throws InvalidOauthTokenException, IOException;
    List<Response> getFromCanvas(OauthToken oauthToken, String url, Consumer<Response> consumer) throws InvalidOauthTokenException, IOException;
    //TODO: Should probably make this parameter list more sane
    Response sendToCanvas(OauthToken oauthToken, String url, Map<String, List<String>> parameters) throws InvalidOauthTokenException, IOException;
    Response sendJsonPostToCanvas(OauthToken oauthToken, String url, JsonObject requestBody) throws InvalidOauthTokenException, IOException;
    Response sendJsonPutToCanvas(OauthToken oauthToken, String url, JsonObject requestBody) throws InvalidOauthTokenException, IOException;
    Response deleteFromCanvas(OauthToken oauthToken, String url, Map<String, List<String>> parameters) throws InvalidOauthTokenException, IOException;
    Response getSingleResponseFromCanvas(OauthToken oauthToken, String url) throws InvalidOauthTokenException, IOException;
    Response putToCanvas(OauthToken oauthToken, String url, Map<String, List<String>> parameters) throws InvalidOauthTokenException, IOException;
}
