package com.project.UrlShortenerProject.model.exception;

import lombok.Getter;

@Getter
public enum UrlServiceExceptionCode {

    URL_INVALID_ERROR ("ERR-101", "Invalid URL, Please check!!");

    private final String errorCode;
    private final String errorMsg;

    UrlServiceExceptionCode (String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
}
