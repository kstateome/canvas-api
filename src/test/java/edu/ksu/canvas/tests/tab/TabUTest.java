package edu.ksu.canvas.tests.tab;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.TabImpl;
import edu.ksu.canvas.interfaces.TabWriter;
import edu.ksu.canvas.model.Tab;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.UpdateCourseTabOptions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TabUTest extends CanvasTestBase {

    @Autowired
    private FakeRestClient fakeRestClient;

    private TabWriter tabWriter;

    @Before
    public void setUp() {
        tabWriter = new TabImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testUpdateCourseTab() throws IOException {
        String url = baseUrl + "/api/v1/courses/1234/tabs/files";
        fakeRestClient.addSuccessResponse(url, "SampleJson/tab/UpdateTabSuccess.json");
        UpdateCourseTabOptions options = new UpdateCourseTabOptions("1234", "files");
        options.hidden(true);
        Optional<Tab> tab = tabWriter.updateCourseTab(options);

        assertTrue(tab.isPresent());
        assertEquals("files", tab.get().getId());
        assertTrue(tab.get().isHidden());
        assertEquals("admins", tab.get().getVisibility());
        assertEquals("Files", tab.get().getLabel());
    }
}
