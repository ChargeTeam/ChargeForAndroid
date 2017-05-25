package com.example.zhangqi.charge.bean;

public class User {
	
	private String userId;
	private String phone;
	private String password;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", phone='" + phone + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
