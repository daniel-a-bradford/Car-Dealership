package com.cardealer.entity;

public class VehicleInputFields {

	private String validStyle = "input-valid";
	private String errorStyle = "input-error";
	private String checked = "checked=\"checked\"";

	// Describes the field style class (blank if not required), and field value of checked="checked" for use in HTML check boxes if chosen
	private String[] newVehicle = { "", ""};
	private String[] usedVehicle = { "", ""};
	// Describes the field style class (blank if not required), the placeholder text, and field value if valid.
	private String[] make = { "", "Vehicle make", "" };
	private String[] model = { "", "Vehicle model", "" };
	private String[] type = { "", "Vehicle type", "" };
	private String[] year = { "", "Vehicle year", "" };
	private String[] vin = { "", "Vehicle VIN", "" };
	private String[] color = { "", "Vehicle color", "" };
	private String[] odometer = { "", "Vehicle odometer reading", "" };
	private String[] tagline = { "", "Vehicle tagline", "" };
	private String[] description = { "", "Vehicle description", "" };
	private String[] pictureLink = { "", "", "" };
	private String[] dealerPurchased = { "", "YYYY-MM-DD", "" };
	private String[] dealerPurchasePrice = { "", "10000", "" };
	
	public void flagNew(boolean valid, boolean input) {
		if (valid) {
			this.newVehicle[0] = validStyle;
		} else {
			this.newVehicle[0] = errorStyle;
		}
		if (input) {
			this.newVehicle[1] = checked;
		} else {
			this.newVehicle[1] = "";
		}
	}

	public void flagUsed(boolean valid, boolean input) {
		if (valid) {
			this.usedVehicle[0] = validStyle;
		} else {
			this.usedVehicle[0] = errorStyle;
		}
		if (input) {
			this.usedVehicle[1] = checked;
		} else {
			this.usedVehicle[1] = "";
		}
	}

	public void flagMake(boolean valid, String input) {
		if (valid) {
			this.make[0] = validStyle;
			this.make[2] = input;
		} else {
			this.make[0] = errorStyle;
			this.make[1] = "Please enter a valid vehicle make.";
		}
	}

	public void flagModel(boolean valid, String input) {
		if (valid) {
			this.model[0] = validStyle;
			this.model[2] = input;
		} else {
			this.model[0] = errorStyle;
			this.model[1] = "Please enter a valid vehicle model.";
		}
	}

	public void flagType(boolean valid, String input) {
		if (valid) {
			this.type[0] = validStyle;
			this.type[2] = input;
		} else {
			this.type[0] = errorStyle;
			this.type[1] = "Please enter a valid vehicle type.";
		}
	}

	public void flagYear(boolean valid, String input) {
		if (valid) {
			this.year[0] = validStyle;
			this.year[2] = input;
		} else {
			this.year[0] = errorStyle;
			this.year[1] = "Please enter a valid vehicle year.";
		}
	}

	public void flagVin(boolean valid, String input) {
		if (valid) {
			this.vin[0] = validStyle;
			this.vin[2] = input;
		} else {
			this.vin[0] = errorStyle;
			this.vin[1] = "Please enter a valid vehicle VIN.";
		}
	}

	public void flagColor(boolean valid, String input) {
		if (valid) {
			this.color[0] = validStyle;
			this.color[2] = input;
		} else {
			this.color[0] = errorStyle;
			this.color[1] = "Please enter a valid vehicle color.";
		}
	}

	public void flagOdometer(boolean valid, String input) {
		if (valid) {
			this.odometer[0] = validStyle;
			this.odometer[2] = input;
		} else {
			this.odometer[0] = errorStyle;
			this.odometer[1] = "Please enter a valid vehicle odometer reading.";
		}
	}

	public void flagTagline(boolean valid, String input) {
		if (valid) {
			this.tagline[0] = validStyle;
			this.tagline[2] = input;
		} else {
			this.tagline[0] = errorStyle;
			this.tagline[1] = "Please enter a valid vehicle tagline.";
		}
	}

	public void flagDescription(boolean valid, String input) {
		if (valid) {
			this.description[0] = validStyle;
			this.description[2] = input;
		} else {
			this.description[0] = errorStyle;
			this.description[1] = "Please enter a valid vehicle description.";
		}
	}

	public void flagPictureLink(boolean valid, String input) {
		if (valid) {
			this.pictureLink[0] = validStyle;
			this.pictureLink[2] = "Image uploaded as: " + input;
		} else {
			this.pictureLink[0] = errorStyle;
			this.pictureLink[1] = "Upload not successful, please check the file type and try again.";
			this.pictureLink[2] = "";
		}
	}

	public void flagDealerPurchased(boolean valid, String input) {
		if (valid) {
			this.dealerPurchased[0] = validStyle;
			this.dealerPurchased[2] = input;
		} else {
			this.dealerPurchased[0] = errorStyle;
			this.dealerPurchased[1] = "Please enter a valid date YYYY-MM-DD.";
		}
	}

	public void flagDealerPurchasePrice(boolean valid, String input) {
		if (valid) {
			this.dealerPurchasePrice[0] = validStyle;
			this.dealerPurchasePrice[2] = input;
		} else {
			this.dealerPurchasePrice[0] = errorStyle;
			this.dealerPurchasePrice[1] = "Please enter a valid dealer purchase price.";
		}
	}

	public String getCheckedValue() {
		return this.checked;
	}

	public String getValidStyle() {
		return this.validStyle;
	}

	public void setValidStyle(String validStyle) {
		this.validStyle = validStyle;
	}

	public String getErrorStyle() {
		return this.errorStyle;
	}

	public void setErrorStyle(String errorStyle) {
		this.errorStyle = errorStyle;
	}


	public String[] getNew() {
		return this.newVehicle;
	}

	public void setNew(String[] newVehicle) {
		this.newVehicle = newVehicle;
	}

	public String[] getUsed() {
		return this.usedVehicle;
	}

	public void setUsed(String[] usedVehicle) {
		this.usedVehicle = usedVehicle;
	}

	public String[] getNewVehicle() {
		return this.newVehicle;
	}

	public void setNewVehicle(String[] newVehicle) {
		this.newVehicle = newVehicle;
	}

	public String[] getUsedVehicle() {
		return this.usedVehicle;
	}

	public void setUsedVehicle(String[] usedVehicle) {
		this.usedVehicle = usedVehicle;
	}

	public String[] getMake() {
		return this.make;
	}

	public void setMake(String[] make) {
		this.make = make;
	}

	public String[] getModel() {
		return this.model;
	}

	public void setModel(String[] model) {
		this.model = model;
	}

	public String[] getType() {
		return this.type;
	}

	public void setType(String[] type) {
		this.type = type;
	}

	public String[] getYear() {
		return this.year;
	}

	public void setYear(String[] year) {
		this.year = year;
	}

	public String[] getVin() {
		return this.vin;
	}

	public void setVin(String[] vin) {
		this.vin = vin;
	}

	public String[] getColor() {
		return this.color;
	}

	public void setColor(String[] color) {
		this.color = color;
	}

	public String[] getOdometer() {
		return this.odometer;
	}

	public void setOdometer(String[] odometer) {
		this.odometer = odometer;
	}

	public String[] getTagline() {
		return this.tagline;
	}

	public void setTagline(String[] tagline) {
		this.tagline = tagline;
	}

	public String[] getDescription() {
		return this.description;
	}

	public void setDescription(String[] description) {
		this.description = description;
	}

	public String[] getPictureLink() {
		return this.pictureLink;
	}

	public void setPictureLink(String[] pictureLink) {
		this.pictureLink = pictureLink;
	}

	public String[] getDealerPurchased() {
		return this.dealerPurchased;
	}

	public void setDealerPurchased(String[] dealerPurchased) {
		this.dealerPurchased = dealerPurchased;
	}

	public String[] getDealerPurchasePrice() {
		return this.dealerPurchasePrice;
	}

	public void setDealerPurchasePrice(String[] dealerPurchasePrice) {
		this.dealerPurchasePrice = dealerPurchasePrice;
	}

}
