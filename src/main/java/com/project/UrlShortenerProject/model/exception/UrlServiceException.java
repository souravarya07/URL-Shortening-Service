package com.project.UrlShortenerProject.model.exception;

import lombok.Getter;

@Getter
public class UrlServiceException extends RuntimeException {

    private final UrlServiceExceptionCode exceptionCode;

    public UrlServiceException(UrlServiceExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
    
}
