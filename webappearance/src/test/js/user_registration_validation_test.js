module('DOM test');
const NOT_ERROR_CLASS = 'not_error';
const ERROR_CLASS = 'error';
const MESSAGE_CLASS = '.message';

test('Positive email test', function () {
    const VALID_EMAIL_MIN_LENGTH_BORDERED = 'd@w.s';
    const VALID_EMAIL_MAX_LENGTH_BORDERED = 'fasdf3qe6kfasdff3qef6kfasdfqe6k2351fdsfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kdffasdf3qe6kfasdf3qe6kfasdfqe6ke6kfasdfqe6kdffasdf3qv53scvvvefasdf3fase6k@gmail.com';
    const VALID_EMAIL_MIN_LENGTH_EQUIVALENT = 'd@wh.s';
    const VALID_EMAIL_MAX_LENGTH_EQUIVALENT = 'fasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kdffasdf3qe6kfasd3qe6kfasdfqe6ke6kfasdfqe6kdffasdf3qv53scvvve6kfasdf3fase6k@gmail.com';
    const EMAIL = $('#email');

    EMAIL.val(VALID_EMAIL_MIN_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid email. 5 symbols. Min length value.');

    EMAIL.val(VALID_EMAIL_MAX_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid email. 255 symbols. Max length value.');

    EMAIL.val(VALID_EMAIL_MIN_LENGTH_EQUIVALENT).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid email. 6 symbols. Min equivalent value.');

    EMAIL.val(VALID_EMAIL_MAX_LENGTH_EQUIVALENT).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid email. 254 symbols. Max equivalent value.');
});

test('Negative email test', function () {
    const INVALID_EMAIL_MIN_LENGTH_BORDERED = 'd@.s';
    const INVALID_EMAIL_MIN_LENGTH_EQUIVALENT = '';
    const INVALID_EMAIL_MAX_LENGTH_BORDERED = 'asdfgzxcvsdfghwergxbgstmdfgvhdrthv45hvdrthvdrhdvrthdgfvdfghvdrthvdrtvhfdhvdrthvdrvhdthvdrthvdxgvsevs4vev4v34v34tvs43tsv34tvs3tv34tv34tv43tv34tvs3tvs34tvs34tvs34vs34tvs34tvs34tvrtvsthdrtcaewiurvoewunvaweirviewouriweovrnwaoervu23uvriwacmwrevnawenawer@asdf.by';
    const INVALID_EMAIL_MAX_LENGTH_EQUIVALENT = 'asdasdadcfsdfasdfcasdfacsdfacsdfacsdfcaewgvbrthbdrthbfhvvgsgvsfvgsdgvdfgzxcvsdfghwergxbgstmdfgvhdrthv45hvdrthvdrhdvrthdgfvdfghvdrthvdrtvhfdhvdrthvdrvhdthvdrthvdxgvsevs4vev4v34v34tvs43tsv34tvs3tv34tv34tv43tv34tvs3tvs34tvs34tvs34vs34tvs34tvs34tvrtvsthdrtcaewiurvoewunvaweirviewouriweovrnwaoervu23uvriwacmwrevnawenawer@asdf.by';
    const EMAIL = $('#email');

    EMAIL.val(INVALID_EMAIL_MIN_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid EMAIL. 4 symbols. Min length value.');

    EMAIL.val(INVALID_EMAIL_MIN_LENGTH_EQUIVALENT).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid EMAIL. Empty string. Min equivalent value.');

    EMAIL.val(INVALID_EMAIL_MAX_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid EMAIL. 256 symbols. Max bordered value.');

    EMAIL.val(INVALID_EMAIL_MAX_LENGTH_EQUIVALENT).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid EMAIL. 323 symbols. Max bordered value.');
});

test('Positive password test', function () {
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = 'As1Yw';
    const VALID_PASSWORD_MAX_LENGTH_BORDERED = 'As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw';
    const VALID_PASSWORD_MIN_LENGTH_EQUIVALENT = 'As1Yw2';
    const VALID_PASSWORD_MAX_LENGTH_EQUIVALENT = 'As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Y';
    const PASSWORD = $('#password');

    PASSWORD.val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 5 symbols. Min length value.');

    PASSWORD.val(VALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 255 symbols. Max length value.');

    PASSWORD.val(VALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 6 symbols. Min equivalent value.');

    PASSWORD.val(VALID_PASSWORD_MAX_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 254 symbols. Max equivalent value.');
});

test('Negative password test', function () {
    const INVALID_PASSWORD_MIN_LENGTH_BORDERED = 'As1Y';
    const INVALID_PASSWORD_MAX_LENGTH_BORDERED = 'As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw9';
    const INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT = '';
    const INVALID_PASSWORD_MAX_LENGTH_EQUIVALENT = 'As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw9As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw9';
    const INVALID_PASSWORD_WITH_NO_UPPERCASE = 'valid_length_but_no_uppercase_символь_999';
    const INVALID_PASSWORD_WITH_NO_DIGIT = 'EVERYTHING_is_Ok_but_no_Digits';
    const PASSWORD = $('#password');

    PASSWORD.val(INVALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. 4 symbols. Min length value.');

    PASSWORD.val(INVALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. 41 symbols. Max length value.');

    PASSWORD.val(INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. Empty string. Min equivalent value.');

    PASSWORD.val(INVALID_PASSWORD_MAX_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. 82 symbols. Max equivalent value.');

    PASSWORD.val(INVALID_PASSWORD_WITH_NO_UPPERCASE).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. No uppercase symbols.');

    PASSWORD.val(INVALID_PASSWORD_WITH_NO_DIGIT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. No digits.');

});

test('Positive confirm password test. Valid password, valid confirm password. Minimal length bordered values.', function () {
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = 'As6Yw';
    const PASSWORD = $('#password');
    const CONFIRM_PASSWORD = $('#confirm_password');

    PASSWORD.val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    CONFIRM_PASSWORD.val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 5 symbols. Min length value.');
    equal(CONFIRM_PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid confirmed password. 5 symbols. Min length value.');
});

test('Positive confirm password test. Valid password, valid confirm password. Maximal length bordered values.', function () {
    const VALID_PASSWORD_MAX_LENGTH_BORDERED = 'As6YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw';
    const PASSWORD = $('#password');
    const CONFIRM_PASSWORD = $('#confirm_password');

    PASSWORD.val(VALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    CONFIRM_PASSWORD.val(VALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 255 symbols. Max length value.');
    equal(CONFIRM_PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 255 symbols. Max length value.');
});

test('Positive confirm password test. Valid password, valid confirm password. Minimal length equivalent values.', function () {
    const VALID_PASSWORD_MIN_LENGTH_EQUIVALENT = 'As6Yw2';
    const PASSWORD = $('#password');
    const CONFIRM_PASSWORD = $('#confirm_password');

    PASSWORD.val(VALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    CONFIRM_PASSWORD.val(VALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 6 symbols. Min equivalent value.');
    equal(CONFIRM_PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 6 symbols. Min equivalent value.');
});

test('Positive confirm password test. Valid password, valid confirm password. Maximal length equivalent values.', function () {
    const VALID_PASSWORD_MAX_LENGTH_EQUIVALENT = 'As6YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Y';
    const PASSWORD = $('#password');
    const CONFIRM_PASSWORD = $('#confirm_password');

    PASSWORD.val(VALID_PASSWORD_MAX_LENGTH_EQUIVALENT).change();
    CONFIRM_PASSWORD.val(VALID_PASSWORD_MAX_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 254 symbols. Max equivalent value.');
    equal(CONFIRM_PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 254 symbols. Max equivalent value.');
});

test('Negative confirm password test. Valid password, valid confirm password. Passwords don\'t match.', function () {
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = 'As1Yw';
    const VALID_PASSWORD_MIN_LENGTH_EQUIVALENT = 'As1Yw2';
    const PASSWORD = $('#password');
    const CONFIRM_PASSWORD = $('#confirm_password');

    PASSWORD.val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    CONFIRM_PASSWORD.val(VALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 5 symbols. Min length value.');
    equal(CONFIRM_PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Confirmed password does\'n match. 6 symbols. Equivalent value.');
    notEqual(VALID_PASSWORD_MIN_LENGTH_BORDERED, VALID_PASSWORD_MIN_LENGTH_EQUIVALENT, 'Passwords don\'t match.');
});

test('Negative confirm password test. Invalid password, invalid confirm password.', function () {
    const INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT = '';
    const PASSWORD = $('#password');
    const CONFIRM_PASSWORD = $('#confirm_password');

    PASSWORD.val(INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    CONFIRM_PASSWORD.val(INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. 0 symbols.');
    equal(CONFIRM_PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid confirmed password. 0 symbols.');
    equal(INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT, INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT, 'Passwords match.');
});

test('Negative confirm password test. Valid password, invalid confirm password - wrong pattern.', function () {
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = 'As1Yw';
    const INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT = '';
    const VALID_PASSWORD_MIN_LENGTH_EQUIVALENT = 'As1Yw2';
    const PASSWORD = $('#password');
    const CONFIRM_PASSWORD = $('#confirm_password');

    PASSWORD.val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    CONFIRM_PASSWORD.val(INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 5 symbols. Min length value.');
    equal(CONFIRM_PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Confirmed password does\'n match. Confirmed password invalid - 0 symbols. Equivalent value.');
    notEqual(VALID_PASSWORD_MIN_LENGTH_BORDERED, VALID_PASSWORD_MIN_LENGTH_EQUIVALENT, 'Passwords don\'t match.');
});

test('Positive name fields test', function () {
    const VALID_NAME_MIN_LENGTH = 'Bm';
    const VALID_NAME_MAX_LENGTH_BORDERED = 'samplsamplsamplsamplsamplsamplsampl';
    const VALID_NAME_MIN_LENGTH_EQUIVALENT = 'Bmq';
    const VALID_NAME_MAX_LENGTH_EQUIVALENT = 'samplsamplsamplsamplsamplsamplsaml';
    const NAME = $('input[name$="name"]');

    NAME.val(VALID_NAME_MIN_LENGTH).change();
    equal(NAME.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid name. 2 symbols. Min length value.');

    NAME.val(VALID_NAME_MAX_LENGTH_BORDERED).change();
    equal(NAME.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid name. 35 symbols. Max length value.');

    NAME.val(VALID_NAME_MIN_LENGTH_EQUIVALENT).change();
    equal(NAME.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid name. 3 symbols. Min equivalent value.');

    NAME.val(VALID_NAME_MAX_LENGTH_EQUIVALENT).change();
    equal(NAME.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid name. 34 symbols. Max equivalent value.');
});

test('Negative name fields test', function () {
    const INVALID_NAME_MIN_LENGTH = '';
    const INVALID_NAME_MAX_LENGTH_BORDERED = 'samplsamplsamplsamplsamplsamplsampls';
    const INVALID_NAME_MIN_LENGTH_EQUIVALENT = 'B';
    const INVALID_NAME_MAX_LENGTH_EQUIVALENT = 'samplsamplsamplsamplsamplsamplsamlsamplsamplsamplsamplsamplsamplsaml';
    const NAME = $('input[name$="name"]');

    NAME.val(INVALID_NAME_MIN_LENGTH).change();
    equal(NAME.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid name. 0 symbols. Min length value.');

    NAME.val(INVALID_NAME_MAX_LENGTH_BORDERED).change();
    equal(NAME.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid name. 36 symbols. Max length value.');

    NAME.val(INVALID_NAME_MIN_LENGTH_EQUIVALENT).change();
    equal(NAME.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid name. 1 symbols. Min equivalent value.');

    NAME.val(INVALID_NAME_MAX_LENGTH_EQUIVALENT).change();
    equal(NAME.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid name. 70 symbols. Max equivalent value.');
});

test('Verification fields before send request on server. All fields are valid. Should not block sumbit button.', function () {
    const VALID_NAME_MIN_LENGTH = 'Bmh';
    const VALID_EMAIL_MIN_LENGTH_BORDERED = 'd@w.s';
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = 'As1Yw';
    const VALID_PASSPORT_SERIES = 'KH';
    const VALID_PASSPORT_NUMBER = '23201111';

    $('#second_name').val(VALID_NAME_MIN_LENGTH).change();
    $('#name').val(VALID_NAME_MIN_LENGTH).change();
    $('#surname').val(VALID_NAME_MIN_LENGTH).change();
    $('#email').val(VALID_EMAIL_MIN_LENGTH_BORDERED).change();
    $('#password').val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    $('#confirm_password').val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    $('#passport_series').val(VALID_PASSPORT_SERIES).change();
    $('#passport_number').val(VALID_PASSPORT_NUMBER).change();

    equal($('.not_error').length, 8, '8 successful labels. No mistakes.');
    equal($('.error').length, 0, '0 error labels. No mistakes.');

    $('.submit_button').trigger('click');

    equal($('.not_active').length === 0 && $('.active').length === 1, true, 'Button has not been blocked.');
});

test('Verification fields before send request on server. One field invalid - Passport series. Should block sumbit button.', function () {
    const VALID_NAME_MIN_LENGTH = 'Bmh';
    const VALID_EMAIL_MIN_LENGTH_BORDERED = 'd@w.s';
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = 'As1Yw';
    const VALID_PASSPORT_SERIES = '';
    const VALID_PASSPORT_NUMBER = '23201111';

    $('#second_name').val(VALID_NAME_MIN_LENGTH).change();
    $('#name').val(VALID_NAME_MIN_LENGTH).change();
    $('#surname').val(VALID_NAME_MIN_LENGTH).change();
    $('#email').val(VALID_EMAIL_MIN_LENGTH_BORDERED).change();
    $('#password').val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    $('#confirm_password').val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    $('#passport_series').val(VALID_PASSPORT_SERIES).change();
    $('#passport_number').val(VALID_PASSPORT_NUMBER).change();

    equal($('.not_error').length, 7, '7 not_error labels. 1 mistake.');
    equal($('.error').length, 1, '1 error label. 1 mistake.');

    equal($('input[name$="passport_series"]').next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid passport series. 0 symbols');

    $('.submit_button').trigger('click');

    equal($('.not_active').length === 1 && $('.active').length === 0, true, 'Button has been blocked.');
});
