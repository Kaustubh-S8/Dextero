package com.Oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

	@GetMapping("/public/hello")
	public String PublicApi() {
		return "Hello, it is a public api";
	}
	
	@GetMapping("/secured/hello")
	public String SecuredApi() {
		return "hello, it is a Secured api" ;
	}
	
	
	
	
}
