package edu.ksu.canvas;

import edu.ksu.canvas.impl.*;
import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.RefreshingRestClient;
import edu.ksu.canvas.oauth.OauthToken;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Entry point for using the Canvas API library. It constructs concrete
 * implementations of reader/writer classes to perform API calls with.
 * It must be constructed with a Canvas instance URL which will be used
 * for all API calls. It has options to specify network timeouts and a way
 * to control API pagination.
 */
public class CanvasApiFactory {

    public static final Integer CANVAS_API_VERSION = 1;
    private static final Logger LOG = Logger.getLogger(CanvasApiFactory.class);
    private static final int DEFAULT_CONNECT_TIMEOUT_MS = 5000;
    private static final int DEFAULT_READ_TIMEOUT_MS = 120000;
    Map<Class<? extends CanvasReader>, Class<? extends BaseImpl>> readerMap;
    Map<Class<? extends CanvasWriter>, Class<? extends BaseImpl>> writerMap;
    private String canvasBaseUrl;
    private int connectTimeout;
    private int readTimeout;

    /**
     * Construct an API factory for a given instance of Canvas.
     * @param canvasBaseUrl The base URL used to access your Canvas instance
     */
    public CanvasApiFactory(String canvasBaseUrl) {
        LOG.debug("Creating Canvas API factory with base URL: " + canvasBaseUrl);
        this.canvasBaseUrl = canvasBaseUrl;
        this.connectTimeout = DEFAULT_CONNECT_TIMEOUT_MS;
        this.readTimeout = DEFAULT_READ_TIMEOUT_MS;
        setupClassMap();
    }

    /**
     * Construct an API factory with specified timeout values
     * @param canvasBaseUrl The base URL used to access your Canvas instance
     * @param connectTimeout Connection timeout in milliseconds
     * @param readTimeout Read timeout in milliseconds. If this is too low, longer API queries could time out prematurely
     */
    public CanvasApiFactory(String canvasBaseUrl, int connectTimeout, int readTimeout) {
        this.canvasBaseUrl = canvasBaseUrl;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        setupClassMap();
    }

    /**
     * Get a reader implementation class to perform API calls with.
     * @param type Interface type you wish to get an implementation for
     * @param oauthToken An OAuth token to use for authentication when making API calls
     * @param <T> The reader type to request an instance of
     * @return A reader implementation class
     */
    public <T extends CanvasReader> T getReader(Class<T> type, OauthToken oauthToken) {
        return getReader(type, oauthToken, null);
    }

    /**
     * Get a reader implementation class to perform API calls with while specifying
     * an explicit page size for paginated API calls. This gets translated to a per_page=
     * parameter on API requests. Note that Canvas does not guarantee it will honor this page size request.
     * There is an explicit maximum page size on the server side which could change. The default page size
     * is 10 which can be limiting when, for example, trying to get all users in a 800 person course.
     * @param type Interface type you wish to get an implementation for
     * @param oauthToken An OAuth token to use for authentication when making API calls
     * @param paginationPageSize Requested pagination page size
     * @param <T> The reader type to request an instance of
     * @return An instance of the requested reader class
     */
    public <T extends CanvasReader> T getReader(Class<T> type, OauthToken oauthToken, Integer paginationPageSize) {
        LOG.debug("Factory call to instantiate class: " + type.getName());
        RestClient restClient = new RefreshingRestClient();

        @SuppressWarnings("unchecked")
        Class<T> concreteClass = (Class<T>)readerMap.get(type);

        if (concreteClass == null) {
            throw new UnsupportedOperationException("No implementation for requested interface found: " + type.getName());
        }

        LOG.debug("got class: " + concreteClass);
        try {
            Constructor<T> constructor = concreteClass.getConstructor(String.class, Integer.class, OauthToken.class, RestClient.class, Integer.TYPE, Integer.TYPE, Integer.class);
            return constructor.newInstance(canvasBaseUrl, CANVAS_API_VERSION, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new UnsupportedOperationException("Unknown error instantiating the concrete API class: " + type.getName(), e);
        }
    }

    /**
     * Get a writer implementation to push data into Canvas.
     * @param type Interface type you wish to get an implementation for
     * @param oauthToken An OAuth token to use for authentication when making API calls
     * @param <T> A writer implementation
     * @return A writer implementation class
     */
    public <T extends CanvasWriter> T getWriter(Class<T> type, OauthToken oauthToken) {
        LOG.debug("Factory call to instantiate class: " + type.getName());
        RestClient restClient = new RefreshingRestClient();

        @SuppressWarnings("unchecked")
        Class<T> concreteClass = (Class<T>) writerMap.get(type);

        if (concreteClass == null) {
            throw new UnsupportedOperationException("No implementation for requested interface found: " + type.getName());
        }

        LOG.debug("got class: " + concreteClass);
        try {
            Constructor<T> constructor = concreteClass.getConstructor(String.class, Integer.class, OauthToken.class, RestClient.class, Integer.TYPE, Integer.TYPE, Integer.class);
            return constructor.newInstance(canvasBaseUrl, CANVAS_API_VERSION, oauthToken, restClient, connectTimeout, readTimeout, null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new UnsupportedOperationException("Unknown error instantiating the concrete API class: " + type.getName(), e);
        }
    }

    private void setupClassMap() {
        readerMap = new HashMap<>();
        writerMap = new HashMap<>();
        readerMap.put(AccountReader.class, AccountImpl.class);
        readerMap.put(AssignmentOverrideReader.class, AssignmentOverrideImpl.class);
        readerMap.put(AssignmentReader.class, AssignmentImpl.class);
        readerMap.put(ConversationReader.class, ConversationImpl.class);
        readerMap.put(CourseReader.class, CourseImpl.class);
        readerMap.put(EnrollmentReader.class, EnrollmentImpl.class);
        readerMap.put(QuizQuestionReader.class, QuizQuestionImpl.class);
        readerMap.put(QuizReader.class, QuizImpl.class);
        readerMap.put(QuizSubmissionQuestionReader.class, QuizSubmissionQuestionImpl.class);
        readerMap.put(QuizSubmissionReader.class, QuizSubmissionImpl.class);
        readerMap.put(SectionReader.class, SectionsImpl.class);
        readerMap.put(UserReader.class, UserImpl.class);
        readerMap.put(PageReader.class, PageImpl.class);
        readerMap.put(EnrollmentTermReader.class, EnrollmentTermImpl.class);
        readerMap.put(SubmissionReader.class, SubmissionImpl.class);
        readerMap.put(AssignmentGroupReader.class, AssignmentGroupImpl.class);
        readerMap.put(RoleReader.class, RoleImpl.class);
        readerMap.put(ExternalToolReader.class, ExternalToolImpl.class);
        readerMap.put(LoginReader.class, LoginImpl.class);

        writerMap.put(AssignmentOverrideWriter.class, AssignmentOverrideImpl.class);
        writerMap.put(AssignmentWriter.class, AssignmentImpl.class);
        writerMap.put(ConversationWriter.class, ConversationImpl.class);
        writerMap.put(CourseWriter.class, CourseImpl.class);
        writerMap.put(EnrollmentWriter.class, EnrollmentImpl.class);
        writerMap.put(QuizQuestionWriter.class, QuizQuestionImpl.class);
        writerMap.put(QuizWriter.class, QuizImpl.class);
        writerMap.put(QuizSubmissionQuestionWriter.class, QuizSubmissionQuestionImpl.class);
        writerMap.put(QuizSubmissionWriter.class, QuizSubmissionImpl.class);
        writerMap.put(SectionWriter.class, SectionsImpl.class);
        writerMap.put(UserWriter.class, UserImpl.class);
        writerMap.put(PageWriter.class, PageImpl.class);
        writerMap.put(SectionWriter.class, SectionsImpl.class);
        writerMap.put(SubmissionWriter.class, SubmissionImpl.class);
        writerMap.put(AssignmentGroupWriter.class, AssignmentGroupImpl.class);
        writerMap.put(RoleWriter.class, RoleImpl.class);
        writerMap.put(ExternalToolWriter.class, ExternalToolImpl.class);
        writerMap.put(LoginWriter.class, LoginImpl.class);
    }
}
