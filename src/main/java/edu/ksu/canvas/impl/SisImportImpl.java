package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.SisImportReader;
import edu.ksu.canvas.interfaces.SisImportWriter;
import edu.ksu.canvas.model.SisImport;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.CreateSisImportOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SisImportImpl extends BaseImpl<SisImport, SisImportReader, SisImportWriter> implements SisImportReader, SisImportWriter {

    private static final Logger LOG = LoggerFactory.getLogger(SisImportImpl.class);

    public SisImportImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                      int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public  Optional<SisImport> getSisImport(String accountId, Long id) throws IOException {
        LOG.debug("getting sis import");
        String url = buildCanvasUrl("accounts/" + accountId + "/sis_imports/" + id.toString(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(SisImport.class, response);
    }

    @Override
    public Optional<SisImport> createSisImport(CreateSisImportOptions options) throws IOException {
        LOG.debug("creating sis import");
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/sis_imports", Collections.emptyMap());
        Response response = canvasMessenger.sendFileToCanvas(oauthToken, url, options.getOptionsMap(), CreateSisImportOptions.ATTACHMENT, options.getFilePath(), options.getInputStream());
        return responseParser.parseToObject(SisImport.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<SisImport>>(){}.getType();
    }

    @Override
    protected Class<SisImport> objectType() {
        return SisImport.class;
    }

}
