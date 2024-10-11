package ru.vspochernin.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import ru.vspochernin.model.WeatherInput;

public class YandexWeatherApiClient implements Closeable {

    private static final String URL_FORMAT = "https://api.weather.yandex.ru/v2/forecast?lat=%s&lon=%s&limit=%s";
    private static final String KEY_HEADER = "X-Yandex-Weather-Key";

    private final String key;
    private final HttpClient httpClient;

    public YandexWeatherApiClient(String key) {
        this.key = key;
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getWeatherJson(WeatherInput weatherInput) {
        HttpRequest httpRequest = buildHttpRequest(URL_FORMAT.formatted(
                weatherInput.lat(),
                weatherInput.lon(),
                weatherInput.limit()));
        return getHttpResponseBody(httpRequest);
    }

    private HttpRequest buildHttpRequest(String uri) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .header(KEY_HEADER, key)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Incorrect URI: %s", uri), e);
        }
    }

    private String getHttpResponseBody(HttpRequest httpRequest) {
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            validateHttpResponse(httpResponse);
            return httpResponse.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error getting http response", e);
        }
    }

    private static void validateHttpResponse(HttpResponse<String> httpResponse) {
        if (httpResponse.statusCode() != 200) {
            throw new RuntimeException(String.format(
                    "Unexpected response code: %s, body: %s",
                    httpResponse.statusCode(),
                    httpResponse.body()));
        }
    }

    @Override
    public void close() {
        httpClient.close();
    }
}
