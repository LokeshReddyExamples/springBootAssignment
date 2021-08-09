package com.example.assignment;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private EmployeeService employeeService;
	@InjectMocks
	private EmployeeController controller;
	
	/*
	 * @Before private void init(){ System.out.println("Hello  "); }
	 */
	
	@Test
	public void createTest() throws Exception {
		Employee employee = new Employee();
		employee.setFirstName("Thor");
		employee.setLastName("RR");
		employee.setEmailId("thor@gmal.com");
		String inputInJson = this.mapToJson(employee);
		when(employeeService.save(any(Employee.class))).thenReturn(employee);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/create")
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult   mvcResult = mockMvc.perform(requestBuilder).andReturn();
		String outPutInJson = mvcResult.getRequest().getContentAsString();
		Assert.assertEquals(inputInJson, outPutInJson);
	}
	
	@Test
	public void updateTest() throws Exception {
		Employee employee = new Employee();
		employee.setFirstName("Thor");
		employee.setLastName("RR");
		employee.setEmailId("thor@gmal.com");
		Optional<Employee> newEmployee = Optional.of(employee);
		
		String inputInJson = this.mapToJson(employee);
		when(employeeService.findById(any(Integer.class)))
		.thenReturn(employee);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/update/"+1)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult   mvcResult = mockMvc.perform(requestBuilder).andReturn();
		String outPutInJson = mvcResult.getRequest().getContentAsString();
		Assert.assertEquals(inputInJson, outPutInJson);
	}
	
	@Test
	public void getAllEmployeesTest() throws Exception {
		Employee e1 = new Employee();
		e1.setFirstName("name-1");
		Employee e2 = new Employee();
		e2.setFirstName("name-2");
		Employee e3 = new Employee();
		e3.setFirstName("name-3");
		List<Employee> empList = new ArrayList<Employee>(Arrays.asList(e1,e2,e3));
		when(employeeService.getAllEmployees()).thenReturn(empList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getAllEmployees");
		MvcResult   mvcResult = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(this.mapToJson(empList),mvcResult.getResponse().getContentAsString());
	}
	

	@Test
	public void getEmployeeReturnsObjectTest() throws Exception {
			Employee employee = new Employee();
			employee.setFirstName("F-1");
			when(employeeService.findById(Matchers.anyInt())).thenReturn(employee);
		    RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getEmployee/"+1);
		    	MvcResult   mvcResult = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertEquals(this.mapToJson(employee), mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void deleteEmployeeTest() throws Exception {
		int id = 1;
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/delete/"+id);
		mockMvc.perform(requestBuilder).andReturn();
	}
	
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	
}
