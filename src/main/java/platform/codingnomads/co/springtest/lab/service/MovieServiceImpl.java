package platform.codingnomads.co.springtest.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.springtest.lab.entity.Movie;
import platform.codingnomads.co.springtest.lab.repository.MovieRepository;

import javax.annotation.Nonnull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    @Nonnull private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
