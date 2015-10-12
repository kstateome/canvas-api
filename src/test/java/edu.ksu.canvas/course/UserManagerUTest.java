package edu.ksu.canvas.course;

import edu.ksu.canvas.config.BaseTestConfig;
import edu.ksu.canvas.impl.AccountImpl;
import edu.ksu.canvas.impl.UserImpl;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.FakeRestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = {BaseTestConfig.class})
@WebAppConfiguration
public class UserManagerUTest {
    @Autowired
    private FakeRestClient fakeRestClient;
    private String canvasBaseURL = "https://k-state.test.instructure.com";
    private Integer apiVersion = 1;
    private String token = "1726~MQ8vuaJUbVosHUcvEcUfdHixQobAkS03AxSKVXvRy79lAcSX2uURHc2IHnDINpP2";
    private Integer userId ;
    private UserImpl userImpl;
    private AccountImpl accountImpl;

    @Before
    public void setupData() {
        userImpl = new UserImpl(canvasBaseURL, apiVersion, token, fakeRestClient);
        accountImpl = new AccountImpl(canvasBaseURL, apiVersion, token, fakeRestClient);
    }

    @Test
    public void testCreateUser() throws IOException {
        User user = new User();
        user.setName("somestring4");
        user.setLoginId("somestring4");
        Optional<User> response = userImpl.createUser(token,user);
        userId=response.get().getId();
        System.out.println(response.toString());
        Assert.assertEquals("somestring4",response.get().getName());
        Assert.assertNotNull(response.get().getId());
    }

    @Test
    public void testDeleteUser() throws IOException {
        //Assert.assertTrue(accountImpl.deleteUser(token, 78839));
    }
}

