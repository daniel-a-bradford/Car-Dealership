package com.cardealer.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

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
import com.cardealer.entity.Vehicle;

/**
 * Servlet implementation class Startup
 */
@WebServlet(description = "Initializes objects needed for the dealership to run", 
			urlPatterns = { "/LoadHighlights" })
public class LoadHighlights extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		Inventory thisInventory = new Inventory();
		@SuppressWarnings("unchecked")
		ArrayList<Vehicle> forSaleList = thisInventory.findForSale();
		forSaleList = thisInventory.sortByListPrice(forSaleList, false);
		@SuppressWarnings("unchecked")
		ArrayList<Vehicle> highlightList = (ArrayList<Vehicle>)session.getAttribute("highlights");
		if (highlightList == null) { 
			highlightList = new ArrayList<Vehicle>();
			if (forSaleList.size() != 0) {
				Random rand = new Random();
				int numHighlights = 3;
				int tierSize = forSaleList.size()/numHighlights;
				for (int tier = 0; tier < numHighlights; tier++) {
					int choice = (tierSize * tier) + (rand.nextInt(tierSize));
					if (choice >= forSaleList.size()) {
						choice = forSaleList.size();
					}
					System.out.println(forSaleList.size() + " total, tier size=" + tierSize + " choice " + tier + "= " + choice);
					highlightList.add(forSaleList.get(choice));
				}
			}
		}
		session.setAttribute("highlights", highlightList);
	}
}
