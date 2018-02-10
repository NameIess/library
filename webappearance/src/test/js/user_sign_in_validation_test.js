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
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid EMAIL. 5 symbols. Min length bordered value.');

    EMAIL.val(VALID_EMAIL_MAX_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid EMAIL. 255 symbols. Max length bordered value.');

    EMAIL.val(VALID_EMAIL_MIN_LENGTH_EQUIVALENT).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid EMAIL. 6 symbols. Min equivalent value.');

    EMAIL.val(VALID_EMAIL_MAX_LENGTH_EQUIVALENT).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid EMAIL. 254 symbols. Max equivalent value.');
});

test('Negative email test', function () {
    const INVALID_EMAIL_MIN_LENGTH_BORDERED = 'd@.s';
    const INVALID_EMAIL_MIN_LENGTH_EQUIVALENT = '';
    const INVALID_EMAIL_MAX_LENGTH_BORDERED = 'asdfgzxcvsdfghwergxbgstmdfgvhdrthv45hvdrthvdrhdvrthdgfvdfghvdrthvdrtvhfdhvdrthvdrvhdthvdrthvdxgvsevs4vev4v34v34tvs43tsv34tvs3tv34tv34tv43tv34tvs3tvs34tvs34tvs34vs34tvs34tvs34tvrtvsthdrtcaewiurvoewunvaweirviewouriweovrnwaoervu23uvriwacmwrevnawenawer@asdf.by';
    const INVALID_EMAIL_MAX_LENGTH_EQUIVALENT = 'asdasdadcfsdfasdfcasdfacsdfacsdfacsdfcaewgvbrthbdrthbfhvvgsgvsfvgsdgvdfgzxcvsdfghwergxbgstmdfgvhdrthv45hvdrthvdrhdvrthdgfvdfghvdrthvdrtvhfdhvdrthvdrvhdthvdrthvdxgvsevs4vev4v34v34tvs43tsv34tvs3tv34tv34tv43tv34tvs3tvs34tvs34tvs34vs34tvs34tvs34tvrtvsthdrtcaewiurvoewunvaweirviewouriweovrnwaoervu23uvriwacmwrevnawenawer@asdf.by';
    const EMAIL = $('#email');

    EMAIL.val(INVALID_EMAIL_MIN_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid EMAIL. 4 symbols. Min length bordered value.');

    EMAIL.val(INVALID_EMAIL_MIN_LENGTH_EQUIVALENT).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid EMAIL. Empty string. Min equivalent value.');

    EMAIL.val(INVALID_EMAIL_MAX_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid EMAIL. 256 symbols. Max bordered value.');

    EMAIL.val(INVALID_EMAIL_MAX_LENGTH_EQUIVALENT).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid EMAIL. 323 symbols. Max equivalent value.');
});

test('Positive password test', function () {
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = 'As1Yw';
    const VALID_PASSWORD_MAX_LENGTH_BORDERED = 'As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw';
    const VALID_PASSWORD_MIN_LENGTH_EQUIVALENT = 'As1Yw2';
    const VALID_PASSWORD_MAX_LENGTH_EQUIVALENT = 'As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Y';
    const PASSWORD = $('#password');

    PASSWORD.val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 5 symbols. Min bordered length value.');

    PASSWORD.val(VALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 255 symbols. Max bordered length value.');

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
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. 4 symbols. Min bordered length value.');

    PASSWORD.val(INVALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. 41 symbols. Max bordered length value.');

    PASSWORD.val(INVALID_PASSWORD_MIN_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. Empty string. Min equivalent value.');

    PASSWORD.val(INVALID_PASSWORD_MAX_LENGTH_EQUIVALENT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. 82 symbols. Max equivalent value.');

    PASSWORD.val(INVALID_PASSWORD_WITH_NO_UPPERCASE).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. No uppercase symbols.');

    PASSWORD.val(INVALID_PASSWORD_WITH_NO_DIGIT).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. No digits.');

});


test('Verification fields before send request on server. Valid email, valid password. Should not block submit button.', function () {
    const VALID_EMAIL_MIN_LENGTH_BORDERED = 'd@w.s';
    const VALID_PASSWORD_MIN_LENGTH_BORDERED = 'As1Yw';
    const PASSWORD = $('#password');
    const EMAIL = $('#email');

    EMAIL.val(VALID_EMAIL_MIN_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid email. 5 symbols. Min length bordered value.');

    $(PASSWORD).val(VALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 6 symbols. Min bordered value.');

    equal($('.not_error').length, 2, '2 not_error labels. No mistakes.');
    equal($('.error').length, 0, '0 error labels. No mistakes.');

    $('.submit_button').trigger('click');

    equal($('.not_active').length === 0 && $('.active').length === 1, true, 'Button has not been blocked.');
});

test('Verification fields before send request on server. Invalid email, invalid password. Should block sumbit button.', function () {
    const INVALID_EMAIL_MIN_LENGTH_BORDERED = 'd@.s';
    const INVALID_PASSWORD_MIN_LENGTH_BORDERED = 'As1Y';
    const PASSWORD = $('#password');
    const EMAIL = $('#email');

    EMAIL.val(INVALID_EMAIL_MIN_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid email. 4 symbols. Min length bordered value.');

    PASSWORD.val(INVALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. 4 symbols. Min bordered length value.');

    equal($('.not_error').length, 0, '0 not_error labels. 2 mistakes.');
    equal($('.error').length, 2, '2 error labels. 2 mistakes.');

    $('.submit_button').trigger('click');

    equal($('.not_active').length === 1 && $('.active').length === 0, true, 'Button has been blocked.');
});

test('Verification fields before send request on server. Invalid email, valid password. Should block sumbit button.', function () {
    const INVALID_EMAIL_MIN_LENGTH_BORDERED = 'd@.s';
    const VALID_PASSWORD_MAX_LENGTH_BORDERED = 'As1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1YwAs1Yw';
    const PASSWORD = $('#password');
    const EMAIL = $('#email');

    EMAIL.val(INVALID_EMAIL_MIN_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid email. 4 symbols. Min length bordered value.');

    PASSWORD.val(VALID_PASSWORD_MAX_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid password. 255 symbols. Max bordered length value.');

    equal($('.not_error').length, 1, '1 not_error label. 1 mistake.');
    equal($('.error').length, 1, '1 error label. 1 mistake.');

    $('.submit_button').trigger('click');

    equal($('.not_active').length === 1 && $('.active').length === 0, true, 'Button has been blocked.');
});

test('Verification fields before send request on server. Valid email, invalid password. Should block sumbit button.', function () {
    const VALID_EMAIL_MIN_LENGTH_BORDERED = 'd@w.s';
    const INVALID_PASSWORD_MIN_LENGTH_BORDERED = 'As1Y';
    const PASSWORD = $('#password');
    const EMAIL = $('#email');

    EMAIL.val(VALID_EMAIL_MIN_LENGTH_BORDERED).change();
    equal(EMAIL.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid email. 5 symbols. Min length bordered value.');

    PASSWORD.val(INVALID_PASSWORD_MIN_LENGTH_BORDERED).change();
    equal(PASSWORD.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid password. 4 symbols. Min bordered length value.');

    equal($('.not_error').length, 1, '1 not_error label. 1 mistake.');
    equal($('.error').length, 1, '1 error label. 1 mistake.');

    $('.submit_button').trigger('click');


    equal($('.not_active').length === 1 && $('.active').length === 0, true, 'Button has been blocked.');
});