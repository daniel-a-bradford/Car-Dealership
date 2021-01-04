package com.cardealer.entity;

public class InitEmployees {

	/* *****NOTE, this method initializes data files, but saves them under the project workspace root folder.
	 * When running on a server, the project expects the data files to be in the user's desktop folder, so
	 * the files must be copied there in order to be found. 
	 */
	public static void main(String[] args) {
//			public Employee(String name, String email, String password) 
		Employee employee1 = new Employee
				("Dan Bradford",
				"db@carsrus.com",
				"a");
		Employee employee2 = new Employee
				("Manny Schavitz",
				"ms@carsrus.com",
				"a");
		EmployeeList empList = new EmployeeList();
		employee1 = empList.addEmployee(employee1);
		employee2 = empList.addEmployee(employee2);
	}

}
