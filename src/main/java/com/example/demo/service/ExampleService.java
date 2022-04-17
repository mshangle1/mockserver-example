package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExampleService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.test-api.url}")
    private String url;

    public ResponseEntity<String> callApi() {
        return restTemplate.getForEntity(url, String.class);
    }

}
