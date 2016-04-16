<%@ page session="true"%>
<%--@elvariable id="games" type="domain.Game"--%>

<title>GG Marketing - Add a New Promotion</title>
<body>
  <div class="container-fluid panel panel-default">
    <div class="panel-heading row"><h3 class="panel-title">Enter New Promotion Details</h3></div>	
    <div class="panel-body">
      <form class="form-horizontal panel-body" role="form" action="marketing" method="POST">
        <input type="hidden" name="action" value="addNewPromo"/>

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
          <label for="discount" class="col-sm-1 control-label">Discount</label>
          <div class="col-sm-6">
            <div class="input-group">
              <span class="input-group-addon" id="percentsymbol">&#37;</span>
              <input type="number" name="discount" id="discount" maxlength="3"
                     min="0" max="100" class="form-control" required />
            </div>
            <p class="help-block">
              The level of discount you wish to apply as a percentage. Minimum is
              0% and maximum is 100%. <b>NOTE:</b> This will overwrite any existing value.
            </p>
          </div>
        </div>

        <div class="form-group">
          <label for="pointMult" class="col-sm-1 control-label">Loyalty Points<br/> Multiplier</label>
          <div class="col-sm-6">
            <div class="input-group">
              <span class="input-group-addon" id="discount">x</span>
              <input type="number" name="pointMult" 
                     id="pointMult" class="form-control" step="0.01" min="1" max="10" required />
            </div>
            <p class="help-block">
              The amount of points purchasing this game will reward. e.g. A multiplier
              of 5 will reward 5x the normal amount of points. Minimum is 1x and 
              maximum is 10x. <b>NOTE:</b> This will overwrite any existing value.
            </p>
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-1 col-sm-6">
            <input type="submit" name="submit" value="Create the Promotion" class="btn btn-primary" />
          </div>
        </div>
      </form>
    </div>
  </div>
</body>