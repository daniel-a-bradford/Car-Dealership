package com.cardealer.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

/**
 * Servlet implementation class Startup
 */
@WebServlet(description = "Removes the searchResults session attribute and redirects back to the browse page, where they are loaded fresh.", 
			urlPatterns = { "/ResetSearch" })
public class ResetSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.removeAttribute("searchFields");
		session.removeAttribute("searchResults");
		response.sendRedirect("browse.jsp");
	}
}
