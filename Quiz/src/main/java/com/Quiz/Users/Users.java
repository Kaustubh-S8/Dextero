package com.Quiz.Users;

import java.util.*;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "user_details")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "Email",unique  = true)
    private String email;
    
    @Column(name = "phone_number")
    private String phone;
    
    private Integer authority_id;
    
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Map<String, Object>> otherDetails;
    
   

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Map<String, Object>> getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(List<Map<String, Object>> otherDetails) {
        this.otherDetails = otherDetails;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAuthority_id() {
		return authority_id;
	}

	public void setAuthority_id(Integer authority_id) {
		this.authority_id = authority_id;
	}

    
}
