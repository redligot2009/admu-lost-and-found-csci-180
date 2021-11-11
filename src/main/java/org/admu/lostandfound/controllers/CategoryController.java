package org.admu.lostandfound.controllers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/api")
public class CategoryController {
	
	@GET
	@Path("/category")
	public String getCategory() {
		return "category";
	}
	
	@POST
	@Path("/category")
	public String newCategory() {
		return "New category";
	}
	
	@PUT
	@Path("/category/{id}")
	public String updateCategory() {
		return "updated category";
	}
	
	@DELETE
	@Path("/category/{id}")
	public String deleteCategory() {
		return "deleted category";
	}
}
