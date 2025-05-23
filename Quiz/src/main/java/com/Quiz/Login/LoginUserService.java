package com.Quiz.Login;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;



@Service
@Transactional
public class LoginUserService implements UserDetailsService{

		 @Autowired
			public LoginUserRepository repo;
			
			@Autowired
			private LoginUserAuthorityService loginuserauthorityService;

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				LoginUser appUser = repo.findByUsername(username)
						.orElseThrow(()-> new UsernameNotFoundException("Not Exists User"));
				
				return new User(appUser.getUsername(), appUser.getPassword(), getAuthorities(appUser));
			}
				private static Collection<? extends GrantedAuthority> getAuthorities(LoginUser appUser) {
			        String[] userRoles = appUser.getAuthority().stream().map((authority) -> authority.getAuthority()).toArray(String[]::new);
			        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
			        return authorities;
			    }
				
				
				public Integer GetAuthorityID(String username) {
				      return repo.GetAuthorityID(username);
				 }
				
				public List<LoginUser> listAll() {
				      return repo.findAll();
				 }
				 
				public LoginUser get(int id) {
					  return repo.findById(id).get();
				 }
				 
				 public void save(LoginUser std) {
				      repo.save(std);
				 }
				 
				 public void deleteLoginUser(int login_user_id) {
						repo.deleteById(login_user_id);
					}
				 public void UpdatePasswordByUsername(String username, String password, String apassword) {
					  repo.UpdatePasswordByUsername(username, password, apassword);
				 }
				 
				 public List<LoginUser> getUserName(String username){
					 return repo.getUserName(username);
				 }
				
				 public int findByUser(String username) {
					 return repo.findByUser(username);
				 }
				 
				 public LoginUser findByUserName(String username) {
					 return repo.findByUserName(username);
				 }
				 
				 public String getStatusByUsernamePassword(String username) {
					 return repo.getStatusByUsernamePassword(username);
				 }
}

