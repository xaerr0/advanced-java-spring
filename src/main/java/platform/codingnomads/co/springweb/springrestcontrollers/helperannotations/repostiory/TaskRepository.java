package platform.codingnomads.co.springweb.springrestcontrollers.helperannotations.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springweb.springrestcontrollers.helperannotations.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
