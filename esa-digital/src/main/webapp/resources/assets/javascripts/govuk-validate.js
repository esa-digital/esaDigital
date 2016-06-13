var GOVUK = GOVUK || {}

GOVUK.validate = function(formItems) {

    var ok = true;

    for (var i = 0; i < formItems.length; i++) {

        var field = $($(formItems)[i]);
        var rules = field.attr('data-validation').split(" ");

        for (var j = 0; j < rules.length; j++) {

            if ((/maxLength[\d*]/).test(rules[j])) {
                var length = rules[j].substring(9);
                rules[j] = rules[j].substring(0, 9);
            }

            switch (rules[j]) {
                case "required":
                    if (!this.validateRequired(field)) ok = false;
                    break;

                case "maxLength":
                    if (!this.validateMaxLength(field, length)) ok = false;
                    break;

                case "alpha":
                    if (!this.validateAlpha(field)) ok = false;
                    break;

                case "nino":
                    if (!this.validateNino(field)) ok = false;
                    break;

                case "date":
                    if (!this.validateDate(field)) ok = false;
                    break;
            }
        }
    }

    if (!ok) $('.error-summary').removeClass('visually-hidden');

    return ok;
}

GOVUK.validateDate = function(formItem) {

    var errorText = "This field contains an invalid National Insurance number";
    var descriptionText = formItem.children('label').text() + " must be vaild";

    var ok = true;
    var formItemInput = formItem.children('input');

    if (!(/^(?!BG|GB|NK|KN|TN|NT|ZZ)[ABCEGHJ-PRSTW-Z][ABCEGHJ-NPRSTW-Z]\d{6}[A-D]$/i).test(formItemInput.val())) {
        
        ok = false;
        GOVUK.renderTextErrors(formItem, formItemInput, errorText, descriptionText);
    }

    return ok;
}



GOVUK.validateRequired = function(formItem) {

    var errorText = "This field can not be blank";
    var descriptionText = formItem.children('label').text() + " can not be blank";

    var ok = true;
    var formItemInput = formItem.children('input');

    if (formItemInput.val() == "") {
        ok = false;
        GOVUK.renderTextErrors(formItem, formItemInput, errorText, descriptionText);
    }

    return ok;
}

GOVUK.validateMaxLength = function(formItem, l) {

    var errorText = "This field can not be longer than " + l + " charaters";
    var descriptionText = formItem.children('label').text() + " can not be longer than " + l + " charaters";

    var ok = true;
    var formItemInput = formItem.children('input');

    if (formItemInput.val().length > l) {
        ok = false;
        GOVUK.renderTextErrors(formItem, formItemInput, errorText, descriptionText);
    }

    return ok;
}

GOVUK.validateAlpha = function(formItem) {

    var errorText = "This field must contain charaters only";
    var descriptionText = formItem.children('label').text() + " must contain charaters only";

    var ok = true;
    var formItemInput = formItem.children('input');

    if (!(/^[a-zA-Z\s-',]*$/).test(formItemInput.val())) {
        ok = false;
        GOVUK.renderTextErrors(formItem, formItemInput, errorText, descriptionText);
    }

    return ok;
}

GOVUK.validateNino = function(formItem) {

    var errorText = "This field contains an invalid National Insurance number";
    var descriptionText = formItem.children('label').text() + " must be vaild";

    var ok = true;
    var formItemInput = formItem.children('input');

    if (!(/^(?!BG|GB|NK|KN|TN|NT|ZZ)[ABCEGHJ-PRSTW-Z][ABCEGHJ-NPRSTW-Z]\d{6}[A-D]$/i).test(formItemInput.val())) {
        
        ok = false;
        GOVUK.renderTextErrors(formItem, formItemInput, errorText, descriptionText);
    }

    return ok;
}

GOVUK.renderTextErrors = function(formItem, formItemInput, errorText, descriptionText) {
    $(GOVUK.getValidationErrorElement(errorText)).insertBefore(formItemInput);
    formItem.addClass('error');

    if (!formItem.attr('id')) formItem.attr('id', formItem.children('label').text().toLowerCase().split(" ").join("-"));
    $('.error-summary-list').append(GOVUK.getValidationDescriptionElement(descriptionText, formItem.attr('id')));
}

GOVUK.getValidationErrorElement = function(text) {
    return '<span class="error-message">' + text + '</span>';
}

GOVUK.getValidationDescriptionElement = function(text, id) {
    return '<li><a href="#' + id + '">' + text + '</a></li>';
}

$(document).ready(function() {
    $('form').on('submit', function(e) {
        e.preventDefault();
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
