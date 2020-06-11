package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.interfaces.MigrationIssueReader;
import edu.ksu.canvas.model.MigrationIssue;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MigrationIssueImpl extends BaseImpl<MigrationIssue, MigrationIssueReader, CanvasWriter> implements MigrationIssueReader {

    private static final Logger LOG = LoggerFactory.getLogger(MigrationIssueImpl.class);

    public MigrationIssueImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                      int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<MigrationIssue> getCourseMigrationIssues(String courseId, Integer migrationId) throws IOException {
        LOG.debug("listing the migration issues for a content migration for the course");
        String url = buildCanvasUrl("courses/" + courseId + "/content_migrations/" + migrationId.toString() + "/migration_issues", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<MigrationIssue> getCourseMigrationIssue(String courseId, Integer migrationId, Integer issueId) throws IOException {
        LOG.debug("listing a migration issue for a content migration for the course");
        String url = buildCanvasUrl("courses/" + courseId + "/content_migrations/" + migrationId.toString() + "/migration_issues/" + issueId.toString(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(MigrationIssue.class, response);
    }

    @Override
    public List<MigrationIssue> getUserMigrationIssues(String userId, Integer migrationId) throws IOException {
        LOG.debug("listing the migration issues for a content migration for the course");
        String url = buildCanvasUrl("users/" + userId + "/content_migrations/" + migrationId.toString() + "/migration_issues", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<MigrationIssue> getUserMigrationIssue(String userId, Integer migrationId, Integer issueId) throws IOException {
        LOG.debug("listing a migration issue for a content migration for the course");
        String url = buildCanvasUrl("users/" + userId + "/content_migrations/" + migrationId.toString() + "/migration_issues/" + issueId.toString(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(MigrationIssue.class, response);
    }

    @Override
    public List<MigrationIssue> getGroupMigrationIssues(String groupId, Integer migrationId) throws IOException {
        LOG.debug("listing the migration issues for a content migration for the group");
        String url = buildCanvasUrl("groups/" + groupId + "/content_migrations/" + migrationId.toString() + "/migration_issues", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<MigrationIssue> getGroupMigrationIssue(String groupId, Integer migrationId, Integer issueId) throws IOException {
        LOG.debug("listing a migration issue for a content migration for the group");
        String url = buildCanvasUrl("groups/" + groupId + "/content_migrations/" + migrationId.toString() + "/migration_issues/" + issueId.toString(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(MigrationIssue.class, response);
    }

    @Override
    public List<MigrationIssue> getAccountMigrationIssues(String accountId, Integer migrationId) throws IOException {
        LOG.debug("listing the migration issues for a content migration for the account");
        String url = buildCanvasUrl("accounts/" + accountId + "/content_migrations/" + migrationId.toString() + "/migration_issues", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<MigrationIssue> getAccountMigrationIssue(String accountId, Integer migrationId, Integer issueId) throws IOException {
        LOG.debug("listing a migration issue for a content migration for the account");
        String url = buildCanvasUrl("accounts/" + accountId + "/content_migrations/" + migrationId.toString() + "/migration_issues/" + issueId.toString(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(MigrationIssue.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<MigrationIssue>>(){}.getType();
    }

    @Override
    protected Class<MigrationIssue> objectType() {
        return MigrationIssue.class;
    }

}
