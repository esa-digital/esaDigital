<jsp:include page="../includes/head.jsp" />

  <div class="grid-row">
      <div class="column-two-thirds">
          <form action="personal-details" method="post">
  
              <h1 class="heading-large">Personal Details</h1>

              <div class="form-group" data-validation="required maxLength27 alpha">
                  <label class="form-label-bold" for="title">Title</label>
                  <input class="form-control" type="text" name="title" id="title" value="${Claimant.title}">
              </div>
              

              <div class="form-group" data-validation="required maxLength27 alpha">
                  <label class="form-label-bold" for="firstName">First name</label>
                  <input class="form-control" type="text" name="firstName" id="forename" value="${Claimant.firstName}">
              </div>
              
              <div class="form-group" data-validation="maxLength27 alpha">
                  <label class="form-label-bold" for="otherName">Middle names(s) <span class="form-hint">(optional)</span></label>
                  <input class="form-control" type="text" name="otherName" id="otherName" value="${Claimant.otherName}">
              </div>
              
              <div class="form-group" data-validation="required maxLength27 alpha">
                  <label class="form-label-bold" for="surname">Surname</label>
                  <input class="form-control" type="text" name="surname" id="surname" value="${Claimant.surname}">
              </div>
              
              <div class="form-group" id="dob">
                  <fieldset>
                      <legend><span class="form-label-bold">Date of birth</span>
                          <span class="form-hint" id="dob-hint">For example, 31 3 1980</span>
                      </legend>
                      <div class="form-date" name="formDate">
                          <div class="form-group form-group-day">
                              <label for="dob-day">Day</label>
                              <input class="form-control" id="dob-day" name="dobDay" type="number" pattern="[0-9]*" min="0" max="31" aria-describedby="dob-hint" value="${Claimant.dobDay}">
                          </div>
                          <div class="form-group form-group-month">
                              <label for="dob-month">Month</label>
                              <input class="form-control" id="dob-month" name="dobMonth" type="number" pattern="[0-9]*" min="0" max="12" value="${Claimant.dobMonth}">
                          </div>
                          <div class="form-group form-group-year">
                              <label for="dob-year">Year</label>
                              <input class="form-control" id="dob-year" name="dobYear" type="number" pattern="[0-9]*" min="0" max="2016" value="${Claimant.dobYear}">
                          </div>
                      </div>
                  </fieldset>
              </div>
              
              <div class="form-group" data-validation="required nino">
                  <label class="form-label-bold" for="nino" id="nino">National Insurance number</label>
                  <input class="form-control" type="text" name="nino" id="nino" value="${Claimant.nino}">
              </div>
              
              <input id="submit" class="button" type="submit" value="Continue">

          </form>
      </div>
      
      <div class="column-one-third">
        <jsp:include page="../includes/sidebar-helplines.jsp" />
      </div>
  </div>

<jsp:include page="../includes/footer.jsp" />