package net.javaguides.springboot_backend_react.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot_backend_react.exception.ResourceNotFoundException;
import net.javaguides.springboot_backend_react.model.Employee;
import net.javaguides.springboot_backend_react.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
	    return employeeRepository.save(employee);
	}
	
	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	    Employee employee = employeeRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
	    return ResponseEntity.ok(employee);
	}
	
	// update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
	    Employee employee = employeeRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

	    employee.setFirstName(employeeDetails.getFirstName());
	    employee.setLastName(employeeDetails.getLastName());
	    employee.setEmailId(employeeDetails.getEmailId());

	    Employee updatedEmployee = employeeRepository.save(employee);
	    return ResponseEntity.ok(updatedEmployee);
	}
	
	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
	    Employee employee = employeeRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
	    employeeRepository.delete(employee);
	    return ResponseEntity.noContent().build();
	}

	


}