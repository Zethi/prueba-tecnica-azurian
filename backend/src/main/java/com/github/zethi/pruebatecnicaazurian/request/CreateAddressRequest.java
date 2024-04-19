package com.github.zethi.pruebatecnicaazurian.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAddressRequest(@NotNull(message = "Field 'city_id' can not be empty") Long city_id,
                                   @NotBlank(message = "Field 'streetName' can not be empty") String streetName,
                                   @NotNull(message = "Field 'number' can not be empty") Integer number) {
}
