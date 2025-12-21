package com.cantho.luanvan.repository;

import com.cantho.luanvan.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> getEmployeeById(Long employeeId);
}
