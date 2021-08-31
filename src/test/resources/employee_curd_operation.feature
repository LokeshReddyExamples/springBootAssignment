Feature: employee_curd_operation
	Employee CRUD operation
	
	Scenario: Save employee record
		Given employee is Json record
		When  employee is successfully saved to db
		Then  it should return saved employee object

	Scenario: is to Get employee by id
		When  employee id is 33
		Then  it should return the employee record


	Scenario: is to get all employee
		Then it should return all the employees

	Scenario: is to update employee by id
		When passing latest employee with valid id 33
		Then it should update with latest employee

	Scenario: is to delete existing employee
		When passing valid employee id 57
		Then  it should delete employee with id 57

	
    