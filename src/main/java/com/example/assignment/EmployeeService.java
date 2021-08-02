package com.example.assignment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	public Employee save (Employee employee) {
		return repository.save(employee);
	}
	
	public Optional<Employee> findById(int id) {
		return repository.findById(id);
	}
	
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		 repository.deleteById(id);
	}

}
