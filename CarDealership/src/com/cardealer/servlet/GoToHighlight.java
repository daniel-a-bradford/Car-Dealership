package com.cardealer.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.Inventory;
import com.cardealer.entity.StringChecker;
import com.cardealer.entity.Vehicle;
import com.cardealer.entity.VehicleSearchFields;

/** Servlet implementation */
@WebServlet(description = "Sets the VehicleSearchField of VIN which LoadVehicleBrowse uses to override all other results.", urlPatterns = {
		"/GoToHighlight" })
public class GoToHighlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** The method loads all vehicles for sale from the inventory. If searchFields is set in the session, the method uses
	 * 	the values set there in order to narrow down the list based on what the user previously selected.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		StringChecker check = new StringChecker();
		
		VehicleSearchFields searchFields = (VehicleSearchFields) session.getAttribute("searchFields");
		if (searchFields == null) {
			searchFields = new VehicleSearchFields();
		}
		ArrayList<Vehicle> searchResults = new ArrayList<Vehicle>();
		// If vin is a valid string, then the user clicked on a highlight picture, so only find the vehicle.
		System.out.println("The vin passed is: " + request.getParameter("vin"));
		if (check.isValidString(request.getParameter("vin"))) {
			Inventory thisInventory = new Inventory();
			searchFields.flagVin(true, request.getParameter("vin"));
			session.setAttribute("searchFields", searchFields);
			response.sendRedirect("browse.jsp");
			return;
		}
	}
}
