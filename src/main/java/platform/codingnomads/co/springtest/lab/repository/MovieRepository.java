package platform.codingnomads.co.springtest.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springtest.lab.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
