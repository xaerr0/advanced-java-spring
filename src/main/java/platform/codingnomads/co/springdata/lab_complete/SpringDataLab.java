package platform.codingnomads.co.springdata.lab_complete;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import platform.codingnomads.co.springdata.lab_complete.models.Area;
import platform.codingnomads.co.springdata.lab_complete.models.PointOfInterest;
import platform.codingnomads.co.springdata.lab_complete.models.Route;
import platform.codingnomads.co.springdata.lab_complete.repositories.AreaRepository;
import platform.codingnomads.co.springdata.lab_complete.repositories.PointOfInterestRepository;
import platform.codingnomads.co.springdata.lab_complete.repositories.RouteRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataLab implements CommandLineRunner {

    private final AreaRepository areaRepository;
    private final RouteRepository routeRepository;
    private final PointOfInterestRepository pointOfInterestRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataLab.class);
    }

    @Override
    @Transactional
    // this will keep toString() methods from throwing an exception due to lazy loading
    public void run(String... args) throws Exception {
        // don't attempt to add these records again on future runs
       if (areaRepository.findAll().size() == 0) {
            final List<Area> areas = areaRepository.saveAll(
                    Arrays.asList(
                            Area.builder().code("A").build(),
                            Area.builder().code("B").build(),
                            Area.builder().code("C").build(),
                            Area.builder().code("G").build(),
                            Area.builder().code("H").build(),
                            Area.builder().code("L").build(),
                            Area.builder().code("O").build(),
                            Area.builder().code("Y").build(),
                            Area.builder().code("Z").build()
                    )
            );
        }

        if (routeRepository.findAll().size() == 0) {
            final List<Route> routes = routeRepository.saveAll(
                    Arrays.asList(
                            Route.builder().origin(areaRepository.findByCode("L"))
                                    .destination(areaRepository.findByCode("O")).build(),
                            Route.builder().origin(areaRepository.findByCode("Y"))
                                    .destination(areaRepository.findByCode("Z")).build(),
                            Route.builder().origin(areaRepository.findByCode("H"))
                                    .destination(areaRepository.findByCode("Y")).build(),
                            Route.builder().origin(areaRepository.findByCode("A"))
                                    .destination(areaRepository.findByCode("Z")).build(),
                            Route.builder().origin(areaRepository.findByCode("B"))
                                    .destination(areaRepository.findByCode("A")).build(),
                            Route.builder().origin(areaRepository.findByCode("H"))
                                    .destination(areaRepository.findByCode("Z")).build()
                    )
            );
        }

        if (pointOfInterestRepository.findAll().size() == 0) {

            PointOfInterest poi1 = new PointOfInterest("Gas Station", "Buc-ee's",
                    areaRepository.findByCode("A"));
            // if a poi has an associated area, it may have routes as well
            poi1.addRoutes(routeRepository.findAllByCodeContaining("A"));

            PointOfInterest poi2 = new PointOfInterest("Gas Station", "Shell",
                    areaRepository.findByCode("Z"));
            poi2.addRoutes(routeRepository.findAllByCodeContaining("Z"));

            PointOfInterest poi3 = new PointOfInterest("Restaurant", "Brick House",
                    areaRepository.findByCode("O"));
            poi3.addRoutes(routeRepository.findAllByCodeContaining("O"));

            PointOfInterest poi4 = new PointOfInterest("Restaurant", "Nirmanz",
                    areaRepository.findByCode("L"));
            poi4.addRoutes(routeRepository.findAllByCodeContaining("L"));

            // a poi can be associated with a route only, not a particular area (somewhere between 2 areas)
            PointOfInterest poi5 = new PointOfInterest("Roadside Attraction", "Ufo",
                    routeRepository.findByCode("B-A"));
            PointOfInterest poi6 = new PointOfInterest("Roadside Attraction", "Waterfall",
                    routeRepository.findByCode("H-Y"));

            final List<PointOfInterest> poiList = pointOfInterestRepository.saveAll(
                    List.of(poi1, poi2, poi3, poi4, poi5, poi6)
            );
        }

        System.out.println(pointOfInterestRepository.findAllByArea_code("A"));
        System.out.println(pointOfInterestRepository.findAllDistinctByRoutes_codeContaining("A"));

        System.out.println(areaRepository.findAllByPointsOfInterest_typeIgnoreCase("gas station"));
        System.out.println(areaRepository.findByCode("A"));

        System.out.println(routeRepository.findByOrigin_code("T"));
        System.out.println(routeRepository.findByOrigin_code("Y"));
        System.out.println(routeRepository.findByOrigin_code("H"));

        System.out.println(routeRepository.findByDestination_code("R"));
        System.out.println(routeRepository.findByDestination_code("Y"));
        System.out.println(routeRepository.findByDestination_code("Z"));

        System.out.println(routeRepository.findByCode("T-Z"));
        System.out.println(routeRepository.findByCode("H-Z"));
        System.out.println(routeRepository.findByCode("L-O"));
    }
}
