package com.cardealer.entity;

import java.io.File;
import java.util.ArrayList;

public class CustomerList {
	StringChecker check = new StringChecker(true);

	private String customerListFileName = "CustomerList.txt";
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private long nextCustomerID = 1L;

	public CustomerList() {
		// Upon creation, restore the customer list from a file.
		restoreCustomerList(this.customerListFileName);
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	/** Method addCustomer takes a Customer object, validates it as usable, then assigns the next CustomerNumber, and updates the
	 * customer list with the new customer included as the last element.
	 * 
	 * @param Customer - the Customer object to be added.
	 * @return Customer - if successful the Customer as added to the list with ID assigned, null if not added. */
	public Customer addCustomer(Customer newCustomer) {
		if (newCustomer == null) {
			System.out.println("Customer object is null.");
			return null;
		}
		// If new customer information is valid for a new customer (has an ID of zero),
		// add the new customer and update this.customers
		if (newCustomer.isValidCustomer(true)) {
			// If the customerID is not found and the email(userid) is not found, add the customer
			if (findCustomer(newCustomer.getCustomerID()) == null && findByEmail(newCustomer.getEmail()).size() == 0) {
				// TODO check to see if any customers in the file are more current than this
				// customer list
				// if so, load the list from the file before adding this customer and saving.

				// Set the customerID and increment nextCustomerID
				newCustomer.setCustomerID(this.nextCustomerID);
				this.nextCustomerID++;
				this.customers.add(newCustomer);
				this.customers = sortByID(this.customers, true);
				// Save the customer list to a file
				// TODO check to see if the save was successful.
				saveCustomerList(customerListFileName);
				return newCustomer;
			} else {
				System.out.println("addCustomer - A customer with the same customer ID or email is already in the customer list.");
				return null;
			}
		}
		System.out.println("addCustomer - Customer was not valid and was not added.");
		return null;
	}

	/** Method updateCustomer takes a Customer object, validates it as usable, then checks the existing customer list for a
	 * duplicate customerID or email. If no duplicates found, it removes the old customer and replaces that customer with 
	 * updatedCustomer.
	 * 
	 * @param Customer - the Customer object to be updated.
	 * @return boolean - true if the customer is updated, false if not. */
	public boolean updateCustomer(Customer updatedCustomer) {
		if (updatedCustomer == null) {
			System.out.println("Customer object is null.");
			return false;
		}
		// If updated customer information is valid, add the new customer and update this.customers
		if (updatedCustomer.isValidCustomer(false)) {
			Customer existingCustomer = findCustomer(updatedCustomer.getCustomerID());
			// If a matching customer ID was found, ensure the updated e-mail either does not exist in the list, or
			// if it does exist, it matches the email address of the existing customer.
			if (existingCustomer != null) {
				if (findByEmail(updatedCustomer.getEmail()).size() == 0
						|| existingCustomer.getEmail().equalsIgnoreCase(updatedCustomer.getEmail())) {
					// TODO check to see if any customers in the file are more current than this
					// customer list
					// if so, load the list from the file before adding this customer and saving.
					this.customers.remove(existingCustomer);
					this.customers.add(updatedCustomer);
					this.customers = sortByID(this.customers, true);
					// Save the updated customer list to a file
					// TODO check to see if the save was successful.
					saveCustomerList(customerListFileName);
					return true;
				}
				System.out.println("updateCustomer - Another customer has a matching email address.");
				return false;
			}
			System.out.println("updateCustomer - Customer ID was not found in the current customer list.");
			return false;
		}
		System.out.println("updateCustomer - Customer was not valid and was not updated.");
		return false;
	}

	/** Method removeCustomer takes a Customer object, checks the existing customer list for a duplicate customerID. If found, it
	 * removes the old customer.
	 * 
	 * @param removeThisCustomer - the Customer object to be removed.
	 * @return boolean - true if the customer is removed, false if not. */
	public boolean removeCustomer(Customer removeThisCustomer) {
		if (removeThisCustomer == null) {
			System.out.println("Customer object is null.");
			return false;
		}
		Customer existingCustomer = findCustomer(removeThisCustomer.getCustomerID());
		if (existingCustomer != null) {
			// TODO check to see if any customers in the file are more current than this
			// customer list
			// if so, load the list from the file before adding this customer and saving.
			this.customers.remove(existingCustomer);
			// Save the updated customer list to a file
			// TODO check to see if the save was successful.
			saveCustomerList(customerListFileName);
			return true;
		}
		System.out.println("removeCustomer - Customer ID was not found in the current customer list.");
		return false;
	}

	/** Method findCustomer searches the customer list for a matching customer ID and returns the matching customer. If no match
	 * is found returns null.
	 * 
	 * @param searchID - the customerID for which to search stored as a long integer
	 * @return Customer - the Customer which was found, or null if there was no matching ID. */
	public Customer findCustomer(long searchID) {
		for (int index = 0; index < this.customers.size(); index++) {
			if (this.customers.get(index).getCustomerID() == searchID) {
				return this.customers.get(index);
			}
		}
		return null;
	}

	/** Method findByName searches the customer list for a partial or full matching name and returns an ArrayList of customers it
	 * finds. Match is not case sensitive.
	 * 
	 * @param name - the full name for which to search
	 * @return ArrayList<Customer> - an ArrayList of all matching Customer objects */
	public ArrayList<Customer> findByName(String name) {
		if (!check.isValidString(name)) {
			return new ArrayList<Customer>();
		}
		name = name.trim().toLowerCase();
		ArrayList<Customer> results = new ArrayList<Customer>();
		String tempString = "";
		for (Customer tempCustomer : this.customers) {
			tempString = tempCustomer.getName().toLowerCase();
			if (tempString.contains(name)) {
				results.add(tempCustomer);
			}
		}
		return results;
	}

	/** Method findByEmail searches the customer list for a partial or full matching email and returns an ArrayList of customers
	 * it finds. Match is not case sensitive.
	 * 
	 * @param email - the email for which to search
	 * @return ArrayList<Customer> - an ArrayList of all matching Customer objects */
	public ArrayList<Customer> findByEmail(String email) {
		if (!check.isValidString(email)) {
			return new ArrayList<Customer>();
		}
		email = email.trim().toLowerCase();
		ArrayList<Customer> results = new ArrayList<Customer>();
		String tempString = "";
		for (Customer tempCustomer : this.customers) {
			tempString = tempCustomer.getEmail().toLowerCase();
			if (tempString.contains(email)) {
				results.add(tempCustomer);
			}
		}
		return results;
	}

	/** Method findByPhone searches the customer list for a full matching phone number and returns an ArrayList of customers it
	 * finds.
	 * 
	 * @param phone - the phone number for which to search
	 * @return ArrayList<Customer> - an ArrayList of all matching Customer objects */
	public ArrayList<Customer> findByPhone(long phone) {
		String phoneString = Long.toString(phone);
		ArrayList<Customer> results = new ArrayList<Customer>();
		String tempString = "";
		for (Customer tempCustomer : this.customers) {
			tempString = Long.toString(tempCustomer.getPhone());
			if (tempString.contains(phoneString)) {
				results.add(tempCustomer);
			}
		}
		return results;
	}

	/** Method findByStreet searches the customer list for a partial or full matching of their street address and returns an
	 * ArrayList of customers it finds. Match is not case sensitive.
	 * 
	 * @param street - the street number and name for which to search
	 * @return ArrayList<Customer> - an ArrayList of all matching Customer objects */
	public ArrayList<Customer> findByStreet(String street) {
		if (!check.isValidString(street)) {
			return new ArrayList<Customer>();
		}
		street = street.trim().toLowerCase();
		ArrayList<Customer> results = new ArrayList<Customer>();
		String tempString = "";
		for (Customer tempCustomer : this.customers) {
			tempString = tempCustomer.getAddress().getStreet1().toLowerCase() + " "
					+ tempCustomer.getAddress().getStreet2().toLowerCase();
			if (tempString.contains(street)) {
				results.add(tempCustomer);
			}
		}
		return results;
	}

	/** Method findByCity searches the customer list for a partial or full matching of their city and returns an ArrayList of
	 * customers it finds. Match is not case sensitive.
	 * 
	 * @param city - the city name for which to search
	 * @return ArrayList<Customer> - an ArrayList of all matching Customer objects */
	public ArrayList<Customer> findByCity(String city) {
		if (!check.isValidString(city)) {
			return new ArrayList<Customer>();
		}
		city = city.trim().toLowerCase();
		ArrayList<Customer> results = new ArrayList<Customer>();
		String tempString = "";
		for (Customer tempCustomer : this.customers) {
			tempString = tempCustomer.getAddress().getCity().toLowerCase();
			if (tempString.contains(city)) {
				results.add(tempCustomer);
			}
		}
		return results;
	}

	/** Method findByState searches the customer list for a partial or full matching of their state abbreviation and returns an
	 * ArrayList of customers it finds. Match is not case sensitive.
	 * 
	 * @param state - the state abbreviation for which to search
	 * @return ArrayList<Customer> - an ArrayList of all matching Customer objects */
	public ArrayList<Customer> findByState(String state) {
		if (!check.isValidString(state)) {
			return new ArrayList<Customer>();
		}
		state = state.trim().toLowerCase();
		ArrayList<Customer> results = new ArrayList<Customer>();
		String tempString = "";
		for (Customer tempCustomer : this.customers) {
			tempString = tempCustomer.getAddress().getState().toLowerCase();
			if (tempString.contains(state)) {
				results.add(tempCustomer);
			}
		}
		return results;
	}

	/** Method findByZip searches the customer list for a partial or full matching of their zip code and zip+4 (if not zero) and
	 * returns an ArrayList of customers it finds. Match is not case sensitive.
	 * 
	 * @param zip - the zip code for which to search
	 * @return ArrayList<Customer> - an ArrayList of all matching Customer objects */
	public ArrayList<Customer> findByZip(int zip) {
		String zipString = Integer.toString(zip);
		ArrayList<Customer> results = new ArrayList<Customer>();
		String tempString = "";
		for (Customer tempCustomer : this.customers) {
			tempString = Integer.toString(tempCustomer.getAddress().getZip());
			if (tempCustomer.getAddress().getZipPlus4() != 0) {
				tempString += tempCustomer.getAddress().getZipPlus4();
			}
			if (tempString.contains(zipString)) {
				results.add(tempCustomer);
			}
		}
		return results;
	}

	/** Method sortByID sorts an ArrayList of Customer by CustomerID depending on leastToGreatest. If leastToGreatest is true:
	 * Bubble sort from least to greatest. If leastToGreatest is false: Bubble sort from greatest to least.
	 * 
	 * @param inputList       - the list to be sorted
	 * @param leastToGreatest - sort order
	 * @return ArrayList<Customer> - the sorted list */
	public ArrayList<Customer> sortByID(ArrayList<Customer> inputList, boolean leastToGreatest) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ((leastToGreatest && inputList.get(in - 1).getCustomerID() > inputList.get(in).getCustomerID())
						|| (!leastToGreatest && inputList.get(in - 1).getCustomerID() < inputList.get(in).getCustomerID())) {
					Customer temp = inputList.get(in - 1);
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
	
	/** Method sortByName sorts an ArrayList of Customer by full name as specified by alphabeticalOrder. If alphabeticalOrder is
	 * true: Bubble sort from in alphabetical order. If alphabeticalOrder is false: Bubble sort from in reverse alphabetical
	 * order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Customer> - the sorted list */
	public ArrayList<Customer> sortByName(ArrayList<Customer> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getName();
				String current = inputList.get(in).getName();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Customer temp = inputList.get(in - 1);
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

	/** Method sortByEmail sorts an ArrayList of Customer by email as specified by alphabeticalOrder. If alphabeticalOrder is
	 * true: Bubble sort from in alphabetical order. If alphabeticalOrder is false: Bubble sort from in reverse alphabetical
	 * order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Customer> - the sorted list */
	public ArrayList<Customer> sortByEmail(ArrayList<Customer> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getEmail();
				String current = inputList.get(in).getEmail();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Customer temp = inputList.get(in - 1);
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

	/** Method sortByPhone sorts an ArrayList of Customer by phone number depending on leastToGreatest. If leastToGreatest is
	 * true: Bubble sort from least to greatest. If leastToGreatest is false: Bubble sort from greatest to least.
	 * 
	 * @param inputList       - the list to be sorted
	 * @param leastToGreatest - sort order
	 * @return ArrayList<Customer> - the sorted list */
	public ArrayList<Customer> sortByPhone(ArrayList<Customer> inputList, boolean leastToGreatest) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ((leastToGreatest && inputList.get(in - 1).getPhone() > inputList.get(in).getPhone())
						|| (!leastToGreatest
								&& inputList.get(in - 1).getPhone() < inputList.get(in).getPhone())) {
					Customer temp = inputList.get(in - 1);
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

	/** Method sortByStreet sorts an ArrayList of Customer by street address as specified by alphabeticalOrder. If
	 * alphabeticalOrder is true: Bubble sort from in alphabetical order. If alphabeticalOrder is false: Bubble sort from in
	 * reverse alphabetical order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Customer> - the sorted list */
	public ArrayList<Customer> sortByStreet(ArrayList<Customer> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getAddress().getStreet1() + " "
						+ inputList.get(in - 1).getAddress().getStreet2();
				String current = inputList.get(in).getAddress().getStreet1() + " "
						+ inputList.get(in).getAddress().getStreet2();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Customer temp = inputList.get(in - 1);
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

	/** Method sortByCity sorts an ArrayList of Customer by city as specified by alphabeticalOrder. If alphabeticalOrder is true:
	 * Bubble sort from in alphabetical order. If alphabeticalOrder is false: Bubble sort from in reverse alphabetical order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Customer> - the sorted list */
	public ArrayList<Customer> sortByCity(ArrayList<Customer> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getAddress().getCity();
				String current = inputList.get(in).getAddress().getCity();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Customer temp = inputList.get(in - 1);
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

	/** Method sortByState sorts an ArrayList of Customer by state abbreviation as specified by alphabeticalOrder. If
	 * alphabeticalOrder is true: Bubble sort from in alphabetical order. If alphabeticalOrder is false: Bubble sort from in
	 * reverse alphabetical order. */
	public ArrayList<Customer> sortByState(ArrayList<Customer> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getAddress().getState();
				String current = inputList.get(in).getAddress().getState();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Customer temp = inputList.get(in - 1);
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

	/** Method sortByZip sorts an ArrayList of Customer by zip code and zip+4 depending on leastToGreatest. If leastToGreatest is
	 * true: Bubble sort from least to greatest. If leastToGreatest is false: Bubble sort from greatest to least.
	 * 
	 * @param inputList       - the list to be sorted
	 * @param leastToGreatest - sort order
	 * @return ArrayList<Customer> - the sorted list */
	public ArrayList<Customer> sortByZip(ArrayList<Customer> inputList, boolean leastToGreatest) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				int previous = inputList.get(in - 1).getAddress().getZip();
				if (inputList.get(in - 1).getAddress().getZip() <= 0) {
					previous = previous * 10000;
				} else {
					previous = (previous * 10000) + inputList.get(in - 1).getAddress().getZip();
				}
				int current = inputList.get(in).getAddress().getZip();
				if (inputList.get(in).getAddress().getZip() <= 0) {
					current = current * 10000;
				} else {
					current = (current * 10000) + inputList.get(in).getAddress().getZip();
				}
				// Depending on direction, check to see if a swap is necessary.
				if ((leastToGreatest && previous > current) || (!leastToGreatest && previous < current)) {
					Customer temp = inputList.get(in - 1);
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

	/** Method saveCustomerList checks if the customersFileName is writeable and then replaces the entire contents of that file
	 * with all the customers in the current customers attribute. Each customer is stored on a separate line in the file. It
	 * returns false if the file was not valid or writable
	 * 
	 * @param customersFileName
	 * @return boolean */
	private boolean saveCustomerList(String customersFileName) {
		// Check to see if the inventory data file is an existing file.
		File customersFile = new File(customersFileName);
		if (!customersFile.isFile() && !customersFile.canWrite()) {
			System.out.println(customersFileName + " does not exist or is not accessible for writing.");
			FileReadWrite.createOutputFile(customersFileName);
		}
		String fileOutputString = "";
		for (Customer tempCustomer : this.customers) {
			fileOutputString += tempCustomer.toDataFileString() + System.lineSeparator();
		}
		return FileReadWrite.writeToFile(customersFile, fileOutputString);
	}

	/** method restoreCustomerList checks if the file indicated by the string customersFileName is present and readable then
	 * populates the customers attribute with the values found in the file. It returns false if the file does not exist or is not
	 * readable. The console will show the number of errors encountered reading the file.
	 * 
	 * @param customersFileName
	 * @return boolean */
	private boolean restoreCustomerList(String customersFileName) {
		ArrayList<String> fileStringArray = new ArrayList<String>();
		File customersFile = new File(customersFileName);
		if (customersFile.isFile() && customersFile.canRead()) {
			fileStringArray = FileReadWrite.readFileToStringList(customersFile.getName());
		} else {
			System.out.println(customersFileName + " does not exist or is not accessible for reading.");
			return false;
		}
		Customer readCustomer = new Customer(0L);
		int errorCount = 0;
		for (int index = 0; index < fileStringArray.size(); index++) {
			if (!readCustomer.parseCustomerString(fileStringArray.get(index))) {
				errorCount++;
			} else {
				this.customers.add(readCustomer);
				readCustomer = new Customer(0L);
			}
			// For troubleshooting
			// System.out.println(readCustomer.toDataFileString());
			// System.out.println(readCustomer.toString());
		}
		System.out.println(customersFileName + " import produced " + errorCount + " errors.");
		restoreNextCustomerID();
		return true;
	}

	/** Method restoreNextCustomerID searches the customer list for the highest customerID value, and sets this.nextCustomerID to
	 * the highest existing customerID plus 1 */
	private void restoreNextCustomerID() {
		long maxIDNum = 0L;
		for (Customer tempCustomer : this.customers) {
			if (tempCustomer.getCustomerID() > maxIDNum) {
				maxIDNum = tempCustomer.getCustomerID();
			}
		}
		this.nextCustomerID = maxIDNum + 1;
	}

	@Override
	public String toString() {
		if (this.customers.size() == 0) {
			return "There are no customers in the list.";
		}
		String outputString = "";
		for (Customer tempCustomer : this.customers) {
			outputString += (tempCustomer.toString() + "\n");
		}
		return outputString;
	}
}
