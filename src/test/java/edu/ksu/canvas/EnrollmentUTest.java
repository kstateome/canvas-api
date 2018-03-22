package edu.ksu.canvas;

import edu.ksu.canvas.impl.EnrollmentImpl;
import edu.ksu.canvas.interfaces.EnrollmentReader;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.model.Grade;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.GetEnrollmentOptions;
import edu.ksu.canvas.requestOptions.GetEnrollmentOptions.EnrollmentType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class EnrollmentUTest extends CanvasTestBase {
    private static final String SECTION_ID = "1";
    @Autowired
    private FakeRestClient fakeRestClient;
    @Autowired
    private String baseUrl;
    private EnrollmentReader enrollmentsReader;

    @Before
    public void setup() {
        String url =  baseUrl  + "/api/v1/sections/" + SECTION_ID + "/enrollments?type[]=StudentEnrollment";
        fakeRestClient.addSuccessResponse(url, "SampleJson/Enrollments.json");
        enrollmentsReader = new EnrollmentImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }
    
    @Test
    public void getSectionEnrollmentParsesEnrollmentObject() throws Exception {
        List<Enrollment> enrollments = enrollmentsReader.getSectionEnrollments(new GetEnrollmentOptions(SECTION_ID).type(Collections.singletonList(EnrollmentType.STUDENT)));
        
        Enrollment firstEnrollment = enrollments.get(0);
        Assert.assertEquals("Expected id in object to match id in json", 1, firstEnrollment.getId());
        Assert.assertEquals("Expected userId in object to match userId in json", "38", firstEnrollment.getUserId());
        Assert.assertEquals("Expected type in object to match type in json", "StudentEnrollment", firstEnrollment.getType());
        Assert.assertEquals("Expected associatedUserID in object to match associatedUserID in json", 40, (int)firstEnrollment.getAssociatedUserId());
        Assert.assertEquals("Expected courseSectionId in object to match courseSectionId in json", "6546", firstEnrollment.getCourseSectionId());
        Assert.assertEquals("Expected rootAccountId in object to match rootAccountId in json", 1, (int)firstEnrollment.getRootAccountId());
        Assert.assertEquals("Expected limitPrivileges in object to match limitPrivileges in json", false, firstEnrollment.getLimitPrivilegesToCourseSection());
        Assert.assertEquals("Expected enrollmentState in object to match enrollmentState in json", "active", firstEnrollment.getEnrollmentState());
        Assert.assertEquals("Expected role in object to match role in json", "StudentEnrollment", firstEnrollment.getRole());
        Assert.assertEquals("Expected sisCourseId in object to match sisCourseId in json", "12345", firstEnrollment.getSisCourseId());
        Assert.assertEquals("Expected htmlUrl in object to match htmlUrl in json", "http://canvas.example.edu/courses/25/users/38", firstEnrollment.getHtmlUrl());
    }

    @Test
    public void getSectionEnrollmentParsesUserObject() throws Exception {
        List<Enrollment> enrollments = enrollmentsReader.getSectionEnrollments(new GetEnrollmentOptions(SECTION_ID).type(Collections.singletonList(EnrollmentType.STUDENT)));

        Enrollment firstEnrollment = enrollments.get(0);
        User firstUser = firstEnrollment.getUser();
        Assert.assertEquals("Expected id in object to match id in json", 38, firstUser.getId());
        Assert.assertEquals("Expected name in object to match name in json", "Jane Doe", firstUser.getName());
        Assert.assertEquals("Expected sortableName in object to match sortableName in json", "Doe, Jane", firstUser.getSortableName());
        Assert.assertEquals("Expected shortName in object to match shortName in json", "Jane Doe", firstUser.getShortName());
        Assert.assertEquals("Expected sisUserId in object to match sisUserId in json", "SIS_001", firstUser.getSisUserId());
        Assert.assertEquals("Expected loginId in object to match loginId in json", "user1", firstUser.getLoginId());
    }

    @Test
    public void getSectionEnrollmentsParsesGradeObject() throws Exception {
        List<Enrollment> enrollments = enrollmentsReader.getSectionEnrollments(new GetEnrollmentOptions(SECTION_ID).type(Collections.singletonList(EnrollmentType.STUDENT)));

        Enrollment firstEnrollment = enrollments.get(0);
        Grade grade = firstEnrollment.getGrades();
        Assert.assertEquals("Expected htmlUrl to match htmlUrl in json", "http://canvas.example.edu/courses/25/grades/68794", grade.getHtmlUrl());
        Assert.assertEquals("Expected currentScore to match currentScore in json", "45", grade.getCurrentScore());
        Assert.assertEquals("Expected finalScore to match finalScore in json", "30.24", grade.getFinalScore());
        Assert.assertEquals("Expected currentGrade to match currentGrade in json", "F", grade.getCurrentGrade());
        Assert.assertEquals("Expected finalGrade to match finalGrade in json", "A", grade.getFinalGrade());
    }

    @Test
    public void getSectionEnrollmentsHasCorrectSizeList() throws Exception {
        List<Enrollment> enrollments = enrollmentsReader.getSectionEnrollments(new GetEnrollmentOptions(SECTION_ID).type(Collections.singletonList(EnrollmentType.STUDENT)));
        Assert.assertEquals("Expected enrollmentReader to return 3 enrollments from the JSON", 3, enrollments.size());
    }
    
    
}
