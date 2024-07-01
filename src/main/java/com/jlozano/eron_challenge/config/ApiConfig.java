package com.jlozano.eron_challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfig {
    @Value("${api.base.url}")
    private String apiBaseUrl;

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
