package com.cardealer.entity;

public class SignInInputFields {
	
	private String validStyle = "valid-input";
	private String errorStyle = "input-error";

	// Describes the field style class (blank if not required), the placeholder text, and field value if valid.
	private String[] email = { "", "Your email address", ""};
	private String[] password = { "", "Enter password", ""}; 


	public void flagEmail(boolean valid, String input) {
		if (valid) {
			this.email[0] = validStyle;
			this.email[2] = input;
		} else {
			this.email[0] = errorStyle;
			this.email[1] = "Email not found.";
		}
	}

	public void flagPassword(boolean valid) {
		if (valid) {
			this.password[0] = validStyle;
		} else {
			this.password[0] = errorStyle;
			this.password[1] = "Incorrect password.";
		}
	}

	public String getValidStyle() {
		return this.validStyle;
	}

	public String getErrorStyle() {
		return this.errorStyle;
	}

	public String[] getEmail() {
		return this.email;
	}

	public void setEmail(String[] email) {
		this.email = email;
	}

	public String[] getPassword() {
		return this.password;
	}

	public void setPassword(String[] password) {
		this.password = password;
	}
}

