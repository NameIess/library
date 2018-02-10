$(document).ready(function () {
    const RENT_TERM_ATTRIBUTE = 'term';
    const DATE_PICKER = $('#date_pick');
    const TIME_PICKER = $('#time_pick');
    const CLEARABLE_PICKER = $('.clear');
    const NAME_ALIAS = 'name';
    const EMPTY_STRING = '';
    const UNIVERSAL_SUCCESS_MESSAGE = $('#general_success_message').val();

    var options = {
        clearable: true
    };

    $('#localization').bind('change', function () {
        this.form.submit();
    });

    DATE_PICKER.prop(NAME_ALIAS, RENT_TERM_ATTRIBUTE);

    $('input[name="is_subscribtion"]').change(function () {
        if ($(this).is(':checked')) {
            DATE_PICKER.show();
            TIME_PICKER.hide();
            CLEARABLE_PICKER.hide();
            DATE_PICKER.prop(NAME_ALIAS, RENT_TERM_ATTRIBUTE);
            TIME_PICKER.prop(NAME_ALIAS, EMPTY_STRING);
        } else {
            $(TIME_PICKER).show();
            DATE_PICKER.hide();
            DATE_PICKER.prop(NAME_ALIAS, EMPTY_STRING);
            TIME_PICKER.prop(NAME_ALIAS, RENT_TERM_ATTRIBUTE);
            $('.timepicker').wickedpicker(options);
            CLEARABLE_PICKER.show();
        }
    });

    $('.table_countable tbody tr').each(function (i) {
        let number = ++i;
        $(this).find('td:first').text(number + '.');
    });

    $('.order_form').submit(function (e) {
        let currentTime = TIME_PICKER.val();
        let currentDate = DATE_PICKER.val();
        let cartMessageDate = $('#cart_message_date');

        if (currentTime.length === 0 && currentDate.length === 0) {
            e.preventDefault();
            let errorMessage = $('#no_date_error_message').val();
            setErrorMessage(cartMessageDate, errorMessage);
        } else {
            setSuccessMessage(cartMessageDate, UNIVERSAL_SUCCESS_MESSAGE);
        }

        let currentQuantity = $('input[name$="quantity"]').val();
        let cartMessageAmount = $('#cart_message_amount');

        if (currentQuantity.length === 0 || currentQuantity === 0) {
            e.preventDefault();
            let errorMessage = $('#ordered_book_amount_error_message').val();
            setErrorMessage(cartMessageAmount, errorMessage);
        } else {
            setSuccessMessage(cartMessageAmount, UNIVERSAL_SUCCESS_MESSAGE);
        }
    });

    function setSuccessMessage(element, message) {
        $(element).removeClass('fault').addClass('success');
        setMessage(element, message);
    }

    function setErrorMessage(element, message) {
        $(element).removeClass('success').addClass('fault');
        setMessage(element, message);
    }

    function setMessage(element, message) {
        $(element).text(message);
    }
});


