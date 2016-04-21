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
    private String canvasBaseUrl;

    Map<Class<? extends CanvasBase>, Class<? extends BaseImpl>> instanceMap;

    public CanvasApiFactory(String canvasBaseUrl) {
        LOG.debug("Creating Canvas API factory with base URL: " + canvasBaseUrl);
        this.canvasBaseUrl = canvasBaseUrl;
        instanceMap = new HashMap<>();
        instanceMap.put(AccountsReader.class, AccountImpl.class);
        instanceMap.put(AccountsWriter.class, AccountImpl.class);
        instanceMap.put(AssignmentOverrideReader.class, AssignmentOverrideImp.class);
        instanceMap.put(AssignmentOverrideWriter.class, AssignmentOverrideImp.class);
        instanceMap.put(AssignmentReader.class, AssignmentsImpl.class);
        instanceMap.put(AssignmentWriter.class, AssignmentsImpl.class);
        instanceMap.put(CourseReader.class, CoursesImpl.class);
        instanceMap.put(CourseWriter.class, CoursesImpl.class);
        instanceMap.put(EnrollmentsReader.class, EnrollmentsImpl.class);
        instanceMap.put(EnrollmentsWriter.class, EnrollmentsImpl.class);
        instanceMap.put(QuizQuestionReader.class, QuizQuestionImpl.class);
        instanceMap.put(QuizQuestionWriter.class, QuizQuestionImpl.class);
        instanceMap.put(QuizReader.class, QuizImpl.class);
        instanceMap.put(QuizWriter.class, QuizImpl.class);
        instanceMap.put(QuizSubmissionQuestionReader.class, QuizSubmissionImpl.class);
        instanceMap.put(QuizSubmissionQuestionWriter.class, QuizSubmissionImpl.class);
        instanceMap.put(QuizSubmissionReader.class, QuizSubmissionImpl.class);
        instanceMap.put(QuizSubmissionWriter.class, QuizSubmissionImpl.class);
        instanceMap.put(SectionReader.class, SectionsImpl.class);
        instanceMap.put(UserReader.class, UserImpl.class);
        instanceMap.put(UserWriter.class, UserImpl.class);
    }

    public <T extends CanvasBase> T getApiClass(Class<T> type, String oauthToken) {
        LOG.debug("Factory call to instantiate class: " + type.getName());
        RestClient restClient = new RestClientImpl();

        @SuppressWarnings("unchecked")
        Class<T> concreteClass = (Class<T>)instanceMap.get(type);
        if(concreteClass == null) {
            throw new UnsupportedOperationException("No implementation for requested interface in instanceMap: " + type.getName());
        }

        System.out.println("got class: " + concreteClass);
        try {
            Constructor<T> constructor = concreteClass.getConstructor(String.class, Integer.class, String.class, RestClient.class);
            T concreteInstance = constructor.newInstance(canvasBaseUrl, CANVAS_API_VERSION, oauthToken, restClient);
            return concreteInstance;
        } catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            LOG.error("Unknown error instantiating the concrete API class: " + type.getName(), e);
            throw new UnsupportedOperationException();
        }
    }
}
