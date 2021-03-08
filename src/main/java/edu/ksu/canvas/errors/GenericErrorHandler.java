package edu.ksu.canvas.errors;

import com.google.gson.Gson;
import edu.ksu.canvas.exception.CanvasException;
import edu.ksu.canvas.impl.GsonResponseParser;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * The error handler that should be used when creation of a Canvas object fails.
 */
public class GenericErrorHandler implements ErrorHandler {

    private Pattern coursePattern = Pattern.compile("/api/v1/accounts/(\\d+|\\w+:\\w+)/courses");
    private Pattern loginPattern = Pattern.compile("/api/v1/accounts/\\d+/logins");

    @Override
    public boolean shouldHandle(HttpRequest httpRequest, HttpResponse httpResponse) {
        return (coursePattern.matcher(httpRequest.getRequestLine().getUri()).find() ||
                loginPattern.matcher(httpRequest.getRequestLine().getUri()).find()) &&
                httpResponse.getStatusLine().getStatusCode() == 400 &&
                httpResponse.getEntity().getContentType().getValue().contains("application/json");
    }

    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        Gson gson = GsonResponseParser.getDefaultGsonParser(false);
        try {
            String entityString = EntityUtils.toString(httpResponse.getEntity());
            GenericErrorResponse response = gson.fromJson(entityString, GenericErrorResponse.class);
            if (response.getErrors() != null) {
                throw new CanvasException("Failed to create user.", httpRequest.getRequestLine().getUri(), response);
            }
        } catch (IOException e) {
            // Ignore.
        }
    }
}
