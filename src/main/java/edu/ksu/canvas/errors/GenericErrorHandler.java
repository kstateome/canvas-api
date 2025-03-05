package edu.ksu.canvas.errors;

import com.google.gson.Gson;
import edu.ksu.canvas.exception.CanvasException;
import edu.ksu.canvas.impl.GsonResponseParser;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * The error handler that should be used when creation of a Canvas object fails.
 */
public class GenericErrorHandler implements ErrorHandler {

    private Pattern coursePattern = Pattern.compile("/api/v1/accounts/(\\d+|\\w+:\\w+)/courses");
    private Pattern loginPattern = Pattern.compile("/api/v1/accounts/\\d+/logins");

    @Override
    public boolean shouldHandle(ClassicHttpRequest httpRequest, ClassicHttpResponse httpResponse) {
        return (coursePattern.matcher(httpRequest.getRequestUri()).find() ||
                loginPattern.matcher(httpRequest.getRequestUri()).find()) &&
                httpResponse.getCode() == 400 &&
                httpResponse.getEntity().getContentType().contains("application/json");
    }

    @Override
    public void handle(ClassicHttpRequest httpRequest, ClassicHttpResponse httpResponse) throws ParseException {
        Gson gson = GsonResponseParser.getDefaultGsonParser(false);
        try {
            String entityString = EntityUtils.toString(httpResponse.getEntity());
            GenericErrorResponse response = gson.fromJson(entityString, GenericErrorResponse.class);
            if (response.getErrors() != null) {
                throw new CanvasException("Failed to create user.", httpRequest.getRequestUri(), response);
            }
        } catch (IOException e) {
            // Ignore.
        }
    }
}
