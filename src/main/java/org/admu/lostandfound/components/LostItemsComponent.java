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

//	public LostItem[] getLostItems(
//			String title,
//			String description,
//			String itemStatus,
//			Date date,
//			Integer categoryId,
//			Integer locationId
//	) {
//
//	}
	public LostItem newLostItem(Map<String, Object> body) {

		// String attributes
		String title = body.get("title").toString();
		String description = body.get("description").toString();
		String itemStatus = body.get("itemStatus").toString();

		// Dates - default is now
		LocalDate date = LocalDate.now();
		System.out.println(date);
		LocalTime time = LocalTime.now();

		// Foreign keys
		Long locationId = Long.valueOf(body.get("locationId").toString());
		Optional<Location> foundLocation = locationRepo.findById(locationId);
		Location location = null;
		if (foundLocation.isPresent()) {
			location = foundLocation.get();
		}

		Long categoryId = Long.valueOf(body.get("categoryId").toString());
		Optional<Category> foundCategory = categoryRepo.findById(categoryId);
		Category category = null;
		if (foundCategory.isPresent()) {
			category = foundCategory.get();
		}

		// Save new lost item
		LostItem lostItem = new LostItem(title, description, itemStatus, date, time, location, category);
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

//	public String updateLostItemByID(Long id, Map<String, Object> body) {
//
//	}
}
