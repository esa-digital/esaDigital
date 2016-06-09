<jsp:include page="includes/head.jsp" />

<form action="personal-details" method="post">

  <div class="grid-row">
      <div class="column-two-thirds">
          
          <h1 class="heading-xlarge">Personal Details</h1>

          <div class="form-group" data-validation="required">
              <label class="form-label-bold" for="title">Title</label>
              <input class="form-control" type="text" name="title" id="title" value="${Claimant.title}">
          </div>
          

          <div class="form-group">
              <label class="form-label-bold" for="firstName">First name</label>
              <input class="form-control" type="text" name="firstName" id="forename" value="${Claimant.firstName}">
          </div>
          
          <div class="form-group">
              <label class="form-label-bold" for="otherName">Middle names(s)</label>
              <input class="form-control" type="text" name="otherName" id="otherName" value="${Claimant.otherName}">
          </div>
          
          <div class="form-group">
              <label class="form-label-bold" for="surname">Surname</label>
              <input class="form-control" type="text" name="surname" id="surname" value="${Claimant.surname}">
          </div>
          
          <div class="form-group" id="dob">
              <fieldset>
                  <legend><span class="form-label-bold">Date of birth</span></legend>
                  <div class="form-date" name="formDate">
                      <div class="form-group form-group-day">
                          <label for="dob-day">Day</label>
                          <input type="text" class="form-control" id="dob-day" name="dobDay" value="${Claimant.dobDay}">
                      </div>
                      <div class="form-group form-group-month">
                          <label for="dob-month">Month</label>
                          <input type="text" class="form-control" id="dob-month" name="dobMonth" value="${Claimant.dobMonth}">
                      </div>
                      <div class="form-group form-group-year">
                          <label for="dob-year">Year</label>
                          <input type="text" class="form-control" id="dob-year" name="dobYear" value="${Claimant.dobYear}">
                      </div>
                  </div>
              </fieldset>
          </div>
          
          <div class="form-group">
              <label class="form-label-bold" for="nino" id="nino">National Insurance number</label>
              <input class="form-control" type="text" name="nino" id="nino" value="${Claimant.nino}">
          </div>
          
          <input id="submit" class="button" type="submit" value="Continue">
      </div>
      
      <div class="column-one-third"></div>
  </div>
</form>

<jsp:include page="includes/footer.jsp" />