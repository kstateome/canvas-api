package edu.ksu.canvas;

import edu.ksu.canvas.interfaces.CanvasReader;
import edu.ksu.canvas.interfaces.CourseWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.ksu.canvas.interfaces.CourseReader;

public class ApiFactoryUTest extends CanvasTestBase {

    private CanvasApiFactory apiFactory;

    @Before
    public void setupFactory() {
        apiFactory = new CanvasApiFactory(baseUrl);
    }

    @Test
    public void testGetCourseReader() {
        CourseReader courseReader = apiFactory.getReader(CourseReader.class, SOME_OAUTH_TOKEN);
        Assert.assertNotNull("API factory did not return a course reader object", courseReader);
    }

    @Test
    public void testGetCourseWriter() {
        CourseWriter courseWriter = apiFactory.getWriter(CourseWriter.class, SOME_OAUTH_TOKEN);
        Assert.assertNotNull("API factory did not return a course writer object", courseWriter);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testExceptionThrownForInvalidClass() {
        //The base class does not have an implementation.
        //This simulates passing in a new class that hasn't been added to the readerMap yet
        apiFactory.getReader(CanvasReader.class, SOME_OAUTH_TOKEN);
    }
}
