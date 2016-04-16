<%@ page session="true"%>
<%--@elvariable id="customers" type="domain.Customer"--%>

<title>GG Marketing - Email a Customer</title>
<body>
  
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li><a href="marketing">Marketing Home</a></li>
            <li><a href="?action=newGame">Add New Game</a></li>
            <li><a href="?action=listGames">Edit Existing Game</a></li>
            <li><a href="?action=newPromo">Start New Promotion</a></li>
            <li class="active"><a href="#">Email Customer</a></li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
  
  <div class="container-fluid panel panel-default">
    <div class="panel-heading row"><h3 class="panel-title">Enter the Email Information</h3></div>	
    <div class="panel-body">
      <form class="form-horizontal panel-body" role="form" action="marketing" method="POST">
        <input type="hidden" name="action" value="sendEmail"/>

        <div class="form-group">
          <label for="selectCustomer" class="col-sm-1 control-label">Customer</label>
          <div class="col-sm-6">
            <div class="input-group">
              <span class="input-group-addon" id="atsymbol">&#64;</span>
              <select id="selectCustomer" name="barcode" class="form-control">
                <c:forEach items="${customers}" var="customer">
                  <option value="${customer.getEmail()}">
                    ${customer.getTitle()} &#32; ${customer.getFirstName()} &#32;
                    ${customer.getSecondName()} - ${customer.getEmail()}
                  </option>
                </c:forEach>
              </select>
            </div>
          </div>
        </div>

        <div class="form-group">
          <label for="emailSubject" class="col-sm-1 control-label">Subject</label>
          <div class="col-sm-6">
            <input type="text" name="emailSubject" id="emailSubject" 
                   class="form-control" placeholder="Email Subject" required />
            <p class="help-block">
          </div>
        </div>

        <div class="form-group">
          <label for="emailBody" class="col-sm-1 control-label">Body</label>
          <div class="col-sm-6">
            <textarea name="emailBody" id="emailBody" class="form-control"
                      placeholder="Email Contents" 
                      rows="5" required></textarea>
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-1 col-sm-6">
            <input type="submit" name="submit" value="Send Email" class="btn btn-primary" />
          </div>
        </div>

      </form>
    </div>
  </div>
</body>