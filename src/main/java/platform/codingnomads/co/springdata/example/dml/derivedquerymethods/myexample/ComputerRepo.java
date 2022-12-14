package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.myexample;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ComputerRepo extends JpaRepository<Computer, Long> {

    List<Computer> findByMonitor(String monitor);

    List<Computer> findByForGamingIsTrue();

    Computer findFirstByMouse(String mouse);

    List<Computer> findByKeyboardContainingAndForGaming(String partialKeyboardName, Boolean forGaming);

    List<Computer> findTop5DistinctByForGamingIsTrue(Sort sort);

    List<Computer> findByKeyboardIsNot(String keyboard);

    List<Computer> findByMouseStartingWith(String monitorPrefix);

    List<Computer> findByMonitorEndingWith(String monitorSuffix);

    List<Computer> findTop2ByMouse(String mouse);

    Integer countByKeyboard(String keyboard);

    List<Computer> findByForGamingIs(Boolean forGaming);
}