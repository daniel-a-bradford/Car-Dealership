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
@WebServlet("/CustomerUpdate")
public class CustomerUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public CustomerUpdate() {
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerInputFields fields = new CustomerInputFields();
		Customer sessionCustomer = (Customer)session.getAttribute("customer");
		if (sessionCustomer == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		fields.flagFirstName(sessionCustomer.setFirstName(request.getParameter("firstName")), sessionCustomer.getFirstName());
		fields.flagLastName(sessionCustomer.setLastName(request.getParameter("lastName")), sessionCustomer.getLastName());
		fields.flagEmail(sessionCustomer.setEmail(request.getParameter("email")), sessionCustomer.getEmail());
		fields.flagPhone(sessionCustomer.setPhone(request.getParameter("phone")), String.valueOf(sessionCustomer.getPhone()));
		fields.flagStreet1(sessionCustomer.getAddress().setStreet1(sessionCustomer.getAddress().getStreet1()),
				sessionCustomer.getAddress().getStreet1());
		// Street2 field is optional.
		sessionCustomer.getAddress().setStreet2(request.getParameter("street2"));
		fields.flagStreet2(true, sessionCustomer.getAddress().getStreet2());
		fields.flagCity(sessionCustomer.getAddress().setCity(request.getParameter("city")),
				sessionCustomer.getAddress().getCity());
		fields.flagState(sessionCustomer.getAddress().setState(request.getParameter("state")),
				sessionCustomer.getAddress().getState());
		fields.flagZip(sessionCustomer.getAddress().setZip(request.getParameter("zip")),
				String.valueOf(sessionCustomer.getAddress().getZip()));
		// The following fields are optional for registration. However, if the input is changed from default, it must be valid.
		boolean goodOptional = true;
		if (!request.getParameter("cardName").isBlank()) {
			boolean tempValid = sessionCustomer.getPay().setNameOnCard(request.getParameter("cardName"));
			fields.flagCardName(tempValid, request.getParameter("cardName"));
			if (!tempValid) {
				goodOptional = false;
			}
		}
		if (!request.getParameter("cardNum").isBlank() 
				&& !request.getParameter("cardNum").contentEquals("0000-0000-0000-0000") 
				&& !request.getParameter("cardNum").contains("XXXX")) {
			boolean tempValid = sessionCustomer.getPay().setCardNum(request.getParameter("cardNum"));
			fields.flagCardNum(tempValid, request.getParameter("cardNum"));
			if (!tempValid) {
				goodOptional = false;
			}
		}
		if (!request.getParameter("expiration").isBlank()) {
			boolean tempValid = sessionCustomer.getPay().setExpiration(request.getParameter("expiration"));
			fields.flagExpiration(tempValid, request.getParameter("expiration"));
			if (!tempValid) {
				goodOptional = false;
			}
		}
		if (!request.getParameter("cvv").isBlank() 
				&& !request.getParameter("cvv").contentEquals("000")
				&& !request.getParameter("cvv").contains("X")) {
			boolean tempValid = sessionCustomer.getPay().setCvv(request.getParameter("cvv"));
			fields.flagCvv(tempValid, request.getParameter("cvv"));
			if (!tempValid) {
				goodOptional = false;
			}
		}
		if (!request.getParameter("paypalEmail").isBlank()) {
			boolean tempValid = sessionCustomer.getPay().setPaypalEmail(request.getParameter("paypalEmail"));
			fields.flagPaypalEmail(tempValid, request.getParameter("paypalEmail"));
			if (!tempValid) {
				goodOptional = false;
			}
		}
		// Attempts to add the updated customer to the customer list. 
		CustomerList updateList = new CustomerList();
		if (sessionCustomer.isValidCustomer(false)) {
			Customer existingCustomer = updateList.findCustomer(sessionCustomer.getCustomerID());
			if (existingCustomer != null) {
				// If the e-mail exists in the list and it does not belong to the customer with this customerID, .
				if (updateList.findByEmail(sessionCustomer.getEmail()).size() != 0
					&& !existingCustomer.getEmail().equalsIgnoreCase(sessionCustomer.getEmail())) {
					String[] duplicateEmail = { fields.getErrorStyle(), "Email already in use by another customer.", "" };
					fields.setEmail(duplicateEmail);
				}
			}
		}
		// If the update is successful, add the updated customer to the session and send them to their account page.
		if (updateList.updateCustomer(sessionCustomer) && goodOptional) {
			session.setAttribute("customer", sessionCustomer);
			fields.flagUpdateStatus(true);
			request.setAttribute("custFields", fields);
		} else {
			// If the result of updating the customer false, a field or fields are invalid, return to account with invalid fields highlighted.
			fields.flagUpdateStatus(false);
			request.setAttribute("custFields", fields );
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("customerAccount.jsp");
		rd.forward(request, response);
	}
}