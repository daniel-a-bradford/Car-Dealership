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
@WebServlet("/ResetVehicleValidation")
public class ResetVehicleValidation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public ResetVehicleValidation() {
	}

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		request.removeAttribute("vehicleAdded");
		request.removeAttribute("errorAdding");
		request.setAttribute("vehicleFields", new VehicleInputFields());
		
	}

}