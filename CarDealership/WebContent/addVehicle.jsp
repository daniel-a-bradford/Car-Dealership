<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.cardealer.entity.VehicleInputFields"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/LoadNewVehicleValidation" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<title>Add a vehicle</title>

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
<jsp:include page="header.jsp"/>

	<main role="main" id="topOfPage">
	<jsp:include page="employeeHeader.jsp" />

		<div class="container">
			<div class="pt-5 text-center">
				<c:choose>
				<c:when test="${errorAdding}">
					<h2 class="input-error">Vehicle not added</h2>
					<p class="lead input-error">Check and update the highlighted fields below.</p>
				</c:when>
				<c:when test="${vehicleAdded}">
					<h2 class="valid-input">Vehicle Added Successfully!</h2>
					<p class="lead valid-input">Put another vehicle on the virtual lot using the following fields, if you wish.</p>
				</c:when>
				<c:otherwise>
					<h2>Add a vehicle</h2>
					<p class="lead">Put a new vehicle on the virtual lot using the following fields.</p>
				</c:otherwise>
				</c:choose>
			</div>
			<hr class="mb-4">
			<h4 class="mb-3 centered">1. Start with a picture. To appear correctly crop the picture to a 5x7 (height x width).</h4>
			<div class="row">
				
				<div class="col-md-6 mb-3">
					<form action="ImageUpload" method="post" enctype="multipart/form-data">
						
						Please select the image file to upload: <input type="file" name="file" size="60" /><br> <br> 
						<button class="btn btn-info ${vehicleFields.pictureLink[0]}" type="submit">Upload selected file</button>
						<small class="text-muted">The only acceptable file types are jpg, jpeg, png, and gif</small>
						<p class="${vehicleFields.pictureLink[0]}">${vehicleFields.pictureLink[1]}${vehicleFields.pictureLink[2]}</p>
					</form>
				</div>
				<div class="col-md-6 mb-3">
					<img src="${pictureLink}" width="300" height="215">
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h4 class="mb-3 centered">2. Enter the vehicle information below. NOTE: If you upload another picture, these values will be erased.</h4>
					<form class="needs-validation" action="AddVehicle" method="post">
						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="vin">Vehicle VIN</label> <input type="text" name="vin" 
									class="form-control ${vehicleFields.vin[0]}" id="vin"
									placeholder="${vehicleFields.vin[1]}" value="${vehicleFields.vin[2]}" required
								>
								<div class="invalid-feedback">Please enter a valid VIN.</div>
							</div>
							<div class="col-md-2 mb-3">
								<label for="year">Year Made</label> <input type="text" name="year" 
									class="form-control ${vehicleFields.year[0]}" id="year"
									placeholder="${vehicleFields.year[1]}" value="${vehicleFields.year[2]}" required
								>
								<div class="invalid-feedback">Please enter a valid year made.</div>
							</div>
							<div class="col-md-2 mb-3">
								<label for="make">Make</label> <input type="text" name="make" 
									class="form-control ${vehicleFields.make[0]}" id="make" placeholder="${vehicleFields.make[1]}" 
									value="${vehicleFields.make[2]}" required>
								<div class="invalid-feedback">Please enter a valid make.</div>
							</div>
							<div class="col-md-2 mb-3">
								<label for="model">Model</label> <input type="text" name="model" 
								class="form-control ${vehicleFields.model[0]}" id="model"
								placeholder="${vehicleFields.model[1]}" value="${vehicleFields.model[2]}" required>
							<div class="invalid-feedback">Please a valid model.</div>
							</div>
							<div class="col-md-3 mb-3">
								<label for="odometer">Odometer</label> <input type="text" name="odometer" 
								class="form-control ${vehicleFields.odometer[0]}" id="odometer"
								placeholder="${vehicleFields.odometer[1]}" value="${vehicleFields.odometer[2]}" required>
							<div class="invalid-feedback">Please a valid odometer reading.</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-1 mb-2">
							  	<input type="radio" id="new" name="newOrUsed" value="new">
							  	<label for="new">New</label><br>
							  	<input type="radio" id="used" name="newOrUsed" value="used">
							  	<label for="used">Used</label><br>
		  					</div>
							<div class="col-md-2 mb-3">
								<label for="type">Vehicle Type</label> <input type="text" name="type" 
								class="form-control ${vehicleFields.type[0]}" id="type"
									placeholder="${vehicleFields.type[1]}" value="${vehicleFields.type[2]}" required>
								<div class="invalid-feedback">Please enter a valid vehicle type.</div>
							</div>
							<div class="col-md-2 mb-3">
								<label for="color">Color</label> <input type="text" name="color" 
								class="form-control ${vehicleFields.color[0]}" id="color"
									placeholder="${vehicleFields.color[1]}" value="${vehicleFields.color[2]}" required>
								<div class="invalid-feedback">Please enter a valid color.</div>
							</div>
							<div class="col-md-2 mb-3">
								<label for="price">Dealer Purchase Price</label> <input type="text" name="dealerPurchasePrice" 
									class="form-control ${vehicleFields.dealerPurchasePrice[0]}" id="price"
									placeholder="${vehicleFields.dealerPurchasePrice[1]}" value="${vehicleFields.dealerPurchasePrice[2]}" required>
								<div class="invalid-feedback">Please enter the price the dealer paid for the vehicle.</div>
							</div>
							<div class="col-md-3 mb-3">
								<label for="dealerPurchased">Date Dealer Purchased</label> <input type="datetime-local" 
									class="form-control ${vehicleFields.dealerPurchased[0]}" id="dealerPurchased" 
									name="dealerPurchased" placeholder="${vehicleFields.dealerPurchased[1]}" maxlength="10"
									value="${vehicleFields.dealerPurchased[2]}" required>
								<div class="invalid-feedback">Please enter a valid date.</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 mb-3">
								<label for="tagline">Short Tagline to Draw Attention</label> 
								<input type="text" name="tagline" class="form-control ${vehicleFields.tagline[0]}" id="tagline" 
									placeholder="${vehicleFields.tagline[1]}" value="${vehicleFields.tagline[2]}" required>
									<div class="invalid-feedback">Please enter a valid tagline.</div>
							</div>
							<div class="col-md-12 mb-3">
								<label for="description">Description of the Vehicle (features, etc.)</label> <input type="text" name="description" class="form-control ${vehicleFields.description[0]}" id="description"
									placeholder="${vehicleFields.description[1]}" value="${vehicleFields.description[2]}" required>
								<div class="invalid-feedback">Please enter a valid description.</div>
							</div>
						</div>
						<hr class="mb-4">
						<div class="row">
							<div class="col-md-12 centered">
								<button class="btn btn-primary btn-lg" type="submit">Submit new vehicle information</button>
								<button class="btn btn-warning btn-lg" type="submit" formaction="ResetVehicleValidation" 
									formmethod="post" >Reset fields</button>
						<p>Click "Reset search" to clear field highlights and messages.</p>
							</div>
						</div>
						<hr class="mb-4">
					</form>
				</div>
			</div>
		</div>
	</main>
	<script>
		window.jQuery
				|| document
						.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')
	</script>
	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
</html>
