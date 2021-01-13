package com.cardealer.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.CustomerInputFields;

/** Servlet implementation class UpdateServlet */
@WebServlet("/LoadNewCustomerValidation")
public class LoadNewCustomerValidation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public LoadNewCustomerValidation() {
	}
	
	// Redirects the parameters from the form in the page to the doGet which runs when the page loads. 
	// NOTE: doPost will not run if included in the jsp page (e.g. <jsp:include page="/thisServlet"/> )
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); 
	}

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		CustomerInputFields fields  = (CustomerInputFields)request.getAttribute("custFields");
		if (fields == null) {
			fields = new CustomerInputFields();
			request.setAttribute("custFields", fields);
		}
		
	}

}