package com.Quiz.JobPost;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	private Integer Designation;
	private Integer EmploymentType;
	private String CompanyName;
	@Column(length = 2000)
	private String JobDescription; 
	@Column(length = 2000)
	private String Responsibilities;
	@Column(length = 2000)
	private String KeySkills;
	private String MinWorkExperience;
	private String MaxWorkExperience;  
	private String salary;
	@Column(length = 2000)
	private String CompanyDetails;
	private Integer Role;
	private String postedDate;
	private Integer WorkMode;
	private Integer Industry;
	private Integer FunctionalArea;
	private Integer EducationalQualification;
	private String NumberofVacancies;
	private Integer response;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDesignation() {
		return Designation;
	}
	public void setDesignation(Integer designation) {
		Designation = designation;
	}
	public Integer getEmploymentType() {
		return EmploymentType;
	}
	public void setEmploymentType(Integer employmentType) {
		EmploymentType = employmentType;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getJobDescription() {
		return JobDescription;
	}
	public void setJobDescription(String jobDescription) {
		JobDescription = jobDescription;
	}
	public String getResponsibilities() {
		return Responsibilities;
	}
	public void setResponsibilities(String responsibilities) {
		Responsibilities = responsibilities;
	}
	public String getKeySkills() {
		return KeySkills;
	}
	public void setKeySkills(String keySkills) {
		KeySkills = keySkills;
	}
	public String getMinWorkExperience() {
		return MinWorkExperience;
	}
	public void setMinWorkExperience(String minWorkExperience) {
		MinWorkExperience = minWorkExperience;
	}
	public String getMaxWorkExperience() {
		return MaxWorkExperience;
	}
	public void setMaxWorkExperience(String maxWorkExperience) {
		MaxWorkExperience = maxWorkExperience;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getCompanyDetails() {
		return CompanyDetails;
	}
	public void setCompanyDetails(String companyDetails) {
		CompanyDetails = companyDetails;
	}
	public Integer getRole() {
		return Role;
	}
	public void setRole(Integer role) {
		Role = role;
	}
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	public Integer getWorkMode() {
		return WorkMode;
	}
	public void setWorkMode(Integer workMode) {
		WorkMode = workMode;
	}
	public Integer getIndustry() {
		return Industry;
	}
	public void setIndustry(Integer industry) {
		Industry = industry;
	}
	public Integer getFunctionalArea() {
		return FunctionalArea;
	}
	public void setFunctionalArea(Integer functionalArea) {
		FunctionalArea = functionalArea;
	}
	public Integer getEducationalQualification() {
		return EducationalQualification;
	}
	public void setEducationalQualification(Integer educationalQualification) {
		EducationalQualification = educationalQualification;
	}
	public String getNumberofVacancies() {
		return NumberofVacancies;
	}
	public void setNumberofVacancies(String numberofVacancies) {
		NumberofVacancies = numberofVacancies;
	}
	public Integer getResponse() {
		return response;
	}
	public void setResponse(Integer response) {
		this.response = response;
	}
	
	
	
	
	
}
