package com.sarvesh.urlshortener.repository;

import com.sarvesh.urlshortener.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByShortcode(String shortcode);
}
/*
This interface allows CRUD operations on  UrlMapping table, plus a finder by shortCode.
UrlMappingRepository extends JpaRepository, which gives you ready-made CRUD methods like save(), findById(), findAll(), delete(),
You specify the entity as UrlMapping and the ID type as Long.

You add a custom finder method:


Optional<UrlMapping> findByShortCode(String shortCode);
This lets you query the DB for a URL mapping using the short code (the shortened URL key).

The return type is Optional<UrlMapping> to handle the case where the short code might not exist in the DB.

Why is this smart?
Spring Data JPA automatically implements this method based on its name pattern findBy<Property>() — you don’t write SQL or JPQL.

This method will be critical for your URL shortener’s redirect feature: look up the original URL using the short code.

Example usage in service:

Optional<UrlMapping> mapping = urlMappingRepository.findByShortCode("abc123");
if (mapping.isPresent()) {
   String originalUrl = mapping.get().getOriginalUrl();
redirect user to originalUrl
} else {
handle not found, show 404
}
In short: This repo interface is your database access layer for URL entries, with built-in and custom methods.
*/