package com.sarvesh.urlshortener.controller;

import com.sarvesh.urlshortener.entity.UrlMapping;
import com.sarvesh.urlshortener.service.UrlMappingService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class UrlShortenerController {
    private UrlMappingService urlMappingService;
    @Autowired
    UrlShortenerController(UrlMappingService urlMappingService) {
        this.urlMappingService = urlMappingService;
    }
    // API to create short URL
    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody Map<String,String> body ) {
        String originalUrl = body.get("url");
        if(originalUrl == null || originalUrl.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Url is unavailable.Url is required.");
        }
        UrlMapping mapping=urlMappingService.createShortUrl(originalUrl);
        String shortUrl="http://localhost:8080/"+mapping.getShortcode();
        return ResponseEntity.ok(Map.of("shorturl",shortUrl));
    }
    // Redirect endpoint
    @GetMapping("/{shortcode}")
    public void redirectToOriginal(@PathVariable String shortcode, HttpServletResponse response)throws IOException {
        var urlMappingOptional=urlMappingService.getUrlMappingByShortcode(shortcode);
        if(urlMappingOptional.isPresent())
        {
            UrlMapping mapping=urlMappingOptional.get();
            urlMappingService.incrementVisitCount(mapping);
            response.sendRedirect(mapping.getOriginalUrl());
        }else{
            response.sendError(404,"Short Url not found");
        }
    }

}

