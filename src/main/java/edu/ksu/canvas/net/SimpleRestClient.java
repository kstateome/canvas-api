package edu.ksu.canvas.net;

import com.google.gson.Gson;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.errors.ErrorHandler;
import edu.ksu.canvas.errors.GenericErrorHandler;
import edu.ksu.canvas.errors.UserErrorHandler;
import edu.ksu.canvas.exception.*;
import edu.ksu.canvas.impl.GsonResponseParser;
import edu.ksu.canvas.model.status.CanvasErrorResponse;
import edu.ksu.canvas.model.status.CanvasErrorResponse.ErrorMessage;
import edu.ksu.canvas.oauth.OauthToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleRestClient implements RestClient {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleRestClient.class);

    private List<ErrorHandler> errorHandlers;

    public SimpleRestClient() {
        errorHandlers = new ArrayList<>();
        errorHandlers.add(new UserErrorHandler());
        errorHandlers.add(new GenericErrorHandler());
    }

    @Override
    public Response sendApiGet(@NotNull OauthToken token, @NotNull String url,
                               int connectTimeout, int readTimeout) throws IOException {

        LOG.debug("Sending GET request to URL: " + url);
        Long beginTime = System.currentTimeMillis();
        Response response = new Response();
        try (CloseableHttpClient httpClient = createHttpClient(connectTimeout, readTimeout)) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Authorization", "Bearer" + " " + token.getAccessToken());

            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {

                //deal with the actual content
                response.setContent(handleResponse(httpResponse, httpGet));
                response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
                Long endTime = System.currentTimeMillis();
                LOG.debug("GET call took: " + (endTime - beginTime) + "ms");

                //deal with pagination
                Header linkHeader = httpResponse.getFirstHeader("Link");
                String linkHeaderValue = linkHeader == null ? null : httpResponse.getFirstHeader("Link").getValue();
                if (linkHeaderValue == null) {
                    return response;
                }
                List<String> links = Arrays.asList(linkHeaderValue.split(","));
                for (String link : links) {
                    if (link.contains("rel=\"next\"")) {
                        LOG.debug("response has more pages");
                        String nextLink = link.substring(1, link.indexOf(';') - 1); //format is <http://.....>; rel="next"
                        response.setNextLink(nextLink);
                    }
                }
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

    // PUT and POST are identical calls except for the header specifying the method
    private Response sendJsonPostOrPut(OauthToken token, String url, String json,
                                       int connectTimeout, int readTimeout, String method) throws IOException {
        LOG.debug("Sending JSON " + method + " to URL: " + url);
        Response response = new Response();

        HttpClient httpClient = createHttpClient(connectTimeout, readTimeout);
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

        StringEntity requestBody = new StringEntity(json, ContentType.APPLICATION_JSON);
        action.setEntity(requestBody);
        try {
            HttpResponse httpResponse = httpClient.execute(action);

            String content = handleResponse(httpResponse, action);

            response.setContent(content);
            response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
            Long endTime = System.currentTimeMillis();
            LOG.debug("POST call took: " + (endTime - beginTime) + "ms");
        } finally {
            action.releaseConnection();
        }

        return response;
    }

    @Override
    public Response sendApiPost(OauthToken token, String url, Map<String, List<String>> postParameters,
                                int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending API POST request to URL: " + url);
        Response response = new Response();
        HttpClient httpClient = createHttpClient(connectTimeout, readTimeout);
        Long beginTime = System.currentTimeMillis();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization", "Bearer" + " " + token.getAccessToken());
        List<NameValuePair> params = convertParameters(postParameters);

        httpPost.setEntity(new UrlEncodedFormEntity(params, CanvasConstants.URLENCODING_TYPE));
        HttpResponse httpResponse =  httpClient.execute(httpPost);
        String content = handleResponse(httpResponse, httpPost);

        response.setContent(content);
        response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("POST call took: " + (endTime - beginTime) + "ms");
        return response;
    }

    @Override
    public Response sendApiPostFile(OauthToken token, String url, Map<String, List<String>> postParameters, String fileParameter, String filePath, InputStream is,
                                int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending API POST file request to URL: " + url);
        Response response = new Response();
        HttpClient httpClient = createHttpClient(connectTimeout, readTimeout);
        Long beginTime = System.currentTimeMillis();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization", "Bearer" + " " + token.getAccessToken());
        List<NameValuePair> params = convertParameters(postParameters);

        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        if(is == null) {
            FileBody fileBody = new FileBody(new File(filePath));
            entityBuilder.addPart(fileParameter, fileBody);
        } else {
            entityBuilder.addPart(fileParameter, new InputStreamBody(is, filePath));
        }
        for(NameValuePair param : params) {
            entityBuilder.addTextBody(param.getName(), param.getValue());
        }

        httpPost.setEntity(entityBuilder.build());
        HttpResponse httpResponse =  httpClient.execute(httpPost);
        String content = handleResponse(httpResponse, httpPost);

        response.setContent(content);
        response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("POST file call took: " + (endTime - beginTime) + "ms");
        return response;
    }

    @Override
    public Response sendApiPut(OauthToken token, String url, Map<String, List<String>> putParameters,
                               int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending API PUT request to URL: " + url);
        Response response = new Response();
        HttpClient httpClient = createHttpClient(connectTimeout, readTimeout);
        Long beginTime = System.currentTimeMillis();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Authorization", "Bearer" + " " + token.getAccessToken());
        List<NameValuePair> params = convertParameters(putParameters);

        httpPut.setEntity(new UrlEncodedFormEntity(params, CanvasConstants.URLENCODING_TYPE));
        HttpResponse httpResponse =  httpClient.execute(httpPut);
        String content = handleResponse(httpResponse, httpPut);

        response.setContent(content);
        response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("PUT call took: " + (endTime - beginTime) + "ms");
        return response;
    }


    @Override
    public Response sendApiDelete(OauthToken token, String url, Map<String, List<String>> deleteParameters,
                                  int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending API DELETE request to URL: " + url);
        Response response = new Response();

        Long beginTime = System.currentTimeMillis();
        HttpClient httpClient = createHttpClient(connectTimeout, readTimeout);

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
        List<NameValuePair> params = convertParameters(deleteParameters);

        httpDelete.setEntity(new UrlEncodedFormEntity(params, CanvasConstants.URLENCODING_TYPE));
        HttpResponse httpResponse = httpClient.execute(httpDelete);

        String content = handleResponse(httpResponse, httpDelete);
        response.setContent(content);
        response.setResponseCode(httpResponse.getStatusLine().getStatusCode());
        Long endTime = System.currentTimeMillis();
        LOG.debug("DELETE call took: " + (endTime - beginTime) + "ms");

        return response;
    }

    @Override
    public String sendUpload(String uploadUrl, Map<String, List<String>> params, InputStream in, String filename, int connectTimeout, int readTimeout) throws IOException {

        HttpClient client = buildHttpClient(connectTimeout, readTimeout)
                .disableRedirectHandling() // We need to handle redirects ourselves
                .build();

        HttpPost httpPost = new HttpPost(uploadUrl);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            for (String value : entry.getValue()) {
                entityBuilder.addTextBody(entry.getKey(), value);
            }
        }
        ContentBody fileBody = new InputStreamBody(in, filename);
        entityBuilder.addPart("file", fileBody);
        httpPost.setEntity(entityBuilder.build());

        HttpResponse httpResponse = client.execute(httpPost);
        checkHeaders(httpResponse, httpPost, true);
        int httpStatus = httpResponse.getStatusLine().getStatusCode();
        if (httpStatus == 201 || (300 <= httpStatus && httpStatus <= 399)) {
            Header location = httpResponse.getFirstHeader("Location");
            if (location != null) {
                return location.getValue();
            } else {
                throw new CanvasException("No location to redirect to when uploading file: " + httpStatus, uploadUrl);
            }
        } else {
            throw new CanvasException("Bad status when uploading file: "+ httpStatus, uploadUrl);
        }

    }

    private void checkHeaders(HttpResponse httpResponse, HttpRequestBase request, boolean allowRedirect) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        double rateLimitThreshold = 0.1;
        double xRateCost = 0;
        double xRateLimitRemaining = 0;

        try {
            xRateCost = Double.parseDouble(httpResponse.getFirstHeader("x-request-cost").getValue());
            xRateLimitRemaining = Double.parseDouble(httpResponse.getFirstHeader("x-rate-limit-remaining").getValue());

            //Throws a 403 with a "Rate Limit Exceeded" error message if the API throttle limit is hit.
            //See https://canvas.instructure.com/doc/api/file.throttling.html.
            if(xRateLimitRemaining < rateLimitThreshold) {
                LOG.error("Canvas API rate limit exceeded. Bucket quota: " + xRateLimitRemaining + " Cost: " + xRateCost
                        + " Threshold: " + rateLimitThreshold + " HTTP status: " + statusCode + " Requested URL: " + request.getURI());
                throw new RateLimitException(extractErrorMessageFromResponse(httpResponse), String.valueOf(request.getURI()));
            }
        } catch (NullPointerException e) {
            LOG.debug("Rate not being limited: " + e);
        }
        if (statusCode == 401) {
            //If the WWW-Authenticate header is set, it is a token problem.
            //If the header is not present, it is a user permission error.
            //See https://canvas.instructure.com/doc/api/file.oauth.html#storing-access-tokens
            if(httpResponse.containsHeader(HttpHeaders.WWW_AUTHENTICATE)) {
                LOG.debug("User's token is invalid. It might need refreshing");
                throw new InvalidOauthTokenException();
            }
            LOG.error("User is not authorized to perform this action");
            throw new UnauthorizedException();
        }
        if(statusCode == 403) {
            LOG.error("Canvas has throttled this request. Requested URL: " + request.getURI());
            throw new ThrottlingException(extractErrorMessageFromResponse(httpResponse), String.valueOf(request.getURI()));
        }
        if(statusCode == 404) {
            LOG.error("Object not found in Canvas. Requested URL: " + request.getURI());
            throw new ObjectNotFoundException(extractErrorMessageFromResponse(httpResponse), String.valueOf(request.getURI()));
        }
        if(statusCode == 504) {
            LOG.error("504 Gateway Time-out while requesting: " + request.getURI());
            throw new RetriableException("status code: 504, reason phrase: Gateway Time-out", String.valueOf(request.getURI()));
        }
        // If we receive a 5xx exception, we should not wrap it in an unchecked exception for upstream clients to deal with.
        if(statusCode < 200 || (statusCode > (allowRedirect?399:299) && statusCode <= 499)) {
            LOG.error("HTTP status " + statusCode + " returned from " + request.getURI());
            handleError(request, httpResponse);
        }
        //TODO Handling of 422 when the entity is malformed.
    }

    private void handleError(HttpRequestBase httpRequest, HttpResponse httpResponse) {
        for (ErrorHandler handler : errorHandlers) {
            if (handler.shouldHandle(httpRequest, httpResponse)) {
                handler.handle(httpRequest, httpResponse);
            }
        }
        String canvasErrorString = extractErrorMessageFromResponse(httpResponse);
        throw new CanvasException(canvasErrorString, String.valueOf(httpRequest.getURI()));
    }

    /**
     * Attempts to extract a useful Canvas error message from a response object.
     * Sometimes Canvas API errors come back with a JSON body containing something like
     * <pre>{"errors":[{"message":"Human readable message here."}],"error_report_id":123456}</pre>.
     * This method will attempt to extract the message. If parsing fails, it will return
     * the raw JSON string without trying to parse it. Returns null if all attempts fail.
     * @param response HttpResponse object representing the error response from Canvas
     * @return The Canvas human-readable error string or null if unable to extract it
     */
    private String extractErrorMessageFromResponse(HttpResponse response) {
        String contentType = response.getEntity().getContentType().getValue();
        String message = null;
        if(contentType.contains("application/json")) {
            Gson gson = GsonResponseParser.getDefaultGsonParser(false);
            String responseBody = null;
            try {
                responseBody = EntityUtils.toString(response.getEntity());
                LOG.error("Body of error response from Canvas: " + responseBody);
                CanvasErrorResponse errorResponse = gson.fromJson(responseBody, CanvasErrorResponse.class);
                List<ErrorMessage> errors = errorResponse.getErrors();
                if(errors != null) {
                    //I have only ever seen a single error message but it is an array so presumably there could be more.
                    message = errors.stream().map(ErrorMessage::getMessage).collect(Collectors.joining(", "));
                }
                else{
                    message = responseBody;
                }
            } catch (Exception e) {
                //Returned JSON was not in expected format. Fall back to returning the whole response body, if any
                if(StringUtils.isNotBlank(responseBody)) {
                    message = responseBody;
                }
            }
        }
        return message;
    }

    private String handleResponse(HttpResponse httpResponse, HttpRequestBase request) throws IOException {
        checkHeaders(httpResponse, request, false);
        return new BasicResponseHandler().handleResponse(httpResponse);
    }

    private CloseableHttpClient createHttpClient(int connectTimeout, int readTimeout) {
        return buildHttpClient(connectTimeout, readTimeout).build();
    }

    private HttpClientBuilder buildHttpClient(int connectTimeout, int readTimeout) {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(readTimeout)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(config);
    }

    private static List<NameValuePair> convertParameters(final Map<String, List<String>> parameterMap) {
        final List<NameValuePair> params = new ArrayList<>();

        if (parameterMap == null) {
            return params;
        }

        for (final Map.Entry<String, List<String>> param : parameterMap.entrySet()) {
            final String key = param.getKey();
            if(param.getValue() == null || param.getValue().isEmpty()) {
                params.add(new BasicNameValuePair(key, null));
                LOG.debug("key: " + key + "\tempty value");
            }
            for (final String value : param.getValue()) {
                params.add(new BasicNameValuePair(key, value));
                LOG.debug("key: "+ key +"\tvalue: " + value);
            }
        }
        return params;
    }

}
