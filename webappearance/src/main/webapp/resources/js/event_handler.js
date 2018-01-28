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
            $(TIME_PICKER).prop(NAME_ALIAS, "");
        } else {
            $(TIME_PICKER).show();
            $(DATE_PICKER).hide();
            $(DATE_PICKER).prop(NAME_ALIAS, "");
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
        let currentTime = $("#time_pick").val();
        let currentDate = $("#date_pick").val();

        if (currentTime.length === 0 && currentDate.length === 0) {
            e.preventDefault();
            let errorMessage = $("#no_date_error_message").val();
            $("#cart_message_date").text(errorMessage);
        }

        if (currentQuantity.length === 0 || currentQuantity === 0) {
            e.preventDefault();
            let errorMessage = $("#ordered_book_amount_error_message").val();
            $("#cart_message").text(errorMessage);
        }
    });
});


