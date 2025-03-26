package edu.ksu.canvas;

import edu.ksu.canvas.interfaces.AccountReader;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.interfaces.DiscussionTopicReader;
import edu.ksu.canvas.model.Account;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.DiscussionTopic;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetSingleDiscussionTopicOptions;
import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions;
import edu.ksu.canvas.requestOptions.ListDiscussionTopicsOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * A class with a main method that executes a couple of simple read-only requests
 * to the Canvas API. Intended as an example of how to use the library and an
 * easy place to write simple tests when developing.
 *
 * When executing the main method, you must pass in the Canvas base URL and a
 * manually generated API access token which you can get from your Canvas user
 * settings page.
 */
public class TestLauncher {

    private static final Logger LOG = LoggerFactory.getLogger(TestLauncher.class);

    private final String canvasUrl;
    private final OauthToken oauthToken;

    public static void main(String[] args) {

        String canvasUrl = System.getenv("CANVAS_URL");
        String oauthToken = System.getenv("CANVAS_API_TOKEN");

        if(canvasUrl == null || oauthToken == null) {
            LOG.error("Canvas URL or OAuth token is blank. Must have to continue!");
            System.exit(1);
        }

        TestLauncher launcher = new TestLauncher(canvasUrl, oauthToken);
        try {
            //launcher.getRootAccount();
            launcher.getOwnCourses();
            launcher.getDiscussionTopics();
        } catch(Exception e) {
            LOG.error("Problem while executing example methods", e);
        }
    }

    public TestLauncher(String canvasUrl, String tokenString) {
        this.canvasUrl = canvasUrl;
        this.oauthToken = new NonRefreshableOauthToken(tokenString);
    }

    public void getDiscussionTopics() throws IOException {
        CanvasApiFactory apiFactory = new CanvasApiFactory(canvasUrl);
        DiscussionTopicReader topicReader = apiFactory.getReader(DiscussionTopicReader.class, oauthToken);
        List<DiscussionTopic> topics = topicReader.listDiscussionTopics(new ListDiscussionTopicsOptions("141571", ListDiscussionTopicsOptions.IdType.COURSES).onlyAnnouncements());
        System.out.println("Got " + topics.size() + " topics back from Canvas: ");
        for (DiscussionTopic topic : topics) {
            System.out.println("  topic: " + topic.getTitle());
            System.out.println(topic.getLockInfo());
        }
    }

    public void getRootAccount() throws IOException {
        CanvasApiFactory apiFactory = new CanvasApiFactory(canvasUrl);
        AccountReader acctReader = apiFactory.getReader(AccountReader.class, oauthToken);
        Account rootAccount = acctReader.getSingleAccount("1").get();
        LOG.info("Got account from Canvas: {}",  rootAccount.getName());
    }

    public void getOwnCourses() throws IOException {
        CanvasApiFactory apiFactory = new CanvasApiFactory(canvasUrl);
        CourseReader courseReader = apiFactory.getReader(CourseReader.class, oauthToken);
        List<Course> myCourses = courseReader.listCurrentUserCourses(new ListCurrentUserCoursesOptions());
        LOG.info("Got {} courses back from Canvas: ",  myCourses.size());
        for(Course course : myCourses) {
            LOG.info("  course {}, name: {}", course.getId(), course.getName());
        }
    }
}
