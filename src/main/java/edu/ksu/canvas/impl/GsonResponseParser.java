package edu.ksu.canvas.impl;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.ResponseParser;
import edu.ksu.canvas.net.Response;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GsonResponseParser implements ResponseParser {

    @Override
    public <T> List<T> parseToList(Class<T> clazz, Response response) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Type listType = new TypeToken<List<T>>(){}.getType();
        return gson.fromJson(response.getContent(), listType);
    }

    @Override
    public <T> Optional<T> parseToObject(Class<T> clazz, Response response) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return Optional.of(gson.fromJson(response.getContent(), clazz));
    }

    @Override
    public <T> Map<String, T> parseToMap(Class<T> clazz, Response response) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Type mapType = new TypeToken<Map<String,T>>(){}.getType();
        return gson.fromJson(response.getContent(), mapType);
    }


}
