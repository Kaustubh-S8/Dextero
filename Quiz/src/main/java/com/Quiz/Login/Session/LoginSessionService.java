package com.Quiz.Login.Session;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginSessionService {

	@Autowired
	private LoginSessionRepository repo;
	 
	public List<LoginSession> listAll() {
		return repo.findAll();
	}
	 
	public LoginSession get(int id) {
		return repo.findById(id).get();
	}
	 
	public void save(LoginSession std) {
		repo.save(std);
	}
	 
	public void deleteLoginSession(int id) {
		repo.deleteById(id);
	}
	
	public LoginSession FindByUserName(String username) {
		return repo.FindByUserName(username);
	}
	
	public Integer GetCountID(String username) {
		return repo.GetCountID(username);
	}
	
	public LoginSession FindByAccessToken(String accesstoken) {
		return repo.FindByAccessToken(accesstoken);
	}
	
	public Integer GetCountAccessToken(String accesstoken) {
		return repo.GetCountAccessToken(accesstoken);
	}
}
