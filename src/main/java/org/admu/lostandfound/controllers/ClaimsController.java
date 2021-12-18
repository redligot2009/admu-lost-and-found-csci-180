package org.admu.lostandfound.controllers;

import org.admu.lostandfound.components.ClaimsComponent;
import org.admu.lostandfound.models.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Component
@Path("/Claims")
public class ClaimsController
{
    @Autowired
    ClaimsComponent claimsComp;


    @GET
    @Path("/claiming")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClaims(@QueryParam("item_id") Long item,
                              @QueryParam("user_id") Long claimer)
    {
        try{
            List<Claim> claim = claimsComp.retrieveClaims(item, claimer);
            return Response.ok(claim).build();
        }catch (Exception e){
            return Response.status(400)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/claims/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteClaims(@PathParam("id") Long claimID)
    {
        try {
            return Response.ok(claimsComp.closeClaim(claimID)).build();
        }catch (Exception e){
            return Response.status(400)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
	@Path("/claims")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response postClaim(Map<String, Object> body)
    {
        try {
            Claim newClaim = claimsComp.createNewClaim(body);
            return Response.ok(newClaim).build();
        }catch (Exception e){
            return Response.status(400)
                    .entity(e.getMessage())
                    .build();
        }
	}

	// Need authentication for this. No exceptions yet.
    @GET
    @Path("/my_claims")
    @Produces(MediaType.APPLICATION_JSON)
    public String myClaims(@QueryParam("item_id") Long item)
    {
        return "my claims";
    }

}
