package edu.ksu.canvas;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.ksu.canvas.impl.*;
import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.RestClientImpl;

public class CanvasApiFactory {

    private static final Logger LOG = Logger.getLogger(CanvasApiFactory.class);

    public static final Integer CANVAS_API_VERSION = 1;
    private static final int DEFAULT_CONNECT_TIMEOUT_MS = 5000;
    private static final int DEFAULT_READ_TIMEOUT_MS = 120000;
    private String canvasBaseUrl;
    private int connectTimeout;
    private int readTimeout;

    Map<Class<? extends CanvasReader>, Class<? extends BaseImpl>> readerMap;
    Map<Class<? extends CanvasWriter>, Class<? extends BaseImpl>> writerMap;

    public CanvasApiFactory(String canvasBaseUrl) {
        LOG.debug("Creating Canvas API factory with base URL: " + canvasBaseUrl);
        this.canvasBaseUrl = canvasBaseUrl;
        this.connectTimeout = DEFAULT_CONNECT_TIMEOUT_MS;
        this.readTimeout = DEFAULT_READ_TIMEOUT_MS;
        setupClassMap();
    }

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
     * @return A reader implementation class
     */
    public <T extends CanvasReader> T getReader(Class<T> type, String oauthToken) {
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
     * @return
     */
    public <T extends CanvasReader> T getReader(Class<T> type, String oauthToken, Integer paginationPageSize) {
        LOG.debug("Factory call to instantiate class: " + type.getName());
        RestClient restClient = new RestClientImpl();

        @SuppressWarnings("unchecked")
        Class<T> concreteClass = (Class<T>)readerMap.get(type);

        if (concreteClass == null) {
            throw new UnsupportedOperationException("No implementation for requested interface found: " + type.getName());
        }

        LOG.debug("got class: " + concreteClass);
        try {
            Constructor<T> constructor = concreteClass.getConstructor(String.class, Integer.class, String.class, RestClient.class, Integer.TYPE, Integer.TYPE, Integer.class);
            return constructor.newInstance(canvasBaseUrl, CANVAS_API_VERSION, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new UnsupportedOperationException("Unknown error instantiating the concrete API class: " + type.getName(), e);
        }
    }

    public <T extends CanvasWriter> T getWriter(Class<T> type, String oauthToken) {
        LOG.debug("Factory call to instantiate class: " + type.getName());
        RestClient restClient = new RestClientImpl();

        @SuppressWarnings("unchecked")
        Class<T> concreteClass = (Class<T>) writerMap.get(type);

        if (concreteClass == null) {
            throw new UnsupportedOperationException("No implementation for requested interface found: " + type.getName());
        }

        LOG.debug("got class: " + concreteClass);
        try {
            Constructor<T> constructor = concreteClass.getConstructor(String.class, Integer.class, String.class, RestClient.class, Integer.TYPE, Integer.TYPE);
            return constructor.newInstance(canvasBaseUrl, CANVAS_API_VERSION, oauthToken, restClient, connectTimeout, readTimeout);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new UnsupportedOperationException("Unknown error instantiating the concrete API class: " + type.getName(), e);
        }
    }

    private void setupClassMap() {
        readerMap = new HashMap<>();
        writerMap = new HashMap<>();
        readerMap.put(AccountsReader.class, AccountImpl.class);
        readerMap.put(AssignmentOverrideReader.class, AssignmentOverrideImp.class);
        readerMap.put(AssignmentReader.class, AssignmentsImpl.class);
        readerMap.put(CourseReader.class, CoursesImpl.class);
        readerMap.put(EnrollmentsReader.class, EnrollmentsImpl.class);
        readerMap.put(QuizQuestionReader.class, QuizQuestionImpl.class);
        readerMap.put(QuizReader.class, QuizImpl.class);
        readerMap.put(QuizSubmissionQuestionReader.class, QuizSubmissionImpl.class);
        readerMap.put(QuizSubmissionReader.class, QuizSubmissionImpl.class);
        readerMap.put(SectionReader.class, SectionsImpl.class);
        readerMap.put(UserReader.class, UserImpl.class);

        writerMap.put(AccountsWriter.class, AccountImpl.class);
        writerMap.put(AssignmentOverrideWriter.class, AssignmentOverrideImp.class);
        writerMap.put(AssignmentWriter.class, AssignmentsImpl.class);
        writerMap.put(CourseWriter.class, CoursesImpl.class);
        writerMap.put(EnrollmentsWriter.class, EnrollmentsImpl.class);
        writerMap.put(QuizQuestionWriter.class, QuizQuestionImpl.class);
        writerMap.put(QuizWriter.class, QuizImpl.class);
        writerMap.put(QuizSubmissionQuestionWriter.class, QuizSubmissionImpl.class);
        writerMap.put(QuizSubmissionWriter.class, QuizSubmissionImpl.class);
        writerMap.put(UserWriter.class, UserImpl.class);
    }
}
