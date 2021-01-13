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
import com.cardealer.entity.CustomerList;
import com.cardealer.entity.Employee;
import com.cardealer.entity.EmployeeList;
import com.cardealer.entity.SignInInputFields;

/** Servlet implementation class UpdateServlet */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public SignIn() {
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Determine the referring page, includes http:\\localhost:8080\CarDealership\ then page name.
		// This works for a redirect, but not RequestDispatcher since it only deals with local pages.
		String referringAbsPage = request.getHeader("Referer");
		System.out.println("Absolute referring page: " + referringAbsPage);
		boolean returnToReferring = true;
		// If it is blank or null, just go to the index.jsp.
		if (referringAbsPage == null || referringAbsPage.isBlank()) {
			returnToReferring = false;
		} 
		HttpSession session = request.getSession(true);
		SignInInputFields fields = new SignInInputFields();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (!email.isBlank()) {
			if (!password.isBlank()) {
				CustomerList currentList = new CustomerList();
				ArrayList<Customer> result = currentList.findByEmail(email);
				EmployeeList empList = new EmployeeList();
				Employee foundEmployee = empList.findEmployee(email);
				if (result.size() > 0) {
					Customer foundCustomer = result.get(0);
					
					if (foundCustomer.isCorrectPassword(password)) {
						session.setAttribute("customer", foundCustomer);
						session.setAttribute("signInFields", new SignInInputFields());
						if (returnToReferring) {
							response.sendRedirect(referringAbsPage);
						} else {
							response.sendRedirect("customerAccount.jsp");
						}
						return;
					} else {
						System.out.println("password incorrect");
						fields.flagPassword(false);
					}
				} else if (foundEmployee != null) {
					if (foundEmployee.isCorrectPassword(password)) {
						session.setAttribute("employee", foundEmployee);
						session.setAttribute("signInFields", new SignInInputFields());
						response.sendRedirect("employeeAccount.jsp");
						return;
					} else {
						System.out.println("password incorrect");
						fields.flagPassword(false);
					}
				} else {
					System.out.println("user id not found");
					fields.flagEmail(false, email);
				}
			} else {
				fields.flagEmail(true, email);
				fields.flagPassword(false);
			}
		} else

		{
			fields.flagEmail(false, "");
		}
		session.setAttribute("signInFields", fields);
		if (returnToReferring) {
			response.sendRedirect(referringAbsPage);
		} else {
			response.sendRedirect("index.jsp");
		}
	}
}