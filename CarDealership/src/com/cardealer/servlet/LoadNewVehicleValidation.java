package com.cardealer.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.VehicleInputFields;

/** Servlet implementation class */
@WebServlet("/LoadNewVehicleValidation")
public class LoadNewVehicleValidation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public LoadNewVehicleValidation() {
	}

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		@SuppressWarnings("unchecked")
		VehicleInputFields fields = (VehicleInputFields)session.getAttribute("vehicleFields");
		if (fields == null) {
			fields = new VehicleInputFields();
			session.setAttribute("vehicleFields", fields);
		}
		
	}

}