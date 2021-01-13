package com.cardealer.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.Address;
import com.cardealer.entity.Customer;
import com.cardealer.entity.CustomerList;
import com.cardealer.entity.Inventory;
import com.cardealer.entity.PaymentMethod;
import com.cardealer.entity.StringChecker;
import com.cardealer.entity.Vehicle;
import com.cardealer.entity.VehicleInputFields;
import com.cardealer.entity.VehicleSearchFields;

/**
 * Servlet implementation class Startup
 */
@WebServlet(description = "Looks for non-blank parameters then narrows down the search results based on them.", 
			urlPatterns = { "/SearchVehicles" })
public class SearchVehicles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		StringChecker check = new StringChecker();
		Inventory thisInventory = new Inventory();
		@SuppressWarnings("unchecked")
		ArrayList<Vehicle> searchResults = (ArrayList<Vehicle>)request.getAttribute("searchResults");
		if (searchResults == null) {
			searchResults = thisInventory.findForSale();
		}
		VehicleSearchFields searchFields = (VehicleSearchFields) request.getAttribute("searchFields");
		// Skip the narrowing down sold vehicles if searchFields is not set. This is a new search.
		if (searchFields == null) {
			searchFields = new VehicleSearchFields();
		}
		// Flag and store attributes with which LoadVehicleBrowse will search.
		if (check.isValidString(request.getParameter("new"))) {
			if (request.getParameter("new").equalsIgnoreCase("new")) {
				searchFields.flagNew(true, true);
			} 
		} else {
			searchFields.flagNew(false, false);
		}
		if (check.isValidString(request.getParameter("used"))) {
			if (request.getParameter("used").equalsIgnoreCase("used")) {
				searchFields.flagUsed(true, true);
			}
		} else {
			searchFields.flagUsed(false, false);
		}
		if (check.isBigDecimal(request.getParameter("lowPrice")) ) {
			searchFields.flagLowPrice(true, request.getParameter("lowPrice"));
		} else  {
			searchFields.flagLowPrice(false, "");
		}
		if (check.isBigDecimal(request.getParameter("highPrice"))) {
			searchFields.flagHighPrice(true, request.getParameter("highPrice"));
		}  else {
			searchFields.flagHighPrice(false, "");
		}
		if (check.isValidString(request.getParameter("type"))) {
			searchFields.flagType(true, request.getParameter("type"));
		}  else {
			searchFields.flagType(false, "");
		}
		if (check.isInt(request.getParameter("year"))) {
			searchFields.flagYear(true, request.getParameter("year"));
		}  else {
			searchFields.flagYear(false, "");
		}
		if (check.isValidString(request.getParameter("make"))) {
			searchFields.flagMake(true, request.getParameter("make"));
		}  else {
			searchFields.flagMake(false, "");
		}
		if (check.isValidString(request.getParameter("model"))) {
			searchFields.flagModel(true, request.getParameter("model"));
		}  else {
			searchFields.flagModel(false, "");
		}
		if (check.isValidString(request.getParameter("color"))) {
			searchFields.flagColor(true, request.getParameter("color"));
		} else {
			searchFields.flagColor(false, "");
		}
		if (check.isValidString(request.getParameter("odometerRange"))) {
			// Split the odometer string to break out the values separated by a hyphen
			String[] miles = request.getParameter("odometerRange").split("-");
			if (miles.length == 2) {
				// If the number parses as an integer store it as lowMiles. StringChecker stores the parsed value as goodInt.
				if (check.isInt(miles[0]) && check.isInt(miles[1])) {
					searchFields.flagOdometerRange(true, request.getParameter("odometerRange"));
				}
			}
		} else {
			searchFields.flagOdometerRange(false, "");
		}
		if (check.isValidString(request.getParameter("tagline"))) {
			searchFields.flagTagline(true, request.getParameter("tagline"));
		} else {
			searchFields.flagTagline(false, "");
		}
		if (check.isValidString(request.getParameter("description"))) {
			searchFields.flagDescription(true, request.getParameter("description"));
		} else {
			searchFields.flagDescription(false, "");
		}
		request.setAttribute("searchFields", searchFields);
		RequestDispatcher rd = request.getRequestDispatcher("browse.jsp");
		rd.include(request, response);
//		response.sendRedirect("browse.jsp");
	}
}
