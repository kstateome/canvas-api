package edu.ksu.canvas.impl;


import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.EnrollmentTermReader;
import edu.ksu.canvas.interfaces.EnrollmentTermWriter;
import edu.ksu.canvas.model.EnrollmentTerm;
import edu.ksu.canvas.model.EnrollmentTermWrapper;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetEnrollmentTermOptions;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class EnrollmentTermImpl extends BaseImpl<EnrollmentTerm, EnrollmentTermReader, EnrollmentTermWriter> implements EnrollmentTermReader, EnrollmentTermWriter {
    private static final Logger LOG = Logger.getLogger(EnrollmentTermReader.class);

    public EnrollmentTermImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
    }

    @Override
    public List<EnrollmentTerm> getEnrollmentTerms(GetEnrollmentTermOptions options) throws IOException {
        LOG.debug("getting enrollment term with account id " + options.getAccountId());
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/terms/" , options.getOptionsMap());
        LOG.debug("Final URL of API call: " + url);
        JsonObject requestBody = new JsonObject();
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, requestBody);
        EnrollmentTermWrapper wrapper = GsonResponseParser.getDefaultGsonParser().fromJson(response.getContent(), EnrollmentTermWrapper.class);
        return wrapper.getEnrollmentTerms();
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<EnrollmentTerm>>(){}.getType();
    }

    @Override
    protected Class<EnrollmentTerm> objectType() {
        return EnrollmentTerm.class;
    }

}
