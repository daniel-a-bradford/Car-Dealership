<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>About Danware</title>
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
</head>
<body>
<jsp:include page="header.jsp"/>
	<main role="main" id="topOfPage">
	<div class="about-body">
		<div class="about-row">
			<h1 class="headline">Danware provides amazing web applications.</h1>
			<p>We provide solutions tailored to your needs, from our home to yours.</p>
		</div>
		<div>
			<img src="images/Danprofile.png" width="350" height="350" alt="Photograph of Founder">
		</div>
		<div>
			<p>Danware founder Dan Bradford.</p>
		</div>
		<div class="about-row">
			<div class="about-column">
				<h2 class="headline">Our Mission</h2>
				<p>We believe that trust is built through excellence. We make software solutions not only to impress and get a job, but
					solutions which can be trusted to make your life better.</p>
			</div>
		</div>
		<div class="about-row">
			<div class="about-column">
				<img src="images/Computerbuild.jpg" width="350" height="500" alt="Founder and son with homebuilt desktop computer">
				<h4>We're here to help.</h4>
				<p>When you join the Danware family, we care for you like one of our own.</p>
			</div>
		</div>
		<div class="about-row">
			<h5>Since 2020, we've written thousands of lines of code in the pursuit of something better for our family.</h5>
		</div>
	</div>
	</main>
	<jsp:include page="footer.jsp" />
	
</body>
</html>