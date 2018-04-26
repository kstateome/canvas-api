package edu.ksu.canvas.impl;


import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.EnrollmentTermReader;
import edu.ksu.canvas.interfaces.EnrollmentTermWriter;
import edu.ksu.canvas.model.EnrollmentTerm;
import edu.ksu.canvas.model.wrapper.EnrollmentTermWrapper;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetEnrollmentTermOptions;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentTermImpl extends BaseImpl<EnrollmentTerm, EnrollmentTermReader, EnrollmentTermWriter> implements EnrollmentTermReader, EnrollmentTermWriter {
    private static final Logger LOG = Logger.getLogger(EnrollmentTermReader.class);

    public EnrollmentTermImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                              int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<EnrollmentTerm> getEnrollmentTerms(GetEnrollmentTermOptions options) throws IOException {
        LOG.debug("getting enrollment term with account id " + options.getAccountId());
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/terms/" , options.getOptionsMap());
        LOG.debug("Final URL of API call: " + url);
        List<Response> response = canvasMessenger.getFromCanvas(oauthToken, url);
        return parseEnrollmentTermList(response);
    }

    //Unfortunately we can't use the generic list parse methods in BaseImpl because Canvas wraps enrollment terms in
    //a useless object at the top level of the response JSON for no reason at all.
    private List<EnrollmentTerm> parseEnrollmentTermList(final List<Response> responses) {
        return responses.stream().
                map(this::parseEnrollmentTermList).
                flatMap(Collection::stream).
                collect(Collectors.toList());
    }

    private List<EnrollmentTerm> parseEnrollmentTermList(final Response response) {
        EnrollmentTermWrapper wrapper = GsonResponseParser.getDefaultGsonParser(serializeNulls).fromJson(response.getContent(), EnrollmentTermWrapper.class);
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
