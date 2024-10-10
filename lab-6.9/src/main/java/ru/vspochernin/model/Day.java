package ru.vspochernin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Day(@JsonProperty("temp_avg") int temp) {
}
