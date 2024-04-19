package com.github.zethi.pruebatecnicaazurian.request;

import com.github.zethi.pruebatecnicaazurian.constant.CivilStatus;
import com.github.zethi.pruebatecnicaazurian.constant.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CreatePersonRequest(
        @NotBlank(message = "Field 'firstName' can not be empty") String firstName,
        @NotBlank(message = "Field 'lastName' can not be empty") String lastName,
        @Valid @NotBlank(message = "Field 'rut' can not be empty") String rut,
        Gender gender,
        CivilStatus civilStatus,
        CreateAddressRequest address
) {
}