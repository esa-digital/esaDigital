<#macro inputText name content value hint="">
	<div class="form-group" id="${name}">
	  <label class="form-label-bold" for="${name}">${content} 
	  	<#if hint?has_content>
	  		<span class="form-hint">${hint}</span>
	  	</#if>
	  </label>
	  <input class="form-control" type="text" name="${name}" value="${value}">
	</div>
</#macro>

<#macro dob name content day month year dayText monthText yearText hint>
	<div class="form-group" id="${name}">
		<fieldset>
			<legend>
				 <span class="form-label-bold">${content}</span>
				 <span class="form-hint">${hint}</span>
			</legend>
			<div class="form-date" name="formDate">
			  <div class="form-group form-group-day">
			      <label for="dob-day">${dayText}</label>
			      <input class="form-control" type="number" name="dobDay" value="$(day)" id="dob-day" pattern="[0-9]*" min="0" max="31">
			  </div>
			  <div class="form-group form-group-month">
			      <label for="dob-month">${monthText}</label>
			      <input class="form-control" type="number" name="dobMonth" value="${month}" id="dob-month" pattern="[0-9]*" min="0" max="12">
			  </div>
			  <div class="form-group form-group-year">
			      <label for="dob-year">${yearText}</label>
			      <input class="form-control" type="number" name="dobYear" value="${year}" id="dob-year" pattern="[0-9]*" min="0" max="2016">
			  </div>
			</div>
		</fieldset>
	</div>
</#macro>

<#macro submit value>
	<input id="submit" class="button" type="submit" value="${value}">
</#macro>

<#macro errorSummary obj>
	<div class="error-summary visually-hidden" role="group" aria-labelledby="error-summary-heading-example-1" tabindex="-1">
            
		<h1 class="heading-medium error-summary-heading" id="error-summary-heading-example-1">Message to alert the user to a problem goes here</h1>
		<p>Optional description of the errors and how to correct them</p>

		<ul class="error-summary-list">
			<#list obj as item>
				<#if (obj.error)??>
					<li><a href="#obj">${obj.error}</a></li>
				</#if>
			</#list>
		</ul>
	</div>
</#macro>

