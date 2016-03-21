<%@ page session="true" import="domain.ShoppingCart, domain.OrderItem, domain.Game"%>

<div class="container">
	<div class="row">
		<div class="col-xs-6 col-md-10">
			<div class="panel panel-default">
				<div class="panel-heading">
      				<h4  class="panel-title">Your basket items:</h4>
  				</div>
  				<ul class="panel-body list-group">
  				<% for(OrderItem orderItem : cart.getBasket()) { 
						Game game = orderItem.getItem();
				%>
					<li class="row list-group-item">
						<div class="col col-md-2"><img src="<%= game.getPathPictureThumbLocation() %>" alt="<%= game.getName() %>" width=80px/></div>
						<div class="col col-md-10">
							<h5><a href="<c:url value="/store">
								<c:param name="action" value="product" />
								<c:param name="barcode" value="<%= game.getBarcodeGS1() %>" />
								</c:url>"><%= game.getName()%></a>
							</h5>
      						<p>Price: 
      						<%if (game.getDiscount() > 0) { %>
      							<span class="label label-default"><s>&#163;<%= game.getPrice() %></s></span>
								<span class="label label-danger" role="alert">&#163;<%= game.getDiscountedPrice()%></span>
							<% } else { %>
								<span class="label label-default">&#163;<%= game.getPrice() %></span>
							<% } %>
      							<strong>Quantity: <%= orderItem.getQuantity() %></strong>
      						</p>
						</div>
					</li>
  				<% }%>
  				</ul>
  			</div>
 		</div>
 		<div class="col-xs-6 col-md-2" id="basket">checkout</div>
 	</div>
</div>