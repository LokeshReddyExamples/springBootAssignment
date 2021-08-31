package com.example.assignment;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
		actualResult = employeeHttpClient.getContents(id);
	}
	@Then("it should return the employee record")
	public void it_should_return_the_employee_record() {
		assertEquals(33,actualResult.getId());
	}

	@Then("it should return all the employees")
	public void it_should_return_all_the_employees() {
			List<Employee> list = employeeHttpClient.getContents();
			Assert.assertTrue(list instanceof List);
	}

	@When("passing latest employee with valid id {int}")
	public void passing_latest_employee_with_valid_id(Integer id) {

	}
	@Then("it should update with latest employee")
	public void it_should_update_with_latest_employee() {

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