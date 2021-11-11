package org.admu.lostandfound.components;

import org.admu.lostandfound.models.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationComponent 
{
	
	// Repositories here
	

	// Regular Methods that return null
	public User registerUser(String username, String password, String email, String role)
	{
		return null;
	}
	
	public User loginUser(String username, String password) // "UserCredentials" to replace "User"
	{
		return null;
	}
	
	
}
