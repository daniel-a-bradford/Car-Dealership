<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.cardealer.entity.Vehicle"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/LoadVehicleBrowse" />
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
			<div class="pt-5 text-center">
				<br>
				<h2>Welcome to our searchable inventory of quality vehicles</h2>
				<p class="lead">To find your perfect vehicle, please enter any and all search criteria and click "Find your vehicle!"</p> 
				<p class="lead">Or feel free to just browse our full inventory below.</p>
			</div>
			<hr class="mb-4">
			<form action="SearchVehicles" method="post">
				<c:if test="${searchFields.vin[2].isBlank() || searchFields.vin[2] == null}">
				<div class="form-row">
					<div class="col-md-2 mb-2">
					  	<input type="checkbox" id="new" name="new" value="new" 
							<c:if test="${searchFields.newVehicle[1].equals(searchFields.checked)}"> checked="checked" </c:if>>
					  	<label for="new">New</label><br>
					  	<input type="checkbox" id="used" name="used" value="used" 
					  		<c:if test="${searchFields.usedVehicle[1].equals(searchFields.checked)}"> checked="checked" </c:if>>
					  	<label for="used">Pre-owned</label><br>
  					</div>
  					<div class="col-md-2 mb-2">
						<label for="lowPrice">Price range (lowest)</label> 
						<span>$</span><input type="text" class="form-control ${searchFields.lowPrice[0]}" name="lowPrice" 
							id="lowPrice" placeholder="${searchFields.lowPrice[1]}" value="${searchFields.lowPrice[2]}" 
							maxlength="10" size="10" >
					</div>
					<div class="col-md-2 mb-2">
						<label for="highPrice">Price range (highest)</label> 
						<span>$</span><input type="text" class="form-control ${searchFields.highPrice[0]}" name="highPrice" 
						id="highPrice" placeholder="${searchFields.highPrice[1]}" value="${searchFields.highPrice[2]}" 
						maxlength="10" size="10" >
					</div>
					<div class="col-md-2 mb-2">
						<label for="type"> Choose a vehicle type</label>
						<div class="col-sm-12 pl-0">
							<select class="form-control ${searchFields.type[0]}" name="type" id="type">
								<option selected value="${searchFields.type[2]}"><c:out value="${searchFields.type[2]}"/></option>
								<c:forEach var="type" items="${typeList}">
									<option value="${type}"><c:out value="${type}"/></option>
								</c:forEach>
								<option value="">Clear selected</option>
							</select>
						</div>
					</div>
					<div class="col-md-2 mb-2">
						<label for="make">  Choose a make</label>
						<div class="col-sm-12 pl-0">
							<select class="form-control ${searchFields.make[0]}" name="make" id="make">
								<option selected value="${searchFields.make[2]}"><c:out value="${searchFields.make[2]}"/></option>
								<c:forEach var="make" items="${makeList}">
									<option value="${make}"><c:out value="${make}"/></option>
								</c:forEach>
								<option value="">Clear selected</option>
							</select>
						</div>
					</div>
					<div class="col-md-2 mb-2">
						<label for="model">  Choose a model</label>
						<div class="col-sm-12 pl-0">
							<select class="form-control ${searchFields.model[0]}" name="model" id="model">
								<option selected value="${searchFields.model[2]}"><c:out value="${searchFields.model[2]}"/></option>
								<c:forEach var="model" items="${modelList}">
									<option value="${model}"><c:out value="${model}"/></option>
								</c:forEach>
								<option value="">Clear selected</option>
							</select>
						</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-2 mb-2 pl-0">
						<label for="color">  Choose a color</label>
						<div class="col-sm-12 pl-0">
							<select class="form-control ${searchFields.color[0]}" name="color" id="color">
								<option selected value="${searchFields.color[2]}"><c:out value="${searchFields.color[2]}"/></option>
								<c:forEach var="color" items="${colorList}">
									<option value="${color}"><c:out value="${color}"/></option>
								</c:forEach>
								<option value="">Clear selected</option>
							</select>
						</div>
					</div>
					<div class="col-md-2 mb-2">
						<label for="year">  Choose a year</label>
						<div class="col-sm-12 pl-0">
							<select class="form-control ${searchFields.year[0]}" name="year" id="year">
								<option selected value="${searchFields.year[2]}"><c:out value="${searchFields.year[2]}"/></option>
								<c:forEach var="year" items="${yearList}">
									<option value="${year}"><c:out value="${year}"/></option>
								</c:forEach>
								<option value="">Clear selected</option>
							</select>
						</div>
					</div>
					<div class="col-md-2 mb-2">
						<label for="odometer">  Choose miles</label>
						<div class="col-sm-12 pl-0">
								<select class="form-control ${searchFields.odometerRange[0]}" name="odometerRange" id="odometer">
								<option selected value="${searchFields.odometerRange[2]}"><c:out value="${searchFields.odometerRange[2]}"/></option>
								<c:forEach var="odometerRange" items="${odometerRangeList}">
									<option value="${odometerRange}"><c:out value="${odometerRange}"/></option>
								</c:forEach>
								<option value="">Clear selected</option>
							</select>
						</div>
					</div>
					<div class="col-md-3 mb-2">
						<label for="tagline">Part of the tagline</label> 
						<input type="text" class="form-control ${searchFields.tagline[0]}" name="tagline" id="tagline" 
						placeholder="${searchFields.tagline[1]}" value="${searchFields.tagline[2]}">
					</div>
					<div class="col-md-3 mb-2">
						<label for="description">Part of the description or a feature</label> 
						<input type="text" class="form-control ${searchFields.description[0]}" name="description" id="description" 
						placeholder="${searchFields.description[1]}" value="${searchFields.description[2]}">
					</div>
				</div>
				</c:if>
				<div class="form-row">
					<div class="col-md-12 text-center">
						<c:if test="${searchFields.vin[2].isBlank() || searchFields.vin[2] == null}">
						<button class="btn btn-primary" type="submit">Find your vehicle!</button>
						</c:if>
						<button class="btn btn-warning ml-5" type="submit" formaction="ResetSearch" formmethod="post" >Reset search</button>
						<p>Want to see more? Click "Reset search" to start with the full inventory again.</p>
					</div>
				</div>
			</form>
		</div>
		<div class="album py-5 bg-light">
			<div class="container">
			<c:set var="decimalFormat" scope="session" value='${DecimalFormat("###,###,###.00")}'/>
			<c:set var="integerFormat" scope="session" value='${DecimalFormat("###,###,###")}'/>
				<div class="row">
					<c:forEach var="vehicle" items="${searchResults}">
						<div class="col-md-4">
							<div class="card mb-4 shadow-sm">
								<img src="${vehicle.pictureLink}" alt="${vehicle.tagline}" width="100%" height="225">
									
								<div class="card-body">
									<p class="card-text">
											<c:choose>
											<c:when test="${vehicle.newVehicle}">New </c:when> 
											<c:otherwise>Pre-owned </c:otherwise>
											</c:choose>
											${vehicle.year} ${vehicle.make} ${vehicle.model} </p>
									<p class="card-text">${vehicle.tagline} with only ${integerFormat.format(vehicle.odometer)} miles.</p>
									<div class="d-flex justify-content-between align-items-center text-centered">
										
										<div class="btn-group">
											<form action="SetChosenVehicle" method="post">
											<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" 
												data-target="#vehicleDetails${vehicle.vin}">Details</button>
											<button class="btn btn-sm btn-success" type="submit" name="chosenVIN" value="${vehicle.vin}">Buy Now!</button>
											<%-- <button type="button" class="btn btn-sm btn-outline-secondary" type="submit" name="chosenVIN"
												value="${vehicle.vin}">Buy Now</button> --%>
											</form>
										</div>
										
										<span>
											<small class="text-muted">Was $${decimalFormat.format(vehicle.recommendedPrice)}</small>
											<p>Now only $${decimalFormat.format(vehicle.listPrice)}</p>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade" id="vehicleDetails${vehicle.vin}">
							<div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
								<div class="modal-content">
									<div class="modal-header">
										<h2 class="modal-title">${vehicle.year} ${vehicle.make} ${vehicle.model}</h2>
										<h4 class="modal-title">${vehicle.tagline}</h4>
									</div>
									<div class="modal-body">
										<img src="${vehicle.pictureLink}" alt="${vehicle.tagline}" width="100%" height="95%">
										<h5 class="centered">${vehicle.vin} This <c:choose>
											<c:when test="${vehicle.newVehicle}">new vehicle comes with a full manufacturer's warranty.</c:when> 
											<c:otherwise>pre-owned with ${integerFormat.format(vehicle.odometer)} miles.</c:otherwise>
											</c:choose></h5>
										<h4 class="centered">Regular market value: $${decimalFormat.format(vehicle.recommendedPrice)}  
											    Cars R Us price: only $${decimalFormat.format(vehicle.listPrice)}!</h4>
										<c:if test="${vehicle.biddable}">
										<h4 class="text-gold back-green centered">And if you act now, you can bid an even lower price at checkout!</h4>
										</c:if>
										<h6>${vehicle.description}</h6>
									</div>
									<div class="modal-footer">
										<span>
											<form action="SetChosenVehicle" method="post">
												<button class="btn btn-success ml-5" type="submit" name="chosenVIN" value="${vehicle.vin}">Buy Now!</button>
												<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
											</form>
										</span>
									</div>

								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="footer.jsp"/>

</html>
