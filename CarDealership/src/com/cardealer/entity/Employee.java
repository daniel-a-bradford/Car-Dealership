package com.cardealer.entity;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Employee {
	private StringChecker check = new StringChecker(true);

	private String name = "";
	private String firstName = "";
	private String lastName = "";
	private String middleName = "";
	// Their email address is their unique identifier
	private String email = "";
	// password is stored as an encrypted string
	private String password = "";
	private String passwordSalt = "";

	/** Constructor to build a blank Employee 
	 * 
	 * @param employeeID */
	public Employee() {
	}

	/** Constructor to build a Employee with all employee data.
	 * 
	 * @param name
	 * @param email
	 * @param password */
	public Employee(String name, String email, String password) {
		setName(name);
		setEmail(email);
		setPassword(password);
	}

	public String getName() {
		return this.name;
	}
	
	public boolean setName(String employeeName) {
		if (check.isValidString(employeeName)) {
			extractNames(employeeName);
			this.name = employeeName;
			return true;
		}
		this.name = "";
		return false;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public boolean setFirstName(String firstName) {
		if (check.isValidString(firstName)) {
			this.firstName = firstName.trim();
			if (this.middleName.trim().length() == 0) {
				this.name = this.firstName + " " + this.lastName;
			} else {
				this.name = this.firstName + " " + this.middleName + " " + this.lastName;
			}
			return true;
		}
		this.firstName = "";
		return false;
	}

	public String getLastName() {
		return this.lastName;
	}
	
	public boolean setLastName(String lastName) {
		if (check.isValidString(lastName)) {
			this.lastName = lastName.trim();
			if (this.middleName.trim().length() == 0) {
				this.name = this.firstName + " " + this.lastName;
			} else {
				this.name = this.firstName + " " + this.middleName + " " + this.lastName;
			}
			return true;
		}
		this.lastName = "";
		return false;
	}

	public String getMiddleName() {
		return this.middleName;
	}
	
	public boolean setMiddleName(String middleName) {
		if (check.isValidString(middleName)) {
			this.middleName = middleName.trim();
			this.name = this.firstName + " " + this.middleName + " " + this.lastName;
			return true;
		}
		this.middleName = "";
		return false;
	}

	public String getEmail() {
		return this.email;
	}

	public boolean setEmail(String employeeEmail) {
		if (check.isEmail(employeeEmail)) {
			this.email = employeeEmail;
			return true;
		}
		this.email = "";
		return false;
	}

	public String getPassword() {
		return "";
	}

	public boolean setPassword(String employeePassword) {
		return setEncryptedPassword(employeePassword);
	}

	/** Method extractNames splits the input string into parts separated by spaces, assigning the first part to the property
	 * firstName, the last part to lastName (unless there is only one part, where it assigns a blank string). Middle name remains
	 * blank unless there are more than 2 parts.
	 * 
	 * @param personName */
	private void extractNames(String personName) {
		this.middleName = "";
		if (personName != null) {
			personName = personName.trim();
			String[] nameArray = personName.split(" ");
			this.firstName = nameArray[0];

			for (int index = 1; index < nameArray.length - 1; index++) {
				this.middleName += nameArray[index] + " ";
			}
			if (nameArray.length > 1) {
				this.lastName = nameArray[nameArray.length - 1];
			} else {
				this.lastName = "";
			}
		} else {
			this.firstName = "";
			this.lastName = "";
		}
	}

	/** Method isValidEmployee checks the current Employee object to ensure all properties have non-blank or null values. It
	 * prints the employee to the console and if invalid, the first reason why it was found to be invalid.
	 * 
	 * @return boolean - true if the employee information is valid, otherwise false. */
	public boolean isValidEmployee() {
		//TODO remove this following troubleshooting
		// Print this employee information to the console.
//		System.out.println(this.toString());
		if (this.firstName.isBlank() || this.firstName.isEmpty()) {
			System.out.println("isValidEmployee - employee has no first name.");
			return false;
		}
		if (this.lastName.isBlank() || this.lastName.isEmpty()) {
			System.out.println("isValidEmployee - employee has no last name.");
			return false;
		}
		if (this.name.isBlank() || this.name.isEmpty()) {
			System.out.println("isValidEmployee - employee has no full name.");
			return false;
		}
		if (this.email.isBlank() || this.email.isEmpty()) {
			System.out.println("isValidEmployee - employee has no e-mail address information.");
			return false;
		}
		if (this.password.isBlank() || this.password.isEmpty()) {
			System.out.println("isValidEmployee - employee has no password set.");
			return false;
		}
		System.out.println("isValidEmployee - employee is valid.");
		return true;
	}

	/** Method setEncryptedPassword takes the string password, checks to see if it is a valid String. If it is, it encrypts the
	 * password and stores it as the the employeePassword class attribute.
	 * 
	 * @param password
	 * @return */
	private boolean setEncryptedPassword(String password) {
		if (!check.isValidString(password)) {
			System.out.println("setEncryptedPassword - Not a valid password.");
			return false;
		}
		password = password.trim();
		try {
			String salt = generateNewSalt();
			String encryptedPassword = generateEncryptedString(password, salt);
			this.password = encryptedPassword;
			this.passwordSalt = salt;
			return true;
		} catch (Exception ex) {
			System.out.println("Out of memory during decoding or other unspecified error.\n" + ex);
			ex.printStackTrace();
		}
		return false;
	}

	/** Method isCorrectPassword encodes the inputPassword with the current user's Base64 encrypted salt and compares it to the
	 * current user's encrypted password. If it matches, it returns true. Otherwise false.
	 * 
	 * @param inputPassword
	 * @return */
	public boolean isCorrectPassword(String inputPassword) {
		if (!check.isValidString(inputPassword)) {
			return false;
		}
		inputPassword = inputPassword.trim();
		try {
			String inputHash = generateEncryptedString(inputPassword, this.passwordSalt);
			if (inputHash.equals(this.password)) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println("Out of memory during decoding or other unspecified error.\n" + ex);
			ex.printStackTrace();
		}
		return false;
	}

	/** Method generateNewSalt uses a SecureRandom 8 byte value based on the SHA1PRNG algorithm, converts it to a string returns
	 * the string after it has been encoded with a Base64 encoder.
	 * 
	 * @return
	 * @throws Exception */
	private String generateNewSalt() throws Exception {
		// Generate a new salt value every time a password is set.
		// Don't use Random. SecureRandom complies with FIPS 140-2.
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		// NIST recommends minimum 4 bytes. Safer to use 8 bytes.
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	/** Method generateEncryptedString takes a inputString and Base64 encoded salt value and uses it to generate an encrypted
	 * inputString using the PBKDF2 hash algorithm and returns it as a string.
	 * 
	 * @param inputString
	 * @param salt
	 * @return
	 * @throws Exception */
	private String generateEncryptedString(String inputString, String salt) throws Exception {
		// Get a encrypted inputString using PBKDF2 hash algorithm from
		// https://www.quickprogrammingtips.com/java/how-to-securely-store-passwords-in-java.html
		String algorithm = "PBKDF2WithHmacSHA1";
		int derivedKeyLength = 160; // for SHA1
		int iterations = 20000; // NIST specifies 10000, safer with 20000

		byte[] saltAsBytes = Base64.getDecoder().decode(salt);
		KeySpec spec = new PBEKeySpec(inputString.toCharArray(), saltAsBytes, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

		byte[] encodedBytes = f.generateSecret(spec).getEncoded();
		return Base64.getEncoder().encodeToString(encodedBytes);
	}

	public boolean parseEmployeeString(String inputString) {
		if (!check.isValidString(inputString)) {
			System.out.println("Not a valid input string found.");
			return false;
		}
		String[] attributeArray = check.getGoodString().split("~`~");
		// Check if there are enough data fields
		if (attributeArray.length != 4) {
			System.out.println("parseEmployeeString - Expected 4 attributes, but found " + attributeArray.length);
			return false;
		}
		// Trim whitespace from the ends of the strings.
		for (int index = 0; index < attributeArray.length; index++) {
			attributeArray[index] = attributeArray[index].trim();
		}
		// Check if it is a blank employee, and if so, bypass the setters which check for valid values.
		if (attributeArray[0].length() == 0) {
			return true;
		}
		if (setName(attributeArray[0]) && setEmail(attributeArray[1]) && check.isValidString(attributeArray[2]) 
				&& check.isValidString(attributeArray[3])) {
			this.password = attributeArray[2];
			this.passwordSalt = attributeArray[3];
			return true;
		}
		System.out.println("Employee attribute string not correct.");
//TODO remove after troubleshooting
System.out.println(this.toString());
		return false;
	}

	/** Method toDataFileString creates a string where the object attributes are delimited by " ~`~". Embedded objects are
	 * delimited by " ~``~". The space is necessary for the read function which uses .split in case string attributes are empty.
	 * 
	 * @return */
	public String toDataFileString() {
		return this.name + " ~`~" + this.email + " ~`~"	+ this.password + " ~`~" + this.passwordSalt;
	}

	/** Method toString overrides the default Object.toString to summarize the employee.
	 * 
	 * @return String with the salient details of the employee */
	@Override
	public String toString() {
		String outputString = "Employee " + this.name + " with this e-mail: " + this.email + " ";
		return outputString;
	}

}
