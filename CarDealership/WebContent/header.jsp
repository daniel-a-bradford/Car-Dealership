<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.cardealer.entity.SignInInputFields"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<jsp:include page="/LoadSignInValidation" />
</head>
<header>
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="index.jsp"><i class="fa fa-fw fa-home"></i>Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation"
		>
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="browse.jsp"><i class="fa fa-fw fa-search"></i>Browse Vehicles<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="aboutUs.jsp"><i class="fa fa-fw fa-smile-o"></i>About Us<span class="sr-only">(current)</span>
				</a></li>
				<c:choose>
					<c:when test="${customer != null}">
					<li class="nav-item"><a class="nav-link" href="customerAccount.jsp">
						<i class="fa fa-fw fa-user-circle-o"></i>My Account<span class="sr-only">(current)</span>
						</a></li>
					</c:when>
					<c:when test="${employee != null}">
					<li class="nav-item"><a class="nav-link" href="employeeAccount.jsp">
						<i class="fa fa-fw fa-user-circle-o"></i>My Account<span class="sr-only">(current)</span>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link disabled" href="#" tabindex="-1" aria-label="Login to access My Account"
							aria-disabled="true"><i class="fa fa-fw fa-user-circle-o"></i>My Account</a></li>
					</c:otherwise>
				</c:choose>
			</ul>

			<c:choose>
				<c:when test="${customer == null && employee == null}">
					<form class="form-inline mt-2 mt-md-0" action="SignIn" method="post">
						<a href="signup.jsp" class="btn btn-outline-info rt-pad"><i class="fa fa-fw  fa-user-plus"></i>Sign Up</a> 
						<input class="form-control mr-sm-2  ${signInFields.email[0]}" name="email" type="text" 
							placeholder="${signInFields.email[1]}" value="${signInFields.email[2]}"> 
						<input class="form-control mr-sm-2 ${signInFields.password[0]}" name="password" type="password" 
							placeholder="${signInFields.password[1]}">
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit"><i class="fa fa-fw  fa-user"></i>Sign In</button>
					</form>
				</c:when>
				<c:otherwise>
					<form class="form-inline mt-2 mt-md-0" action="SignOut" method="post">
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign Out</button>
					</form>
				</c:otherwise>
			</c:choose>

		</div>
	</nav>
</header>