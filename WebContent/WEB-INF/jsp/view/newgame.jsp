<%@ page session="true"%>
<%--@elvariable id="platforms" type="java.lang.String"--%>
<%--@elvariable id="genres" type="java.lang.String"--%>

<div class="container-fluid panel panel-default">
  <div class="panel-heading row"><h3 class="panel-title">Add New Game</h3></div>	
  <div class="panel-body">
    <form class="form-horizontal panel-body" role="form" action="marketing" method="POST">
      <input type="hidden" name="action" value="addNewGame"/>
      Game Barcode: <input type="text" name="gameBarcode" id="gameBarcode" required /> <br />
      Game Name: <input type="text" name="gameName" id="gameName" required /> <br />
      Game Description: <input type="text" name="gameDesc" id="gameDesc" required /> <br />
      Game Genre:
      <select id="gameGenre" name="gameGenre">
        <c:forEach items="${genres}" var="genre">
          <option value="${genre}">
            ${genre}
          </option>
        </c:forEach>
      </select> <br />

      Game Platform:
      <select id="gamePlatform" name="gamePlatform">
        <c:forEach items="${platforms}" var="platform">
          <c:if test="${game.getPlatform().equals(platform)}">
            <option value="${platform}" selected="true">
            ${platform}
          </option>
          </c:if>
          <option value="${platform}">
            ${platform}
          </option>
        </c:forEach>
      </select> <br />

      Game Price: <input type="number" name="gamePrice" id="gamePrice" min="0" max="1000" step="0.01" required /> <br />
            Game Discount: <input type="number" name="gameDiscount" id="gameDiscount" min="0" max="100" required /> <br />
            Game Stock: <input type="number" name="gameStock" id="gameStock" min="0" readonly="true" /> <br />
            Game Point Multiplier: <input type="number" name="gamePointMult" id="gamePointMult" step="0.1" min="1" required /> <br />
      <input type="submit" name="submit" value="submit" />
    </form>
  </div>
</div>
</body>