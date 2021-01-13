package com.cardealer.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardealer.entity.Employee;
import com.cardealer.entity.EmployeeInputFields;
import com.cardealer.entity.EmployeeList;

/** Servlet implementation class UpdateServlet */
@WebServlet("/EmployeeUpdate")
public class EmployeeUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public EmployeeUpdate() {
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		EmployeeInputFields fields = new EmployeeInputFields();
		Employee sessionEmployee = (Employee)session.getAttribute("employee");
		if (sessionEmployee == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		fields.flagFirstName(sessionEmployee.setFirstName(request.getParameter("firstName")), sessionEmployee.getFirstName());
		fields.flagLastName(sessionEmployee.setLastName(request.getParameter("lastName")), sessionEmployee.getLastName());
		String oldEmail = sessionEmployee.getEmail();
		fields.flagEmail(sessionEmployee.setEmail(request.getParameter("email")), sessionEmployee.getEmail());
		// Attempts to add the updated employee to the employee list. 
		EmployeeList updateList = new EmployeeList();
		if (sessionEmployee.isValidEmployee()) {
			Employee existingEmployee = updateList.findEmployee(sessionEmployee.getEmail());
			if (existingEmployee != null) {
				// If the e-mail exists in the list and it does not belong to the employee with this employeeID, .
				if (updateList.findByEmail(sessionEmployee.getEmail()).size() != 0
					&& !existingEmployee.getEmail().equalsIgnoreCase(sessionEmployee.getEmail())) {
					String[] duplicateEmail = { fields.getErrorStyle(), "Email already in use by another employee.", "" };
					fields.setEmail(duplicateEmail);
				}
			}
		}
		// If the update is successful, add the updated employee to the session and send them to their account page.
		if (updateList.updateEmployee(sessionEmployee, oldEmail)) {
			session.setAttribute("employee", sessionEmployee);
			fields.flagUpdateStatus(true);
			request.setAttribute("empFields", fields );
		} else {
			// If the result of updating the employee false, a field or fields are invalid, return to account with invalid fields highlighted.
			fields.flagUpdateStatus(false);
			request.setAttribute("empFields", fields );
		}
		RequestDispatcher rd = request.getRequestDispatcher("employeeAccount.jsp");
		rd.forward(request, response);
	}
}