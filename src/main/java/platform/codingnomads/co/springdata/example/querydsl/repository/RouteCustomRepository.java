package platform.codingnomads.co.springdata.example.querydsl.repository;

import platform.codingnomads.co.springdata.example.querydsl.models.SearchQuery;
import platform.codingnomads.co.springdata.example.querydsl.models.Route;

import java.util.List;

public interface RouteCustomRepository {

    List<Route> findAllRoutesBySearchQuery(SearchQuery searchQuery);
}
