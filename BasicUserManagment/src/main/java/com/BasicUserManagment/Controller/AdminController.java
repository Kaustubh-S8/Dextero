package com.BasicUserManagment.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BasicUserManagment.dto.UserDto;
import com.BasicUserManagment.service.UserService;




@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createnewuser")
    public ResponseEntity<UserDto> CreateNewUser(@RequestBody UserDto request) {
    	return ResponseEntity.ok(userService.CreateUser(request));

    }
    @GetMapping("/getuserdetails/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    
    @PutMapping("/updateuser/userid/{id}")
    public ResponseEntity<UserDto> UpdateUser(@PathVariable Long id, @RequestBody UserDto userdto) {
        return ResponseEntity.ok(userService.UpdateUser(id,userdto));
    }
    
    @DeleteMapping("/deleteuser/userid/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted Successfully");
    }
}