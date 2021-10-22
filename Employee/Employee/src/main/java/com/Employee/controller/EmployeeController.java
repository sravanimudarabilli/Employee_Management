package com.Employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.exception.ResourceNotFound;
import com.Employee.model.Employee;
import com.Employee.repository.EmployeeRepository;



@RestController
public class EmployeeController {
	
@Autowired
EmployeeRepository repo;

//get all employee details
@GetMapping("/employees")
public List<Employee> getAll(){
	return repo.findAll();
}

//create employee 
@PostMapping("/employee")
public Employee create(@RequestBody Employee employee) {
	return repo.save(employee);
}

//get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}
	
	//updating employee details
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Employee not exist with id :" + id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee updatedEmployee = repo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// delete employee rest api
		@DeleteMapping("/employees/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
			Employee employee = repo.findById(id)
					.orElseThrow(() -> new ResourceNotFound("Employee not exist with id :" + id));
			
			repo.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}

}
