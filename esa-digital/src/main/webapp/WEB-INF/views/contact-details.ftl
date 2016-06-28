<#include "includes/head.ftl"> 

  <div class="grid-row">
      
      <div class="column-two-thirds">
        
        <form action="contact-details" method="post" autocomplete="off">

          <@errors.errorSummary fields=["contactDetails.addressLine1","contactDetails.addressLine2","contactDetails.addressLine3","contactDetails.addressLine4","contactDetails.postCode","contactDetails.phoneNumber","contactDetails.otherNumber","contactDetails.email"] />

          <h1 class="form-title heading-large">${content['page.title']}</h1>
          <p class="lede">${content['page.intro']}</p>

		  <@components.address paths=["contactDetails.addressLine1","contactDetails.addressLine2","contactDetails.addressLine3", "contactDetails.addressLine4", "contactDetails.postCode"] 
		  					   name="address" 
		  					   content="${content['page.question.address']}"
		  					   contentPostcode="${content['page.question.postcode']}" 
		  					   addr1="${(contactDetails.addressLine1)!}" 
		  					   addr2="${(contactDetails.addressLine2)!}" 
		  					   addr3="${(contactDetails.addressLine3)!}" 
		  					   addr4="${(contactDetails.addressLine4)!}" 
		  					   postcode="${(contactDetails.postCode)!}" 
		  					   hint="${content['page.question.address.hint']}" 
                   			   addrBinding="addressLine" />
          

		  <@components.inputText path="contactDetails.phoneNumber" name="phoneNumber" content="${content['page.question.phoneNumber']}" value="${(contactDetails.phoneNumber)!}" />
          
          <@components.inputText path="contactDetails.otherNumber" name="otherNumber" content="${content['page.question.otherNumber']}" value="${(contactDetails.otherNumber)!}" />

		  <@components.inputText path="contactDetails.email" name="email" content="${content['page.question.email']}" value="${(contactDetails.email)!}" />
          
          <@components.submit value="${content['page.question.submit']}" />

        </form>
      </div>
      
      <div class="column-one-third">
        <#include "includes/sidebar-helplines.ftl"> 
      </div>
  </div>

<#include "includes/footer.ftl"> 