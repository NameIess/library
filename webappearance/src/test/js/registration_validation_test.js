module("DOM test");
const NOT_ERROR_CLASS = "not_error";
const NOT_ERROR_CLASS_SELECTOR = ".not_error";
const ERROR_CLASS = "error";

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

test("Positive confirm password test", function () {
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = "As1Yw";
    const VALID_PASSWORD_MAX_LENGTH_BORDERED = "As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw";
    const VALID_PASSWORD_MIN_LENGTH_EQUIVALENT = "As1Yw2";
    const VALID_PASSWORD_MAX_LENGTH_EQUIVALENT = "As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Y";


    $("#password").val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    $("#confirm_password").val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal($("#password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 5 symbols. Min length value.");
    equal($("#confirm_password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid confirmed password. 5 symbols. Min length value.");
    equal($(".not_error").length === 2, true, "Two success labels");

    $("#password").val(VALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    $("#confirm_password").val(VALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    equal($("#password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 255 symbols. Max length value.");
    equal($("#confirm_password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 255 symbols. Max length value.");
    equal($(".not_error").length === 2, true, "Two success labels");

    $("#password").val(VALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    $("#confirm_password").val(VALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal($("#password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 6 symbols. Min equivalent value.");
    equal($("#confirm_password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 6 symbols. Min equivalent value.");
    equal($(".not_error").length === 2, true, "Two success labels");

    $("#password").val(VALID_PASSWORD_MAX_LENGTH_EQUIVALENT).change();
    $("#confirm_password").val(VALID_PASSWORD_MAX_LENGTH_EQUIVALENT).change();
    equal($("#password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 254 symbols. Max equivalent value.");
    equal($("#confirm_password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 254 symbols. Max equivalent value.");
    equal($(".not_error").length === 2, true, "Two success labels");
});

test("Negative confirm password test", function () {
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = "As1Yw";
    const INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT = "";
    const VALID_PASSWORD_MIN_LENGTH_EQUIVALENT = "As1Yw2";


    $("#password").val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    $("#confirm_password").val(VALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal($("#password").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid password. 5 symbols. Min length value.");
    equal($("#confirm_password").next('.message').hasClass(ERROR_CLASS), true, "Confirmed password does'n match. 6 symbols. Equivalent value.");
    notEqual(VALID_PASSWORD_MIN_LENGTH_BORDERED, VALID_PASSWORD_MIN_LENGTH_EQUIVALENT, "Passwords don't match.");
    equal($(".not_error").length === 1, true, "One success label");

    $("#password").val(INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    $("#confirm_password").val(INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal($("#password").next('.message').hasClass(ERROR_CLASS), true, "Invalid password. 0 symbols.");
    equal($("#confirm_password").next('.message').hasClass(ERROR_CLASS), true, "Invalid confirmed password. 0 symbols.");
    equal(INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT, INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT, "Passwords match.");
    equal($(".not_error").length === 0, true, "No success label");
});

test("Positive name fields test", function () {
    const VALID_NAME_MIN_LENGTH = "Bm";
    const VALID_EMAIL_MAX_LENGTH_BORDERED = "samplsamplsamplsamplsamplsamplsampl";
    const VALID_EMAIL_MIN_LENGTH_EQUIVALENT = "Bmq";
    const VALID_EMAIL_MAX_LENGTH_EQUIVALENT = "samplsamplsamplsamplsamplsamplsaml";


    $("input[name$='name']").val(VALID_NAME_MIN_LENGTH).change();
    equal($("input[name$='name']").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid name. 2 symbols. Min length value.");

    $("input[name$='name']").val(VALID_EMAIL_MAX_LENGTH_BORDERED).change();
    equal($("input[name$='name']").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid name. 35 symbols. Max length value.");

    $("input[name$='name']").val(VALID_EMAIL_MIN_LENGTH_EQUIVALENT).change();
    equal($("input[name$='name']").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid name. 3 symbols. Min equivalent value.");

    $("input[name$='name']").val(VALID_EMAIL_MAX_LENGTH_EQUIVALENT).change();
    equal($("input[name$='name']").next('.message').hasClass(NOT_ERROR_CLASS), true, "Valid name. 34 symbols. Max equivalent value.");
});

test("Negative name fields test", function () {
    const INVALID_NAME_MIN_LENGTH = "";
    const INVALID_EMAIL_MAX_LENGTH_BORDERED = "samplsamplsamplsamplsamplsamplsampls";
    const INVALID_EMAIL_MIN_LENGTH_EQUIVALENT = "B";
    const INVALID_EMAIL_MAX_LENGTH_EQUIVALENT = "samplsamplsamplsamplsamplsamplsamlsamplsamplsamplsamplsamplsamplsaml";


    $("input[name$='name']").val(INVALID_NAME_MIN_LENGTH).change();
    equal($("input[name$='name']").next('.message').hasClass(ERROR_CLASS), true, "Invalid name. 0 symbols. Min length value.");

    $("input[name$='name']").val(INVALID_EMAIL_MAX_LENGTH_BORDERED).change();
    equal($("input[name$='name']").next('.message').hasClass(ERROR_CLASS), true, "Invalid name. 36 symbols. Max length value.");

    $("input[name$='name']").val(INVALID_EMAIL_MIN_LENGTH_EQUIVALENT).change();
    equal($("input[name$='name']").next('.message').hasClass(ERROR_CLASS), true, "Invalid name. 1 symbols. Min equivalent value.");

    $("input[name$='name']").val(INVALID_EMAIL_MAX_LENGTH_EQUIVALENT).change();
    equal($("input[name$='name']").next('.message').hasClass(ERROR_CLASS), true, "Invalid name. 70 symbols. Max equivalent value.");
});

test("Verification fields before send request on server. All fields are valid. Should not block sumbit button.", function () {
    const VALID_NAME_MIN_LENGTH = "Bmh";
    const VALID_EMAIL_MIN_LENGTH_BORDERED = "d@w.s";
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = "As1Yw";
    const VALID_PASSPORT_SERIES = "KH";
    const VALID_PASSPORT_NUMBER = "23201111";

    $("#second_name").val(VALID_NAME_MIN_LENGTH).change();
    $("#name").val(VALID_NAME_MIN_LENGTH).change();
    $("#surname").val(VALID_NAME_MIN_LENGTH).change();
    $("#email").val(VALID_EMAIL_MIN_LENGTH_BORDERED).change();
    $("#password").val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    $("#confirm_password").val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    $("#passport_series").val(VALID_PASSPORT_SERIES).change();
    $("#passport_number").val(VALID_PASSPORT_NUMBER).change();

    equal($(NOT_ERROR_CLASS_SELECTOR).length, 8, '8 successful labels. No mistakes.');

    $('.submit_button').trigger('click');

    equal($(".not_active").length === 0, true, 'Button has not been blocked.');
});

test("Verification fields before send request on server. All fields are valid. Should not block sumbit button.", function () {
    const VALID_NAME_MIN_LENGTH = "Bmh";
    const VALID_EMAIL_MIN_LENGTH_BORDERED = "d@w.s";
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = "As1Yw";
    const VALID_PASSPORT_SERIES = "";
    const VALID_PASSPORT_NUMBER = "23201111";

    $("#second_name").val(VALID_NAME_MIN_LENGTH).change();
    $("#name").val(VALID_NAME_MIN_LENGTH).change();
    $("#surname").val(VALID_NAME_MIN_LENGTH).change();
    $("#email").val(VALID_EMAIL_MIN_LENGTH_BORDERED).change();
    $("#password").val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    $("#confirm_password").val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    $("#passport_series").val(VALID_PASSPORT_SERIES).change();
    $("#passport_number").val(VALID_PASSPORT_NUMBER).change();

    equal($(NOT_ERROR_CLASS_SELECTOR).length, 7, '7 successful labels. 1 mistake.');
    equal($("input[name$='passport_series']").next('.message').hasClass(ERROR_CLASS), true, "Invalid passport series. 0 symbols");

    $('.submit_button').trigger('click');

    equal($(".not_active").length === 1, true, 'Button has been blocked.');
});
