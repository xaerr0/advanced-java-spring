package platform.codingnomads.co.springtest.lab.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import platform.codingnomads.co.springtest.TestUtil;
import platform.codingnomads.co.springtest.lab.SpringTestLab;
import platform.codingnomads.co.springtest.lab.controller.MovieController;
import platform.codingnomads.co.springtest.lab.entity.Movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = SpringTestLab.class)
@AutoConfigureMockMvc
public class MovieControllerTest {


    @Autowired
    private MockMvc mockMvc;

    // Task 1: Please complete an implementation of both testGetAllMoviesSuccess() and testGetAllMoviesFailure(),
    // which should test the return result of the /all endpoint. These methods will test using data returned
    // from the actual database.
    @Test
    public void testGetAllMoviesSuccess() throws Exception {
        mockMvc.perform((get("/movies/all")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Task 3: Create a new variation of testGetAllMoviesSuccess() using a different method of data validation that
    // you used in Task 1. For example, if you used JSON paths in your implementation of Task 1,
    // maybe use TestUtil.convertJsonBytesToObject() to create an object for testing instead (or vice versa).

    @Test
    public void testGetAllMoviesSuccessUsingObjectMapper() throws Exception {
        byte[] responseBytes = mockMvc.perform(get("/movies/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().handlerType(MovieController.class))
                .andExpect(handler().method(MovieController.class.getMethod("getAllMovies")))
                .andReturn().getResponse().getContentAsByteArray();

        Movie[] movies = TestUtil.convertJsonBytesToObject(responseBytes, Movie[].class);

        assertThat(movies.length).isEqualTo(2);
        assertThat(movies[0].getName()).isEqualTo("The Shawshank Redemption");
        assertThat(movies[0].getRating()).isEqualTo(9.3);
        assertThat(movies[1].getName()).isEqualTo("The Pursuit of Happyness");
        assertThat(movies[1].getRating()).isEqualTo(8.0);
    }

    @Test
    public void testGetAllMoviesSuccessAlternate() throws Exception {
        mockMvc.perform((get("/movies/all")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[0].rating").value(9.3))
                .andExpect(jsonPath("$[1].name").value("The Pursuit of Happyness"))
                .andExpect(jsonPath("$[1].rating").value(8.0));
    }

    //Task 4: Create new endpoint in MovieController (and accompanying methods in MovieService, etc.) that will
    // return a list of movies based on a minimum rating.

    @Test
    public void testGetMoviesByMinimumRatingSuccess() throws Exception {
        mockMvc.perform(get("/movies/{rating}", 6))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    //Task 5: Implement success and failure testing for this new endpoint.
    @Test
    public void testGetMoviesByMinimumRatingFailure() throws Exception {
        mockMvc.perform(get("/movies/all/{rating}", 4))
                .andExpect(status().isNotFound());
    }
}