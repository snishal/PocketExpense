package com.example.pocketexpense;

public class User {

	private String name;
	private int amount;
	private String username;
	private String password;
	
	public User() {
		this.name = "";
		this.amount = 0;
		this.username = "";
		this.password = "";
	}

	public User(String name, int amount, String username, String password) {
		this.name = name;
		this.amount = amount;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
