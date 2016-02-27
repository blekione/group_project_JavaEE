<%@ page session="true"  import="domain.Game" %>

<% 
	Game game = (Game)request.getAttribute("game");
	%>
	
	

	<div class="row">
		<div class="col-xs-6 col-md-3">
			<img src="<%= game.getPathPictureLocation() %>" alt="<%= game.getName() %>" width=300px>
		</div>
		<div class="col-xs-6 col-md-7">
			<h2><%= game.getName() %></h3>
			<div class="row">
				<div class="col-md-2 text-right">
				<% if (game.getDiscount() > 0) {%>
					<p>Price:</p>
					<p>Deal Price:</p>
				<% } else {%>
					<p>Price:</p>
				<% } %>
					<p>Stock</p>
					<p>Description:</p>
				</div>
				<div class="col-md-10">
				<% if (game.getDiscount() > 0) {%>
					<p class="text-danger"><s>&#163;<%= game.getPrice() %></s></p>
					<p>&#163;<%= game.getDiscountedPrice()%> <span class="text-warning">(-<%= game.getDiscount() %>&#37;)</span></p>
				<% } else { %>
					<p>&#163;<%= game.getPrice() %></p>
				<% } %>
					<p><% if (game.getStock() <= 5) { %>
						<span class="text-danger">only <%= game.getStock() %> left</span>
						<% } else { %>
							<%=game.getStock()%>
						<% } %>
						</p>
					<p><%= game.getDescription() %></p>
				</div>
			</div>
		</div>
		<div class="col-xs-6 col-md-2">
			<a href="<c:url value="/store">
						<c:param name="action" value="addToBasket" />
						<c:param name="barcode" value="<%= game.getBarcodeGS1() %>"/>
					</c:url>" class="btn btn-info" alt="Add to shopping basket">Add to basket</a>
		</div>
	</div>
</div>	
</body>
</html>