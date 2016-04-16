<%@ page session="true"%>
<%--@elvariable id="games" type="domain.Game"--%>
<%--@elvariable id="success" type="java.lang.Boolean"--%>

<title>GG Marketing - Landing</title>
<body>

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Marketing Home</a></li>
            <li><a href="?action=newGame">Add New Game</a></li>
            <li><a href="?action=listGames">Edit Existing Game</a></li>
            <li><a href="?action=newPromo">Start New Promotion</a></li>
            <li><a href="?action=emailCustomer">Email Customer</a></li>
          </ul>
        </div>
      </div>
    </div>
  </nav>

  <div class="container-fluid panel panel-default">
    <div class="panel-heading row">
      <h3 class="panel-title">Marketing Options</h3></div>	
    <div class="panel-body">
      <center>

        <c:if test="${success}">
          <div class="container alert alert-success">
            <p>
              That action was successful. All changes have been made and
              their changes reflected.
            </p>
          </div>			
        </c:if>

        <div class="col-sm-8 col-md-2">
          <div class="thumbnail">
            <a href="?action=newGame">
              <img src="resources/images/marketing/add.png" alt="Add New Game Image">
              <div class="btn-group btn-group-justified" role="group">
                <div class="btn-group">
                  <br />
                  <button type="button" class="btn btn-primary">Add a New Game</button>
                </div>
              </div>
            </a>
          </div>
        </div>

        <div class="col-sm-8 col-md-2">
          <a href="?action=listGames">
            <div class="thumbnail">
              <img src="resources/images/marketing/edit.png" alt="Edit Exsting Game Image">
              <div class="btn-group btn-group-justified" role="group">
                <div class="btn-group">
                  <br />
                  <button type="button" class="btn btn-primary">Edit an Existing Game</button>
                </div>
              </div>
            </div>
          </a>
        </div>

        <div class="col-sm-8 col-md-2">
          <a href="?action=newPromo">
            <div class="thumbnail">
              <img src="resources/images/marketing/promo.png" alt="Add New Promotion Image">
              <div class="btn-group btn-group-justified" role="group">
                <div class="btn-group">
                  <br />
                  <button type="button" class="btn btn-primary">Start a New Promotion</button>
                </div>
              </div>
            </div>
          </a>
        </div>

        <div class="col-sm-8 col-md-2">
          <a href="?action=emailCustomer">
            <div class="thumbnail">
              <img src="resources/images/marketing/email.png" alt="Email User Image">
              <div class="btn-group btn-group-justified" role="group">
                <div class="btn-group">
                  <br />
                  <button type="button" class="btn btn-primary">Email a Customer</button>
                </div>
              </div>
            </div>
          </a>
        </div>

      </center>
    </div>
  </div>
</body>