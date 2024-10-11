package ru.vspochernin;

import ru.vspochernin.client.YandexWeatherApiClient;
import ru.vspochernin.model.Day;
import ru.vspochernin.model.Forecast;
import ru.vspochernin.model.Parts;
import ru.vspochernin.model.Weather;
import ru.vspochernin.model.WeatherInput;
import ru.vspochernin.utils.KeyReadingUtils;
import ru.vspochernin.utils.WeatherParsingUtils;

public class Main {

    // Key file that stores the secret key from https://yandex.ru/pogoda/b2b/console/api-page.
    private static final String KEY_FILE_NAME = "key.txt";

    public static void main(String[] args) {
        WeatherInput weatherInput = WeatherInput.getHardcoded();
        String weatherJson = getWeatherJson(weatherInput);
        Weather weather = WeatherParsingUtils.parseWeather(weatherJson);

        int limit = weatherInput.limit();
        int temp = weather.fact().temp();
        double average = weather.forecasts().stream()
                .map(Forecast::parts)
                .map(Parts::day)
                .map(Day::temp)
                .mapToInt(Integer::valueOf)
                .average()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("There is no forecasts for weather with limit %s: ", limit)));

        System.out.printf("Weather json string: %s\n", weatherJson);
        System.out.printf("Current temperature: %s degrees Celsius\n", temp);
        System.out.printf("Average temperature for limit %s is %.2f degrees Celsius\n", limit, average);
    }

    private static String getWeatherJson(WeatherInput weatherInput) {
        String key = KeyReadingUtils.readKeyFromFile(KEY_FILE_NAME);
        try (YandexWeatherApiClient apiClient = new YandexWeatherApiClient(key)) {
            return apiClient.getWeatherJson(weatherInput);
        }
    }
}