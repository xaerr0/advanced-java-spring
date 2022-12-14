package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.codewarriorexample.myexample;

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

    //TODO How to use this one?
//    List<Computer> findTop5DistinctForGamingIsTrue(Sort sort);

    List<Computer> findByKeyboardIsNot(String keyboard);

    List<Computer> findByMouseStartingWith(String monitorPrefix);

    List<Computer> findByMonitorEndingWith(String monitorSuffix);

    List<Computer> findTop2ByMouse(String mouse);

    Integer countByKeyboard(String keyboard);
}