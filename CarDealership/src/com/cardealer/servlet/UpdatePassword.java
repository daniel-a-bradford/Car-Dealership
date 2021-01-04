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
@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public UpdatePassword() {
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerInputFields fields = new CustomerInputFields();
		Customer currentCustomer = (Customer) session.getAttribute("customer");
		if (currentCustomer == null) {
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.forward(request, response);
			return;
		}
		// Only continue if the old password is correct, otherwise flag the old password entry, and return to customer account page.
		if (currentCustomer.isCorrectPassword(request.getParameter("oldPassword"))) {
			if (request.getParameter("password").contentEquals(request.getParameter("password2"))) {
				fields.flagNewPassword(currentCustomer.setPassword(request.getParameter("password")),
						request.getParameter("password"));
			} else {
				String[] noMatch = { fields.getErrorStyle(), "They don't match.", "" };
				fields.setEmail(noMatch);
			}

			// Attempts to add the updated customer to the customer list.
			CustomerList updateList = new CustomerList();

			// If successful, add the customer to the session and send them to their account page.
			if (updateList.updateCustomer(currentCustomer)) {
				session.setAttribute("customer", currentCustomer);
				CustomerInputFields resetFields = new CustomerInputFields();
				session.setAttribute("custFields", resetFields);
			}
		} else {
			fields.flagOldPassword(false, "");
		}
		// If the result of updating the customer false, a field or fields are invalid, return to account with invalid fields
		// highlighted.
		session.setAttribute("custFields", fields);
		RequestDispatcher rs = request.getRequestDispatcher("customerAccount.jsp");
		rs.forward(request, response);
		return;
	}
}