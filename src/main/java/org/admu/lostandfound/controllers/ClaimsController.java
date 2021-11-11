package org.admu.lostandfound.controllers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Component;

@Component
@Path("/api")
public class ClaimsController {
	
	
	@GET
	@Path("/claims")
	public String getClaims () {
		return "Here's your claim";
	}
	
	@DELETE
	@Path("/claims/{id}")
	public String deleteClaims (@PathParam("id") Integer id) {
		return "Claim deleted";
	}
	
	@POST
	@Path("/claims")
	public String postClaim () {
		return "Claim posted" ;
	}
	
	@GET
	@Path("/my_claims")
	public String myClaims() {
		return "my claims";
	}
}
