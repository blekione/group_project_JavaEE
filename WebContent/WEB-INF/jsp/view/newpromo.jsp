<%@ page session="true"%>
<%--@elvariable id="games" type="domain.Game"--%>

<div class="container-fluid panel panel-default">
  <div class="panel-heading row"><h3 class="panel-title">Marketing Options</h3></div>	
  <div class="panel-body">
    <center>
      <c:forEach items="${games}" var="game">
        ${game.getName()}
      </c:forEach>
    </center>
  </div>
</div>
</div>
</body>