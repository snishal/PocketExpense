package com.example.pocketexpense;

public class Expense {
	private int expense;
	private String date;
	private String type;
	
	public Expense() {
		expense=0;
		date="";
		type="";
	}

	public Expense(int expense, String date,String type) {
		this.expense = expense;
		this.date = date;
		this.type=type;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
