package com.minhtien.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhtien.app.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContaining(String q);

}
