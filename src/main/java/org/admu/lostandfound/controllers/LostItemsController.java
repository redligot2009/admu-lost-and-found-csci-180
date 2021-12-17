package org.admu.lostandfound.controllers;

import org.admu.lostandfound.components.LostItemsComponent;
import org.admu.lostandfound.models.LostItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
	public List<LostItem> getLostItems(
			@QueryParam("title") String title,
			@QueryParam("itemStatus") String itemStatus,
			@QueryParam("date") String date,
			@QueryParam("categoryId") Long categoryId,
			@QueryParam("locationId") Long locationId
	) {
		return lostItemsComponent.getLostItems(title, itemStatus, date, categoryId, locationId);
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

	@DELETE
	@Path("/postings/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public LostItem deleteLostItemByID(@PathParam("id") Long id) {
		return lostItemsComponent.deleteLostItemByID(id);
	}
}
