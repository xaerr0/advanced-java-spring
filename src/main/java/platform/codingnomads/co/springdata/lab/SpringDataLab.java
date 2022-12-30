package platform.codingnomads.co.springdata.lab;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springdata.lab.models.Area;
import platform.codingnomads.co.springdata.lab.models.Cafe;
import platform.codingnomads.co.springdata.lab.models.Route;
import platform.codingnomads.co.springdata.lab.repositories.AreaRepository;
import platform.codingnomads.co.springdata.lab.repositories.CafeRepository;
import platform.codingnomads.co.springdata.lab.repositories.RouteRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataLab implements CommandLineRunner {

    private final AreaRepository areaRepository;
    private final RouteRepository routeRepository;
    private final CafeRepository cafeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataLab.class);
    }

    @Override
    public void run(String... args) throws Exception {

        if (areaRepository.count() == 0) {


            final List<Area> areas = areaRepository.saveAll(
                    Arrays.asList(
                            Area.builder().code("G").build(),
                            Area.builder().code("H").build(),
                            Area.builder().code("Y").build(),
                            Area.builder().code("Z").build(),
                            Area.builder().code("A").build(),
                            Area.builder().code("B").build(),
                            Area.builder().code("C").build(),
                            Area.builder().code("D").build()
                    )
            );
        }
        // find by code
        System.out.println(areaRepository.getByCode("Z"));

//        final List<Route> routes = routeRepository.saveAll(
//                Arrays.asList(
//                        Route.builder().origin(areaRepository.getByCode("Y"))
//                                .destination(areaRepository.getByCode("Z")).build(),
//                        Route.builder().origin(areaRepository.getByCode("G"))
//                                .destination(areaRepository.getByCode("H")).build(),
//                        Route.builder().origin(areaRepository.getByCode("A"))
//                                .destination(areaRepository.getByCode("B")).build(),
//                        Route.builder().origin(areaRepository.getByCode("C"))
//                                .destination(areaRepository.getByCode("D")).build(),
//                        Route.builder().origin(areaRepository.getByCode("A"))
//                                .destination(areaRepository.getByCode("Z")).build()
//                )
//
//
//        );
//        routes.forEach(System.out::println);

        if (routeRepository.count() == 0) {
            Route route1 = new Route();
            route1.setOrigin(areaRepository.getByCode("Y"));
            route1.setDestination(areaRepository.getByCode("Z"));
            route1.setCode(route1.getOrigin().toString() + "-" + route1.getDestination().toString());

            Route route2 = new Route();
            route2.setOrigin(areaRepository.getByCode("G"));
            route2.setDestination(areaRepository.getByCode("H"));
            route2.setCode(route2.getOrigin().toString() + "-" + route2.getDestination().toString());

            Route route3 = new Route();
            route3.setOrigin(areaRepository.getByCode("A"));
            route3.setDestination(areaRepository.getByCode("B"));
            route3.setCode(route3.getOrigin().toString() + "-" + route3.getDestination().toString());

            Route route4 = new Route();
            route4.setOrigin(areaRepository.getByCode("C"));
            route4.setDestination(areaRepository.getByCode("A"));
            route4.setCode(route4.getOrigin().toString() + "-" + route4.getDestination().toString());

            Route route5 = new Route();
            route5.setOrigin(areaRepository.getByCode("A"));
            route5.setDestination(areaRepository.getByCode("Z"));
            route5.setCode(route5.getOrigin().toString() + "-" + route5.getDestination().toString());

            List<Route> routeList = routeRepository.saveAll(List.of(route1, route2, route3, route4, route5));

        }

        // TODO Had to change fetchtype to EAGER for this to work? por queso?
        System.out.println(routeRepository.findByOrigin_code("A"));
        System.out.println(routeRepository.findByDestination_code("Z"));


        System.out.println(routeRepository.findByCode("A-B"));


        if (cafeRepository.count() == 0) {

            Cafe cafe1 = new Cafe("Bean Martin's", 4, areaRepository.getByCode("A"));
            Cafe cafe2 = new Cafe("Mr. Bean", 2, areaRepository.getByCode("Y"));
            Cafe cafe3 = new Cafe("Fed-Espresso", 5, areaRepository.getByCode("G"));
            Cafe cafe4 = new Cafe("Bean There, Bean That", 3, areaRepository.getByCode("H"));
            Cafe cafe5 = new Cafe("Are You Coffee to Me?", 5, areaRepository.getByCode("C"));

            List<Cafe> cafeList = cafeRepository.saveAll(List.of(cafe1, cafe2, cafe3, cafe4, cafe5));



        }


    }
}