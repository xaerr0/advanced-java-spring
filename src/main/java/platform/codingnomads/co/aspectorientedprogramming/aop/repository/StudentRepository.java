package platform.codingnomads.co.aspectorientedprogramming.aop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.aspectorientedprogramming.aop.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
