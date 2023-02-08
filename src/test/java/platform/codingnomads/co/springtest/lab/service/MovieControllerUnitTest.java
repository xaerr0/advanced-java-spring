package platform.codingnomads.co.springtest.lab.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import platform.codingnomads.co.springtest.lab.SpringTestLab;
import platform.codingnomads.co.springtest.lab.controller.MovieController;
import platform.codingnomads.co.springtest.lab.entity.Movie;
import platform.codingnomads.co.springtest.lab.repository.MovieRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
@ContextConfiguration(classes = SpringTestLab.class)
public class MovieControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;


    // Task 2: Use Mockito to complete an implementation of testGetAllMoviesSuccessMockService(),
    // which mocks MovieService and creates a list of movies for testing, instead of using the database.
    @Test
    public void testGetAllMoviesSuccessMockService() throws Exception {
        Movie movie1 = Movie.builder().id(1L).name("Die Hard").rating(9.3).build();
        Movie movie2 = Movie.builder().id(2L).name("Wizard of Oz").rating(9.8).build();
        Movie movie3 = Movie.builder().id(3L).name("Spirited").rating(0.5).build();

        List<Movie> movieList = new ArrayList<>(Arrays.asList(movie1, movie2, movie3));

        when(movieService.getAllMovies()).thenReturn(movieList);
        mockMvc.perform(get("/movies/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetAllMoviesFailure() throws Exception {
        when(movieService.getAllMovies()).thenReturn(new ArrayList<>());
        mockMvc.perform((get("/movies/all")))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }
}