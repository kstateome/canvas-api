package edu.ksu.canvas.net;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.exception.UnauthorizedException;
import edu.ksu.canvas.oauth.OauthToken;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SimpleRestClient implements RestClient {
    private static final Logger LOG = Logger.getLogger(SimpleRestClient.class);

    @Override
    public Response sendApiGet(@NotNull OauthToken token, @NotNull String url,
                                      int connectTimeout, int readTimeout) throws IOException {
        LOG.debug("url - " + url);
        Long beginTime = System.currentTimeMillis();
        Response response = new Response();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "Bearer" + " " + token.getAccessToken());

        HttpResponse httpResponse = httpClient.execute(httpGet);
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
        Header linkHeader = httpResponse.getFirstHeader("Link");
        String linkHeaderValue = linkHeader == null ? null : httpResponse.getFirstHeader("Link").getValue();
        if(linkHeaderValue == null) {
            return response;
        }
        List<String> links = Arrays.asList(linkHeaderValue.split(","));
        for (String link : links) {
            if(link.contains("rel=\"next\"")) {
                LOG.debug("response has more pages");
                String nextLink = link.substring(1, link.indexOf(';')-1); //format is <http://.....>; rel="next"
                response.setNextLink(nextLink);
            }
        }
        return response;
    }

    @Override
    public Response sendJsonPut(OauthToken token, String url, String json, int connectTimeout, int readTimeout) throws IOException {
        return sendJsonPostOrPut(token, url, json, connectTimeout, readTimeout, "PUT");
    }

    @Override
    public Response sendJsonPost(OauthToken token, String url, String json, int connectTimeout, int readTimeout) throws IOException {
        return sendJsonPostOrPut(token, url, json, connectTimeout, readTimeout, "POST");
    }

    //TODO: remove awful duplication
    private Response sendJsonPostOrPut(OauthToken token, String url, String json,
                                        int connectTimeout, int readTimeout, String method) throws IOException {
        LOG.debug("sendApiPost");
        Response response = new Response();

        HttpClient httpClient = new DefaultHttpClient();
        HttpEntityEnclosingRequestBase action;
        if("POST".equals(method)) {
            action = new HttpPost(url);
        } else if("PUT".equals(method)) {
            action = new HttpPut(url);
        } else {
            throw new IllegalArgumentException("Method must be either POST or PUT");
        }
        Long beginTime = System.currentTimeMillis();
        action.setHeader("Authorization", "Bearer" + " " + token.getAccessToken());

        StringEntity params = new StringEntity(json, ContentType.APPLICATION_JSON);
        action.setEntity(params);
        HttpResponse httpResponse = httpClient.execute(action);

        LOG.debug("Sending API " + method + " request to URL: " + url);
        String content = handleResponse(httpResponse, action);

        response.setContent(content);
        response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("Canvas API call took: " + (endTime - beginTime) + "ms");


        return response;
    }

    @Override
    public Response sendApiPost(OauthToken token, String url, Map<String, String> postParameters,
                                       int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("sendApiPost");
        Response response = new Response();
        HttpClient httpClient = new DefaultHttpClient();
        Long beginTime = System.currentTimeMillis();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization", "Bearer" + " " + token.getAccessToken());
        List<NameValuePair> params = new ArrayList<>();

        if (postParameters != null) {
            for (Map.Entry<String, String> entry : postParameters.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
                LOG.debug("key "+ entry.getKey() +"\t value : "+ entry.getValue());
            }
        }

        httpPost.setEntity(new UrlEncodedFormEntity(params));
        LOG.debug("Sending API POST request to URL: " + url);
        HttpResponse httpResponse =  httpClient.execute(httpPost);
        String content = handleResponse(httpResponse, httpPost);

        response.setContent(content);
        response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("Canvas API call took: " + (endTime - beginTime) + "ms");
        return response;
    }

    @Override
    public Response sendApiPut(OauthToken token, String url, Map<String, Object> putParameters,
                                int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("sendApiPut");
        Response response = new Response();
        HttpClient httpClient = new DefaultHttpClient();
        Long beginTime = System.currentTimeMillis();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Authorization", "Bearer" + " " + token.getAccessToken());
        List<NameValuePair> params = new ArrayList<>();

        if (putParameters != null) {
            for (Map.Entry<String, Object> entry : putParameters.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
                LOG.debug("key "+ entry.getKey() +"\t value : "+ entry.getValue());
            }
        }

        httpPut.setEntity(new UrlEncodedFormEntity(params));
        LOG.debug("Sending API PUT request to URL: " + url);
        HttpResponse httpResponse =  httpClient.execute(httpPut);
        String content = handleResponse(httpResponse, httpPut);

        response.setContent(content);
        response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("Canvas API call took: " + (endTime - beginTime) + "ms");
        return response;
    }


    @Override
    public Response sendApiDelete(OauthToken token, String url,Map<String, String> deleteParameters,
                                       int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("sendApiDelete");
        Response response = new Response();

        Long beginTime = System.currentTimeMillis();
        HttpClient httpClient = new DefaultHttpClient();

        //This class is defined here because we need to be able to add form body elements to a delete request for a few api calls.
        class HttpDeleteWithBody extends HttpPost {
            @Override
            public String getMethod() {
                return "DELETE";
            }
        }

        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody();

        httpDelete.setURI(URI.create(url));
        httpDelete.setHeader("Authorization", "Bearer" + " " + token.getAccessToken());
        List<NameValuePair> params = new ArrayList<>();
        if (deleteParameters != null) {
            for (Map.Entry<String, String> entry : deleteParameters.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }
        httpDelete.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse httpResponse = httpClient.execute(httpDelete);
        LOG.debug("Sending API DELETE request to URL: " + url);

        String content = handleResponse(httpResponse, httpDelete);
        response.setContent(content);
        response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("Canvas API call took: " + (endTime - beginTime) + "ms");

        return response;
    }

    private String handleResponse(HttpResponse httpResponse, HttpRequestBase request) throws IOException {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 401) {
            //If the WWW-Authenticate header is set, it is a token problem.
            //If the header is not present, it is a user permission error.
            //See https://canvas.instructure.com/doc/api/file.oauth.html#storing-access-tokens
            LOG.error("HTTP status 401 returned from " + request.getURI());
            if (httpResponse.containsHeader(HttpHeaders.WWW_AUTHENTICATE)) {
                throw new InvalidOauthTokenException();
            }
            LOG.debug("User is not authorized to perform this action");
            throw new UnauthorizedException();
        }
        if(statusCode < 200 || statusCode > 299) {
            LOG.error("HTTP status " + statusCode + " returned from " + request.getURI());
            HttpEntity entity = httpResponse.getEntity();
            if(entity != null) {
                LOG.error("Response from Canvas: " + EntityUtils.toString(entity));
            }
        }
        return new BasicResponseHandler().handleResponse(httpResponse);
    }

}
