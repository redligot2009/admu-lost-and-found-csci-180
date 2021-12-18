package org.admu.lostandfound.controllers;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.admu.lostandfound.components.JwtUtils;
import org.admu.lostandfound.models.User;
import org.admu.lostandfound.models.UserDetailsImpl;
import org.admu.lostandfound.payload.JwtResponse;
import org.admu.lostandfound.payload.LoginRequest;
import org.admu.lostandfound.payload.SignupRequest;
import org.admu.lostandfound.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("/api/auth")
public class AuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;

  	@Autowired
  	UserRepository userRepository;

  	@Autowired
  	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity("Error: Username is already taken!")
					.build();
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity("Error: Email is already in use!")
					.build();
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getRole());

		userRepository.save(user);

		// Return newly created user if successful
		return Response
				.ok()
				.entity(user)
				.build();
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return Response.ok()
				.entity(new JwtResponse(jwt,
					userDetails.getId(),
					userDetails.getUsername(),
					userDetails.getEmail(),
					roles.get(0)))
				.build();
	}
}
