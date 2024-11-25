package com.GUESST.bean;

public class Owner {
	
	private int id;
	private String name;
	private String email;
	private String buildingName;
	private String password;
//	private int tenantent;
	
	

	public Owner(int id, String name, String email, String buildingName, String password) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.buildingName = buildingName;
	this.password = password;
	
}
	
	public Owner(String name, String email, String buildingName, String password) {
	super();
//	this.id = id;
	this.name = name;
	this.email = email;
	this.buildingName = buildingName;
	this.password = password;
	
}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public int getRent() {
//		return rent;
//	}
//	public void setRent(int rent) {
//		this.rent = rent;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	

}
