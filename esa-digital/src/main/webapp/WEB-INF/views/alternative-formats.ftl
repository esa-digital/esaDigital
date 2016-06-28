<#include "includes/head.ftl">

<div class="grid-row">
  <div class="column-two-thirds">

    <form action="alternative-formats" method="post" autocomplete="off">

      <@errors.errorSummary fields=["alternativeFormat.alternativeFormatYN","alternativeFormat.alternateFormatType", "alternativeFormat.alternateFormatOther"] />

      
      <h1 class="form-title heading-large">${content['page.title']}</h1>

      <div class="form-group">
        <fieldset class="inline">
        
        <legend>

          <span class="form-label-bold">
            ${content['page.question.title']}
          </span>
          
          <#--
          <@errors.listInlineErrors "alternativeFormat.alternativeFormatYN"/>
          <@errors.listInlineErrors "alternativeFormat.alternateFormatType"/>
          <@errors.listInlineErrors "alternativeFormat.alternateFormatOther"/>
          -->
        </legend>


          <label for="radio-part-2" data-target="format"
            class="block-label"> <input id="radio-part-2"
            type="radio" name="alternativeFormatYN" value="Yes"
            aria-controls="format"> Yes
          </label> <label for="radio-part-3" class="block-label"> <input
            id="radio-part-3" type="radio" name="alternativeFormatYN" value="No">
            No
          </label>

        </fieldset>
      </div>

      <div class="panel panel-border-narrow form-group js-hidden"
        id="format">
        <fieldset class="">
          <label class="block-label" for="radio-inline-1"> <input
            id="radio-inline-1" type="radio" name="alternateFormatType" value="LargePrint">
            ${content['page.question.largePrint']}
          </label> <label class="block-label" for="radio-inline-2"> <input
            id="radio-inline-2" type="radio" name="alternateFormatType" value="Braille">
            ${content['page.question.braille']}
          </label> <label class="block-label" for="radio-inline-7"> <input
            id="radio-inline-7" type="radio" name="alternateFormatType" value="AudioCD">
            ${content['page.question.audioCd']}
          </label> <label class="block-label" data-target="other"
            for="radio-inline-3"> <input id="radio-inline-3"
            type="radio" name="alternateFormatType" value="Other" aria-controls="Other">
            ${content['page.question.other']}
          </label>
          <div class="panel panel-border-narrow js-hidden" id="other">
            <label class="form-label" for="other-field">${content['page.question.otherDetail']}</label>
            <textarea type="text" class="form-control" id="alternateFormatOther" name="alternateFormatOther"></textarea>
          </div>
        </fieldset>
      </div>

      <@components.submit value="${content['page.question.submit']}" />
    </form>

  </div>
  <div class="column-one-third"><#include
    "includes/sidebar-helplines.ftl"></div>
</div>
<#include "includes/footer.ftl">
