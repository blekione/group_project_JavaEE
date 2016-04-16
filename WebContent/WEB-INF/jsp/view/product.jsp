<%@ page session="true"%>
<%--@elvariable id="game" type="domain.Game"--%>	

	<div class="container-fluid row">
		<div class="col-xs-6 col-md-3">
			<img src="${game.getPathPictureLocation()}" alt="${game.getName()}" width=300px>
		</div>
		<div class="col-xs-6 col-md-7">
			<h2>${game.getName()}</h3>
			<div class="row">
				<div class="col-md-2 text-right">
					<c:choose>
						<c:when test="${game.getDiscount() > 0}">
							<p>Price:</p>
							<p>Deal Price:</p>
						</c:when>
						<c:otherwise>
							<p>Price:</p>
						</c:otherwise>
					</c:choose>
					<p>Stock:</p>
					<p>Description:</p>
				</div>
				<div class="col-md-10">
					<c:choose>
						<c:when test="${game.getDiscount() > 0}">
							<p class="text-danger"><s>&#163;${game.getPrice()}</s></p>
							<p>&#163;${game.getDiscountedPrice()}<span class="text-warning"> (-${game.getDiscount()}&#37;)</span></p>
						</c:when>
						<c:otherwise>
							<p>&#163;${game.getPrice()}</p>
						</c:otherwise>
					</c:choose>
					<p>
						<c:choose>
							<c:when test="${game.getStock() <= 5}">
								<span class="text-danger">Only ${game.getStock()} left.</span>
							</c:when>
							<c:otherwise>
								${game.getStock()}
							</c:otherwise>
						</c:choose>
					</p>
					<p>${game.getDescription()}</p>
				</div>
			</div>
		</div>
		<form  action="store"  method="POST" class="col-xs-6 col-md-2 form-horizontal" id="basket">
			<div class="form-group">
    			<label for="quantity" class="col-sm-4 control-label">Quantity:</label>
   				<div class="col-sm-5">
      				<select class="form-control" name="quantity">
  						<option>1</option>
  						<option>2</option>
  						<option>3</option>
  						<option>4</option>
  						<option>5</option>
					</select>
    			</div>
    		</div>
    		<input type="hidden" name="action" value="addToBasket"/>
    		<input type="hidden" name="barcode" value="${game.getBarcodeGS1()}"/>
    		<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      			<button type="submit" class="btn btn-info" alt="Add to shopping basket">Add Item to Basket <span class="badge"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></span></button>
    		</div>
    	</form>
	</div>
</div>	
</body>
</html>