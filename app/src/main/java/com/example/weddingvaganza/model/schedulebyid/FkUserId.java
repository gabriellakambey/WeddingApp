package com.example.weddingvaganza.model.schedulebyid;

public class FkUserId{
	private String date;
	private String userPassword;
	private String userCouple;
	private String userEmail;
	private String userName;
	private String nomorHp;
	private int userId;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public void setUserCouple(String userCouple){
		this.userCouple = userCouple;
	}

	public String getUserCouple(){
		return userCouple;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setNomorHp(String nomorHp){
		this.nomorHp = nomorHp;
	}

	public String getNomorHp(){
		return nomorHp;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}
}
