package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.enums.SectionIncludes;
import edu.ksu.canvas.impl.SectionsImpl;
import edu.ksu.canvas.interfaces.SectionReader;
import edu.ksu.canvas.interfaces.SectionWriter;
import edu.ksu.canvas.model.Section;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.FakeRestClient;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SectionManagerUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private SectionReader sectionReader;
    private SectionWriter sectionWriter;

    private static final String ARBITRARY_COURSE_ID = "503";

    @BeforeEach
    void setupData() {
        sectionWriter = new SectionsImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
            SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
        sectionReader = new SectionsImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
            SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testSectionCreation() throws IOException, URISyntaxException, ParseException {

        String sectionName = "someName";

        Section newSection = new Section();
        newSection.setName(sectionName);

        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID + "/sections";
        fakeRestClient.addSuccessResponse(url, "SampleJson/section/CreateSectionsSuccess.json");
        Optional<Section> response = sectionWriter.createSection(ARBITRARY_COURSE_ID, newSection, null);

        assertNotNull(response.get().getId());
        assertEquals(sectionName, response.get().getName());
    }

    @Test
    void testSectionStudents() throws IOException, URISyntaxException, ParseException {

        final List<SectionIncludes> includes = Arrays.asList(
                SectionIncludes.STUDENTS,
                SectionIncludes.ENROLLMENTS
        );
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID + "/sections?include[]=students&include[]=enrollments";
        fakeRestClient.addSuccessResponse(url, "SampleJson/section/Sections.json");
        List<Section> response = sectionReader.listCourseSections(ARBITRARY_COURSE_ID, includes);

        assertNotNull(response);
        assertEquals(10, response.size());
        for(Section s : response) {
            if(s.getId() == 47397 || s.getId() == 47399) {
                assertNotNull(s.getStudents());
                assertFalse(s.getStudents().isEmpty());
                for(User u : s.getStudents()) {
                    assertNotNull(u.getEnrollments());
                }
            } else {
                assertNull(s.getStudents());
            }
        }
    }
}
