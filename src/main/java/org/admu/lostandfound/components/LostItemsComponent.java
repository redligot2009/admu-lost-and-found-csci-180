package org.admu.lostandfound.components;

import org.admu.lostandfound.models.Category;
import org.admu.lostandfound.models.Location;
import org.admu.lostandfound.models.LostItem;
import org.admu.lostandfound.repositories.CategoryRepository;
import org.admu.lostandfound.repositories.LocationRepository;
import org.admu.lostandfound.repositories.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;


@Component
public class LostItemsComponent 
{
	@Autowired
	private CategoryComponent categoryComponent;

	@Autowired
	private LocationRepository locationRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private LostItemRepository lostItemRepo;


	public LostItem[] retrieveByCategory(Category category)
	{
		return null;
	}

	public LostItem newLostItem(Map<String, Object> body) {

		// Get foreign key objects
		Location location = null; // null by default
		if (body.get("locationId") != null) {
			Long locationId = Long.valueOf(body.get("locationId").toString());
			Optional<Location> foundLocation = locationRepo.findById(locationId);
			if (foundLocation.isPresent()) {
				location = foundLocation.get();
			}
		}

		Category category = null; // null by default
		if (body.get("categoryId") != null) {
			Long categoryId = Long.valueOf(body.get("categoryId").toString());
			Optional<Category> foundCategory = categoryRepo.findById(categoryId);
			if (foundCategory.isPresent()) {
				category = foundCategory.get();
			}
		}

		// Save new lost item
		LostItem lostItem = new LostItem(
				body.get("title").toString(),
				body.get("description").toString(),
				body.get("itemStatus").toString(),
				LocalDate.now(),
				LocalTime.now(),
				location,
				category
		);
		lostItemRepo.save(lostItem);
		return lostItem;
	}
	public LostItem getLostItemByID(Long id) {
		Optional<LostItem> lostItem = lostItemRepo.findById(id);
		if (lostItem.isPresent()) {
			return lostItem.get();
		}
		return null;
	}
	public LostItem updateLostItemByID(Long id, Map<String, Object> body) {

		// Get foreign keys objects
		Location location = null; // null by default
		if (body.get("locationId") != null) {
			Long locationId = Long.valueOf(body.get("locationId").toString());
			Optional<Location> foundLocation = locationRepo.findById(locationId);
			if (foundLocation.isPresent()) {
				location = foundLocation.get();
			}
		}

		Category category = null; // null by default
		if (body.get("categoryId") != null) {
			Long categoryId = Long.valueOf(body.get("categoryId").toString());
			Optional<Category> foundCategory = categoryRepo.findById(categoryId);
			if (foundCategory.isPresent()) {
				category = foundCategory.get();
			}
		}

		// Get existing row
		Optional<LostItem> foundLostItem = lostItemRepo.findById(id);
		LostItem lostItem;

		if (foundLostItem.isPresent()) {
			lostItem = foundLostItem.get();
		} else {
			return null; // just return null if it doesn't exist
		}

		// set new fields
		lostItem.setTitle(body.get("title").toString());
		lostItem.setDescription(body.get("description").toString());
		lostItem.setItemStatus(body.get("itemStatus").toString());
		lostItem.setDate(LocalDate.now());
		lostItem.setTime(LocalTime.now());
		lostItem.setLocation(location);
		lostItem.setCategory(category);

		// save and return
		LostItem savedItem = lostItemRepo.save(lostItem);
		return savedItem;
	}
}
