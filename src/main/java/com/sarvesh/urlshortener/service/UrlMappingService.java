package com.sarvesh.urlshortener.service;

import com.sarvesh.urlshortener.entity.UrlMapping;
import com.sarvesh.urlshortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlMappingService {

    public UrlMappingRepository urlMappingRepository;
    @Autowired
    public UrlMappingService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }
    private static final String APLHANUM="abcdefghijklmnopqrstuvwxzzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private final Random random = new Random();
    private static final int SHORT_CODE_LENGTH=6;
    private String generateShortCode(){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<SHORT_CODE_LENGTH;i++){
            sb.append(APLHANUM.charAt(random.nextInt(APLHANUM.length())));
        }
        return sb.toString();
    }
    public UrlMapping createShortUrl(String originalUrl){
        String shortcode;
        do{
            shortcode=generateShortCode();
        }while(urlMappingRepository.findByShortcode(shortcode).isPresent());
         UrlMapping urlMapping=new UrlMapping(shortcode,originalUrl);
         return urlMappingRepository.save(urlMapping);
    }
    public Optional<UrlMapping> getUrlMappingByShortcode(String shortcode){
        return urlMappingRepository.findByShortcode(shortcode);
    }
    public void incrementVisitCount(UrlMapping urlMapping){
        urlMapping.setVisitCount(urlMapping.getVisitCount()+1);
        urlMappingRepository.save(urlMapping);
    }

}
