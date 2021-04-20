package edu.ksu.canvas.tests.course;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.enums.SectionIncludes;
import edu.ksu.canvas.impl.SectionsImpl;
import edu.ksu.canvas.interfaces.SectionReader;
import edu.ksu.canvas.interfaces.SectionWriter;
import edu.ksu.canvas.model.Section;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.FakeRestClient;

public class SectionManagerUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private SectionReader sectionReader;
    private SectionWriter sectionWriter;

    private static final String ARBITRARY_COURSE_ID = "503";

    @Before
    public void setupData() {
        sectionWriter = new SectionsImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
            SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
        sectionReader = new SectionsImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
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

    @Test
    public void testSectionStudents() throws IOException {

        final List<SectionIncludes> includes = Arrays.asList(
                SectionIncludes.STUDENTS,
                SectionIncludes.ENROLLMENTS
        );
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID + "/sections?include[]=students&include[]=enrollments";
        fakeRestClient.addSuccessResponse(url, "SampleJson/section/Sections.json");
        List<Section> response = sectionReader.listCourseSections(ARBITRARY_COURSE_ID, includes);

        Assert.assertNotNull(response);
        Assert.assertEquals(10, response.size());
        for(Section s : response) {
            if(s.getId() == 47397 || s.getId() == 47399) {
                Assert.assertNotNull(s.getStudents());
                Assert.assertTrue(s.getStudents().size() > 0);
                for(User u : s.getStudents()) {
                    Assert.assertNotNull(u.getEnrollments());
                }
            } else {
                Assert.assertNull(s.getStudents());
            }
        }
    }
}
