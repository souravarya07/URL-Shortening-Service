package com.project.UrlShortenerProject.constants;

public interface RestURIConstants {
    String BASE_URI = "/api/v1";
    String APPLICATION_JSON = "application/json";
    String GENERATE_SHORT_LINK = "/generate";
    String REDIRECT_TO_ORIGINAL = "/{shortLink}";
}
