package com.cardealer.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.SignInInputFields;

/** Servlet implementation class */
@WebServlet("/LoadSignInValidation")
public class LoadSignInValidation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public LoadSignInValidation() {
	}
	
	// Redirects the parameters from the form in the page to the doGet which runs when the page loads. 
	// NOTE: doPost will not run if included in the jsp page (e.g. <jsp:include page="/thisServlet"/> )
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); 
	}

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		@SuppressWarnings("unchecked")
		SignInInputFields fields  = (SignInInputFields)session.getAttribute("signInFields");
		if (fields == null) {
			fields = new SignInInputFields();
			session.setAttribute("signInFields", fields);
		}
		
	}

}