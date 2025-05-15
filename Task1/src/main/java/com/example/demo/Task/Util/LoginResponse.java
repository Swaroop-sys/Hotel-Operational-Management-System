package com.example.demo.Task.Util;

import com.example.demo.Task.Enum.UserRoles;

public class LoginResponse {
	private String token;
	private String username;
	private UserRoles userRole;
	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResponse(String token, String username, UserRoles userRoles) {
		
		this.token = token;
		this.username = username;
		this.userRole = userRoles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserRoles getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", username=" + username + ", userRole=" + userRole + "]";
	}
	
	
}
