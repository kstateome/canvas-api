package edu.ksu.canvas.util;

import edu.ksu.canvas.constants.CanvasConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/*
 * This class transforms a map into a parameter string
 *
 * EX: '{ array[]: [a, b], item: [c] } would return the following string
 * ?array[]=a&array[]=b&item=c
 */
public class HttpParameterBuilder {
    private static final Logger LOG = Logger.getLogger(HttpParameterBuilder.class);

    public static String buildParameters(Map<String, List<String>> parameters) {
        return parameters.entrySet().stream()
                .map(HttpParameterBuilder::buildParameter)
                .reduce((a, b) -> a + b)
                .filter(s -> s.length() > 0)
                .map(s -> s.substring(1))
                .map(paramString -> "?" + paramString)
                .orElse("");
    }

    private static String buildParameter(Map.Entry<String, List<String>> entry) {
        String paramName = entry.getKey();
        return entry.getValue()
                .stream()
                .reduce("", (a, paramValue) -> {
                    String urlParams = "";
                    try {
                        urlParams = (a + "&" + URLEncoder.encode(paramName, CanvasConstants.URLENCODING_TYPE)
                                + "=" + URLEncoder.encode(paramValue, CanvasConstants.URLENCODING_TYPE));

                    } catch (UnsupportedEncodingException e){
                        LOG.warn("Failed to encode parameter " + paramName);
                    }
                    return urlParams;
                });
    }
}
