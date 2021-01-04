package com.cardealer.entity;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Vehicle {

	private StringChecker check = new StringChecker(true);
	private LocalTime defaultTime = LocalTime.of(00, 00);
	private LocalDate defaultDate = LocalDate.of(1900, 01, 01);
	private LocalDateTime defaultDateTime = LocalDateTime.of(defaultDate, defaultTime);
	private BigDecimal defaultPrice = new BigDecimal("0.00");

	private BigDecimal recommendedPriceMarkup = new BigDecimal("0.75");
	private BigDecimal listPriceMarkup = new BigDecimal("0.5");
	private int daysUntilBid = 120;
	private BigDecimal bidListPriceMaxReduction = new BigDecimal("0.1");

	private LocalDateTime lastModified = defaultDateTime;
	private boolean newVehicle = false;
	// TODO include boolean inCart to ensure a vehicle is held while someone is in
	// the process of purchasing it.
	private String make = "";
	private String model = "";
	private String type = "";
	private int year = 0;
	// VIN is the vehicle's unique identifier.
	private String vin = "";
	private String color = "";
	private int odometer;
	private String tagline = "";
	private String description = "";
	private String pictureLink = "";
	private LocalDate dealerPurchased = defaultDate;
	private BigDecimal dealerPurchasePrice = defaultPrice;
	private BigDecimal recommendedPrice = defaultPrice;
	private BigDecimal listPrice = defaultPrice;
	private BigDecimal minBidPrice = defaultPrice;
	private BigDecimal soldPrice = defaultPrice;
	private LocalDate dateSold = defaultDate;
	private long soldToCustomer = 0L;

	public Vehicle() {
	}

	public Vehicle(LocalDateTime lastModified, boolean newVehicle, String make, String model, String type, int year,
			String vin, String color, int odometer, String tagline, String description, String pictureLink,
			LocalDate dealerPurchased, BigDecimal dealerPurchasePrice, BigDecimal soldPrice, LocalDate dateSold,
			long soldToCustomer) {
		this.lastModified = lastModified;
		this.newVehicle = newVehicle;
		this.make = make;
		this.model = model;
		this.type = type;
		this.year = year;
		this.vin = vin;
		this.color = color;
		this.odometer = odometer;
		this.tagline = tagline;
		this.description = description;
		this.pictureLink = pictureLink;
		this.dealerPurchased = dealerPurchased;
		this.dealerPurchasePrice = dealerPurchasePrice;
		this.soldPrice = soldPrice;
		this.dateSold = dateSold;
		this.soldToCustomer = soldToCustomer;
	} 

	public Vehicle(String lastModified, String newVehicle, String make, String model, String type, String year, String vin,
			String color, String odometer, String tagline, String description, String pictureLink,
			String dealerPurchased, String dealerPurchasePrice, String soldPrice, String dateSold,
			String soldToCustomer) {
		setLastModified(lastModified);
		setNew(newVehicle);
		setMake(make);
		setModel(model);
		setType(type);
		setYear(year);
		setVin(vin);
		setColor(color);
		setOdometer(odometer);
		setTagline(tagline);
		setDescription(description);
		setPictureLink(pictureLink);
		setDealerPurchased(dealerPurchased);
		setDealerPurchasePrice(dealerPurchasePrice);
		setSoldPrice(soldPrice);
		setDateSold(dateSold);
		setSoldToCustomer(soldToCustomer);
	}

	public LocalTime getDefaultTime() {
		return defaultTime;
	}

	public LocalDate getDefaultDate() {
		return defaultDate;
	}

	public LocalDateTime getDefaultDateTime() {
		return defaultDateTime;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public boolean setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
		return true;
	}

	public boolean setLastModified(String inputString) {
		if (!check.isDateTime(inputString)) {
			System.out.println("Not a valid LocalDateTime " + inputString);
			this.lastModified = this.defaultDateTime;
			return false;
		}
		this.lastModified = check.getGoodDateTime();
		return true;
	}

	public boolean isNewVehicle() {
		return newVehicle;
	}

	public void setNew(boolean newVehicle) {
		this.newVehicle = newVehicle;
		this.lastModified = LocalDateTime.now();
	}

	public boolean setNew(String inputString) {
		if (!check.isValidString(inputString)) {
			return false;
		}
		if (!inputString.equals("true") && !inputString.equals("false")) {
			System.out.println("Not a valid boolean " + inputString);
			this.newVehicle = false;
			return false;
		}
		this.newVehicle = Boolean.parseBoolean(inputString);
		this.lastModified = LocalDateTime.now();
		return true;
	}

	public String getMake() {
		return make;
	}

	public boolean setMake(String make) {
		if (check.isValidString(make)) {
			this.make = make.trim();
			this.lastModified = LocalDateTime.now();
			return true;
		} else {
			this.make = "";
			this.lastModified = LocalDateTime.now();
			return false;
		}
	}

	public String getModel() {
		return model;
	}

	public boolean setModel(String model) {
		if (check.isValidString(model)) {
			this.model = model.trim();
			this.lastModified = LocalDateTime.now();
			return true;
		} else {
			this.model = "";
			this.lastModified = LocalDateTime.now();
			return false;
		}
	}

	public String getType() {
		return type;
	}

	public boolean setType(String type) {
		if (check.isValidString(type)) {
			this.lastModified = LocalDateTime.now();
			this.type = type.trim();
			return true;
		} else {
			this.type = "";
			this.lastModified = LocalDateTime.now();
			return false;
		}
	}

	public int getYear() {
		return year;
	}

	public boolean setYear(int year) {
		return setYear(String.valueOf(year));
	}

	public boolean setYear(String inputString) {
		// Check if year is a 4 digit integer and assign year
		if (!check.isInt(inputString, 4)) {
			System.out.println("Not a valid 4-digit integer " + inputString);
			this.year = 0;
			this.lastModified = LocalDateTime.now();
			return false;
		}
		this.year = check.getGoodInt();
		this.lastModified = LocalDateTime.now();
		return true;
	}

	public String getVin() {
		return vin;
	}

	public boolean setVin(String vin) {
		if (check.isValidString(vin)) {
			this.vin = vin.trim();
			this.lastModified = LocalDateTime.now();
			return true;
		} else {
			this.vin = "";
			this.lastModified = LocalDateTime.now();
			return false;
		}
	}

	public String getColor() {
		return color;
	}

	public boolean setColor(String color) {
		if (check.isValidString(color)) {
			this.color = color;
			this.lastModified = LocalDateTime.now();
			return true;
		} else {
			this.color = "";
			this.lastModified = LocalDateTime.now();
			return false;
		}
	}

	public int getOdometer() {
		return odometer;
	}

	public boolean setOdometer(int odometer) {
		return setOdometer(String.valueOf(odometer));
	}

	public boolean setOdometer(String inputString) {
		// Check if odometer is an integer 0 to 500000 and assign odometer.
		if (check.isInt(inputString, 0, 500000)) {
			this.odometer = check.getGoodInt();
			this.lastModified = LocalDateTime.now();
			return true;
		}
		System.out.println("Not a valid integer 0 to 500000 " + inputString);
		this.lastModified = LocalDateTime.now();
		return false;
	}

	public String getTagline() {
		return tagline;
	}

	public boolean setTagline(String tagline) {
		if (check.isValidString(tagline)) {
			this.tagline = tagline.trim();
			this.lastModified = LocalDateTime.now();
			return true;
		} else {
			this.tagline = "";
			this.lastModified = LocalDateTime.now();
			return false;
		}
	}

	public String getDescription() {
		return description;
	}

	public boolean setDescription(String description) {
		if (check.isValidString(description)) {
			this.description = description.trim();
			this.lastModified = LocalDateTime.now();
			return true;
		} else {
			this.description = "";
			this.lastModified = LocalDateTime.now();
			return false;
		}

	}

	public String getPictureLink() {
		return pictureLink;
	}

	public boolean setPictureLink(String pictureLink) {
		if (check.isValidString(pictureLink)) {
			this.pictureLink = pictureLink.trim();
			this.lastModified = LocalDateTime.now();
			return true;
		} else {
			this.pictureLink = "";
			this.lastModified = LocalDateTime.now();
			return false;
		}
	}

	public LocalDate getDealerPurchased() {
		return dealerPurchased;
	}

	public boolean setDealerPurchased(LocalDate dealerPurchased) {
		return setDealerPurchased(dealerPurchased.toString());
	}

	public boolean setDealerPurchased(String inputString) {
		// Check and assign dealer purchase date
		if (check.isDate(inputString)) {
			this.dealerPurchased = check.getGoodDate();
			this.lastModified = LocalDateTime.now();
			return true;
		}
		System.out.println("Not a valid LocalDate " + inputString);
		this.dealerPurchased = this.defaultDate;
		this.lastModified = LocalDateTime.now();
		return false;
	}

	public BigDecimal getDealerPurchasePrice() {
		return dealerPurchasePrice;
	}

	public boolean setDealerPurchasePrice(BigDecimal dealerPurchasePrice) {
		return setDealerPurchasePrice(dealerPurchasePrice.toString());
	}

	public boolean setDealerPurchasePrice(String inputString) {
		// Check dealer purchase price is a positive decimal number from 0 to 600000
		if (check.isBigDecimal(inputString, BigDecimal.ZERO, new BigDecimal("600000.00"))) {
			this.dealerPurchasePrice = check.getGoodBigDecimal();
			// Recommended price = dealer purchase price + (dealer purchase price * recommended price markup percentage)
			this.recommendedPrice = this.dealerPurchasePrice
					.add(this.dealerPurchasePrice.multiply(this.recommendedPriceMarkup));
			// Recommended price = dealer purchase price + (dealer purchase price * list price markup percentage)
			this.listPrice = this.dealerPurchasePrice.add(this.dealerPurchasePrice.multiply(this.listPriceMarkup));
			// Recommended price = dealer purchase price + (dealer purchase price * (list price markup - bid reduction percentage)
			this.minBidPrice = this.dealerPurchasePrice.add(this.dealerPurchasePrice
					.multiply(this.listPriceMarkup.subtract(bidListPriceMaxReduction)));
			this.lastModified = LocalDateTime.now();
			return true;
		}
		System.out.println("Not a valid BigDecimal from 0 to 600000 " + inputString);
		this.dealerPurchasePrice = defaultPrice;
		this.recommendedPrice = defaultPrice;
		this.listPrice = defaultPrice;
		this.lastModified = LocalDateTime.now();
		return false;
	}

	public BigDecimal getRecommendedPrice() {
		return recommendedPrice;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public BigDecimal getMinBidPrice() {
		return minBidPrice;
	}

	public BigDecimal getSoldPrice() {
		return soldPrice;
	}

	public boolean setSoldPrice(BigDecimal soldPrice) {
		return setSoldPrice(soldPrice.toString());
	}

	public boolean setSoldPrice(String inputString) {
		// Check dealer purchase price is a positive decimal number from 0 to 600000
		if (check.isBigDecimal(inputString, BigDecimal.ZERO, new BigDecimal("600000.0"))) {
			this.soldPrice = check.getGoodBigDecimal();
			this.lastModified = LocalDateTime.now();
			return true;
		}
		System.out.println("Not a valid BigDecimal from 0 to 600000 " + inputString);
		this.soldPrice = defaultPrice;
		this.lastModified = LocalDateTime.now();
		return false;
	}

	public LocalDate getDateSold() {
		return dateSold;
	}

	public boolean setDateSold(LocalDate dateSold) {
		return setDateSold(dateSold.toString());
	}

	public boolean setDateSold(String inputString) {
		// Check and set date sold
		if (check.isDate(inputString)) {
			this.dateSold = check.getGoodDate();
			this.lastModified = LocalDateTime.now();
			return true;
		}
		System.out.println("Not a valid date " + inputString);
		this.dateSold = this.defaultDate;
		this.lastModified = LocalDateTime.now();
		return false;
	}

	public long getSoldToCustomer() {
		return soldToCustomer;
	}

	public boolean setSoldToCustomer(long soldToCustomer) {
		return setSoldToCustomer(Long.toString(soldToCustomer));
	}

	public boolean setSoldToCustomer(String inputString) {
		// Check and set soldToCustomer
		if (check.isLong(inputString)) {
			this.soldToCustomer = check.getGoodLong();
			this.lastModified = LocalDateTime.now();
			return true;
		}
		System.out.println("Not a valid long integer for customerID " + inputString);
		this.lastModified = LocalDateTime.now();
		return false;
	}

	/**
	 * Method isSold checks to see if the dateSold, soldPrice and soldTtoCustomer
	 * have been set to other than default values. if so, returns true, otherwise
	 * returns false.
	 * 
	 * @return
	 */
	public boolean isSold() {
		if (this.dateSold.equals(defaultDate) || this.soldPrice == this.defaultPrice || this.soldToCustomer == 0L) {
			return false;
		}
		return true;
	}

	/**
	 * Method isBiddable checks to see if the dateSold, soldPrice and soldTtoCustomer
	 * have been set to other than default values. if so, returns true, otherwise
	 * returns false.
	 * 
	 * @return
	 */
	public boolean isBiddable() {
		// Check if the dealer purchased date plus daysUntilBid today or earlier. 
		// Subtract 1 day to allow for <= rather than just < check which .isBefore does.
		if (this.dealerPurchased.plusDays(daysUntilBid - 1).isBefore(LocalDate.now())) {
			return true;
		}
		return false;
	}

	public String formatPrice(BigDecimal price) {
		return price.setScale(2, RoundingMode.HALF_UP).toString();
	}

	/**
	 * Method wordWrap inserts carriage returns to separate lines into a length <=
	 * specified by lineLength. It searches from lineLength for the previous space
	 * or hypen to avoid splitting words. If lineLength is < 1, then its default
	 * length is 50 characters. If the inputString is blank or null, it returns and
	 * empty String.
	 * 
	 * @param inputString
	 * @param lineLength
	 * @return
	 */
	public String wordWrap(String inputString, int lineLength) {
		StringChecker check = new StringChecker(true);
		if (lineLength < 1) {
			lineLength = 50;
		}
		String outputString = "";
		if (check.isValidString(inputString)) {
			inputString = inputString.trim();
			for (int beginIndex = 0; beginIndex < inputString.length(); beginIndex += lineLength) {
				// Set endIndex to the character at lineLength further on the string, or the end
				// of the string.
				int endIndex = beginIndex + lineLength;
				if (endIndex > inputString.length() - 1) {
					endIndex = inputString.length() - 1;
				}
				for (int backup = 0; backup < (endIndex - beginIndex); backup++) {
					// Find the end of words or hyphens separating compound words then reduce
					// endIndex to that value.
					if (inputString.charAt(endIndex - backup) == ' ' || inputString.charAt(endIndex - backup) == '-') {
						endIndex = endIndex - backup;
						break;
					}
					// If no spaces or hyphens are found and one character is left, do not adjust
					// endIndex
					if (backup == (endIndex - beginIndex + 1)) {
						break;
					}
				}
				// Split the string into a substring and place a carriage return at the end of
				// the line.
				outputString += description.substring(beginIndex, endIndex + 1) + "\n";
			}
			return outputString;
		}
		return "";
	}

	public boolean parseVehicleString(String inputString) {
		if (!check.isValidString(inputString)) {
			System.out.println("Not a valid input string found.");
			return false;
		}
		String[] attributeArray = inputString.split("~`~");
		// Check if there are enough data fields
		if (attributeArray.length != 17) {
			System.out.println("Expected 17 attributes, but found " + attributeArray.length);
			for (int index = 0; index < attributeArray.length; index++)
				System.out.println(attributeArray[index]);
			return false;
		}
		// Trim whitespace from the ends of the strings.
		for (int index = 0; index < attributeArray.length; index++) {
			attributeArray[index] = attributeArray[index].trim();
		}
		if (setLastModified(attributeArray[0]) && setNew(attributeArray[1]) && setMake(attributeArray[2])
				&& setModel(attributeArray[3]) && setType(attributeArray[4]) && setYear(attributeArray[5])
				&& setVin(attributeArray[6]) && setColor(attributeArray[7]) && setOdometer(attributeArray[8])
				&& setTagline(attributeArray[9]) && setDescription(attributeArray[10])
				&& setPictureLink(attributeArray[11]) && setDealerPurchased(attributeArray[12])
				&& setDealerPurchasePrice(attributeArray[13]) && setSoldPrice(attributeArray[14])
				&& setDateSold(attributeArray[15])) {
			setSoldToCustomer(attributeArray[16]);
			// Reset the last modified time from the file since the setters each update it.
			setLastModified(attributeArray[0]);
			return true;
		}
		System.out.println("Vehicle attribute string not correct.");
		return false;
	}

	/**
	 * Method isValidVehicle checks the vehicle attributes to see if all data fields
	 * are populated with non-default values. If so, it returns true, otherwise,
	 * false.
	 * 
	 * @return boolean - true if this Vehicle is valid, false if not
	 */
	public boolean isValidVehicle() {
		if (this.lastModified == this.defaultDateTime) {
			System.out.println("lastModified has not been set.");
			return false;
		}
		if (!check.isValidString(this.make)) {
			System.out.println("make is not a valid string");
			return false;
		}
		if (!check.isValidString(this.model)) {
			System.out.println("model is not a valid string");
			return false;
		}
		if (!check.isValidString(this.type)) {
			System.out.println("type is not a valid string");
			return false;
		}
		if (this.year != 0 && String.format("%04d", this.year).length() != 4) {
			System.out.println("year is not a four digit value");
			return false;
		}
		if (!check.isValidString(this.vin)) {
			System.out.println("vin is not a valid string");
			return false;
		}
		if (!check.isValidString(this.color)) {
			System.out.println("color is not a valid string");
			return false;
		}
		if (!check.isInt(Integer.toString(odometer), 0, 500000)) {
			System.out.println("odometer is not an integer from 0 to 500000");
			return false;
		}
		if (!check.isValidString(this.tagline)) {
			System.out.println("tagline is not a valid string");
			return false;
		}
		if (!check.isValidString(this.description)) {
			System.out.println("description is not a valid string");
			return false;
		}
		if (!check.isValidString(this.pictureLink)) {
			// TODO check to see if the picture link refers to an actual file.
			System.out.println("pictureLink is not a valid string");
			return false;
		}
		if (this.dealerPurchased == this.defaultDate || this.dealerPurchased.isAfter(LocalDate.now())) {
			System.out.println("dealerPurchased date is not set or is in the future.");
			return false;
		}
		if (this.dealerPurchasePrice.compareTo(defaultPrice) < 0
				|| this.dealerPurchasePrice.compareTo(new BigDecimal("600000.00")) > 0) {
			System.out.println("dealerPurchasePrice is not between 0.00 and 600000.00");
			return false;
		}
		if (isSold()) {
			if (this.soldPrice.compareTo(defaultPrice) < 0
					|| this.soldPrice.compareTo(new BigDecimal("600000.00")) > 0) {
				System.out.println("Vehicle is sold, but soldPrice is not between 0.00 and 600000.00");
				return false;
			}
		} else {
			if (!this.dateSold.isEqual(this.defaultDate) || this.soldToCustomer != 0L 
					|| this.soldPrice.compareTo(this.defaultPrice) != 0) {
				System.out.println("Vehicle is not sold, but soldPrice and/or dateSold and/or soldToCustomer are set.");
				return false;
			}
		}
		return true;
	}

	/**
	 * Method toDataFileString creates a string where the object attributes are
	 * delimited by " ~`~". Embedded objects are delimited by " ~~``~~". The space
	 * is necessary for the read function which uses .split in case string
	 * attributes are empty.
	 * 
	 * @return
	 */
	public String toDataFileString() {
		return this.lastModified + " ~`~" + this.newVehicle + " ~`~" + this.make + " ~`~" + this.model + " ~`~" + this.type
				+ " ~`~" + this.year + " ~`~" + this.vin + " ~`~" + this.color + " ~`~" + this.odometer + " ~`~"
				+ this.tagline + " ~`~" + this.description + " ~`~" + this.pictureLink + " ~`~" + this.dealerPurchased
				+ " ~`~" + this.dealerPurchasePrice + " ~`~" + this.soldPrice + " ~`~" + this.dateSold + " ~`~"
				+ this.soldToCustomer;

		// System.lineSeparator();
	}

	@Override
	public String toString() {
		String outputString = "";
		if (this.newVehicle) {
			outputString += "New ";
		} else {
			outputString += "Pre-owned ";
		}
		outputString += this.color + " " + this.year + " " + this.make + " " + this.model + " " + this.type + " with "
				+ this.odometer + " miles. \n";
		if (!this.dealerPurchased.equals(defaultDate) && !isSold()) {
			outputString += this.tagline + " only $" + formatPrice(this.getListPrice()) + "\n"
					+ wordWrap(this.description, 50) + " has been on the lot since " + this.dealerPurchased;
		}
		if (isSold()) {
			outputString += this.vin + " was sold to customer#" + this.soldToCustomer + " on " + this.dateSold;
		}
		return outputString;
	}

}
