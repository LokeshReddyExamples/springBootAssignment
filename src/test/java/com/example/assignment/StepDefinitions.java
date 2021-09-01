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
	public void passing_valid_employee_record() {
		expectedObject.setEmailId("joy@gmail.com");
		expectedObject.setFirstName("joy");
		expectedObject.setLastName("R");
	}
	@When("employee should successfully saved to db")
	public void employee_should_successfully_saved_to_db() {
		actualResult = employeeHttpClient.put(expectedObject);
	}
	@Then("it should return saved employee object")
	public void it_should_return_saved_employee_object() {
		assertEquals(expectedObject.getFirstName(), actualResult.getFirstName());
	}

	@When("employee id is {int}")
	public void employee_id_is(Integer id) {
			ResponseEntity responseEntity = employeeHttpClient.getContents(id);
			actualResult = (Employee) responseEntity.getBody();
	}
	@Then("it should return the employee record")
	public void it_should_return_the_employee_record() {
		assertEquals(36,actualResult.getId());
	}

	@When("passing invalid employee id {int}")
	public void passing_invalid_employee_id(Integer id) {
		 exception = assertThrows(HttpClientErrorException.class,() ->{
			employeeHttpClient.getContents(id);
		});

	}
	@Then("it should return empty employee record")
	public void it_should_return_empty_employee_record() throws JsonProcessingException {
		assertEquals(HttpStatus.NOT_FOUND,exception.getStatusCode());

	}

	@Then("it should return all the employees")
	public void it_should_return_all_the_employees() {
			List<Employee> list = employeeHttpClient.getContents();
			Assert.assertTrue(list instanceof List);
	}

	@When("passing latest employee with valid id {int}")
	public void passing_latest_employee_with_valid_id(Integer id) {
		expectedObject.setFirstName("update_f_name");
		expectedObject.setLastName("update_l_name");
		employeeHttpClient.put(expectedObject,id);
	}
	@Then("it should update with latest employee record for same id {int}")
	public void it_should_update_with_latest_employee(Integer id) {
			assertEquals(expectedObject.getFirstName(),((Employee)employeeHttpClient.getContents(id).getBody()).getFirstName());
	}

	@When("passing valid employee id {int}")
	public void passing_valid_employee_id(Integer id) {
			employeeHttpClient.delete(id);
	}

	@Then("it should delete employee with id {int}")
	public void it_should_delete_employee_with_id(Integer empId) {
		assertFalse(employeeHttpClient.getContents().stream()
				 .anyMatch(employee -> employee.getId()==empId));
		}

}