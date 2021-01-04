package com.cardealer.entity;


public class Address {
	private StringChecker check = new StringChecker(true);

	private String street1 = "";
	private String street2 = "";
	private String city = "";
	private String state = "";
	private int zip = 0;
	private int zipPlus4 = 0;

	/**
	 * Constructor to build an Address
	 */
	public Address() {
	}

	/**
	 * Constructor to build an address with all information
	 * 
	 * @param street1
	 * @param street2
	 * @param city
	 * @param state
	 * @param zip
	 * @param zipPlus4
	 */
	public Address(String street1, String street2, String city, String state, int zip, int zipPlus4) {
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.zipPlus4 = zipPlus4;
	}

	public String getStreet1() {
		return street1;
	}

	public boolean setStreet1(String street1) {
		if (check.isValidString(street1)) {
			this.street1 = street1.trim();
			return true;
		}
		this.street1 = "";
		return false;
	}

	public String getStreet2() {
		return street2;
	}

	public boolean setStreet2(String street2) {
		if (check.isValidString(street2)) {
			this.street2 = street2.trim();
		} else {
			this.street2 = "";
		}
		// Optional field. It is set to blank if parameter is null.
		return true;
	}

	public String getCity() {
		return city;
	}

	public boolean setCity(String city) {
		if (check.isValidString(city)) {
			city = city.trim();
			if (city.length() > 1) {
				this.city = city;
				return true;
			}
		}
		System.out.println("setCity - Not enough letters for a city.");
		return false;
	}

	public String getState() {
		return state;
	}

	public boolean setState(String state) {
		if (check.isStateAbbreviation(state)) {
			this.state = check.getGoodString();
			return true;
		}
	System.out.println("setState - Invalid 2 letter state abbreviation.");
	return false;

	}

	public int getZip() {
		return zip;
	}

	public boolean setZip(int zip) {
		// Values with leading zeros are fine as long as the zip code is not zero.
		if (String.format("%05d", this.zip).length() != 5) {
			this.zip = zip;
			return true;
		}
		System.out.println("setZip - Invalid 5 digit zip code.");
		return false;
	}

	public boolean setZip(String inputString) {
		if (check.isInt(inputString, 5)) {
			this.zip = check.getGoodInt();
			return true;
		}
		System.out.println("setZip - Invalid 5 digit zip code.");
		return false;
	}

	public int getZipPlus4() {
		return zipPlus4;
	}

	public boolean setZipPlus4(int zipPlus4) {
		// Allows zero values
		if (String.format("%04d", this.zipPlus4).length() == 4) {
			this.zipPlus4 = zipPlus4;
		}
		// Optional field
		this.zipPlus4 = 0;
		return true;
	}

	public boolean setZipPlus4(String inputString) {
		if (check.isInt(inputString, 4)) {
			this.zipPlus4 = check.getGoodInt();
			return true;
		}
		// Optional field
		this.zipPlus4 = 0;
		return true;
	}

	/**
	 * Method isValidAddress checks the current Address object to ensure all
	 * properties have non-blank or null values. It prints the address to the
	 * console and if invalid, the first reason why it was found to be invalid.
	 * 
	 * @param isNewAddress - true if the Address is new so it does not check for a
	 *                     address ID.
	 * @return boolean - true if the address information is valid, otherwise false.
	 */
	public boolean isValidAddress(boolean isNewAddress) {
		if (!check.isValidString(this.street1)) {
			System.out.println("isValidAddress - address has no street information.");
			return false;
		}
		if (!check.isValidString(this.city)) {
			System.out.println("isValidAddress - address has no city information.");
			return false;
		}
		if (!check.isValidString(this.state)) {
			System.out.println("isValidAddress - address has no state information.");
			return false;
		}
		if (!check.isStateAbbreviation(this.state)) {
			System.out.println("isValidAddress - address has no state information.");
			return false;
		}
		if (this.zip != 0 && String.format("%05d", this.zip).length() != 5) {
			System.out.println("isValidAddress - address zip code information is invalid.");
			return false;
		}
		if (this.zipPlus4 != 0 && String.format("%04d", this.zipPlus4).length() != 4) {
			System.out.println("isValidAddress - address zip code +4 information is invalid.");
			return false;
		}
		return true;
	}

	public boolean parseAddressString(String inputString) {
		if (!check.isValidString(inputString)) {
			System.out.println("Not a valid input string found.");
			return false;
		}
		String[] attributeArray = inputString.split("~`~");
		// Check if there are enough data fields
		if (attributeArray.length != 6) {
			System.out.println("Expected 6 attributes, but found " + attributeArray.length);
			return false;
		}
		// Trim whitespace from the ends of the strings.
		for (int index = 0; index < attributeArray.length; index++) {
			attributeArray[index] = attributeArray[index].trim();
		}
		// Check if the address is blank, and if so, bypass the setters which check for
		// valid values.
		if (attributeArray[0].length() == 0) {
			this.street1 = "";
			this.street2 = "";
			this.city = "";
			this.state = "";
			this.zip = 0;
			this.zipPlus4 = 0;
			return true;
		}
		if (setStreet1(attributeArray[0]) && setStreet2(attributeArray[1])
				&& setCity(attributeArray[2]) && setState(attributeArray[3])
				&& setZip(attributeArray[4]) && setZipPlus4(attributeArray[5])) {
			return true;
		}
		System.out.println("Address attribute string not correct.");
		return false;
	}

	/**
	 * Method toDataFileString creates a string where the object attributes are
	 * delimited by " ~`~". The space is necessary for the read function which uses
	 * .split in case string attributes are empty.
	 * 
	 * @return
	 */
	public String toDataFileString() {
		return this.street1 + " ~`~" + this.street2 + " ~`~" + this.city + " ~`~" + this.state + " ~`~" + +this.zip
				+ " ~`~" + this.zipPlus4;
	}

	/**
	 * Method toString overrides the default Object.toString to summarize the
	 * address.
	 * 
	 * @return String with the salient details of the address
	 */
	@Override
	public String toString() {
		String outputString = this.street1 + ", ";
		if (!this.street2.isEmpty()) {
			outputString += " " + this.street2 + " ";
		}
		outputString += this.city + ", " + this.state + "  " + String.format("%05d", this.zip);
		// Don't include zip+4 if it is set to zero.
		if (this.zipPlus4 != 0) {
			outputString += "-" + String.format("%04d", this.zipPlus4) + " ";
		}
		return outputString;
	}

//	/** Method promptUsertoUpdateAddressInfo uses dialog boxes to prompt the user for the street number and name,
//	 * 	 additional street info (if needed), city, state, zip code, zip code+4 (if known).
//	 * @param Address newAddress - new address object shell with addressID assigned
//	 */
//	public boolean promptUsertoUpdateAddressInfo() {
////		Skip the function if the newAddress.addressID is not assigned.
////		TODO After prompting display address information and re-prompt if the information is incorrect
//			userCancelled:
//			do {
//				String streetName1 = DialogInput.getInputString("Please enter the street number and street name: ");
//				// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
//				if (streetName1 == null) {
//					System.out.println("User cancelled");
//					break userCancelled;
//				}
//				streetName1 = streetName1.trim();
//				this.street1 = streetName1;
//				
//				String streetName2 = DialogInput.getInputString("Please enter the additional street information "
//						+ "(e.g. Apt#, Building#, Care of, etc.) (Enter - if none): ");
//				// If DialogInput return is null, then the user pressed Cancel, set street equal to "".
//				if (streetName2 == null) {
//					System.out.println("Skipping additional street information");
//					streetName2 = "";
//				}
//				streetName2 = streetName2.trim();
//				this.street2 = streetName2;
//				
//				String city = DialogInput.getInputString("Please enter the city: ");
//				// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
//				if (city == null) {
//					System.out.println("User cancelled");
//					break userCancelled;
//				}
//				city = city.trim();
//				this.city = city;
//				
//				String state = DialogInput.getInputState("Please enter the two characters of a US State, Commonwealth, or Territory \n"
//						+ "(e.g. IL for Illinois): ");
//				// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
//				if (state == null) {
//					System.out.println("User cancelled");
//					break userCancelled;
//				}
//				this.state = state;
//				
//				Integer zip = DialogInput.getInputInt("Please enter the 5 digit zip code: ", 5);
//				// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
//				if (zip == null) {
//					System.out.println("User cancelled");
//					break userCancelled;
//				}
//				this.zip = zip;
//				
//				Integer zipPlus4 = DialogInput.getInputInt("Please enter the 4 digit zip code+4:\n"
//								+ "(exit if not known)", 4);
//				// If DialogInput return is null, then the user pressed Cancel, so set zipPlus4 to 0.
//				if (zipPlus4 == null) {
//					System.out.println("Skipping zip code+4");
//					zipPlus4 = 0;
//				} 
//				this.zipPlus4 = zipPlus4;
//		
//				return true;
//			} while (false);	
//		System.out.println("promptUsertoUpdateAddressInfo - address information invalid");
//		return false;
//	}
//	
//	/** Method promptUsertoUpdateAddressInfo updates the user information by prompting the user for the updated address name, 
//	 * 	street number and name, additional street info (if needed), city, state, zip code, zip code+4 (if known),
//	 * 	address e-mail, and if the new address can check out books right away. 
//	 * 
//	 * @param Address newAddress - new address object shell with addressID assigned
//	 */
//	public boolean promptUsertoUpdateAddressInfo(Address oldAddress) {
////TODO After prompting display address information and re-prompt if the information is incorrect
//		userCancelled:
//		do {
//			String streetName1 = DialogInput.getInputString("Current street number and name: " 
//			+ oldAddress.getStreet1() + "\nPlease enter the new street number and street name: ");
//			// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
//			if (streetName1 == null) {
//				System.out.println("User cancelled");
//				break userCancelled;
//			}
//			streetName1 = streetName1.trim();
//			this.street1 = streetName1;
//			
//			String streetName2 = DialogInput.getInputString("Current additional street information: " 
//					+ oldAddress.getStreet2() + "\nPlease enter the new additional street information "
//					+ "(e.g. Apt#, Building#, Care of, etc.) (exit if none): ");
//			// If DialogInput return is null, then the user pressed Cancel, set street equal to "".
//			if (streetName2 == null) {
//				System.out.println("Skipping additional street information");
//				streetName2 = "";
//			}
//			streetName2 = streetName2.trim();
//			this.street2 = streetName2;
//			
//			String city = DialogInput.getInputString("Current city: " + oldAddress.getCity() 
//				+ "\nPlease enter the new city:");
//			// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
//			if (city == null) {
//				System.out.println("User cancelled");
//				break userCancelled;
//			}
//			city = city.trim();
//			this.city = city;
//			
//			String state = DialogInput.getInputState("Current state abbreviation: " 
//					+ oldAddress.getState() + "\nPlease enter the two characters of a US State, Commonwealth, or Territory \n"
//					+ "(e.g. IL for Illinois):");
//			// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
//			if (state == null) {
//				System.out.println("User cancelled");
//				break userCancelled;
//			}
//			this.state = state;
//			
//			Integer zip = DialogInput.getInputInt("Current zip code: " 
//					+ oldAddress.getZip() + "\nPlease enter the new 5 digit zip code:", 5);
//			// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
//			if (zip == null) {
//				System.out.println("User cancelled");
//				break userCancelled;
//			}
//			this.zip = zip;
//			
//			Integer zipPlus4 = DialogInput.getInputInt("Current zip code+4: " 
//					+ oldAddress.getZip() + "\nPlease enter the new 4 digit zip code+4:\n"
//							+ "(exit if not known)", 4);
//			// If DialogInput return is null, then the user pressed Cancel, so set zipPlus4 to 0.
//			if (zipPlus4 == null) {
//				System.out.println("Skipping zip code+4");
//				zipPlus4 = 0;
//			} 
//			this.zipPlus4 = zipPlus4;
//			
//			return true;
//		} while (false);	
//		System.out.println("promptUsertoUpdateAddressInfo - address information invalid");
//		return false;
//	}

}
