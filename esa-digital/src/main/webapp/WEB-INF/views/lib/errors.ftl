<#macro errorSummary fields>
	<div class="error-summary<#if noErrors(fields)> visually-hidden</#if>" role="group" aria-labelledby="error-summary-heading" tabindex="-1">
            
		<h1 class="heading-medium error-summary-heading" id="error-summary-heading">There's a problem</h1>
		<p>Check your form. You must:</p>

		<ul class="error-summary-list">
		<#list fields as item>
			<#if getStatus(item).error >
				<#list getStatus(item).errorMessages as error>
					<#if item?contains("dob")>
						<li><a href="#${item?keep_before(item?keep_after("dob"))?keep_after(".")}">${error}</a></li>
					<#elseif item?contains("address")>
						<li><a href="#${item?keep_before(item?keep_after("address"))?keep_after(".")}">${error}</a></li>
					<#else>
						<li><a href="#${item?keep_after(".")}">${error}</a></li>
					</#if>
				</#list>
	   		</#if>
		</#list>
		</ul>
	</div>
</#macro>

<#macro listInlineErrors path >
	<#if getStatus(path).error >
		<#list status.errorMessages as error>
            <span class="error-message">${error}</span>
        </#list>
	</#if>
</#macro>

<#macro hasError path ><#if getStatus(path).error> error</#if></#macro>

<#function noErrors fields>
	<#list fields as item>
		<#if getStatus(item).error >
			<#return false>
		</#if>
	</#list>
	<#return true>
</#function>

<#function getStatus path>
   <#if htmlEscape?exists>
       <#assign status = springMacroRequestContext.getBindStatus(path, htmlEscape)>
   <#else>
       <#assign status = springMacroRequestContext.getBindStatus(path)>
   </#if>
	<#return status>
</#function>
