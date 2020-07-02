package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CourseImpl;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.GetSingleCourseOptions;
import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions;
import edu.ksu.canvas.requestOptions.ListUserCoursesOptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class CourseGetUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private CourseReader courseReader;

    @Before
    public void setupData() {
        courseReader = new CourseImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testGettingCourses() throws IOException {
        String url = baseUrl + "/api/v1/courses/1234?include[]=course_image";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/GetCourseIncludeCourseImage.json");
        Optional<Course> optionalCourse = courseReader.getSingleCourse(new GetSingleCourseOptions("1234").includes(Collections.singletonList(GetSingleCourseOptions.Include.COURSE_IMAGE)));
        assertTrue(optionalCourse.isPresent());
        Course course = optionalCourse.get();
        assertEquals(1234, course.getId().intValue());
        assertEquals("Test", course.getName());
        assertEquals(567, course.getAccountId().intValue());
        assertNull(course.getStartAt());
        assertFalse(course.getIsPublic());
        assertEquals("T-001", course.getCourseCode());
        assertEquals("modules", course.getDefaultView());
        assertEquals(48, course.getEnrollmentTermId());
        assertEquals("private", course.getLicense());
        assertNull(course.getEndAt());
        assertFalse(course.getPublicSyllabus());
        assertFalse(course.getPublicSyllabusToAuth());
        assertEquals(5000, course.getStorageQuotaMb().intValue());
        assertFalse(course.getIsPublicToAuthUsers());
        assertFalse(course.getHideFinalGrades());
        assertFalse(course.getApplyAssignmentGroupWeights());
        assertEquals("Europe/London", course.getTimeZone());
        assertNotNull(course.getImageDownloadUrl());
        assertTrue(course.getImageDownloadUrl().startsWith("https://"));
        assertNull(course.getSisCourseId());
        assertEquals("c89b1859-0da0-403c-a29f-db34f917459c", course.getIntegrationId());
        assertEquals("unpublished", course.getWorkflowState());
        assertFalse(course.getRestrictEnrollmentsToCourseDates());
        assertFalse(course.getBlueprint());
        ZonedDateTime zdt = ZonedDateTime.parse("2020-05-22T12:24:09Z");
        assertEquals(course.getCreatedAt(), Date.from(zdt.toInstant()));
    }

}
