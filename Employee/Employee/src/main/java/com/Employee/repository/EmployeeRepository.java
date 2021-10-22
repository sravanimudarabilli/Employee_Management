package com.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	

}
