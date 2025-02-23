package com.example.springboot.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(

    @NotBlank(message = "Nome não deve estar em branco")
    String name,

    @NotNull(message = "Nome não deve ser nulo")
    BigDecimal value

    ) {
}
