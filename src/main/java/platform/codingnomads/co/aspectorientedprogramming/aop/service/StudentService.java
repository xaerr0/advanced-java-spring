package platform.codingnomads.co.aspectorientedprogramming.aop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.aspectorientedprogramming.aop.aspect.Loggable;
import platform.codingnomads.co.aspectorientedprogramming.aop.aspect.TrackMethodExecutionTime;
import platform.codingnomads.co.aspectorientedprogramming.aop.model.Student;
import platform.codingnomads.co.aspectorientedprogramming.aop.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

//    @TrackMethodExecutionTime
//    @Loggable
    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

//    @TrackMethodExecutionTime
//    @Loggable
    public List<Student> saveAllStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }
}
