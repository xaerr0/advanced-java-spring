package platform.codingnomads.co.springtest.mockingmethods.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import platform.codingnomads.co.springtest.mockingmethods.RecipeMain;
import platform.codingnomads.co.springtest.mockingmethods.exceptions.NoSuchRecipeException;
import platform.codingnomads.co.springtest.mockingmethods.models.Ingredient;
import platform.codingnomads.co.springtest.mockingmethods.models.Recipe;
import platform.codingnomads.co.springtest.mockingmethods.models.Step;
import platform.codingnomads.co.springtest.mockingmethods.repositories.RecipeRepo;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = RecipeMain.class)
@ActiveProfiles("test")
public class RecipeServiceUnitTest {

    @MockBean
    private RecipeRepo recipeRepo;

    @Autowired
    private RecipeService recipeService;

    @Test
    public void testGetRecipeByIdSuccessBehavior() throws Exception {
        Recipe pasta = Recipe.builder()
                .id(1L)
                .name("Make a pasta")
                .difficultyRating(1)
                .minutesToMake(5)
                .ingredients(Set.of(
                        Ingredient.builder().amount("1").name("pasta").build(),
                        Ingredient.builder().amount("1").name("pasta sauce").build())
                )
                .steps(Set.of(Step.builder()
                        .description("Boil water").stepNumber(1)
                        .description("Add and cook pasta").stepNumber(2)
                        .description("Drain water from pasta").stepNumber(3)
                        .description("Add sauce").stepNumber(4)
                        .description("Enjoy!")
                        .build()))
                .build();

        when(recipeRepo.findById(anyLong())).thenReturn(Optional.of(pasta));

        assertThat(recipeService.getRecipeById(1L)).isEqualTo(pasta);
    }

    @Test
    public void testGetRecipeByIdFailureBehavior() {
        when(recipeRepo.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchRecipeException.class, () -> {
            recipeService.getRecipeById(1L);
        });
    }

    @Test
    public void testGetRecipeByNameSuccessBehavior() throws Exception {
        when(recipeRepo.findByNameContaining(anyString())).thenReturn(
                new ArrayList<>(Arrays.asList(
                        Recipe.builder()
                                .id(1L)
                                .name("milkshake")
                                .difficultyRating(1)
                                .minutesToMake(3)
                                .ingredients(Set.of(
                                        Ingredient.builder().amount("1 liter").name("milk").build())
                                )
                                .steps(Set.of(Step.builder().description("shake the milk for 2 minutes").stepNumber(1).build()))
                                .build(),
                        Recipe.builder()
                                .id(2L)
                                .name("glass of milk")
                                .difficultyRating(1)
                                .minutesToMake(1)
                                .build()

                ))
        );

        ArrayList<Recipe> returnedRecipes = recipeService.getRecipesByName("milk");

        assertThat(returnedRecipes.size()).isEqualTo(2);
        assertThat(returnedRecipes.get(0).getLocationURI().toURL().toString()).isNotNull();
        assertThat(returnedRecipes.get(1).getLocationURI().toURL().toString()).isNotNull();

    }

    @Test
    public void testGetRecipeByNameFailureBehavior() {
        when(recipeRepo.findByNameContaining(anyString())).thenReturn(new ArrayList<>());

        assertThrows(NoSuchRecipeException.class, () -> {
            recipeService.getRecipesByName("some name. idk");
        }, "No recipes could be found with that name");
    }

    @Test
    public void testGetAllRecipesSuccessBehavior() throws Exception {

        Recipe pasta = Recipe.builder()
                .id(1L)
                .name("Make a pasta")
                .difficultyRating(1)
                .minutesToMake(5)
                .ingredients(Set.of(
                        Ingredient.builder().amount("1").name("pasta").build(),
                        Ingredient.builder().amount("1").name("pasta sauce").build())
                )
                .steps(Set.of(Step.builder()
                        .description("Boil water").stepNumber(1)
                        .description("Add and cook pasta").stepNumber(2)
                        .description("Drain water from pasta").stepNumber(3)
                        .description("Add sauce").stepNumber(4)
                        .description("Enjoy!")
                        .build()))
                .build();

        when(recipeRepo.findAll()).thenReturn(new ArrayList<>(Collections.singletonList(pasta)));

        ArrayList<Recipe> recipes = recipeService.getAllRecipes();

        assertThat(recipes.size()).isEqualTo(1);
        assertThat(recipes.get(0)).isEqualTo(pasta);
    }

    @Test
    public void testGetAllRecipesFailureBehavior() {
        when(recipeRepo.findAll()).thenReturn(new ArrayList<>());

        assertThrows(NoSuchRecipeException.class, () -> {
            recipeService.getAllRecipes();
        }, "There are no recipes yet :( feel free to add one though");

    }

    @Test
    public void testCreateNewRecipeSuccessBehavior() throws Exception {
        Recipe hangoverCureRecipe = Recipe.builder()
                .id(1L)
                .name("Hangover Headache Cure")
                .difficultyRating(1)
                .minutesToMake(5)
                .ingredients(Set.of(
                        Ingredient.builder().amount("0.5 liters").name("water").build()
                ))
                .steps(Set.of(
                        Step.builder().description("Drink water before you go to bed!").stepNumber(1).build())
                )
                .build();

        when(recipeRepo.save(any(Recipe.class))).thenReturn(hangoverCureRecipe);

        assertThat(recipeService.createNewRecipe(hangoverCureRecipe).getLocationURI()).isNotNull();

        hangoverCureRecipe.generateLocationURI();
        assertThat(recipeService.createNewRecipe(hangoverCureRecipe)).isEqualTo(hangoverCureRecipe);
    }

    @Test
    public void testCreateNewRecipeFailureBehavior() throws Exception {
        Recipe shouldFailRecipe = Recipe.builder()
                .id(1L)
                .name("should fail")
                .difficultyRating(1)
                .ingredients(new ArrayList<>())
                .steps(new ArrayList<>())
                .minutesToMake(1)
                .reviews(new ArrayList<>())
                .build();

        assertThrows(IllegalStateException.class, () -> {
            recipeService.createNewRecipe(shouldFailRecipe);
        });


    }



}
