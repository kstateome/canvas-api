package edu.ksu.canvas;

import com.google.common.reflect.ClassPath;
import edu.ksu.canvas.annotation.AbstainRegister;
import edu.ksu.canvas.impl.*;
import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.RefreshingRestClient;
import edu.ksu.canvas.oauth.OauthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Entry point for using the Canvas API library. It constructs concrete
 * implementations of reader/writer classes to perform API calls with.
 * It must be constructed with a Canvas instance URL which will be used
 * for all API calls. It has options to specify network timeouts and a way
 * to control API pagination.
 */
public class CanvasApiFactory {

    public static final Integer CANVAS_API_VERSION = 1;
    private static final Logger LOG = LoggerFactory.getLogger(CanvasApiFactory.class);
    private static final String IMPLEMENTATION_PACKAGE = "edu.ksu.canvas.impl";
    private static final int DEFAULT_CONNECT_TIMEOUT_MS = 5000;
    private static final int DEFAULT_READ_TIMEOUT_MS = 120000;
    private final Map<Class<? extends CanvasReader>, Class<? extends BaseImpl>> readerMap = new HashMap<>();
    private final Map<Class<? extends CanvasWriter>, Class<? extends BaseImpl>> writerMap = new HashMap<>();
    private final String canvasBaseUrl;
    private final int connectTimeout;
    private final int readTimeout;

    /**
     * Construct an API factory for a given instance of Canvas.
     * @param canvasBaseUrl The base URL used to access your Canvas instance
     */
    public CanvasApiFactory(String canvasBaseUrl) {
        LOG.debug("Creating Canvas API factory with base URL: {}", canvasBaseUrl);
        this.canvasBaseUrl = canvasBaseUrl;
        this.connectTimeout = DEFAULT_CONNECT_TIMEOUT_MS;
        this.readTimeout = DEFAULT_READ_TIMEOUT_MS;
        try {
            setupClassMap();
        } catch (IOException e) {
            LOG.error("Error setting up class map", e);
            throw new RuntimeException("Error setting up class map", e);
        }
    }

    /**
     * Construct an API factory with specified timeout values
     * @param canvasBaseUrl The base URL used to access your Canvas instance
     * @param connectTimeout Connection timeout in milliseconds
     * @param readTimeout Read timeout in milliseconds. If this is too low, longer API queries could time out prematurely
     */
    public CanvasApiFactory(String canvasBaseUrl, int connectTimeout, int readTimeout) {
        LOG.debug("Creating Canvas API factory with base URL: {}, connect timeout: {}, read timeout: {}", canvasBaseUrl, connectTimeout, readTimeout);
        this.canvasBaseUrl = canvasBaseUrl;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        try {
            setupClassMap();
        } catch (IOException e) {
            LOG.error("Error setting up class map", e);
            throw new RuntimeException("Error setting up class map", e);
        }
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
        LOG.debug("Factory call to instantiate reader class: {}", type.getName());
        RestClient restClient = new RefreshingRestClient();

        @SuppressWarnings("unchecked")
        Class<T> concreteClass = (Class<T>)readerMap.get(type);

        if (concreteClass == null) {
            throw new UnsupportedOperationException("No implementation for requested interface found: " + type.getName());
        }

        LOG.debug("got class: {}", concreteClass);
        try {
            Constructor<T> constructor = concreteClass.getConstructor(String.class, Integer.class,
                    OauthToken.class, RestClient.class, Integer.TYPE, Integer.TYPE, Integer.class, Boolean.class);
            return constructor.newInstance(canvasBaseUrl, CANVAS_API_VERSION, oauthToken, restClient,
                    connectTimeout, readTimeout, paginationPageSize, false);
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
        return getWriter(type, oauthToken, false);
    }

    /**
     * Get a writer implementation to push data into Canvas while being able to control the behavior of blank values.
     * If the serializeNulls parameter is set to true, this writer will serialize null fields in the JSON being
     * sent to Canvas. This is required if you want to explicitly blank out a value that is currently set to something.
     * @param type Interface type you wish to get an implementation for
     * @param oauthToken An OAuth token to use for authentication when making API calls
     * @param serializeNulls Whether or not to include null fields in the serialized JSON. Defaults to false if null
     * @param <T> A writer implementation
     * @return An instantiated instance of the requested writer type
     */
    public <T extends CanvasWriter> T getWriter(Class<T> type, OauthToken oauthToken, Boolean serializeNulls) {
        LOG.debug("Factory call to instantiate writer class: {}", type.getName());
        RestClient restClient = new RefreshingRestClient();

        @SuppressWarnings("unchecked")
        Class<T> concreteClass = (Class<T>) writerMap.get(type);

        if (concreteClass == null) {
            throw new UnsupportedOperationException("No implementation for requested interface found: " + type.getName());
        }

        LOG.debug("got writer class: {}", concreteClass);
        try {
            Constructor<T> constructor = concreteClass.getConstructor(String.class, Integer.class, OauthToken.class,
                    RestClient.class, Integer.TYPE, Integer.TYPE, Integer.class, Boolean.class);
            return constructor.newInstance(canvasBaseUrl, CANVAS_API_VERSION, oauthToken, restClient,
                    connectTimeout, readTimeout, null, serializeNulls);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new UnsupportedOperationException("Unknown error instantiating the concrete API class: " + type.getName(), e);
        }
    }

    private void setupClassMap() throws IOException {
        Set<Class<?>> implClasses = ClassPath.from(this.getClass().getClassLoader())
                .getTopLevelClasses(IMPLEMENTATION_PACKAGE)
                .stream()
                .map(ClassPath.ClassInfo::load)
                .collect(Collectors.toSet());

        for (Class<?> implClass : implClasses) {
            Class<?>[] interfaces = implClass.getInterfaces();

            for (Class<?> interfaze : interfaces) {
                if (interfaze.isAnnotationPresent(AbstainRegister.class)) {
                    LOG.debug("Skipped registering: {}", implClass.getCanonicalName());
                    continue;
                }

                if (CanvasReader.class.isAssignableFrom(interfaze) && !interfaze.equals(CanvasReader.class)) {
                    readerMap.put((Class<? extends CanvasReader>) interfaze, (Class<? extends BaseImpl>) implClass);
                } else if (CanvasWriter.class.isAssignableFrom(interfaze) && !interfaze.equals(CanvasWriter.class)) {
                    writerMap.put((Class<? extends CanvasWriter>) interfaze, (Class<? extends BaseImpl>) implClass);
                }
            }
        }
    }

}
