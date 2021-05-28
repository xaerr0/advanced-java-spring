package platform.codingnomads.co.springdata.example.dml.transactional.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.example.dml.transactional.models.Point;

public interface PointRepo extends JpaRepository<Point, Long> {
}
