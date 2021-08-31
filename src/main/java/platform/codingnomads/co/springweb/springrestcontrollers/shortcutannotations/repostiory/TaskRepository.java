package platform.codingnomads.co.springweb.springrestcontrollers.shortcutannotations.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springweb.springrestcontrollers.shortcutannotations.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
