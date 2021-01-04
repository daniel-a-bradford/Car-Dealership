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
@WebServlet("/UpdateEmpPassword")
public class UpdateEmpPassword extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public UpdateEmpPassword() {
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		EmployeeInputFields fields = new EmployeeInputFields();
		Employee currentEmployee = (Employee) session.getAttribute("employee");
		if (currentEmployee == null) {
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.forward(request, response);
			return;
		}
		// Only continue if the old password is correct, otherwise flag the old password entry, and return to employee account page.
		if (currentEmployee.isCorrectPassword(request.getParameter("oldPassword"))) {
			if (request.getParameter("password").contentEquals(request.getParameter("password2"))) {
				fields.flagNewPassword(currentEmployee.setPassword(request.getParameter("password")),
						request.getParameter("password"));
			} else {
				String[] noMatch = { fields.getErrorStyle(), "They don't match.", "" };
				fields.setEmail(noMatch);
			}

			// Attempts to add the updated employee to the employee list.
			EmployeeList updateList = new EmployeeList();

			// If successful, add the employee to the session and send them to their account page.
			if (updateList.updateEmployee(currentEmployee, currentEmployee.getEmail())) {
				session.setAttribute("employee", currentEmployee);
				EmployeeInputFields resetFields = new EmployeeInputFields();
				session.setAttribute("empFields", resetFields);
			}
		} else {
			fields.flagOldPassword(false, "");
		}
		// If the result of updating the employee false, a field or fields are invalid, return to account with invalid fields
		// highlighted.
		session.setAttribute("empFields", fields);
		response.sendRedirect("employeeAccount.jsp");
		return;
	}
}