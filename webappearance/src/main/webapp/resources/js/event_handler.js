$(document).ready(function () {
    const LOCALIZATION = "#localization";
    const RENT_TERM = "term";
    const RENT_CHECKBOX = 'input[name="is_subscribtion"]';
    const DATE_PICKER = "#date_pick";
    const TIME_PICKER = "#time_pick";
    const TIME_PICKER_CLASS = ".timepicker";
    const CLEARABLE_PICKER = ".clear";
    const NAME_ALIAS = "name";
    const COUNTABLE_TABLE_ROW = ".table_countable tbody tr";
    const FIRST_TABLE_DATA = "td:first";
    const EMPTY_STRING = "";

    var options = {
        clearable: true
    };

    $(LOCALIZATION).bind("change", function () {
        this.form.submit();
    });

    $(DATE_PICKER).prop(NAME_ALIAS, RENT_TERM);

    $(RENT_CHECKBOX).change(function () {
        if ($(this).is(':checked')) {
            $(DATE_PICKER).show();
            $(TIME_PICKER).hide();
            $(CLEARABLE_PICKER).hide();
            $(DATE_PICKER).prop(NAME_ALIAS, RENT_TERM);
            $(TIME_PICKER).prop(NAME_ALIAS, EMPTY_STRING);
        } else {
            $(TIME_PICKER).show();
            $(DATE_PICKER).hide();
            $(DATE_PICKER).prop(NAME_ALIAS, EMPTY_STRING);
            $(TIME_PICKER).prop(NAME_ALIAS, RENT_TERM);
            $(TIME_PICKER_CLASS).wickedpicker(options);
            $(CLEARABLE_PICKER).show();
        }
    });

    $(COUNTABLE_TABLE_ROW).each(function (i) {
        let number = ++i;
        $(this).find(FIRST_TABLE_DATA).text(number + ".");
    });

    $('.order_form').submit(function (e) {
        let currentQuantity = $("input[name$='quantity']").val();
        let currentTime = $(TIME_PICKER).val();
        let currentDate = $(DATE_PICKER).val();

        if (currentTime.length === 0 && currentDate.length === 0) {
            e.preventDefault();
            let errorMessage = $("#no_date_error_message").val();
            $("#cart_message_date").removeClass('success').addClass('fault');
            $("#cart_message_date").text(errorMessage);
        } else {
            let successMessage = $("#general_success_message").val();
            $("#cart_message_date").removeClass('fault').addClass('success');
            $("#cart_message_date").text(successMessage);
        }

        if (currentQuantity.length === 0 || currentQuantity === 0) {
            e.preventDefault();
            let errorMessage = $("#ordered_book_amount_error_message").val();
            $("#cart_message_amount").removeClass('success').addClass('fault');
            $("#cart_message_amount").text(errorMessage);
        }
        else {
            let successMessage = $("#general_success_message").val();
            $("#cart_message_amount").removeClass('fault').addClass('success');
            $("#cart_message_amount").text(successMessage);
        }
    });
});


