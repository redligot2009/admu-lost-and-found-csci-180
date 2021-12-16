package org.admu.lostandfound.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.admu.lostandfound.components.CategoryComponent;
import org.admu.lostandfound.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Map;

@Component
@Path("/api")
public class CategoryController {

	@Autowired
	CategoryComponent categoryComponent;

	@GET
	@Path("/category")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Category getCategory(@QueryParam("title") String title) {

		Category category = categoryComponent.getCategory(title);
		return category;
	}
	
	@POST
	@Path("/category")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Category newCategory(@RequestBody Map<String,Object> body) {

		LocalDate currentDate = LocalDate.now();
		Category postCategory = categoryComponent.createNewCategory(body, currentDate);
		return postCategory;
	}
	
	@PUT
	@Path("/category/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Category updateCategory(@PathParam("id") Long id, @RequestBody Map<String,Object> body) {

		Category putCategory = categoryComponent.putCategory(id, body);
		return putCategory;

	}
	
	@DELETE
	@Path("/category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Category deleteCategory(@PathParam("id") Long id) {

		Category deletedCategory = categoryComponent.deleteCategory(id);
		return deletedCategory;
	}
}
