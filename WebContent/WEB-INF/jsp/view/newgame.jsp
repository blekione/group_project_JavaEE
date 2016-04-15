<%@ page session="true"%>
<%--@elvariable id="platforms" type="java.lang.String"--%>
<%--@elvariable id="genres" type="java.lang.String"--%>

<div class="container-fluid panel panel-default">
  <div class="panel-heading row"><h3 class="panel-title">Add New Game</h3></div>	
  <div class="panel-body">
    <form class="form-horizontal panel-body" role="form" action="marketing" method="POST">
      <input type="hidden" name="action" value="addNewGame"/>
      Game Barcode: <input type="text" name="gameBarcode" id="gameBarcode" /> <br />
      Game Name: <input type="text" name="gameName" id="gameName" /> <br />
      Game Description: <input type="text" name="gameDesc" id="gameDesc" /> <br />
      Game Genre:
      <select>
	<c:forEach items="${genres}" var="genre">
	  <option value="${genres}">
	    ${genre}
	  </option>
	</c:forEach>
      </select> <br />

      Game Platform:
      <select>
	<c:forEach items="${platforms}" var="platform">
	  <option value="${platform}">
	    ${platform}
	  </option>
	</c:forEach>
      </select> <br />

      Game Price: <input type="number" name="gamePrice" id="gamePrice" min="0" max="1000" /> <br />
      Game Discount: <input type="number" name="gameDiscount" id="gameDiscount" min="0" max="100" /> <br />
      Game Stock: <input type="number" name="gameStock" id="gameStock" min="0" /> <br />
      Game Point Multiplier: <input type="number" name="gamePointMult" id="gamePointMult" min="1" /> <br />
      <input type="submit" name="submit" value="submit" />
    </form>
  </div>
</div>
</body>