package edu.ksu.canvas.net;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* Class extracted from methods in CanvasUtil */
public class RestClient {
    private static final Logger LOG = Logger.getLogger(RestClient.class);

    public static Response sendApiGet(@NotNull String token, @NotNull String url, 
                                      int connectTimeout, int readTimeout) throws IOException {
        LOG.debug("url - " + url);
        Long beginTime = System.currentTimeMillis();
        Response response = new Response();
        URL apiUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection)apiUrl.openConnection();
        con.setConnectTimeout(connectTimeout);
        con.setReadTimeout(readTimeout);
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer" + " " + token);


        //deal with the actual content
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        response.setContent(content.toString());
        response.setResponseCode(con.getResponseCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("Canvas API call took: " + (endTime - beginTime) + "ms");

        //deal with pagination
        String linkHeader = con.getHeaderField("Link");
        if(linkHeader == null) {
            return response;
        }
        List<String> links = Arrays.asList(linkHeader.split(","));
        for (String link : links) {
            if(link.contains("rel=\"next\"")) {
                LOG.debug("response has more pages");
                String nextLink = link.substring(1, link.indexOf(';')); //format is <http://.....>; rel="next"
                response.setNextLink(nextLink);
            }
        }
        return response;
    }

    //TODO: remove awful duplication
    public static Response sendJsonPost(String token, String url, String json,
                                        int connectTimeout, int readTimeout) throws IOException {
        LOG.debug("sendApiPost");
        Response response = new Response();

        Long beginTime = System.currentTimeMillis();
        URL apiUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) apiUrl.openConnection();
        con.setConnectTimeout(connectTimeout);
        con.setReadTimeout(readTimeout);
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer" + " " + token);
        con.setRequestProperty("Content-Type", "application/json");

        OutputStream outputStream = con.getOutputStream();
        outputStream.write(json.getBytes());
        outputStream.close();

        LOG.debug("Sending API POST request to URL: " + url);
        if (con.getResponseCode() == 401) {
            throw new InvalidOauthTokenException();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        response.setContent(content.toString());
        Long endTime = System.currentTimeMillis();
        LOG.debug("Canvas API call took: " + (endTime - beginTime) + "ms");

        //deal with pagination
        String linkHeader = con.getHeaderField("Link");
        if (linkHeader == null) {
            return response;
        }
        List<String> links = Arrays.asList(linkHeader.split(","));
        for (String link : links) {
            if (link.contains("rel=\"next\"")) {
                LOG.debug("response has more pages");
                String nextLink = link.substring(1, link.indexOf(';')); //format is <http://.....>; rel="next"
                response.setNextLink(nextLink);
            }
        }
        return response;
    }

    public static Response sendApiPost(String token, String url, Map<String, String> postParameters,
                                       int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("sendApiPost");
        Response response = new Response();

        Long beginTime = System.currentTimeMillis();
        URL apiUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) apiUrl.openConnection();
        con.setConnectTimeout(connectTimeout);
        con.setReadTimeout(readTimeout);
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer" + " " + token);
        if (postParameters != null) {
            for (Map.Entry<String, String> entry : postParameters.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        LOG.debug("Sending API POST request to URL: " + url);
        if (con.getResponseCode() == 401) {
            throw new InvalidOauthTokenException();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        response.setContent(content.toString());
        Long endTime = System.currentTimeMillis();
        LOG.debug("Canvas API call took: " + (endTime - beginTime) + "ms");

        //deal with pagination
        String linkHeader = con.getHeaderField("Link");
        if (linkHeader == null) {
            return response;
        }
        List<String> links = Arrays.asList(linkHeader.split(","));
        for (String link : links) {
            if (link.contains("rel=\"next\"")) {
                LOG.debug("response has more pages");
                String nextLink = link.substring(1, link.indexOf(';')); //format is <http://.....>; rel="next"
                response.setNextLink(nextLink);
            }
        }
        return response;
    }
}
