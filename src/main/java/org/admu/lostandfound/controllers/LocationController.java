package org.admu.lostandfound.controllers;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.admu.lostandfound.components.LocationsComponent;
import org.admu.lostandfound.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Path("/api")
public class LocationController {


	@Autowired
	LocationsComponent locationsComponent;

	@GET
	@Path("/location")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocation(@QueryParam("buildingName") String buildingName,
								@QueryParam("roomName") String roomName) {

		try{
			List<Location> location = locationsComponent.getLocation(buildingName, roomName);
			return Response.ok(location).build();
		}catch (Exception e){
			return Response.status(404)
					.entity(e.getMessage())
					.build();
		}

	}
	
	@POST
	@Path("/location")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newLocation(Location locationReq) {

		try{
			Location location = locationsComponent.postLocation(
					locationReq.getTitle(),
					locationReq.getBuildingName(),
					locationReq.getRoomName(),
					locationReq.getDescription()
			);
			return Response.ok(location).build();
		} catch(Exception e){
			return Response.status(400).entity(e.getMessage()).build();
		}

	}
	
	@PUT
	@Path("/location/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocationById(@PathParam("id") Long id, Location locationReq) {


		try {
			Location location = locationsComponent.putLocation(id,locationReq.getBuildingName(), locationReq.getRoomName());
			return Response.ok(location).build();
		} catch (Exception e){
			return Response.status(404)
					.entity(e.getMessage())
					.build();
		}


	}
	
	@DELETE
	@Path("/location/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteLocationById(@PathParam("id") Long id) {

		try{
			Location location = locationsComponent.deleteLocation(id);
			return Response.ok(location).build();
		} catch (Exception e){
			return Response.status(404)
					.entity(e.getMessage())
					.build();
		}

	}
}
