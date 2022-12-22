package platform.codingnomads.co.springdata.example.dml.transactional.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import platform.codingnomads.co.springdata.example.dml.transactional.models.Point;

public interface PointRepo extends JpaRepository<Point, Long> {

}