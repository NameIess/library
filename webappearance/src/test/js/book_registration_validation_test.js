module('DOM test');
const NOT_ERROR_CLASS = 'not_error';
const ERROR_CLASS = 'error';
const MESSAGE_CLASS = '.message';

function positiveBookLabels(element) {
    const VALID_TITLE_MIN_LENGTH_BORDERED = 'q';
    const VALID_TITLE_MAX_LENGTH_BORDERED = 'fasdff3qef6kfasdfqe6k2351fdsfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3q';
    const VALID_TITLE_MIN_LENGTH_EQUIVALENT = 'qw';
    const VALID_TITLE_MAX_LENGTH_EQUIVALENT = 'fasdff3qef6kfasdfqe6k2351fdsfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3';

    element.val(VALID_TITLE_MIN_LENGTH_BORDERED).change();
    equal(element.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid title. 1 symbol. Min length value.');

    element.val(VALID_TITLE_MAX_LENGTH_BORDERED).change();
    equal(element.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid title. 150 symbols. Max length value.');

    element.val(VALID_TITLE_MIN_LENGTH_EQUIVALENT).change();
    equal(element.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid title. 2 symbols. Min equivalent value.');

    element.val(VALID_TITLE_MAX_LENGTH_EQUIVALENT).change();
    equal(element.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid title. 149 symbols. Max equivalent value.');
}

test('Positive title, author test', function () {
    const TITLE = $('#title');
    positiveBookLabels(TITLE);

    const AUTHOR = $('#author');
    positiveBookLabels(AUTHOR);
});

function negativeBookLabels(element) {
    const INVALID_TITLE_MIN_LENGTH_BORDERED = ' ';
    const INVALID_TITLE_MIN_LENGTH_EQUIVALENT = '';
    const INVALID_EMAIL_MAX_LENGTH_BORDERED = 'fasdff3qef6kfasdfqe6k2351fdsfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qs';
    const INVALID_EMAIL_MAX_LENGTH_EQUIVALENT = 'fasdff3qef6kfasdfqe6k2351fdsfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qasdfasdfasdfasfdaf';

    element.val(INVALID_TITLE_MIN_LENGTH_BORDERED).change();
    equal(element.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid TITLE. 1 symbol - space, invalid. Min bordered value.');

    element.val(INVALID_TITLE_MIN_LENGTH_EQUIVALENT).change();
    equal(element.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid TITLE. Empty string. Min equivalent value.');

    element.val(INVALID_EMAIL_MAX_LENGTH_BORDERED).change();
    equal(element.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid TITLE. 151 symbols. Max bordered value.');

    element.val(INVALID_EMAIL_MAX_LENGTH_EQUIVALENT).change();
    equal(element.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid TITLE. 168 symbols. Max equivalent value.');
}

test('Negative title, author test', function () {
    const TITLE = $('#title');
    negativeBookLabels(TITLE);

    const AUTHOR = $('#author');
    negativeBookLabels(AUTHOR);
});

function positiveNumberTest(element) {
    const VALID_NUMBER_MIN_LENGTH_BORDERED = '2';
    const VALID_NUMBER_MAX_LENGTH_BORDERED = '12345';
    const VALID_NUMBER_MIN_LENGTH_EQUIVALENT = '12';
    const VALID_NUMBER_MAX_LENGTH_EQUIVALENT = '1234';

    element.val(VALID_NUMBER_MIN_LENGTH_BORDERED).change();
    equal(element.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid number. 1 symbol. Min bordered length value.');

    element.val(VALID_NUMBER_MAX_LENGTH_BORDERED).change();
    equal(element.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid number. 5 symbols. Max bordered length value.');

    element.val(VALID_NUMBER_MIN_LENGTH_EQUIVALENT).change();
    equal(element.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid number. 2 symbols. Min equivalent value.');

    element.val(VALID_NUMBER_MAX_LENGTH_EQUIVALENT).change();
    equal(element.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid number. 4 symbols. Max equivalent value.');
}

test('Positive year of publishing, number of pages, amount test', function () {
    const YEAR_OF_PUBLISHING = $('#year_of_publishing');
    positiveNumberTest(YEAR_OF_PUBLISHING);

    const NUMBER_OF_PAGES = $('#number_of_pages');
    positiveNumberTest(NUMBER_OF_PAGES);

    const AMOUNT = $('#amount');
    positiveNumberTest(AMOUNT);
});

function negativeNumberTest(element) {
    const INVALID_NUMBER_MIN_LENGTH = 'k';
    const INVALID_NUMBER_MAX_LENGTH_BORDERED = '123456';
    const INVALID_NUMBER_MIN_LENGTH_EQUIVALENT = ' ';
    const INVALID_NUMBER_MAX_LENGTH_EQUIVALENT = 'nineteen';

    element.val(INVALID_NUMBER_MIN_LENGTH).change();
    equal(element.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid number. 1 symbol - letter. Min bordered value.');

    element.val(INVALID_NUMBER_MAX_LENGTH_BORDERED).change();
    equal(element.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid number. 6 symbols. Max bordered value.');

    element.val(INVALID_NUMBER_MIN_LENGTH_EQUIVALENT).change();
    equal(element.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid number. 1 symbol - space. Min equivalent value.');

    element.val(INVALID_NUMBER_MAX_LENGTH_EQUIVALENT).change();
    equal(element.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid number. 8 symbols - letters. Max equivalent value.');
}

test('Negative year of publishing, number of pages, amount test', function () {
    const YEAR_OF_PUBLISHING = $('#year_of_publishing');
    negativeNumberTest(YEAR_OF_PUBLISHING);

    const NUMBER_OF_PAGES = $('#number_of_pages');
    negativeNumberTest(NUMBER_OF_PAGES);

    const AMOUNT = $('#amount');
    negativeNumberTest(AMOUNT);
});

test('Negative description test', function () {
    const INVALID_DESCRIPTION_MIN_LENGTH_BORDERED = ' ';
    const INVALID_DESCRIPTION_MIN_LENGTH_EQUIVALENT = '';
    const INVALID_DESCRIPTION_MAX_LENGTH_BORDERED = '123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890x';
    const INVALID_DESCRIPTION_MAX_LENGTH_EQUIVALENT = '1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890';
    const DESCRIPTION = $('#description');

    DESCRIPTION.val(INVALID_DESCRIPTION_MIN_LENGTH_BORDERED).change();
    equal(DESCRIPTION.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid description. 1 symbol - space, invalid. Min length value.');

    DESCRIPTION.val(INVALID_DESCRIPTION_MIN_LENGTH_EQUIVALENT).change();
    equal(DESCRIPTION.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid description. Empty string. Min equivalent value.');

    DESCRIPTION.val(INVALID_DESCRIPTION_MAX_LENGTH_BORDERED).change();
    equal(DESCRIPTION.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid description. 1501 symbols. Max bordered value.');

    DESCRIPTION.val(INVALID_DESCRIPTION_MAX_LENGTH_EQUIVALENT).change();
    equal(DESCRIPTION.next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid description. 1600 symbols. Max bordered value.');
});

test('Positive description test', function () {
    const VALID_DESCRIPTION_MIN_LENGTH_BORDERED = 't';
    const VALID_DESCRIPTION_MIN_LENGTH_EQUIVALENT = 'tg';
    const VALID_DESCRIPTION_MAX_LENGTH_BORDERED = 'One thousand characters will be in this paragraph. In fact, 10000 signs -caseuuuuuucaseuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu in one case more than enough, in another - too little, in the third - even too much. Agree, the headline of 15 sentences would look strange. A story with a prehistory, intrigue and denouement in a thousand characters can only be laid by a very talented writer. On average, one thousand signs is a good detailed description of the goods in the catalog. In this volume will fit and detailed characteristics, and scope, and features of this model, and even on calling the advertising paragraph place will remain. If you evaluate by eye, then the text in 1000 characters (without spaces) - it\'s about half the page A4 font Times NewRoman size 12 pts and a half interval. If you are not told these parameters, then just take a word: it\'s about half a page of the text you are familiar with. Now the next question is: when 1000 characters are small? If 700-1000 characters are enough to describe the product, then the text on the page One thousand characters will be in this paragraph. In fact, 1000 signs - in one case more than enough, in another - too little, in the third - even too much. Agree, the headline of. A story with a prehistory, intrigue and denouement in a thousand characters. On average, one thousand signs is a good detailed description of the goods in the catalog. In this volume will fit and detailed characteristics, and scope, and feat';
    const VALID_DESCRIPTION_MAX_LENGTH_EQUIVALENT = 'One thousand characters will be in this paragraph. In fact, 10000 signs -caseuuuuuucaseuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu in one case more than enough, in another - too little, in the third - even too much. Agree, the headline of 15 sentences would look strange. A story with a prehistory, intrigue and denouement in a thousand characters can only be laid by a very talented writer. On average, one thousand signs is a good detailed description of the goods in the catalog. In this volume will fit and detailed characteristics, and scope, and features of this model, and even on calling the advertising paragraph place will remain. If you evaluate by eye, then the text in 1000 characters (without spaces) - it\'s about half the page A4 font Times NewRoman size 12 pts and a half interval. If you are not told these parameters, then just take a word: it\'s about half a page of the text you are familiar with. Now the next question is: when 1000 characters are small? If 700-1000 characters are enough to describe the product, then the text on the page One thousand characters will be in this paragraph. In fact, 1000 signs - in one case more than enough, in another - too little, in the third - even too much. Agree, the headline of. A story with a prehistory, intrigue and denouement in a thousand characters. On average, one thousand signs is a good detailed description of the goods in the catalog. In this volume will fit and detailed characteristics, and scope, and eat';
    const DESCRIPTION = $('#description');

    DESCRIPTION.val(VALID_DESCRIPTION_MIN_LENGTH_BORDERED).change();
    equal(DESCRIPTION.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid description. 1 symbol. Min length value.');

    DESCRIPTION.val(VALID_DESCRIPTION_MIN_LENGTH_EQUIVALENT).change();
    equal(DESCRIPTION.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid description. 2 symbols. Min equivalent value.');

    DESCRIPTION.val(VALID_DESCRIPTION_MAX_LENGTH_BORDERED).change();
    equal(DESCRIPTION.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid description. 1500 symbols. Max bordered value.');

    DESCRIPTION.val(VALID_DESCRIPTION_MAX_LENGTH_EQUIVALENT).change();
    equal(DESCRIPTION.next(MESSAGE_CLASS).hasClass(NOT_ERROR_CLASS), true, 'Valid description. 1499 symbols. Max bordered value.');
});

test('Verification fields before send request on server. All fields are valid. Should not block submit button.', function () {
    const VALID_TITLE_MAX_LENGTH_EQUIVALENT = 'fasdff3qef6kfasdfqe6k2351fdsfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3';
    const VALID_NUMBER_MAX_LENGTH_EQUIVALENT = '1234';
    const VALID_DESCRIPTION_MAX_LENGTH_EQUIVALENT = 'One thousand characters will be in this paragraph. In fact, 10000 signs -caseuuuuuucaseuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu in one case more than enough, in another - too little, in the third - even too much. Agree, the headline of 15 sentences would look strange. A story with a prehistory, intrigue and denouement in a thousand characters can only be laid by a very talented writer. On average, one thousand signs is a good detailed description of the goods in the catalog. In this volume will fit and detailed characteristics, and scope, and features of this model, and even on calling the advertising paragraph place will remain. If you evaluate by eye, then the text in 1000 characters (without spaces) - it\'s about half the page A4 font Times NewRoman size 12 pts and a half interval. If you are not told these parameters, then just take a word: it\'s about half a page of the text you are familiar with. Now the next question is: when 1000 characters are small? If 700-1000 characters are enough to describe the product, then the text on the page One thousand characters will be in this paragraph. In fact, 1000 signs - in one case more than enough, in another - too little, in the third - even too much. Agree, the headline of. A story with a prehistory, intrigue and denouement in a thousand characters. On average, one thousand signs is a good detailed description of the goods in the catalog. In this volume will fit and detailed characteristics, and scope, and eat';

    $('#title').val(VALID_TITLE_MAX_LENGTH_EQUIVALENT).change();
    $('#author').val(VALID_TITLE_MAX_LENGTH_EQUIVALENT).change();
    $('#year_of_publishing').val(VALID_NUMBER_MAX_LENGTH_EQUIVALENT).change();
    $('#number_of_pages').val(VALID_NUMBER_MAX_LENGTH_EQUIVALENT).change();
    $('#description').val(VALID_DESCRIPTION_MAX_LENGTH_EQUIVALENT).change();
    $('#amount').val(VALID_NUMBER_MAX_LENGTH_EQUIVALENT).change();

    equal($('.not_error').length, 6, '6 successful labels. No mistakes.');
    equal($('.error').length, 0, '0 error labels. No mistakes.');

    $('.submit_button').trigger('click');

    equal($('.not_active').length === 0 && $('.active').length === 1, true, 'Button has not been blocked.');
});

test('Verification fields before send request on server. One field invalid - description. Should block submit button.', function () {
    const VALID_TITLE_MAX_LENGTH_EQUIVALENT = 'fasdff3qef6kfasdfqe6k2351fdsfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3qe6kfasdfqe6kfasdf3qe6kfasdf3qe6kfasdfqe6k2351fdsdffasdf3qe6kfasdf3';
    const VALID_NUMBER_MAX_LENGTH_EQUIVALENT = '1234';
    const INVALID_DESCRIPTION_MIN_LENGTH_BORDERED = ' ';


    $('#title').val(VALID_TITLE_MAX_LENGTH_EQUIVALENT).change();
    $('#author').val(VALID_TITLE_MAX_LENGTH_EQUIVALENT).change();
    $('#year_of_publishing').val(VALID_NUMBER_MAX_LENGTH_EQUIVALENT).change();
    $('#number_of_pages').val(VALID_NUMBER_MAX_LENGTH_EQUIVALENT).change();
    $('#description').val(INVALID_DESCRIPTION_MIN_LENGTH_BORDERED).change();
    $('#amount').val(VALID_NUMBER_MAX_LENGTH_EQUIVALENT).change();

    equal($('.not_error').length, 5, '5 not_error labels. 1 mistake.');
    equal($('.error').length, 1, '1 error label. 1 mistake.');

    equal($('#description').next(MESSAGE_CLASS).hasClass(ERROR_CLASS), true, 'Invalid amount. Space value');

    $('.submit_button').trigger('click');

    equal($('.not_active').length === 1 && $('.active').length === 0, true, 'Button has been blocked.');
});
