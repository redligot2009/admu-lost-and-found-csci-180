package org.admu.lostandfound.components;

import org.admu.lostandfound.models.Category;
import org.admu.lostandfound.models.LostItem;
import org.springframework.stereotype.Component;

@Component
public class LostItemsComponent 
{

	// Repositories @Autowire here
	
	
	public LostItem[] retrieveByCategory(Category category)
	{
		return null;
	}
	
}
