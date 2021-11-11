package org.admu.lostandfound.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/api")
public class LostItemsController {
	
	@GET
	@Path("/postings")
	public String getLostItems() {
		return "found items";
	}
	
	@POST
	@Path("/postings")
	public String newLostItem() {
		return "posted Item";
	}
	
}
