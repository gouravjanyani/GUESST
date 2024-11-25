package com.GUESST.bean;



public class Tenant {
	
	private int id;
	private String name;
	private String password;
	private int floor;

	private String email;
	private int roomNumber;
	private String building;
	private String DOJ;
	private int rent;
	private boolean rentStatus;
	private boolean payVerification;
	private String proof;
	
	
	public Tenant(int id, String proof) {
		super();
		this.id = id;
		this.proof = proof;
		
	}

	public Tenant(String name, String password, int floor, String email, int roomNumber, String building, String DOJ, int rent, boolean rentStatus, boolean payVerification) {
		super();
		this.name = name;
		this.password = password;
		this.floor = floor;
		this.email = email;
		this.roomNumber = roomNumber;
		this.building = building;
		this.DOJ = DOJ;
		this.rent = rent;
		this.rentStatus = rentStatus;
		this.payVerification = payVerification;
		//insert
	}

	public Tenant(int id, String name, String password, int floor, String email, int roomNumber, String building, String DOJ, int rent, boolean rentStatus, boolean payVerification , String proof) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.floor = floor;
		this.email = email;
		this.roomNumber = roomNumber;
		this.building = building;
		this.DOJ = DOJ;
		this.rent = rent;
		this.rentStatus = rentStatus;
		this.payVerification = payVerification;
		this.proof = proof;
	}
	public Tenant(int id, String name, int floor, String email, int roomNumber, String building, String DOJ, int rent, boolean rentStatus, boolean payVerification) {
		super();
		this.id = id;
		this.name = name;
		this.floor = floor;
		this.email = email;
		this.roomNumber = roomNumber;
		this.building = building;
		this.DOJ = DOJ;
		this.rent = rent;
		this.rentStatus = rentStatus;
		this.payVerification = payVerification;
		
		
	}
	public Tenant(int id, String name, int floor, String email, int roomNumber, int rent, boolean rentStatus, boolean payVerification) {
		super();
		this.id = id;
		this.name = name;
		this.floor = floor;
		this.email = email;
		this.roomNumber = roomNumber;
		this.rent = rent;
		this.rentStatus = rentStatus;
		this.payVerification = payVerification;
		//update
	}
	
	
	public String getProof() {
		return proof;
	}
	public void setProof(String proof) {
		this.proof = proof;
	}
	public boolean isPayVerification() {
		return payVerification;
	}
	public void setPayVerification(boolean payVerification) {
		this.payVerification = payVerification;
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
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public boolean isRentStatus() {
		return rentStatus;
	}
	public String getDOJ() {
		return DOJ;
	}

	public void setDOJ(String dOJ) {
		DOJ = dOJ;
	}

	public void setRentStatus(boolean rentStatus) {
		this.rentStatus = rentStatus;
	}
	
	

}
