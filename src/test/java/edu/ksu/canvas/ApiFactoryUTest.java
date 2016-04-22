package edu.ksu.canvas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.ksu.canvas.interfaces.CanvasBase;
import edu.ksu.canvas.interfaces.CourseReader;

public class ApiFactoryUTest extends CanvasTestBase {

    private CanvasApiFactory apiFactory;

    @Before
    public void setupFactory() {
        apiFactory = new CanvasApiFactory(baseUrl);
    }

    @Test
    public void testGetCourseReader() {
        CourseReader courseReader = apiFactory.getApiClass(CourseReader.class, SOME_OAUTH_TOKEN);

        Assert.assertNotNull("API factory did not return a course reader object", courseReader);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testExceptionThrownForInvalidClass() {
        //The base class does not have an implementation.
        //This simulates passing in a new class that hasn't been added to the instanceMap yet
        apiFactory.getApiClass(CanvasBase.class, SOME_OAUTH_TOKEN);
    }
}
