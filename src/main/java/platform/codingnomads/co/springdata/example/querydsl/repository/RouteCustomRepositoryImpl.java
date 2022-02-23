package platform.codingnomads.co.springdata.example.querydsl.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import platform.codingnomads.co.springdata.example.querydsl.models.SearchQuery;
import platform.codingnomads.co.springdata.example.querydsl.models.Route;
import platform.codingnomads.co.springdata.example.querydsl.models.QRoute;

import java.util.List;

@Repository
public class RouteCustomRepositoryImpl extends QuerydslRepositorySupport implements RouteCustomRepository {

    public RouteCustomRepositoryImpl() {
        super(Route.class);
    }

    @Override
    public List<Route> findAllRoutesBySearchQuery(SearchQuery searchQuery) {
        QRoute route = QRoute.route;

        BooleanBuilder predicate = new BooleanBuilder();

        if (searchQuery.getId() != null) {
            predicate.and(route.id.eq(searchQuery.getId()));
        }

        if (!StringUtils.isEmpty(searchQuery.getName())) {
            predicate.and(route.name.eq(searchQuery.getName()));
        }

        if (!StringUtils.isEmpty(searchQuery.getCode())) {
            predicate.and(route.code.eq(searchQuery.getCode()));
        }

        if (!StringUtils.isEmpty(searchQuery.getOrigin())) {
            predicate.and(route.origin.code.eq(searchQuery.getOrigin()));
        }

        if (!StringUtils.isEmpty(searchQuery.getDestination())) {
            predicate.and(route.origin.code.eq(searchQuery.getDestination()));
        }

        return from(route)
                .where(predicate)
                .orderBy(route.id.desc())
                .select(route)
                .fetch();
    }
}
