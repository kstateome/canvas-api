package edu.ksu.canvas.impl;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.interfaces.SubmissionWriter;
import edu.ksu.canvas.model.assignment.Progress;
import edu.ksu.canvas.requestOptions.SubmissionOptions;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SubmissionImplTest extends CanvasTestBase {
    private static final Logger LOG = Logger.getLogger(SubmissionImplTest.class);
    public static final int COURSE_ID = 37836;
    public static final int SECTION_ID = 41478;
    public static final int ASSIGNEMNET_ID = 409522;
    public static final String SOME_COMMENT_1 = "SOME COMMENT 1";
    public static final String GRADE_1 = "5";
    public static final String SOME_COMMENT_2 = "COMMENT 2";
    public static final String GRADE_2 = "5.3";
    public static final int STUDENT_ID_1 = 52359;
    public static final int STUDENT_ID_2 = 90605;

    SubmissionWriter submissionWriter;

    @Before
    public void setup() {
        String url =  baseUrl  + "/api/v1/sections/" + SECTION_ID + "/assignments/" + ASSIGNEMNET_ID + "/submissions/update_grades";
        LOG.info(url);
        fakeRestClient.addSuccessResponse(url, "SampleJson/Progress.json");
        submissionWriter = new SubmissionImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE);
    }

    @Test
    public void sectionGradeSubmissionHappyPath() throws IOException {
        SubmissionOptions submissionOptions = new SubmissionOptions(COURSE_ID, SECTION_ID, ASSIGNEMNET_ID, null);
        Map<Integer, SubmissionOptions.StudentSubmissionOption> map = new HashMap<>();
        SubmissionOptions.StudentSubmissionOption studentSubmissionOption1 = submissionOptions.createStudentSubmissionOption(SOME_COMMENT_1, GRADE_1, null, null, null, null);
        SubmissionOptions.StudentSubmissionOption studentSubmissionOption2 = submissionOptions.createStudentSubmissionOption(SOME_COMMENT_2, GRADE_2, true, null, null, null);
        map.put(STUDENT_ID_1, studentSubmissionOption1);
        map.put(STUDENT_ID_2, studentSubmissionOption2);
        submissionOptions.setStudentSubmissionOptionMap(map);

        Optional<Progress> optional = submissionWriter.sectionGradeSubmission(submissionOptions);
        Assert.assertTrue(optional.isPresent());

        Progress progress = optional.get();
        Assert.assertEquals("613777", progress.getId().toString());
        Assert.assertEquals(null, progress.getMessage());
        Assert.assertEquals("https://k-state.test.instructure.com/api/v1/progress/613777", progress.getUrl());
    }

}