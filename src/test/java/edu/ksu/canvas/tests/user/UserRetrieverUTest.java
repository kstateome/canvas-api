package edu.ksu.canvas.tests.user;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.UserImpl;
import edu.ksu.canvas.interfaces.UserReader;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class UserRetrieverUTest extends CanvasTestBase {
    private static final Logger LOG = Logger.getLogger(UserRetrieverUTest.class);
    @Autowired
    private FakeRestClient fakeRestClient;
    private UserReader userReader;

    @Before
    public void setupData() {
        userReader = new UserImpl(baseUrl, apiVersion, null, fakeRestClient);
    }

    @Test
    public void testListCourseQuizzes() throws Exception {
        String someCourseId = "123";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/users", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/user/UserList.json");

        List<User> users = userReader.getUsersInCourse(SOME_OAUTH_TOKEN, someCourseId);
        Assert.assertEquals(2, users.size());
        Assert.assertTrue(users.stream().map(User::getName).filter("Student Number 1"::equals).findFirst().isPresent());
        Assert.assertTrue(users.stream().map(User::getName).filter("Student Number 2"::equals).findFirst().isPresent());

    }
}
