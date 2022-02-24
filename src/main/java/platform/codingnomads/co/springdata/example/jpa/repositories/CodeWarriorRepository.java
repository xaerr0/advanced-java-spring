package platform.codingnomads.co.springdata.example.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.example.jpa.domain.CodeWarrior;

public interface CodeWarriorRepository extends JpaRepository<CodeWarrior,Long> {
}
