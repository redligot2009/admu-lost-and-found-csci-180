package org.admu.lostandfound.components;

import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.models.LostItem;
import org.admu.lostandfound.models.User;
import org.admu.lostandfound.repositories.ClaimRepository;
import org.admu.lostandfound.repositories.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

@Component
public class ClaimsComponent 
{
	
	// Repositories @Autowire here
	@Autowired
	private ClaimRepository claimRepo;

	private LostItemRepository lostItemRepo;

	
	public Claim createNewClaim(User claimer, LostItem lostItem)
	{
		return null;
	}
	
	public Claim closeClaim(Long claimID)
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
		Optional<Claim> Claim = claimRepo.findById(claimID);
		if (Claim.isPresent())
		{
			return Claim.get();
		}
		return null;
	}
	
	public Claim[] retrieveClaimsByClaimer(User claimer)
	{
//		Optional List<Claim>getList Claim = claimRepo.findById(claimer);
//		if (Claim.isPresent())
//		{
//			return Claim.get();
//		}
		return null;
	}
	
	public Claim[] retrieveClaimsByItem(LostItem item)
	{
		return null;
	}


}
