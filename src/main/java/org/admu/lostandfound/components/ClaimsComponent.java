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
	
	public Claim closeClaim(long claimID)
	{
		Claim Claim = retrieveClaimByID(claimID);
		if (Claim != null) {
			claimRepo.delete(Claim);
			return Claim;
		}
		return null;
	}

	public Claim retrieveClaimByID(long claimID)
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


}
