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
              
              <label class="form-label"><input type="text" class="form-control" id="address-line1"></label>
              <label class="form-label"><input type="text" class="form-control" id="address-line2"></label>
              <label class="form-label"><input type="text" class="form-control" id="address-line3"></label>
              
              <div class="form-group">
                <label class="form-label" for="postcode">Postcode</label>
                <input type="text" class="form-control form-control-1-4" id="postcode">
              </div>

            </fieldset>
          </div>

          <div class="form-group">
            <label class="form-label-bold" for="phone">Phone number <span class="form-hint">(optional)</span></label>
            <input type="text" class="form-control" id="phone">
          </div>

          <details class="form-group" role="group">
            <summary role="button" aria-controls="details-content-0" aria-expanded="true"><span class="summary">You need to explain more about how to contact you on this number</span></summary>

            <div class="panel panel-border-narrow" id="details-content-0" aria-hidden="false">
              <div class="form-group">
                <fieldset class="">
                  <legend class="form-label-bold" for="have-mortgage">Select the one that applies to you</legend>
                  <label class="block-label" for="radio-inline-20">
                    <input id="radio-inline-20" type="radio" name="have-mortgage" value="Daily">
                    Text only (no voice calls)
                  </label>
                  <label class="block-label" for="radio-inline-21">
                    <input id="radio-inline-21" type="radio" name="have-mortgage" value="Weekly">
                    Voice only (no text)
                  </label>
                </fieldset>
            </div>
          </div></details>

          <div class="form-group">
            <label class="form-label-bold" for="phone2">Other number <span class="form-hint">(optional)</span></label>
            <input type="text" class="form-control" id="phone2">
          </div>

          <details class="form-group" role="group">
            <summary role="button" aria-controls="details-content-1" aria-expanded="false">
              <span class="summary">You need to explain more about how to contact you on this number</span>
            </summary>

            <div class="panel panel-border-narrow" id="details-content-1" aria-hidden="true">
              <div class="form-group">
                <fieldset class="">
                  <legend class="form-label-bold" for="have-mortgage2">Select the one that applies to you</legend>
                  <label class="block-label" for="radio-inline-202">
                    <input id="radio-inline-202" type="radio" name="have-mortgage2" value="Daily">
                    Text only (no voice calls)
                  </label>
                  <label class="block-label" for="radio-inline-212">
                    <input id="radio-inline-212" type="radio" name="have-mortgage2" value="Weekly">
                    Voice only (no text)
                  </label>
                </fieldset>
            </div>
          </div></details>

          <div class="form-group">
            <label class="form-label-bold" for="email">Email <span class="form-hint">(optional)</span></label>

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