<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.cardealer.entity.EmployeeInputFields"%>
<%@ page import="com.cardealer.entity.Customer"%>
<%@ page import="com.cardealer.entity.Address"%>
<%@ page import="com.cardealer.entity.PaymentMethod"%>
<%@ page import="com.cardealer.entity.Vehicle"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/LoadTransactionList" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<title>Transaction List</title>

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
	<c:set var="decimalFormat" scope="session" value='${DecimalFormat("###,###,###.00")}'/>
	<c:set var="integerFormat" scope="session" value='${DecimalFormat("###,###,###")}'/>
		<div>
			<div class="text-center">
				<br>
				<h2>Transaction List</h2>

				<p class="lead">${soldVehicles.size()} vehicle transactions listed starting with most recent.</p>
			</div>
			<hr class="mb-4">
			<c:choose>
			<c:when test="${soldVehicles.size() > 0}">
				<div class="table-responsive">
					<table class="table table-striped table-dark table-bordered">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Bought On</th>
								<th scope="col">VIN</th>
								<th scope="col">Year Make Model</th>
								<th scope="col">Color / Odometer</th>
								<th scope="col">Sale / Dealer Price</th>
								<th scope="col">Customer Name</th>
								<th scope="col">Email / Phone</th>
								<th scope="col">Address</th>
								<th scope="col">Card Number / Paypal Email</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="vehicle" items="${soldVehicles}" varStatus="loop">
								<tr>
									<th scope="row">${loop.count}</th>
									<td>${vehicle.dateSold}</td>
									<td>${vehicle.vin}</td>
									<td>${vehicle.year} ${vehicle.make} ${vehicle.model}</td>
									<td>${vehicle.color} / ${integerFormat.format(vehicle.odometer)}</td>
									<td>$${decimalFormat.format(vehicle.soldPrice)} / $${decimalFormat.format(vehicle.dealerPurchasePrice)}</td>
									<td>${soldToCustomers[loop.index].name}</td>
									<td>${soldToCustomers[loop.index].email} / ${soldToCustomers[loop.index].phone}</td>
									<td>${soldToCustomers[loop.index].address.street1} 
										${soldToCustomers[loop.index].address.street2}
										${soldToCustomers[loop.index].address.city}, ${soldToCustomers[loop.index].address.state}
										${soldToCustomers[loop.index].address.zip}-${soldToCustomers[loop.index].address.zipPlus4}</td>
									<td>${soldToCustomers[loop.index].pay.cardNum} / ${soldToCustomers[loop.index].pay.paypalEmail}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<p class="text-center">No vehicles sold. Get out there and spread the word!</p>
			</c:otherwise>
			</c:choose>

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
