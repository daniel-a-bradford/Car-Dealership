package com.cardealer.entity;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.text.DecimalFormat;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Customer {
	private StringChecker check = new StringChecker(true);

	private long customerID = 0;
	private String name = "";
	private String firstName = "";
	private String lastName = "";
	private String middleName = "";
	private long phone = 0;
	private String email = "";
	// customerPassword is stored as an encrypted string
	private String password = "";
	private String passwordSalt = "";
	private Address address = new Address();
	private PaymentMethod pay = new PaymentMethod();

	/** Constructor to build a Customer with only a customerID number
	 * 
	 * @param customerID */
	public Customer(long customerID) {
		this.customerID = customerID;
	}

	/** Constructor to build a Customer with all customer data, an address and pay object.
	 * 
	 * @param customerID
	 * @param customerName
	 * @param customerAddress
	 * @param customerPhone
	 * @param customerEmail
	 * @param pay */
	public Customer(long ID, String name, long phone, String email, String password,
			Address address, PaymentMethod pay) {
		setCustomerID(customerID);
		setName(name);
		this.address = address;
		setPhone(phone);
		setEmail(email);
		setPassword(password);
		this.pay = pay;
	}

	/** Constructor to build a Customer with all customer data except system generated customerID as strings, an address and pay
	 * object.
	 * 
	 * @param customerID
	 * @param customerName
	 * @param customerAddress
	 * @param customerPhone
	 * @param customerEmail
	 * @param pay */
	public Customer(long customerID, String name, String phone, String email, String password,
			Address address, PaymentMethod pay) {
		setCustomerID(customerID);
		setName(name);
		this.address = address;
		setPhone(phone);
		setEmail(email);
		setPassword(password);
		this.pay = pay;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public boolean setCustomerID(String customerID) {
		if (check.isLong(customerID)) {
			this.customerID = check.getGoodLong();
			return true;
		}
		this.customerID = 0L;
		return false;
	}

	public String getName() {
		return this.name;
	}
	
	public boolean setName(String customerName) {
		if (check.isValidString(customerName)) {
			extractNames(customerName);
			this.name = customerName;
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
			this.firstName = firstName;
			if (this.name.isEmpty() || this.name.split(" ").length == 1) {
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
			this.lastName = lastName;
			if (this.name.isEmpty() || this.name.split(" ").length == 1) {
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
			this.middleName = middleName;
			if (this.name.isEmpty() || this.name.split(" ").length < 3) {
				this.name = this.firstName + " " + this.middleName + " " + this.lastName;
			}
			return true;
		}
		this.middleName = "";
		return false;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address customerAddress) {
		this.address = customerAddress;
	}

	public long getPhone() {
		return this.phone;
	}

	public boolean setPhone(long customerPhone) {
		// Check to see if there are leading zeroes when the long is converted to a
		// string
		if (Long.toString(this.phone).length() <= 10) {
			this.phone = customerPhone;
			return true;
		}
		System.out.println("Customer phone number information is not 10 digits.");
		return false;
	}

	public boolean setPhone(String inputString) {
		if (check.isLong(inputString, 10)) {
			this.phone = check.getGoodLong();
			return true;
		}
		this.phone = 0;
		return false;
	}

	public String getEmail() {
		return this.email;
	}

	public boolean setEmail(String customerEmail) {
		if (check.isEmail(customerEmail)) {
			this.email = customerEmail;
			return true;
		}
		this.email = "";
		return false;
	}

	public String getPassword() {
		return "";
	}

	public boolean setPassword(String customerPassword) {
		return setEncryptedPassword(customerPassword);
	}

	public PaymentMethod getPay() {
		return pay;
	}

	public void setPay(PaymentMethod pay) {
		this.pay = pay;
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

	/** Method isValidCustomer checks the current Customer object to ensure all properties have non-blank or null values. It
	 * prints the customer to the console and if invalid, the first reason why it was found to be invalid.
	 * 
	 * @param isNewCustomer - true if the Customer is new so it does not check for a customer ID.
	 * @return boolean - true if the customer information is valid, otherwise false. */
	public boolean isValidCustomer(boolean isNewCustomer) {
		//TODO remove this following troubleshooting
		// Print this customer information to the console.
//		System.out.println(this.toString());
		if (!isNewCustomer) {
			if ((Long) this.customerID == null || this.customerID == 0) {
				System.out.println("isValidCustomer - existing customer has no customerID.");
				return false;
			}
		}
		if (this.firstName.isBlank() || this.firstName.isEmpty()) {
			System.out.println("isValidCustomer - customer has no first name.");
			return false;
		}
		if (this.lastName.isBlank() || this.lastName.isEmpty()) {
			System.out.println("isValidCustomer - customer has no last name.");
			return false;
		}
		if (this.name.isBlank() || this.name.isEmpty()) {
			System.out.println("isValidCustomer - customer has no full name.");
			return false;
		}
		if (!this.address.isValidAddress(isNewCustomer)) {
			return false;
		}
		if (this.phone == 0L || Long.toString(this.phone).length() != 10) {
			System.out.println("isValidCustomer - customer phone number information is invalid.");
			return false;
		}
		if (this.email.isBlank() || this.email.isEmpty()) {
			System.out.println("isValidCustomer - customer has no e-mail address information.");
			return false;
		}
		if (this.password.isBlank() || this.password.isEmpty()) {
			System.out.println("isValidCustomer - customer has no password set.");
			return false;
		}
		System.out.println("isValidCustomer - customer is valid.");
		return true;
	}

	/** Method setEncryptedPassword takes the string password, checks to see if it is a valid String. If it is, it encrypts the
	 * password and stores it as the the customerPassword class attribute.
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

	public boolean parseCustomerString(String inputString) {
		if (!check.isValidString(inputString)) {
			System.out.println("Not a valid input string found.");
			return false;
		}
		String[] objectArray = inputString.split("~``~");
		if (objectArray.length != 3) {
			System.out.println("Expected 3 objects, but found " + objectArray.length);
			return false;
		}
		String[] attributeArray = objectArray[0].split("~`~");
		Address parsedAddress = new Address();
		if (!parsedAddress.parseAddressString(objectArray[1])) {
			return false;
		}
		PaymentMethod parsedPay = new PaymentMethod();
		if (!parsedPay.parsePaymentString(objectArray[2])) {
			return false;
		}
		// Check if there are enough data fields
		if (attributeArray.length != 6) {
			System.out.println("Expected 6 attributes, but found " + attributeArray.length);
			return false;
		}
		// Trim whitespace from the ends of the strings.
		for (int index = 0; index < attributeArray.length; index++) {
			attributeArray[index] = attributeArray[index].trim();
		}
		// Check if it is a blank customer, and if so, bypass the setters which check for valid values.
		if (attributeArray[1].length() == 0) {
			this.customerID = 0L;
			this.email = "";
			this.password = "";
			this.passwordSalt = "";
			this.phone = 0L;
			this.address = new Address();
			this.pay = new PaymentMethod();
			return true;
		}
		if (setCustomerID(attributeArray[0]) && setName(attributeArray[1]) && setPhone(attributeArray[2])
				&& setEmail(attributeArray[3]) && check.isValidString(attributeArray[4]) 
				&& check.isValidString(attributeArray[5])) {
			this.password = attributeArray[4];
			this.passwordSalt = attributeArray[5];
			this.address = parsedAddress;
			this.pay = parsedPay;
			return true;
		}
		System.out.println("Customer attribute string not correct.");
//TODO remove after troubleshooting
System.out.println(this.toString());
		return false;
	}

	/** Method toDataFileString creates a string where the object attributes are delimited by " ~`~". Embedded objects are
	 * delimited by " ~``~". The space is necessary for the read function which uses .split in case string attributes are empty.
	 * 
	 * @return */
	public String toDataFileString() {
		return this.customerID + " ~`~" + this.name + " ~`~" + this.phone + " ~`~" + this.email + " ~`~"
				+ this.password + " ~`~" + this.passwordSalt + " ~``~" + this.address.toDataFileString() + " ~``~"
				+ this.pay.toDataFileString();
	}

	/** Method toString overrides the default Object.toString to summarize the customer.
	 * 
	 * @return String with the salient details of the customer */
	@Override
	public String toString() {
		String outputString = "Customer #" + this.customerID + " " + this.name + "\n";
		outputString += this.address.toString();
		String phoneString = Long.toString(this.phone);
		if (phoneString.length() < 10) {
			DecimalFormat paddedPhone = new DecimalFormat("0000000000");
			phoneString = paddedPhone.format(this.phone);
		}
		if (phoneString.length() == 10) {
			phoneString = "(" + phoneString.subSequence(0, 3) + ") " + phoneString.subSequence(3, 6) + "-"
					+ phoneString.subSequence(6, 10);
		}
		outputString += " Phone " + phoneString;
		outputString += "\nE-mail  " + this.email + " ";
		outputString += this.pay.toString();
		return outputString;
	}

}
