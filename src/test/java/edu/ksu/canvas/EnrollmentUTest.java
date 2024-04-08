package edu.ksu.canvas;

import edu.ksu.canvas.impl.EnrollmentImpl;
import edu.ksu.canvas.interfaces.EnrollmentReader;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.model.Grade;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.GetEnrollmentOptions;
import edu.ksu.canvas.requestOptions.GetEnrollmentOptions.EnrollmentType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EnrollmentUTest extends CanvasTestBase {
    private static final String SECTION_ID = "1";
    @Autowired
    private FakeRestClient fakeRestClient;
    @Autowired
    private String baseUrl;
    private EnrollmentReader enrollmentsReader;

    @BeforeEach
    void setup() {
        String url =  baseUrl  + "/api/v1/sections/" + SECTION_ID + "/enrollments?type[]=StudentEnrollment";
        fakeRestClient.addSuccessResponse(url, "SampleJson/Enrollments.json");
        enrollmentsReader = new EnrollmentImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }
    
    @Test
    void getSectionEnrollmentParsesEnrollmentObject() throws Exception {
        List<Enrollment> enrollments = enrollmentsReader.getSectionEnrollments(new GetEnrollmentOptions(SECTION_ID).type(Collections.singletonList(EnrollmentType.STUDENT)));
        
        Enrollment firstEnrollment = enrollments.get(0);
        assertEquals(1, firstEnrollment.getId(), "Expected id in object to match id in json");
        assertEquals("38", firstEnrollment.getUserId(), "Expected userId in object to match userId in json");
        assertEquals("StudentEnrollment", firstEnrollment.getType(), "Expected type in object to match type in json");
        assertEquals(40, (long)firstEnrollment.getAssociatedUserId(), "Expected associatedUserID in object to match associatedUserID in json");
        assertEquals("6546", firstEnrollment.getCourseSectionId(), "Expected courseSectionId in object to match courseSectionId in json");
        assertEquals(1, (long)firstEnrollment.getRootAccountId(), "Expected rootAccountId in object to match rootAccountId in json");
        assertEquals(false, firstEnrollment.getLimitPrivilegesToCourseSection(), "Expected limitPrivileges in object to match limitPrivileges in json");
        assertEquals("active", firstEnrollment.getEnrollmentState(), "Expected enrollmentState in object to match enrollmentState in json");
        assertEquals("StudentEnrollment", firstEnrollment.getRole(), "Expected role in object to match role in json");
        assertEquals("12345", firstEnrollment.getSisCourseId(), "Expected sisCourseId in object to match sisCourseId in json");
        assertEquals("http://canvas.example.edu/courses/25/users/38", firstEnrollment.getHtmlUrl(), "Expected htmlUrl in object to match htmlUrl in json");
    }

    @Test
    void getSectionEnrollmentParsesUserObject() throws Exception {
        List<Enrollment> enrollments = enrollmentsReader.getSectionEnrollments(new GetEnrollmentOptions(SECTION_ID).type(Collections.singletonList(EnrollmentType.STUDENT)));

        Enrollment firstEnrollment = enrollments.get(0);
        User firstUser = firstEnrollment.getUser();
        assertEquals(38, firstUser.getId(), "Expected id in object to match id in json");
        assertEquals("Jane Doe", firstUser.getName(), "Expected name in object to match name in json");
        assertEquals("Doe, Jane", firstUser.getSortableName(), "Expected sortableName in object to match sortableName in json");
        assertEquals("Jane Doe", firstUser.getShortName(), "Expected shortName in object to match shortName in json");
        assertEquals("SIS_001", firstUser.getSisUserId(), "Expected sisUserId in object to match sisUserId in json");
        assertEquals("user1", firstUser.getLoginId(), "Expected loginId in object to match loginId in json");
    }

    @Test
    void getSectionEnrollmentsParsesGradeObject() throws Exception {
        List<Enrollment> enrollments = enrollmentsReader.getSectionEnrollments(new GetEnrollmentOptions(SECTION_ID).type(Collections.singletonList(EnrollmentType.STUDENT)));

        Enrollment firstEnrollment = enrollments.get(0);
        Grade grade = firstEnrollment.getGrades();
        assertEquals("http://canvas.example.edu/courses/25/grades/68794", grade.getHtmlUrl(), "Expected htmlUrl to match htmlUrl in json");
        assertEquals("45", grade.getCurrentScore(), "Expected currentScore to match currentScore in json");
        assertEquals("30.24", grade.getFinalScore(), "Expected finalScore to match finalScore in json");
        assertEquals("F", grade.getCurrentGrade(), "Expected currentGrade to match currentGrade in json");
        assertEquals("A", grade.getFinalGrade(), "Expected finalGrade to match finalGrade in json");
        assertEquals("B", grade.getOverrideGrade(), "Expected overrideGrade to match overrideGrade in json");
        assertEquals("83", grade.getOverrideScore(), "Expected overrideScore to match overrideScore in json");
    }

    @Test
    void getSectionEnrollmentsHasCorrectSizeList() throws Exception {
        List<Enrollment> enrollments = enrollmentsReader.getSectionEnrollments(new GetEnrollmentOptions(SECTION_ID).type(Collections.singletonList(EnrollmentType.STUDENT)));
        assertEquals(3, enrollments.size(), "Expected enrollmentReader to return 3 enrollments from the JSON");
    }
}
