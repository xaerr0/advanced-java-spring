package platform.codingnomads.co.springdata.example.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.example.jpa.domain.CodeWarrior;

import java.util.List;

public interface CodeWarriorRepository extends JpaRepository<CodeWarrior,Long> {

    public List<CodeWarrior> findByIdGreaterThan(int i);
    public List<CodeWarrior> findByNameLikeAndEmailEqual(String name, String email);
}