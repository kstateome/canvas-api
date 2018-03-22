package edu.ksu.canvas.tests.user;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.impl.UserImpl;
import edu.ksu.canvas.interfaces.UserReader;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.requestOptions.GetUsersInCourseOptions;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserRetrieverUTest extends CanvasTestBase {
    private static final Logger LOG = Logger.getLogger(UserRetrieverUTest.class);
    @Autowired
    private FakeRestClient fakeRestClient;
    private UserReader userReader;

    private static final String someCourseId = "123";

    @Before
    public void setupData() {
        userReader = new UserImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testListCourseQuizzes() throws Exception {
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/users", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserList.json");

        List<User> users = userReader.getUsersInCourse(new GetUsersInCourseOptions(someCourseId));
        Assert.assertEquals(2, users.size());
        Assert.assertTrue(users.stream().map(User::getName).filter("Student Number 1"::equals).findFirst().isPresent());
        Assert.assertTrue(users.stream().map(User::getName).filter("Student Number 2"::equals).findFirst().isPresent());
    }

    @Test
     public void testSisUserMasqueradeListCourseQuizzes() throws Exception {
        String someUserId = "8991123123";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/users?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserList.json");

        List<User> users = userReader.readAsSisUser(someUserId).getUsersInCourse(new GetUsersInCourseOptions(someCourseId));
        Assert.assertEquals(2, users.size());
        Assert.assertTrue(users.stream().map(User::getName).filter("Student Number 1"::equals).findFirst().isPresent());
        Assert.assertTrue(users.stream().map(User::getName).filter("Student Number 2"::equals).findFirst().isPresent());
    }

    @Test
    public void testCanvasUserMasqueradeListCourseQuizzes() throws Exception {
        String someUserId = "8991123123";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/users?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserList.json");

        List<User> users = userReader.readAsCanvasUser(someUserId).getUsersInCourse(new GetUsersInCourseOptions(someCourseId));
        Assert.assertEquals(2, users.size());
        Assert.assertTrue(users.stream().map(User::getName).filter("Student Number 1"::equals).findFirst().isPresent());
        Assert.assertTrue(users.stream().map(User::getName).filter("Student Number 2"::equals).findFirst().isPresent());
    }

    @Test
    public void testShowUserDetailsByUserId() throws Exception {
        int userId = 20;
        String url = baseUrl + "/api/v1/users/" + String.valueOf(userId);
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserById.json");
        Optional<User> result = userReader.showUserDetails(String.valueOf(userId));
        User user = result.get();
        Assert.assertEquals(userId, user.getId());
    }

    @Test
    public void testShowUserDetailsBySisUserId() throws Exception {
        int userId = 31;
        String sisUserId = "sis_user_id:ABC123";
        String url = baseUrl + "/api/v1/users/" + sisUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserBySisUserId.json");
        Optional<User> result = userReader.showUserDetails(sisUserId);
        User user = result.get();
        Assert.assertEquals(userId, user.getId());
    }

    @Test
    public void testShowUserDetailsBySelfIdentifier() throws Exception {
        int userId = 32;
        String selfIdentifier = "self";
        String url = baseUrl + "/api/v1/users/" + selfIdentifier;
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserBySelfIdentifier.json");
        Optional<User> result = userReader.showUserDetails(selfIdentifier);
        User user = result.get();
        Assert.assertEquals(userId, user.getId());
    }

    @Test
    public void testShowUserDetailsBySisIntegrationId() throws Exception {
        int userId = 33;
        String sisIntegrationUserId = "sis_integration_id:ABC123";
        String url = baseUrl + "/api/v1/users/" + sisIntegrationUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserBySisIntegrationId.json");
        Optional<User> result = userReader.showUserDetails(sisIntegrationUserId);
        User user = result.get();
        Assert.assertEquals(userId, user.getId());
    }
}
