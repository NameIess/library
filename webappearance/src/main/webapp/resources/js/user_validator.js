$(document).ready(function () {

    const PASSWORD_PATTERN = /^((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,40})$/;
    const EMAIL_PATTERN = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;

    $("input[name='password']").bind("keyup change", function () {
        let passwordValue = $("input[name='password']").val();
        if (!PASSWORD_PATTERN.test(passwordValue)) {
            $(this).removeClass('not_error');
            $(this).next('.error_message')
                .html('Password length must be between 5 and 40 characters<br>Password must contains at least 3 characters one uppercase and digit')
                .css('color', '#E3000E')
                .animate({'paddingLeft': '10px'}, 400)
                .animate({'paddingLeft': '5px'}, 400);
        }
        else {
            $(this).addClass('not_error');
            $(this).next('.error_message')
                .text('Done!')
                .css('color', '#25be2d')
                .animate({'paddingLeft': '10px'}, 400)
                .animate({'paddingLeft': '5px'}, 400);
        }
    });

    $("input[name='confirm_password']").bind("keyup change", function () {
        let pass = $("input[name='password']").val();
        let confirmPass = $("input[name='confirm_password']").val();
        if (!PASSWORD_PATTERN.test(confirmPass) || pass.toString() !== confirmPass.toString()) {
            $(this).removeClass('not_error');
            $(this).next('.error_message')
                .html('Passwords don\'t match<br>')
                .css('color', '#E3000E')
                .animate({'paddingLeft': '10px'}, 400)
                .animate({'paddingLeft': '5px'}, 400);
        }
        else {
            $(this).addClass('not_error');
            $(this).next('.error_message')
                .text('Done!')
                .css('color', '#25be2d')
                .animate({'paddingLeft': '10px'}, 400)
                .animate({'paddingLeft': '5px'}, 400);
        }
    });

    $("input[name='email']").bind("keyup change", function () {
        let emailValue = $("input[name='email']").val();
        if (!EMAIL_PATTERN.test(emailValue) || emailValue.length === 0) {
            $(this).removeClass('not_error');
            $(this).next('.error_message').html('Invalid email address.')
                .css('color', '#E3000E')
                .animate({'paddingLeft': '10px'}, 400)
                .animate({'paddingLeft': '5px'}, 400);
        }
        else {
            $(this).addClass('not_error');
            $(this).next('.error_message').text('Done!')
                .css('color', '#25be2d')
                .animate({'paddingLeft': '10px'}, 400)
                .animate({'paddingLeft': '5px'}, 400);
        }
    });


    $('.validated_form').submit(function (e) {
        if ($('.not_error').length !== $('.verifiable').length) {
            e.preventDefault();
            $(this).closest('form').find('input').not(':button, :submit, :reset, :hidden').trigger('change');
        }
    });

});