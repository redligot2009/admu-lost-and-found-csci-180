package org.admu.lostandfound.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	
	@GET
	@Path("/postings/{id}")
	public String getLostItemByID(@PathParam("id") Integer id) {
		return "Item by ID";
	}
	
	@PUT
	@Path("/postings/{id}")
	public String updateLostItemByID(@PathParam("id") Integer id) {
		return "Updated Item with ID";
	}
	
}
