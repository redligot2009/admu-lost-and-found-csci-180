package org.admu.lostandfound.controllers;

import org.admu.lostandfound.components.ClaimsComponent;
import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/Claims")
public class ClaimsController
{
    @Autowired
    ClaimsComponent claimsComp;

    @Autowired
    ClaimRepository claimRepo;

    @GET
    @Path("/claiming")
    @Produces(MediaType.APPLICATION_JSON)
    public String getClaims(@QueryParam("item_id") Long item,
                            @QueryParam("user_id") Long claimer)
    {
        return "Here's your claim";
        // Doesn't account for all Cases yet
    }

    @DELETE
    @Path("/claims/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Claim deleteClaims(@PathParam("id") Long claimID)
    {
        return claimsComp.closeClaim(claimID);
    }

    @POST
	@Path("/claims")
	public String postClaim()
    {
		return "Claim posted";
	}

    @GET
    @Path("/my_claims")
    @Produces(MediaType.APPLICATION_JSON)
    public String myClaims(@QueryParam("item_id") Long item)
    {
        return "my claims";
    }

}
