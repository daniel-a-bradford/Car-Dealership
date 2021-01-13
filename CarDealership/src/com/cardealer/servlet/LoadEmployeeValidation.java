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

import com.cardealer.entity.Employee;
import com.cardealer.entity.EmployeeInputFields;
import com.cardealer.entity.Inventory;
import com.cardealer.entity.Vehicle;

/** Servlet implementation class */
@WebServlet("/LoadEmployeeValidation")
public class LoadEmployeeValidation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public LoadEmployeeValidation() {
	} 
	
	// Redirects the parameters from the form in the page to the doGet which runs when the page loads. 
	// NOTE: doPost will not run if included in the jsp page (e.g. <jsp:include page="/thisServlet"/> )
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); 
	}

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Employee currentEmployee = (Employee)session.getAttribute("employee");
		// If there is no employee in the session, the employee is not registered or signed in, so redirect them to the home page.
		if (currentEmployee == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		// Find the vehicles in the inventory which have been sold to currentEmployee and send the list to the session.
		Inventory inv = new Inventory();
		@SuppressWarnings("unchecked")
		EmployeeInputFields fields = (EmployeeInputFields)session.getAttribute("empFields");
		if (fields == null) {
			fields = new EmployeeInputFields();
		}
		session.setAttribute("empFields", fields);
	}

}