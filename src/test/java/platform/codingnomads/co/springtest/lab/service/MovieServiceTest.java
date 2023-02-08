package platform.codingnomads.co.springtest.lab.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import platform.codingnomads.co.springtest.lab.SpringTestLab;
import platform.codingnomads.co.springtest.lab.entity.Movie;
import platform.codingnomads.co.springtest.lab.repository.MovieRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringTestLab.class)
@AutoConfigureMockMvc
class MovieServiceTest {

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @Test
    public void getAllMoviesSuccess() {
        List<Movie> movies = movieService.getAllMovies();
        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getName()).isEqualTo("The Shawshank Redemption");
        assertThat(movies.get(0).getRating()).isEqualTo(9.3);
        assertThat(movies.get(1).getName()).isEqualTo("The Pursuit of Happyness");
        assertThat(movies.get(1).getRating()).isEqualTo(8.0);
    }






    @Test
    public void getAllMoviesByMinimumRatingSuccess() {
        when(movieRepository.findByRatingGreaterThanEqual(4.0)).thenReturn(
                new ArrayList<>(Collections.singletonList(
                        Movie.builder()
                                .name("The Pursuit of Happyness")
                                .rating(8.0)
                                .build()
                ))
        );
    }

    @Test
    public void testGetMoviesByMinimumRatingFailure() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                movieService.getAllMoviesByMinimumRating(-1d)
        );

        assertThat(exception.getMessage()).isEqualTo("Rating must specify a value between 0 and 10");
    }


    @Test
    public void testGetAllMoviesFailure() {

        movieRepository.deleteAll();

        List<Movie> movies = movieService.getAllMovies();
        assertThat(movies.size()).isEqualTo(0);
    }
}