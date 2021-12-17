package org.admu.lostandfound.controllers;

import org.admu.lostandfound.components.LostItemsComponent;
import org.admu.lostandfound.models.LostItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Component
@Path("/api")
public class LostItemsController {

	@Autowired
	private LostItemsComponent lostItemsComponent;
	
	@GET
	@Path("/postings")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLostItems(
			@QueryParam("title") String title,
			@QueryParam("itemStatus") String itemStatus,
			@QueryParam("date") String date,
			@QueryParam("categoryId") Long categoryId,
			@QueryParam("locationId") Long locationId
	) {
		try {
			List<LostItem> lostItem = lostItemsComponent.getLostItems(title, itemStatus, date, categoryId, locationId);
			return Response.ok(lostItem).build();
		} catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("/postings")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newLostItem(@RequestBody Map<String,Object> body) {
		try {
			LostItem lostItem = lostItemsComponent.newLostItem(body);
			return Response.ok(lostItem).build();
		} catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/postings/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLostItemByID(@PathParam("id") Long id) {
		try {
			LostItem lostItem = lostItemsComponent.getLostItemByID(id);
			return Response.ok(lostItem).build();
		} catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/postings/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateLostItemByID(@PathParam("id") Long id, @RequestBody Map<String,Object> body) {
		try {
			LostItem lostItem = lostItemsComponent.updateLostItemByID(id, body);
			return Response.ok(lostItem).build();
		} catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/postings/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteLostItemByID(@PathParam("id") Long id) {
		try {
			LostItem lostItem = lostItemsComponent.deleteLostItemByID(id);
			return Response.ok(lostItem).build();
		} catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
}
