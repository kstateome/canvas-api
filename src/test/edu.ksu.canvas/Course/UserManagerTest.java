package Course;

import config.BaseTestConfig;
import config.CourseTestConfig;
import edu.ksu.canvas.impl.UserImpl;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import scala.runtime.TraitSetter;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by prakashreddy on 10/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = {BaseTestConfig.class})
@WebAppConfiguration
public class UserManagerTest {
    String canvasBaseURL = "https://k-state.test.instructure.com";
    Integer apiVersion = 1;
    String token = "1726~MQ8vuaJUbVosHUcvEcUfdHixQobAkS03AxSKVXvRy79lAcSX2uURHc2IHnDINpP2";

    UserImpl userImpl;

    @Before
    public void setupData(){
        userImpl = new UserImpl(canvasBaseURL,apiVersion,token);
    }

    @Test
    public void testCreateUser() throws IOException{
        User user = new User();
        user.setId(1);
        user.setName("Selenium Test User 1");
        user.setEmail("selenium_test_user@test.test");
        user.setLoginId("selenium_test_user_loginId");
        Optional<User> response = userImpl.createUser(token,user);
        Assert.assertEquals(response.get().getName(), "Selenium Test User 1");
        Assert.assertEquals(response.get().getLoginId(),"selenium_test_user_loginId");
    }

    @Test
    public void testDeleteUser(){
        User user = new User();
        user.setName("Selenium Test User 1");

    }
}

