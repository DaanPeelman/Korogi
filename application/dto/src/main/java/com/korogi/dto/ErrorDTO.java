package com.korogi.dto;

import static lombok.AccessLevel.PUBLIC;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = PUBLIC)
@NoArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newErrorDTO")
public class ErrorDTO {
    private String status;
    private int code;
    private String message;
}