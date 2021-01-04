package com.cardealer.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.Inventory;
import com.cardealer.entity.StringChecker;
import com.cardealer.entity.Vehicle;
import com.cardealer.entity.VehicleInputFields;

/** Servlet implementation class AddVehicleServlet */
@WebServlet("/AddVehicle")
public class AddVehicle extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AddVehicle() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringChecker check = new StringChecker();
		HttpSession session = request.getSession(true);
		VehicleInputFields fields = (VehicleInputFields) session.getAttribute("vehicleFields");
		if (fields == null) {
			fields = new VehicleInputFields();
		}
		Vehicle newVehicle = new Vehicle();
		boolean newOne = false;
		if (check.isValidString(request.getParameter("newOrUsed"))) {
			if (request.getParameter("newOrUsed").equalsIgnoreCase("new")) {
				newOne = true;
			}
		}
		newVehicle.setNew(newOne);
		fields.flagMake(newVehicle.setMake(request.getParameter("make")), request.getParameter("make"));
		fields.flagModel(newVehicle.setModel(request.getParameter("model")), request.getParameter("model"));
		fields.flagType(newVehicle.setType(request.getParameter("type")), request.getParameter("type"));
		fields.flagYear(newVehicle.setYear(request.getParameter("year")), request.getParameter("year"));
		fields.flagVin(newVehicle.setVin(request.getParameter("vin")), request.getParameter("vin"));
		fields.flagColor(newVehicle.setColor(request.getParameter("color")), request.getParameter("color"));
		fields.flagOdometer(newVehicle.setOdometer(request.getParameter("odometer")), request.getParameter("odometer"));
		fields.flagTagline(newVehicle.setTagline(request.getParameter("tagline")), request.getParameter("tagline"));
		fields.flagDescription(newVehicle.setDescription(request.getParameter("description")),
				request.getParameter("description"));
		fields.flagPictureLink(newVehicle.setPictureLink((String) session.getAttribute("pictureLink")),
				request.getParameter("pictureLink"));
		fields.flagDealerPurchased(newVehicle.setDealerPurchased(request.getParameter("dealerPurchased")),
				request.getParameter("dealerPurchased"));
		fields.flagDealerPurchasePrice(newVehicle.setDealerPurchasePrice(request.getParameter("dealerPurchasePrice")),
				request.getParameter("dealerPurchasePrice"));
		session.setAttribute("vehicleFields", fields);
		if (newVehicle.isValidVehicle()) {
			Inventory thisInventory = new Inventory();
			if (thisInventory.addVehicle(newVehicle)) {
				session.removeAttribute("errorAdding");
				session.setAttribute("vehicleAdded", true);
				session.setAttribute("vehicleFields", fields);
				response.sendRedirect("addVehicle.jsp");
				return;
			}
		}
		session.setAttribute("errorAdding", true);
		session.setAttribute("vehicleFields", fields);
		response.sendRedirect("addVehicle.jsp");
	}

}