package platform.codingnomads.co.springtest.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.springtest.lab.entity.Movie;
import platform.codingnomads.co.springtest.lab.repository.MovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movieList = movieRepository.findAll();
        if (movieList.isEmpty()) {
            throw new IllegalStateException("No movies found!");
        }
        return movieList;
    }

    @Override
    public List<Movie> getAllMoviesByMinimumRating(Double rating) {
        return movieRepository.findByRatingGreaterThanEqual(rating);
    }
}