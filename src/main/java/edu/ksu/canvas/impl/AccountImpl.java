package edu.ksu.canvas.impl;

import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.AccountsReader;
import edu.ksu.canvas.interfaces.AccountsWriter;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collections;

public class AccountImpl extends  BaseImpl implements AccountsReader,AccountsWriter {
    private static final Logger LOG = Logger.getLogger(CourseReader.class);
    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl The base URL of your canvas instance
     * @param apiVersion    The version of the Canvas API (currently 1)
     * @param oauthToken    OAuth token to use when executing API calls
     */
    public AccountImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    @Override
    public Boolean deleteUser(Integer userId) throws InvalidOauthTokenException, IOException {
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "accounts/" + CanvasConstants.ACCOUNT_ID + "/users/" + userId, Collections.emptyMap());
        LOG.debug("create URl for user creation : "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, null);
        if (response.getErrorHappened() || (response.getResponseCode() != 200)) {
            LOG.debug("Failed to create user, error message: " + response.toString());
            return false;
        }
        return true;
    }
}
