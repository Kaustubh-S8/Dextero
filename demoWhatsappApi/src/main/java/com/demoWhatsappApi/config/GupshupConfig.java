//package com.demoWhatsappApi.config;
//
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GupshupConfig {
//    
//    @Value("${gupshup.api.key}")
//    private String apiKey;
//    
//    @Value("${gupshup.api.url}")
//    private String apiUrl;
//    
//    @Value("${gupshup.app.name}")
//    private String appName;
//    
//    @Value("${gupshup.source.phone}")
//    private String sourcePhone;
//    
//    @Bean
//    public CloseableHttpClient httpClient() {
//        return HttpClientBuilder.create().build();
//    }
//    
//    // Getters for the properties
//    public String getApiKey() { return apiKey; }
//    public String getApiUrl() { return apiUrl; }
//    public String getAppName() { return appName; }
//    public String getSourcePhone() { return sourcePhone; }
//}