package com.project.UrlShortenerProject.Controller;

import com.project.UrlShortenerProject.Utility.UrlValidator;
import com.project.UrlShortenerProject.constants.CacheConstants;
import com.project.UrlShortenerProject.constants.RestURIConstants;
import com.project.UrlShortenerProject.model.Url;
import com.project.UrlShortenerProject.model.UrlDto;
import com.project.UrlShortenerProject.model.UrlErrorResponseDto;
import com.project.UrlShortenerProject.model.UrlRequest;
import com.project.UrlShortenerProject.model.UrlResponseDto;
import com.project.UrlShortenerProject.model.exception.UrlServiceException;
import com.project.UrlShortenerProject.model.exception.UrlServiceExceptionCode;
import com.project.UrlShortenerProject.service.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping(RestURIConstants.BASE_URI)
public class UrlShortnerController {

    @Autowired
    private UrlService urlService;


    @RequestMapping(value=RestURIConstants.GENERATE_SHORT_LINK, method = RequestMethod.POST, produces = RestURIConstants.APPLICATION_JSON)
    public ResponseEntity<?> generateShortLink(@RequestBody UrlDto urlDto) {
        Url urlToRet = urlService.generateShortLink(urlDto);
        if(urlToRet != null) {
            UrlResponseDto urlResponseDto = new UrlResponseDto();
            urlResponseDto.setOriginalUrl(urlDto.getUrl());
            urlResponseDto.setExpirationDate(urlToRet.getExpirationDate());
            urlResponseDto.setShortLink(urlToRet.getShortLink());
            return new ResponseEntity<UrlResponseDto>(urlResponseDto,HttpStatus.OK);
        }

        UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
        urlErrorResponseDto.setError(CacheConstants.URL_ERROR_MSG);
        urlErrorResponseDto.setStatus(CacheConstants.URL_ERROR_STATUS);
        return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value=RestURIConstants.REDIRECT_TO_ORIGINAL, method = RequestMethod.GET, produces = RestURIConstants.APPLICATION_JSON)
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
        if(StringUtils.isEmpty(shortLink)) {
            UrlErrorResponseDto errorResponseDto = new UrlErrorResponseDto();
            errorResponseDto.setError(CacheConstants.INVALID_SHORT_LINK_MSG);
            errorResponseDto.setStatus(CacheConstants.INVALID_SHORT_LINK_STATUS);
            return new ResponseEntity<UrlErrorResponseDto>(errorResponseDto, HttpStatus.OK);
        }
        Url urlToRet = urlService.getEncodedUrl(shortLink);
        if(urlToRet == null) {
            UrlErrorResponseDto errorResponseDto = new UrlErrorResponseDto();
            errorResponseDto.setError(CacheConstants.URL_DOES_NOT_EXISTS_MSG);
            errorResponseDto.setStatus(CacheConstants.URL_DOES_NOT_EXISTS_STATUS);
            return new ResponseEntity<UrlErrorResponseDto>(errorResponseDto, HttpStatus.OK);
        }

        if(urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
            urlService.deleteShortLink(urlToRet);
            UrlErrorResponseDto errorResponseDto = new UrlErrorResponseDto();
            errorResponseDto.setError(CacheConstants.URL_EXPIRED_MSG);
            errorResponseDto.setStatus(CacheConstants.URL_EXPIRED_STATUS);
            return new ResponseEntity<UrlErrorResponseDto>(errorResponseDto, HttpStatus.OK);
        }
        response.sendRedirect(urlToRet.getOriginalUrl());
        return null;
    }

    
    @PostMapping(value = RestURIConstants.GENERATE_SHORT_LINK, consumes = "application/json")
    public String shortenUrl(@RequestBody @Valid UrlRequest urlrequest) {
        String longUrl = urlrequest.getUrl();
        if (UrlValidator.validateUrl(longUrl)) {
            urlService.shortenUrlV2(urlrequest);
        }
        throw new UrlServiceException(UrlServiceExceptionCode.URL_INVALID_ERROR);

    }
}
