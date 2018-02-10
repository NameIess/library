$(document).ready(function () {

    const PASSWORD_PATTERN = /^((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,40})$/;
    const EMAIL_PATTERN = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
    const NAME_PATTERN = /^[a-zA-Z]{2,35}$/;
    const NUMBER_PATTERN = /^(0|[1-9]\d{0,4})$/;
    const BOOK_DESCRIPTION_PATTERN = /^\w[\w\W]{0,1499}$/;
    const BOOK_INFO_PATTERN = /^\w[\w\W]{0,149}$/;
    const BR_TAG = '<br>';
    const UNIVERSAL_SUCCESS_MESSAGE = $('#general_success_message').val();

    $('input[name="password"]').bind('keyup change', function () {
        let passwordValue = $(this).val();
        if (!PASSWORD_PATTERN.test(passwordValue)) {
            let lengthErrorMessage = $('#password_length_message').val();
            let infoErrorMessage = $('#password_info_message').val();
            let errorMessage = lengthErrorMessage + BR_TAG + infoErrorMessage;
            setErrorMessage(this, errorMessage);
        } else {
            setSuccessMessage(this, UNIVERSAL_SUCCESS_MESSAGE);
        }
    });

    $('input[name="confirm_password"]').bind('keyup change', function () {
        let pass = $('input[name="password"]').val();
        let confirmPass = $(this).val();
        if (!PASSWORD_PATTERN.test(confirmPass) || pass.toString() !== confirmPass.toString()) {
            let errorMessage = $('#passwords_dont_match_message').val();
            setErrorMessage(this, errorMessage);
        } else {
            setSuccessMessage(this, UNIVERSAL_SUCCESS_MESSAGE);
        }
    });

    $('input[name="email"]').bind('keyup change', function () {
        let emailValue = $(this).val();
        if (!EMAIL_PATTERN.test(emailValue) || emailValue.length > 255) {
            let errorMessage = $('#email_error_message').val();
            setErrorMessage(this, errorMessage);
        } else {
            setSuccessMessage(this, UNIVERSAL_SUCCESS_MESSAGE);
        }
    });

    $('input[name$="name"]').bind('keyup change', function () {
        let inputValue = $(this).val();
        if (!NAME_PATTERN.test(inputValue)) {
            let errorMessage = $('#name_length_message').val();
            setErrorMessage(this, errorMessage);
        } else {
            setSuccessMessage(this, UNIVERSAL_SUCCESS_MESSAGE);
        }
    });

    $('.verifiable.not_null').bind('keyup change', function () {
        let inputValue = $(this).val();
        if (inputValue.length === 0) {
            let errorMessage = $('#required_field_message').val();
            setErrorMessage(this, errorMessage);
        } else {
            setSuccessMessage(this, UNIVERSAL_SUCCESS_MESSAGE);
        }
    });

    $('input.verifiable.book_data').bind('keyup change', function () {
        let inputValue = $(this).val();
        if (!BOOK_INFO_PATTERN.test(inputValue)) {
            let errorMessage = $('#wrong_field_150_length_message').val();
            setErrorMessage(this, errorMessage);
        } else {
            setSuccessMessage(this, UNIVERSAL_SUCCESS_MESSAGE);
        }
    });

    $('input.verifiable.book_number').bind('keyup change', function () {
        let inputValue = $(this).val();
        if (!NUMBER_PATTERN.test(inputValue)) {
            let errorMessage = $('#wrong_number_amount_message').val();
            setErrorMessage(this, errorMessage);
        } else {
            setSuccessMessage(this, UNIVERSAL_SUCCESS_MESSAGE);
        }
    });

    $('textarea.verifiable').bind('keyup change', function () {
        let inputValue = $(this).val();
        if (!BOOK_DESCRIPTION_PATTERN.test(inputValue)) {
            let errorMessage = $('#wrong_field_1500_length_message').val();
            setErrorMessage(this, errorMessage);
        } else {
            setSuccessMessage(this, UNIVERSAL_SUCCESS_MESSAGE);
        }
    });

    $('.validated_form').submit(function (e) {
        if ($('.not_error').length !== $('.verifiable').length) {
            e.preventDefault();
            $(this).closest('form').find('input, textarea').not(':button, :submit, :reset, :hidden').trigger('change');

            $("form.validated_form").removeClass('active').addClass('not_active'); // marker of the blocked submit button for testing
        } else {
            $("form.validated_form").removeClass('not_active').addClass('active'); // marker of the active submit button for testing
        }
    });

    function setSuccessMessage(element, message) {
        $(element).next('.message').removeClass('error').addClass('not_error');
        setActionMessage(element, message);
    }

    function setErrorMessage(element, message) {
        $(element).next('.message').removeClass('not_error').addClass('error');
        setActionMessage(element, message);
    }

    function setActionMessage(element, message) {
        $(element).next('.message').html(message);
    }
});