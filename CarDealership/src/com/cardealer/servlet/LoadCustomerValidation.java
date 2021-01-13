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
import com.cardealer.entity.Vehicle;

/** Servlet implementation class */
@WebServlet("/LoadCustomerValidation")
public class LoadCustomerValidation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public LoadCustomerValidation() {
	} 
	
	// Redirects the parameters from the form in the page to the doGet which runs when the page loads. 
	// NOTE: doPost will not run if included in the jsp page (e.g. <jsp:include page="/thisServlet"/> )
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); 
	}

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Customer currentCustomer = (Customer)session.getAttribute("customer");
		// If there is no customer in the session, the customer is not registered or signed in, so redirect them to the home page.
		if (currentCustomer == null) {
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			request.setAttribute("error", "Please sign in to access your customer account page.");
			rs.forward(request, response);
			return;
		}
		// Find the vehicles in the inventory which have been sold to currentCustomer and add the list to the request.
		Inventory inv = new Inventory();
		ArrayList<Vehicle> soldToCustomer = inv.findBySoldToCustomer(currentCustomer.getCustomerID());
		request.setAttribute("vehiclesBought", soldToCustomer);
		@SuppressWarnings("unchecked")
		CustomerInputFields fields = (CustomerInputFields)request.getAttribute("custFields");
		if (fields == null) {
			fields = new CustomerInputFields();
		}
		request.setAttribute("custFields", fields);
	}

}