package platform.codingnomads.co.springdata.example.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.springdata.example.querydsl.models.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>, RouteCustomRepository {

}


