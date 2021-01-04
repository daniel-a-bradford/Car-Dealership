<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.cardealer.entity.CustomerInputFields"%>
<%@ page import="com.cardealer.entity.Customer"%>
<%@ page import="com.cardealer.entity.Address"%>
<%@ page import="com.cardealer.entity.PaymentMethod"%>
<%@ page import="com.cardealer.entity.Vehicle"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/LoadCheckout" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<title>Buy your dream vehicle</title>

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

		<div class="container">
			<c:choose>
			<c:when test="${existingCustomer}">
			<div class="pt-5 text-center">
				<br>
				<h2>Hello ${customer.name}!</h2>

				<p class="lead">Thank you for being such a loyal customer! Your information is pre-filled in for your convenience.</p>
			</div>
			</c:when>
			<c:otherwise>
				<div class="pt-5 text-center">
				<br>
				<h2>Thank you for shopping with us!</h2>

				<p class="lead">If you are an existing customer, please sign in on the navigation bar above. <i class="fa fa-fw fa-arrow-up"></i></p>
				<p class="lead">Otherwise, please fill out the following information and we'll sign you up with Cars R Us while you check out!.</p>
			</div>
			</c:otherwise>
			</c:choose>
			<hr class="mb-4">
			<div class="row">
			<c:set var="decimalFormat" scope="session" value='${DecimalFormat("###,###,###.00")}'/>
					<div class="col-md-4 order-md-2">
						<c:choose>
							<c:when test="${chosenVehicle != null}">
								<h4 class="d-flex justify-content-between align-items-center mb-3">
									<span class="text-muted">Your purchase</span>
								</h4>
								<ul class="list-group mb-3">
									<li class="list-group-item d-flex justify-content-between lh-condensed">
										<div>
											<h6 class="my-0">${chosenVehicle.year} ${chosenVehicle.make} ${chosenVehicle.model}</h6>
											<small class="text-muted">${chosenVehicle.vin}</small>
										</div> <span class="text-muted">$${decimalFormat.format(chosenVehicle.listPrice)}</span>
									</li>
									<li class="list-group-item d-flex justify-content-between lh-condensed">
										<div>
											<img src="${chosenVehicle.pictureLink}" alt="${chosenVehicle.tagline}" width="300" height="215">
										</div>
									</li>
									<c:choose>
									<c:when test="${goodBid}">
									<li class="list-group-item d-flex justify-content-between bg-light">
										<div class="text-success">
											<h6 class="my-0">Discount</h6>
											<small>BID ACCEPTED</small>
										</div> <span class="text-success">${priceReduction}</span>
									</li>
									<li class="list-group-item d-flex justify-content-between"><span>Total (USD)</span> 
										<strong>$${decimalFormat.format(totalPrice)}</strong></li>
									</c:when>
									<c:otherwise>
									<li class="list-group-item d-flex justify-content-between"><span>Total (USD)</span> 
										<strong>$${decimalFormat.format(chosenVehicle.listPrice)}</strong></li>
									</c:otherwise>
									</c:choose>
								</ul>
								<c:if test="${chosenVehicle.biddable && !goodBid}">
									<form class="card p-2" action="CheckBid" method="post">
										<div class="input-group">
											<label for="bid"></label>
											<input type="text" id="bid" name="bid" class="form-control" placeholder="25000.00">
											<div class="input-group-append">
												<button type="submit" class="btn btn-secondary">Check Bid</button>
											</div>
										</div>
									</form>
								</c:if>
								<c:if test="${!goodBid && bidMade}">
									<div>
										<p class="input-error centered">Sorry, we cannot accept your bid.</p>
									</div>
								</c:if>
							</c:when>
							<c:otherwise>
								<p class="text-center">
									It looks like you haven't selected a vehicle yet. Please feel free to <a href="browse.jsp">browse our inventory</a>
								</p>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-md-8 order-md-1">
					<h4 class="mb-3">Billing name and address</h4>
					<form class="needs-validation" action="BuyVehicle" method="post">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="firstName">First name</label> 
								<input type="text" name="firstName"	class="form-control ${custFields.firstName[0]}" 
									id="firstName" placeholder="${custFields.firstName[1]}" 
									<c:if test="${existingCustomer}">value="${customer.firstName}"</c:if> 
									<c:if test="${existingCustomer == null}">value="${custFields.firstName[2]}"</c:if> required>
								<div class="invalid-feedback">Valid first name is required.</div>
							</div>
							<div class="col-md-6 mb-3">
								<label for="lastName">Last name</label> 
								<input type="text" name="lastName" class="form-control ${custFields.lastName[0]}" 
									id="lastName" placeholder="${custFields.lastName[1]}" 
									<c:if test="${existingCustomer}">value="${customer.lastName}"</c:if> 
									<c:if test="${existingCustomer == null}">value="${custFields.lastName[2]}"</c:if> required>
								<div class="invalid-feedback">Valid last name is required.</div>
							</div>
						</div>

						<div class="mb-3">
							<label for="email">Email (serves as your sign in ID)</label> 
								<input type="email" name="email" class="form-control ${custFields.email[0]}" id="email" 
									placeholder="${custFields.email[1]}" 
									<c:if test="${existingCustomer}">value="${customer.email}"</c:if> 
									<c:if test="${existingCustomer == null}">value="${custFields.email[2]}"</c:if> required>
							<div class="invalid-feedback">Please enter a valid email address.</div>
						</div>
				
						<div class="row">
							<c:if test="${existingCustomer == null}">
								<div class="col-md-8">
									<label for="password">New password</label> 
									<input type="password" name="password" class="form-control ${custFields.newPassword[0]}" 
										id="password" placeholder="${custFields.newPassword[1]}"
										<c:if test="${existingCustomer == null}">value="${custFields.firstName[2]}"</c:if> required>
									<div class="invalid-feedback">Please a valid password.</div>
								</div>
							</c:if>
							<div class="col-md-6 mb-3">
								<label for="phoneNum">Phone Number</label> 
								<input type="text" name="phone" class="form-control ${custFields.phone[0]}"	id="phoneNum" 
									placeholder="${custFields.phone[1]}" 
									<c:if test="${existingCustomer}">value="${customer.phone}"</c:if> 
									<c:if test="${existingCustomer == null}">value="${custFields.phone[2]}"</c:if> required>
								<div class="invalid-feedback">Valid phone number is required.</div>
							</div>
						</div>
						<div class="mb-3">
							<label for="address">Street Number and Name</label> 
							<input type="text" name="street1" class="form-control ${custFields.street1[0]}" id="address" 
								placeholder="${custFields.street1[1]}" 
								<c:if test="${existingCustomer}">value="${customer.address.street1}"</c:if> 
								<c:if test="${existingCustomer == null}">value="${custFields.street1[2]}"</c:if> required>
							<div class="invalid-feedback">Please enter your billing address.</div>
						</div>

						<div class="mb-3">
							<label for="street2">Additional Address<span class="text-muted"> (Required for card)</span></label> 
							<input type="text" name="street2" class="form-control ${custFields.street2[0]}" id="street2" 
								placeholder="${custFields.street2[1]}" 
								<c:if test="${existingCustomer}">value="${customer.address.street2}"</c:if> 
								<c:if test="${existingCustomer == null}">value="${custFields.street2[2]}"</c:if>>
						</div>

						<div class="row">
							<div class="col-md-4 mb-3">
								<label for="city">City</label> 
								<input type="text" name="city" class="form-control ${custFields.city[0]}" id="city"
									placeholder="${custFields.city[1]}" 
									<c:if test="${existingCustomer}">value="${customer.address.city}"</c:if> 
									<c:if test="${existingCustomer == null}">value="${custFields.city[2]}"</c:if> required>
								<div class="invalid-feedback">Please enter your city.</div>
							</div>
							<div class="col-md-4 mb-3">
								<label for="state">State</label> 
								<select class="custom-select d-block w-100 ${custFields.state[0]}" name=state id="state"
									value="${customer.address.state}" required>
									<c:if test="${existingCustomer}">
										<option value="${customer.address.state}"><c:out value="${customer.address.state}" /></option>
									</c:if>
									<c:if test="${existingCustomer == null}">
										<option value="${custFields.state[2]}"><c:out value="${custFields.state[2]}" /></option>
									</c:if>
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
								<label for="zip">Zip</label> 
								<input type="text" class="form-control ${custFields.zip[0]}" id="zip" name="zip"
									placeholder="${custFields.zip[1]}" maxlength="5" 
								<c:if test="${existingCustomer}">value="${customer.address.zip}"</c:if> 
								<c:if test="${existingCustomer == null}">value="${custFields.zip[2]}"</c:if> required>
								<div class="invalid-feedback">Zip code required.</div>
							</div>
						</div>
						<hr class="mb-4">

						<h4 class="mb-3">Please select your payment method and associated payment information.</h4>

						<div class="d-block my-3">
							<p class="${custFields.paymentType[0]}">${custFields.paymentType[1]}</p>
							<div class="custom-control custom-radio">
								<input id="credit" name="paymentMethod" type="radio" class="custom-control-input" 
									value="creditCard" ${custFields.paymentType[2]} required> 
								<label class="custom-control-label" for="credit">Credit card</label>
							</div>
							<div class="custom-control custom-radio">
								<input id="debit" name="paymentMethod" type="radio" class="custom-control-input" 
									value="debitCard" ${custFields.paymentType[3]} required> 
								<label class="custom-control-label" for="debit">Debit card</label>
							</div>
							<div class="custom-control custom-radio">
								<input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" 
									value="paypal" ${custFields.paymentType[4]} required> 
								<label class="custom-control-label" for="paypal">PayPal</label>
							</div>
						</div>

						<div class="row">
							<div class="col-md-5 mb-3">
								<label for="cc-name">Name on card<span class="text-muted"> (Required for card)</span></label> 
								<input type="text" name="cardName" class="form-control ${custFields.cardName[0]}" id="cc-name" 
									placeholder="${custFields.cardName[1]}"	
									<c:if test="${existingCustomer}">value="${customer.pay.nameOnCard}"</c:if> 
									<c:if test="${existingCustomer == null}">value="${custFields.cardName[2]}"</c:if>> 
								<small class="text-muted">Full name as displayed on card</small>
								<div class="invalid-feedback">Name on card is required</div>
							</div>
							<c:choose>
							<c:when test="${storedCard}">
							<div class="col-md-7 mb-3">
								<label for="cc-number">Card number: Confirm last 4 digits<span class="text-muted"> (Required for card)</span></label> 
								<input type="password" name="cardLast4" class="form-control ${custFields.cardLast4[0]}" id="cc-number" 
									placeholder="${custFields.cardLast4[1]}" maxlength="19" value="${custFields.cardLast4[2]}"> 
								<small class="text-muted">Reminder: It starts with ${customer.pay.cardNum}</small>
								<div class="invalid-feedback">Credit card number is required</div>
							</div>
							</c:when>
							<c:otherwise>
							<div class="col-md-7 mb-3">
								<label for="cc-number">Credit/Debit card number<span class="text-muted"> (Required for card)</span></label> 
								<input type="text" name="cardNum" class="form-control ${custFields.cardNum[0]}" id="cc-number" 
									placeholder="${custFields.cardNum[1]}" maxlength="19" value="${custFields.cardNum[2]}"> 
								<div class="invalid-feedback">Credit card number is required</div>
							</div>
							</c:otherwise>
							</c:choose>
						</div>
						<div class="row">
							<div class="col-md-5 mb-3">
								<label for="cc-expiration">Expiration<span class="text-muted"> (Req'd for card)</span></label> 
								<input type="text" name="expiration" class="form-control ${custFields.expiration[0]}" id="cc-expiration" 
									placeholder="${custFields.expiration[1]}" 
									<c:if test="${existingCustomer}">value="${customer.pay.expiration}"</c:if> 
									<c:if test="${existingCustomer == null}">value="${custFields.expiration[2]}"</c:if>>
								<div class="invalid-feedback">Expiration date required</div>
							</div>
							<c:choose>
							<c:when test="${storedCard}">
							<div class="col-md-5 mb-3">
								<label for="cc-cvv">Please confirm your CVV<span class="text-muted"> (Req'd for card)</span></label> 
								<input type="password" name="cvv" class="form-control ${custFields.cvv[0]}" id="cc-cvv" 
									placeholder="${custFields.cvv[1]}" maxlength="3" value="${custFields.cvv[2]}"> 
								<small class="text-muted">The CVV is encrypted for security. Enter all three digits to confirm.</small>
								<div class="invalid-feedback">Security code required</div>
							</div>
							</c:when>
							<c:otherwise>
							<div class="col-md-5 mb-3">
								<label for="cc-cvv">CVV<span class="text-muted"> (Req'd for card)</span></label> 
								<input type="password" name="cvv" class="form-control  ${custFields.cvv[0]}" id="cc-cvv" 
									placeholder="${custFields.cvv[1]}" maxlength="3" value="${custFields.cvv[2]}"> 
								<small class="text-muted">Enter all three digits.</small>
								<div class="invalid-feedback">Security code required</div>
							</div>
							</c:otherwise>
							</c:choose>
						</div>
						<div class="mb-3">
							<label for="email">PayPal email<span class="text-muted"> (Required for PayPal transaction)</span></label> 
							<input type="email" name="paypalEmail" class="form-control ${custFields.paypalEmail[0]}" id="email" 
								placeholder="${custFields.paypalEmail[1]}"
								<c:if test="${existingCustomer}">value="${customer.pay.paypalEmail}"</c:if> 
								<c:if test="${existingCustomer == null}">value="${custFields.paypalEmail[2]}"</c:if>>
							<div class="invalid-feedback">Please enter a valid email address associated with your PayPal account.</div>
						</div>
						<hr class="mb-4">
						<c:choose>
						<c:when test="${existingCustomer}">
							<button class="btn btn-primary btn-lg btn-block" type="submit">Purchase vehicle</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-primary btn-lg btn-block" type="submit">Sign up and purchase vehicle</button>
						</c:otherwise>
						</c:choose>
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
