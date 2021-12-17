package org.admu.lostandfound.components;

import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.models.LostItem;
import org.admu.lostandfound.models.User;
import org.admu.lostandfound.repositories.ClaimRepository;
import org.admu.lostandfound.repositories.LostItemRepository;
import org.admu.lostandfound.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ClaimsComponent 
{
	
	// Repositories @Autowire here
	@Autowired
	ClaimRepository claimRepo;

	@Autowired
	LostItemRepository lostItemRepo;

	@Autowired
	UserRepository userRepo;

	
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
	
	public List<Claim> retrieveClaims(Long claimer, Long item)
	{
		Optional<LostItem> LostItem = lostItemRepo.findById(item);
		Optional<User> User = userRepo.findById(claimer);

		// Case 1: Only item
		if ((item != null) && (claimer == null))
		{
			List<Claim> claim = claimRepo.findByLostItem(LostItem.get());
			if (claim.size() > 0)
			{
				return claim;
			}
		}

		// Case 2: Only claimer
		else if ((item == null) && (claimer != null))
		{
			List<Claim> claim = claimRepo.findByClaimer(User.get());
			if (claim.size() > 0)
			{
				return claim;
			}
		}

		// Case 3: Both passed
		else if ((item != null) && (claimer != null))
		{
			List<Claim> claim = claimRepo.findByClaimerAndLostItem(User.get(), LostItem.get());
			if (claim.size() == 1)
			{
				return claim;
			}
		}

		return null;
	}
	
//	public Claim[] retrieveClaimsByItem(LostItem item)
//	{
//		return null;
//	}


}
