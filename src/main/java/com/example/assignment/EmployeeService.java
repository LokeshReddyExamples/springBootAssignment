package com.example.assignment;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	public Employee save (Employee employee) {
			if(Objects.nonNull(employee))
				return repository.save(employee);
			else 
				return employee;
	}
	
	public Employee findById(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new EmployeException("no record available."));
	}
	
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		 repository.deleteById(id);
	}

}
