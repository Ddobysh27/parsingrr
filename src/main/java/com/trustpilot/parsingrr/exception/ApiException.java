package com.trustpilot.parsingrr.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException {
    private int code;
    private String description;
}
