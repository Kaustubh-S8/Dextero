package com.Quiz.Api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Quiz.Login.LoginUser;
import com.Quiz.Login.LoginUserService;
import com.Quiz.Login.Session.LoginSession;
import com.Quiz.Login.Session.LoginSessionService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Job/Portal")
public class LoginApiController {

	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private LoginSessionService loginSessionService;
	
	
	@PostMapping("/Login/Check")
	public ResponseEntity<?> LoginCheck(@RequestBody String requestbody) {
		String Status = "False";
		String st = "";
		APIMessageModel a = new APIMessageModel();
		LocalDateTime currentDateAndTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm:ss");
        String Accesstoken = generateRandomString(60);
		try {
			JSONObject object = new JSONObject(requestbody);
			String username = object.optString("username");
			String password = object.optString("password");
			if(loginuserService.findByUser(username) == 0) {
				st = "Username Not Found";
			}
			else {
				LoginUser l = loginuserService.findByUserName(username);
				if(l.getApassword().equals(password)) {
					if(l.isStatus()) {
						if(loginSessionService.GetCountID(username) == 0) {
							LoginSession s = new LoginSession();
							s.setCurrent_datetime(currentDateAndTime.format(dateFormatter));
							s.setUsername(username);
							s.setAccesstoken(Accesstoken);
							loginSessionService.save(s);
						}
						else {
							LoginSession s = loginSessionService.FindByUserName(username);
							s.setCurrent_datetime(currentDateAndTime.format(dateFormatter));
							s.setUsername(username);
							s.setAccesstoken(Accesstoken);
							loginSessionService.save(s);
						}
						a.setStatus_code(200);
						a.setStatus_message("Ok");
						a.setMessage("Login Successfully");
						a.setAccesstoken(Accesstoken);
						Status = "True";
					}
					else {
						st = "Account is Locked";
					}
				}
				else {
					st = "Password is Invalid";
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(Status.equals("False")) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(st);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(a);
		}
	}
	
	public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }
	
	@GetMapping("/check-username/{username}")
    public @ResponseBody String checkUsername(@PathVariable("username") String username) {
        if (loginuserService.findByUser(username) != 0) {
        	return "A user with that email already exists";
        }
        return "";
	}
    
	
}
