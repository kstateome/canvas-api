package edu.ksu.canvas.interfaces;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.ksu.canvas.net.Response;

public interface ResponseParser {
    <T> List<T> parseToList(Type type, Response response);
    <T> Optional<T> parseToObject(Class<T> clazz, Response response);
    <T> Map<String, T> parseToMap(Class<T> clazz, Response response);
}
