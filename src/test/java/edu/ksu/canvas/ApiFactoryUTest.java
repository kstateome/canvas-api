package edu.ksu.canvas;

import edu.ksu.canvas.interfaces.CanvasReader;
import edu.ksu.canvas.interfaces.CourseWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.ksu.canvas.interfaces.CourseReader;

public class ApiFactoryUTest extends CanvasTestBase {

    private CanvasApiFactory apiFactoryWithDefaultTimeout;
    private CanvasApiFactory apiFactory;

    @Before
    public void setupFactory() {
        apiFactoryWithDefaultTimeout = new CanvasApiFactory(baseUrl);
        apiFactory = new CanvasApiFactory(baseUrl, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
    }

    @Test
    public void testGetCourseReader() {
        CourseReader courseReader = apiFactoryWithDefaultTimeout.getReader(CourseReader.class, SOME_OAUTH_TOKEN);
        Assert.assertNotNull("API factory did not return a course reader object", courseReader);
    }

    @Test
    public void testGetCourseWriter() {
        CourseWriter courseWriter = apiFactoryWithDefaultTimeout.getWriter(CourseWriter.class, SOME_OAUTH_TOKEN);
        Assert.assertNotNull("API factory did not return a course writer object", courseWriter);
    }

    @Test
    public void testGetWriterWithTimeout() {
        apiFactory = new CanvasApiFactory(baseUrl, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
        CourseWriter courseWriter = apiFactory.getWriter(CourseWriter.class, SOME_OAUTH_TOKEN);
        Assert.assertNotNull("API factory did not return a course writer object", courseWriter);
    }

    @Test
    public void testGetReaderWithTimeout() {
        apiFactory = new CanvasApiFactory(baseUrl, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
        CourseReader courseReader = apiFactory.getReader(CourseReader.class, SOME_OAUTH_TOKEN);
        Assert.assertNotNull("API factory did not return a course writer object", courseReader);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testExceptionThrownForInvalidClass() {
        //The base class does not have an implementation.
        //This simulates passing in a new class that hasn't been added to the readerMap yet
        apiFactoryWithDefaultTimeout.getReader(CanvasReader.class, SOME_OAUTH_TOKEN);
    }
}
