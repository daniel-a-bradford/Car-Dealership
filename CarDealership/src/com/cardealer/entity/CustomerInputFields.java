package com.cardealer.entity;

public class CustomerInputFields {

	private String validStyle = "input-valid";
	private String errorStyle = "input-error";

	// Describes the field style class (blank if not required), the placeholder text, and field value if valid.
	private String[] firstName = { "", "Your first name", "" };
	private String[] middleName = { "", "Your middle name", "" };
	private String[] lastName = { "", "Your last name", "" };
	private String[] fullName = { "", "Your full name", "" };
	private String[] phone = { "", "Ten digits", "" };
	private String[] email = { "", "Your email address", "" };
	private String[] newPassword = { "", "Set a password", "" };
	private String[] oldPassword = { "", "Enter password", "" };
	private String[] street1 = { "", "Your billing street address", "" };
	private String[] street2 = { "", "Suite, apt, c/o, etc.", "" };
	private String[] city = { "", "Your city", "" };
	private String[] state = { "", "", "Choose..." };
	private String[] zip = { "", "00000", "" };
	private String[] zipPlus4 = { "", "0000", "" };
	private String[] paymentType = { "", "", "checked", "" , ""};
	private String[] cardName = { "", "Name on card", "" };
	private String[] cardNum = { "", "0000-0000-0000-0000", "" };
	private String[] cardLast4 = { "", "0000", "" };
	private String[] expiration = { "", "YYYY-MM-DD", "" };
	private String[] cvv = { "", "000", "" };
	private String[] paypalEmail = { "", "Your PayPal email", "" };

	public void flagFirstName(boolean valid, String input) {
		if (valid) {
			this.firstName[0] = validStyle;
			this.firstName[2] = input;
		} else {
			this.firstName[0] = errorStyle;
			this.firstName[1] = "Please enter a valid first name.";
		}
	}

	public void flagMiddleName(boolean valid, String input) {
		if (valid) {
			this.middleName[0] = validStyle;
			this.middleName[2] = input;
		} else {
			this.middleName[0] = errorStyle;
			this.middleName[1] = "Please enter a valid middle name.";
		}
	}

	public void flagLastName(boolean valid, String input) {
		if (valid) {
			this.lastName[0] = validStyle;
			this.lastName[2] = input;
		} else {
			this.lastName[0] = errorStyle;
			this.lastName[1] = "Please enter a valid last name.";
		}
	}

	public void flagFullName(boolean valid, String input) {
		if (valid) {
			this.fullName[0] = validStyle;
			this.fullName[2] = input;
		} else {
			this.fullName[0] = errorStyle;
			this.fullName[1] = "Please enter a valid full name.";
		}
	}

	public void flagPhone(boolean valid, String input) {
		if (valid) {
			this.phone[0] = validStyle;
			this.phone[2] = input;
		} else {
			this.phone[0] = errorStyle;
			this.phone[1] = "10 digits";
		}
	}

	public void flagEmail(boolean valid, String input) {
		if (valid) {
			this.email[0] = validStyle;
			this.email[2] = input;
		} else {
			this.email[0] = errorStyle;
			this.email[1] = "Please enter a valid email.";
		}
	}

	public void flagNewPassword(boolean valid, String input) {
		if (valid) {
			this.newPassword[0] = validStyle;
			this.newPassword[2] = input;
		} else {
			this.newPassword[0] = errorStyle;
			this.newPassword[1] = "Please enter a valid password.";
		}
	} 
	
	public void flagOldPassword(boolean valid, String input) {
		if (valid) {
			this.oldPassword[0] = validStyle;
			this.oldPassword[2] = input;
		} else {
			this.oldPassword[0] = errorStyle;
			this.oldPassword[1] = "Incorrect password.";
		}
	}

	public void flagStreet1(boolean valid, String input) {
		if (valid) {
			this.street1[0] = validStyle;
			this.street1[2] = input;
		} else {
			this.street1[0] = errorStyle;
			this.street1[1] = "Please enter a valid street number and name.";
		}
	}

	public void flagStreet2(boolean valid, String input) {
		if (valid) {
			this.street2[0] = validStyle;
			this.street2[2] = input;
		} else {
			this.street2[0] = errorStyle;
			this.street2[1] = "Please enter valid additional address info.";
		}
	}

	public void flagCity(boolean valid, String input) {
		if (valid) {
			this.city[0] = validStyle;
			this.city[2] = input;
		} else {
			this.city[0] = errorStyle;
			this.city[1] = "Please enter a valid city.";
		}
	}

	public void flagState(boolean valid, String input) {
		if (valid) {
			this.state[0] = validStyle;
			this.state[2] = input;
		} else {
			this.state[0] = errorStyle;
			this.state[1] = "";
		}
	}

	public void flagZip(boolean valid, String input) {
		if (valid) {
			this.zip[0] = validStyle;
			this.zip[2] = input;
		} else {
			this.zip[0] = errorStyle;
			this.zip[1] = "00000";
		}
	}

	public void flagZipPlus4(boolean valid, String input) {
		if (valid) {
			this.zipPlus4[0] = validStyle;
			this.zipPlus4[2] = input;
		} else {
			this.zipPlus4[0] = errorStyle;
			this.zipPlus4[1] = "0000";
		}
	}
	
	public void flagPaymentType(boolean valid, String input) {
		if (input.equalsIgnoreCase("creditCard")) {
			this.paymentType[2] = "checked";
			this.paymentType[3] = "";
			this.paymentType[4] = "";
		}
		if (input.equalsIgnoreCase("debitCard")) {
			this.paymentType[2] = "";
			this.paymentType[3] = "checked";
			this.paymentType[4] = "";
		}
		if (input.equalsIgnoreCase("paypal")) {
			this.paymentType[2] = "";
			this.paymentType[3] = "";
			this.paymentType[4] = "checked";
		}
		if (valid) {
			this.paymentType[0] = validStyle;
		} else {
			this.paymentType[0] = errorStyle;
			this.paymentType[1] = "If credit/debit is selected, ensure you have a good card name, number, expiration and cvv. "
					+ "If PayPal is selected, ensure you have a good email address.";
		}
	}

	public void flagCardName(boolean valid, String input) {
		if (valid) {
			this.cardName[0] = validStyle;
			this.cardName[2] = input;
		} else {
			this.cardName[0] = errorStyle;
			this.cardName[1] = "Please enter a first and last name.";
		}
	}

	public void flagCardNum(boolean valid, String input) {
		if (valid) {
			this.cardNum[0] = validStyle;
			this.cardNum[2] = input;
		} else {
			this.cardNum[0] = errorStyle;
			this.cardNum[1] = "0000-0000-0000-0000";
		}
	}
	
	public void flagCardLast4(boolean valid, String input) {
		if (valid) {
			this.cardLast4[0] = validStyle;
			this.cardLast4[2] = input;
		} else {
			this.cardLast4[0] = errorStyle;
			this.cardLast4[1] = "0000";
		}
	}

	public void flagExpiration(boolean valid, String input) {
		if (valid) {
			this.expiration[0] = validStyle;
			this.expiration[2] = input;
		} else {
			this.expiration[0] = errorStyle;
			this.expiration[1] = "YYYY-MM-DD";
		}
	}

	public void flagCvv(boolean valid, String input) {
		if (valid) {
			this.cvv[0] = validStyle;
			this.cvv[2] = input;
		} else {
			this.cvv[0] = errorStyle;
			this.cvv[1] = "000";
		}
	}

	public void flagPaypalEmail(boolean valid, String input) {
		if (valid) {
			this.paypalEmail[0] = validStyle;
			this.paypalEmail[2] = input;
		} else {
			this.paypalEmail[0] = errorStyle;
			this.paypalEmail[1] = "Please enter your PayPal email.";
		}
	}

	public String getValidStyle() {
		return this.validStyle;
	}

	public String getErrorStyle() {
		return this.errorStyle;
	}

	public String[] getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String[] firstName) {
		this.firstName = firstName;
	}

	public String[] getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String[] middleName) {
		this.middleName = middleName;
	}

	public String[] getLastName() {
		return this.lastName;
	}

	public void setLastName(String[] lastName) {
		this.lastName = lastName;
	}

	public String[] getFullName() {
		return this.fullName;
	}

	public void setFullName(String[] fullName) {
		this.fullName = fullName;
	}

	public String[] getPhone() {
		return this.phone;
	}

	public void setPhone(String[] phone) {
		this.phone = phone;
	}

	public String[] getEmail() {
		return this.email;
	}

	public void setEmail(String[] email) {
		this.email = email;
	}

	public String[] getNewPassword() {
		return this.newPassword;
	}

	public void setNewPassword(String[] password) {
		this.newPassword = password;
	}
	
	public String[] getOldPassword() {
		return this.oldPassword;
	}

	public void setOldPassword(String[] password) {
		this.oldPassword = password;
	}

	public String[] getStreet1() {
		return this.street1;
	}

	public void setStreet1(String[] street1) {
		this.street1 = street1;
	}

	public String[] getStreet2() {
		return this.street2;
	}

	public void setStreet2(String[] street2) {
		this.street2 = street2;
	}

	public String[] getCity() {
		return this.city;
	}

	public void setCity(String[] city) {
		this.city = city;
	}

	public String[] getState() {
		return this.state;
	}

	public void setState(String[] state) {
		this.state = state;
	}

	public String[] getZip() {
		return this.zip;
	}

	public void setZip(String[] zip) {
		this.zip = zip;
	}

	public String[] getZipPlus4() {
		return this.zipPlus4;
	}

	public void setZipPlus4(String[] zipPlus4) {
		this.zipPlus4 = zipPlus4;
	}

	public String[] getCardName() {
		return this.cardName;
	}

	public void setCardName(String[] cardName) {
		this.cardName = cardName;
	}

	public String[] getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(String[] cardNum) {
		this.cardNum = cardNum;
	}

	public String[] getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String[] paymentType) {
		this.paymentType = paymentType;
	}

	public String[] getCardLast4() {
		return this.cardLast4;
	}

	public void setCardLast4(String[] cardLast4) {
		this.cardLast4 = cardLast4;
	}

	public String[] getExpiration() {
		return this.expiration;
	}

	public void setExpiration(String[] expiration) {
		this.expiration = expiration;
	}

	public String[] getCvv() {
		return this.cvv;
	}

	public void setCvv(String[] cvv) {
		this.cvv = cvv;
	}

	public String[] getPaypalEmail() {
		return this.paypalEmail;
	}

	public void setPaypalEmail(String[] paypalEmail) {
		this.paypalEmail = paypalEmail;
	}
	
}
