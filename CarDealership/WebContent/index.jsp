<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.cardealer.entity.Vehicle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/LoadHighlights" />
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Welcome page carousel and highlights">
<title>Welcome to Cars R Us</title>

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

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="styles/carousel.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>

	<main role="main" id="topOfPage">
		<h3 class="input-error">${error}</h3>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="images/fastcar.jpg" class="d-block w-100" alt="Going fast">
					<div class="carousel-caption text-left">
						<h1>The Cars R Us Advantage</h1>
						<p class=back-shade>If are in the market for a new or used car, then you have come to the right place. Because if there's one thing we
							love more than our lineup of new and used cars, it's serving customers like you here at Cars R Us.</p>
						<p>
							<a class="btn btn-lg btn-primary" href="signup.jsp" role="button">Sign up today</a>
						</p>
					</div>

				</div>
				<div class="carousel-item">
					<img src="images/manycars.jpg" class="d-block w-100" alt="Large selection">
					<div class="carousel-caption">
						<h1>Amazing Selection</h1>
						<p class=back-shade>You have got your pick of the litter right here online. Whether you're window shopping or looking for a test drive,
							you can rest assured that you won't be disappointed here at Cars R Us.</p>
						<p>
							<a class="btn btn-lg btn-primary" href="browse.jsp" role="button">Learn more</a>
						</p>
					</div>

				</div>
				<div class="carousel-item">
					<img src="images/keys.jpg" class="d-block w-100" alt="Got your back">
					<div class="carousel-caption text-right">
						<h1>We're Here For You</h1>
						<p class=back-shade>If there are any questions you have that needs answering, the automotive experts on our staff are here to cater to
							your every automotive need.</p>
						<p>
							<a class="btn btn-lg btn-primary" href="browse.jsp" role="button">Browse gallery</a>
						</p>
					</div>

				</div>
			</div>
			<a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"
			></span> <span class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"
			></span> <span class="sr-only">Next</span>
			</a>
		</div>

		<c:forEach var="vehicle" items="${highlights}" varStatus="index">
		<div class="container marketing">
			<hr class="mb-4">
				<div class="row featurette">
					<c:choose>
						<c:when test="${(index.count) % 2 == 0}">
							<div class="col-md order-md-first">
						</c:when>
						<c:otherwise>
							<div class="col-md order-md-last">
						</c:otherwise>
					</c:choose>
					<h2 class="featurette-heading my-1">
						${vehicle.tagline} <br> <span class="text-muted"> ${vehicle.year} ${vehicle.make} ${vehicle.model} </span>
					</h2>
					<p class="lead">${vehicle.description}</p>
				</div>
				<div class="col-md">
					<a href="GoToHighlight?vin=${vehicle.vin}">
					<img src="${vehicle.pictureLink}" alt="${vehicle.tagline}" width="500" height="360">
					</a>
				</div>
				
		</div>
		</c:forEach>


		<hr class="mb-4">

	<jsp:include page="footer.jsp"/>
	</main>
	<script>
		window.jQuery
				|| document
						.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')
	</script>
	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
</html>
