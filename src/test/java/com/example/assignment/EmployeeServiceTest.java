package com.example.assignment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository repository;
	@InjectMocks
	private EmployeeService service;
	
	@Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void saveTest() {
        Employee employee = new Employee();
        employee.setFirstName("Hulk");
        employee.setLastName("HK");
        when(repository.save(any(Employee.class))).thenReturn(employee);
        Employee newEmployee = service.save(new Employee());
        Assert.assertEquals("Hulk", newEmployee.getFirstName());
	}
	@Test
	public void saveNullCheckTest() {
        Employee newEmployee = service.save(null);
        Assert.assertEquals(null, newEmployee);
	}
	
	@Test
	public void findByIdTest() {
		 Employee emp = new Employee();
		 emp.setFirstName("selected one");
		 when(repository.findById(anyInt())).thenReturn(Optional.of(emp));
         Employee newEmployee = service.findById(1);
         Assert.assertEquals(emp.getFirstName(), newEmployee.getFirstName());
	}
	
	@Test
	public void getAllEmployeesTest() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Hulk");
        employee1.setLastName("HK");
        Employee employee2 = new Employee();
        employee2.setFirstName("Dr strange");
        employee2.setLastName("strange");
        List<Employee> mockEmpLIst = new ArrayList<Employee>(Arrays.asList(employee1,employee2)); 
        when(repository.findAll()).thenReturn(mockEmpLIst);
        List<Employee> actalEmpList = service.getAllEmployees();
        Assert.assertEquals(actalEmpList.size(), actalEmpList.size());
	}
	
}
