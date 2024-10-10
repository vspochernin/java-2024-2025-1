package ru.vspochernin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.vspochernin.client.YandexWeatherApiClient;
import ru.vspochernin.model.Day;
import ru.vspochernin.model.Forecast;
import ru.vspochernin.model.Parts;
import ru.vspochernin.model.Weather;
import ru.vspochernin.utils.KeyReaderUtils;

public class Main {

    // Key file storing secret key from https://yandex.ru/pogoda/b2b/console/api-page.
    private static final String KEY_FILE_NAME = "key.txt";

    public static void main(String[] args) throws JsonProcessingException {
        String key = KeyReaderUtils.readKeyFromFile(KEY_FILE_NAME);

        double lat = 55.75;
        double lon = 37.62;
        int limit = 5;

        String weatherJsonString;
        String weatherWithLimitJsonString;
        try (YandexWeatherApiClient apiClient = new YandexWeatherApiClient(key)) {
            weatherJsonString = apiClient.getWeatherJsonString(lat, lon);
            weatherWithLimitJsonString = apiClient.getWeatherJsonString(lat, lon, limit);
        }

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Weather weather = objectMapper.readValue(weatherJsonString, Weather.class);
        int temp = weather.fact().temp();

        Weather weatherWithLimit = objectMapper.readValue(weatherWithLimitJsonString, Weather.class);
        double average = weatherWithLimit.forecasts().stream()
                .map(Forecast::parts)
                .map(Parts::day)
                .map(Day::temp)
                .mapToInt(Integer::valueOf)
                .average()
                .orElseThrow(() -> new IllegalStateException("There is no forecasts for weather with limit"));

        System.out.println("Weather json string: " + weatherJsonString);
        System.out.println("Temperature (degrees Celsius): " + temp);
        System.out.println("Weather with limit json string: " + weatherJsonString);
        System.out.printf("Average temperature for limit %s is %s degrees Celsius\n", limit, average);
    }
}