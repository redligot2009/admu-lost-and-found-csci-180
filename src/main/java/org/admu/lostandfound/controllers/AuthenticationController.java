package org.admu.lostandfound.controllers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/api/auth")
public class AuthenticationController {
	
	@POST
	@Path("/register")
	public String register() {
		return "register";
	}
	
	@POST
	@Path("/login")
	public String login() {
		return "login";
	}
}
