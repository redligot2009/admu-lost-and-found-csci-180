package org.admu.lostandfound.controllers;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.admu.lostandfound.components.LocationsComponent;
import org.admu.lostandfound.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Path("/api")
public class LocationController {


	@Autowired
	LocationsComponent locationsComponent;

	@GET
	@Path("/location")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Location getLocation(@QueryParam("buildingName") String buildingName,
								@QueryParam("roomName") String roomName) {
		Location location = locationsComponent.getLocation(buildingName, roomName);
		return location;
	}
	
	@POST
	@Path("/location")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Location newLocation(Location locationReq) {

		Location location = locationsComponent.postLocation(
				locationReq.getTitle(),
				locationReq.getBuildingName(),
				locationReq.getRoomName(),
				locationReq.getDescription()
		);
		return location;
	}
	
	@PUT
	@Path("/location/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Location getLocationById(@PathParam("id") Long id, Location locationReq) {

		Location location = locationsComponent.putLocation(id,locationReq.getBuildingName(), locationReq.getRoomName());
		return location;

	}
	
	@DELETE
	@Path("/location/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Location deleteLocationById(@PathParam("id") Long id) {
		Location location = locationsComponent.deleteLocation(id);
		return location;
	}
}
