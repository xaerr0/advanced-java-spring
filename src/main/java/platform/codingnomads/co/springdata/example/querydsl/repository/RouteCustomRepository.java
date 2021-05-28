package platform.codingnomads.co.springdata.example.querydsl.repository;

import platform.codingnomads.co.springdata.example.querydsl.SearchQuery;
import platform.codingnomads.co.springdata.example.querydsl.domain.Route;

import java.util.List;

public interface RouteCustomRepository {

    List<Route> findAllRoutesBySearchQuery(SearchQuery searchQuery);
}
