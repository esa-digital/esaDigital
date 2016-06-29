<#macro inputText name content value path hint="" class="">
	<div class="form-group<@errors.hasError path />" id="${formatName(name)}">
	  <label class="form-label-bold" for="${name}-field">${content} 
	  	<#if hint?has_content>
	  		<span class="form-hint">${hint}</span>
	  	</#if>
	  </label>
	  <@errors.listInlineErrors path/>

	  <input class="form-control" type="text" name="${name}" id="${name}-field" value="${value}" >
	</div>
</#macro>

<#macro inputRadio id value group content target="" class="" selected="">
	<label for="${id}" class="block-label" <#if target?has_content>data-target="${target}"</#if>>
        <input type="radio" name="${group}" id="${id}" value="${value}" <#if selected==value>checked="checked"</#if>>
        ${content}
    </label>
</#macro>

<#macro textarea name content value path hint="" class="">
	<div class="form-group<@errors.hasError path />" id="${formatName(name)}">
        <label for="${name}-field" class="form-label-bold">${content}</label>
        <#if hint?has_content>
	  		<span class="form-hint">${hint}</span>
	  	</#if>
        <@errors.listInlineErrors path/>
        <textarea class="form-control form-control-2-3" type="text" id="${name}-field" name="${name}">${value}</textarea>
    </div>
</#macro>

<#macro dob paths name content day month year dayText monthText yearText hint="" binding="">
	<div class="form-group<#list paths as path><@errors.hasError path /></#list>" id="${formatName(name)}">
		<fieldset>
			<legend>
				 <span class="form-label-bold">${content}</span>
				 <#if hint?has_content><span class="form-hint">${hint}</span></#if>
			</legend>
			<div class="form-date" name="formDate">
			  <#list paths as path>
				<@errors.listInlineErrors path/>
			  </#list>
			  <div class="form-group form-group-day">
			      <label for="dob-day">${dayText}</label>
			      <input class="form-control" type="number" name="<#if binding?has_content>${binding}</#if>dobDay" value="${day}" id="dob-day" pattern="[0-9]*" min="0" max="31">
			  </div>
			  <div class="form-group form-group-month">
			      <label for="dob-month">${monthText}</label>
			      <input class="form-control" type="number" name="<#if binding?has_content>${binding}</#if>dobMonth" value="${month}" id="dob-month" pattern="[0-9]*" min="0" max="12">
			  </div>
			  <div class="form-group form-group-year">
			      <label for="dob-year">${yearText}</label>
			      <input class="form-control" type="number" name="<#if binding?has_content>${binding}</#if>dobYear" value="${year}" id="dob-year" pattern="[0-9]*" min="0" max="2016">
			  </div>
			</div>
		</fieldset>
	</div>
</#macro>

<#macro address paths name content contentPostcode addr1 addr2 addr3 addr4 postcode addrBinding postcodeBinding="" hint="">
	<div class="form-group<#list paths as path><@errors.hasError path /></#list>" id="${formatName(name)}">
            
		<h2 class="form-title heading-medium">${content}</h2>
		<#if hint?has_content><p>${hint}</p></#if>
		
		<fieldset>
		
			<legend class="visuallyhidden">${content}</legend>
  
			<label class="form-label"><input type="text" class="form-control" name="${addrBinding}1" value="${addr1}"></label>
			<label class="form-label"><input type="text" class="form-control" name="${addrBinding}2" value="${addr2}"></label>
			<label class="form-label"><input type="text" class="form-control" name="${addrBinding}3" value="${addr3}"></label>
			<label class="form-label"><input type="text" class="form-control" name="${addrBinding}4" value="${addr4}"></label>
		  
		  	<div class="form-group">
			    <label class="form-label" for="postcode">${contentPostcode}</label>
			    <input type="text" class="form-control form-control-1-4" name="<#if postcodeBinding?has_content>${postcodeBinding}</#if>postCode" value="${postcode}" id="postCode">
			</div>
		
		</fieldset>
	</div>
</#macro>

<#macro submit value>
	<input id="submit" class="button" type="submit" value="${value}">
</#macro>

<#function formatName name>
   <#if name?contains(".")>
       <#assign formattedName = name?keep_after(".")>
       <#return formattedName>
   <#else>
   		<#return name>
   </#if>
	
</#function>
