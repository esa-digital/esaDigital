<#include "includes/head.ftl"> 

  <div class="grid-row">
      
      <div class="column-two-thirds">
        
        
        <form action="contact-details" method="post">

          <div class="error-summary visually-hidden" role="group" aria-labelledby="error-summary-heading-example-1" tabindex="-1">
            
            <h1 class="heading-medium error-summary-heading" id="error-summary-heading-example-1">Message to alert the user to a problem goes here</h1>
            <p>Optional description of the errors and how to correct them</p>
            
            <ul class="error-summary-list"></ul>
          </div>

          <h1 class="form-title heading-large">Contact details</h1>
          <p class="lede">Updates about your application will be sent to you by letter, text message and email.</p>

          <div class="form-group">
            
            <h2 class="form-title heading-medium">Address</h2>
            <p>Enter an address where you want letters about your claim to be posted.</p>

            <fieldset>

              <legend class="visuallyhidden">Address</legend>
              
              <label class="form-label"><input type="text" class="form-control" id="address-line1" value="<#if (ContactDetails.addressLine1)??>${ContactDetails.addressLine1}<#else>""</#if>"></label>
              <label class="form-label"><input type="text" class="form-control" id="address-line2" value="<#if (ContactDetails.addressLine2)??>${ContactDetails.addressLine2}<#else>""></label>
              <label class="form-label"><input type="text" class="form-control" id="address-line3" value="<#if (ContactDetails.addressLine3)??>${ContactDetails.addressLine3}<#else>""></label>
              <label class="form-label"><input type="text" class="form-control" id="address-line4" value="<#if (ContactDetails.addressLine4)??>${ContactDetails.addressLine4}<#else>""></label>
              
              <div class="form-group">
                <label class="form-label" for="postcode" value="<#if (ContactDetails.postCode)??>${ContactDetails.postCode}<#else>"">Postcode</label>
                <input type="text" class="form-control form-control-1-4" id="postcode">
              </div>

            </fieldset>
          </div>

          <div class="form-group">
            <label class="form-label-bold" for="phone" value="<#if (ContactDetails.phoneNumber)??>${ContactDetails.phoneNumber}<#else>"">Phone number <span class="form-hint">(optional)</span></label>
            <input type="text" class="form-control" id="phone">
          </div>

         

          <div class="form-group">
            <label class="form-label-bold" for="phone2" value="<#if (ContactDetails.otherNumber)??>${ContactDetails.otherNumber}<#else>"">Other number <span class="form-hint">(optional)</span></label>
            <input type="text" class="form-control" id="phone2">
          </div>


          <div class="form-group">
            <label class="form-label-bold" for="email" value="<#if (ContactDetails.email)??>${ContactDetails.email}<#else>"">Email <span class="form-hint">(optional)</span></label>
            <input type="text" class="form-control" id="email">
          </div>

          <div class="form-group">
            <input type="submit" class="button" value="Continue">
          </div>
        </form>
      </div>
      
      <div class="column-one-third">
        <#include "includes/sidebar-helplines.ftl"> 
      </div>
  </div>

<#include "includes/footer.ftl"> 