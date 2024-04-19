package com.github.zethi.pruebatecnicaazurian.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCityRequest(@NotBlank(message = "Field 'name' can not be empty") String name,
                                    @NotNull(message = "Field 'province_id' can not be empty") Long province_id) {
}