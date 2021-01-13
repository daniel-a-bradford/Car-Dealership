package com.cardealer.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.Customer;
import com.cardealer.entity.CustomerInputFields;
import com.cardealer.entity.CustomerList;

/** Servlet implementation class UpdateServlet */
@WebServlet("/Registration")
public class Registration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public Registration() {
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerInputFields fields = new CustomerInputFields();
		Customer newCustomer = new Customer(0L);
		fields.flagFirstName(newCustomer.setFirstName(request.getParameter("firstName")), newCustomer.getFirstName());
		fields.flagLastName(newCustomer.setLastName(request.getParameter("lastName")), newCustomer.getLastName());
		fields.flagEmail(newCustomer.setEmail(request.getParameter("email")), newCustomer.getEmail());
		fields.flagNewPassword(newCustomer.setPassword(request.getParameter("password")), request.getParameter("password"));
		fields.flagPhone(newCustomer.setPhone(request.getParameter("phone")), request.getParameter("phone"));
		fields.flagStreet1(newCustomer.getAddress().setStreet1(request.getParameter("street1")),
				newCustomer.getAddress().getStreet1());
		// Street2 field is optional.
		newCustomer.getAddress().setStreet2(request.getParameter("street2"));
		fields.flagStreet2(true, newCustomer.getAddress().getStreet2());
		fields.flagCity(newCustomer.getAddress().setCity(request.getParameter("city")),
				newCustomer.getAddress().getCity());
		fields.flagState(newCustomer.getAddress().setState(request.getParameter("state")),
				newCustomer.getAddress().getState());
		fields.flagZip(newCustomer.getAddress().setZip(request.getParameter("zip")),
				request.getParameter("zip"));
		// The following fields are optional for registration. However, if the input is changed from default, it must be valid.
		boolean goodOptional = true;
		if (!request.getParameter("cardName").isBlank()) {
			boolean tempValid = newCustomer.getPay().setNameOnCard(request.getParameter("cardName"));
			fields.flagCardName(tempValid, request.getParameter("cardName"));
			if (!tempValid) {
				goodOptional = false;
			}
		}
		if (!request.getParameter("cardNum").isBlank() 
				&& !request.getParameter("cardNum").contentEquals("0000-0000-0000-0000")) {
			boolean tempValid = newCustomer.getPay().setCardNum(request.getParameter("cardNum"));
			fields.flagCardNum(tempValid, request.getParameter("cardNum"));
			if (!tempValid) {
				goodOptional = false;
			}
		}
		if (!request.getParameter("expiration").isBlank()) {
			boolean tempValid = newCustomer.getPay().setExpiration(request.getParameter("expiration"));
			fields.flagExpiration(tempValid, request.getParameter("expiration"));
			if (!tempValid) {
				goodOptional = false;
			}
		}
		if (!request.getParameter("cvv").isBlank() 
				&& !request.getParameter("cvv").contentEquals("000")) {
			boolean tempValid = newCustomer.getPay().setCvv(request.getParameter("cvv"));
			fields.flagCvv(tempValid, request.getParameter("cvv"));
			if (!tempValid) {
				goodOptional = false;
			}
		}
		if (!request.getParameter("paypalEmail").isBlank()) {
			boolean tempValid = newCustomer.getPay().setPaypalEmail(request.getParameter("paypalEmail"));
			fields.flagPaypalEmail(tempValid, request.getParameter("paypalEmail"));
			if (!tempValid) {
				goodOptional = false;
			}
		}
		// Attempts to add the new customer to the customer list. 
		CustomerList addList = new CustomerList();
		// Check if the email (user ID) is already in the system. If so add a custom email field flag.
		if (addList.findByEmail(newCustomer.getEmail()).size() != 0) {
			String[] duplicateEmail = { fields.getErrorStyle(), "Email already exists in the system.", "" };
			fields.setEmail(duplicateEmail);
		}
		newCustomer = addList.addCustomer(newCustomer);
		// If successful, add the customer to the session and send them to their account page.
		if (newCustomer != null && goodOptional) {
			session.setAttribute("customer", newCustomer);
			response.sendRedirect("customerAccount.jsp");
			return;
		}
		// If the result of adding the customer is null, it is invalid, so send the user back to signup.
		request.setAttribute("custFields", fields );
		RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
		rd.forward(request, response);
	}

}