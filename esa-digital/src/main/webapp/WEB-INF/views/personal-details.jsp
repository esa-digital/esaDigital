
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	      <form action="personal-details" method="post">

        <div class="grid-row">
          <div class="column-two-thirds">

        <div class="form-group">
          <label class="form-label-bold" for="title">Title</label>
          <input class="form-control" type="text" name="title" id="title" maxlength="27" value="${Claimant.title}">
        </div>

        <div class="form-group">
          <label class="form-label-bold" for="firstName">First name</label>
          <input class="form-control" type="text" name="firstName" id="forename" maxlength="27" value="${Claimant.firstName}">
        </div>

         <div class="form-group">
          <label class="form-label-bold" for="otherName">Middle names(s)</label>
          <input class="form-control" type="text" name="otherName" id="otherName" maxlength="27" value="${Claimant.otherName}"">
        </div>

        <div class="form-group">
          <label class="form-label-bold" for="surname">Surname</label>
          <input class="form-control" type="text" name="surname" id="surname" maxlength="27" value="${Claimant.surname}">
        </div>

        <div class="form-group" id="dob">
          <fieldset>
            <legend><span class="form-label-bold">Date of birth</span></legend>

            <div class="form-date" name="formDate">

              <div class="form-group form-group-day">
                <label for="dob-day">Day</label>
                <input type="text" class="form-control" id="dob-day" maxlength="2" min="1" max="31" name="dobDay" value="${Claimant.dobDay}">
              </div>
              <div class="form-group form-group-month">
                <label for="dob-month">Month</label>
                <input type="text" class="form-control" id="dob-month" maxlength="2" min="01" max="12" name="dobMonth" value="${Claimant.dobMonth}">
              </div>
              <div class="form-group form-group-year">
                <label for="dob-year">Year</label>
                <input type="text" class="form-control" id="dob-year" maxlength="4" name="dobYear"  value="${Claimant.dobYear}">
              </div>
            </div>
          </fieldset>
        </div>

        <div class="form-group">
          <label class="form-label-bold" for="nino" id="nino" >National Insurance number</label>
          <input class="form-control" type="text" name="nino" id="nino" maxlength="9" value="${Claimant.nino}">
        </div>

        <input class="button" type="submit" value="Continue">

    </div>

    <div class="column-one-third"></div>
  </div>

 </form>
</body>
</html>