package com.project.UrlShortenerProject.service;

import com.google.common.hash.Hashing;
import com.project.UrlShortenerProject.model.Url;
import com.project.UrlShortenerProject.model.UrlDto;
import com.project.UrlShortenerProject.model.UrlRequest;
import com.project.UrlShortenerProject.model.UrlResponseDto;
import com.project.UrlShortenerProject.repository.UrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;

@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortLink(UrlDto urlDto) {
        String encodedUrl = encodeUrl(urlDto.getUrl());
        Url urlToPersist = new Url();
        urlToPersist.setOriginalUrl(urlDto.getUrl());
        urlToPersist.setShortLink(encodedUrl);
        urlToPersist.setCreationDate(LocalDateTime.now());
        urlToPersist.setExpirationDate(getExpirationDate(urlDto.getExpirationDate(), urlToPersist.getCreationDate()));
        Url urlToRet = persistShortLink(urlToPersist);
        return urlToRet;
    }

    private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
        if(StringUtils.isBlank(expirationDate)) {
            return creationDate.plusSeconds(120);
        }
        LocalDateTime expirationDateToReturn = LocalDateTime.parse(expirationDate);
        return expirationDateToReturn;
    }

    private String encodeUrl(String url) {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return encodedUrl;
    }

    @Override
    public Url persistShortLink(Url url) {
        Url urlToRet = urlRepository.save(url);
        return urlToRet;
    }

    @Override
    public Url getEncodedUrl(String url) {
        Url urlToRet = urlRepository.findByShortLink(url);
        return urlToRet;
    }

    @Override
    public void deleteShortLink(Url url) {
        urlRepository.delete(url);
    }

    /*
     * Making use of threading and caching for this approach
     */
    @Override
    public void shortenUrlV2(@Valid UrlRequest urlrequest) {
        
    }
}
