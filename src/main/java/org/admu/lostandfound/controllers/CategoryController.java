package org.admu.lostandfound.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.admu.lostandfound.components.CategoryComponent;
import org.admu.lostandfound.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
@Path("/api")
public class CategoryController {

	@Autowired
	CategoryComponent categoryComponent;

	@GET
	@Path("/category")
	@Produces(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasRole('ADMIN')")
	public Response getCategoryByTitle(@QueryParam("title") String title) {

		try {
			List<Category> categories = categoryComponent.getCategory(title);
			return Response.ok(categories).build();
		} catch(Exception e){
			return Response.status(404)
					.entity(e.getMessage())
					.build();
		}


	}

	@POST
	@Path("/category")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasRole('ADMIN')")
	public Category newCategory(@RequestBody Map<String,Object> body) {

		LocalDate currentDate = LocalDate.now();
		Category postCategory = categoryComponent.createNewCategory(body, currentDate);
		return postCategory;

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PUT
	@Path("/category/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategory(@PathParam("id") Long id, @RequestBody Map<String,Object> body) {

		try{
			Category putCategory = categoryComponent.putCategory(id, body);
			return Response.ok(putCategory).build();
		} catch(Exception e) {
			return  Response.status(404)
					.entity(e.getMessage())
					.build();
		}


	}

	@DELETE
	@Path("/category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasRole('ADMIN')")
	public Response deleteCategory(@PathParam("id") Long id) {

		try{
			Category deletedCategory = categoryComponent.deleteCategory(id);
			return Response.ok(deletedCategory).build();
		} catch(Exception e) {
			return Response.status(404)
					.entity(e.getMessage())
					.build();
		}

	}
}
