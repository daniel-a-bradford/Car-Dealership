package com.cardealer.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.Customer;
import com.cardealer.entity.CustomerInputFields;
import com.cardealer.entity.CustomerList;
import com.cardealer.entity.Inventory;
import com.cardealer.entity.StringChecker;
import com.cardealer.entity.Vehicle;

/** Servlet implementation class UpdateServlet */
@WebServlet("/BuyVehicle")
public class BuyVehicle extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public BuyVehicle() {
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		StringChecker check = new StringChecker(true);
		CustomerInputFields fields = new CustomerInputFields();
		Vehicle chosenVehicle = (Vehicle) session.getAttribute("chosenVehicle");
		// If there is no vehicle to purchase or the vehicle is sold, skip it and go back to browse.
		if (chosenVehicle == null || chosenVehicle.isSold()) {
			response.sendRedirect("browse.jsp");
			return;
		}
		Customer currentCustomer = (Customer) session.getAttribute("customer");
		// If there is no customer in the session, the customer is not signed in so set up to register a new one.
		boolean isNew = false;
		boolean needsCard = false;
		boolean needsPaypal = false;
		if (currentCustomer == null) {
			currentCustomer = new Customer(0);
			isNew = true;
			// If there is a customer in the session, check if card information is blank.
		} else {
			// If card number is totally masked (XXXX-XXXX-XXXX-XXXX), it is blank.
			// If cvv is "000" then it is blank.
			// So if either are blank, look to validate and store the full card information.
			if (currentCustomer.getPay().getCardNum().contains("XXXX-") || currentCustomer.getPay().getCvv().contains("0")) {
				needsCard = true;
			}
		}
		// If their selected payment method is PayPal, they do not need to input card information.
		if (check.isValidString(request.getParameter("paymentMethod")) && check.getGoodString().equalsIgnoreCase("paypal")) {
			needsCard = false;
			needsPaypal = true;
		}
		fields.flagFirstName(currentCustomer.setFirstName(request.getParameter("firstName")), currentCustomer.getFirstName());
		fields.flagLastName(currentCustomer.setLastName(request.getParameter("lastName")), currentCustomer.getLastName());
		fields.flagEmail(currentCustomer.setEmail(request.getParameter("email")), currentCustomer.getEmail());
		// Password only needs to be set for new customers.
		if (isNew) {
			fields.flagNewPassword(currentCustomer.setPassword(request.getParameter("password")),
					request.getParameter("password"));
		}
		fields.flagPhone(currentCustomer.setPhone(request.getParameter("phone")), request.getParameter("phone"));
		fields.flagStreet1(currentCustomer.getAddress().setStreet1(request.getParameter("street1")),
				currentCustomer.getAddress().getStreet1());
		// Street2 field is optional so don't flag it.
		currentCustomer.getAddress().setStreet2(request.getParameter("street2"));
		fields.flagStreet2(true, currentCustomer.getAddress().getStreet2());
		fields.flagCity(currentCustomer.getAddress().setCity(request.getParameter("city")),
				currentCustomer.getAddress().getCity());
		fields.flagState(currentCustomer.getAddress().setState(request.getParameter("state")),
				currentCustomer.getAddress().getState());
		fields.flagZip(currentCustomer.getAddress().setZip(request.getParameter("zip")), request.getParameter("zip"));

		fields.flagPaypalEmail(currentCustomer.getPay().setPaypalEmail(request.getParameter("paypalEmail")),
			request.getParameter("paypalEmail"));
		// goodCard indicates all required card payment information is valid for a new or an existing customer.
		boolean goodCard = false;
		if (needsCard) {
			// With no previous card information, attempt to set all card attributes
			fields.flagCardName(currentCustomer.getPay().setNameOnCard(request.getParameter("cardName")),
					request.getParameter("cardName"));
			fields.flagCardNum(currentCustomer.getPay().setCardNum(request.getParameter("cardNum")),
					request.getParameter("cardNum"));
			fields.flagExpiration(currentCustomer.getPay().setExpiration(request.getParameter("expiration")),
					request.getParameter("expiration"));
			fields.flagExpiration(!currentCustomer.getPay().isExpired(), "expired");
			fields.flagCvv(currentCustomer.getPay().setCvv(request.getParameter("cvv")), request.getParameter("cvv"));
			// If the card name, number, and cvv are valid strings and expiration is not past, isValidPayment is true.
			goodCard = currentCustomer.getPay().isValidPayment(true);
		} else {
			// Existing customers only need to check the last 4 of their card and cvv against the encrypted values.
			fields.flagCardName(currentCustomer.getPay().setNameOnCard(request.getParameter("cardName")),
					request.getParameter("cardName"));
			fields.flagCardLast4(currentCustomer.getPay().isCorrectLast4(request.getParameter("cardLast4")),
					request.getParameter("cardLast4"));
			fields.flagExpiration(currentCustomer.getPay().setExpiration(request.getParameter("expiration")),
					request.getParameter("expiration"));
			fields.flagExpiration(!currentCustomer.getPay().isExpired(), "expired");
			fields.flagCvv(currentCustomer.getPay().isCorrectCvv(request.getParameter("cvv")), request.getParameter("cvv"));
			// If the card name, number, and cvv are valid strings and expiration is not past, isValidPayment is true.
			// For security, need to make sure the last 4 and cvv entered match
			goodCard = currentCustomer.getPay().isValidPayment(true) 
					&& currentCustomer.getPay().isCorrectLast4(request.getParameter("cardLast4")) 
					&& currentCustomer.getPay().isCorrectCvv(request.getParameter("cvv"));
		}
		// goodPayment only if the payment type is card and all card attributes are correct or if the payment type is PayPal and
		// 		the PayPal email is valid.
		boolean goodPayment = (!needsPaypal && goodCard) || (needsPaypal && currentCustomer.getPay().isValidPayment(false));
		fields.flagPaymentType(goodPayment, request.getParameter("paymentMethod"));
		// If the pay information is correct, attempt to add the updated customer to the customer list.
		CustomerList updateList = new CustomerList();
		boolean goodCustomer = false;
		if (goodPayment) {
			// New customers need to be added. Else existing customer needs to be updated.
			if (isNew) {
				// Check if the email (user ID) is already in the system. If so add a custom email field flag.
				if (updateList.findByEmail(currentCustomer.getEmail()).size() != 0) {
					String[] duplicateEmail = { fields.getErrorStyle(), "Email already exists in the system.", "" };
					fields.setEmail(duplicateEmail);
				}
				currentCustomer = updateList.addCustomer(currentCustomer);
				// If successful, the customer information is good, otherwise addCustomer returns null.
				if (currentCustomer != null) {
					goodCustomer = true;
				}
			} else {
				Customer existingCustomer = updateList.findCustomer(currentCustomer.getCustomerID());
				if (existingCustomer != null) {
					// Flag email field if the e-mail exists in the list and it does not belong to the customer with this
					// customerID.
					if (updateList.findByEmail(currentCustomer.getEmail()).size() != 0
							&& !existingCustomer.getEmail().equalsIgnoreCase(currentCustomer.getEmail())) {
						String[] duplicateEmail = { fields.getErrorStyle(), "Email already in use by another customer.", "" };
						fields.setEmail(duplicateEmail);
					} else {
						// If successful, the customer information is good
						if (updateList.updateCustomer(currentCustomer)) {
							goodCustomer = true;
						}
					}
				}
			}
		}
		// If customer is stored successfully, they have good pay information so add their id to the vehicle the updated customer
		// to
		// the session and send them to their account page.
		if (goodCustomer) {
			session.setAttribute("customer", currentCustomer);
			chosenVehicle.setDateSold(LocalDate.now());
			chosenVehicle.setSoldToCustomer(currentCustomer.getCustomerID());
			BigDecimal bidPrice = (BigDecimal) request.getAttribute("totalPrice");
			// In case the attribute was changed, ensure it is not null, is greater than min bid price and less than list price.
			if (bidPrice != null && bidPrice.compareTo(chosenVehicle.getMinBidPrice()) > 0
					&& bidPrice.compareTo(chosenVehicle.getListPrice()) < 0) {
				chosenVehicle.setSoldPrice(bidPrice);
			} else {
				// Otherwise, set the sold price to the list price.
				chosenVehicle.setSoldPrice(chosenVehicle.getListPrice());
			}
			Inventory inv = new Inventory();
			if (!inv.updateVehicle(chosenVehicle)) {
				System.out.println("BuyVehicle - could not update vehicle.");
			}
			session.setAttribute("soldVehicle", chosenVehicle);
			// Cleanup the session
			session.removeAttribute("chosenVehicle");
			response.sendRedirect("congrats.jsp");
		} else {
			// If the customer and pay information were not correct and stored, return to account with invalid fields highlighted.
			session.setAttribute("custFields", fields);
			response.sendRedirect("checkout.jsp");
		}
	}
}