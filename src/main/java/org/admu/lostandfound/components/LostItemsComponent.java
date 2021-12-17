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
import java.util.List;
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

	// GET All
	public List<LostItem> getLostItems(
			String title,
			String itemStatus,
			String date,
			Long categoryId,
			Long locationId
	) throws RuntimeException {

		// Get Date
		LocalDate dt = null;
		if (date != null) {
			dt = LocalDate.parse(date);
		}

		// Get Category
		Category category = null;
		if (categoryId != null) {
			Optional<Category> categoryFound = categoryRepo.findById(categoryId);
			if (!categoryFound.isPresent()) {
				throw new RuntimeException("Invalid Category ID");
			}
			category = categoryFound.get();
		}

		// Get Location
		Location location = null;
		if (locationId != null) {
			Optional<Location> locationFound = locationRepo.findById(locationId);
			if (!locationFound.isPresent()) {
				throw new RuntimeException("Invalid Location ID");
			}
			location = locationFound.get();
		}

		return lostItemRepo.findByTitleAndItemStatusAndDateAndCategoryAndLocation(title, itemStatus, dt, category, location);
	}

	// CREATE
	public LostItem newLostItem(Map<String, Object> body) throws RuntimeException {

		// Check if there are empty non-null fields
		if (
				body.get("title") == null ||
				body.get("description") == null ||
				body.get("itemStatus") == null
		) {
			throw new RuntimeException("Title, description, and status are required");
		}

		// Get foreign key objects
		Location location = null; // null by default
		if (body.get("locationId") != null) {
			Long locationId = Long.valueOf(body.get("locationId").toString());
			Optional<Location> foundLocation = locationRepo.findById(locationId);
			if (!foundLocation.isPresent()) {
				throw new RuntimeException("Invalid Location ID");
			}
			location = foundLocation.get();
		}

		Category category = null; // null by default
		if (body.get("categoryId") != null) {
			Long categoryId = Long.valueOf(body.get("categoryId").toString());
			Optional<Category> foundCategory = categoryRepo.findById(categoryId);
			if (!foundCategory.isPresent()) {
				throw new RuntimeException("Invalid Category ID");
			}
			category = foundCategory.get();
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

	// GET One
	public LostItem getLostItemByID(Long id) throws RuntimeException {
		Optional<LostItem> lostItem = lostItemRepo.findById(id);
		if (!lostItem.isPresent()) {
			throw new RuntimeException("No LostItem Found");
		}
		return lostItem.get();
	}

	// PUT
	public LostItem updateLostItemByID(Long id, Map<String, Object> body) throws RuntimeException {

		// Check if there are empty non-null fields
		if (
				body.get("title") == null ||
				body.get("description") == null ||
				body.get("itemStatus") == null
		) {
			throw new RuntimeException("Title, description, and status are required");
		}

		// Get foreign keys objects
		Location location = null; // null by default
		if (body.get("locationId") != null) {
			Long locationId = Long.valueOf(body.get("locationId").toString());
			Optional<Location> foundLocation = locationRepo.findById(locationId);
			if (!foundLocation.isPresent()) {
				throw new RuntimeException("Invalid Location ID");
			}
			location = foundLocation.get();
		}

		Category category = null; // null by default
		if (body.get("categoryId") != null) {
			Long categoryId = Long.valueOf(body.get("categoryId").toString());
			Optional<Category> foundCategory = categoryRepo.findById(categoryId);
			if (!foundCategory.isPresent()) {
				throw new RuntimeException("Invalid Category ID");
			}
			category = foundCategory.get();
		}

		// Get existing item
		Optional<LostItem> foundLostItem = lostItemRepo.findById(id);
		if (!foundLostItem.isPresent()) {
			throw new RuntimeException("LostItem not found");
		}
		LostItem lostItem = foundLostItem.get();

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

	// DELETE
	public LostItem deleteLostItemByID(Long id) throws RuntimeException {
		LostItem lostItem = getLostItemByID(id);
		if (lostItem == null) {
			throw new RuntimeException("No LostItem Found");
		}
		lostItemRepo.delete(lostItem);
		return lostItem;
	}
}
