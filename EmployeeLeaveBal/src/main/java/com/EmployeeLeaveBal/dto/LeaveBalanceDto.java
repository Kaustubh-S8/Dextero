package com.EmployeeLeaveBal.dto;

import lombok.Data;

@Data
public class LeaveBalanceDto {

	private String email;
	private String name;
	
	private String designation;
	
	private int totalAollcatedleaves;
	private int leavestaken;
	private int Remaining_leaves;
	public LeaveBalanceDto(String email,String name, String designation, int totalAollcatedleaves, int leavestaken) {
		super();
		this.email=email;
		this.name = name;
		this.designation = designation;
		this.totalAollcatedleaves = totalAollcatedleaves;
		this.leavestaken = leavestaken;
		this.Remaining_leaves=totalAollcatedleaves-leavestaken;
	}
	
	
}
