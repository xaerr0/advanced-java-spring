package platform.codingnomads.co.springdata.lab_complete;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springdata.lab_complete.domain.Area;
import platform.codingnomads.co.springdata.lab_complete.domain.Route;
import platform.codingnomads.co.springdata.lab_complete.repository.AreaRepository;
import platform.codingnomads.co.springdata.lab_complete.repository.RouteRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataLab implements CommandLineRunner {

    private final AreaRepository areaRepository;
    private final RouteRepository routeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataLab.class);
    }

    @Override
    public void run(String... args) throws Exception {
        // don't attempt to add these records again on future runs
        if (areaRepository.findAll().size() == 0) {
            final List<Area> areas = areaRepository.saveAll(
                    Arrays.asList(
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
                            Route.builder().origin(areaRepository.findByCode("H"))
                                    .destination(areaRepository.findByCode("Z")).build()
                    )
            );
        }

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
