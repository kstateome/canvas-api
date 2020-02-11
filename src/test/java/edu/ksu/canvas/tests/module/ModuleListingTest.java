package edu.ksu.canvas.tests.module;

import edu.ksu.canvas.BaseCanvasModelUTest;
import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.EnrollmentImpl;
import edu.ksu.canvas.impl.ModuleImpl;
import edu.ksu.canvas.interfaces.EnrollmentWriter;
import edu.ksu.canvas.interfaces.ModuleReader;
import edu.ksu.canvas.model.Module;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.ListModulesOptions;
import edu.ksu.canvas.requestOptions.ListModulesOptions.Include;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ModuleListingTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private ModuleReader moduleReader;

    @Before
    public void setupData() {
        moduleReader = new ModuleImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testListModulesInCourse() throws IOException {
        Long courseId = 1092L;
        ListModulesOptions options = new ListModulesOptions(courseId).studentId("526007");
        String url = baseUrl + "/api/v1/courses/1092/modules?student_id=526007";
        fakeRestClient.addSuccessResponse(url, "SampleJson/Modules.json");
        List<Module> modules = moduleReader.getModulesInCourse(options);
        Assert.assertNotNull(modules);
        Assert.assertEquals(2, modules.size());
        Assert.assertEquals("Module One", modules.get(0).getName());
        Assert.assertEquals(new Date(1581379227000L), modules.get(0).getCompletedAt());
        Assert.assertNull(modules.get(1).getCompletedAt());
    }

}
