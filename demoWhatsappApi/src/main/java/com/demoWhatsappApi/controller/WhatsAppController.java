package com.demoWhatsappApi.controller;














import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demoWhatsappApi.service.WhatsAppService;

import java.util.List;

@RestController
@RequestMapping("/whatsapp")
public class WhatsAppController {

    @Autowired
    private WhatsAppService whatsAppService;

    // Send Simple Text Message
    @PostMapping("/send-text")
    public ResponseEntity<String> sendText(@RequestParam String phoneNumber, @RequestParam String message) {
        return whatsAppService.sendTextMessage(phoneNumber, message);
    }

    // Send Template Message
    @PostMapping("/send-template")
    public ResponseEntity<String> sendTemplate(@RequestParam String phoneNumber,
                                               @RequestParam String templateName,
                                               @RequestParam String languageCode,
                                               @RequestParam List<String> templateParams) {
        return whatsAppService.sendTemplateMessage(phoneNumber, templateName, languageCode, templateParams);
    }
}






//
//import java.io.IOException;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.demoWhatsappApi.service.WhatsAppService;
//
//@RestController
//@RequestMapping("/api/whatsapp")
//public class WhatsAppController {
//    
//    private final WhatsAppService whatsAppService;
//    
//    @Autowired
//    public WhatsAppController(WhatsAppService whatsAppService) {
//        this.whatsAppService = whatsAppService;
//    }
//    
//    @PostMapping("/send-freeform")
//    public ResponseEntity<String> sendFreeFormMessage(
//            @RequestParam String phone,
//            @RequestParam String message) {
//        try {
//            String response = whatsAppService.sendFreeFormMessage(phone, message);
//            return ResponseEntity.ok(response);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error sending message: " + e.getMessage());
//        }
//    }
//    
//    @PostMapping("/send-template")
//    public ResponseEntity<String> sendTemplateMessage(
//            @RequestParam String phone,
//            @RequestParam String templateName,
//            @RequestBody Map<String, String> parameters) {
//        try {
//            String response = whatsAppService.sendTemplateMessage(phone, templateName, parameters);
//            return ResponseEntity.ok(response);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error sending template: " + e.getMessage());
//        }
//    }
//}