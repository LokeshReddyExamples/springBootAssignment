package com.example.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController	
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping(path="/create")
	public @ResponseBody Employee create(@RequestBody Employee employee) {
		System.out.println(employee.getFirstName());
		Employee object = new Employee();
		object.setFirstName(employee.getFirstName());
		object.setLastName(employee.getLastName());
		object.setEmailId(employee.getEmailId());
		return employeeRepository.save(object);
	}

	
}
