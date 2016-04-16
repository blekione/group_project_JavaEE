<%@ page session="true"%>
<%--@elvariable id="games" type="domain.Game"--%>
<%--@elvariable id="edit" type="java.lang.Boolean"--%>
<%--@elvariable id="game" type="domain.Game"--%>

<title>GG Marketing - Edit an Existing Game</title>

<body>
  <div class="container-fluid panel panel-default">
    <div class="panel-heading row"><h3 class="panel-title">Edit Game Details</h3></div>	
    <div class="panel-body">
      <c:choose>
        <c:when test="${edit == false}">
          <form class="form-horizontal panel-body" role="form" 
                action="marketing" method="POST">
            <input type="hidden" name="action" value="editGame"/>

            <div class="form-group">
              <label for="selectGame" class="col-sm-1 control-label">Game</label>
              <div class="col-sm-6">
                <select id="selectGame" name="barcode" class="form-control">
                  <c:forEach items="${games}" var="game">
                    <option value="${game.getBarcodeGS1()}">
                      ${game.getName()} - ${game.getPlatform()}
                    </option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <div class="form-group">
              <div class="col-sm-offset-1 col-sm-6">
                <input type="submit" name="submit" value="Modify Selected Game" class="btn btn-primary" />
              </div>
            </div>

          </form>
        </c:when>
        <c:otherwise>
          <form class="form-horizontal panel-body" role="form" action="marketing" method="POST">
            <input type="hidden" name="action" value="updateGame"/>

            <div class="form-group">
              <label for="gameBarcode" class="col-sm-1 control-label">Barcode</label>
              <div class="col-sm-6">
                <input type="text" name="gameBarcode" id="gameBarcode"
                       class="form-control" placeholder="XYZ123"
                       value="${game.getBarcodeGS1()}" readonly />
              </div>
            </div>

            <div class="form-group">
              <label for="gameName" class="col-sm-1 control-label">Name</label>
              <div class="col-sm-6">
                <input type="text" name="gameName" id="gameName"
                       class="form-control" placeholder="GameXYZ"
                       value="${game.getName()}" required />
              </div>
            </div>

            <div class="form-group">
              <label for="gameDesc" class="col-sm-1 control-label">Description</label>
              <div class="col-sm-6">
                <textarea name="gameDesc" id="gameDesc" class="form-control"
                          placeholder="GameXYZ Description"
                          required>${game.getDescription()}</textarea>
              </div>
            </div>

            <div class="form-group">
              <label for="gamePrice" class="col-sm-1 control-label">Price</label>
              <div class="col-sm-6">
                <div class="input-group">
                  <span class="input-group-addon" id="poundsymbol">&pound;</span>
                  <input type="number" name="gamePrice" id="gamePrice" maxlength="3"
                         min="0" max="100" step="0.01" class="form-control" 
                         placeholder="35.00" value="${game.getPrice()}" required />
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="gameGenre" class="col-sm-1 control-label">Genre</label>
              <div class="col-sm-6">
                <select id="gameGenre" name="gameGenre" class="form-control">
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
                </select>
              </div>
            </div>

            <div class="form-group">
              <label for="gamePlatform" class="col-sm-1 control-label">Platform</label>
              <div class="col-sm-6">
                <select id="gamePlatform" name="gamePlatform" class="form-control">
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
                </select>
              </div>
            </div>

            <div class="page-header">
              <h3>
                Promotion & Stock Information
                <small>
                  This information can only be updated in their respective
                  sections.
                </small>
              </h3>
            </div>

            <div class="form-group">
              <label for="gameStock" class="col-sm-1 control-label">Stock</label>
              <div class="col-sm-6">
                <input type="number" name="gameStock" id="gameStock" maxlength="3"
                       min="0" max="100" class="form-control"
                       placeholder="20" value="${game.getStock()}" readonly />
              </div>
            </div>

            <div class="form-group">
              <label for="gameDiscount" class="col-sm-1 control-label">Discount</label>
              <div class="col-sm-6">
                <div class="input-group">
                  <span class="input-group-addon" id="percentsymbol">&#37;</span>
                  <input type="number" name="gameDiscount" id="gameDiscount" maxlength="3"
                         min="0" max="100" class="form-control" 
                         placeholder="15.00" value="${game.getDiscount()}" readonly />
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="gamePointMult" class="col-sm-1 control-label">Loyalty Points<br/> Multiplier</label>
              <div class="col-sm-6">
                <div class="input-group">
                  <span class="input-group-addon" id="discount">x</span>
                  <input type="number" name="gamePointMult" 
                         id="gamePointMult" class="form-control" step="0.01"
                         min="1" max="10" value="${game.getPointMultiplier()}" readonly />
                </div>
              </div>
            </div>

            <div class="form-group">
              <div class="col-sm-offset-1 col-sm-6">
                <input type="submit" name="submit" value="Update Selected Game" class="btn btn-primary" />
              </div>
            </div>

          </c:otherwise>
        </c:choose>
    </div>
  </div>
</body>