package com.cardealer.entity;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.time.LocalDate;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PaymentMethod {
	private StringChecker check = new StringChecker(true);
	private LocalDate defaultDate = LocalDate.of(1900, 1, 1);

	private String nameOnCard = "";
	private String cardNum = "";
	private String cardNumSalt = "";
	// cardLast4 is stored as an encrypted string.
	private String cardLast4 = "";
	private LocalDate expiration = defaultDate;
	// cvv is stored as a an encrypted string.
	private String cvv = "";
	private String cvvSalt = "";
	private boolean cardToken = false;
	private String paypalEmail = "";
	private boolean paypalToken = false;

	public PaymentMethod() {
	}

	public PaymentMethod(String nameOnCard, String cardNum, LocalDate expiration, String cvv, String paypalEmail) {
		if (validateCardInput(nameOnCard, cardNum, expiration, cvv)) {
			// Return good trimmed card num if valid, otherwise return null.
			String parsedCardNum = parseCardNum(cardNum);
			if (parsedCardNum != null) {
				if (setEncryptedLast4(parsedCardNum.substring(15, 19)) && setEncryptedCvv(cvv)) {
					this.nameOnCard = nameOnCard;
					this.cardNum = parsedCardNum.substring(0, 15);
					this.setExpiration(expiration);
				} else {
					System.out.println("Could not parse card number");
				}
			}
		}
		if (validatePaypalInput(paypalEmail)) {
			this.paypalEmail = paypalEmail;
		}
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public boolean setNameOnCard(String nameOnCard) {
		if (check.isValidString(nameOnCard)) {
			this.nameOnCard = nameOnCard;
			return true;
		}
		this.nameOnCard = "";
		return false;
	}

	public String getCardNum() {
		if (this.cardNum.length() == 15) {
			return (this.cardNum + "XXXX");
		}
		System.out.println("cardNum length = " + this.cardNum.length());
		return "XXXX-XXXX-XXXX-XXXX";
	}

	public boolean setCardNum(String cardNum) {
		// Return good trimmed card num if valid, otherwise return null.
		String parsedCardNum = parseCardNum(cardNum);
		if (parsedCardNum != null && !parsedCardNum.equalsIgnoreCase("0000-0000-0000-0000")) {
			if (setEncryptedLast4(parsedCardNum.substring(15, 19))) {
				this.cardNum = parsedCardNum.substring(0, 15);
				return true;
			}
		}
		System.out.println("Invalid card number");
		return false;
	}

	// TODO Consider removing once troubleshooting complete to safeguard payment information.
	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public boolean setExpiration(String expiration) {
		if (check.isDate(expiration)) {
			this.expiration = check.getGoodDate();
			return true;
		}
		System.out.println("Date string invalid: " + expiration);
		return false;
	}

	// TODO Consider removing this once troubleshooting complete to safeguard payment information.
	public String getCvv() {
		if (this.cvv.isBlank()) {
			return "000";
		}
		return "XXX";
	}

	public boolean setCvv(String cvv) {
		return setEncryptedCvv(cvv);
	}

	public String getPaypalEmail() {
		return paypalEmail;
	}

	public boolean setPaypalEmail(String paypalEmail) {
		if (validatePaypalInput(paypalEmail)) {
			this.paypalEmail = paypalEmail;
			return true;
		}
		this.paypalEmail = "";
		return false;
	}

	// TODO Consider removing once troubleshooting complete. Internal class use only.
	public boolean isCardToken() {
		return this.cardToken;
	}

	// TODO Consider removing once troubleshooting complete. Internal class use only.
	public boolean isPaypalToken() {
		return this.paypalToken;
	}
	/** Method isValidPayment checks to see if the payment method indicated by payWithCard is valid. If payWithCard is true, then 
	 * 	if the card name, number, and cvv are valid strings and expiration is not past, isValidPayment returns true. If payWithCard
	 *  is false, then if the PayPal email address is valid returns true. Otherwise it returns false. 
	 *  @param payWithCard
	 *  @return boolean
	 */
	public boolean isValidPayment(boolean payWithCard) {
		if (payWithCard) {
			if (!check.isValidString(this.nameOnCard)) {
				System.out.println("isValidPayment - invalid name on card.");
				return false;
			}
			if (!check.isValidString(this.cardNum)) {
				System.out.println("isValidPayment - invalid card number.");
				return false;
			}
			if (!check.isValidString(cardLast4)) {
				System.out.println("isValidPayment - invalid card number.");
				return false;
			}
			if (!check.isValidString(this.cvv)) {
				System.out.println("isValidPayment - invalid CVV.");
				return false;
			}
			if (isExpired()) {
				System.out.println("isValidPayment - date indicates card is expired.");
				return false;
			}
			this.cardToken = true;
			return true;
		} else {
			if (!check.isEmail(this.paypalEmail)) {
				System.out.println("isValidPayment - PayPal email is not a valid email.");
				return false;
			}
			this.paypalToken = true;
			return true;
		}
	}

	/** Method isExpired checks the expiration LocalDate and if it is after LocalDate.now(), then return true, otherwise false. If
	 * expiration is null, return true.
	 * 
	 * @param expiration */
	public boolean isExpired() {
		if (this.expiration == null) {
			return true;
		}
		if (LocalDate.now().isAfter(this.expiration)) {
			return true;
		}
		return false;
	}

	/** Method validateCardInput checks the input credit or debit card payment information is valid.
	 * 
	 * @return boolean - true if the payment information is valid, otherwise false. */
	private boolean validateCardInput(String nameOnCard, String cardNum, LocalDate expiration, String cvv) {
		if (!check.isValidString(nameOnCard)) {
			System.out.println("validateCardInput - invalid name on card.");
			return false;
		}
		if (!check.isCardNum(cardNum)) {
			System.out.println("validateCardInput - invalid card number.");
			return false;
		}
		if (!check.isValidString(cvv, 3)) {
			System.out.println("validateCardInput - invalid CVV.");
			return false;
		}
		if (expiration == null) {
			System.out.println("validateCardInput - expiration date is null.");
			return false;
		}
		if (LocalDate.now().isAfter(expiration)) {
			System.out.println("validateCardInput - date indicates card is expired.");
			return false;
		}
		this.cardToken = true;
		return true;
	}

	/** Method setEncryptedCardNum takes the string cardNum parses it to a 19 character card number (including separating hyphens)
	 * and then encrypts the card number and stores it as the the cardNum class attribute.
	 * 
	 * @param cardNum
	 * @return */
	private boolean setEncryptedLast4(String Last4) {
		if (!check.isInt(Last4, 4)) {
			System.out.println("setEncryptedLast4 - Not a 4 digit integer.");
			return false;
		}
		try {
			String salt = generateNewSalt();
			String encryptedCardNum = generateEncryptedString(Last4, salt);
			this.cardLast4 = encryptedCardNum;
			this.cardNumSalt = salt;
			return true;
		} catch (Exception ex) {
			System.out.println("Out of memory during decoding or other unspecified error.\n" + ex);
			ex.printStackTrace();
		}
		return false;
	}

	/** Method parseCardNum takes a cardNum string and converts is to the format ####-####-####-#### If the cardNum string is not
	 * a valid card number, it returns null.
	 * 
	 * @param cardNum
	 * @return */
	private String parseCardNum(String cardNum) {
		if (!check.isCardNum(cardNum)) {
			return null;
		}
		cardNum = cardNum.trim();
		// First check if the cardNum is 16 digits. If so insert the hypens between
		if (cardNum.length() == 16) {
			cardNum = (cardNum.substring(0, 4) + "-" + cardNum.substring(4, 8) + "-" + cardNum.substring(8, 12) + "-"
					+ cardNum.substring(12, 16));
		}
		return cardNum;
	}

	/** Method setEncryptedCvv takes the string cvv, checks to see if it is a 3 digit integer. If it is, it encrypts the cvv and
	 * stores it as the the cvv class attribute.
	 * 
	 * @param cvv
	 * @return boolean */
	private boolean setEncryptedCvv(String cvv) {
		if (!check.isInt(cvv, 3)) {
			System.out.println("setEncryptedCvv - Not a valid cvv.");
			return false;
		}
		cvv = cvv.trim();
		try {
			String salt = generateNewSalt();
			String encryptedCvv = generateEncryptedString(cvv, salt);
			this.cvv = encryptedCvv;
			this.cvvSalt = salt;
			return true;
		} catch (Exception ex) {
			System.out.println("Out of memory during decoding or other unspecified error.\n" + ex);
			ex.printStackTrace();
		}
		return false;
	}

	/** Method validatePaypalInput checks the PayPal email information is valid.
	 * 
	 * @return boolean - true if the email is valid, otherwise false. */
	private boolean validatePaypalInput(String paypalEmail) {
		if (check.isEmail(paypalEmail)) {
			this.paypalToken = true;
			return true;
		}
		return false;
	}

	/** isCorrectCardNum encodes the inputCardNum with the current user's Base64 encrypted salt and compares it to the current
	 * user's encrypted cardNum. If it matches, it returns true. Otherwise false.
	 * 
	 * @param inputCardNum
	 * @return */
	public boolean isCorrectLast4(String inputLast4) {
		// Ensure the card number is formatted correctly, return a good and trimmed value.
		if (!check.isInt(inputLast4, 4)) {
			System.out.println("isCorrectLast4 - Not a 4 digit integer.");
			return false;
		}
		try {
			String inputHash = generateEncryptedString(inputLast4, this.cardNumSalt);
			if (inputHash.equals(this.cardLast4)) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println("Out of memory during decoding or other unspecified error.\n" + ex);
			ex.printStackTrace();
		}
		return false;
	}

	/** Method isCorrectCvv encodes the inputCvv with the current user's Base64 encrypted salt and compares it to the current
	 * user's encrypted cvv. If it matches, it returns true. Otherwise false.
	 * 
	 * @param inputCvv
	 * @return */
	public boolean isCorrectCvv(String inputCvv) {
		if (!check.isValidString(inputCvv)) {
			return false;
		}
		inputCvv = inputCvv.trim();
		try {
			String inputHash = generateEncryptedString(inputCvv, this.cvvSalt);
			if (inputHash.equals(this.cvv)) {
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

	public boolean parsePaymentString(String inputString) {
		if (!check.isValidString(inputString)) {
			System.out.println("Not a valid input string found.");
			return false;
		}
		String[] attributeArray = inputString.split("~`~");
		// Check if there are enough data fields
		if (attributeArray.length != 8) {
			System.out.println("Expected 8 attributes, but found " + attributeArray.length);
			return false;
		}
		// Trim whitespace from the ends of the strings.
		for (int index = 0; index < attributeArray.length; index++) {
			attributeArray[index] = attributeArray[index].trim();
		}
		// Don't attempt to load or validate if card number, cvv, and paypalEmail elements are blank.
		if (attributeArray[1].isBlank() && attributeArray[5].isBlank() && attributeArray[7].isBlank()) {
			return true;
		}
		// Expect to have either PayPal or card information. If neither return false.
		boolean oneValid = false;
		if (setPaypalEmail(attributeArray[7])) {
			oneValid = true;
		}
		if (setNameOnCard(attributeArray[0]) && setExpiration(attributeArray[4])) {
			this.cardNum = attributeArray[1];
			this.cardNumSalt = attributeArray[2];
			this.cardLast4 = attributeArray[3];
			this.cvv = attributeArray[5];
			this.cvvSalt = attributeArray[6];
			oneValid = true;
		}
		if (oneValid) {
			return true;
		}

		System.out.println("parsePaymentString - PaymentMethod attribute string is not valid.");
		// TODO remove this following troubleshooting
		System.out.println("This is it: " + this.toString());
		return false;
	}

	/** Method toDataFileString creates a string where the object attributes are delimited by " ~`~". The space is necessary for
	 * the read function which uses .split in case string attributes are empty.
	 * 
	 * @return */
	public String toDataFileString() {
		return this.nameOnCard + " ~`~" + this.cardNum + " ~`~" + this.cardNumSalt + " ~`~" + this.cardLast4 + " ~`~"
				+ this.expiration + " ~`~" + this.cvv + " ~`~" + this.cvvSalt + " ~`~" + this.paypalEmail + " ~`~";
	}

	@Override
	public String toString() {
		return this.nameOnCard + " " + getCardNum() + " which expires on " + this.expiration + "\nPaypal e-mail address is "
				+ this.paypalEmail;
	}
}
