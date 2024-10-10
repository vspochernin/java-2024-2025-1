package ru.vspochernin.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class YandexWeatherApiClient implements Closeable {

    public static final String URL_FORMAT = "https://api.weather.yandex.ru/v2/forecast?lat=%s&lon=%s";
    public static final String LIMIT_URL_FORMAT = "https://api.weather.yandex.ru/v2/forecast?lat=%s&lon=%s&limit=%s";
    public static final String KEY_HEADER = "X-Yandex-Weather-Key";

    private final String key;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public YandexWeatherApiClient(String key) {
        this.key = key;
    }

    public String getWeatherJsonString(double lat, double lon) {
        HttpRequest httpRequest = buildHttpRequest(URL_FORMAT.formatted(lat, lon));
        return getHttpResponseBody(httpRequest);
    }

    public String getWeatherJsonString(double lat, double lon, int limit) {
        HttpRequest httpRequest = buildHttpRequest(LIMIT_URL_FORMAT.formatted(lat, lon, limit));
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
            throw new RuntimeException("Incorrect URI: " + e.getMessage());
        }
    }

    private String getHttpResponseBody(HttpRequest httpRequest) {
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            validateHttpResponse(httpResponse);

            return httpResponse.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void validateHttpResponse(HttpResponse<String> httpResponse) {
        if (httpResponse.statusCode() != 200) {
            throw new RuntimeException("Unexpected response code: " + httpResponse.statusCode());
        }
    }

    @Override
    public void close() {
        httpClient.close();
    }
}
