package com.BasicUserManagment.dto;


import com.BasicUserManagment.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    @Email(message = "Invalid email format")
    @Pattern(regexp = ".+@.+\\..+")
    private String email;
    
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be at least 8 characters long and contain one digit, one lowercase, one uppercase, and one special character"
        )
    private String password;
    private Role role;

    
   
}