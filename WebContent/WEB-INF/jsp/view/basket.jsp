<%@ page session="true" import="domain.Game"%>
<%--@elvariable id="cartItems" type="domain.OrderItem"--%>

<div class="container">
	<div class="row">
		<div class="col-xs-6 col-md-10">
			<div class="panel panel-default">
				<div class="panel-heading">
      				<h4  class="panel-title">Your basket items:</h4>
  				</div>
  				<ul class="panel-body list-group">
  				<c:forEach items="${cartItems}" var="cartItem">
					<li class="row list-group-item">
						<div class="col col-md-2">
							<img src="${cartItem.getItem().getPathPictureThumbLocation()}" alt="${cartItem.getItem().getName()}" width=80px/>
						</div>
						<div class="col col-md-10">
							<h5><a href="<c:url value="/store">
								<c:param name="action" value="product" />
								<c:param name="barcode" value="${cartItem.getItem().getBarcodeGS1()}" />
								</c:url>">${cartItem.getItem().getName()}</a>
							</h5>
      						<p>Price:
      							<c:choose>
      								<c:when test="${cartItem.getItem().getDiscount() > 0}">
      									<span class="label label-default"><s>&#163;${cartItem.getItem().getPrice()}</s></span>
										<span class="label label-danger" role="alert">&#163;${cartItem.getItem().getDiscountedPrice()}</span>
									</c:when>
									<c:otherwise>
										<span class="label label-default">&#163;${cartItem.getItem().getPrice()}</span>
									</c:otherwise>
      							</c:choose>
      						</p>
      						<p>
      							<strong>Quantity: ${cartItem.getQuantity()}</strong>
      						</p>
						</div>
					</li>
  				</c:forEach>
  				</ul>
  			</div>
 		</div>
 		<div class="col-xs-6 col-md-2" id="basket">checkout</div>
 	</div>
</div>