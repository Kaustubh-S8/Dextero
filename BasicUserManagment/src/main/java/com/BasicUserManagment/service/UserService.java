package com.BasicUserManagment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BasicUserManagment.dto.UserDto;
import com.BasicUserManagment.entity.User;
import com.BasicUserManagment.exception.UserNotFound;
import com.BasicUserManagment.repository.UserRepository;


@Service
public class UserService {
	@Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder Encoder;
    
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }
 

	public UserDto findUserById(Long id) {
		User user=userRepo.findById(id).orElseThrow(()->new UserNotFound("User is already registered"));
		return convertToDto(user);
	}


	public List<UserDto> getAllUser(int page,int size) {
		Pageable pageable=PageRequest.of(page-1, size);//i do page-1 because page cannot be zero, page number start from 1
		Page<User> listofuser= userRepo.findAll(pageable);
		
		
		return listofuser.stream().map(this::convertToDto)
                .collect(Collectors.toList());
	}


	public UserDto CreateUser(UserDto userdto) {
		 Optional<User> user = userRepo.findByEmail(userdto.getEmail());
		    if (user.isPresent()) throw new UserNotFound("User is already registered");

		    User newuser = new User();
		    
		    newuser.setName(userdto.getName());
		    newuser.setEmail(userdto.getEmail());
		    newuser.setPassword(Encoder.encode(userdto.getPassword()));
		    newuser.setRole(userdto.getRole());
		    return convertToDto(userRepo.save(newuser));
		
	}
	


	public UserDto UpdateUser(Long id, UserDto userdto) {
		User user=userRepo.findById(id).orElseThrow(()->new UserNotFound("User is already registered"));
		 if (userRepo.existsByEmail(userdto.getEmail())) {
	            throw new UserNotFound("Email is already in used!");
	        }
			user.setName(userdto.getName());
			user.setEmail(userdto.getEmail());
			user.setPassword(Encoder.encode(userdto.getPassword()));
			user.setRole(userdto.getRole());
			return convertToDto(userRepo.save(user));
	}


	public void deleteUser(Long id) {
		User user=userRepo.findById(id).orElseThrow(()->new UserNotFound("User is already registered"));
	
		userRepo.delete(user);
	}

   
}