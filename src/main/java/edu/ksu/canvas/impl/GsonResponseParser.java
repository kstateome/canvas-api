package edu.ksu.canvas.impl;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.ResponseParser;
import edu.ksu.canvas.net.Response;

import java.lang.reflect.Type;
import java.util.Date;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class GsonResponseParser implements ResponseParser {
    private static final Logger LOG = Logger.getLogger(GsonResponseParser.class);

    @Override
    public <T> List<T> parseToList(Type type, Response response) {
        Gson gson = getDefaultGsonParser();
        return gson.fromJson(response.getContent(), type);
    }

    @Override
    public <T> Optional<T> parseToObject(Class<T> clazz, Response response) {
        Gson gson = getDefaultGsonParser();
        return Optional.of(gson.fromJson(response.getContent(), clazz));
    }

    @Override
    public <T> Map<String, T> parseToMap(Class<T> clazz, Response response) {
        Gson gson = getDefaultGsonParser();
        Type mapType = new TypeToken<Map<String,T>>(){}.getType();
        return gson.fromJson(response.getContent(), mapType);
    }

    public static Gson getDefaultGsonParser() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        //Custom type adapter for Date because: GSON throws a parse exception for blank dates instead of returning null.
        //Also, it doesn't handle ISO 8601 dates with time zone info. Dates are hard.
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
                if(json == null || StringUtils.isBlank(json.getAsString())) {
                    return null;
                }
                try {
                    ZonedDateTime zdt = ZonedDateTime.parse(json.getAsString());
                    return Date.from(zdt.toInstant());
                } catch(DateTimeParseException e) {
                    LOG.error("error parsing date from Canvas: " + json.getAsString());
                    throw new JsonParseException(e);
                }
            }
        });
        return gsonBuilder.create();
    }

}
