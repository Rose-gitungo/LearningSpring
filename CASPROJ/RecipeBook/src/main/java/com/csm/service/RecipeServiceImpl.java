package com.csm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csm.model.Recipe;
import com.csm.model.User;
import com.csm.repository.RecipeRepo;
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeRepo recipeRepo;
	
	@Autowired
	UserService userService;
	
	@Override
	public Recipe findRecipeById(Integer id)throws Exception{
			Optional<Recipe> r = recipeRepo.findById(id);
			if (r.isPresent()) {
				return r.get();
			}
	throw new Exception("recipe not found with id: "+id);
	}

	@Override
	public List<Recipe> getAllRecipe() {
		return recipeRepo.findAll();
	}

	@Override
	public String deleteRecipe(Integer id) {
		recipeRepo.deleteById(id);
		return "Recipe Delete Successfully!";
	}

	@Override
	public Recipe createRecipe(Recipe recipe) {
		recipe.setCreatedAt(LocalDateTime.now());
		User u= new User();
		try {
			
			u= userService.findUserById(recipe.getUser().getUid());
			recipe.setUser(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recipeRepo.save(recipe);
	}

	@Override
	public Recipe updateRecipe(Recipe recipe, Integer id) throws Exception {
	Recipe olderecipe= findRecipeById(id);
	if (recipe!=null) {
		olderecipe.setTitle(recipe.getTitle());
		olderecipe.setSubtitle(recipe.getSubtitle());
		olderecipe.setImage(recipe.getImage());
		olderecipe.setDescription(recipe.getDescription());
		olderecipe.setVegeterian(recipe.isVegeterian());
	}
		return recipeRepo.save(olderecipe);
	}

	@Override
	public Recipe likeRecipe(Integer id, User user) throws Exception {
		Recipe recipe=findRecipeById(id);
		if (recipe.getLikes().contains(user.getUid())){ 
			recipe.getLikes().remove(user.getUid());
		}else {
			recipe.getLikes().add((long)user.getUid());
		}
		return recipe;
	}

}
