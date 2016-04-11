<%--@elvariable id="checkoutPass" type="domain.Customer"--%>
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
		go and pay 
	</c:otherwise>
</c:choose>
</div>
</body>
