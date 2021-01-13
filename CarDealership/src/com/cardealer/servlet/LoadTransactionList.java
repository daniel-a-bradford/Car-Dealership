package com.cardealer.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import com.cardealer.entity.Employee;
import com.cardealer.entity.Inventory;
import com.cardealer.entity.PaymentMethod;
import com.cardealer.entity.Vehicle;

/**
 * Servlet implementation class Startup
 */
@WebServlet(description = "Initializes objects needed for the vehicle browsing page to run and search.", 
			urlPatterns = { "/LoadTransactionList" })
public class LoadTransactionList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Employee currentEmployee = (Employee)session.getAttribute("employee");
		// If there is no employee in the session, the employee is not registered or signed in, so redirect them to the home page.
		if (currentEmployee == null) {
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			request.setAttribute("error", "User not authorized.");
			rs.forward(request, response);
			return;
		}
		Inventory inventory = new Inventory();
		ArrayList<Vehicle> soldVehicleList = inventory.sortByDateSold(inventory.findSold(), false);
		ArrayList<Customer> customerSoldToList = makeMatchingCustomerList(soldVehicleList);
		request.setAttribute("soldVehicles", soldVehicleList);
		request.setAttribute("soldToCustomers", customerSoldToList);
	}
	
	/** Create a matching ArrayList of customers where each element matches the soldVehicleList.
	 * @param vehicles - the ArrayList of Vehicle to match Customer in CustomerList.
	/** @return
	 */
	private ArrayList<Customer> makeMatchingCustomerList(ArrayList<Vehicle> vehicles) {
		CustomerList customers = new CustomerList();	
		ArrayList<Customer> matchingCustomers = new ArrayList<Customer>();

		for (Vehicle tempVehicle: vehicles) {
			Customer searchCustomer = customers.findCustomer(tempVehicle.getSoldToCustomer());
			// If the customer is not found, add a placeholder named Unknown Customer
			if (searchCustomer == null) {
				Customer tempCustomer = new Customer(0);
				tempCustomer.setName("Unknown Customer");
				matchingCustomers.add(tempCustomer);
			} else {
				matchingCustomers.add(searchCustomer);
			}
		}
		return matchingCustomers;
	}
}
