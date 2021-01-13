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
@WebServlet("/UpdateCustomerPassword")
public class UpdateCustPassword extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public UpdateCustPassword() {
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerInputFields fields = new CustomerInputFields();
		Customer currentCustomer = (Customer) session.getAttribute("customer");
		if (currentCustomer == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		request.removeAttribute("PasswordStatus");
		// Only continue if the old password is correct, otherwise flag the old password entry, and return to customer account page.
		if (currentCustomer.isCorrectPassword(request.getParameter("oldPassword"))) {
			if (request.getParameter("password").contentEquals(request.getParameter("password2"))) {
				fields.flagNewPassword(currentCustomer.setPassword(request.getParameter("password")),
						request.getParameter("password"));
			} else {
				String[] noMatch = { fields.getErrorStyle(), "These don't match.", "" };
				fields.setNewPassword(noMatch);
				request.setAttribute("PasswordStatus", "New passwords do not match.");
				request.setAttribute("custFields", fields);
				RequestDispatcher rs = request.getRequestDispatcher("customerAccount.jsp");
				rs.forward(request, response);
				return;
			}
			// Attempts to add the updated customer to the customer list.
			CustomerList updateList = new CustomerList();
			// If successful, add the customer to the session and send them to their account page.
			if (updateList.updateCustomer(currentCustomer)) {
				session.setAttribute("customer", currentCustomer);
				CustomerInputFields resetFields = new CustomerInputFields();
				request.setAttribute("custFields", resetFields);
				request.setAttribute("PasswordStatus", "Update successful!");
			}
		} else {
			fields.flagOldPassword(false, "");
			request.setAttribute("PasswordStatus", "Incorrect current password.");
		}
		// If the result of updating the customer false, a field or fields are invalid, return to account with invalid fields
		// highlighted.
		request.setAttribute("custFields", fields);
		RequestDispatcher rs = request.getRequestDispatcher("customerAccount.jsp");
		rs.forward(request, response);
	}
}