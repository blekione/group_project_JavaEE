<%@ page session="true"%>
<%--@elvariable id="games" type="domain.Game"--%>
<%--@elvariable id="edit" type="java.lang.Boolean"--%>
<%--@elvariable id="game" type="domain.Game"--%>

<body>
  <div class="container-fluid panel panel-default">
    <div class="panel-heading row"><h3 class="panel-title">Edit Game Details</h3></div>	
    <div class="panel-body">
      <c:choose>
        <c:when test="${edit == false}">
          <form class="form-horizontal panel-body" role="form" 
                action="marketing" method="POST">
            <input type="hidden" name="action" value="editGame"/>
            <center>
              <select id="selectGame" name="barcode">
                <c:forEach items="${games}" var="game">
                  <option value="${game.getBarcodeGS1()}">
                    ${game.getName()} - ${game.getPlatform()}
                  </option>
                </c:forEach>
                <input type="submit" name="submit" value="submit" />
            </center>
          </form>
        </c:when>
        <c:otherwise>
          <form class="form-horizontal panel-body" role="form" action="marketing" method="POST">
            <input type="hidden" name="action" value="updateGame"/>
            Game Barcode: <input type="text" name="gameBarcode" id="gameBarcode"
                                 value="${game.getBarcodeGS1()}" readonly="true" /> <br />
            Game Name: <input type="text" name="gameName" id="gameName"
                              value="${game.getName()}" required /> <br />
            Game Description: <input type="text" name="gameDesc" id="gameDesc"
                                     value="${game.getDescription()}" required /> <br />
            Game Genre:
            <select id="gameGenre" name="gameGenre">
              <c:forEach items="${genres}" var="genre">
                <c:if test="${game.getGenre().equals(genre)}">
                  <option value="${genre}" selected="true">
                    ${genre}
                  </option>
                </c:if>
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

            Game Price: <input type="number" name="gamePrice" id="gamePrice"
                               value="${game.getPrice()}" min="0" max="1000" step="0.01" required /> <br />
            Game Discount: <input type="number" name="gameDiscount" id="gameDiscount"
                                  value="${game.getDiscount()}" min="0" max="100" required /> <br />
            Game Stock: <input type="number" name="gameStock" id="gameStock" min="0"
                               value="${game.getStock()}" readonly="true" /> <br />
            Game Point Multiplier: <input type="number" name="gamePointMult" id="gamePointMult"
                                          value="${game.getPointMultiplier()}" step="0.1" min="1" required /> <br />
            <input type="submit" name="submit" value="submit" />
          </c:otherwise>
        </c:choose>
    </div>
  </div>
</body>