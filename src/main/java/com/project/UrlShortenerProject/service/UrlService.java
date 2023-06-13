package com.project.UrlShortenerProject.service;

import javax.validation.Valid;

import com.project.UrlShortenerProject.model.Url;
import com.project.UrlShortenerProject.model.UrlDto;
import com.project.UrlShortenerProject.model.UrlRequest;

public interface UrlService {
    public Url generateShortLink(UrlDto urlDto);
    public Url persistShortLink(Url url);
    public Url getEncodedUrl(String url);
    public void deleteShortLink(Url url);
    public void shortenUrlV2(@Valid UrlRequest urlrequest);
}
