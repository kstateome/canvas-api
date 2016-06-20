package edu.ksu.canvas.net;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* Class extracted from methods in CanvasUtil */
public class RestClientImpl implements RestClient {
    private static final Logger LOG = Logger.getLogger(RestClientImpl.class);

    public Response sendApiGet(@NotNull String token, @NotNull String url,
                               int connectTimeout, int readTimeout) throws IOException {
        LOG.debug("url - " + url);
        Long beginTime = System.currentTimeMillis();
        Response response = new Response();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "Bearer" + " " + token);

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        //deal with the actual content
        BufferedReader in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        response.setContent(content.toString());
        response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("Canvas API call took: " + (endTime - beginTime) + "ms");

        //deal with pagination
        String linkHeader = httpResponse.getFirstHeader("Link").getValue();
        if(linkHeader == null) {
            return response;
        }
        List<String> links = Arrays.asList(linkHeader.split(","));
        for (String link : links) {
            if(link.contains("rel=\"next\"")) {
                LOG.debug("response has more pages");
                String nextLink = link.substring(1, link.indexOf(';')-1); //format is <http://.....>; rel="next"
                response.setNextLink(nextLink);
            }
        }
        return response;
    }

    //TODO: remove awful duplication
    public Response sendJsonPost(String token, String url, String json,
                                 int connectTimeout, int readTimeout) throws IOException {
        LOG.debug("sendApiPost");
        Response response = new Response();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        Long beginTime = System.currentTimeMillis();
        httpPost.setHeader("Authorization", "Bearer" + " " + token);
        httpPost.setHeader("Content-Type", "application/json");

        StringEntity params = new StringEntity(json);
        httpPost.setEntity(params);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        LOG.debug("Sending API POST request to URL: " + url);
        if (httpResponse.getStatusLine().getStatusCode() == 401) {
            throw new InvalidOauthTokenException();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(
                httpPost.getEntity().getContent()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        response.setContent(content.toString());
        Long endTime = System.currentTimeMillis();
        LOG.debug("Canvas API call took: " + (endTime - beginTime) + "ms");
        return response;
    }

    public Response sendApiPost(String token, String url, Map<String, String> postParameters,
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
                LOG.debug("key "+ entry.getKey() +"\t value : "+ entry.getValue());
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
        response.setResponseCode(con.getResponseCode());
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

    public Response sendApiDelete(String token, String url, Map<String,String> deleteParameters,
                                  int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("sendApiDelete");
        Response response = new Response();

        Long beginTime = System.currentTimeMillis();
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //This class is defined here because we need to be able to add form body elements to a delete request for a few api calls.
        class HttpDeleteWithBody extends HttpPost {
            @Override
            public String getMethod() {
                return "DELETE";
            }
        }

        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody();

        httpDelete.setURI(URI.create(url));
        httpDelete.setHeader("Authorization", "Bearer" + " " + token);
        List<NameValuePair> params = new ArrayList<>();
        if (deleteParameters != null) {
            for (Map.Entry<String, String> entry : deleteParameters.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }
        httpDelete.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);
        LOG.debug("Sending API DELETE request to URL: " + url);
        if (httpResponse.getStatusLine().getStatusCode() == 401) {
            throw new InvalidOauthTokenException();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        response.setContent(content.toString());
        response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("Canvas API call took: " + (endTime - beginTime) + "ms");
        return response;
    }
}
