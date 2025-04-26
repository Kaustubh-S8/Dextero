package com.BasicUserManagment.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BasicUserManagment.dto.JwtResponse;
import com.BasicUserManagment.dto.LoginRequest;
import com.BasicUserManagment.dto.SignupRequest;
import com.BasicUserManagment.entity.User;
import com.BasicUserManagment.repository.UserRepository;
import com.BasicUserManagment.security.JwtUtils;
import com.BasicUserManagment.security.UserDetailsImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
       


        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    	 if (userRepository.existsByEmail(signUpRequest.getEmail())) {
    	        return ResponseEntity
    	            .badRequest()
    	            .body("Email is already in use!");
    	    }

    	    User user = new User(
    	        signUpRequest.getName(),
    	        signUpRequest.getEmail(),
    	        encoder.encode(signUpRequest.getPassword())
    	    );

    	   
    	    
    	    user.setRole(signUpRequest.getRole());
    	    userRepository.save(user);

    	    return ResponseEntity.ok("User registered successfully!");
    	}
}