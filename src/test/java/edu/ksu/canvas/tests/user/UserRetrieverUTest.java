package edu.ksu.canvas.tests.user;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.impl.UserImpl;
import edu.ksu.canvas.interfaces.UserReader;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.requestOptions.GetUsersInAccountOptions;
import edu.ksu.canvas.requestOptions.GetUsersInCourseOptions;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRetrieverUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private UserReader userReader;

    private static final String someCourseId = "123";

    @BeforeEach
    void setupData() {
        userReader = new UserImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testListCourseQuizzes() throws Exception {
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/users", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserList.json");

        List<User> users = userReader.getUsersInCourse(new GetUsersInCourseOptions(someCourseId));
        assertEquals(2, users.size());
        assertTrue(users.stream().map(User::getName).filter("Student Number 1"::equals).findFirst().isPresent());
        assertTrue(users.stream().map(User::getName).filter("Student Number 2"::equals).findFirst().isPresent());
    }

    @Test
     void testSisUserMasqueradeListCourseQuizzes() throws Exception {
        String someUserId = "8991123123";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/users?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserList.json");

        List<User> users = userReader.readAsSisUser(someUserId).getUsersInCourse(new GetUsersInCourseOptions(someCourseId));
        assertEquals(2, users.size());
        assertTrue(users.stream().map(User::getName).filter("Student Number 1"::equals).findFirst().isPresent());
        assertTrue(users.stream().map(User::getName).filter("Student Number 2"::equals).findFirst().isPresent());
    }

    @Test
    void testCanvasUserMasqueradeListCourseQuizzes() throws Exception {
        String someUserId = "8991123123";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/users?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserList.json");

        List<User> users = userReader.readAsCanvasUser(someUserId).getUsersInCourse(new GetUsersInCourseOptions(someCourseId));
        assertEquals(2, users.size());
        assertTrue(users.stream().map(User::getName).filter("Student Number 1"::equals).findFirst().isPresent());
        assertTrue(users.stream().map(User::getName).filter("Student Number 2"::equals).findFirst().isPresent());
    }

    @Test
    void testShowUserDetailsByUserId() throws Exception {
        long userId = 20;
        String url = baseUrl + "/api/v1/users/" + String.valueOf(userId);
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserById.json");
        Optional<User> result = userReader.showUserDetails(String.valueOf(userId));
        User user = result.get();
        assertEquals(userId, user.getId());
        assertEquals("2011-05-30T16:45:25Z", user.getCreatedAt().toString());
    }

    @Test
    void testShowUserDetailsByUserIdLong() throws Exception {
        // When users exist from multiple instances the IDs can become very long (larger than an int).
        long userId = 123450000000000020L;
        String url = baseUrl + "/api/v1/users/" + String.valueOf(userId);
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserByIdLong.json");
        Optional<User> result = userReader.showUserDetails(String.valueOf(userId));
        User user = result.get();
        assertEquals(userId, user.getId());
    }

    @Test
    void testShowUserDetailsBySisUserId() throws Exception {
        long userId = 31;
        String sisUserId = "sis_user_id:ABC123";
        String url = baseUrl + "/api/v1/users/" + sisUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserBySisUserId.json");
        Optional<User> result = userReader.showUserDetails(sisUserId);
        User user = result.get();
        assertEquals(userId, user.getId());
    }

    @Test
    void testShowUserDetailsBySelfIdentifier() throws Exception {
        long userId = 32;
        String selfIdentifier = "self";
        String url = baseUrl + "/api/v1/users/" + selfIdentifier;
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserBySelfIdentifier.json");
        Optional<User> result = userReader.showUserDetails(selfIdentifier);
        User user = result.get();
        assertEquals(userId, user.getId());
    }

    @Test
    void testShowUserDetailsBySisIntegrationId() throws Exception {
        long userId = 33;
        String sisIntegrationUserId = "sis_integration_id:ABC123";
        String url = baseUrl + "/api/v1/users/" + sisIntegrationUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserBySisIntegrationId.json");
        Optional<User> result = userReader.showUserDetails(sisIntegrationUserId);
        User user = result.get();
        assertEquals(userId, user.getId());
    }

    @Test
    void testGetAllUsersFromAccount() throws Exception {
        String accountId = "1";
        String url = baseUrl + "/api/v1/accounts/" + accountId + "/users";
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/LargeUserList.json");

        GetUsersInAccountOptions options = new GetUsersInAccountOptions(accountId);
        List<User> result = userReader.getUsersInAccount(options);
        assertEquals(100, result.size());
    }

    @Test
    void testGetAllUsersBySearchTermFromAccount() throws Exception {
        String accountId = "1";
        String url = baseUrl + "/api/v1/accounts/" + accountId + "/users?search_term=test user47";
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/IndividualUserList.json");

        GetUsersInAccountOptions options = new GetUsersInAccountOptions(accountId).searchTerm("test user47");
        List<User> result = userReader.getUsersInAccount(options);
        assertEquals(1, result.size());
    }
}
