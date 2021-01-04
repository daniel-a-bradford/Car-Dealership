<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.cardealer.entity.CustomerInputFields"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/LoadNewCustomerValidation" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
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
<link href="styles/index.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>

	<main role="main" id="topOfPage">

		<div class="container">
			<div class="py-5 text-center">
				<h2>Sign Up</h2>
				<p class="lead">By becoming part of the Cars R Us family, you are eligible for special offers and discounts, have access
					your purchase history, as well as having your payment information securely stored for your convenience.</p>
			</div>
			<div class="row">
				<div class="col-md-4 order-md-2">
					<h4 class="mb-3">Welcome to the Cars R Us family!</h4>
					<img src="images/Handshake.jpg" alt="Welcome to our family" preserveAspectRatio="xMidYMid slice" focusable="false">
				</div>
				<div class="col-md-8 order-md-1">
					<h4 class="mb-3">Billing name and address</h4>
					<form class="needs-validation" action="Registration" method="post">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="firstName">First name</label> <input type="text" name="firstName" class="form-control ${custFields.firstName[0]}" id="firstName"
									placeholder="${custFields.firstName[1]}" value="${custFields.firstName[2]}" required
								>
								<div class="invalid-feedback">Valid first name is required.</div>
							</div>
							<div class="col-md-6 mb-3">
								<label for="lastName">Last name</label> <input type="text" name="lastName" class="form-control ${custFields.lastName[0]}" id="lastName"
									placeholder="${custFields.lastName[1]}" value="${custFields.lastName[2]}" required
								>
								<div class="invalid-feedback">Valid last name is required.</div>
							</div>
						</div>

						<div class="mb-3">
							<label for="email">Email (serves as your sign in ID)</label> <input type="email" name="email" class="form-control ${custFields.email[0]}"
								id="email" placeholder="${custFields.email[1]}" value="${custFields.email[2]}" required>
							<div class="invalid-feedback">Please enter a valid email address.</div>
						</div>

						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="password">Password</label> <input type="password" name="password" class="form-control ${custFields.newPassword[0]}" id="password"
								placeholder="${custFields.newPassword[1]}" value="${custFields.newPassword[2]}" required>
							<div class="invalid-feedback">Please a valid password.</div>
							</div>
							<div class="col-md-6 mb-3">
								<label for="phoneNum">Phone Number</label> <input type="text" name="phone" class="form-control ${custFields.phone[0]}" id="phoneNum"
									placeholder="${custFields.phone[1]}" value="${custFields.phone[2]}" required>
								<div class="invalid-feedback">Valid phone number is required.</div>
							</div>
						</div>
						<div class="mb-3">
							<label for="address">Street Number and Name</label> <input type="text" name="street1" class="form-control ${custFields.street1[0]}" id="address"
								placeholder="${custFields.street1[1]}" value="${custFields.street1[2]}" required>
							<div class="invalid-feedback">Please enter your billing address.</div>
						</div>

						<div class="mb-3">
							<label for="street2">Additional Address<span class="text-muted"> (Optional)</span></label> <input type="text" name="street2"
								class="form-control ${custFields.street2[0]}" id="street2" placeholder="${custFields.street2[1]}" value="${custFields.street2[2]}">
						</div>

						<div class="row">
						<div class="col-md-4 mb-3">
							<label for="city">City</label> <input type="text" name="city" class="form-control ${custFields.city[0]}" id="city"
								placeholder="${custFields.city[1]}" value="${custFields.city[2]}" required>
							<div class="invalid-feedback">Please enter your city.</div>
						</div>
							<div class="col-md-4 mb-3">
								<label for="state">State</label> <select class="custom-select d-block w-100 ${custFields.state[0]}" name=state id="state" value="${custFields.state[2]}" required>
									<option value="${custFields.state[2]}"><c:out value="${custFields.state[2]}"/></option>
									<option value="AK">Alaska</option>
									<option value="AL">Alabama</option>
									<option value="AR">Arkansas</option>
									<option value="AZ">Arizona</option>
									<option value="CA">California</option>
									<option value="CO">Colorado</option>
									<option value="CT">Connecticut</option>
									<option value="DC">District of Columbia</option>
									<option value="DE">Delaware</option>
									<option value="FL">Florida</option>
									<option value="GA">Georgia</option>
									<option value="HI">Hawaii</option>
									<option value="IA">Iowa</option>
									<option value="ID">Idaho</option>
									<option value="IL">Illinois</option>
									<option value="IN">Indiana</option>
									<option value="KS">Kansas</option>
									<option value="KY">Kentucky</option>
									<option value="LA">Louisiana</option>
									<option value="MA">Massachusetts</option>
									<option value="MD">Maryland</option>
									<option value="ME">Maine</option>
									<option value="MI">Michigan</option>
									<option value="MN">Minnesota</option>
									<option value="MO">Missouri</option>
									<option value="MS">Mississippi</option>
									<option value="MT">Montana</option>
									<option value="NC">North Carolina</option>
									<option value="ND">North Dakota</option>
									<option value="NE">Nebraska</option>
									<option value="NH">New Hampshire</option>
									<option value="NJ">New Jersey</option>
									<option value="NM">New Mexico</option>
									<option value="NV">Nevada</option>
									<option value="NY">New York</option>
									<option value="OH">Ohio</option>
									<option value="OK">Oklahoma</option>
									<option value="OR">Oregon</option>
									<option value="PA">Pennsylvania</option>
									<option value="PR">Puerto Rico</option>
									<option value="RI">Rhode Island</option>
									<option value="SC">South Carolina</option>
									<option value="SD">South Dakota</option>
									<option value="TN">Tennessee</option>
									<option value="TX">Texas</option>
									<option value="UT">Utah</option>
									<option value="VA">Virginia</option>
									<option value="VT">Vermont</option>
									<option value="WA">Washington</option>
									<option value="WI">Wisconsin</option>
									<option value="WV">West Virginia</option>
									<option value="WY">Wyoming</option>
									<option value="AS">American Samoa</option>
									<option value="FM">Micronesia</option>
									<option value="GU">Guam</option>
									<option value="MH">Marshall Islands</option>
									<option value="MP">Northern Mariana Islands</option>
									<option value="PW">Palau</option>
									<option value="VI">U.S. Virgin Islands</option>
								</select>
								<div class="invalid-feedback">Please provide a valid state.</div>
							</div>
							<div class="col-md-3 mb-3">
								<label for="zip">Zip</label> <input type="text" class="form-control ${custFields.zip[0]}" id="zip" name="zip" placeholder="${custFields.zip[1]}" maxlength="5"
									value="${custFields.zip[2]}" required>
								<div class="invalid-feedback">Zip code required.</div>
							</div>
						</div>
						<hr class="mb-4">

						<h4 class="mb-3">Payment for future purchases. Will be required at checkout.</h4>

						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="cc-name">Name on card<span class="text-muted"> (Optional)</span></label> <input type="text" name="cardName"
									class="form-control ${custFields.cardName[0]}" id="cc-name" placeholder="${custFields.cardName[1]}" value="${custFields.cardName[2]}"
								> <small class="text-muted">Full name as displayed on card</small>
								<div class="invalid-feedback">Name on card is required</div>
							</div>
							<div class="col-md-6 mb-3">
								<label for="cc-number">Credit card number<span class="text-muted"> (Optional)</span></label> <input type="text"
									name="cardNum" class="form-control ${custFields.cardNum[0]}" id="cc-number" placeholder="${custFields.cardNum[1]}" maxlength="19"
									value="${custFields.cardNum[2]}">
								<div class="invalid-feedback">Credit card number is required</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="cc-expiration">Expiration<span class="text-muted">(Opt.)</span></label> <input type="text" name="expiration"
									class="form-control ${custFields.expiration[0]}" id="cc-expiration" placeholder="${custFields.expiration[1]}"
									value="${custFields.expiration[2]}">
								<div class="invalid-feedback">Expiration date required</div>
							</div>
							<div class="col-md-3 mb-3">
								<label for="cc-cvv">CVV<span class="text-muted"> (Optional)</span></label> <input type="password" name="cvv"
									class="form-control ${custFields.cvv[0]}" id="cc-cvv" placeholder="${custFields.cvv[1]}" maxlength="3"
									value="${custFields.cvv[2]}">
								<div class="invalid-feedback">Security code required</div>
							</div>
						</div>
						<div class="mb-3">
							<label for="email">PayPal email<span class="text-muted"> (Optional)</span></label> <input type="email" name="paypalEmail"
								class="form-control ${custFields.paypalEmail[0]}" id="email" placeholder="${custFields.paypalEmail[1]}"
								value="${custFields.paypalEmail[2]}">
							<div class="invalid-feedback">Please enter a valid email address associated with your PayPal account.</div>
						</div>
						<hr class="mb-4">
						<button class="btn btn-primary btn-lg btn-block" type="submit">Submit sign in information</button>
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
