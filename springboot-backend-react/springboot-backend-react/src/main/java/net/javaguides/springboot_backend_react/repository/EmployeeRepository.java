package net.javaguides.springboot_backend_react.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot_backend_react.model.Employee;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
