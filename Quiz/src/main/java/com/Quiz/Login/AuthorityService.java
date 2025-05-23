package com.Quiz.Login;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AuthorityService {
	@Autowired
	 private AuthorityRepository repo;
	 
	 public List<Authority> listAll() {
	      return repo.findAll();
	 }
	 public Optional<Authority> findByAuthority(String authority) {
		 return repo.findByAuthority(authority);
	 }
	 
	 public Authority get(int authority_id) {
		  return repo.findById(authority_id).get();
	 }
	 
	 public void save(Authority std) {
	      repo.save(std);
	 }
	 
	 public void deleteAuthority(int authority_id) {
			repo.deleteById(authority_id);
	 }
	 
	 public String getAuthorityNameById(Integer authority_id){
		 return repo.getAuthorityNameById(authority_id);
	 }
}
