package com.csm.service;

import java.util.List;

import com.csm.model.Recipe;
import com.csm.model.User;

public interface RecipeService {

	public Recipe createRecipe(Recipe recipe);
	public Recipe findRecipeById(Integer id) throws Exception;
	public List<Recipe> getAllRecipe();
	public String deleteRecipe(Integer id);
	public Recipe updateRecipe(Recipe recipe,Integer id) throws Exception;
	public Recipe likeRecipe(Integer id,User user) throws Exception;
	
}
