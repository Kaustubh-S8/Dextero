package com.Quiz.Api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Quiz.Login.LoginUser;
import com.Quiz.Login.LoginUserAuthority;
import com.Quiz.Login.LoginUserAuthorityService;
import com.Quiz.Login.LoginUserService;
import com.Quiz.Login.Session.LoginSession;
import com.Quiz.Login.Session.LoginSessionService;
import com.Quiz.Users.Users;
import com.Quiz.Users.UsersService;
import com.Quiz.mainMaster.Roles;
import com.Quiz.mainMaster.RolesService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Job/Portal")
public class AddUserControllerApi {


	@Autowired
	private UsersService usersService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginSessionService loginsessionservice;
	@Autowired
	private RolesService rolesService;
	
	@GetMapping("/Userdetails")
	public ResponseEntity<?> getUserDetails(@RequestHeader("accesstoken") String accesstoken) {
		if(loginsessionservice.GetCountAccessToken(accesstoken) == 0) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token Expire Or Invalid");
		}
		else {
			LoginSession l = loginsessionservice.FindByAccessToken(accesstoken);
		
			Users user=usersService.findByEmail(l.getUsername()).get();
			
		return  ResponseEntity.status(HttpStatus.CREATED).body(user);
		}
	}
	
	
	
	@PostMapping("/addGroupAdmin")
	public ResponseEntity<String> createGroupAdmin(@RequestHeader("accesstoken") String accesstoken,@RequestBody String requestbody) {	
		if(loginsessionservice.GetCountAccessToken(accesstoken) == 0) {
		
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token Expire Or Invalid");
		}
		else {
			LoginSession l = loginsessionservice.FindByAccessToken(accesstoken);
	  
			String s="groupAdmin is not created";
			  try {
			    	JSONObject object = new JSONObject(requestbody);
			    
			    	
			    	
					String fname = object.optString("firstname");
					String lname= object.optString("lastname");
					String email_id = object.optString("email");
					String phone= object.optString("phone");
					JSONArray array= object.optJSONArray("otherDetails");
					List<Map<String, Object>> otherDetailsList = new ArrayList<>();

					if (array != null) {
					    for (int i = 0; i < array.length(); i++) {
					        JSONObject jsonObject = array.getJSONObject(i);
					        Map<String, Object> map = new HashMap<>();
					        for (String key : jsonObject.keySet()) {
					            map.put(key, jsonObject.get(key));
					        }
					        otherDetailsList.add(map);
					    }
					}
				
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);	
					Users u=new Users();
					u.setFirstname(fname);
					u.setLastname(lname);
					u.setEmail(email_id);
					u.setPhone(phone);
					u.setAuthority_id(2);
					u.setOtherDetails(otherDetailsList);
					usersService.save(u);
					

					LoginUser m = new LoginUser();
					m.setUsername(email_id);
					m.setPassword(bCryptPasswordEncoder.encode(fname+"@123"));
					m.setApassword(fname+"@123");
					m.setStatus(true);
					loginuserService.save(m);
					
					LoginUserAuthority a = new LoginUserAuthority();
					a.setUser_id(loginuserService.GetAuthorityID(email_id));
					a.setAuthority_id(2);
					loginuserauthorityService.save(a);
					s="groupAdmin is created";
					
			      } catch (Exception e) {
			        e.printStackTrace();
			       
			      }
			  return ResponseEntity.status(HttpStatus.CREATED).body(s);
		}
  }
	
	
	
	
	@PostMapping("/addCompanyAdmin")
	public ResponseEntity<String> createCompanyAdmin(@RequestHeader("accesstoken") String accesstoken,@RequestBody String requestbody) {	
		if(loginsessionservice.GetCountAccessToken(accesstoken) == 0) {
		
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token Expire Or Invalid");
		}
		else {
			LoginSession l = loginsessionservice.FindByAccessToken(accesstoken);
	  
			String s="CompanyAdmin is not created";
			  try {
			    	JSONObject object = new JSONObject(requestbody);
			    
			    	
			    	
					String fname = object.optString("firstname");
					String lname= object.optString("lastname");
					String email_id = object.optString("email");
					String phone= object.optString("phone");
					JSONArray array= object.optJSONArray("otherDetails");
					List<Map<String, Object>> otherDetailsList = new ArrayList<>();

					if (array != null) {
					    for (int i = 0; i < array.length(); i++) {
					        JSONObject jsonObject = array.getJSONObject(i);
					        Map<String, Object> map = new HashMap<>();
					        for (String key : jsonObject.keySet()) {
					            map.put(key, jsonObject.get(key));
					        }
					        otherDetailsList.add(map);
					    }
					}
				
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);	
					Users u=new Users();
					u.setFirstname(fname);
					u.setLastname(lname);
					u.setEmail(email_id);
					u.setPhone(phone);
					u.setAuthority_id(3);
					u.setOtherDetails(otherDetailsList);
					usersService.save(u);
					

					LoginUser m = new LoginUser();
					m.setUsername(email_id);
					m.setPassword(bCryptPasswordEncoder.encode(fname+"@123"));
					m.setApassword(fname+"@123");
					m.setStatus(true);
					loginuserService.save(m);
					
					LoginUserAuthority a = new LoginUserAuthority();
					a.setUser_id(loginuserService.GetAuthorityID(email_id));
					a.setAuthority_id(3);
					loginuserauthorityService.save(a);
					s="CompanyAdmin is created";
					
			      } catch (Exception e) {
			        e.printStackTrace();
			       
			      }
			  return ResponseEntity.status(HttpStatus.CREATED).body(s);
		}
  }
	
	
	
	@PostMapping("/addLocationAdmin")
	public ResponseEntity<String> createLocationAdmin(@RequestHeader("accesstoken") String accesstoken,@RequestBody String requestbody) {	
		if(loginsessionservice.GetCountAccessToken(accesstoken) == 0) {
		
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token Expire Or Invalid");
		}
		else {
			LoginSession l = loginsessionservice.FindByAccessToken(accesstoken);
	  
			String s="LocationAdmin is not created";
			  try {
			    	JSONObject object = new JSONObject(requestbody);
			    	
					String fname = object.optString("firstname");
					String lname= object.optString("lastname");
					String email_id = object.optString("email");
					String phone= object.optString("phone");
					JSONArray array= object.optJSONArray("otherDetails");
					List<Map<String, Object>> otherDetailsList = new ArrayList<>();

					if (array != null) {
					    for (int i = 0; i < array.length(); i++) {
					        JSONObject jsonObject = array.getJSONObject(i);
					        Map<String, Object> map = new HashMap<>();
					        for (String key : jsonObject.keySet()) {
					            map.put(key, jsonObject.get(key));
					        }
					        otherDetailsList.add(map);
					    }
					}
				
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);	
					Users u=new Users();
					u.setFirstname(fname);
					u.setLastname(lname);
					u.setEmail(email_id);
					u.setPhone(phone);
					u.setAuthority_id(4);
					u.setOtherDetails(otherDetailsList);
					usersService.save(u);
					

					LoginUser m = new LoginUser();
					m.setUsername(email_id);
					m.setPassword(bCryptPasswordEncoder.encode(fname+"@123"));
					m.setApassword(fname+"@123");
					m.setStatus(true);
					loginuserService.save(m);
					
					LoginUserAuthority a = new LoginUserAuthority();
					a.setUser_id(loginuserService.GetAuthorityID(email_id));
					a.setAuthority_id(4);
					loginuserauthorityService.save(a);
					s="LocationAdmin is created";
					
			      } catch (Exception e) {
			        e.printStackTrace();
			       
			      }
			  return ResponseEntity.status(HttpStatus.CREATED).body(s);
		}
  }
	
	
	
	@PostMapping("/addBranchAdmin")
	public ResponseEntity<String> createBranchAdmin(@RequestHeader("accesstoken") String accesstoken,@RequestBody String requestbody) {	
		if(loginsessionservice.GetCountAccessToken(accesstoken) == 0) {
		
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token Expire Or Invalid");
		}
		else {
			LoginSession l = loginsessionservice.FindByAccessToken(accesstoken);
	  
			String s="BranchAdmin is not created";
			  try {
			    	JSONObject object = new JSONObject(requestbody);
			    
			    	
			    	
					String fname = object.optString("firstname");
					String lname= object.optString("lastname");
					String email_id = object.optString("email");
					String phone= object.optString("phone");
					JSONArray array= object.optJSONArray("otherDetails");
					List<Map<String, Object>> otherDetailsList = new ArrayList<>();

					if (array != null) {
					    for (int i = 0; i < array.length(); i++) {
					        JSONObject jsonObject = array.getJSONObject(i);
					        Map<String, Object> map = new HashMap<>();
					        for (String key : jsonObject.keySet()) {
					            map.put(key, jsonObject.get(key));
					        }
					        otherDetailsList.add(map);
					    }
					}
				
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);	
					Users u=new Users();
					u.setFirstname(fname);
					u.setLastname(lname);
					u.setEmail(email_id);
					u.setPhone(phone);
					u.setAuthority_id(5);
					u.setOtherDetails(otherDetailsList);
					usersService.save(u);
					

					LoginUser m = new LoginUser();
					m.setUsername(email_id);
					m.setPassword(bCryptPasswordEncoder.encode(fname+"@123"));
					m.setApassword(fname+"@123");
					m.setStatus(true);
					loginuserService.save(m);
					
					LoginUserAuthority a = new LoginUserAuthority();
					a.setUser_id(loginuserService.GetAuthorityID(email_id));
					a.setAuthority_id(5);
					loginuserauthorityService.save(a);
					s="BranchAdmin is created";
					
			      } catch (Exception e) {
			        e.printStackTrace();
			       
			      }
			  return ResponseEntity.status(HttpStatus.CREATED).body(s);
		}
  }
	
	
	
	@PostMapping("/addEmployee")
	public ResponseEntity<String> createEmployee(@RequestHeader("accesstoken") String accesstoken,@RequestBody String requestbody) {	
		if(loginsessionservice.GetCountAccessToken(accesstoken) == 0) {
		
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token Expire Or Invalid");
		}
		else {
			LoginSession l = loginsessionservice.FindByAccessToken(accesstoken);
	  
			String s="Employee is not created";
			  try {
			    	JSONObject object = new JSONObject(requestbody);
			    
			    	
			    	
					String fname = object.optString("firstname");
					String lname= object.optString("lastname");
					String email_id = object.optString("email");
					String phone= object.optString("phone");
					JSONArray array= object.optJSONArray("otherDetails");
					List<Map<String, Object>> otherDetailsList = new ArrayList<>();

					if (array != null) {
					    for (int i = 0; i < array.length(); i++) {
					        JSONObject jsonObject = array.getJSONObject(i);
					        Map<String, Object> map = new HashMap<>();
					        for (String key : jsonObject.keySet()) {
					            map.put(key, jsonObject.get(key));
					        }
					        otherDetailsList.add(map);
					    }
					}
				
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);	
					Users u=new Users();
					u.setFirstname(fname);
					u.setLastname(lname);
					u.setEmail(email_id);
					u.setPhone(phone);
					u.setAuthority_id(6);
					u.setOtherDetails(otherDetailsList);
					usersService.save(u);
					

					LoginUser m = new LoginUser();
					m.setUsername(email_id);
					m.setPassword(bCryptPasswordEncoder.encode(fname+"@123"));
					m.setApassword(fname+"@123");
					m.setStatus(true);
					loginuserService.save(m);
					
					LoginUserAuthority a = new LoginUserAuthority();
					a.setUser_id(loginuserService.GetAuthorityID(email_id));
					a.setAuthority_id(6);
					loginuserauthorityService.save(a);
					s="Employee is created";
					
			      } catch (Exception e) {
			        e.printStackTrace();
			       
			      }
			  return ResponseEntity.status(HttpStatus.CREATED).body(s);
		}
  }
	
	
	
	@PostMapping("/addCollegeAdmin")
	public ResponseEntity<String> createCollegeAdmin(@RequestHeader("accesstoken") String accesstoken,@RequestBody String requestbody) {	
		if(loginsessionservice.GetCountAccessToken(accesstoken) == 0) {
		
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token Expire Or Invalid");
		}
		else {
			LoginSession l = loginsessionservice.FindByAccessToken(accesstoken);
	  
			String s="CollegeAdmin is not created";
			  try {
			    	JSONObject object = new JSONObject(requestbody);
			    
			    	
			    	
					String fname = object.optString("firstname");
					String lname= object.optString("lastname");
					String email_id = object.optString("email");
					String phone= object.optString("phone");
					JSONArray array= object.optJSONArray("otherDetails");
					List<Map<String, Object>> otherDetailsList = new ArrayList<>();

					if (array != null) {
					    for (int i = 0; i < array.length(); i++) {
					        JSONObject jsonObject = array.getJSONObject(i);
					        Map<String, Object> map = new HashMap<>();
					        for (String key : jsonObject.keySet()) {
					            map.put(key, jsonObject.get(key));
					        }
					        otherDetailsList.add(map);
					    }
					}
				
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);	
					Users u=new Users();
					u.setFirstname(fname);
					u.setLastname(lname);
					u.setEmail(email_id);
					u.setPhone(phone);
					u.setAuthority_id(7);
					u.setOtherDetails(otherDetailsList);
					usersService.save(u);
					

					LoginUser m = new LoginUser();
					m.setUsername(email_id);
					m.setPassword(bCryptPasswordEncoder.encode(fname+"@123"));
					m.setApassword(fname+"@123");
					m.setStatus(true);
					loginuserService.save(m);
					
					LoginUserAuthority a = new LoginUserAuthority();
					a.setUser_id(loginuserService.GetAuthorityID(email_id));
					a.setAuthority_id(7);
					loginuserauthorityService.save(a);
					s="CollegeAdmin is created";
					
			      } catch (Exception e) {
			        e.printStackTrace();
			       
			      }
			  return ResponseEntity.status(HttpStatus.CREATED).body(s);
		}
  }
	
	
	@PostMapping("/addCollegeEmployee")
	public ResponseEntity<String> createCollegeEmployee(@RequestHeader("accesstoken") String accesstoken,@RequestBody String requestbody) {	
		if(loginsessionservice.GetCountAccessToken(accesstoken) == 0) {
		
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token Expire Or Invalid");
		}
		else {
			LoginSession l = loginsessionservice.FindByAccessToken(accesstoken);
	  
			String s="CollegeEmployee is not created";
			  try {
			    	JSONObject object = new JSONObject(requestbody);
			    
			    	
			    	
					String fname = object.optString("firstname");
					String lname= object.optString("lastname");
					String email_id = object.optString("email");
					String phone= object.optString("phone");
					JSONArray array= object.optJSONArray("otherDetails");
					List<Map<String, Object>> otherDetailsList = new ArrayList<>();

					if (array != null) {
					    for (int i = 0; i < array.length(); i++) {
					        JSONObject jsonObject = array.getJSONObject(i);
					        Map<String, Object> map = new HashMap<>();
					        for (String key : jsonObject.keySet()) {
					            map.put(key, jsonObject.get(key));
					        }
					        otherDetailsList.add(map);
					    }
					}
				
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);	
					Users u=new Users();
					u.setFirstname(fname);
					u.setLastname(lname);
					u.setEmail(email_id);
					u.setPhone(phone);
					u.setAuthority_id(8);
					u.setOtherDetails(otherDetailsList);
					usersService.save(u);
					

					LoginUser m = new LoginUser();
					m.setUsername(email_id);
					m.setPassword(bCryptPasswordEncoder.encode(fname+"@123"));
					m.setApassword(fname+"@123");
					m.setStatus(true);
					loginuserService.save(m);
					
					LoginUserAuthority a = new LoginUserAuthority();
					a.setUser_id(loginuserService.GetAuthorityID(email_id));
					a.setAuthority_id(8);
					loginuserauthorityService.save(a);
					s="CollegeEmployee is created";
					
			      } catch (Exception e) {
			        e.printStackTrace();
			       
			      }
			  return ResponseEntity.status(HttpStatus.CREATED).body(s);
		}
  }
	
	
	
	
	///////////////////////////////////////// ADD USER ///////////////////////////////////////////
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> createUser(@RequestBody String requestbody) {	
		
			String s="user is not created";
			  try {
			    	JSONObject object = new JSONObject(requestbody);
			    	
					String fname = object.optString("firstname");
					String lname= object.optString("lastname");
					String email_id = object.optString("email");
					String phone= object.optString("phone");
					JSONArray array= object.optJSONArray("otherDetails");
					List<Map<String, Object>> otherDetailsList = new ArrayList<>();

					if (array != null) {
					    for (int i = 0; i < array.length(); i++) {
					        JSONObject jsonObject = array.getJSONObject(i);
					        Map<String, Object> map = new HashMap<>();
					        for (String key : jsonObject.keySet()) {
					            map.put(key, jsonObject.get(key));
					        }
					        otherDetailsList.add(map);
					    }
					}
				
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);	
					Users u=new Users();
					u.setFirstname(fname);
					u.setLastname(lname);
					u.setEmail(email_id);
					u.setPhone(phone);
					u.setAuthority_id(9);
					u.setOtherDetails(otherDetailsList);
					usersService.save(u);
					

					LoginUser m = new LoginUser();
					m.setUsername(email_id);
					m.setPassword(bCryptPasswordEncoder.encode(fname+"@123"));
					m.setApassword(fname+"@123");
					m.setStatus(true);
					loginuserService.save(m);
					
					LoginUserAuthority a = new LoginUserAuthority();
					a.setUser_id(loginuserService.GetAuthorityID(email_id));
					a.setAuthority_id(9);
					loginuserauthorityService.save(a);
					s="user is created";
					
			      } catch (Exception e) {
			        e.printStackTrace();
			       
			      }
			  return ResponseEntity.status(HttpStatus.CREATED).body(s);
		
  }
	
	
	
	
	//////////////////////////////////////////////////	Roles	//////////////////////////////////////////////////////
	
	
	@PostMapping("/addRoles")
	public ResponseEntity<String> AddRoles(@RequestHeader("accesstoken") String accesstoken,@RequestBody Roles roles) {
		if(loginsessionservice.GetCountAccessToken(accesstoken) == 0) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token Expire Or Invalid");
		}
		else {
			LoginSession l = loginsessionservice.FindByAccessToken(accesstoken);
			
			String s="Roles is not added";
			
//			roles.setCompanyid(null);
			rolesService.save(roles);
			
			
			s="Role is added";
	
		return ResponseEntity.status(HttpStatus.CREATED).body(s);
		}
	}
	
	@GetMapping("/listRoles")
	public ResponseEntity<List<Roles>> ListRoles() {
		List<Roles> list=rolesService.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@PostMapping("/EditRoles/{id}")
	public ResponseEntity<String> EditRole(@PathVariable Integer id,@RequestParam String roles) {
		Roles r= rolesService.findRolesNameById(id);
		r.setRoles(roles);
		rolesService.save(r);
		return ResponseEntity.status(HttpStatus.OK).body("roles updated");
	}
	
	@GetMapping("/deleteRoles/{id}")
	public ResponseEntity<String> DeleteRole(@PathVariable Integer id){
		rolesService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Role is deleted");
	}
	
///////////////////////////////////////////// 
	
}
