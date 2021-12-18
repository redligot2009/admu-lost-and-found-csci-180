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
		Long item = Long.parseLong(body.get("item_id").toString());
		Long claimer = Long.parseLong(body.get("claimer_id").toString());
		LocalDate createdDate = LocalDate.now();

		Optional<User> User = userRepo.findById(claimer);
		Optional<LostItem> Item = lostItemRepo.findById(item);

		Claim claim = new Claim(
				User.get(),
				Item.get(),
				createdDate
		);
		claimRepo.save(claim);
		return claim;
	}
	
	public Claim closeClaim(Long claimID) throws RuntimeException
	{
		Claim Claim = retrieveClaimByID(claimID);
		if (Claim != null) {
			claimRepo.delete(Claim);
			return Claim;
		}
		throw new RuntimeException("Bad Request");
	}

	public Claim retrieveClaimByID(long claimID) throws RuntimeException
	{
		Optional<Claim> Claim = claimRepo.findById(claimID);
		if (Claim.isPresent())
		{
			return Claim.get();
		}
		throw new RuntimeException("Bad Request");
	}
	
	public List<Claim> retrieveClaims(Long claimer, Long item) throws RuntimeException
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
		throw new RuntimeException("Bad Request");
	}


// Darren's Notes: Exceptions
	// If u see null, change to throw
	// if you don't see a null or return null, just do try catch
}
