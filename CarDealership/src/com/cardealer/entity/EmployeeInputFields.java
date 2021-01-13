package com.cardealer.entity;

public class EmployeeInputFields {

	private String validStyle = "input-valid";
	private String errorStyle = "input-error";

	// Describes the field style class (blank if not required), the placeholder text, and field value if valid.
	private String[] firstName = { "", "Your first name", "" };
	private String[] middleName = { "", "Your middle name", "" };
	private String[] lastName = { "", "Your last name", "" };
	private String[] fullName = { "", "Your full name", "" };
	private String[] email = { "", "Your email address", "" };
	private String[] newPassword = { "", "Set a password", "" };
	private String[] oldPassword = { "", "Enter password", "" };
	private String[] updateStatus = { "", "", "" };

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
	
	public void flagUpdateStatus(boolean valid) {
		if (valid) {
			this.updateStatus[0] = validStyle;
			this.updateStatus[2] = "Update Successful";
		} else {
			this.updateStatus[0] = errorStyle;
			this.updateStatus[2] = "Could not update, please fix highlighted fields.";
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
	
	public String[] getUpdateStatus() {
		return this.updateStatus;
	}

	public void setUpdateStatus(String[] updateStatus) {
		this.updateStatus = updateStatus;
	}
}
