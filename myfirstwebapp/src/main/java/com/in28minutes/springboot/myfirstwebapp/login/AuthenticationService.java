package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

	public boolean authenticate(String username, String password) {
		
		boolean isvalidUserName = username.equalsIgnoreCase("anish");
		boolean isvalidPassword = password.equals("123456");
		
		return isvalidPassword && isvalidUserName;
	}
}
