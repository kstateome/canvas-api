package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.FileReader;
import edu.ksu.canvas.interfaces.FileWriter;
import edu.ksu.canvas.model.Deposit;
import edu.ksu.canvas.model.File;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class FileImpl extends BaseImpl<File, FileReader, FileWriter> implements FileReader, FileWriter {

    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl      The base URL of your canvas instance
     * @param apiVersion         The version of the Canvas API (currently 1)
     * @param oauthToken         OAuth token to use when executing API calls
     * @param restClient         a RestClient implementation to use when talking to Canvas
     * @param connectTimeout     Timeout in seconds to use when connecting
     * @param readTimeout        Timeout in seconds to use when waiting for data to come back from an open connection
     * @param paginationPageSize How many objects to request per page on paginated requests
     * @param serializeNulls     Whether or not to include null fields in the serialized JSON. Defaults to false if null
     */
    public FileImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<File> upload(Deposit deposit, InputStream in, String filename) throws IOException {
        Map<String, List<String>> params = deposit.getUploadParams().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Collections.singletonList(e.getValue())));
        String location = canvasMessenger.sendUpload(deposit.getUploadUrl(), params, in, filename);
        return getFile(location);
    }

    @Override
    public Optional<File> getFile(String url) throws IOException {
        return getFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<File>>(){}.getType();
    }

    @Override
    protected Class<File> objectType() {
        return File.class;
    }
}
