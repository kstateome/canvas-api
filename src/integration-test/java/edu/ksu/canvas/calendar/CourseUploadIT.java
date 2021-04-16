package edu.ksu.canvas.calendar;

import edu.ksu.canvas.CanvasApiFactory;
import edu.ksu.canvas.interfaces.CalendarReader;
import edu.ksu.canvas.interfaces.CalendarWriter;
import edu.ksu.canvas.interfaces.CourseWriter;
import edu.ksu.canvas.interfaces.FileWriter;
import edu.ksu.canvas.model.Deposit;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.requestOptions.UploadOptions;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class CourseUploadIT {

    private CanvasApiFactory canvasApiFactory;
    private NonRefreshableOauthToken nonRefreshableOauthToken;
    private String course;
    private String user;
    private CalendarWriter writer;
    private CalendarReader reader;


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

        user = properties.getProperty("user");
        Objects.requireNonNull(user, "Property 'user' must be set in config file.");

        course = properties.getProperty("course");
        Objects.requireNonNull(course, "Property 'course' must be set in config file.");

        reader = canvasApiFactory.getReader(CalendarReader.class, nonRefreshableOauthToken);
        writer = canvasApiFactory.getWriter(CalendarWriter.class, nonRefreshableOauthToken);

    }

    @Test
    public void testFileUpload() throws IOException {
        // This just attempts to upload a file to a course.
        CourseWriter writer = canvasApiFactory.getWriter(CourseWriter.class, nonRefreshableOauthToken);
        String filename = UUID.randomUUID().toString() + ".txt";
        UploadOptions options = new UploadOptions(filename).parentFolderPath("/");

        Deposit deposit = writer.uploadFile(course, options).orElseThrow(RuntimeException::new);

        FileWriter fileWriter = canvasApiFactory.getWriter(FileWriter.class, nonRefreshableOauthToken);

        assertNotNull(
                fileWriter.upload(deposit, new ByteArrayInputStream("Hello World".getBytes()), "hello.txt")
                        .orElseThrow(RuntimeException::new).getUrl()
        );
    }

}
