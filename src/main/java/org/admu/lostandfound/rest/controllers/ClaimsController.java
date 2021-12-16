package org.admu.lostandfound.rest.controllers;

import org.admu.lostandfound.components.ClaimsComponent;
import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

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
        return "JSON";
    }


    @DELETE
    @Path("/claims/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Claim deleteClaimById(@PathParam("id") Long claimID) {
        Claim claim = claimsComp.closeClaim(claimID);
        return claim;
    }


//    @POST
//    @Path("/claims")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Claim newClaim(@RequestBody Map<String,Object> body
//    ) {
//        return claimsComp.newClaim(body);
//    }

    @GET
    @Path("/my_claims")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOngoingClaims(@QueryParam("item_id") Long item)
    {
        return "JSON of Ongoing Claims";
    }

}
