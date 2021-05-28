package platform.codingnomads.co.cachingwithspring.demo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.codingnomads.co.cachingwithspring.demo.exception.ResourceNotFoundException;
import platform.codingnomads.co.cachingwithspring.demo.domain.Employee;
import platform.codingnomads.co.cachingwithspring.demo.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }


    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping("employees/{employeeId}")
    @Cacheable(value = "employees", key = "#employeeId",sync = true)
    public Employee findEmployeeById(@PathVariable(value = "employeeId") Integer employeeId) {
        System.out.println("Employee fetching from database:: " + employeeId);
        return employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found" + employeeId));

    }


    @PutMapping("employees/{employeeId}")
    @CachePut(value = "employees", key = "#employeeId")
    public Employee updateEmployee(@PathVariable(value = "employeeId") Integer employeeId,
                                   @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employee.setName(employeeDetails.getName());
        return employeeRepository.save(employee);
    }


    @DeleteMapping("employees/{id}")
    @CacheEvict(value = "employees", allEntries = true)
    public void deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found" + employeeId));
        employeeRepository.delete(employee);
    }
}
