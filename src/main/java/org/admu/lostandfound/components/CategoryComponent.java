package org.admu.lostandfound.components;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.admu.lostandfound.models.Category;
import org.admu.lostandfound.models.User;
import org.admu.lostandfound.repositories.CategoryRepository;
import org.admu.lostandfound.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryComponent 
{

	// Repositories @Autowire here
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	public Category getCategory(String title){

		Category foundCategory = categoryRepository.findByTitle(title);
		return foundCategory;
	}

	public Category createNewCategory(Map<String, Object> body, LocalDate createdDate)
	{
		System.out.print("here");
		Long admin = Long.parseLong(body.get("admin").toString());
		String title = body.get("title").toString();

		Optional<User> foundAdmin = userRepository.findById(admin);

		//Makes sure admin has a valid user id
		if(foundAdmin.isPresent()){
			Category newCategory = new Category();
			newCategory.setTitle(title);
			newCategory.setAdmin(foundAdmin.get());
			newCategory.setCreatedDate(createdDate);
			newCategory.setUpdatedDate(createdDate);
			newCategory = categoryRepository.save(newCategory);
			return newCategory;
		}

		return null;
	}

	public Category putCategory(Long id, Map<String, Object> body){

		System.out.print("SAVED");
		Long admin = Long.parseLong(body.get("admin").toString());
		String title = body.get("title").toString();

		Optional<Category> savedCategory = categoryRepository.findById(id);
		Optional<User> savedAdmin = userRepository.findById(admin);

		if(savedCategory.isPresent()){
			System.out.print("SAVED");
			Category updateCategory = savedCategory.get();
			updateCategory.setTitle(title);
			updateCategory.setAdmin(savedAdmin.get());
			updateCategory.setUpdatedDate(LocalDate.now());
			updateCategory = categoryRepository.save(updateCategory);
			return updateCategory;
		}

		return null;
	}

	public Category deleteCategory(Long id){

		Optional<Category> category = categoryRepository.findById(id);

		if(category.isPresent()){
			Category deletedCategory = category.get();
			categoryRepository.deleteById(id);
			return deletedCategory;
		}

		return null;
	}

}
