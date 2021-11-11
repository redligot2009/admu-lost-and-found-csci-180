package org.admu.lostandfound.components;

import java.util.Date;

import org.admu.lostandfound.models.Category;
import org.admu.lostandfound.models.User;
import org.springframework.stereotype.Component;

@Component
public class CategoryComponent 
{

	// Repositories @Autowire here
	
	
	public Category createNewCategory(String title, User admin, Date createdDate)
	{
		return null;
	}
	
}
