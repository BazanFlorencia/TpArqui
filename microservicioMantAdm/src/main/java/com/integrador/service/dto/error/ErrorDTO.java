package com.integrador.service.dto.error;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ErrorDTO {

    private final String message;
    private final LocalDate date = LocalDate.now();
}
