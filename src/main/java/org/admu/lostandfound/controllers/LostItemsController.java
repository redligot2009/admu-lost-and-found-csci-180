package org.admu.lostandfound.controllers;

import org.admu.lostandfound.components.LostItemsComponent;
import org.admu.lostandfound.models.LostItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.Map;

@Component
@Path("/api")
public class LostItemsController {

	@Autowired
	private LostItemsComponent lostItemsComponent;
	
	@GET
	@Path("/postings")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String getLostItems(
			@QueryParam("item_title") String title,
			@QueryParam("item_description") String description,
			@QueryParam("item_status") String itemStatus,
			@QueryParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
			@QueryParam("category") Integer categoryId,
			@QueryParam("location") Integer locationId
	) {
		return "lost items";
	}
	
	@POST
	@Path("/postings")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LostItem newLostItem(@RequestBody Map<String,Object> body) {
		return lostItemsComponent.newLostItem(body);
	}

	@GET
	@Path("/postings/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public LostItem getLostItemByID(@PathParam("id") Long id) {
		return lostItemsComponent.getLostItemByID(id);
	}

	@PUT
	@Path("/postings/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public LostItem updateLostItemByID(@PathParam("id") Long id, @RequestBody Map<String,Object> body) {
		return lostItemsComponent.updateLostItemByID(id, body);
	}

}
