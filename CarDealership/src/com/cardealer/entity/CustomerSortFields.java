package com.cardealer.entity;

public class CustomerSortFields {

	private String useStyle = "in-use";

	// Describes the field style class (blank if not required), and field value if valid.
	private StyleAndChecked customerID = new StyleAndChecked ("", false);
	private StyleAndChecked fullName = new StyleAndChecked ("", false);
	private StyleAndChecked phone = new StyleAndChecked ("", false);
	private StyleAndChecked email = new StyleAndChecked ("", false);
	private StyleAndChecked street1 = new StyleAndChecked ("", false);
	private StyleAndChecked city = new StyleAndChecked ("", false);
	private StyleAndChecked state = new StyleAndChecked ("", false);
	private StyleAndChecked zip = new StyleAndChecked ("", false);
	private StyleAndChecked cardName = new StyleAndChecked ("", false);
	private StyleAndChecked cardNum = new StyleAndChecked ("", false);
	private StyleAndChecked expiration = new StyleAndChecked ("", false);
	private StyleAndChecked paypalEmail = new StyleAndChecked ("", false);
	
	public String getUseStyle() {
		return this.useStyle;
	}
	public void setUseStyle(String useStyle) {
		this.useStyle = useStyle;
	}
	
	public StyleAndChecked getCustomerID() {
		return this.customerID;
	}
	public void setCustomerID(boolean isSet) {
		this.customerID.setChecked(isSet);
		if (isSet) {
			this.customerID.setStyle(useStyle);
		} else {
			this.customerID.setStyle("");
		}
	}
	public StyleAndChecked getFullName() {
		return this.fullName;
	}
	public void setFullName(boolean isSet) {
		this.fullName.setChecked(isSet);
		if (isSet) {
			this.fullName.setStyle(useStyle);
		} else {
			this.fullName.setStyle("");
		}
	}
	public StyleAndChecked getPhone() {
		return this.phone;
	}
	public void setPhone(boolean isSet) {
		this.phone.setChecked(isSet);
		if (isSet) {
			this.phone.setStyle(useStyle);
		} else {
			this.phone.setStyle("");
		}
	}
	public StyleAndChecked getEmail() {
		return this.email;
	}
	public void setEmail(boolean isSet) {
		this.email.setChecked(isSet);
		if (isSet) {
			this.email.setStyle(useStyle);
		} else {
			this.email.setStyle("");
		}
	}
	public StyleAndChecked getStreet1() {
		return this.street1;
	}
	public void setStreet1(boolean isSet) {
		this.street1.setChecked(isSet);
		if (isSet) {
			this.street1.setStyle(useStyle);
		} else {
			this.street1.setStyle("");
		}
	}
	public StyleAndChecked getCity() {
		return this.city;
	}
	public void setCity(boolean isSet) {
		this.city.setChecked(isSet);
		if (isSet) {
			this.city.setStyle(useStyle);
		} else {
			this.city.setStyle("");
		}
	}
	public StyleAndChecked getState() {
		return this.state;
	}
	public void setState(boolean isSet) {
		this.state.setChecked(isSet);
		if (isSet) {
			this.state.setStyle(useStyle);
		} else {
			this.state.setStyle("");
		}
	}
	public StyleAndChecked getZip() {
		return this.zip;
	}
	public void setZip(boolean isSet) {
		this.zip.setChecked(isSet);
		if (isSet) {
			this.zip.setStyle(useStyle);
		} else {
			this.zip.setStyle("");
		}
	}
	public StyleAndChecked getCardName() {
		return this.cardName;
	}
	public void setCardName(boolean isSet) {
		this.cardName.setChecked(isSet);
		if (isSet) {
			this.cardName.setStyle(useStyle);
		} else {
			this.cardName.setStyle("");
		}
	}
	public StyleAndChecked getCardNum() {
		return this.cardNum;
	}
	public void setCardNum(boolean isSet) {
		this.cardNum.setChecked(isSet);
		if (isSet) {
			this.cardNum.setStyle(useStyle);
		} else {
			this.cardNum.setStyle("");
		}
	}
	public StyleAndChecked getExpiration() {
		return this.expiration;
	}
	public void setExpiration(boolean isSet) {
		this.expiration.setChecked(isSet);
		if (isSet) {
			this.expiration.setStyle(useStyle);
		} else {
			this.expiration.setStyle("");
		}
	}
	public StyleAndChecked getPaypalEmail() {
		return this.paypalEmail;
	}
	public void setPaypalEmail(boolean isSet) {
		this.paypalEmail.setChecked(isSet);
		if (isSet) {
			this.paypalEmail.setStyle(useStyle);
		} else {
			this.paypalEmail.setStyle("");
		}
	}
}
