$(document).ready(function () {

    const PASSWORD_PATTERN = /^((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,40})$/;
    const EMAIL_PATTERN = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
    const NAME_PATTERN = /^[a-zA-Z]{2,35}$/;

    const BR_TAG = "<br>";

    $("input[name='password']").bind("keyup change", function () {
        let passwordValue = $(this).val();
        if (!PASSWORD_PATTERN.test(passwordValue)) {
            $(this).next().removeClass('not_error').addClass('error');
            let lengthErrorMessage = $("#password_length_message").val();
            let infoErrorMessage = $("#password_info_message").val();
            $(this).next('.message').html(lengthErrorMessage + BR_TAG + infoErrorMessage);
        }
        else {
            $(this).next().removeClass('error').addClass('not_error');
            let successMessage = $("#general_success_message").val();
            $(this).next('.message').text(successMessage);
        }
    });

    $("input[name='confirm_password']").bind("keyup change", function () {
        let pass = $("input[name='password']").val();
        let confirmPass = $(this).val();
        if (!PASSWORD_PATTERN.test(confirmPass) || pass.toString() !== confirmPass.toString()) {
            $(this).next().removeClass('not_error').addClass('error');
            let errorMessage = $("#passwords_dont_match_message").val();
            $(this).next('.message').text(errorMessage);
        } else if (confirmPass.length === 0) {
            $(this).next().removeClass('not_error').addClass('error');
            let errorMessage = $("#required_field_message").val();
            $(this).next('.message').text(errorMessage);
        } else {
            $(this).next().removeClass('error').addClass('not_error');
            let successMessage = $("#general_success_message").val();
            $(this).next('.message').text(successMessage);
        }
    });

    $("input[name='email']").bind("keyup change", function () {
        let emailValue = $(this).val();
        if (!EMAIL_PATTERN.test(emailValue)) {
            $(this).next().removeClass('not_error').addClass('error');
            let errorMessage = $("#email_error_message").val();
            $(this).next('.message').text(errorMessage);
        }
        else {
            $(this).next().removeClass('error').addClass('not_error');
            let successMessage = $("#general_success_message").val();
            $(this).next('.message').text(successMessage);
        }
    });

    $("input[name$='name']").bind("keyup change", function () {
        let inputValue = $(this).val();
        if (!NAME_PATTERN.test(inputValue)) {
            $(this).next().removeClass('not_error').addClass('error');
            let errorMessage = $("#name_length_message").val();
            $(this).next('.message').text(errorMessage);
        }
        else {
            $(this).next().removeClass('error').addClass('not_error');
            let successMessage = $("#general_success_message").val();
            $(this).next('.message').text(successMessage);
        }
    });

    $(".verifiable.not_null").bind("keyup change", function () {
        let inputValue = $(this).val();
        if (inputValue.length === 0) {
            $(this).next().removeClass('not_error').addClass('error');
            let errorMessage = $("#required_field_message").val();
            $(this).next('.message').text(errorMessage);
        }
        else {
            $(this).next().removeClass('error').addClass('not_error');
            let successMessage = $("#general_success_message").val();
            $(this).next('.message').text(successMessage);
        }
    });

    $('.validated_form').submit(function (e) {
        if ($('.not_error').length !== $('.verifiable').length) {
            $("form.validated_form").addClass('not_active'); // marker of the blocked submit button for testing

            e.preventDefault();
            $(this).closest('form').find('input').not(':button, :submit, :reset, :hidden').trigger('change');

        }
    });

});