package edu.ksu.canvas.impl;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.ResponseParser;
import edu.ksu.canvas.net.Response;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GsonResponseParser implements ResponseParser {
    private static final Logger LOG = LoggerFactory.getLogger(GsonResponseParser.class);

    @Override
    public <T> List<T> parseToList(Type type, Response response) {
        Gson gson = getDefaultGsonParser(false);
        return gson.fromJson(response.getContent(), type);
    }

    @Override
    public <T> Optional<T> parseToObject(Class<T> clazz, Response response) {
        Gson gson = getDefaultGsonParser(false);
        return Optional.of(gson.fromJson(response.getContent(), clazz));
    }

    @Override
    public <T> Map<String, T> parseToMap(Class<T> clazz, Response response) {
        Gson gson = getDefaultGsonParser(false);
        Type mapType = new TypeToken<Map<String,T>>(){}.getType();
        return gson.fromJson(response.getContent(), mapType);
    }

    public static Gson getDefaultGsonParser(Boolean serializeNulls) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        if(BooleanUtils.isTrue(serializeNulls)) {
            gsonBuilder.serializeNulls();
        }
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
        }).registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                if(src == null) {
                    return null;
                }
                String dateString = ZonedDateTime.ofInstant(src.toInstant(), ZoneOffset.UTC)
                        .format(DateTimeFormatter.ISO_INSTANT);
                return new JsonPrimitive(dateString);
            }
        }).registerTypeAdapter(Instant.class, new JsonDeserializer<Instant>() {
            // This doesn't support the format showing in the Canvas API documentation of:
            // 2012-07-19T15:00:00-06:00, however when you create a date using this format
            // Canvas sends you back the response in the UTC so we don't need to parse it.
            @Override
            public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if(json == null || StringUtils.isBlank(json.getAsString())) {
                    return null;
                }
                try {

                    return Instant.from(DateTimeFormatter.ISO_INSTANT.parse(json.getAsString()));
                } catch (DateTimeParseException e) {
                    throw new JsonParseException(e);
                }
            }
        }).registerTypeAdapter(Instant.class, new JsonSerializer<Instant>() {
            @Override
            public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
                if(src == null) {
                    return null;
                }
                String dateString = ZonedDateTime.ofInstant(src, ZoneOffset.UTC)
                        .format(DateTimeFormatter.ISO_INSTANT);
                return new JsonPrimitive(dateString);
            }
        }).registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if(json == null || StringUtils.isBlank(json.getAsString())) {
                    return null;
                }
                try {
                    return LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(json.getAsString()));
                } catch (DateTimeParseException e) {
                    throw new JsonParseException(e);
                }
            }
        });
        return gsonBuilder.create();
    }

}
