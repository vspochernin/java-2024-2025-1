package ru.vspochernin.model;

import java.util.List;

public record Weather(
        Fact fact,
        List<Forecast> forecasts
)
{
}
