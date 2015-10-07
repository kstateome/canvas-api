package edu.ksu.canvas.impl;


import org.apache.log4j.Logger;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.CanvasMessenger;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;

import java.io.IOException;
import java.util.Map;


import javax.validation.constraints.NotNull;

/*
 * This class uses the canvas rest api to communicate with canvas
 */
public class RestCanvasMessenger implements CanvasMessenger {
    private static final Logger LOG = Logger.getLogger(RestCanvasMessenger.class);
    private int connectTimeout;
    private int readTimeout;

    public RestCanvasMessenger(int connectTimeout, int readTimeout) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    @Override
    public Response getFromCanvas(@NotNull String oauthToken, @NotNull String url) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending GET request to: " + url);
        final Response response = RestClient.sendApiGet(oauthToken, url, connectTimeout, readTimeout);
        if (response.getResponseCode() == 401) {
            throw new InvalidOauthTokenException();
        }
        return response;
    }

    @Override
    public Response sendToCanvas(@NotNull String oauthToken, @NotNull String url, @NotNull Map<String,String> parameters) throws InvalidOauthTokenException, IOException {
        final Response response = RestClient.sendApiPost(oauthToken, url, parameters, connectTimeout, readTimeout);
        if (response.getResponseCode() == 401) {
            throw new InvalidOauthTokenException();
        }
        return response;
   }

    @Override
    public Response deleteFromCanvas(@NotNull String oauthToken, @NotNull String url,@NotNull Map<String,String> parameters) throws InvalidOauthTokenException, IOException {
        final Response response = RestClient.sendApiDelete(oauthToken,url,parameters,connectTimeout,readTimeout);
        if(response.getResponseCode() == 401){
            throw new InvalidOauthTokenException();
        }
        return response;
    }

}
