package com.project.UrlShortenerProject.constants;

public interface CacheConstants {
    String URL_NOT_EMPTY = "Url cannot be empty or null";
    String URL_ERROR_MSG = "There was some problem in processing your request. Please try again!!";
    String URL_ERROR_STATUS = "404";
    String INVALID_SHORT_LINK_STATUS = "400";
    String INVALID_SHORT_LINK_MSG = "Invalid URL. Please provide correct url!!";
    String URL_DOES_NOT_EXISTS_MSG = "Url does not exists in Database or it might have expired!";
    String URL_DOES_NOT_EXISTS_STATUS = "400";
    String URL_EXPIRED_MSG = "Url Expired. Please try generating a new url!!";
    String URL_EXPIRED_STATUS = "200";
}
