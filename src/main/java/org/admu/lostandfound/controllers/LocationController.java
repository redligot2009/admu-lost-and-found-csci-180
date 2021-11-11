package org.admu.lostandfound.controllers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Component;

@Component
@Path("/api")
public class LocationController {
	
	@GET
	@Path("/location")
	public String getLocation() {
		return "found location";
	}
	
	@POST
	@Path("/location")
	public String newLocation() {
		return "new location";
	}
	
	@PUT
	@Path("/location/{id}")
	public String getLocationById(@PathParam("id") Integer id) {
		return "Found Location by ID";
	}
	
	@DELETE
	@Path("/location/{id}")
	public String deleteLocationById(@PathParam("id") Integer id) {
		return "Deleted Location by ID";
	}
}
