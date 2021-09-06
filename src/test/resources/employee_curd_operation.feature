Feature: employee_curd_operation
	Employee CRUD operation
	
	Scenario: save employee record
		Given passing valid employee record
		When  employee should successfully saved to db
		Then  it should return saved employee object

	Scenario: get employee by id
		When  employee id is 36
		Then  it should return the employee record

	Scenario: get employee by id which is not present in db
		When  passing invalid employee id
		Then  it should return empty employee record

	Scenario: get all employee
		Then it should return all the employees

	Scenario: update employee by id
		When passing latest employee with valid id
		Then it should update with latest employee record for same id
		
	
    