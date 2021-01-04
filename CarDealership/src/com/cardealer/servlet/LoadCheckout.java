package com.cardealer.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.Customer;
import com.cardealer.entity.CustomerInputFields;
import com.cardealer.entity.Inventory;
import com.cardealer.entity.StringChecker;
import com.cardealer.entity.Vehicle;

/** Servlet implementation class */
@WebServlet("/LoadCheckout")
public class LoadCheckout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public LoadCheckout() {
	}

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Customer currentCustomer = (Customer) session.getAttribute("customer");
		// If there is no customer in the session, the customer is not signed in, so set existing customer and stored card to
		// false.
		if (currentCustomer == null) {
			currentCustomer = new Customer(0);
			session.setAttribute("storedCard", false);
			// If there is a customer in the session, set existing customer to true and check card information.
		} else {
			// If card number is totally masked (XXXX-XXXX-XXXX-XXXX), it is blank.
			// If cvv is "000" then it is blank.
			// So if either are blank, it will prompt for the full information, rather than just to confirm.
			if (currentCustomer.getPay().getCardNum().contains("XXXX-") || currentCustomer.getPay().getCvv().contains("0")) {
				session.setAttribute("storedCard", false);
			} else {
				session.setAttribute("storedCard", true);
			}
		}
		if (currentCustomer.getCustomerID() != 0) {
			session.setAttribute("existingCustomer", true);
		}
		CustomerInputFields fields = (CustomerInputFields) session.getAttribute("custFields");
		if (fields == null) {
			System.out.println("LoadCheckout load new fields");
			fields = new CustomerInputFields();
			session.setAttribute("custFields", fields);
		}
	}
}