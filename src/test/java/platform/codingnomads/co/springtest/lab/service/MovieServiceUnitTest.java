package platform.codingnomads.co.springtest.lab.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import platform.codingnomads.co.springtest.lab.SpringTestLab;
import platform.codingnomads.co.springtest.lab.controller.MovieController;
import platform.codingnomads.co.springtest.lab.entity.Movie;
import platform.codingnomads.co.springtest.lab.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@WebMvcTest(MovieService.class)
@ContextConfiguration(classes = SpringTestLab.class)
public class MovieServiceUnitTest {

    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void testGetAllMoviesSuccess() {
        List<Movie> movieList = List.of(
                new Movie(1L, "Troll", 9.1),
                new Movie(2L, "Kazaam ", 5.3),
                new Movie(3L, "3 Ninjas", 3.2)
        );

        when(movieRepository.findAll()).thenReturn(movieList);

        assertThat(movieService.getAllMovies().size()).isEqualTo(movieList.size());
//        for (int i = 0; i < movieList.size(); i++) {
//            assertThat(movieService.getAllMovies().get(i)).isEqualTo(movieList.get(i));

        IntStream.range(0, movieList.size())
                .forEach(i -> assertThat(movieService.getAllMovies().get(i)).isEqualTo(movieList.get(i)));
        }

    @Test
    public void testGetAllMoviesFailure() {
        when(movieRepository.findAll()).thenThrow(new IllegalStateException());

        assertThrows(IllegalStateException.class, () -> {
            movieService.getAllMovies();
        });
    }

    @Test
    public void getAllMoviesFailure() {
        when(movieRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(IllegalStateException.class, () -> {
                    movieService.getAllMovies();
                },
                "No movies found!");
    }

    @Test
    public void testGetMoviesByMinimumRatingSuccess() {
        List<Movie> movieList = List.of(
                new Movie(1L, "Jaws", 8.3d),
                new Movie(2L, "The Godfather", 9.1d),
                new Movie(3L, "Dune", 8.9d),
                new Movie(4L, "No Time To Die", 8.5d)
        );

        when(movieRepository.findByRatingGreaterThanEqual(anyDouble()))
                .thenReturn(movieList);

        assertThat(movieService.getAllMoviesByMinimumRating(8.0d).size())
                .isEqualTo(movieList.size());

        for (int i = 0; i < movieList.size(); i++){
            assertThat(movieService.getAllMoviesByMinimumRating(8.0d).get(i))
                    .isEqualTo(movieList.get(i));
        }
    }

    @Test
    public void testGetMoviesByMinimumRatingFailure() {
        when(movieRepository.findByRatingGreaterThanEqual(anyDouble()))
                .thenThrow(new IllegalStateException());

        assertThrows(IllegalStateException.class, () -> {
            movieService.getAllMoviesByMinimumRating(1d);
        });
    }
    }