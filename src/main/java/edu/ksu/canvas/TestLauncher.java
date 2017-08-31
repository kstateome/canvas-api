package edu.ksu.canvas;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import edu.ksu.canvas.interfaces.AccountReader;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.model.Account;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.oauth.OauthToken;

import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions;

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

    private static final Logger LOG = Logger.getLogger(TestLauncher.class);

    private String canvasUrl;
    private OauthToken oauthToken;

    public static void main(String[] args) {

        String canvasUrl = null;
        String oauthToken = null;
        if(args.length != 4) {
            LOG.error("Must supply two arguments: --canvas_url http://instance.instructure.com --token [Manually generated token]");
            System.exit(1);
        }
        for(int i=0; i < args.length; i++) {
            if("--canvas_url".equals(args[i])) {
                canvasUrl = args[i+1];
            }
            if("--token".equals(args[i])) {
                oauthToken = args[i+1];
            }
        }

        if(canvasUrl == null || oauthToken == null) {
            LOG.error("Canvas URL or OAuth token is blank. Must have to continue!");
            System.exit(1);
        }

        TestLauncher launcher = new TestLauncher(canvasUrl, oauthToken);
        try {
            launcher.getRootAccount();
            launcher.getOwnCourses();
        } catch(Exception e) {
            LOG.error("Problem while executing example methods", e);
        }
    }

    public TestLauncher(String canvasUrl, String tokenString) {
        this.canvasUrl = canvasUrl;
        this.oauthToken = new NonRefreshableOauthToken(tokenString);
    }

    public void getRootAccount() throws IOException {
        CanvasApiFactory apiFactory = new CanvasApiFactory(canvasUrl);
        AccountReader acctReader = apiFactory.getReader(AccountReader.class, oauthToken);
        Account rootAccount = acctReader.getSingleAccount("1").get();
        LOG.info("Got account from Canvas: " + rootAccount.getName());
    }

    public void getOwnCourses() throws IOException {
        CanvasApiFactory apiFactory = new CanvasApiFactory(canvasUrl);
        CourseReader courseReader = apiFactory.getReader(CourseReader.class, oauthToken);
        List<Course> myCourses = courseReader.listCurrentUserCourses(new ListCurrentUserCoursesOptions());
        LOG.info("Got " + myCourses.size() + " courses back from Canvas: ");
        for(Course course : myCourses) {
            LOG.info("  " + course.getName());
        }
    }
}
