<%@ page session="true"%>
<%--@elvariable id="games" type="domain.Game"--%>

<div class="container-fluid panel panel-default">
  <div class="panel-heading row"><h3 class="panel-title">Add New Promotion</h3></div>	
  <div class="panel-body">
    <form class="form-horizontal panel-body" role="form" action="marketing" method="POST">
      <input type="hidden" name="action" value="addNewPromo"/>
      <center>
	<select id="selectGame" name="barcode">
	  <c:forEach items="${games}" var="game">
	    <option value="${game.getBarcodeGS1()}">
	      ${game.getName()} - ${game.getPlatform()}
	    </option>
	  </c:forEach>
	</select>

	<br />
	New Discount: <input type="number" name="discount" id="discount" />

	<br />
	New Points Multiplier: <input type="number" name="pointMult" id="discount" />

	<br />
	<input type="submit" name="submit" value="submit" />
      </center>
    </form>
  </div>
</div>
</div>
</body>