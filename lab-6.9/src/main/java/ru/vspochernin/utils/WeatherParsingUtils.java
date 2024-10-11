package ru.vspochernin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.vspochernin.model.Weather;

/**
 * @author pochernin-vla
 */
public class WeatherParsingUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static Weather parseWeather(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, Weather.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("Error parsing json %s to Weather object", json), e);
        }
    }
}
