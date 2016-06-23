<#include "includes/head.ftl"> 

  <div class="grid-row">

      <div class="column-two-thirds">
      
          <form action="personal-details" method="post">
              
              <@errors.errorSummary fields=["claimant.title","claimant.firstName","claimant.otherName","claimant.surname","claimant.dobDay","claimant.dobMonth","claimant.dobYear","claimant.nino"] />
              
              <h1 class="heading-large">${content['page.title']}</h1>
				      
              <@components.inputText path="claimant.title" name="title" content="${content['page.question.title']}" value="${(claimant.title)!}" />
              
              <@components.inputText path="claimant.firstName" name="firstName" content="${content['page.question.firstName']}" value="${(claimant.firstName)!}" />

              <@components.inputText path="claimant.otherName" name="otherName" content="${content['page.question.otherName']}" value="${(claimant.otherName)!}" hint="${content['page.question.otherName.hint']}" />              
                          
              <@components.inputText path="claimant.surname" name="surname" content="${content['page.question.surname']}" value="${(claimant.surname)!}" />              

              <@components.dob paths=["claimant.dobDay","claimant.dobMonth","claimant.dobYear"] name="dob" content="${content['page.question.dob']}" day="${(claimant.dobDay)!}" month="${(claimant.dobMonth)!}" year="${(claimant.dobYear)!}" dayText="Day" monthText="Month" yearText="Year" hint="${content['page.question.dob.hint']}" />              
              
              <@components.inputText path="claimant.nino" name="nino" content="${content['page.question.nino']}" value="${(claimant.nino)!}" />

              <@components.submit value="${content['page.question.submit']}" />

          </form>
      </div>
      
      <div class="column-one-third">
        <#include "includes/sidebar-helplines.ftl"> 
      </div>
  </div>

<#include "includes/footer.ftl"> 