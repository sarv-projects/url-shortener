package com.sarvesh.urlshortener.entity;

import jakarta.persistence.*;

@Entity
@Table(name="url_mapping")
public class UrlMapping {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String shortcode;
    @Column(nullable = false,length=2048)
    private String originalUrl;
    @Column(nullable = false)
    private Long visitCount;
    public UrlMapping() {}
    public UrlMapping(String shortcode, String originalUrl) {
        this.shortcode = shortcode;
        this.originalUrl = originalUrl;
        this.visitCount=0L;
    }
public Long getId() {
        return id;
}
    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
}
