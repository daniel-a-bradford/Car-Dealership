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
@WebServlet("/SetChosenVehicle")
public class SetChosenVehicle extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public SetChosenVehicle() {
	}

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		StringChecker check = new StringChecker(true);
		Inventory inv = new Inventory();
		// Set chosenVehicle if the VIN is found in the inventory and the vehicle is not already sold.
			if (check.isValidString(request.getParameter("chosenVIN"))) {
				Vehicle chosenVehicle = inv.findVehicle(request.getParameter("chosenVIN"));
				if (chosenVehicle != null && !chosenVehicle.isSold()) {
					session.setAttribute("chosenVehicle", chosenVehicle);
				}
			}
		response.sendRedirect("checkout.jsp");
	}

}