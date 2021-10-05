package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.ContentMigrationReader;
import edu.ksu.canvas.interfaces.ContentMigrationWriter;
import edu.ksu.canvas.model.ContentMigration;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.CreateContentMigrationOptions;
import edu.ksu.canvas.requestOptions.CreateCourseContentMigrationOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ContentMigrationImpl extends BaseImpl<ContentMigration, ContentMigrationReader, ContentMigrationWriter> implements ContentMigrationReader, ContentMigrationWriter {

    private static final Logger LOG = LoggerFactory.getLogger(ContentMigrationImpl.class);

    public ContentMigrationImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                      int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public  Optional<ContentMigration> getCourseContentMigration(String courseId, Integer id) throws IOException {
        LOG.debug("listing a content migration for the course");
        String url = buildCanvasUrl("courses/" + courseId + "/content_migrations/" + id.toString(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    public  List<ContentMigration> getCourseContentMigrations(String courseId) throws IOException {
        LOG.debug("listing content migrations for the course");
        String url = buildCanvasUrl("courses/" + courseId + "/content_migrations/", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<ContentMigration> createCourseContentMigration(CreateCourseContentMigrationOptions options) throws IOException {
        LOG.debug("creating course content migration");
        String url = buildCanvasUrl("courses/" + options.getDestinationCourseId() + "/content_migrations", Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    public Optional<ContentMigration> updateCourseContentMigration(Integer id, CreateCourseContentMigrationOptions options) throws IOException {
        LOG.debug("updating course content migration");
        String url = buildCanvasUrl("courses/" + options.getDestinationCourseId() + "/content_migrations/" + id.toString(), Collections.emptyMap());
        Response response = canvasMessenger.putToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    /******************************** USERS ********************************/
    @Override
    public  Optional<ContentMigration> getUserContentMigration(String userId, Integer id) throws IOException {
        LOG.debug("listing a content migration for the user");
        String url = buildCanvasUrl("users/" + userId + "/content_migrations/" + id.toString(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    public  List<ContentMigration> getUserContentMigrations(String userId) throws IOException {
        LOG.debug("listing content migrations for the user");
        String url = buildCanvasUrl("users/" + userId + "/content_migrations/", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<ContentMigration> createUserContentMigration(String userId, CreateContentMigrationOptions options) throws IOException {
        LOG.debug("creating user content migration");
        String url = buildCanvasUrl("users/" + userId + "/content_migrations", Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    public Optional<ContentMigration> updateUserContentMigration(String userId, Integer id, CreateContentMigrationOptions options) throws IOException {
        LOG.debug("updating user content migration");
        String url = buildCanvasUrl("users/" + userId + "/content_migrations/" + id.toString(), Collections.emptyMap());
        Response response = canvasMessenger.putToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    /******************************** GROUPS ********************************/
    @Override
    public  Optional<ContentMigration> getGroupContentMigration(String groupId, Integer id) throws IOException {
        LOG.debug("listing a content migration for the group");
        String url = buildCanvasUrl("groups/" + groupId + "/content_migrations/" + id.toString(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    public  List<ContentMigration> getGroupContentMigrations(String groupId) throws IOException {
        LOG.debug("listing content migrations for the group");
        String url = buildCanvasUrl("groups/" + groupId + "/content_migrations/", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<ContentMigration> createGroupContentMigration(String groupId, CreateContentMigrationOptions options) throws IOException {
        LOG.debug("creating group content migration");
        String url = buildCanvasUrl("groups/" + groupId + "/content_migrations", Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    public Optional<ContentMigration> updateGroupContentMigration(String groupId, Integer id, CreateContentMigrationOptions options) throws IOException {
        LOG.debug("updating group content migration");
        String url = buildCanvasUrl("groups/" + groupId + "/content_migrations/" + id.toString(), Collections.emptyMap());
        Response response = canvasMessenger.putToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(ContentMigration.class, response);
    }
    
    /******************************** ACCOUNTS ********************************/
    @Override
    public  Optional<ContentMigration> getAccountContentMigration(String accountId, Integer id) throws IOException {
        LOG.debug("listing a content migration for the account");
        String url = buildCanvasUrl("accounts/" + accountId + "/content_migrations/" + id.toString(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    public  List<ContentMigration> getAccountContentMigrations(String accountId) throws IOException {
        LOG.debug("listing content migrations for the account");
        String url = buildCanvasUrl("accounts/" + accountId + "/content_migrations/", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<ContentMigration> createAccountContentMigration(String accountId, CreateContentMigrationOptions options) throws IOException {
        LOG.debug("creating account content migration");
        String url = buildCanvasUrl("accounts/" + accountId + "/content_migrations", Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    public Optional<ContentMigration> updateAccountContentMigration(String accountId, Integer id, CreateContentMigrationOptions options) throws IOException {
        LOG.debug("updating account content migration");
        String url = buildCanvasUrl("accounts/" + accountId + "/content_migrations/" + id.toString(), Collections.emptyMap());
        Response response = canvasMessenger.putToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<ContentMigration>>(){}.getType();
    }

    @Override
    protected Class<ContentMigration> objectType() {
        return ContentMigration.class;
    }

}
