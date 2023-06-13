package com.project.UrlShortenerProject.Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UrlValidator {

    private static final String URL_REGEX = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public static boolean validateUrl(String url) {
        Matcher matcher = URL_PATTERN.matcher(url);
        return matcher.matches();
    }
    
}
