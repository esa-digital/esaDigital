<#include "includes/head.ftl"> 
 <#import "/spring.ftl" as spring/>

  <div class="grid-row">

      <div class="column-two-thirds">
      
          <form action="personal-details" method="post">
              
              <h1 class="heading-large">Personal Details</h1>
				

<#macro showErrorAtw path >
     <#if htmlEscape?exists>
       <#assign status = springMacroRequestContext.getBindStatus(path, htmlEscape)>
   <#else>
       <#assign status = springMacroRequestContext.getBindStatus(path)>
   </#if>
   <#if status.error >
          <#list status.errorMessages as error>
            <span class="error-message">
                ${error}
            </span>
        </#list>
   </#if>
</#macro>

				
			<@showErrorAtw 'claimant.title' />
			
				
				
              <@components.inputText name="title" content="Title" value="${(Claimant.title)!}" />
              
              <@components.inputText name="firstName" content="First name" value="${(Claimant.firstName)!}" />

              <@components.inputText name="otherName" content="Middle names(s)" value="${(Claimant.otherName)!}" hint="(optional)" />              
                          
              <@components.inputText name="surname" content="Surname" value="${(Claimant.surname)!}" />              

              <@components.dob name="dob" content="Date of Birth" day="${(Claimant.dobDay)!}" month="${(Claimant.dobMonth)!}" year="${(Claimant.dobYear)!}" dayText="Day" monthText="Month" yearText="Year" hint="For example, 31 3 1980" />              
              
              <@components.inputText name="nino" content="National Insurance number" value="${(Claimant.nino)!}" />

              <@components.submit value="Continue" />              

          </form>
      </div>
      
      <div class="column-one-third">
        <#include "includes/sidebar-helplines.ftl"> 
      </div>
  </div>

<#include "includes/footer.ftl"> 