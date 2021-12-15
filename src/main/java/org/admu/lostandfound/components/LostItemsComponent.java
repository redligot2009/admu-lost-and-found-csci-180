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
	private LostItemRepository lostItemRepository;


	public LostItem[] retrieveByCategory(Category category)
	{
		return null;
	}

	public LostItem newLostItem(Map<String, Object> body) {

		// String attributes
		String title = body.get("title").toString();
		String description = body.get("description").toString();
		String itemStatus = body.get("itemStatus").toString();

		// Dates - default is now
		LocalDate date = LocalDate.now();
		System.out.println(date);
		LocalTime time = LocalTime.now();

		// Foreign key
		Long locationId = Long.valueOf(body.get("locationId").toString());
		Location location = locationRepo.findById(locationId).get();

		// Save new lost item
		LostItem lostItem = new LostItem(title, description, itemStatus, date, time, location);
		lostItemRepository.save(lostItem);
		return lostItem;
	}
}
