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
import com.cardealer.entity.SignInInputFields;

/** Servlet implementation class UpdateServlet */
@WebServlet("/SignOut")
public class SignOut extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public SignOut() {
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		session.removeAttribute("customer");
		session.removeAttribute("employee");
		session.removeAttribute("vehiclesBought");
		session.removeAttribute("custFields");
		session.removeAttribute("empFields");
		session.removeAttribute("signInFields");
		response.sendRedirect("index.jsp");
		session.removeAttribute("chosenVehicle");
		session.removeAttribute("existingCustomer");
		session.removeAttribute("storedCard");
		session.removeAttribute("goodBid");
		session.removeAttribute("madeBid");
		session.removeAttribute("priceReduction");
		session.removeAttribute("totalPrice");
		session.removeAttribute("soldVehicles");
		session.removeAttribute("soldToCustomers");
		session.removeAttribute("errorAdding");
		session.removeAttribute("vehicleAdded");
		session.removeAttribute("vehicleFields");
	}
}