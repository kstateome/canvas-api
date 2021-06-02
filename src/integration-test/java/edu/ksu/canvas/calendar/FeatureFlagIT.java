package edu.ksu.canvas.calendar;

import edu.ksu.canvas.CanvasApiFactory;
import edu.ksu.canvas.interfaces.FeatureFlagReader;
import edu.ksu.canvas.interfaces.FeatureFlagWriter;
import edu.ksu.canvas.model.FeatureFlag;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FeatureFlagIT {

    private CanvasApiFactory canvasApiFactory;
    private NonRefreshableOauthToken nonRefreshableOauthToken;
    private String course;
    private String user;
    private String account;
    private FeatureFlagWriter writer;
    private FeatureFlagReader reader;

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

        account = properties.getProperty("account");
        Objects.requireNonNull(account, "Property 'account' must be set in config file.");

        reader = canvasApiFactory.getReader(FeatureFlagReader.class, nonRefreshableOauthToken);
        writer = canvasApiFactory.getWriter(FeatureFlagWriter.class, nonRefreshableOauthToken);
    }

    @Test
    public void testAccountFeatureFlag() throws IOException {
        Optional<FeatureFlag> epubExport = reader.getAccountFeatureFlag(account, "epub_export");
        assertTrue(epubExport.isPresent());
        FeatureFlag flag = epubExport.get();
        assertNotNull(flag.getFeature());
        assertNotNull(flag.getContextType());
        assertNotNull(flag.getContextId());
        assertNotNull(flag.getState());
    }

    @Test
    public void testCourseFeatureFlag() throws IOException {
        Optional<FeatureFlag> epubExport = reader.getCourseFeatureFlag(course, "epub_export");
        assertTrue(epubExport.isPresent());
        FeatureFlag flag = epubExport.get();
        assertNotNull(flag.getFeature());
        assertNotNull(flag.getContextType());
        assertNotNull(flag.getContextId());
        assertNotNull(flag.getState());
    }
}
