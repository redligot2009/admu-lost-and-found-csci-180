package org.admu.lostandfound.components;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.admu.lostandfound.models.Category;
import org.admu.lostandfound.models.User;
import org.admu.lostandfound.models.UserDetailsImpl;
import org.admu.lostandfound.repositories.CategoryRepository;
import org.admu.lostandfound.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CategoryComponent 
{

	// Repositories @Autowire here
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	public List<Category> getCategory(String title) throws RuntimeException{

		if(title==null){
			List<Category> foundCategories = categoryRepository.findAll();
			return foundCategories;
		}

		List<Category> foundCategories = new ArrayList<>();
		Category foundCategory = categoryRepository.findByTitle(title);
		foundCategories.add(foundCategory);

		if(foundCategory==null){
			throw new RuntimeException("No category found with that title");
		}

		return foundCategories;

	}

	public Category createNewCategory(Map<String, Object> body, LocalDate createdDate)
	{
		try{
			UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			String title = body.get("title").toString();

			Optional<User> foundAdmin = userRepository.findById(currentUser.getId());

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

			throw new RuntimeException("You are not a valid admin user");
		} catch(Exception e){
			throw new RuntimeException("Bad Request");
		}

	}

	public Category putCategory(Long id, Map<String, Object> body) throws RuntimeException{

		UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String title = body.get("title").toString();

		Optional<Category> savedCategory = categoryRepository.findById(id);
		Optional<User> savedAdmin = userRepository.findById(currentUser.getId());

		if(!savedAdmin.isPresent()){
			throw new RuntimeException("You are not a valid admin user");
		}

		if(savedCategory.isPresent()){
			Category updateCategory = savedCategory.get();
			updateCategory.setTitle(title);
			updateCategory.setAdmin(savedAdmin.get());
			updateCategory.setUpdatedDate(LocalDate.now());
			updateCategory = categoryRepository.save(updateCategory);
			return updateCategory;
		} else {
			throw new RuntimeException("Category Not Found");
		}

	}

	public Category deleteCategory(Long id) throws RuntimeException{

		Optional<Category> category = categoryRepository.findById(id);

		if(category.isPresent()){
			Category deletedCategory = category.get();
			categoryRepository.deleteById(id);
			return deletedCategory;
		}

		throw new RuntimeException("Category not found. No categories deleted.");
	}

}
