package org.admu.lostandfound.components;

import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.models.LostItem;
import org.admu.lostandfound.models.User;
import org.admu.lostandfound.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Component
public class ClaimsComponent 
{
	
	// Repositories @Autowire here
	@Autowired
	private ClaimRepository claimRepo;

	
	public Claim createNewClaim(User claimer, LostItem lostItem)
	{
		return null;
	}
	
	public Claim closeClaim(int claimID)
	{
		return null;
	}

	public Claim retrieveClaimByID(int claimID)
	{
		return null;
	}
	
	public Claim[] retrieveClaimsByClaimer(User claimer)
	{
		return null;
	}
	
	public Claim[] retrieveClaimsByItem(LostItem item)
	{
		return null;
	}

//	public Claim newClaim(Map<String, Object> body) {
//
//		// Dates - default is now
//		LocalDate created_date = LocalDate.now();
//		System.out.println(created_date);
//
//		// Foreign key
//		Long claimer_id = Long.valueOf(body.get("locationId").toString());
//
//		Long lost_item = Long.valueOf(body.get("lost item").toString());
//
//		// Save new lost item
//		Claim Claim = new Claim(claimer_id, lost_item, created_date);
//		claimRepo.save(lostItem);
//		return lostItem;
//	}

}
