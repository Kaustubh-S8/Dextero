package com.Quiz.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class LoginUserController {

	@Autowired
	private LoginUserService loginUserService;
	
	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	
	@Autowired
	private LoginUserAuthorityService loginUserAuthorityService;
	
	
	

}
