package com.example.assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class StepDefinitions {

	@Autowired
	private EmployeeController controller;

	@Autowired
	private EmployeeHttpClient employeeHttpClient;

	private Employee actualResult;

	private Employee expectedObject = new Employee();

	private  String msg = "";

	private ResponseEntity responseEntity;

	private HttpClientErrorException exception;

	@Given("passing valid employee record")
	public void passingValidEmployeeRecord() {
		expectedObject.setEmailId("joy@gmail.com");
		expectedObject.setFirstName("joy");
		expectedObject.setLastName("R");
	}
	@When("employee should successfully saved to db")
	public void employeeShouldSuccessfullySavedToDb() {
		actualResult = employeeHttpClient.put(expectedObject);
	}
	@Then("it should return saved employee object")
	public void it_should_return_saved_employee_object() {
		assertEquals(expectedObject.getFirstName(), actualResult.getFirstName());
	}

	@When("employee id is {int}")
	public void employeeIdIs(Integer id) {
			ResponseEntity responseEntity = employeeHttpClient.getContents(id);
			actualResult = (Employee) responseEntity.getBody();
	}
	@Then("it should return the employee record")
	public void itShouldReturnTheEmployeeRecord() {
		assertEquals(36,actualResult.getId());
	}

	@When("passing invalid employee id")
	public void passingInvalidEmployeeId() {
		 exception = assertThrows(HttpClientErrorException.class,() ->{
			employeeHttpClient.getContents(-1);
		});

	}
	@Then("it should return empty employee record")
	public void itShouldReturnEmptyEmployeeRecord() throws JsonProcessingException {
		assertEquals(HttpStatus.NOT_FOUND,exception.getStatusCode());

	}

	@Then("it should return all the employees")
	public void itShouldReturnAllTheEmployees() {
			List<Employee> list = employeeHttpClient.getContents();
			Assert.assertTrue(list instanceof List);
	}

	@When("passing latest employee with valid id")
	public void passingLatestEmployeeWithValidId() {
		expectedObject.setFirstName("update_f_name");
		expectedObject.setLastName("update_l_name");
		employeeHttpClient.put(expectedObject,88);
	}
	@Then("it should update with latest employee record for same id")
	public void itShouldUpdateWithLatestEmployee() {
			assertEquals(expectedObject.getFirstName(),((Employee)employeeHttpClient.getContents(88).getBody()).getFirstName());
	}

	@When("passing valid employee id {int}")
	public void passingValidEmployeeId(Integer id) {
			employeeHttpClient.delete(id);
	}

	@Then("it should delete employee with id {int}")
	public void itShouldDeleteEmployeeWithId(Integer empId) {
		assertFalse(employeeHttpClient.getContents().stream()
				 .anyMatch(employee -> employee.getId()==empId));
		}

}