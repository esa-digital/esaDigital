<#include "includes/head.ftl">

<div class="grid-row">
  <div class="column-two-thirds">

    <form action="alternative-formats" method="post" autocomplete="off">

      <h1 class="form-title heading-large">${content['page.title']}</h1>

      <div class="form-group">
        <fieldset class="inline">

          <p class="form-label-bold">You will get your decision
            letter in the post. Do you need this in a different format
            eg braille, large print or audio CD?</p>

          <label for="radio-part-2" data-target="format"
            class="block-label"> <input id="radio-part-2"
            type="radio" name="housing-act" value="Yes"
            aria-controls="format"> Yes
          </label> <label for="radio-part-3" class="block-label"> <input
            id="radio-part-3" type="radio" name="housing-act" value="No">
            No
          </label>

        </fieldset>
      </div>

      <div class="panel panel-border-narrow form-group js-hidden"
        id="format">
        <fieldset class="">
          <label class="block-label" for="radio-inline-1"> <input
            id="radio-inline-1" type="radio" name="partner" value="Yes">
            Large print
          </label> <label class="block-label" for="radio-inline-2"> <input
            id="radio-inline-2" type="radio" name="partner" value="No">
            Braille
          </label> <label class="block-label" for="radio-inline-7"> <input
            id="radio-inline-7" type="radio" name="partner" value="No">
            Audio CD
          </label> <label class="block-label" data-target="other"
            for="radio-inline-3"> <input id="radio-inline-3"
            type="radio" name="partner" value="No" aria-controls="other">
            Other
          </label>
          <div class="panel panel-border-narrow js-hidden" id="other">
            <label class="form-label" for="other-field">What
              format do you need?</label>
            <textarea type="text" class="form-control" id="other-field"></textarea>
          </div>
        </fieldset>
      </div>

      <!-- Primary buttons, secondary links -->
      <div class="form-group">
        <input type="submit" class="button" value="Continue">
        <!--a href="overview">I do not agree - leave now</a-->
      </div>
    </form>

  </div>
  <div class="column-one-third"><#include
    "includes/sidebar-helplines.ftl"></div>
</div>
<#include "includes/footer.ftl">
