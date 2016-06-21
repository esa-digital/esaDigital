
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GP-details</title>
</head>
<body>
	      <form action="gp-details" method="post">

        <div class="grid-row">
          <div class="column-two-thirds">

        <div class="form-group">
          <label class="form-label-bold" for="doctorName">Doctor's name</label>
          <input class="form-control" type="text" name="doctorName" id="doctorName" maxlength="27" value= "<#if (GPDetails.doctorName)??>${GPDetails.doctorName}<#else>""</#if>">
        </div>

        <div class="form-group">
          <label class="form-label-bold" for="docAddLine1">Address Line 1</label>
          <input class="form-control" type="text" name="docAddLine1" id="docAddLine1" maxlength="27" value="<#if (GPDetails.docAddLine1)??>${GPDetails.docAddLine1}<#else>""</#if>">
        </div>

         <div class="form-group">
          <label class="form-label-bold" for="docAddLine2">Address Line 2</label>
          <input class="form-control" type="text" name="docAddLine2" id="docAddLine2" maxlength="27" value="<#if (GPDetails.docAddLine2)??>${GPDetails.docAddLine2}<#else>""</#if>">
        </div>

         <div class="form-group">
          <label class="form-label-bold" for="docAddLine3">Address Line 3</label>
          <input class="form-control" type="text" name="docAddLine3" id="docAddLine3" maxlength="27" value="<#if (GPDetails.docAddLine3)??>${GPDetails.docAddLine3}<#else>""</#if>">
        </div>

         <div class="form-group">
          <label class="form-label-bold" for="docAddLine4">Address Line 4</label>
          <input class="form-control" type="text" name="docAddLine4" id="docAddLine4" maxlength="27" value="<#if (GPDetails.docAddLine4)??>${GPDetails.docAddLine4}<#else>""</#if>">
        </div>

        <div class="form-group">
          <label class="form-label-bold" for="docPostCode">Postcode</label>
          <input class="form-control" type="text" name="docPostCode" id="docPostCode" maxlength="9" value="<#if (GPDetails.docPostCode)??>${GPDetails.docPostCode}<#else>""</#if>">
        </div>

        <div class="form-group">
          <label class="form-label-bold" for="docTelNumber" id="docTelNumber">Doctor Telephone Number</label>
          <input class="form-control" type="text" name="docTelNumber" id="docTelNumber" maxlength="27" value="<#if (GPDetails.docTelNumber)??>${GPDetails.docTelNumber}<#else>""</#if>">
        </div>
        
        <input class="button" type="submit" value="Next">

    </div>

    <div class="column-one-third"></div>
  </div>

 </form>
</body>
</html>

