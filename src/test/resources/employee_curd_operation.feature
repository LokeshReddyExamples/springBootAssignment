Feature: employee_curd_operation
	Employee CRUD operation
	
	Scenario: Save employee record
		Given passing valid employee record
		When  employee should successfully saved to db
		Then  it should return saved employee object

#	Scenario: Save employee record null
#		Given passing null employee record
#		When  employee should not successfully save to db
#		Then  it should return null

	Scenario: is to Get employee by id
		When  employee id is 36
		Then  it should return the employee record

	Scenario: is to Get employee by id which is not present in db
		When  passing invalid employee id
		Then  it should return empty employee record

	Scenario: is to get all employee
		Then it should return all the employees

	Scenario: is to update employee by id
		When passing latest employee with valid id
		Then it should update with latest employee record for same id

#	Scenario: is to delete existing employee
#		When passing valid employee id 57
#		Then  it should delete employee with id 57

	
    