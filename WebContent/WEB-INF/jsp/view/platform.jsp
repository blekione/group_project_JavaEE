<%@ page session="true"%>
<%--@elvariable id="success" type="java.lang.Boolean"--%>
<%--@elvariable id="discountedGames" type="domain.Game"--%>
<%--@elvariable id="platformGames" type="domain.Game"--%>
<%--@elvariable id="platform" type="java.lang.String"--%>
	
	<c:if test="${success}">
    	<div class="container alert alert-success">
    		<p><strong>Congratulation!</strong> You successfully added an item to the shopping cart.</p>
    		<p>Please continue your shopping or 
    			<a href="<c:url value="/store">
						<c:param name="action" value="viewBasket" />
						</c:url>"><strong>go to your basket</strong>
				</a>
			 to checkout.</p>
    	</div>			
    </c:if>
	
  	<div class="container-fluid panel panel-default">
  		<div class="panel-heading row"><h3 class="panel-title">Discounted Games</h3></div>
		<div class="panel-body">
			<c:forEach items="${discountedGames}" var="game">
				<div class="col-xs-6 col-md-2 list-group-item-text">
					<div class="thumbnail">
						<img src="${game.getPathPictureThumbLocation()}" alt="${game.getName()}">
						<div class="caption">
							<a href="<c:url value="/store">
								<c:param name="action" value="product" />
								<c:param name="barcode" value="${game.getBarcodeGS1()}" />
								</c:url>">${game.getName()}</a>
							<p>
								<span class="label label-default"><s>&#163;${game.getPrice()}</s></span>
								<span class="label label-danger" role="alert">&#163;${game.getDiscountedPrice()} (-${game.getDiscount()}&#37;)</span>
							</p>
						</div>
					</div>
					<div class="ribbon ${platform.toLowerCase().substring(0, 2)}"><span>-${game.getDiscount()}&#37;</span></div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="container panel panel-default">
		<div class="panel-heading row">
      		<h4  class="panel-title col-md-12">Games for ${platform}</h4>
  		</div>
			<ul class="list-group">
				<c:forEach items="${platformGames}" var="game">
    				<li class="row list-group-item">
      					<div class="col col-md-2"><img src="${game.getPathPictureThumbLocation()}" alt="${game.getName()}" ng-href="#"/></div>
      					<div class="col col-md-10">
      						<h4><a href="<c:url value="/store">
								<c:param name="action" value="product" />
								<c:param name="barcode" value="${game.getBarcodeGS1()}" />
								</c:url>">${game.getName()}</a>
							</h4>
      						<p>Price: 
      							<c:choose>
      								<c:when test="${game.getDiscount() > 0}">
      									<span class="label label-default"><s>&#163;${game.getPrice()}</s></span>
										<span class="label label-danger" role="alert">&#163;${game.getDiscountedPrice()}</span>
									</c:when>
									<c:otherwise>
										<span class="label label-default">&#163;${game.getPrice()}</span>
									</c:otherwise>
      							</c:choose>
      						</p>
							<p>Stock <span class="label label-default">${game.getStock()}</span> </p>
      					</div>
    				</li>
    			</c:forEach>
			</ul>
		</div>			
</div>
</div>
</body>