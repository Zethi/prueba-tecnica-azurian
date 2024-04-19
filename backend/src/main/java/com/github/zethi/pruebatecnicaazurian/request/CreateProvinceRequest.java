package com.github.zethi.pruebatecnicaazurian.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProvinceRequest(@NotBlank(message = "Field 'name' can not be empty") String name,
                                    @NotNull(message = "Field 'region_id' can not be empty") Long region_id) {
}