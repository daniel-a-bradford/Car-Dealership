<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.cardealer.entity.Vehicle"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<title>Browse Our Vehicles</title>

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
<link rel="stylesheet" href="styles/index.css">

<style>
   .bd-placeholder-img {
     font-size: 1.125rem;
     text-anchor: middle;
     -webkit-user-select: none;
     -moz-user-select: none;
     -ms-user-select: none;
     user-select: none;
   }

   @media (min-width: 768px) {
     .bd-placeholder-img-lg {
       font-size: 3.5rem;
     }
   }
</style>
<!-- Custom styles for this template -->
<link href="album.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="header.jsp" />
	<main role="main" id="topOfPage">
	
		<div class="container">
		<c:set var="decimalFormat" scope="session" value='${DecimalFormat("###,###,###.00")}'/>
		<c:set var="integerFormat" scope="session" value='${DecimalFormat("###,###,###")}'/>
			<div class="pt-5 text-center dark-blue">
				<br>
				<h1 class="text-gold">Congratulations on your ${soldVehicle.year} ${soldVehicle.make} ${soldVehicle.model}!</h1>
				<p class="lead text-gold">One of our associates will call you soon to arrange pickup or delivery.</p> 
				<p class="lead text-gold">Can't wait to drive it? Please call us at (555)867-5309.</p>
			</div>
				<div class="row dark-blue text-gold">
					<img src="${soldVehicle.pictureLink}" alt="${soldVehicle.tagline}" width="100%" height="95%">
					<h5 class="centered">${vehicle.vin} This <c:choose>
						<c:when test="${soldVehicle.newVehicle}">new vehicle comes with a full manufacturer's warranty.</c:when> 
						<c:otherwise>pre-owned with ${integerFormat.format(soldVehicle.odometer)} miles.</c:otherwise>
						</c:choose></h5>
					<h4 class="centered">We're happy you bought it for only $${decimalFormat.format(soldVehicle.soldPrice)},  
						    compared to the open market price of $${decimalFormat.format(soldVehicle.recommendedPrice)}!</h4>

				</div>
				<div class="row">
					<span>
						<a href="customerAccount.jsp" class="btn btn-success text-centered"><i class="fa fa-fw  fa-user-circle-o"></i>Go to My Account</a>
						<a href="browse.jsp" class="btn btn-primary text-centered"><i class="fa fa-fw fa-search"></i>Go to Browse</a>
					</span>
				</div>

			</div>

	<jsp:include page="footer.jsp"/>

</html>
