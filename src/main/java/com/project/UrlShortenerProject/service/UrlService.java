package com.project.UrlShortenerProject.service;

import com.project.UrlShortenerProject.model.Url;
import com.project.UrlShortenerProject.model.UrlDto;
import org.springframework.stereotype.Service;

public interface UrlService {
    public Url generateShortLink(UrlDto urlDto);
    public Url persistShortLink(Url url);
    public Url getEncodedUrl(String url);
    public void deleteShortLink(Url url);
}
