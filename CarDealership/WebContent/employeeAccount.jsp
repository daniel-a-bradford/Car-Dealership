<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.cardealer.entity.EmployeeInputFields"%>
<%@ page import="com.cardealer.entity.Employee"%>
<%@ page import="com.cardealer.entity.Address"%>
<%@ page import="com.cardealer.entity.PaymentMethod"%>
<%@ page import="com.cardealer.entity.Vehicle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/LoadEmployeeValidation" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<title>Cars R Us Employee Account</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous"
>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"
></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"
></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
	integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"
></script>
<!-- font awesome -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />

<!-- Bootstrap core CSS -->
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="styles/index.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" />

	<main role="main" id="topOfPage">
	<jsp:include page="employeeHeader.jsp" />
		<div class="container">
			<div class="text-center">
				<br>
				<h2>Hello ${employee.name}!</h2>

				<p class="lead">Please review or update your information below.</p>
			</div>

			<hr class="mb-4">
			<div class="row">
				<div class="col-md-4 order-md-2">
					<h4 class="mb-3">Set new password</h4>
					<form class="needs-validation" action="UpdatePassword" method="post">
						<div class="col-md-8">
							<label for="password">Current password</label> 
							<input type="password" name="oldPassword" class="form-control ${empFields.oldPassword[0]}" 
								id="password" placeholder="${empFields.oldPassword[1]}" required>
							<div class="invalid-feedback">Please a valid password.</div>
						</div>
						<div class="col-md-8">
							<label for="password">New password</label> 
							<input type="password" name="password" class="form-control ${empFields.newPassword[0]}" 
								id="password" placeholder="${empFields.newPassword[1]}" required>
							<div class="invalid-feedback">Please a valid password.</div>
						</div>
						<div class="col-md-8 pb-3">
							<label for="password2">Confirm new password</label> 
							<input type="password" name="password2"	class="form-control ${empFields.newPassword[0]}" 
								id="password2" placeholder="${empFields.newPassword[1]}" required>
							<div class="invalid-feedback">Please a valid password.</div>
						</div>
						<button class="btn btn-primary col-md-7 ml-3" type="submit">Update password</button>
					</form>
				</div>
				<div class="col-md-8 order-md-1">
					<h4 class="mb-3">Billing name and address</h4>
					<form class="needs-validation" action="EmployeeUpdate" method="post">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="firstName">First name</label> <input type="text" name="firstName"
									class="form-control ${empFields.firstName[0]}" id="firstName" 
									placeholder="${empFields.firstName[1]}" value="${employee.firstName}" required>
								<div class="invalid-feedback">Valid first name is required.</div>
							</div>
							<div class="col-md-6 mb-3">
								<label for="lastName">Last name</label> <input type="text" name="lastName" 
									class="form-control ${empFields.lastName[0]}" id="lastName" 
									placeholder="${empFields.lastName[1]}" value="${employee.lastName}" required>
								<div class="invalid-feedback">Valid last name is required.</div>
							</div>
						</div>

						<div class="mb-3">
							<label for="email">Email (serves as your sign in ID)</label> 
								<input type="email" name="email" class="form-control ${empFields.email[0]}" id="email" 
									placeholder="${empFields.email[1]}" value="${employee.email}" required>
							<div class="invalid-feedback">Please enter a valid email address.</div>
						</div>
						<hr class="mb-4">
						<button class="btn btn-primary btn-lg btn-block" type="submit">Submit updated information</button>
					</form>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="footer.jsp" />
	<script>
		window.jQuery
				|| document
						.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')
	</script>
	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
</html>
