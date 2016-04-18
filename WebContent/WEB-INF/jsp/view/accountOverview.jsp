<%@ page session="true"%>
<%--@elvariable id="customer" type="domain.Customer"--%>
<%--@elvariable id="loyalty" type="loyalty_scheme.LoyaltyScheme"--%>

<title>GG Marketing - Add a New Promotion</title>
<body>

  <div class="container-fluid panel panel-default">
    <div class="panel-heading row"><h3 class="panel-title">Welcome back, ${customer.getFirstName()}</h3></div>	
    <div class="panel-body">
      <div class="form-horizontal panel-body">
        <div class="page-header">
          <h3>Your Personal Information</h3>
        </div>

        <div class="form-group">
          <label for="title" class="col-sm-1 control-label">Title</label>
          <div class="col-sm-6">
            <input type="text" name="title" id="title"
                   class="form-control" value="${customer.getTitle()}" readonly />
          </div>
        </div>

        <div class="form-group">
          <label for="firstName" class="col-sm-1 control-label">First Name</label>
          <div class="col-sm-6">
            <input type="text" name="firstName" id="firstName"
                   class="form-control" value="${customer.getFirstName()}" readonly />
          </div>
        </div>

        <div class="form-group">
          <label for="secondName" class="col-sm-1 control-label">Second Name</label>
          <div class="col-sm-6">
            <input type="text" name="secondName" id="secondName"
                   class="form-control" value="${customer.getSecondName()}" readonly />
          </div>
        </div>

        <div class="form-group">
          <label for="email" class="col-sm-1 control-label">Email Address</label>
          <div class="col-sm-6">
            <input type="text" name="email" id="email"
                   class="form-control" value="${customer.getEmail()}" readonly />
          </div>
        </div>

        <c:choose>
          <c:when test="${loyalty != null}">

            <div class="page-header">
              <h3>Loyalty Information</h3>
            </div>

            <div class="form-group">
              <label for="loyaltyId" class="col-sm-1 control-label">Account Number</label>
              <div class="col-sm-6">
                <input type="text" name="loyaltyId" id="loyaltyId"
                       class="form-control" value="${loyalty.getAccountNumber()}" readonly />
              </div>
            </div>

            <div class="form-group">
              <label for="loyaltyId" class="col-sm-1 control-label">Current Points</label>
              <div class="col-sm-6">
                <input type="text" name="loyaltyId" id="loyaltyId"
                       class="form-control" value="${loyalty.getLoyaltyPoints()}" readonly />
                <p class="help-block">
                  You can earn loyalty points for purchasing games from our store. The more you
                  buy, the more you earn! These can be redeemed when checking out and used instead
                  of your hard earned money.
                </p>
              </div>
            </div>

          </c:when>
          <c:otherwise>


          </c:otherwise>
        </c:choose>

      </div>
    </div>
  </div>
</body>