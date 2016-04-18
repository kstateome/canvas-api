package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.UserImpl;
import edu.ksu.canvas.interfaces.UserWriter;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.FakeRestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class UserManagerUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private UserWriter userWriter;

    @Before
    public void setupData() {
        userWriter = new UserImpl(baseUrl, apiVersion, oauthToken.getToken(), fakeRestClient);
    }

    @Test
    public void testCreateUser() throws IOException {
        User user = new User();
        user.setName("somestring4");
        user.setLoginId("somestring4");
        Optional<User> response = userWriter.createUser(oauthToken.getToken(),user);
        System.out.println(response.toString());
        Assert.assertEquals("somestring4",response.get().getName());
        Assert.assertNotNull(response.get().getId());
    }

    @Test
    public void testDeleteUser() throws IOException {
        //Assert.assertTrue(accountImpl.deleteUser(token, 78839));
    }
}

