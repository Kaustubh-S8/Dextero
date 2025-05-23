package com.Quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Quiz.Login.Authority;
import com.Quiz.Login.AuthorityRepository;
import com.Quiz.Login.LoginUser;
import com.Quiz.Login.LoginUserAuthority;
import com.Quiz.Login.LoginUserAuthorityService;
import com.Quiz.Login.LoginUserService;
import com.Quiz.Users.Users;
import com.Quiz.Users.UsersService;

import java.util.Arrays;

@Configuration
public class DataInitializer {
	
	@Autowired
	private UsersService userservice ;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	
    @Bean
    public CommandLineRunner insertAuthorities(AuthorityRepository authorityRepository) {
        return args -> {
            String[] roles = {"Master Admin","Group Admin","Company Admin", "Location Admin", "Branch Admin","Employee","College Admin","College Employee","User"};

            Arrays.stream(roles).forEach(role -> {
                if (!authorityRepository.findByAuthority(role).isPresent()) {
                    Authority authority = new Authority();
                    authority.setAuthority(role);
                    authorityRepository.save(authority);
                    System.out.println("Inserted authority: " + role);
                }
            });
            
            if(!userservice.findByEmail("admin@gmail.com").isPresent()) {
            	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);	
            	Users u=new Users();
            	u.setAuthority_id(1);
            	u.setEmail("admin@gmail.com");
            	u.setFirstname("admin");
            	u.setLastname(null);
            	u.setOtherDetails(null);
            	u.setPhone(null);
            	userservice.save(u);
            	
            	LoginUser m = new LoginUser();
        		m.setUsername("admin@gmail.com");
        		m.setPassword(bCryptPasswordEncoder.encode("admin"));
        		m.setApassword("admin");
        		m.setStatus(true);
        		loginuserService.save(m);
        		
        		LoginUserAuthority a = new LoginUserAuthority();
        		a.setUser_id(loginuserService.GetAuthorityID("admin@gmail.com"));
        		a.setAuthority_id(1);
        		loginuserauthorityService.save(a);
            	
            }
           
        };
    }
}
