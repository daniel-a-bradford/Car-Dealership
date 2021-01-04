package com.cardealer.entity;

import java.io.File;
import java.util.ArrayList;

public class EmployeeList {
	StringChecker check = new StringChecker(true);

	private String employeeListFileName = "EmployeeList.txt";
	private ArrayList<Employee> employees = new ArrayList<Employee>();

	public EmployeeList() {
		// Upon creation, restore the employee list from a file.
		restoreEmployeeList(this.employeeListFileName);
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	/** Method addEmployee takes a Employee object, validates it as usable, checks for a duplicate email address in the list
	 *  and if not found updates the employee list with the new employee included as the last element.
	 * 
	 * @param Employee - the Employee object to be added.
	 * @return Employee - if successful the Employee as added to the list, null if not added. */
	public Employee addEmployee(Employee newEmployee) {
		if (newEmployee == null) {
			System.out.println("Employee object is null.");
			return null;
		}
		// If new employee information is valid for a new employee (has an ID of zero),
		// add the new employee and update this.employees
		if (newEmployee.isValidEmployee()) {
			// If the email(userid) is not found in the list already, add the employee
			if ( findByEmail(newEmployee.getEmail()).size() == 0) {
				// TODO check to see if any employees in the file are more current than this
				// employee list
				// if so, load the list from the file before adding this employee and saving.

				this.employees.add(newEmployee);
				// Save the employee list to a file
				// TODO check to see if the save was successful.
				saveEmployeeList(employeeListFileName);
				return newEmployee;
			} else {
				System.out.println("addEmployee - A employee with the same employee ID or email is already in the employee list.");
				return null;
			}
		}
		System.out.println("addEmployee - Employee was not valid and was not added.");
		return null;
	}

	/** Method updateEmployee takes a Employee object, validates it as usable, then checks the existing employee list for a
	 * duplicate email which does not belong to this employee. If no duplicate is found, it removes the old employee and replaces 
	 * that employee with updatedEmployee.
	 * 
	 * @param updatedEmployee - the Employee object to be updated.
	 * @param existingEmail - the email address of the employee before the update
	 * @return boolean - true if the employee is updated, false if not. */
	public boolean updateEmployee(Employee updatedEmployee, String existingEmail) {
		if (updatedEmployee == null) {
			System.out.println("Employee object is null.");
			return false;
		}
		// If updated employee information is valid, add the new employee and update this.employees
		if (updatedEmployee.isValidEmployee()) {
			Employee existingEmployee = new Employee();
			boolean emailUpdate = false;
			if (check.isValidString(existingEmail)) {
				existingEmployee = findEmployee(existingEmail);
				if (existingEmployee != null) {
					emailUpdate = true;
				}
			} else {
				existingEmployee = findEmployee(updatedEmployee.getEmail());
			}
			// If a matching email was found, ensure the updated e-mail either does not exist in the list, or
			// if it does exist, it matches the email address of the existing employee.
			if (existingEmployee != null) {
				// If the existing email only exists once, or if the new email does not exist, add the updated employee.
				if ( (!emailUpdate && findByEmail(updatedEmployee.getEmail()).size() == 1)
						|| (emailUpdate && findByEmail(updatedEmployee.getEmail()).size() == 0) ) {
					// TODO check to see if any employees in the file are more current than this
					// employee list
					// if so, load the list from the file before adding this employee and saving.
					this.employees.remove(existingEmployee);
					this.employees.add(updatedEmployee);
					this.employees = sortByEmail(this.employees, true);
					// Save the updated employee list to a file
					// TODO check to see if the save was successful.
					saveEmployeeList(employeeListFileName);
					return true;
				}
				System.out.println("updateEmployee - Another employee has a matching email address.");
				return false;
			}
			System.out.println("updateEmployee - Employee email was not found in the current employee list.");
			return false;
		}
		System.out.println("updateEmployee - Employee was not valid and was not updated.");
		return false;
	}

	/** Method removeEmployee takes a Employee object, checks the existing employee list for a duplicate employeeID. If found, it
	 * removes the old employee.
	 * 
	 * @param removeThisEmployee - the Employee object to be removed.
	 * @return boolean - true if the employee is removed, false if not. */
	public boolean removeEmployee(Employee removeThisEmployee) {
		if (removeThisEmployee == null) {
			System.out.println("Employee object is null.");
			return false;
		}
		Employee existingEmployee = findEmployee(removeThisEmployee.getEmail());
		if (existingEmployee != null) {
			// TODO check to see if any employees in the file are more current than this
			// employee list
			// if so, load the list from the file before adding this employee and saving.
			this.employees.remove(existingEmployee);
			// Save the updated employee list to a file
			// TODO check to see if the save was successful.
			saveEmployeeList(employeeListFileName);
			return true;
		}
		System.out.println("removeEmployee - Employee ID was not found in the current employee list.");
		return false;
	}
	
	/** Method findEmployee searches the employee list for a full matching email and returns the employee
	 * it finds, or null if none found. Match is not case sensitive.
	 * 
	 * @param email - the email for which to search
	 * @return Employee - the employee with the matching email or null if none found. */
	public Employee findEmployee(String email) {
		if (!check.isEmail(email)) {
			return null;
		}
		email = email.trim();
		for (Employee tempEmployee : this.employees) {
			if (tempEmployee.getEmail().equalsIgnoreCase(email)) {
				return tempEmployee;
			}
		}
		return null;
	}

	/** Method findByName searches the employee list for a partial or full matching name and returns an ArrayList of employees it
	 * finds. Match is not case sensitive.
	 * 
	 * @param name - the full name for which to search
	 * @return ArrayList<Employee> - an ArrayList of all matching Employee objects */
	public ArrayList<Employee> findByName(String name) {
		if (!check.isValidString(name)) {
			return new ArrayList<Employee>();
		}
		name = name.trim().toLowerCase();
		ArrayList<Employee> results = new ArrayList<Employee>();
		String tempString = "";
		for (Employee tempEmployee : this.employees) {
			tempString = tempEmployee.getName().toLowerCase();
			if (tempString.contains(name)) {
				results.add(tempEmployee);
			}
		}
		return results;
	}

	/** Method findByEmail searches the employee list for a partial or full matching email and returns an ArrayList of employees
	 * it finds. Match is not case sensitive.
	 * 
	 * @param email - the email for which to search
	 * @return ArrayList<Employee> - an ArrayList of all matching Employee objects */
	public ArrayList<Employee> findByEmail(String email) {
		if (!check.isValidString(email)) {
			return new ArrayList<Employee>();
		}
		email = email.trim().toLowerCase();
		ArrayList<Employee> results = new ArrayList<Employee>();
		String tempString = "";
		for (Employee tempEmployee : this.employees) {
			tempString = tempEmployee.getEmail().toLowerCase();
			if (tempString.contains(email)) {
				results.add(tempEmployee);
			}
		}
		return results;
	}
	
	/** Method sortByName sorts an ArrayList of Employee by full name as specified by alphabeticalOrder. If alphabeticalOrder is
	 * true: Bubble sort from in alphabetical order. If alphabeticalOrder is false: Bubble sort from in reverse alphabetical
	 * order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Employee> - the sorted list */
	public ArrayList<Employee> sortByName(ArrayList<Employee> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getName();
				String current = inputList.get(in).getName();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Employee temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/** Method sortByEmail sorts an ArrayList of Employee by email as specified by alphabeticalOrder. If alphabeticalOrder is
	 * true: Bubble sort from in alphabetical order. If alphabeticalOrder is false: Bubble sort from in reverse alphabetical
	 * order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Employee> - the sorted list */
	public ArrayList<Employee> sortByEmail(ArrayList<Employee> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getEmail();
				String current = inputList.get(in).getEmail();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Employee temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/** Method saveEmployeeList checks if the employeesFileName is writeable and then replaces the entire contents of that file
	 * with all the employees in the current employees attribute. Each employee is stored on a separate line in the file. It
	 * returns false if the file was not valid or writable
	 * 
	 * @param employeesFileName
	 * @return boolean */
	private boolean saveEmployeeList(String employeesFileName) {
		// Check to see if the inventory data file is an existing file.
		File employeesFile = new File(employeesFileName);
		if (!employeesFile.isFile() && !employeesFile.canWrite()) {
			System.out.println(employeesFileName + " does not exist or is not accessible for writing.");
			FileReadWrite.createOutputFile(employeesFileName);
		}
		String fileOutputString = "";
		for (Employee tempEmployee : this.employees) {
			fileOutputString += tempEmployee.toDataFileString() + System.lineSeparator();
		}
		return FileReadWrite.writeToFile(employeesFile, fileOutputString);
	}

	/** method restoreEmployeeList checks if the file indicated by the string employeesFileName is present and readable then
	 * populates the employees attribute with the values found in the file. It returns false if the file does not exist or is not
	 * readable. The console will show the number of errors encountered reading the file.
	 * 
	 * @param employeesFileName
	 * @return boolean */
	private boolean restoreEmployeeList(String employeesFileName) {
		ArrayList<String> fileStringArray = new ArrayList<String>();
		File employeesFile = new File(employeesFileName);
		if (employeesFile.isFile() && employeesFile.canRead()) {
			fileStringArray = FileReadWrite.readFileToStringList(employeesFile.getName());
		} else {
			System.out.println(employeesFileName + " does not exist or is not accessible for reading.");
			return false;
		}
		Employee readEmployee = new Employee();
		int errorCount = 0;
		for (int index = 0; index < fileStringArray.size(); index++) {
			if (!readEmployee.parseEmployeeString(fileStringArray.get(index))) {
				errorCount++;
			} else {
				this.employees.add(readEmployee);
				readEmployee = new Employee();
			}
			// For troubleshooting
			// System.out.println(readEmployee.toDataFileString());
			// System.out.println(readEmployee.toString());
		}
		System.out.println(employeesFileName + " import produced " + errorCount + " errors.");
		return true;
	}

	@Override
	public String toString() {
		if (this.employees.size() == 0) {
			return "There are no employees in the list.";
		}
		String outputString = "";
		for (Employee tempEmployee : this.employees) {
			outputString += (tempEmployee.toString() + "\n");
		}
		return outputString;
	}
}
