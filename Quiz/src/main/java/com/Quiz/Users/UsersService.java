package com.Quiz.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

	@Autowired
	private UsersRepository repo;
	
	
	public void save(Users std) {
		repo.save(std);
	}
	public List<Users> listAll(){
		return repo.findAll();
	}
	public Users getById(Integer id) {
		return repo.findById(id).get();
	}
	public Optional<Users> findByEmail(String email) {
		return repo.findByEmail(email);
	}
	 public Integer Update(String emailid) {
		 return repo.Update(emailid);
	 }
}
