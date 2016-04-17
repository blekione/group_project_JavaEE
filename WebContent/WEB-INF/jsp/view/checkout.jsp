<%--@elvariable id="checkoutPass" type="domain.Customer"--%>
<%--@elvariable id="total" type="java.lang.Double"--%>
<%--@elvariable id="loyaltyPoints" type="java.lang.Integer"--%>
<%--@elvariable id="detailsCheckFail" type="java.lang.Boolean"--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${checkoutPass == false}">
		<div class="jumbotron">
			<h3>Please login or create new account</h3>
			<p>To complete purchase you must have account with us</p>
			<div class="row">
				<div class="col col-md-4">
					<div  class="panel panel-default">
						<div class="panel-heading">Login</div>
						<form class="panel-body" role="form" action="store" method="POST">
							<input type="hidden" name="action" value="login"/>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" id="uLogin" placeholder="Email" name="email">
									<label for="uLogin" class="input-group-addon glyphicon glyphicon-envelope"></label>
								</div>
							</div> <!-- /.form-group -->

							<div class="form-group">
								<div class="input-group">
									<input type="password" class="form-control" id="uPassword" placeholder="Password" name="pass">
									<label for="uPassword" class="input-group-addon glyphicon glyphicon-lock"></label>
								</div> <!-- /.input-group -->
							</div> <!-- /.form-group -->
    						<button type="submit" class="btn btn-info" alt="Login">Login <span class="badge"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></span></button>  			
						</form>
					</div>
				</div>
				<div class="col col-md-4">
					<div  class="panel panel-default">
						<div class="panel-heading">Create account</div>
						<div class="panel-body">
							<a class="btn btn-primary btn-block" href="<c:url value="/store">
								<c:param name="action" value="signin" />
								</c:url>" role="button">Sign in</a>
						</div>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<c:if test="${detailsCheckFail}">
			<div class="alert alert-warning" role="alert"><b>Warning!</b> You have entered wrong card details. Please try again</div>
		</c:if>
		<div class="row">
			<div class="col col-md-6">
				<div class="panel panel-info">
  					<div class="panel-heading">
  						<h4>You will be charged: &#163;<b>${total}</b></h4>
  						<p>To proceed the payment, we need the details of your credit card.</p>
  					</div>
  					<div class="panel-body">
  						<form class="form-horizontal" role="form" action="store" method="POST">
    						<input type="hidden" name="action" value="proceed-payment"/>
    						<div class="form-group">
     					 		<label for="inputCardholderName" class="col-sm-3 control-label">Cardholder Name</label>
      							<div class="col-sm-5">
        							<input type="text" class="form-control" id="inputCardholderName" placeholder="Cardholder Name" name="cardholderName" />
      							</div>
    						</div>
    						<div class="form-group">
     					 		<label for="inputCardNUmber" class="col-sm-3 control-label">Card Number</label>
      							<div class="col-sm-5">
      								<div class="input-group">
        							<input type="text" class="form-control" id="inputCardNUmber" placeholder="Card Number" name="cardNumber" />
        							<span class="input-group-addon"><i class="glyphicon glyphicon-credit-card"></i></span>
        							</div>
      							</div>
    						</div>
    						<div class="form-group">
     					 		<label for="inputCVC" class="col-sm-3 control-label">CVC</label>
      							<div class="col-sm-5">
        							<input type="text" class="form-control" id="inputCVC" placeholder="Card Verification Code" name="cvc" />
      							</div>
    						</div>
    						<div class="form-group">
        						<label class="col-sm-3 control-label" for="selectExpiryMonth">Expiration Date</label>
        						<div class="col-sm-9">
          							<div class="row">
           								<div class="col-xs-3">
              								<select class="form-control col-sm-2" name="expiryMonth" id="selectExpiryMonth">
                								<option value="00">Month</option>
                								<option value="01">Jan (01)</option>
                								<option value="02">Feb (02)</option>
                								<option value="03">Mar (03)</option>
                								<option value="04">Apr (04)</option>
                								<option value="05">May (05)</option>
                								<option value="06">June (06)</option>
	                							<option value="07">July (07)</option>
	                							<option value="08">Aug (08)</option>
	                							<option value="09">Sep (09)</option>
	                							<option value="10">Oct (10)</option>
	                							<option value="11">Nov (11)</option>
	                							<option value="12">Dec (12)</option>
	              							</select>
	            						</div>
	            						<div class="col-xs-3">
	              							<select class="form-control" name="selectExpiryYear">
	                                			<option value="0">Year</option>
	                                			<option value="16">2016</option>
	                							<option value="17">2017</option>
	                							<option value="18">2018</option>
	                							<option value="19">2019</option>
	                							<option value="20">2020</option>
	                							<option value="21">2021</option>
	                							<option value="22">2022</option>
	                							<option value="23">2023</option>
	             						 	</select>
	            						</div>
          							</div>
        						</div>
        					</div>
        					<div class="form-group">
        						<p class="alert alert-info text-center">You have <b>${loyaltyPoints}</b> loyalty points worth <b>&#163;${loyaltyPoints * 0.01}</b></p> 	
     					 		<label for="checkRedeem" class="col-sm-4 control-label">Redeem your points</label>
      							<div class="col-sm-1">
        							<input type="checkbox" class="form-control" id="checkRedeem" name="redeem" value="true"/>
      							</div>
    						</div>
        					
        					<div class="form-group">
      							<div class="col-sm-12">
        							<button type="submit" class="btn btn-primary btn-block">Proceed Payment</button>
      							</div>
   							</div>
    					</form>
    				</div>
  				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>
</div>
</body>
