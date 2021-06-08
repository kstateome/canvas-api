package edu.ksu.canvas.contentMigration;

import edu.ksu.canvas.CanvasApiFactory;
import edu.ksu.canvas.interfaces.ContentMigrationReader;
import edu.ksu.canvas.interfaces.ContentMigrationWriter;
import edu.ksu.canvas.model.ContentMigration;
import edu.ksu.canvas.model.ContentMigration.WorkflowState;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.requestOptions.CreateContentMigrationOptions.MigrationType;
import edu.ksu.canvas.requestOptions.CreateCourseContentMigrationOptions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ContentMigrationIT {

    private CanvasApiFactory canvasApiFactory;
    private NonRefreshableOauthToken nonRefreshableOauthToken;
    private String course;
    private String destinationCourse;
    private String user;
    private ContentMigrationWriter writer;
    private ContentMigrationReader reader;
    private final int MAX_LOOPS = 15;

    @Before
    public void setUp() throws IOException {
        // Load from root of project
        File config = new File("integration.properties");
        if (!config.isFile()) {
            throw new FileNotFoundException("Failed to find configuration: " + config.getAbsolutePath());
        }
        Properties properties = new Properties();
        properties.load(new FileInputStream(config));

        String url = properties.getProperty("url");
        Objects.requireNonNull(url, "Property 'url' must be set in config file.");
        canvasApiFactory = new CanvasApiFactory(url);

        String token = properties.getProperty("token");
        Objects.requireNonNull(token, "Property 'token' must be set in config file.");
        nonRefreshableOauthToken = new NonRefreshableOauthToken(token);

        course = properties.getProperty("course");
        Objects.requireNonNull(course, "Property 'course' must be set in config file.");

        destinationCourse = properties.getProperty("destination_course");
        Objects.requireNonNull(destinationCourse, "Property 'destination_course' must be set in config file.");

        user = properties.getProperty("user");
        Objects.requireNonNull(user, "Property 'user' must be set in config file.");

        reader = canvasApiFactory.getReader(ContentMigrationReader.class, nonRefreshableOauthToken);
        writer = canvasApiFactory.getWriter(ContentMigrationWriter.class, nonRefreshableOauthToken);

    }

    @Test
    public void testCreateContentMigration() throws IOException, InterruptedException {
        CreateCourseContentMigrationOptions createCourseContentMigrationOptions = new CreateCourseContentMigrationOptions(destinationCourse, course, MigrationType.course_copy_importer, false);
        Optional<ContentMigration> response = writer.createCourseContentMigration(createCourseContentMigrationOptions);
        assertNotNull(response.get().getProgressUrl());
        Long migrationId = response.get().getId();
        assertNotNull(migrationId);
        assertEquals(user, response.get().getUserId().toString());
        assertEquals(MigrationType.course_copy_importer.toString(), response.get().getMigrationType());
        WorkflowState workflowState = response.get().getWorkflowState();
        assertEquals(WorkflowState.running, workflowState);
        int count = 0;
        while(WorkflowState.running.equals(workflowState) || WorkflowState.pre_processing.equals(workflowState)) {
            response = reader.getCourseContentMigration(destinationCourse, migrationId);
            assertEquals(migrationId, response.get().getId());
            assertEquals(new Integer(0), response.get().getMigrationIssuesCount());
            workflowState = response.get().getWorkflowState();
            if(count++ > MAX_LOOPS) {
                break;
            }
            Thread.sleep(15000);
        }
        assertEquals(WorkflowState.completed, workflowState);
    }

}
