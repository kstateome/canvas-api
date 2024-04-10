package edu.ksu.canvas.impl;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.model.Progress;
import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.requestOptions.GetSubmissionsOptions;
import edu.ksu.canvas.requestOptions.MultipleSubmissionsOptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SubmissionImplTest extends CanvasTestBase {
    private static final Logger LOG = LoggerFactory.getLogger(SubmissionImplTest.class);
    static final String COURSE_ID = "37836";
    static final String SECTION_ID = "41478";
    static final long ASSIGNEMNET_ID = 409522;
    static final String SOME_COMMENT_1 = "SOME COMMENT 1";
    static final String GRADE_1 = "5";
    static final String SOME_COMMENT_2 = "COMMENT 2";
    static final String GRADE_2 = "5.3";
    static final String STUDENT_ID_1 = "52359";
    static final String STUDENT_ID_2 = "90605";

    SubmissionImpl submissionImpl;

    @BeforeEach
    void setup() {
        String url =  baseUrl  + "/api/v1/sections/" + SECTION_ID + "/assignments/" + ASSIGNEMNET_ID + "/submissions/update_grades";
        fakeRestClient.addSuccessResponse(url, "SampleJson/Progress.json");
        submissionImpl = new SubmissionImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void sectionGradeSubmissionHappyPath() throws IOException {
        MultipleSubmissionsOptions multpleSubmissionsOptions = new MultipleSubmissionsOptions(SECTION_ID, ASSIGNEMNET_ID, null);
        Map<String, MultipleSubmissionsOptions.StudentSubmissionOption> map = new HashMap<>();
        MultipleSubmissionsOptions.StudentSubmissionOption studentSubmissionOption1 = multpleSubmissionsOptions.createStudentSubmissionOption(SOME_COMMENT_1, GRADE_1, null, null, null, null);
        MultipleSubmissionsOptions.StudentSubmissionOption studentSubmissionOption2 = multpleSubmissionsOptions.createStudentSubmissionOption(SOME_COMMENT_2, GRADE_2, true, null, null, null);
        map.put(STUDENT_ID_1, studentSubmissionOption1);
        map.put(STUDENT_ID_2, studentSubmissionOption2);
        multpleSubmissionsOptions.setStudentSubmissionOptionMap(map);

        Optional<Progress> optional = submissionImpl.gradeMultipleSubmissionsBySection(multpleSubmissionsOptions);
        assertTrue(optional.isPresent(), "Expected optional to not be empty, containing a Progress object.");

        Progress progress = optional.get();
        assertEquals("61111111", progress.getId().toString(), "Expected progress object did not match returned progress object.");
        assertEquals(null, progress.getMessage(), "Expect no error message.");
        assertEquals("canvas.example.edu/api/v1/progress/61111111", progress.getUrl(), "Expected progress object did not match returned progress object.");
    }

    @Test
    void testListCourseSubmissionsForMultipleAssignments() throws IOException {
        String url = baseUrl + "/api/v1/courses/1234/students/submissions?student_ids[]=12345";
        fakeRestClient.addSuccessResponse(url, "SampleJson/submission/submissionResponse.json");
        GetSubmissionsOptions options = new GetSubmissionsOptions("1234");
        options.userIds(Collections.singletonList("12345"));
        List<Submission> submissions = submissionImpl.listCourseSubmissionsForMultipleAssignments(options);

        assertEquals(2, submissions.size());
        assertEquals("46399308", submissions.get(0).getId().toString());
        assertEquals("12345", submissions.get(1).getUserId().toString());
    }
}