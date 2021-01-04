package com.cardealer.entity;

public class VehicleSearchFields {

	private String usedStyle = "used-in-search";
	private String unusedStyle = "";
	private String checked = "true";

	// Describes the field style class (blank if not required), and field value of checked="checked" for use in HTML check boxes if chosen
	private String[] newVehicle = { "", ""};
	private String[] usedVehicle = { "", ""};
	// Describes the field style class (blank if not required), the placeholder text, and field value if valid.
	private String[] make = { "", "Vehicle make", "" };
	private String[] model = { "", "Vehicle model", "" };
	private String[] type = { "", "Vehicle type", "" };
	private String[] year = { "", "Year made", "" };
	private String[] vin = { "", "AA1A1A1", "" };
	private String[] color = { "", "White", "" };
	private String[] odometerRange = { "", "50000-75000", "" };
	private String[] tagline = { "", "Amazing", "" };
	private String[] description = { "", "Bluetooth", "" };
	private String[] pictureLink = { "", "", "" };
	private String[] lowPrice = { "", "1000.00", "" };
	private String[] highPrice = { "", "50000.00", "" };
	
	public void flagNew(boolean valid, boolean input) {
		if (valid) {
			this.newVehicle[0] = usedStyle;
		} else {
			this.newVehicle[0] = unusedStyle;
		}
		if (input) {
			this.newVehicle[1] = checked;
		} else {
			this.newVehicle[1] = "";
		}
	}

	public void flagUsed(boolean valid, boolean input) {
		if (valid) {
			this.usedVehicle[0] = usedStyle;
		} else {
			this.usedVehicle[0] = unusedStyle;
		}
		if (input) {
			this.usedVehicle[1] = checked;
		} else {
			this.usedVehicle[1] = "";
		}
	}

	public void flagMake(boolean valid, String input) {
		if (valid) {
			this.make[0] = usedStyle;
			this.make[2] = input;
		} else {
			this.make[0] = unusedStyle;
			this.make[2] = "";
		}
	}

	public void flagModel(boolean valid, String input) {
		if (valid) {
			this.model[0] = usedStyle;
			this.model[2] = input;
		} else {
			this.model[0] = unusedStyle;
			this.model[2] = "";
		}
	}

	public void flagType(boolean valid, String input) {
		if (valid) {
			this.type[0] = usedStyle;
			this.type[2] = input;
		} else {
			this.type[0] = unusedStyle;
			this.type[2] = "";
		}
	}

	public void flagYear(boolean valid, String input) {
		if (valid) {
			this.year[0] = usedStyle;
			this.year[2] = input;
		} else {
			this.year[0] = unusedStyle;
			this.year[2] = "";
		}
	}

	public void flagVin(boolean valid, String input) {
		if (valid) {
			this.vin[0] = usedStyle;
			this.vin[2] = input;
		} else {
			this.vin[0] = unusedStyle;
			this.vin[2] = "";
		}
	}

	public void flagColor(boolean valid, String input) {
		if (valid) {
			this.color[0] = usedStyle;
			this.color[2] = input;
		} else {
			this.color[0] = unusedStyle;
			this.color[2] = "";
		}
	}

	public void flagOdometerRange(boolean valid, String input) {
		if (valid) {
			this.odometerRange[0] = usedStyle;
			this.odometerRange[2] = input;
		} else {
			this.odometerRange[0] = unusedStyle;
			this.odometerRange[2] = "";
		}
	}

	public void flagTagline(boolean valid, String input) {
		if (valid) {
			this.tagline[0] = usedStyle;
			this.tagline[2] = input;
		} else {
			this.tagline[0] = unusedStyle;
			this.tagline[2] = "";
		}
	}

	public void flagDescription(boolean valid, String input) {
		if (valid) {
			this.description[0] = usedStyle;
			this.description[2] = input;
		} else {
			this.description[0] = unusedStyle;
			this.description[2] = "";
		}
	}

	public void flagPictureLink(boolean valid, String input) {
		if (valid) {
			this.pictureLink[0] = usedStyle;
			this.pictureLink[2] = input;
		} else {
			this.pictureLink[0] = unusedStyle;
			this.pictureLink[2] = "";
		}
	}

	public void flagLowPrice(boolean valid, String input) {
		if (valid) {
			this.lowPrice[0] = usedStyle;
			this.lowPrice[2] = input;
		} else {
			this.lowPrice[0] = unusedStyle;
			this.lowPrice[2] = "";
		}
	}

	public void flagHighPrice(boolean valid, String input) {
		if (valid) {
			this.highPrice[0] = usedStyle;
			this.highPrice[2] = input;
		} else {
			this.highPrice[0] = unusedStyle;
			this.highPrice[2] = "";
		}
	}
	
	public String getUsedStyle() {
		return this.usedStyle;
	}

	public void setUsedStyle(String usedStyle) {
		this.usedStyle = usedStyle;
	}

	public String getUnusedStyle() {
		return this.unusedStyle;
	}

	public void setUnusedStyle(String unusedStyle) {
		this.unusedStyle = unusedStyle;
	}

	public String getChecked() {
		return this.checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
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

	public String[] getOdometerRange() {
		return this.odometerRange;
	}

	public void setOdometerRange(String[] odometerRange) {
		this.odometerRange = odometerRange;
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

	public String[] getLowPrice() {
		return this.lowPrice;
	}

	public void setLowPrice(String[] lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String[] getHighPrice() {
		return this.highPrice;
	}

	public void setHighPrice(String[] highPrice) {
		this.highPrice = highPrice;
	}

}
