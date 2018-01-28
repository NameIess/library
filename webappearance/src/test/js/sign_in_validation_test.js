module("DOM test");
const NOT_ERROR_CLASS = "not_error";
const NOT_ERROR_CLASS_SELECTOR = ".not_error";
const ERROR_CLASS = "error";
const ERROR_CLASS_SELECTOR = ".error";

test("Positive email test", function () {
    const VALID_EMAIL_MIN_LENGTH_BORDERED = "d@w.s";
    const VALID_EMAIL_MAX_LENGTH_BORDERED = "fasdf3qe6kfasdff3qef6kfasdfqe6k2351fdsfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kdffasdf3qe6kfasdf3qe6kfasdfqe6ke6kfasdfqe6kdffasdf3qv53scvvvefasdf3fase6k@gmail.com";
    const VALID_EMAIL_MIN_LENGTH_EQUIVALENT = "d@wh.s";
    const VALID_EMAIL_MAX_LENGTH_EQUIVALENT = "fasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kdffasdf3qe6kfasd3qe6kfasdfqe6ke6kfasdfqe6kdffasdf3qv53scvvve6kfasdf3fase6k@gmail.com";


    $("#email").val(VALID_EMAIL_MIN_LENGTH_BORDERED).change();
    equal($("#email").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid email. 5 symbols. Min length value.");

    $("#email").val(VALID_EMAIL_MAX_LENGTH_BORDERED).change();
    equal($("#email").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid email. 255 symbols. Max length value.");

    $("#email").val(VALID_EMAIL_MIN_LENGTH_EQUIVALENT).change();
    equal($("#email").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid email. 6 symbols. Min equivalent value.");

    $("#email").val(VALID_EMAIL_MAX_LENGTH_EQUIVALENT).change();
    equal($("#email").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid email. 254 symbols. Max equivalent value.");
});

test("Negative email test", function () {
    const INVALID_EMAIL_MIN_LENGTH_BORDERED = "d@.s";
    const INVALID_EMAIL_MIN_LENGTH_EQUIVALENT = "";

    $("#email").val(INVALID_EMAIL_MIN_LENGTH_BORDERED).change();
    equal($("#email").next('.message').hasClass(ERROR_CLASS), true, "Invalid email. 4 symbols. Min length value.");


    $("#email").val(INVALID_EMAIL_MIN_LENGTH_EQUIVALENT).change();
    equal($("#email").next('.message').hasClass(ERROR_CLASS), true, "Invalid email. Empty string. Min equivalent value.");
});

test("Positive password test", function () {
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = "As1Yw";
    const VALID_PASSWORD_MAX_LENGTH_BORDERED = "As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw";
    const VALID_PASSWORD_MIN_LENGTH_EQUIVALENT = "As1Yw2";
    const VALID_PASSWORD_MAX_LENGTH_EQUIVALENT = "As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Y";


    $("#password").val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal($("#password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 5 symbols. Min length value.");

    $("#password").val(VALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    equal($("#password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 255 symbols. Max length value.");

    $("#password").val(VALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal($("#password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 6 symbols. Min equivalent value.");

    $("#password").val(VALID_PASSWORD_MAX_LENGTH_EQUIVALENT).change();
    equal($("#password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 254 symbols. Max equivalent value.");
});

test("Negative password test", function () {
    const INVALID_PASSWORD_MIN_LENGTH_BORDERED = "As1Y";
    const INVALID_PASSWORD_MAX_LENGTH_BORDERED = "As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw9";
    const INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT = "";
    const INVALID_PASSWORD_MAX_LENGTH_EQUIVALENT = "As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw9As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw9";
    const INVALID_PASSWORD_WITH_NO_UPPERCASE = "valid_length_but_no_uppercase_символь_999";
    const INVALID_PASSWORD_WITH_NO_DIGIT = "EVERYTHING_is_Ok_but_no_Digits";

    $("#password").val(INVALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal($("#password").next('.message').hasClass(ERROR_CLASS), true, "Invalid password. 4 symbols. Min length value.");

    $("#password").val(INVALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    equal($("#password").next('.message').hasClass(ERROR_CLASS), true, "Invalid password. 41 symbols. Max length value.");

    $("#password").val(INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal($("#password").next('.message').hasClass(ERROR_CLASS), true, "Invalid password. Empty string. Min equivalent value.");

    $("#password").val(INVALID_PASSWORD_MAX_LENGTH_EQUIVALENT).change();
    equal($("#password").next('.message').hasClass(ERROR_CLASS), true, "Invalid password. 82 symbols. Max equivalent value.");

    $("#password").val(INVALID_PASSWORD_WITH_NO_UPPERCASE).change();
    equal($("#password").next('.message').hasClass(ERROR_CLASS), true, "Invalid password. No uppercase symbols.");

    $("#password").val(INVALID_PASSWORD_WITH_NO_DIGIT).change();
    equal($("#password").next('.message').hasClass(ERROR_CLASS), true, "Invalid password. No digits.");

});


test("Verification fields before send request on server. Valid email, valid password. Should not block sumbit button.", function () {
    $("#email").val("valid@gmail.com").change();
    $("#password").val("As1Ag6").change();

    equal($(NOT_ERROR_CLASS_SELECTOR).length, 2, '2 successful labels. No mistakes.');

    $('.submit_button').trigger('click');

    equal($(".not_active").length === 0, true, 'Button has not been blocked.');
});

test("Verification fields before send request on server. Invalid email, invalid password. Should block sumbit button.", function () {
    const INVALID_EMAIL_MIN_LENGTH_BORDERED = "d@.s";
    const INVALID_PASSWORD_MIN_LENGTH_BORDERED = "As1Y";

    $("#email").val(INVALID_EMAIL_MIN_LENGTH_BORDERED).change();
    $("#password").val(INVALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal($(NOT_ERROR_CLASS_SELECTOR).length, 0, '0 not_error labels. 2 mistakes.');
    equal($(ERROR_CLASS_SELECTOR).length, 2, '2 error labels. 2 mistakes.');

    $('.submit_button').trigger('click');

    equal($(".not_active").length === 1, true, 'Button has been blocked.');
});

test("Verification fields before send request on server. Invalid email, valid password. Should block sumbit button.", function () {
    const INVALID_EMAIL_MIN_LENGTH_BORDERED = "d@.s";
    const VALID_PASSWORD_MAX_LENGTH_BORDERED = "As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw";

    $("#email").val(INVALID_EMAIL_MIN_LENGTH_BORDERED).change();
    $("#password").val(VALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    equal($(NOT_ERROR_CLASS_SELECTOR).length, 1, '1 not_error label. 1 mistake.');
    equal($(ERROR_CLASS_SELECTOR).length, 1, '1 error label. 1 mistake.');

    $('.submit_button').trigger('click');

    equal($(".not_active").length === 1, true, 'Button has been blocked.');
});

test("Verification fields before send request on server. Valid email, invalid password. Should block sumbit button.", function () {
    const VALID_EMAIL_MIN_LENGTH_BORDERED = "d@w.s";
    const INVALID_PASSWORD_MIN_LENGTH_BORDERED = "As1Y";

    $("#email").val(VALID_EMAIL_MIN_LENGTH_BORDERED).change();
    $("#password").val(INVALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal($(NOT_ERROR_CLASS_SELECTOR).length, 1, '1 not_error label. 1 mistake.');
    equal($(ERROR_CLASS_SELECTOR).length, 1, '1 error label. 1 mistake.');

    $('.submit_button').trigger('click');

    equal($(".not_active").length === 1, true, 'Button has been blocked.');
});