<%@ page session="true"%>
<%--@elvariable id="platforms" type="java.lang.String"--%>
<%--@elvariable id="genres" type="java.lang.String"--%>

<title>GG Marketing - Add a New Game</title>
<body>
  <div class="container-fluid panel panel-default">
    <div class="panel-heading row"><h3 class="panel-title">Enter the New Game's Details</h3></div>	
    <div class="panel-body">
      <form class="form-horizontal panel-body" role="form" action="marketing" method="POST">
        <input type="hidden" name="action" value="addNewGame"/>

        <div class="form-group">
          <label for="gameBarcode" class="col-sm-1 control-label">Barcode</label>
          <div class="col-sm-6">
            <input type="text" name="gameBarcode" id="gameBarcode"
                   class="form-control" placeholder="XYZ123" required />
            <p class="help-block">
              This is the serial number of the game. This must be a unique value,
              otherwise it cannot be stored within the database and as such, sold.
            </p>
          </div>
        </div>

        <div class="form-group">
          <label for="gameName" class="col-sm-1 control-label">Name</label>
          <div class="col-sm-6">
            <input type="text" name="gameName" id="gameName"
                   class="form-control" placeholder="GameXYZ" required />
          </div>
        </div>

        <div class="form-group">
          <label for="gameDesc" class="col-sm-1 control-label">Description</label>
          <div class="col-sm-6">
            <textarea name="gameDesc" id="gameDesc" class="form-control"
                      placeholder="GameXYZ Description" 
                      rows="5" required></textarea>
          </div>
        </div>

        <div class="form-group">
          <label for="gamePrice" class="col-sm-1 control-label">Price</label>
          <div class="col-sm-6">
            <div class="input-group">
              <span class="input-group-addon" id="poundsymbol">&pound;</span>
              <input type="number" name="gamePrice" id="gamePrice" maxlength="3"
                     min="0" max="100" step="0.01" class="form-control" 
                     placeholder="35.00"required />
            </div>
          </div>
        </div>

        <div class="form-group">
          <label for="gameGenre" class="col-sm-1 control-label">Genre</label>
          <div class="col-sm-6">
            <select id="gameGenre" name="gameGenre" class="form-control">
              <c:forEach items="${genres}" var="genre">
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
                <option value="${platform}">
                  ${platform}
                </option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="page-header">
          <h3>Promotion & Stock Information</h3>
        </div>

        <div class="form-group">
          <label for="gameStock" class="col-sm-1 control-label">Stock</label>
          <div class="col-sm-6">
            <input type="number" name="gameStock" id="gameStock" maxlength="3"
                   min="0" max="100" class="form-control"
                   placeholder="20" required />
          </div>
        </div>

        <div class="form-group">
          <label for="gameDiscount" class="col-sm-1 control-label">Discount</label>
          <div class="col-sm-6">
            <div class="input-group">
              <span class="input-group-addon" id="percentsymbol">&#37;</span>
              <input type="number" name="gameDiscount" id="gameDiscount" maxlength="3"
                     min="0" max="100" class="form-control" 
                     placeholder="15.00"required />
            </div>
            <p class="help-block">
              The level of discount you wish to apply as a percentage. Minimum is
              0% and maximum is 100%.
            </p>
          </div>
        </div>

        <div class="form-group">
          <label for="gamePointMult" class="col-sm-1 control-label">Loyalty Points<br/> Multiplier</label>
          <div class="col-sm-6">
            <div class="input-group">
              <span class="input-group-addon" id="discount">x</span>
              <input type="number" name="gamePointMult" 
                     id="gamePointMult" class="form-control" step="0.01"
                     min="1" max="10" placeholder="1" required />
            </div>
            <p class="help-block">
              The amount of points purchasing this game will reward. e.g. A multiplier
              of 5 will reward 5x the normal amount of points. Minimum is 1x and 
              maximum is 10x.
            </p>
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-1 col-sm-6">
            <input type="submit" name="submit" value="Add New Game" class="btn btn-primary" />
          </div>
        </div>
        
      </form>
    </div>
  </div>
</body>