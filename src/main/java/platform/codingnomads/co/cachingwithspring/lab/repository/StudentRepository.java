package platform.codingnomads.co.cachingwithspring.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.cachingwithspring.lab.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
