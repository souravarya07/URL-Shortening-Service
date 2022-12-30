package com.project.UrlShortenerProject.model;

import com.project.UrlShortenerProject.constants.CacheConstants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UrlDto {
    @NotEmpty(message = CacheConstants.URL_NOT_EMPTY)
    private String url;

    private String expirationDate;
}
