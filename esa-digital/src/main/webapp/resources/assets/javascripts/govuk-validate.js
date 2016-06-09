var GOVUK = GOVUK || {}

GOVUK.validate = function(formItems) {

    var ok = true;

    for (var i = 0; i < formItems.length; i++) {

        var field = $($(formItems)[i]);
        var rules = field.attr('data-validation').split(" ");

        for (var j = 0; j < rules.length; j++) {

            switch (rules[j]) {
                case "required":
                	if(!this.validateRequired(field)) ok = false;
                    break;
            }

        }

    }

    if (!ok) $('.error-summary').removeClass('visually-hidden');

    return ok;
}

GOVUK.validateRequired = function(formItem) {

	var errorText = "This field can not be blank";
	var descriptionText = formItem.children('label').text()+" can not be blank";

    var ok = true;
    var formItemInput = formItem.children('input');

    if(formItemInput.val() == ""){
    	ok = false;
    	$(GOVUK.getValidationErrorElement(errorText)).insertBefore(formItemInput);
    	formItem.addClass('error');

    	if(!formItem.attr('id')) formItem.attr('id', formItem.children('label').text().toLowerCase().split(" ").join("-"));
    	$('.error-summary-list').append(GOVUK.getValidationDescriptionElement(descriptionText, formItem.attr('id')));
    } 

    return ok;
}

GOVUK.getValidationErrorElement = function(text){
	return '<span class="error-message">'+text+'</span>';
}

GOVUK.getValidationDescriptionElement = function(text, id){
	return '<li><a href="#'+id+'">'+text+'</a></li>';
}


$(document).ready(function(){
	$('form').on('submit', function(e) {
			        
	    $('.error-message').remove();
		$('.form-group[data-validation].error').removeClass('error');    
	    $('.error-summary').addClass('visually-hidden');
	    $('.error-summary-list').html('');


	    var ok = GOVUK.validate($('.form-group[data-validation]'));
	    
	    if (!ok) {
	        e.preventDefault();
	    }
	});
});