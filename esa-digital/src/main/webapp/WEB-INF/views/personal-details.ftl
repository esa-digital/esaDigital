<#include "includes/head.ftl"> 

  <div class="grid-row">

      <div class="column-two-thirds">
          <form action="personal-details" method="post">
              
              <div class="error-summary visually-hidden" role="group" aria-labelledby="error-summary-heading-example-1" tabindex="-1">
            
                  <h1 class="heading-medium error-summary-heading" id="error-summary-heading-example-1">Message to alert the user to a problem goes here</h1>
                  <p>Optional description of the errors and how to correct them</p>
                  
                  <ul class="error-summary-list"></ul>
              </div>

              <h1 class="heading-large">Personal Details</h1>

              <div class="form-group">
                  <label class="form-label-bold" for="title">Title</label>
                  <input class="form-control" type="text" name="title" id="title" value="<#if (Claimant.title)??>${Claimant.title}<#else>""</#if>">
              </div>
              

              <div class="form-group">
                  <label class="form-label-bold" for="firstName">First name</label>
                  <input class="form-control" type="text" name="firstName" id="forename" value="<#if (Claimant.firstName)??>${Claimant.firstName}<#else>""</#if>">
              </div>
              
              <div class="form-group">
                  <label class="form-label-bold" for="otherName">Middle names(s) <span class="form-hint">(optional)</span></label>
                  <input class="form-control" type="text" name="otherName" id="otherName" value="<#if (Claimant.otherName)??>${Claimant.otherName}<#else>""</#if>">
              </div>
              
              <div class="form-group">
                  <label class="form-label-bold" for="surname">Surname</label>
                  <input class="form-control" type="text" name="surname" id="surname" value="<#if (Claimant.surname)??>${Claimant.surname}<#else>""</#if>">
              </div>
              
              <div class="form-group" id="dob">
                  <fieldset>
                      <legend><span class="form-label-bold">Date of birth</span>
                          <span class="form-hint" id="dob-hint">For example, 31 3 1980</span>
                      </legend>
                      <div class="form-date" name="formDate">
                          <div class="form-group form-group-day">
                              <label for="dob-day">Day</label>
                              <input class="form-control" id="dob-day" name="dobDay" type="number" pattern="[0-9]*" min="0" max="31" aria-describedby="dob-hint" value="<#if (Claimant.dobDay)??>${Claimant.dobDay}<#else>""</#if>">
                          </div>
                          <div class="form-group form-group-month">
                              <label for="dob-month">Month</label>
                              <input class="form-control" id="dob-month" name="dobMonth" type="number" pattern="[0-9]*" min="0" max="12" value="<#if (Claimant.dobMonth)??>${Claimant.dobMonth}<#else>""</#if>">
                          </div>
                          <div class="form-group form-group-year">
                              <label for="dob-year">Year</label>
                              <input class="form-control" id="dob-year" name="dobYear" type="number" pattern="[0-9]*" min="0" max="2016" value="<#if (Claimant.dobYear)??>${Claimant.dobYear}<#else>""</#if>">
                          </div>
                      </div>
                  </fieldset>
              </div>
              
              <div class="form-group">
                  <label class="form-label-bold" for="nino" id="nino">National Insurance number</label>
                  <input class="form-control" type="text" name="nino" id="nino" value="<#if (Claimant.nino)??>${Claimant.nino}<#else>""</#if>">
              </div>
              
              <input id="submit" class="button" type="submit" value="Continue">

          </form>
      </div>
      
      <div class="column-one-third">
        <#include "includes/sidebar-helplines.ftl"> 
      </div>
  </div>

<#include "includes/footer.ftl"> 