<#include "includes/head.ftl">
<div class="grid-row">
    <div class="column-two-thirds">
        <form action="help-details" method="post" autocomplete="off">
        	
        	<@errors.errorSummary fields=["helpDetails.helpDetailsType",
        								  "helpDetails.thirdPartyDetails.personName",
        								  "helpDetails.thirdPartyDetails.personRelation",
        								  "helpDetails.thirdPartyDetails.reasonForHelp",
        								  "helpDetails.thirdPartyDetails.behalfOfType",
        								  "helpDetails.thirdPartyDetails.firstName",
        								  "helpDetails.thirdPartyDetails.surname",
        								  "helpDetails.thirdPartyDetails.title",
        								  "helpDetails.thirdPartyDetails.otherName",
        								  "helpDetails.thirdPartyDetails.dobDay",
        								  "helpDetails.thirdPartyDetails.dobMonth",
        								  "helpDetails.thirdPartyDetails.dobYear",
        								  "helpDetails.thirdPartyDetails.nino",
        								  "helpDetails.thirdPartyDetails.telNumber",
        								  "helpDetails.thirdPartyDetails.addLine1",
        								  "helpDetails.thirdPartyDetails.addLine2",
        								  "helpDetails.thirdPartyDetails.addLine3",
        								  "helpDetails.thirdPartyDetails.addLine4",
        								  "helpDetails.thirdPartyDetails.postCode"] />

            <h1 class="heading-large">${content['page.title']}</h1>
        	
        	<div class="form-group<@errors.hasError "helpDetails.helpDetailsType" />">
	            <fieldset>
	        
	                <legend class="form-label">
						${content['page.question.areYou']}
						<@errors.listInlineErrors "helpDetails.helpDetailsType"/>
					</legend>
	                
	                <div id="helpDetailsType">
	                    
	                    <@components.inputRadio id="self" value="self" group="helpDetailsType" content="${content['page.question.radio.self']}" selected="${(helpDetails.helpDetailsType)!}" />
	
	                    <@components.inputRadio id="help-from-third-party" value="thirdPartyHelp" group="helpDetailsType" content="${content['page.question.radio.thirdParty']}" target="third-party" selected="${(helpDetails.helpDetailsType)!}" />
	                    
	                    <@components.inputRadio id="someone-else" value="behalfType" group="helpDetailsType" content="${content['page.question.radio.someoneElse']}" target="appointee" selected="${(helpDetails.helpDetailsType)!}" />
	                    
	                    <div class="panel panel-border-narrow <#if (helpDetails.helpDetailsType)! != "thirdPartyHelp">js-hidden</#if>" id="third-party">
	                        
	                        <h2 class="heading-medium js-hidden">If you selected Are you getting help from a friend, relative or third party such as a charity</h2>
	                        
	                        <@components.inputText name="thirdPartyDetails.personName" content="${content['page.question.personName']}" value="${(thirdPartyDetails.personName)!}" path="helpDetails.thirdPartyDetails.personName" />
	              			
	              			<@components.inputText name="thirdPartyDetails.personRelation" content="${content['page.question.relation']}" value="${(thirdPartyDetails.personRelation)!}" path="helpDetails.thirdPartyDetails.personRelation" />
							
							<@components.textarea name="thirdPartyDetails.reasonForHelp" content="${content['page.question.reasonForHelp']}" value="${(thirdPartyDetails.reasonForHelp)!}" path="helpDetails.thirdPartyDetails.reasonForHelp" />
	                    </div>
	                    
	                    <div class="panel panel-border-narrow <#if (helpDetails.helpDetailsType)! != "behalfType">js-hidden</#if>" id="appointee">
	                        
	                        <h2 class="heading-medium js-hidden">If you selected Are you applying on behalf of someone else</h2>
	                        
	                        
	                        <fieldset class="form-group<@errors.hasError "helpDetails.thirdPartyDetails.behalfOfType" />" id="behalfOfType">
	                            
	                            <legend class="form-label-bold">${content['page.question.someoneElse']}</legend>
	                            <@errors.listInlineErrors "helpDetails.thirdPartyDetails.behalfOfType"/>

	                            <!--Power of attorney-->
	                            <@components.inputRadio id="powerOfAttourney" value="powerOfAttourney" group="thirdPartyDetails.behalfOfType" content="${content['page.question.radio.poa']}" target="power-of-attorney" selected="${(helpDetails.thirdPartyDetails.behalfOfType)!}"/>
	                            
	                            <!--Deputy-->
		                        <@components.inputRadio id="courtOfLaw" value="courtOfLaw" group="thirdPartyDetails.behalfOfType" content="${content['page.question.radio.deputy']}" target="deputy" selected="${(helpDetails.thirdPartyDetails.behalfOfType)!}"/>
		                        
		                        <!--Appointee-->
		                        <@components.inputRadio id="appointeeCheck" value="appointeeCheck" group="thirdPartyDetails.behalfOfType" content="${content['page.question.radio.appointeeCheck']}" target="registeredAppointee" selected="${(helpDetails.thirdPartyDetails.behalfOfType)!}"/>
		                        
	
		                        <!--Own affiars-->
		                        <@components.inputRadio id="ownAffairs" value="ownAffairs" group="thirdPartyDetails.behalfOfType" content="${content['page.question.radio.ownAffairs']}" target="filling-in" selected="${(helpDetails.thirdPartyDetails.behalfOfType)!}"/>
	                            
	                            
		                        <!--Other-->
	                            <@components.inputRadio id="otherPerson" value="otherPerson" group="thirdPartyDetails.behalfOfType" content="${content['page.question.radio.other']}" target="other" selected="${(helpDetails.thirdPartyDetails.behalfOfType)!}"/>
	                            
	                        </fieldset>
	                        
	                        <div class="panel panel-border-narrow <#if (helpDetails.thirdPartyDetails.behalfOfType)! != "appointeeCheck">js-hidden</#if>" id="registeredAppointee">
	                            <h2 class="heading-medium">${content['page.appointeeCheck.title']}</h2>
	                            <@components.inputText name="thirdPartyDetails.appointeeFirstName" content="${content['page.appointeeCheck.forename']}" value="${(helpDetails.thirdPartyDetails.appointeeFirstName)!}" path="helpDetails.thirdPartyDetails.appointeeFirstName" />
	                            <@components.inputText name="thirdPartyDetails.appointeeSurname" content="${content['page.appointeeCheck.surname']}" value="${(helpDetails.thirdPartyDetails.appointeeSurname)!}" path="helpDetails.thirdPartyDetails.appointeeSurname" />
	                        </div>
	                        
	                        <div class="panel panel-border-narrow heading <#if (helpDetails.thirdPartyDetails.behalfOfType)! != "powerOfAttourney">js-hidden</#if>" id="power-of-attorney">
	                            <h2 class="heading-medium">${content['page.poa.title']}</h2>    
	                        </div>
	                        <div class="panel panel-border-narrow heading <#if (helpDetails.thirdPartyDetails.behalfOfType)! != "courtOfLaw">js-hidden</#if>" id="deputy">
		                        <h2 class="heading-medium">${content['page.deputy.title']}</h2>
		                    </div>
	                        <div class="panel panel-border-narrow heading <#if (helpDetails.thirdPartyDetails.behalfOfType)! != "ownAffairs">js-hidden</#if>" id="filling-in">
	                            <h2 class="heading-medium">${content['page.ownAffairs.title']}</h2>
	                        </div>
	                        <div class="panel panel-border-narrow heading js-hidden">
	                            <h2 class="heading-medium">or</h2>
	                        </div>
	                        <div class="panel panel-border-narrow heading <#if (helpDetails.thirdPartyDetails.behalfOfType)! != "otherPerson">js-hidden</#if>" id="other">
	                            <h2 class="heading-medium">${content['page.other.title']}</h2>
	                        </div>
	                        <div class="panel panel-border-narrow <#if ((helpDetails.thirdPartyDetails.behalfOfType)!"appointeeCheck") == "appointeeCheck">js-hidden</#if>" id="power-of-attorney-deputy-filling-in-other">
								
								<@components.inputText name="thirdPartyDetails.title" content="${content['page.question.title']}" value="${(helpDetails.thirdPartyDetails.title)!}" path="helpDetails.thirdPartyDetails.title" />
		
								<@components.inputText name="thirdPartyDetails.firstName" content="${content['page.question.firstName']}" value="${(helpDetails.thirdPartyDetails.firstName)!}" path="helpDetails.thirdPartyDetails.firstName" />
							
								<@components.inputText name="thirdPartyDetails.surname" content="${content['page.question.surname']}" value="${(helpDetails.thirdPartyDetails.surname)!}" path="helpDetails.thirdPartyDetails.surname" />
								
								<@components.inputText name="thirdPartyDetails.otherName" content="${content['page.question.otherName']}" value="${(helpDetails.thirdPartyDetails.otherName)!}" path="helpDetails.thirdPartyDetails.otherName" />
							
								<@components.dob paths=["helpDetails.thirdPartyDetails.dobDay","helpDetails.thirdPartyDetails.dobMonth","helpDetails.thirdPartyDetails.dobYear"] 
									  name="dob" 
									  content="${content['page.question.dob']}" 
									  day="${(helpDetails.thirdPartyDetails.dobDay)!}" 
									  month="${(helpDetails.thirdPartyDetails.dobMonth)!}" 
									  year="${(helpDetails.thirdPartyDetails.dobYear)!}" 
									  dayText="${content['page.question.dobDay']}" 
									  monthText="${content['page.question.dobMonth']}" 
									  yearText="${content['page.question.dobYear']}" 
									  hint="${content['page.question.dob.hint']}" 
									  binding="thirdPartyDetails."/>
								
								<@components.inputText name="thirdPartyDetails.nino" path="helpDetails.thirdPartyDetails.nino" content="${content['page.question.nino']}" value="${(helpDetails.thirdPartyDetails.nino)!}" />
								
								<@components.inputText name="thirdPartyDetails.telNumber" path="helpDetails.thirdPartyDetails.telNumber" content="${content['page.question.telNumber']}" value="${(helpDetails.thirdPartyDetails.telNumber)!}" />
							
								<@components.address paths=["helpDetails.thirdPartyDetails.addLine1","helpDetails.thirdPartyDetails.addLine2","helpDetails.thirdPartyDetails.addLine3","helpDetails.thirdPartyDetails.addLine4","helpDetails.thirdPartyDetails.postCode"] 
										  name="address" 
										  content=content['page.question.address'] 
										  contentPostcode=content['page.question.postcode']
										  addr1="${(helpDetails.thirdPartyDetails.addLine1)!}" 
										  addr2="${(helpDetails.thirdPartyDetails.addLine2)!}" 
										  addr3="${(helpDetails.thirdPartyDetails.addLine3)!}" 
										  addr4="${(helpDetails.thirdPartyDetails.addLine4)!}" 
										  postcode="${(helpDetails.thirdPartyDetails.postCode)!}"
										  addrBinding="thirdPartyDetails.addLine"
										  postcodeBinding="thirdPartyDetails." />
								
							</div>
	                    </div>
	                </div>
	            </fieldset>
	        </div>
            
			<@components.submit value="Continue" />
        </form>
    </div>
    <div class="column-one-third">
        <#include "includes/sidebar-helplines.ftl">
    </div>
</div>
<#include "includes/footer.ftl">
