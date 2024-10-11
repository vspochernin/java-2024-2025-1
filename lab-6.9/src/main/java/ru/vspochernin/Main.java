package ru.vspochernin;

import ru.vspochernin.client.YandexWeatherApiClient;
import ru.vspochernin.model.Day;
import ru.vspochernin.model.Forecast;
import ru.vspochernin.model.Parts;
import ru.vspochernin.model.Weather;
import ru.vspochernin.utils.KeyReadingUtils;
import ru.vspochernin.utils.WeatherParsingUtils;

public class Main {

    // Key file that stores the secret key from https://yandex.ru/pogoda/b2b/console/api-page.
    private static final String KEY_FILE_NAME = "key.txt";

    public static void main(String[] args) {
        double lat = 55.75;
        double lon = 37.62;
        int limit = 5;

        String weatherJson = getWeatherJson(lat, lon, limit);
        Weather weather = WeatherParsingUtils.parseWeather(weatherJson);

        int temp = weather.fact().temp();
        double average = weather.forecasts().stream()
                .map(Forecast::parts)
                .map(Parts::day)
                .map(Day::temp)
                .mapToInt(Integer::valueOf)
                .average()
                .orElseThrow(() -> new IllegalStateException("There is no forecasts for weather with limit"));

        System.out.println("Weather json string: " + weatherJson);
        System.out.println("Temperature (degrees Celsius): " + temp);
        System.out.printf("Average temperature for limit %s is %s degrees Celsius\n", limit, average);
    }

    private static String getWeatherJson(double lat, double lon, int limit) {
        String key = KeyReadingUtils.readKeyFromFile(KEY_FILE_NAME);
        try (YandexWeatherApiClient apiClient = new YandexWeatherApiClient(key)) {
            return apiClient.getWeatherJsonString(lat, lon, limit);
        }
    }
}