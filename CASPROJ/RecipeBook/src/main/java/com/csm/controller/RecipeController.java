package com.csm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csm.model.Recipe;
import com.csm.model.User;
import com.csm.service.RecipeService;
import com.csm.service.UserService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
	
	@Autowired
	RecipeService recipeService;
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public List<Recipe> getAllRecipes() {
		return recipeService.getAllRecipe();
	}
	
	@PostMapping("/")
	public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt ) throws Exception {
		User user =userService.findUserByJwt(jwt);
		recipe.setUser(user);
		System.out.println(recipe);
		return recipeService.createRecipe(recipe);
	}
	@GetMapping("/{id}")
	public Recipe getRecipeById(@PathVariable("id") Integer id) {
		Recipe recipe = null;
		try {
			recipe = recipeService.findRecipeById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recipe;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRecipe(@PathVariable Integer id) {
		recipeService.deleteRecipe(id);
		return ResponseEntity.ok().body("{\"message\": \"Recipe Deleted Successfully!\"}");
	}
	@PutMapping("/{id}")
	public Recipe updateRecipe(@RequestBody  Recipe recipe,@PathVariable Integer id) throws Exception {
		try {
			return recipeService.updateRecipe(recipe, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new Exception();
	}
	
//	@PutMapping("/{id}/like")
//	public Recipe likeRecipe(@RequestBody Recipe recipe , @RequestHeader("Authorization") String jwt ) throws Exception {
//		User user =userService.findUserByJwt(jwt);
//		Recipe updatedRecipe = recipeService.likeRecipe(recipe.getId(), user);
//		return updatedRecipe;
//	}
	
	
}
