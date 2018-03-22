package edu.ksu.canvas.tests.course;

import java.io.IOException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.SectionsImpl;
import edu.ksu.canvas.interfaces.SectionWriter;
import edu.ksu.canvas.model.Section;
import edu.ksu.canvas.net.FakeRestClient;

public class SectionManagerUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private SectionWriter sectionWriter;

    private static final String ARBITRARY_COURSE_ID = "503";

    @Before
    public void setupData() {
        sectionWriter = new SectionsImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
            SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testSectionCreation() throws IOException {

        String sectionName = "someName";

        Section newSection = new Section();
        newSection.setName(sectionName);

        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID + "/sections";
        fakeRestClient.addSuccessResponse(url, "SampleJson/section/CreateSectionsSuccess.json");
        Optional<Section> response = sectionWriter.createSection(ARBITRARY_COURSE_ID, newSection, null);

        Assert.assertNotNull(response.get().getId());
        Assert.assertEquals(sectionName, response.get().getName());
    }

}
