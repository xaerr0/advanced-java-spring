package platform.codingnomads.co.springsecurity.recipeapi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import platform.codingnomads.co.springsecurity.recipeapi.exceptions.NoSuchRecipeException;
import platform.codingnomads.co.springsecurity.recipeapi.models.Recipe;
import platform.codingnomads.co.springsecurity.recipeapi.services.RecipeService;

import java.security.Principal;
import java.util.ArrayList;


@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping
    public ResponseEntity<?> createNewRecipe(@RequestBody Recipe recipe, Principal principal) {
        try {
            Recipe insertedRecipe = recipeService.createNewRecipe(recipe);
            return ResponseEntity.created(insertedRecipe.getLocationURI()).body(insertedRecipe);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable("id") Long id) {
        try {
            Recipe recipe = recipeService.getRecipeById(id);
            return ResponseEntity.ok(recipe);
        } catch (NoSuchRecipeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRecipes() {
        try {
            return ResponseEntity.ok(recipeService.getAllRecipes());
        } catch (NoSuchRecipeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getRecipesByName(@PathVariable("name") String name) {
        try {
            ArrayList<Recipe> matchingRecipes = recipeService.getRecipesByName(name);
            return ResponseEntity.ok(matchingRecipes);
        } catch (NoSuchRecipeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'Recipe', 'delete')")
    public ResponseEntity<?> deleteRecipeById(@PathVariable("id") Long id) {
        try {
            Recipe deletedRecipe = recipeService.deleteRecipeById(id);
            return ResponseEntity.ok("The recipe with ID " + deletedRecipe.getId() + " and name " + deletedRecipe.getName() + " was deleted");
        } catch (NoSuchRecipeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping
    @PreAuthorize("hasPermission(#updatedRecipe.id, 'Recipe', 'edit')")
    public ResponseEntity<?> updateRecipe(@RequestBody Recipe updatedRecipe) {
        try {
            Recipe returnedUpdatedRecipe = recipeService.updateRecipe(updatedRecipe, true);
            return ResponseEntity.ok(returnedUpdatedRecipe);
        } catch (NoSuchRecipeException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
