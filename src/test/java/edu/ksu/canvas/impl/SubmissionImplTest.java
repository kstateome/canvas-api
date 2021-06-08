package edu.ksu.canvas.impl;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.model.Progress;
import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.requestOptions.GetSubmissionsOptions;
import edu.ksu.canvas.requestOptions.MultipleSubmissionsOptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class SubmissionImplTest extends CanvasTestBase {
    private static final Logger LOG = LoggerFactory.getLogger(SubmissionImplTest.class);
    public static final String COURSE_ID = "37836";
    public static final String SECTION_ID = "41478";
    public static final long ASSIGNEMNET_ID = 409522;
    public static final String SOME_COMMENT_1 = "SOME COMMENT 1";
    public static final String GRADE_1 = "5";
    public static final String SOME_COMMENT_2 = "COMMENT 2";
    public static final String GRADE_2 = "5.3";
    public static final String STUDENT_ID_1 = "52359";
    public static final String STUDENT_ID_2 = "90605";

    SubmissionImpl submissionImpl;

    @Before
    public void setup() {
        String url =  baseUrl  + "/api/v1/sections/" + SECTION_ID + "/assignments/" + ASSIGNEMNET_ID + "/submissions/update_grades";
        fakeRestClient.addSuccessResponse(url, "SampleJson/Progress.json");
        submissionImpl = new SubmissionImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void sectionGradeSubmissionHappyPath() throws IOException {
        MultipleSubmissionsOptions multpleSubmissionsOptions = new MultipleSubmissionsOptions(SECTION_ID, ASSIGNEMNET_ID, null);
        Map<String, MultipleSubmissionsOptions.StudentSubmissionOption> map = new HashMap<>();
        MultipleSubmissionsOptions.StudentSubmissionOption studentSubmissionOption1 = multpleSubmissionsOptions.createStudentSubmissionOption(SOME_COMMENT_1, GRADE_1, null, null, null, null);
        MultipleSubmissionsOptions.StudentSubmissionOption studentSubmissionOption2 = multpleSubmissionsOptions.createStudentSubmissionOption(SOME_COMMENT_2, GRADE_2, true, null, null, null);
        map.put(STUDENT_ID_1, studentSubmissionOption1);
        map.put(STUDENT_ID_2, studentSubmissionOption2);
        multpleSubmissionsOptions.setStudentSubmissionOptionMap(map);

        Optional<Progress> optional = submissionImpl.gradeMultipleSubmissionsBySection(multpleSubmissionsOptions);
        Assert.assertTrue("Expected optional to not be empty, containing a Progress object.", optional.isPresent());

        Progress progress = optional.get();
        Assert.assertEquals("Expected progress object did not match returned progress object.", "61111111", progress.getId().toString());
        Assert.assertEquals("Expect no error message.", null, progress.getMessage());
        Assert.assertEquals("Expected progress object did not match returned progress object.", "canvas.example.edu/api/v1/progress/61111111", progress.getUrl());
    }

    @Test
    public void testListCourseSubmissionsForMultipleAssignments() throws IOException {
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