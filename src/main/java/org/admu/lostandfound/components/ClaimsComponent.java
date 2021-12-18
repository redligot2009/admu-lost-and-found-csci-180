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

	
	public Claim createNewClaim(Map<String, Object> body)
	{
		Long itemId = Long.parseLong(body.get("item_id").toString());
		Long claimerId = Long.parseLong(body.get("claimer_id").toString());
		LocalDate createdDate = LocalDate.now();

		Optional<User> user = userRepo.findById(claimerId);
		Optional<LostItem> lostItem = lostItemRepo.findById(itemId);

		Claim claim = new Claim(
				user.get(),
				lostItem.get(),
				createdDate
		);
		claimRepo.save(claim);
		return claim;
	}
	
	public Claim closeClaim(Long claimID) throws RuntimeException
	{
		Claim claim = retrieveClaimByID(claimID);
		if (claim != null) {
			claimRepo.delete(claim);
			return claim;
		}
		throw new RuntimeException("Bad Request");
	}

	public Claim retrieveClaimByID(long claimID) throws RuntimeException
	{
		Optional<Claim> claim = claimRepo.findById(claimID);
		if (claim.isPresent())
		{
			return claim.get();
		}
		throw new RuntimeException("Bad Request");
	}
	
	public List<Claim> retrieveClaims(Long claimerId, Long itemId) throws RuntimeException
	{
		Optional<LostItem> lostItem = lostItemRepo.findById(itemId);
		Optional<User> user = userRepo.findById(claimerId);

		// Case 1: Only item
		if ((itemId != null) && (claimerId == null))
		{
			List<Claim> claim = claimRepo.findByLostItem(lostItem.get());
			if (claim.size() > 0)
			{
				return claim;
			}
		}

		// Case 2: Only claimer
		else if ((itemId == null) && (claimerId != null))
		{
			List<Claim> claim = claimRepo.findByClaimer(user.get());
			if (claim.size() > 0)
			{
				return claim;
			}
		}

		// Case 3: Both passed
		else if ((itemId != null) && (claimerId != null))
		{
			List<Claim> claim = claimRepo.findByClaimerAndLostItem(user.get(), lostItem.get());
			if (claim.size() == 1)
			{
				return claim;
			}
		}
		throw new RuntimeException("Bad Request");
	}


// Darren's Notes: Exceptions
	// If u see null, change to throw
	// if you don't see a null or return null, just do try catch
}
