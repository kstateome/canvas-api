package edu.ksu.canvas;

import edu.ksu.canvas.interfaces.CanvasReader;
import edu.ksu.canvas.interfaces.CourseWriter;

import edu.ksu.canvas.interfaces.CourseReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApiFactoryUTest extends CanvasTestBase {

    private CanvasApiFactory apiFactoryWithDefaultTimeout;
    private CanvasApiFactory apiFactory;

    @BeforeEach
    void setupFactory() {
        apiFactoryWithDefaultTimeout = new CanvasApiFactory(baseUrl);
        apiFactory = new CanvasApiFactory(baseUrl, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
    }

    @Test
    void testGetCourseReader() {
        CourseReader courseReader = apiFactoryWithDefaultTimeout.getReader(CourseReader.class, SOME_OAUTH_TOKEN);
        assertNotNull(courseReader, "API factory did not return a course reader object");
    }

    @Test
    void testGetCourseWriter() {
        CourseWriter courseWriter = apiFactoryWithDefaultTimeout.getWriter(CourseWriter.class, SOME_OAUTH_TOKEN);
        assertNotNull(courseWriter, "API factory did not return a course writer object");
    }

    @Test
    void testGetWriterWithTimeout() {
        apiFactory = new CanvasApiFactory(baseUrl, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
        CourseWriter courseWriter = apiFactory.getWriter(CourseWriter.class, SOME_OAUTH_TOKEN);
        assertNotNull(courseWriter, "API factory did not return a course writer object");
    }

    @Test
    void testGetReaderWithTimeout() {
        apiFactory = new CanvasApiFactory(baseUrl, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
        CourseReader courseReader = apiFactory.getReader(CourseReader.class, SOME_OAUTH_TOKEN);
        assertNotNull(courseReader, "API factory did not return a course writer object");
    }

    @Test
    void testExceptionThrownForInvalidClass() {
        //The base class does not have an implementation.
        //This simulates passing in a new class that hasn't been added to the readerMap yet
        assertThrows(UnsupportedOperationException.class, () -> {
            apiFactoryWithDefaultTimeout.getReader(CanvasReader.class, SOME_OAUTH_TOKEN);
        });

    }
}
