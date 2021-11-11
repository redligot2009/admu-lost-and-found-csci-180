package org.admu.lostandfound.components;

import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.models.LostItem;
import org.admu.lostandfound.models.User;
import org.springframework.stereotype.Component;

@Component
public class ClaimsComponent 
{
	
	// Repositories @Autowire here
	
	
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
	
}
