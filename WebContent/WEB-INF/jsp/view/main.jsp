<%@ page session="true"%>
<%--@elvariable id="games" type="domain.Game"--%>

<div class="container-fluid panel panel-default">
	<div class="panel-heading row"><h3 class="panel-title">Discounted Games</h3></div>	
		<div class="panel-body">
			<c:forEach items="${games}" var="game">
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
						<span class="label label-danger" role="alert">&#163;${game.getDiscountedPrice()} (- ${game.getDiscount()}&#37;)</span>
					</p>
				</div>
			</div>
			<div class="ribbon ${game.getPlatform().toString().toLowerCase().substring(0, 2)}"><span>${game.getPlatform()}</span></div>
		</div>
		</c:forEach>>
	</div>
</div>
</div>
</body>