package platform.codingnomads.co.springtest.testingjsonresponsecontent.withoutjsonpath;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import platform.codingnomads.co.springtest.TestUtil;
import platform.codingnomads.co.springtest.mockingmethods.RecipeMain;
import platform.codingnomads.co.springtest.mockingmethods.models.Ingredient;
import platform.codingnomads.co.springtest.mockingmethods.models.Recipe;
import platform.codingnomads.co.springtest.mockingmethods.models.Review;
import platform.codingnomads.co.springtest.mockingmethods.models.Step;
import platform.codingnomads.co.springtest.mockingmethods.repositories.RecipeRepo;

import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = RecipeMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeControllerEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecipeRepo recipeRepo;

    @Test
    @Order(1)
    public void testGetRecipeByIdSuccessBehavior() throws Exception {
        final long recipeId = 1;

        //set up GET request
        byte[] responseContent = mockMvc.perform(get("/recipes/" + recipeId))

                //print response
                .andDo(print())
                //expect status 200 OK
                .andExpect(status().isOk())
                //expect return Content-Type header as application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                //get response as a byte[] array
                .andReturn().getResponse().getContentAsByteArray();

        //convert byte[] to Recipe obj
        Recipe returnedRecipe = TestUtil.convertJsonBytesToObject(responseContent, Recipe.class);

        assertThat(returnedRecipe.getId()).isEqualTo(recipeId);
        assertThat(returnedRecipe.getMinutesToMake()).isEqualTo(2);
        assertThat(returnedRecipe.getReviews()).hasSize(1);
        assertThat(returnedRecipe.getIngredients()).hasSize(1);
        assertThat(returnedRecipe.getSteps()).hasSize(2);
    }

    @Test
    @Order(2)
    public void testGetRecipeByIdFailureBehavior() throws Exception {
        final long recipeId = 50;
        //set up guaranteed to fail in testing environment request
        byte[] responseContent = mockMvc.perform(get("/recipes/" + recipeId))
                //print response
                .andDo(print())
                //expect status 404 NOT FOUND
                .andExpect(status().isNotFound())
                //get response byte[]
                .andReturn().getResponse().getContentAsByteArray();

        String possibleErrorMessage = new String(responseContent);

        //confirm that HTTP body contains correct error message
        assertThat(possibleErrorMessage).isEqualTo("No recipe with ID " + recipeId + " could be found");
    }

    @Test
    @Order(3)
    public void testGetAllRecipesSuccessBehavior() throws Exception {

        //set up get request for all recipe endpoint
        byte[] responseContent = mockMvc.perform(get("/recipes"))
                .andDo(print())
                //expect status is 200 OK
                .andExpect(status().isOk())
                //expect it will be returned as JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                //get JSON byte[]
                .andReturn().getResponse().getContentAsByteArray();

        //get a Recipe[] from the response bytes
        Recipe[] recipes = TestUtil.convertJsonBytesToObject(responseContent, Recipe[].class);

        assertThat(recipes).hasSize(4);
        assertThat(recipes[0].getId()).isEqualTo(1);
        assertThat(recipes[0].getName()).isEqualTo("test recipe");
        assertThat(recipes[1].getId()).isEqualTo(2);
        assertThat(recipes[1].getMinutesToMake()).isEqualTo(2);
        assertThat(recipes[2].getId()).isEqualTo(3);
        assertThat(recipes[2].getDifficultyRating()).isEqualTo(5);
    }

    @Test
    @Order(4)
    public void testCreateNewRecipeSuccessBehavior() throws Exception {
        Ingredient ingredient = Ingredient.builder().name("brown sugar").state("dry").amount("1 cup").build();
        Step step1 = Step.builder().description("heat pan").stepNumber(1).build();
        Step step2 = Step.builder().description("add sugar").stepNumber(2).build();

        Review review = Review.builder().description("was just caramel").rating(3).username("idk").build();

        Recipe recipe = Recipe.builder()
                .name("caramel in a pan")
                .difficultyRating(10)
                .minutesToMake(2)
                .ingredients(Set.of(ingredient))
                .steps(Set.of(step1, step2))
                .reviews(Set.of(review))
                .build();


        byte[] responseContent = mockMvc
                .perform(post("/recipes")
                        //set request Content-Type header
                        .contentType("application/json")
                        //set HTTP body equal to JSON based on recipe object
                        .content(TestUtil.convertObjectToJsonBytes(recipe))
                        )
            //confirm HTTP response data
            .andExpect(status().isCreated())
            .andExpect(content().contentType("application/json"))
            //confirm Location header with new location of object matches the correct URL structure
            .andExpect(header().string("Location", containsString("http://localhost/recipes/")))
            .andReturn().getResponse().getContentAsByteArray();

        Recipe returnedRecipe = TestUtil.convertJsonBytesToObject(responseContent, Recipe.class);

        //confirm some recipe data
        assertThat(returnedRecipe.getId()).isNotNull();
        assertThat(returnedRecipe.getName()).isEqualTo("caramel in a pan");

        //confirm ingredient data
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>(returnedRecipe.getIngredients());
        assertThat(ingredientArrayList).hasSize(1);
        assertThat(ingredientArrayList.get(0).getName()).isEqualTo("brown sugar");
        assertThat(ingredientArrayList.get(0).getAmount()).isEqualTo("1 cup");

        //confirm step data
        ArrayList<Step> stepArrayList = new ArrayList<>(returnedRecipe.getSteps());
        assertThat(stepArrayList).hasSize(2);
        assertThat(stepArrayList.get(0)).isNotNull();
        assertThat(stepArrayList.get(1)).isNotNull();

        //confirm review data
        ArrayList<Review> reviewArrayList = new ArrayList<>(returnedRecipe.getReviews());
        assertThat(reviewArrayList).hasSize(1);
        assertThat(reviewArrayList.get(0).getUsername()).isEqualTo("idk");

    }

    @Test
    @Order(5)
    public void testCreateNewRecipeFailureBehavior() throws Exception {

        Recipe recipe = new Recipe();

        //force failure with empty User object POST
        byte[] responseContent = mockMvc.perform(
                    post("/recipes")
                        //set body equal to empty recipe object
                        .content(TestUtil.convertObjectToJsonBytes(recipe))
                        //set Content-Type header
                        .contentType("application/json")
                )
                //confirm status code 400 BAD REQUEST
                .andExpect(status().isBadRequest())
                //get JSON byte[]
                .andReturn().getResponse().getContentAsByteArray();

        String errorMessage = new String(responseContent);

        //confirm there is an error message
        assertThat(errorMessage).isNotBlank();
    }

    @Test
    @Order(6)
    public void testGetRecipesByNameSuccessBehavior() throws Exception {

        //set up get request to search for recipes with names including the word recipe
        MvcResult mvcResult = this.mockMvc.perform(get("/recipes/search/recipe"))
                //expect 200 OK
                .andExpect(status().isOk())
                //expect JSON in return
                .andExpect(content().contentType("application/json"))
                //return the MvcResult
                .andReturn();

        //pull json byte array from the result
        byte[] jsonByteArray = mvcResult.getResponse().getContentAsByteArray();
        //convert the json bytes to an array of Recipe objects
        Recipe[] returnedRecipes = TestUtil.convertJsonBytesToObject(jsonByteArray, Recipe[].class);

        //confirm 3 recipes were returned
        assertThat(returnedRecipes.length).isEqualTo(3);


        for(Recipe r: returnedRecipes) {
            //confirm none of the recipes are null
            assertThat(r).isNotNull();
            //confirm they all have IDs
            assertThat(r.getId()).isNotNull();
            //confirm they all contain recipe in the name
            assertThat(r.getName()).contains("recipe");
        }

        //set up get request to search for recipes with names containing potato
        byte[] jsonBytes = this.mockMvc.perform(get("/recipes/search/potato"))
                //expect 200 OK
                .andExpect(status().isOk())
                //expect json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                //return response byte array
                .andReturn().getResponse().getContentAsByteArray();

        //get recipes as a java array
        returnedRecipes = TestUtil.convertJsonBytesToObject(jsonBytes, Recipe[].class);

        //confirm only one recipe was returned
        assertThat(returnedRecipes.length).isEqualTo(1);

        //make sure the recipe isn't null
        assertThat(returnedRecipes[0]).isNotNull();

        //expect that the name should contain potato
        assertThat(returnedRecipes[0].getName()).contains("potato");
    }

    @Test
    @Order(7)
    public void testGetRecipeByNameFailureBehavior() throws Exception {

        byte[] contentAsByteArray = this.mockMvc.perform(get("/recipes/search/should not exist"))
                //expect 404 NOT FOUND
                .andExpect(status().isNotFound())
                //expect only a String in the body
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                //retrieve content byte array
                .andReturn().getResponse().getContentAsByteArray();

        //convert JSON to String
        String message = new String(contentAsByteArray);

        //confirm error message is correct
        assertThat(message).isEqualTo("No recipes could be found with that name");
    }

    @Test
    @Order(8)
    public void testUpdateRecipeSuccessBehavior() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/recipes/2"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        Recipe recipeToUpdate = TestUtil.convertJsonBytesToObject(response.getContentAsByteArray(), Recipe.class);

        recipeToUpdate.getReviews().add(Review.builder().description("Was pretty good. A lil spicy though").username("ben").rating(5).build());
        recipeToUpdate.getSteps().add(Step.builder().stepNumber(1).description("put tabasco on plate").build());
        recipeToUpdate.getSteps().add(Step.builder().stepNumber(2).description("lick plate").build());

        recipeToUpdate.getIngredients().add(Ingredient.builder().name("Tabasco").amount("5 tbs").build());


        recipeToUpdate.setName("a spicy dish");
        recipeToUpdate.setMinutesToMake(1);
        recipeToUpdate.setDifficultyRating(1);

        byte[] updateResponseByteArr = this.mockMvc.perform(patch("/recipes")
                    .content(TestUtil.convertObjectToJsonBytes(recipeToUpdate))
                    .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsByteArray();

        Recipe returnedUpdatedRecipe = TestUtil.convertJsonBytesToObject(updateResponseByteArr, Recipe.class);

        assertThat(returnedUpdatedRecipe.getSteps()).hasSize(3);
        assertThat(returnedUpdatedRecipe.getIngredients()).hasSize(2);
        assertThat(returnedUpdatedRecipe.getMinutesToMake()).isEqualTo(1);
        assertThat(returnedUpdatedRecipe.getDifficultyRating()).isEqualTo(1);

    }

    @Test
    @Order(9)
    public void testDeleteRecipeByIdSuccessBehavior() throws Exception {
        final long recipeId = 3;
        //get the recipe with ID 3 for future error message confirmation
        byte[] responseByteArr = this.mockMvc.perform(get("/recipes/" + recipeId))
                .andExpect(status().isOk())
                //confirm correct recipe was returned
                .andExpect(jsonPath("id").value(recipeId))
                .andReturn().getResponse().getContentAsByteArray();

        Recipe recipe3 = TestUtil.convertJsonBytesToObject(responseByteArr, Recipe.class);

        //set up delete request
        byte[] deleteResponseByteArr = this.mockMvc.perform(delete("/recipes/" + recipeId))
                //confirm 200 OK was returned
                .andExpect(status().isOk())
                //confirm a String was returned
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .andReturn().getResponse().getContentAsByteArray();

        //pull delete message from byte[]
        String returnedDeleteConfirmationMessage = new String(deleteResponseByteArr);

        //confirm the message is as expected using the previously acquired Recipe object
        assertThat(returnedDeleteConfirmationMessage)
                .isEqualTo("The recipe with ID "  + recipe3.getId() + " and name " + recipe3.getName() + " was deleted");
    }

    @Test
    @Order(10)
    public void testDeleteRecipeByIdFailureBehavior() throws Exception {
        //force error with invalid ID
        byte[] responseContent = mockMvc.perform(delete("/recipes/-1"))
                //expect 400 BAD REQUEST
                .andExpect(status().isBadRequest())
                //expect plain text aka a String
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .andReturn().getResponse().getContentAsByteArray();

        String errorMessage = new String(responseContent);

        //confirm correct error message
        assertThat(errorMessage).isEqualTo("No recipe with ID -1 could be found. Could not delete.");
    }

    @Test
    @Order(11)
    public void testGetAllRecipesFailureBehavior() throws Exception {

        //delete all recipes to force 404 error
        recipeRepo.deleteAll();

        //perform GET all recipes
        byte[] responseContent = mockMvc.perform(get("/recipes"))
                .andDo(print())
                //expect 404 NOT FOUND
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsByteArray();

        String errorMessage = new String(responseContent);

        //expect error message defined in RecipeService class
        assertThat(errorMessage).isEqualTo("There are no recipes yet :( feel free to add one though");
    }

}
