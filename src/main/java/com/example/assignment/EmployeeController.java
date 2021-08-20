package com.example.assignment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController	
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public @ResponseBody Employee create(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}
	
	@PutMapping("/employee/{id}")
	public Employee  update(@RequestBody Employee latestEmployee,@PathVariable int id) {
		return Optional.of(employeeService.findById(id)) 
		.map(employee -> {
			employee.setFirstName(latestEmployee.getFirstName());
			employee.setLastName(latestEmployee.getLastName());
			employee.setEmailId(latestEmployee.getEmailId());
			return employeeService.save(employee);
		}).orElseGet(() -> {
	        return employeeService.save(latestEmployee);
	      });
		
	}
		
	@GetMapping("/employee/{id}")
	  public Employee getEmployee(@PathVariable int id) {
	    return employeeService.findById(id);
	    		
	  }
	
	@GetMapping("employee")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable int id) {
		employeeService.deleteById(id);
	}
	
}
