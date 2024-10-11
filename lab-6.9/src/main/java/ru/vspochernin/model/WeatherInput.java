package ru.vspochernin.model;

import java.util.Locale;
import java.util.Scanner;

public record WeatherInput(double lat, double lon, int limit) {

    public static WeatherInput getHardcoded() {
        WeatherInput weatherInput = new WeatherInput(55.75, 37.62, 5);
        weatherInput.validate();
        return weatherInput;
    }

    public static WeatherInput readFromKeyBoard() {
        Scanner scanner = new Scanner(System.in)
                .useLocale(Locale.US); // For '.' as double delimiter.

        System.out.print("Enter latitude in degrees: ");
        double lat = scanner.nextDouble();

        System.out.print("Enter longitude in degrees: ");
        double lon = scanner.nextDouble();

        System.out.print("Enter limit in degrees: ");
        int limit = scanner.nextInt();

        WeatherInput weatherInput = new WeatherInput(lat, lon, limit);
        weatherInput.validate();

        return weatherInput;
    }

    private void validate() {
        if (lat() < -90 || lat() > 90) {
            throw new IllegalArgumentException("lat must be between -90 and 90 degrees");
        }
        if (lon() < -180 || lon() > 180) {
            throw new IllegalArgumentException("lon must be between -180 and 180 degrees");
        }
        // Free api limits the limit to 7.
        if (limit() < 1 || limit() > 7) {
            throw new IllegalArgumentException("limit must be between 0 and 7");
        }
    }
}
