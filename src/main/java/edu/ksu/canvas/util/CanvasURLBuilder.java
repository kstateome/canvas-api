package edu.ksu.canvas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.List;


public class CanvasURLBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(CanvasURLBuilder.class);

    /* Builds parameters in form of ?param[]=value1&param[]=value2&otherParam=someValue*/
    public static String buildCanvasUrl(String canvasBaseUrl, int canvasAPIVersion, String canvasMethod, Map<String, List<String>> parameters) {
        canvasMethod = removeForwardSlashIfExists(canvasMethod);
        String url = canvasBaseUrl + "/api/v" +  canvasAPIVersion + "/" + canvasMethod;
        String finalUrl = url + HttpParameterBuilder.buildParameters(parameters);
        LOG.debug("Built Canvas url - " + finalUrl);
        return finalUrl;
    }

    private static String removeForwardSlashIfExists(String canvasMethod) {
        int beginIndex = canvasMethod.startsWith("/") ? 1 : 0;
        int endIndex = canvasMethod.endsWith("/") ? canvasMethod.length() - 1 : canvasMethod.length();

        return canvasMethod.substring(beginIndex, endIndex);
    }

}
