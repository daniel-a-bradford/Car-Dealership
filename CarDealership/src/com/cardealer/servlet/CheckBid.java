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
 * Servlet implementation class
 */
@WebServlet(description = "Checks to see if the bid amount is valid and is above the min bid amount.", 
			urlPatterns = { "/CheckBid" })
public class CheckBid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		StringChecker check = new StringChecker(true);
		session.setAttribute("bidMade", true);
		Vehicle bidVehicle = (Vehicle)session.getAttribute("chosenVehicle");
		if (bidVehicle == null) {
			response.sendRedirect("browse.jsp");
			return;
		}
		if (check.isBigDecimal(request.getParameter("bid"))) {
			BigDecimal bidPrice = check.getGoodBigDecimal();
			if (bidPrice.compareTo(bidVehicle.getMinBidPrice()) > 0
				&& bidPrice.compareTo(bidVehicle.getListPrice()) < 0) {
					session.setAttribute("goodBid", true);
					String discount = "-$" + bidVehicle.getListPrice().subtract(bidPrice).toPlainString();
					session.setAttribute("priceReduction", discount);
					session.setAttribute("totalPrice", bidPrice);
				} else {
					session.setAttribute("goodBid", false);
				}
		} else {
			session.setAttribute("goodBid", false);
		}
		response.sendRedirect("checkout.jsp");
	}
}
