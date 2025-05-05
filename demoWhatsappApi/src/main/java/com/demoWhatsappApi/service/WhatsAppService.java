package com.demoWhatsappApi.service;







import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsAppService {

    @Value("${gupshup.api.key}")
    private String apiKey;

    @Value("${gupshup.source.number}")
    private String sourceNumber;

    @Value("${gupshup.app.name}")
    private String appName;

    
    private final RestTemplate restTemplate = new RestTemplate();

    // Send Simple Text Message
    public ResponseEntity<String> sendTextMessage(String phoneNumber, String message) {
        String url = "https://api.gupshup.io/sm/api/v1/msg";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("channel", "whatsapp");
        params.add("source", sourceNumber);
        params.add("destination", phoneNumber);
        params.add("message", message);
        params.add("src.name", appName);

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        return restTemplate.postForEntity(url, request, String.class);
    }
    

    // Send Template Message
    public ResponseEntity<String> sendTemplateMessage(String phoneNumber, String templateName, String languageCode, List<String> templateParams) {
        String url = "https://api.gupshup.io/sm/api/v1/template/msg";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("channel", "whatsapp");
        params.add("source", sourceNumber);
        params.add("destination", phoneNumber);
        params.add("src.name", appName);
        params.add("template", templateName);
        params.add("languageCode", languageCode);
        params.add("templateParams", String.join(",", templateParams));

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        return restTemplate.postForEntity(url, request, String.class);
    }
}



























//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.demoWhatsappApi.config.GupshupConfig;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Service
//public class WhatsAppService {
//    
//    private final GupshupConfig gupshupConfig;
//    private final CloseableHttpClient httpClient;
//    private final ObjectMapper objectMapper;
//    
//    @Autowired
//    public WhatsAppService(GupshupConfig gupshupConfig, 
//                          CloseableHttpClient httpClient,
//                          ObjectMapper objectMapper) {
//        this.gupshupConfig = gupshupConfig;
//        this.httpClient = httpClient;
//        this.objectMapper = objectMapper;
//    }
//    
//    // Send free-form (non-template) message
//    public String sendFreeFormMessage(String destination, String text) throws IOException {
//        String url = gupshupConfig.getApiUrl() + "/msg";
//        
//        HttpPost post = new HttpPost(url);
//        post.setHeader("apikey", gupshupConfig.getApiKey());
//        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
//        
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("channel", "whatsapp"));
//        params.add(new BasicNameValuePair("source", gupshupConfig.getSourcePhone()));
//        params.add(new BasicNameValuePair("destination", destination));
//        params.add(new BasicNameValuePair("src.name", gupshupConfig.getAppName()));
//        params.add(new BasicNameValuePair("message", text));
//        
//        post.setEntity(new UrlEncodedFormEntity(params));
//        
//        try (CloseableHttpResponse response = httpClient.execute(post)) {
//            return EntityUtils.toString(response.getEntity());
//        }
//    }
//    
//    // Send template message
//    public String sendTemplateMessage(String destination, String templateName, 
//                                    Map<String, String> parameters) throws IOException {
//        String url = gupshupConfig.getApiUrl() + "/template/msg";
//        
//        HttpPost post = new HttpPost(url);
//        post.setHeader("apikey", gupshupConfig.getApiKey());
//        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
//        
//        // Convert parameters to JSON string
//        String paramsJson = objectMapper.writeValueAsString(parameters);
//        
//        List<NameValuePair> formParams = new ArrayList<>();
//        formParams.add(new BasicNameValuePair("channel", "whatsapp"));
//        formParams.add(new BasicNameValuePair("source", gupshupConfig.getSourcePhone()));
//        formParams.add(new BasicNameValuePair("destination", destination));
//        formParams.add(new BasicNameValuePair("template", templateName));
//        formParams.add(new BasicNameValuePair("src.name", gupshupConfig.getAppName()));
//        formParams.add(new BasicNameValuePair("namespace", "fc27d30f_a1e9_e84c_cd55_0de6ee10b598")); // Required for template
//        formParams.add(new BasicNameValuePair("params", paramsJson));
//        
//        post.setEntity(new UrlEncodedFormEntity(formParams));
//        
//        try (CloseableHttpResponse response = httpClient.execute(post)) {
//            return EntityUtils.toString(response.getEntity());
//        }
//    }
//}