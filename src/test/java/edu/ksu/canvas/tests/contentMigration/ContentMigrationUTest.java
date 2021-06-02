package edu.ksu.canvas.tests.contentMigration;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.ContentMigrationImpl;
import edu.ksu.canvas.impl.MigrationIssueImpl;
import edu.ksu.canvas.impl.SelectiveDataImpl;
import edu.ksu.canvas.interfaces.ContentMigrationReader;
import edu.ksu.canvas.interfaces.ContentMigrationWriter;
import edu.ksu.canvas.interfaces.MigrationIssueReader;
import edu.ksu.canvas.interfaces.SelectiveDataReader;
import edu.ksu.canvas.model.ContentMigration;
import edu.ksu.canvas.model.ContentMigration.WorkflowState;
import edu.ksu.canvas.model.MigrationIssue;
import edu.ksu.canvas.model.SelectiveData;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.requestOptions.CreateContentMigrationOptions.MigrationType;
import edu.ksu.canvas.requestOptions.CreateCourseContentMigrationOptions;
import edu.ksu.canvas.requestOptions.GetSelectiveDataOptions;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ContentMigrationUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private ContentMigrationReader contentMigrationReader;
    private ContentMigrationWriter contentMigrationWriter;
    private MigrationIssueReader migrationIssueReader;
    private SelectiveDataReader selectiveDataReader;

    private static final Long ARBITRARY_USER_ID = 899123456L;
    private static final String ARBITRARY_COURSE_ID = "20732";
    private static final String ARBITRARY_DESTINATION_COURSE_ID = "20711";
    private static final String ARBITRARY_DESTINATION_COURSE_ID_2 = "20712";
    private static final String ARBITRARY_DESTINATION_COURSE_ID_3 = "20713";
    private static final String ARBITRARY_DESTINATION_COURSE_ID_4 = "20714";
    private static final Long ARBITRARY_CONTENT_MIGRATION_ID = 44L;
    private static final Long ARBITRARY_CONTENT_MIGRATION_ID_SELECTIVE = 45L;
    private static final Long ARBITRARY_CONTENT_MIGRATION_ID_ISSUES = 46L;
    private static final Long ARBITRARY_CONTENT_MIGRATION_ID_FILE = 47L;

    @Before
    public void setupData() {
        contentMigrationReader = new ContentMigrationImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
        contentMigrationWriter = new ContentMigrationImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
        migrationIssueReader = new MigrationIssueImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
        selectiveDataReader = new SelectiveDataImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testCourseContentMigrationCreation() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_DESTINATION_COURSE_ID + "/content_migrations";
        fakeRestClient.addSuccessResponse(url, "SampleJson/contentMigration/CreateContentMigrationRunning.json");
        CreateCourseContentMigrationOptions createCourseContentMigrationOptions = new CreateCourseContentMigrationOptions(ARBITRARY_DESTINATION_COURSE_ID, ARBITRARY_COURSE_ID, MigrationType.course_copy_importer, false);
        //creating regular course content migration and verifying content from the returned object
        Optional<ContentMigration> response = contentMigrationWriter.createCourseContentMigration(createCourseContentMigrationOptions);
        assertNotNull(response.get().getProgressUrl());
        assertEquals(ARBITRARY_CONTENT_MIGRATION_ID, response.get().getId());
        assertEquals(ARBITRARY_USER_ID, response.get().getUserId());
        assertEquals(MigrationType.course_copy_importer.toString(), response.get().getMigrationType());
        assertEquals(WorkflowState.running, response.get().getWorkflowState());
    }

    @Test
    public void testGetCourseContentMigration() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_DESTINATION_COURSE_ID + "/content_migrations/" + ARBITRARY_CONTENT_MIGRATION_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/contentMigration/GetContentMigrationCompleted.json");
        //verifying content from previously created course content migration
        Optional<ContentMigration> response = contentMigrationReader.getCourseContentMigration(ARBITRARY_DESTINATION_COURSE_ID, ARBITRARY_CONTENT_MIGRATION_ID);
        ContentMigration cm = response.get();
        assertEquals(ARBITRARY_CONTENT_MIGRATION_ID, cm.getId());
        assertEquals(MigrationType.course_copy_importer.toString(), cm.getMigrationType());
        assertNotNull(cm.getMigrationTypeTitle());
        assertEquals(WorkflowState.completed, cm.getWorkflowState());
        assertEquals(new Integer(0), cm.getMigrationIssuesCount());
        assertNotNull(cm.getMigrationIssuesUrl());
        assertNotNull(cm.getProgressUrl());
        assertEquals(ARBITRARY_USER_ID, cm.getUserId());
        assertEquals("2020-06-09T15:26:30Z", cm.getStartedAt().toString());
        assertEquals("2020-06-09T15:26:52Z", cm.getFinishedAt().toString());
        assertEquals("2020-06-09T15:26:30Z", cm.getCreatedAt().toString());
    }

    @Test
    public void testSelectiveCourseContentMigration() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_DESTINATION_COURSE_ID_2 + "/content_migrations";
        fakeRestClient.addSuccessResponse(url, "SampleJson/contentMigration/CreateContentMigrationWaiting.json");
        CreateCourseContentMigrationOptions createCourseContentMigrationOptions = new CreateCourseContentMigrationOptions(ARBITRARY_DESTINATION_COURSE_ID_2, ARBITRARY_COURSE_ID, MigrationType.course_copy_importer, true);
        //creating course content migration with selective import
        Optional<ContentMigration> response = contentMigrationWriter.createCourseContentMigration(createCourseContentMigrationOptions);
        assertEquals(ARBITRARY_CONTENT_MIGRATION_ID_SELECTIVE, response.get().getId());
        assertEquals(MigrationType.course_copy_importer.toString(), response.get().getMigrationType());
        assertEquals(WorkflowState.waiting_for_select, response.get().getWorkflowState());

        String selectiveUrl = baseUrl + "/api/v1/courses/" + ARBITRARY_DESTINATION_COURSE_ID_2 + "/content_migrations/" + ARBITRARY_CONTENT_MIGRATION_ID_SELECTIVE + "/selective_data";
        fakeRestClient.addSuccessResponse(selectiveUrl, "SampleJson/contentMigration/GetSelectiveData.json");
        GetSelectiveDataOptions getSelectiveDataOptions = new GetSelectiveDataOptions(ARBITRARY_DESTINATION_COURSE_ID_2, ARBITRARY_CONTENT_MIGRATION_ID_SELECTIVE, null);
        //getting selective data options for the content migration
        List<SelectiveData> selectable = selectiveDataReader.getCourseSelectiveDataFromMigration(getSelectiveDataOptions);
        assertTrue(selectable.stream().anyMatch(item -> "course_settings".equals(item.getType())));
        assertTrue(selectable.stream().anyMatch(item -> "assignments".equals(item.getType())));

        getSelectiveDataOptions = new GetSelectiveDataOptions(ARBITRARY_DESTINATION_COURSE_ID_2, ARBITRARY_CONTENT_MIGRATION_ID_SELECTIVE, "assignments");
        selectiveUrl += "?type=assignments";
        fakeRestClient.addSuccessResponse(selectiveUrl, "SampleJson/contentMigration/GetSelectiveDataTypeAssignments.json");
        //getting selective data options of the assignments type for the content migration
        selectable = selectiveDataReader.getCourseSelectiveDataFromMigration(getSelectiveDataOptions);
        assertEquals(new Integer(1), new Integer(selectable.size()));
        Optional<SelectiveData> selectedAssignment1 = selectable.get(0).getSubItems().stream().filter(assignment -> "assignments".equals(assignment.getType())).findFirst();

        //update course content migration with selected assignment items...
        String updateUrl = baseUrl + "/api/v1/courses/" + ARBITRARY_DESTINATION_COURSE_ID_2 + "/content_migrations/" + ARBITRARY_CONTENT_MIGRATION_ID_SELECTIVE;
        fakeRestClient.addSuccessResponse(updateUrl, "SampleJson/contentMigration/GetContentMigrationSelectiveCompleted.json");
        createCourseContentMigrationOptions =  new CreateCourseContentMigrationOptions(ARBITRARY_DESTINATION_COURSE_ID_2, ARBITRARY_COURSE_ID, MigrationType.course_copy_importer, false, selectedAssignment1.get().getProperty());
        contentMigrationWriter.updateCourseContentMigration(ARBITRARY_CONTENT_MIGRATION_ID_SELECTIVE, createCourseContentMigrationOptions);

        //...and check course content migration has been completed
        response = contentMigrationReader.getCourseContentMigration(ARBITRARY_DESTINATION_COURSE_ID_2, ARBITRARY_CONTENT_MIGRATION_ID_SELECTIVE);
        assertEquals(ARBITRARY_CONTENT_MIGRATION_ID_SELECTIVE, response.get().getId());
        assertEquals(WorkflowState.completed, response.get().getWorkflowState());
    }

    @Test
    public void testGetContentCourseMigrationIssues() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_DESTINATION_COURSE_ID_3 + "/content_migrations/" + ARBITRARY_CONTENT_MIGRATION_ID;
        String migrationUrl = baseUrl + "/api/v1/courses/" + ARBITRARY_DESTINATION_COURSE_ID_3 + "/content_migrations/" + ARBITRARY_CONTENT_MIGRATION_ID_ISSUES + "/migration_issues";

        fakeRestClient.addSuccessResponse(url, "SampleJson/contentMigration/GetContentMigrationCompletedWithIssues.json");
        //get course content migration information with migration issues
        Optional<ContentMigration> response = contentMigrationReader.getCourseContentMigration(ARBITRARY_DESTINATION_COURSE_ID_3, ARBITRARY_CONTENT_MIGRATION_ID); 
        assertEquals(new Integer(1), response.get().getMigrationIssuesCount());
        assertEquals(migrationUrl, response.get().getMigrationIssuesUrl());
        
        fakeRestClient.addSuccessResponse(migrationUrl, "SampleJson/contentMigration/GetMigrationIssue.json");
        //get migration issues from content migration and verify the count matches the retrieved info
        List<MigrationIssue> issues = migrationIssueReader.getCourseMigrationIssues(ARBITRARY_DESTINATION_COURSE_ID_3, ARBITRARY_CONTENT_MIGRATION_ID_ISSUES);
        assertEquals(response.get().getMigrationIssuesCount(), new Integer(issues.size()));
        MigrationIssue mi = issues.get(0);
        assertNotNull(mi.getDescription());
        assertNotNull(mi.getContentMigrationUrl());
        assertNotNull(mi.getWorkflowState());
        assertNotNull(mi.getIssueType());
        assertNotNull(mi.getCreatedAt());
        assertNotNull(mi.getUpdatedAt());
    }

    @Test
    public void testFileContentCourseMigration() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_DESTINATION_COURSE_ID_4 + "/content_migrations";
        fakeRestClient.addSuccessResponse(url, "SampleJson/contentMigration/CreateContentMigrationWithFile.json");
        CreateCourseContentMigrationOptions createCourseContentMigrationOptions = new CreateCourseContentMigrationOptions(ARBITRARY_DESTINATION_COURSE_ID_4, ARBITRARY_COURSE_ID, MigrationType.zip_file_importer, false);
        createCourseContentMigrationOptions.preAttachment("nameOfFile.zip");
        createCourseContentMigrationOptions.folder("112");
        Optional<ContentMigration> response = contentMigrationWriter.createCourseContentMigration(createCourseContentMigrationOptions);
        assertEquals(ARBITRARY_CONTENT_MIGRATION_ID_FILE, response.get().getId());
        assertEquals(MigrationType.zip_file_importer.toString(), response.get().getMigrationType());
        assertEquals(WorkflowState.pre_processing, response.get().getWorkflowState());
        assertNotNull("preAttachment", response.get().getPreAttachment());

        String tempUrl = "https://tempurl.net/files?token=token";//this is retrieved from the preAttachment json from the response, upload_url field
        Map<String, List<String>> postParameters = new HashMap<>();
        postParameters.put("filename", Collections.singletonList("nameOfFile.zip"));
        fakeRestClient.addSuccessResponse(tempUrl, "SampleJson/contentMigration/CreateContentMigrationWithFile2.json");
        Response r = fakeRestClient.sendApiPostFile(SOME_OAUTH_TOKEN, tempUrl, postParameters, "file", "/folder/filePath", null,  SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
        assertThat(r.getContent(), containsString("\"upload_status\": \"success\""));
    }
}
