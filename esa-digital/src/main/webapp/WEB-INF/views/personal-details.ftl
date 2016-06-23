<#include "includes/head.ftl"> 

  <div class="grid-row">

      <div class="column-two-thirds">
      
          <form action="personal-details" method="post">
              
              <@errors.errorSummary fields=["claimant.title","claimant.firstName","claimant.otherName","claimant.surname","claimant.dobDay","claimant.dobMonth","claimant.dobYear","claimant.nino"] />
              
              <h1 class="heading-large">Personal Details</h1>
				      
              <@components.inputText path="claimant.title" name="title" content="Title" value="${(claimant.title)!}" />
              
              <@components.inputText path="claimant.firstName" name="firstName" content="First name" value="${(claimant.firstName)!}" />

              <@components.inputText path="claimant.otherName" name="otherName" content="Middle names(s)" value="${(claimant.otherName)!}" hint="(optional)" />              
                          
              <@components.inputText path="claimant.surname" name="surname" content="Surname" value="${(claimant.surname)!}" />              

              <@components.dob paths=["claimant.dobDay","claimant.dobMonth","claimant.dobYear"] name="dob" content="Date of Birth" day="${(claimant.dobDay)!}" month="${(claimant.dobMonth)!}" year="${(claimant.dobYear)!}" dayText="Day" monthText="Month" yearText="Year" hint="DD MM YYYY" />              
              
              <@components.inputText path="claimant.nino" name="nino" content="National Insurance number" value="${(claimant.nino)!}" />

              <@components.submit value="Continue" />              

          </form>
      </div>
      
      <div class="column-one-third">
        <#include "includes/sidebar-helplines.ftl"> 
      </div>
  </div>

<#include "includes/footer.ftl"> 