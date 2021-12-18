package org.admu.lostandfound.components;

import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.models.LostItem;
import org.admu.lostandfound.models.User;
import org.admu.lostandfound.models.UserDetailsImpl;
import org.admu.lostandfound.repositories.ClaimRepository;
import org.admu.lostandfound.repositories.LostItemRepository;
import org.admu.lostandfound.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

		UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long claimerId = currentUser.getId();

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
		Claim claim = claimRepo.findById(claimID).get();
		if (claim != null) {

			claimRepo.deleteById(claimID);
			LostItem parentLostItem = claim.getLostItem();
			parentLostItem.getClaims().remove(claim);
			lostItemRepo.save(parentLostItem);
			System.out.println("Claim " + claimID.toString() + " successfully deleted");
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
		else
		{
			throw new RuntimeException("No claim with given ID");
		}
	}
	
	public List<Claim> retrieveClaims(Long claimerId, Long itemId) throws RuntimeException
	{
		Optional<User> user = null;
		Optional<LostItem> lostItem = null;

		/* Debug logs
		if(itemId != null) {
			System.out.println("Item ID: " + itemId.toString());
			lostItem = lostItemRepo.findById(itemId);
		}
		if(claimerId != null) {
			System.out.println("Claimer ID: " + claimerId.toString());
			user = userRepo.findById(claimerId);
		}
		*/

		// Case 1: Only item
		if ((lostItem != null) && (user == null))
		{
			//System.out.println("Case 1: Only item");
			List<Claim> claim = claimRepo.findByLostItem(lostItem.get());
			if (claim.size() > 0)
			{
				return claim;
			}
		}
		// Case 2: Only claimer
		else if ((itemId == null) && (claimerId != null))
		{
			//System.out.println("Case 2: Only claimer");
			List<Claim> claim = claimRepo.findByClaimer(user.get());
			if (claim.size() > 0)
			{
				return claim;
			}
			else
			{
				throw new RuntimeException("No item was found for the given claimer");
			}
		}
		// Case 3: Both passed
		else if ((itemId != null) && (claimerId != null))
		{
			//System.out.println("Case 3: Both passed");
			List<Claim> claim = claimRepo.findByClaimerAndLostItem(user.get(), lostItem.get());
			if (claim.size() == 1)
			{
				return claim;
			}
		}
		// Case 4: None passed for both -- retrieve all claims
		else
		{
			//System.out.println("Case 4: None passed for both -- retrieve all claims");
			List<Claim> allClaims = claimRepo.findAll();
			return allClaims;
		}
		throw new RuntimeException("Bad Request");
	}


// Darren's Notes: Exceptions
	// If u see null, change to throw
	// if you don't see a null or return null, just do try catch
}
