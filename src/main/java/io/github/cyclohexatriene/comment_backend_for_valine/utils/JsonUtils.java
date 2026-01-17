package io.github.cyclohexatriene.comment_backend_for_valine.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

public class JsonUtils {
    private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();
    private static final ObjectMapper CAMEL_MAPPER = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);


    public static <T> String toJson(T obj) {
        try {
            return DEFAULT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    public static <T> String toCamelJson(T obj) {
        try {
            return CAMEL_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to camel case JSON", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return DEFAULT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON to object: " + clazz.getName(), e);
        }
    }


    public static <T> T fromCamelJson(String json, Class<T> clazz) {
        try {
            return CAMEL_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse camel case JSON to object: " + clazz.getName(), e);
        }
    }


    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return DEFAULT_MAPPER.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON to type reference", e);
        }
    }

    public static <T> T fromCamelJson(String json, TypeReference<T> typeReference) {
        try {
            return CAMEL_MAPPER.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse camel case JSON to type reference", e);
        }
    }
}