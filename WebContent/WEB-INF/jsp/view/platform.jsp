<%@ page session="false"  import="java.util.List, domain.Game" %>

<% 
	String platform = (String)request.getAttribute("platform");
	List<Game> discountedGames = (List)request.getAttribute("discountedGames");
	List<Game> platformGames = (List)request.getAttribute("platformGames");
	%>
	
  	<div class="container panel panel-default">
  		<div class="panel-heading row"><h3 class="panel-title">Games with discount</h3></div>
		<div class="panel-body">
			<% for(Game game : discountedGames) { %>
			<div class="col-xs-6 col-md-2 list-group-item-text">
				<div class="thumbnail">
					<img src="<%= game.getPictureLocation() %>" alt="<%= game.getName() %>">
					<div class="caption">
						<a href="<c:url value="/product">
							<c:param name="id" value="<%= game.getBarcodeGS1() %>" />
							</c:url>"><%= game.getName()%></a>
						<p>
							<span class="label label-default"><s>&#163;<%= game.getPrice() %></s></span>
							<span class="label label-danger" role="alert">&#163;<%= game.getDiscountedPrice()%></span>
						</p>
					</div>
				</div>
				<div class="ribbon <%= platform.toLowerCase().substring(0, 2)%>"><span>-<%= game.getDiscount()%>&#37;</span></div>
			</div>
			<%} %>
		</div>
	</div>
	<div class="container panel panel-default">
		<div class="panel-heading row">
      		<h4  class="panel-title col-md-12">Games for <%= platform %></h4>
  		</div>
			<ul class="list-group">
				<% for (Game game : platformGames) {%>
    			<li class="row list-group-item">
      				<div class="col-md-2"><img src="<%= game.getPictureLocation() %>" alt="<%= game.getName() %>" ng-href="#"/></div>
      				<div class="col-md-10">
      					<h4><a ng-href="#"><%= game.getName() %></a></h4>
      					<p>Price: 
      						<%if (game.getDiscount() > 0) { %>
      						<span class="label label-default"><s>&#163;<%= game.getPrice() %></s></span>
							<span class="label label-danger" role="alert">&#163;<%= game.getDiscountedPrice()%></span></p>
							<% } else { %>
							<span class="label label-default">&#163;<%= game.getPrice() %></span>
							<% } %>
      					<p>Stock</p>
      				</div>
    			</li>
    			<% } %>
			</ul>
		</div>			
</div>
</body>