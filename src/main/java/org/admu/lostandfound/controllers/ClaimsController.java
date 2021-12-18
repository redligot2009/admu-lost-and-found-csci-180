package org.admu.lostandfound.controllers;

import org.admu.lostandfound.components.ClaimsComponent;
import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.models.User;
import org.admu.lostandfound.models.UserDetailsImpl;
import org.admu.lostandfound.payload.ClaimResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Path("/api/")
public class ClaimsController
{
    @Autowired
    ClaimsComponent claimsComp;

    @GET
    @Path("/claims/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClaims(@QueryParam("item_id") Long item,
                              @QueryParam("user_id") Long claimer)
    {
        try{
            if(item != null || claimer != null) {
                List<Claim> claims = claimsComp.retrieveClaims(claimer, item);
                List<ClaimResponse> finalClaimsResponse = new ArrayList<>();
                for(Claim currentClaim : claims)
                {
                    User currentClaimer = currentClaim.getClaimer();
                    finalClaimsResponse.add(new ClaimResponse(currentClaim.getId(),
                            currentClaimer.getUsername(),
                            currentClaimer.getId(),
                            currentClaim.getLostItem()));
                }
                return Response.ok(finalClaimsResponse).build();
            }
            else
            {
                List<Claim> allClaims = claimsComp.retrieveClaims(null, null);
                List<ClaimResponse> finalClaimsResponse = new ArrayList<>();
                for(Claim currentClaim : allClaims)
                {
                    User currentClaimer = currentClaim.getClaimer();
                    finalClaimsResponse.add(new ClaimResponse(currentClaim.getId(),
                            currentClaimer.getUsername(),
                            currentClaimer.getId(),
                            currentClaim.getLostItem()));
                }
                return Response.ok(finalClaimsResponse).build();
            }
        }
        catch (Exception e){
            return Response.status(400)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/claims/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClaimById(@PathParam("id") Long claimID)
    {
        try {
            Claim currentClaim = claimsComp.retrieveClaimByID(claimID);
            User claimer = currentClaim.getClaimer();
            return Response.ok(new ClaimResponse(currentClaim.getId(),
                                                claimer.getUsername(),
                                                claimer.getId(),
                                                currentClaim.getLostItem()))
                                        .build();
        }
        catch (Exception e){
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
            Claim currentClaim = claimsComp.closeClaim(claimID);
            User claimer = currentClaim.getClaimer();
            return Response.ok(new ClaimResponse(currentClaim.getId(),
                                            claimer.getUsername(),
                                            claimer.getId(),
                                            currentClaim.getLostItem()))
                                    .build();
        }
        catch (Exception e){
            return Response.status(400)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
	@Path("/claims")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('USER')")
	public Response postClaim(Map<String, Object> body)
    {
        try {
            Claim newClaim = claimsComp.createNewClaim(body);
            User claimer = newClaim.getClaimer();
            return Response.ok(new ClaimResponse(newClaim.getId(),
                            claimer.getUsername(),
                            claimer.getId(),
                            newClaim.getLostItem()))
                    .build();
        }
        catch (Exception e){
            return Response.status(400)
                    .entity(e.getMessage())
                    .build();
        }
	}

    @GET
    @Path("/my_claims")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('USER')")
    public Response getMyClaims(@QueryParam("item_id") Long item)
    {
        try{
            UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("Current User ID: " + currentUser.getId().toString());
            List<Claim> myClaims = claimsComp.retrieveClaims(currentUser.getId(),item);
            List<ClaimResponse> finalClaimsResponse = new ArrayList<>();
            for(Claim currentClaim : myClaims)
            {
                User currentClaimer = currentClaim.getClaimer();
                finalClaimsResponse.add(new ClaimResponse(currentClaim.getId(),
                        currentClaimer.getUsername(),
                        currentClaimer.getId(),
                        currentClaim.getLostItem()));
            }
            return Response.ok(finalClaimsResponse).build();
        }
        catch (Exception e){
            return Response.status(400)
                    .entity(e.getMessage())
                    .build();
        }
    }

}
