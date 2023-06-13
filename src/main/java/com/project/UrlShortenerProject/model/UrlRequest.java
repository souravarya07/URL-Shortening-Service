package com.project.UrlShortenerProject.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UrlRequest {

    private Integer expiry;

    @NotEmpty(message = "INVALID_URL_ERROR")
    private String url;

}
