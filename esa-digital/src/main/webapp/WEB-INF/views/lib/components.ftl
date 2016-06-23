<#macro inputText name content value path hint="" >
	<div class="form-group<@errors.hasError path />" id="${name}">
	  <label class="form-label-bold" for="${name}">${content} 
	  	<#if hint?has_content>
	  		<span class="form-hint">${hint}</span>
	  	</#if>
	  </label>
	  <@errors.listInlineErrors path/>
	  <input class="form-control" type="text" name="${name}" value="${value}">
	</div>
</#macro>

<#macro dob paths name content day month year dayText monthText yearText hint>
	<div class="form-group<#list paths as path><@errors.hasError path /></#list>" id="${name}">
		<fieldset>
			<legend>
				 <span class="form-label-bold">${content}</span>
				 <span class="form-hint">${hint}</span>
			</legend>
			<div class="form-date" name="formDate">
			  <#list paths as path>
				<@errors.listInlineErrors path/>
			  </#list>
			  <div class="form-group form-group-day">
			      <label for="dob-day">${dayText}</label>
			      <input class="form-control" type="number" name="dobDay" value="${day}" id="dob-day" pattern="[0-9]*" min="0" max="31">
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

