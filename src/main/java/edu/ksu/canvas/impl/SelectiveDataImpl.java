package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.interfaces.SelectiveDataReader;
import edu.ksu.canvas.model.SelectiveData;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetSelectiveDataOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SelectiveDataImpl extends BaseImpl<SelectiveData, SelectiveDataReader, CanvasWriter> implements SelectiveDataReader {

    private static final Logger LOG = LoggerFactory.getLogger(SelectiveDataImpl.class);

    public SelectiveDataImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                      int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    /**
     * NOTE : Urls for subitems are expected to be on the same endpoint as the top-level but adding a type parameter
     *        The available types can be retrieved in the top-level GET, then a type can be added to the GetSelectiveDataOptions object on the following call
     **/ 
    @Override
    public List<SelectiveData> getCourseSelectiveDataFromMigration(GetSelectiveDataOptions options) throws IOException {
        LOG.debug("listing the items available for a content migration for the course");
        String url = buildCanvasUrl("courses/" + options.getItemId() + "/content_migrations/" + options.getMigrationId().toString() + "/selective_data", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<SelectiveData> getUserSelectiveDataFromMigration(GetSelectiveDataOptions options) throws IOException {
        LOG.debug("listing the items available for a content migration for the user");
        String url = buildCanvasUrl("users/" + options.getItemId() + "/content_migrations/" + options.getMigrationId().toString() + "/selective_data", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<SelectiveData> getGroupSelectiveDataFromMigration(GetSelectiveDataOptions options) throws IOException {
        LOG.debug("listing the items available for a content migration for the group");
        String url = buildCanvasUrl("groups/" + options.getItemId() + "/content_migrations/" + options.getMigrationId().toString() + "/selective_data", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<SelectiveData> getAccountSelectiveDataFromMigration(GetSelectiveDataOptions options) throws IOException {
        LOG.debug("listing the items available for a content migration for the account");
        String url = buildCanvasUrl("accounts/" + options.getItemId() + "/content_migrations/" + options.getMigrationId().toString() + "/selective_data", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<SelectiveData>>(){}.getType();
    }

    @Override
    protected Class<SelectiveData> objectType() {
        return SelectiveData.class;
    }

}
