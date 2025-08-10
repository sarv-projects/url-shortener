package com.sarvesh.urlshortener;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public class DataSourceLogger {
    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    public void log() {
        System.out.println("Datasource user: " + username);
        System.out.println("Datasource password: " + password);
    }
}
