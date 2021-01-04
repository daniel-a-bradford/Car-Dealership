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
@WebServlet(description = "Initializes objects needed for the vehicle browsing page display search results.", urlPatterns = {
		"/LoadVehicleBrowse" })
public class LoadVehicleBrowse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** The method loads all vehicles for sale from the inventory. If searchFields is set in the session, the method uses the
	 * values set there in order to narrow down the list based on what the user previously selected. */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		StringChecker check = new StringChecker();
		Inventory thisInventory = new Inventory();
		VehicleSearchFields searchFields = (VehicleSearchFields) session.getAttribute("searchFields");
		ArrayList<Vehicle> searchResults = new ArrayList<Vehicle>();
		// If vin is a valid string, then the user clicked on a highlight picture, so only find the vehicle.
		if (searchFields != null) {
			if (check.isValidString(searchFields.getVin()[2])) {
				searchResults.add(thisInventory.findVehicle(searchFields.getVin()[2]));
				session.setAttribute("searchResults", searchResults);
				response.sendRedirect("browse.jsp");
				return;
			}
		}
		searchResults = thisInventory.findForSale();
		// Skip the narrowing down sold vehicles if searchFields is not set. This is a new search.
		if (searchFields == null) {
			searchFields = new VehicleSearchFields();
		} else {
			/*
			 * Unset field values (element 2 in all but new and used) will be blank. Set field values will contain values with
			 * which the user wishes to search. isValidString will return false if the value is blank or null. Avoids exceptions
			 * if the value get sets to null.
			 */
			if (check.isValidString(searchFields.getNewVehicle()[1]) && check.isValidString(searchFields.getNewVehicle()[1])) {
				searchResults = thisInventory.findNew(searchResults);
				searchResults.addAll(thisInventory.findUsed(searchResults));
			} else if (check.isValidString(searchFields.getNewVehicle()[1])) {
				searchResults = thisInventory.findUsed(searchResults);
			} else if (check.isValidString(searchFields.getUsedVehicle()[1])) {
				searchResults = thisInventory.findUsed(searchResults);
			}
			String lowPrice = searchFields.getLowPrice()[2];
			String highPrice = searchFields.getHighPrice()[2];
			if (check.isBigDecimal(lowPrice) && check.isBigDecimal(highPrice)) {
				searchResults = thisInventory.findByListPrice(searchResults, new BigDecimal(lowPrice), new BigDecimal(highPrice));
			} else if (check.isBigDecimal(lowPrice)) {
				searchResults = thisInventory.findByListPrice(searchResults, check.getGoodBigDecimal(),
						new BigDecimal("600000.00"));
			} else if (check.isBigDecimal(highPrice)) {
				searchResults = thisInventory.findByListPrice(searchResults, new BigDecimal("0.00"), check.getGoodBigDecimal());
			}
			if (check.isValidString(searchFields.getType()[2])) {
				searchResults = thisInventory.findByType(searchResults, searchFields.getType()[2]);
			}
			if (check.isValidString(searchFields.getMake()[2])) {
				searchResults = thisInventory.findByMake(searchResults, searchFields.getMake()[2]);
			}

			if (check.isValidString(searchFields.getModel()[2])) {
				searchResults = thisInventory.findByModel(searchResults, searchFields.getModel()[2]);
			}
			if (check.isValidString(searchFields.getColor()[2])) {
				searchResults = thisInventory.findByColor(searchResults, searchFields.getColor()[2]);
				System.out.println(searchResults.size() + " found.");
			}
			if (check.isValidString(searchFields.getOdometerRange()[2])) {
				// Split the odometer string to break out the values separated by a hyphen
				String[] miles = searchFields.getOdometerRange()[2].split("-");
				if (miles.length == 2) {
					// If the number parses as an integer store it as lowMiles. StringChecker stores the parsed value as goodInt.
					if (check.isInt(miles[0])) {
						// Do the same for the high miles string, then search if valid.
						int lowMiles = check.getGoodInt();
						if (check.isInt(miles[1])) {
							searchResults = thisInventory.findByOdometer(searchResults, lowMiles, check.getGoodInt());
						}
					}
				}

			}
			if (check.isValidString(searchFields.getTagline()[2])) {
				searchResults = thisInventory.findByTagline(searchResults, searchFields.getTagline()[2]);
			}
			if (check.isValidString(searchFields.getDescription()[2])) {
				searchResults = thisInventory.findByDescription(searchResults, searchFields.getDescription()[2]);
			}
		}
		// Search results are narrowed down based on search field values. Now prepare the dropdown lists.
		ArrayList<String> typeList = new ArrayList<String>();
		ArrayList<String> makeList = new ArrayList<String>();
		ArrayList<String> modelList = new ArrayList<String>();
		ArrayList<String> yearList = new ArrayList<String>();
		ArrayList<String> colorList = new ArrayList<String>();
		int highestOdometer = 0;
		for (Vehicle tempVehicle : searchResults) {
			if (!typeList.contains(tempVehicle.getType())) {
				typeList.add(tempVehicle.getType());
			}
			if (!makeList.contains(tempVehicle.getMake())) {
				makeList.add(tempVehicle.getMake());
			}
			if (!modelList.contains(tempVehicle.getModel())) {
				modelList.add(tempVehicle.getModel());
			}
			if (!yearList.contains(Integer.toString(tempVehicle.getYear()))) {
				yearList.add(Integer.toString(tempVehicle.getYear()));
			}
			if (!colorList.contains(tempVehicle.getColor())) {
				colorList.add(tempVehicle.getColor());
			}
			if (highestOdometer < tempVehicle.getOdometer()) {
				highestOdometer = tempVehicle.getOdometer();
			}
		}
		Collections.sort(typeList);
		Collections.sort(makeList);
		Collections.sort(modelList);
		Collections.sort(yearList);
		Collections.sort(colorList);
		ArrayList<String> odometerRangeList = new ArrayList<String>();
		// Round highest odometer up to the nearest 10000
		highestOdometer = ((highestOdometer + 10000) / 10000) * 10000;
		for (int temp = 0; temp < highestOdometer; temp += highestOdometer / 10) {
			odometerRangeList.add(temp + "-" + (temp + (highestOdometer / 10)));
		}
		session.setAttribute("typeList", typeList);
		session.setAttribute("makeList", makeList);
		session.setAttribute("modelList", modelList);
		session.setAttribute("yearList", yearList);
		session.setAttribute("colorList", colorList);
		session.setAttribute("odometerRangeList", odometerRangeList);
		session.setAttribute("searchResults", searchResults);
	}
}
