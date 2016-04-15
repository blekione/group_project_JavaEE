<%@ page session="true"%>
<%--@elvariable id="games" type="domain.Game"--%>
<%--@elvariable id="success" type="java.lang.Boolean"--%>

<div class="container-fluid panel panel-default">
  <div class="panel-heading row"><h3 class="panel-title">Marketing Options</h3></div>	
  <div class="panel-body">
    <center>
      
      <c:if test="${success}">
	<div class="container alert alert-success">
	  <p>That action was successful. All changes have been made and the database updated.</p>
	</div>			
      </c:if>

      <a href="?action=newGame">Add New Game</a> <br />
      <a href="?action=newPromo">Add New Promotion</a> <br />
      <a href="?action=stats">View Store Statistics</a>
    </center>
  </div>
</div>
</div>
</body>