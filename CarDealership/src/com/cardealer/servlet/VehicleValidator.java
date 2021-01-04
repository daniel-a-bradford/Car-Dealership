package com.cardealer.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.CustomerList;
import com.cardealer.entity.Inventory;
import com.cardealer.entity.Vehicle;
import com.cardealer.entity.VehicleInputFields;

/**
 * Servlet implementation class VehicleValidator
 */
@WebServlet(description = "Initializes objects needed for the dealership to run", 
			urlPatterns = { "/Validator" })
public class VehicleValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Inventory thisInventory = (Inventory)session.getAttribute("inventory");
		if (thisInventory == null) {
			// Constructor loads the Inventory from the disk
			thisInventory = new Inventory();
		}
		VehicleInputFields fields = (VehicleInputFields)session.getAttribute("vehicleFields");
		if (fields == null) {
			fields = new VehicleInputFields();
		}

		
		session.setAttribute("inventory", thisInventory);
		
	}

}
