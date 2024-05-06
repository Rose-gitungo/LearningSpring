package com.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csm.model.Recipe;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Integer> {

}
