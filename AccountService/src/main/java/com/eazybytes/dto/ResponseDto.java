package com.eazybytes.dto;

import lombok.*;
import org.springframework.http.HttpStatusCode;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseDto {
    private String httpStatusCode;
    private String message;
}
