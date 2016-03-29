<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="titles" type="domain.Title"--%>
<div class="panel panel-default">
	<div class="panel-heading"><h4>Please enter your details</h4></div>
	<form class="form-horizontal panel-body" role="form" action="store" method="POST">
		<input type="hidden" name="action" value="sign-new-customer"/>
		<div class="form-group">
    		<label for="inputEmail" class="col-sm-2 control-label">Email</label>
    		<div class="col-sm-4">
      			<input type="email" class="form-control" id="inputEmail" placeholder="Email" name="email">
    		</div>
  		</div>
		<div class="form-group">
    		<label for="inputPassword" class="col-sm-2 control-label">Password</label>
    		<div class="col-sm-4">
      			<input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password">
    		</div>
  		</div>
  		<div class="form-group">
  			<label for="selectTitle" class="col-sm-2 control-label">Title</label>
  			<div class="col-sm-1">
  				<select class="form-control" id="selectTitle">
  					<c:forEach items="${titles}" var="title">
  						<option>${title}</option>
  					</c:forEach>
  				</select>
  			</div>
  		</div>
  		<div class="form-group">
    		<label for="inputFirstName" class="col-sm-2 control-label">First Name</label>
    		<div class="col-sm-4">
      			<input type="text" class="form-control" id="inputFirstName" placeholder="First Name" name="firstName">
    		</div>
  		</div>
  		<div class="form-group">
    		<label for="inputLastName" class="col-sm-2 control-label">Last Name</label>
    		<div class="col-sm-4">
      			<input type="text" class="form-control" id="inputLastName" placeholder="Last Name" name="lastName">
    		</div>
  		</div>
  		<div class="form-group">
    		<label for="inputTelephone" class="col-sm-2 control-label">Telephone</label>
    		<div class="col-sm-4">
      			<input type="text" class="form-control" id="inputTelephone" placeholder="Telephone" name="telephone">
    		</div>
  		</div>
		<div class="form-group">
    		<label for="inputLoyaltyAcc" class="col-sm-2 control-label">Loyalty Account No.</label>
    		<div class="col-sm-4">
      			<input type="text" class="form-control" id="inputLoyaltyAcc" placeholder="Loyalty Account No." name="loyaltyAcc">
    		</div>
  		</div>
		<legend>Address</legend>  		
  		<div class="form-group">
    		<label for="inputHouseNo" class="col-sm-2 control-label">House Number</label>
    		<div class="col-sm-4">
      			<input type="text" class="form-control" id="inputHouseNumer" placeholder="House No." name="houseNumber">
    		</div>
  		</div>
  		<div class="form-group">
    		<label for="inputStreet" class="col-sm-2 control-label">Street</label>
    		<div class="col-sm-4">
      			<input type="text" class="form-control" id="inputStreet" placeholder="Street" name="street">
    		</div>
  		</div>
  		<div class="form-group">
    		<label for="inputCity" class="col-sm-2 control-label">City</label>
    		<div class="col-sm-4">
      			<input type="text" class="form-control" id="inputCity" placeholder="City" name="city">
    		</div>
  		</div>
  		<div class="form-group">
    		<label for="inputCounty" class="col-sm-2 control-label">County</label>
    		<div class="col-sm-4">
      			<input type="text" class="form-control" id="inputCounty" placeholder="County" name="county">
    		</div>
  		</div>
  		<div class="form-group">
    		<label for="inputPostcode" class="col-sm-2 control-label">Postcode</label>
    		<div class="col-sm-4">
      			<input type="text" class="form-control" id="inputPostcode" placeholder="Postcode" name="postcode">
    		</div>
  		</div>
  		<div class="form-group">
    		<div class="col-sm-offset-2 col-sm-10">
      			<button type="submit" class="btn btn-primary">Sign in</button>
    		</div>
  		</div>
	</form>
</div>
