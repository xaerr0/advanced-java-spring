package platform.codingnomads.co.mockingmethods.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import platform.codingnomads.co.TestUtil;
import platform.codingnomads.co.springtest.mockingmethods.RecipeMain;
import platform.codingnomads.co.springtest.mockingmethods.controllers.RecipeController;
import platform.codingnomads.co.springtest.mockingmethods.exceptions.NoSuchRecipeException;
import platform.codingnomads.co.springtest.mockingmethods.models.Ingredient;
import platform.codingnomads.co.springtest.mockingmethods.models.Recipe;
import platform.codingnomads.co.springtest.mockingmethods.models.Step;
import platform.codingnomads.co.springtest.mockingmethods.services.RecipeService;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
@ContextConfiguration(classes = RecipeMain.class)
@ActiveProfiles("test")
public class RecipeControllerUnitTest {

    @MockBean
    RecipeService recipeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetRecipeByIdNormalBehavior() throws Exception {
        //create recipe
        Recipe recipe = Recipe.builder()
                .name("test recipe")
                .difficultyRating(1)
                .minutesToMake(5)
                .ingredients(
                        Set.of(Ingredient.builder().amount("1 jar").name("pickles").build())
                )
                .steps(
                        Set.of(Step.builder().description("eat pickles").stepNumber(1).build())
                )
                .build();

        //when RecipeService's getRecipeById() method is called with any Long passed in
        when(recipeService.getRecipeById(anyLong()))
                //it will return the above defined recipe
                .thenReturn(recipe);

        //test that the controller works properly when the above recipe is guaranteed to be returned from its
        //getRecipeById() method call
        mockMvc.perform(get("/recipes/1"))
                //expect 200 OK
                .andExpect(status().isOk())
                //expect JSON in the body
                .andExpect(content().contentType("application/json"))
                //expect the JSON matches the Recipe returned from getRecipeById()
                .andExpect(content().string(TestUtil.convertObjectToJsonString(recipe)));
    }

    @Test
    public void testGetRecipeByIdFailureBehavior() throws Exception {
        when(recipeService.getRecipeById(any()))
                .thenThrow(new NoSuchRecipeException("test this is in body"));

        mockMvc.perform(get("/recipes/" + 1L))
                //print response
                .andDo(print())
                //expect status 404 NOT FOUND
                .andExpect(status().isNotFound())
                //confirm that HTTP body contains correct error message
                .andExpect(content().string(containsString("test this is in body")));
    }

    @Test
    public void testGetRecipeByNameSuccessBehavior() throws Exception {
        when(recipeService.getRecipesByName(anyString())).thenReturn(
                new ArrayList<>(Arrays.asList(
                        Recipe.builder()
                                .id(1L)
                                .name("searched recipe 1")
                                .difficultyRating(1)
                                .minutesToMake(5)
                                .ingredients(Set.of(Ingredient.builder().amount("1 jar").name("pickles").state("jarred").build()))
                                .steps(Set.of(
                                        Step.builder().description("eat pickles").stepNumber(1).build(),
                                        Step.builder().description("don't forget to close the jar!").stepNumber(2).build()
                                ))
                                .build(),

                        Recipe.builder()
                                .id(2L)
                                .name("searched recipe 2")
                                .difficultyRating(10)
                                .minutesToMake(10)
                                .build()
                ))
        );


        mockMvc.perform(get("/recipes/search/searched"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(containsString("searched")))
                .andExpect(jsonPath("$[1].name").value(containsString("searched")));

    }

    @Test
    public void testGetRecipeByNameFailureBehavior() throws Exception {
        when(recipeService.getRecipesByName(anyString()))
                .thenThrow(new NoSuchRecipeException("could not find recipes"));

        mockMvc.perform(get("/recipes/search/gibberish"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("could not find recipes"));
    }

    @Test
    public void testGetAllRecipesSuccessBehavior() throws Exception {

        when(recipeService.getAllRecipes()).thenReturn(
                new ArrayList<>(Arrays.asList(
                        Recipe.builder()
                                .id(1L)
                                .name("mocked name")
                                .minutesToMake(2000)
                                .difficultyRating(2)
                                .build(),
                        Recipe.builder()
                                .id(2L)
                                .name("another mocked name")
                                .minutesToMake(1)
                                .difficultyRating(10)
                                .ingredients(Set.of(Ingredient.builder()
                                            .id(1L).name("mock sauce")
                                            .state("saucy")
                                            .amount("lots").build()))
                                .steps(Set.of(Step.builder()
                                            .id(1L)
                                            .stepNumber(1)
                                            .description("mock the mock sauce").build()))
                                .build())
                )
        );

        //set up get request for all recipe endpoint
        mockMvc.perform(get("/recipes"))
                .andDo(print())
                //expect status is 200 OK
                .andExpect(status().isOk())
                //expect it will be returned as JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                //expect there are 4 entries
                .andExpect(jsonPath("$", hasSize(2)))
                //expect the first entry to have ID 1
                .andExpect(jsonPath("$[0].id").value(1))
                //expect the first entry to have name test recipe
                .andExpect(jsonPath("$[0].name").value("mocked name"))
                //expect no ingredients for first entry
                .andExpect(jsonPath("$[0].ingredients").isEmpty())
                //expect the second entry to have id 2
                .andExpect(jsonPath("$[1].id").value(2))
                //expect the second entry to have a minutesToMake value of 2
                .andExpect(jsonPath("$[1].minutesToMake").value(1))
                //expect the second entry to have an ingredient with name "mock sauce"
                .andExpect(jsonPath("$[1].ingredients[0].name").value("mock sauce"))
                //expect the second entry has 1 step
                .andExpect(jsonPath("$[1].steps[0].description").value("mock the mock sauce"));
    }

    @Test
    public void testGetAllRecipesFailureBehavior() throws Exception {
        //when all recipes is called throw new NoSuchRecipeException with a random message
        when(recipeService.getAllRecipes()).thenThrow(new NoSuchRecipeException("this should be in the body"));

        //perform GET all recipes
        mockMvc.perform(get("/recipes"))
                .andDo(print())
                //expect 404 NOT FOUND
                .andExpect(status().isNotFound())
                //expect error message defined above
                .andExpect(content().string(containsString("this should be in the body")));
    }

    @Test
    public void testCreateNewRecipeSuccessBehavior() throws Exception {
        Recipe recipe = Recipe.builder()
                .id(1L)
                .name("water in a glass")
                .difficultyRating(1)
                .minutesToMake(1)
                .ingredients(Set.of(Ingredient.builder().id(1L).name("water").state("liquid").build()))
                .steps(Set.of(
                        Step.builder().id(1L).stepNumber(1).description("turn on faucet").build(),
                        Step.builder().id(2L).stepNumber(2).description("fill glass").build(),
                        Step.builder().id(3L).stepNumber(3).description("turn off faucet").build())
                )
                .locationURI(new URI("http://thisIsALink.com/1"))
                .build();

        when(recipeService.createNewRecipe(any(Recipe.class))).thenReturn(recipe);

        mockMvc.perform(post("/recipes")
                            .contentType("application/json")
                            .content(TestUtil.convertObjectToJsonBytes(recipe))
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://thisIsALink.com/1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.minutesToMake").value(1))
                .andExpect(jsonPath("$.ingredients", hasSize(1)))
                .andExpect(jsonPath("$.ingredients[0].name").value("water"))
                .andExpect(jsonPath("$.steps", hasSize(3)))
                .andExpect(jsonPath("$.steps[0].id").isNumber())
                .andExpect(jsonPath("$.steps[0].stepNumber").isNotEmpty())
                .andExpect(jsonPath("$.steps[1].description").isNotEmpty());
    }

    @Test
    public void testCreateNewRecipeFailureBehavior() throws Exception {
        when(recipeService.createNewRecipe(any(Recipe.class)))
                .thenThrow(new IllegalStateException("does it match?"));


        //force failure with empty User object
        mockMvc.perform(
                post("/recipes")
                        //set body equal to empty recipe object
                        .content(TestUtil.convertObjectToJsonBytes(Recipe.builder().build()))
                        //set Content-Type header
                        .contentType("application/json")
        )
                //confirm status code 400 BAD REQUEST
                .andExpect(status().isBadRequest())
                //confirm the body only contains a String
                .andExpect(content().string(containsString("does it match?")));
    }

    @Test
    public void testDeleteRecipeByIdSuccessBehavior() throws Exception {
        when(recipeService.deleteRecipeById(anyLong())).thenReturn(
                Recipe.builder()
                        .id(1L)
                        .name("deleted!")
                        .minutesToMake(1)
                        .difficultyRating(5)
                        .build()
        );

        mockMvc.perform(delete("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .andExpect(content().string("The recipe with ID 1 and name deleted! was deleted"));
    }

    @Test
    public void testDeleteRecipeByIdFailureBehavior() throws Exception {
        when(recipeService.deleteRecipeById(anyLong()))
                .thenThrow(new NoSuchRecipeException("this should be in the body!"));

        mockMvc.perform(delete("/recipes/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("this should be in the body!"));
    }

    @Test
    public void testUpdateRecipeSuccessBehavior() throws Exception {

        Recipe recipe = Recipe.builder().name("updated recipe").build();

        when(recipeService.updateRecipe(any(Recipe.class), eq(true))).thenReturn(recipe);

        mockMvc.perform(patch("/recipes")
                            .contentType("application/json")
                            .content(TestUtil.convertObjectToJsonBytes(recipe))
                        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(TestUtil.convertObjectToJsonString(recipe)));
    }

    @Test
    public void testUpdateRecipeFailureBehavior() throws Exception {
        when(recipeService.updateRecipe(any(Recipe.class), eq(true)))
                .thenThrow(new NoSuchRecipeException("found in body?"));

        mockMvc.perform(patch("/recipes")
                        .contentType("application/json")
                        .content(TestUtil.convertObjectToJsonBytes(Recipe.builder().build()))
                    )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("found in body?"));


        when(recipeService.updateRecipe(any(Recipe.class), eq(true)))
                .thenThrow(new IllegalStateException("found in body?"));

        mockMvc.perform(patch("/recipes")
                .contentType("application/json")
                .content(TestUtil.convertObjectToJsonBytes(Recipe.builder().build()))
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("found in body?"));

    }

}
