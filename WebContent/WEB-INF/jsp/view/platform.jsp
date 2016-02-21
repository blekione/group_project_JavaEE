<%@ page session="false"  import="java.util.List, domain.Game" %>

<% 
	String platform = (String)request.getAttribute("platform");
	List<Game> discountedGames = (List)request.getAttribute("discountedGames");
	%>
	
<div class="container">
  		<div class="panel panel-default">
  			<div class="panel-heading"><strong>Games with discount</strong></div>
			<div class="panel-body">
				<% for(Game game : discountedGames) { %>
				<div class="col-xs-6 col-md-2 list-group-item-text">
					<div class="thumbnail">
						<img src="<%= game.getPictureLocation() %>" alt="<%= game.getName() %>">
						<div class="caption">
							<a href="<c:url value="/product">
								<c:param name="id" value="<%= game.getBarcodeGS1() %>" />
								</c:url>"><%= game.getName()%></a>
							<p><span class="label label-default"><s>&#163;<%= game.getPrice() %></s></span>
							 <span class="label label-danger" role="alert">&#163;<%= game.getDiscountedPrice() + " -" + game.getDiscount() %>&#37;</span></p>
						</div>
					</div>
					<div class="ribbon <%= platform.toLowerCase().substring(0, 2)%>"><span><%= game.getPlatform() %></span></div>
				</div>
				<%} %>
			</div>
		</div>
	</div>
</body>