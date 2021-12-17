package org.admu.lostandfound.controllers;

import org.admu.lostandfound.components.ClaimsComponent;
import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

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
    public List<Claim> getClaims(@QueryParam("item_id") Long item,
                                 @QueryParam("user_id") Long claimer)
    {
        List<Claim> claim = claimsComp.retrieveClaims(item, claimer);
        return claim;
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
