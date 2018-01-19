$(document).ready(function () {
   $("#localization").bind("change", function () {
       this.form.submit();
   });

    const RENT_TERM = "term";
    let rentDateTime = 'input[name="term"]';
    let isRentCheckbox = 'input[name="is_subscription"]';
    let datePick = "#date_pick";
    let timePick = "#time_pick";
    let clear = ".clear";
    let options = {
        clearable: true
    };

    $(isRentCheckbox).change(function () {
        if ($(this).is(':checked')) {
            $(datePick).show();
            $(timePick).hide();
            $(clear).hide();
            $(datePick).prop("name", RENT_TERM);
        } else {
            $(timePick).show();
            $(clear).show();
            $(datePick).hide();
            $(timePick).prop("name", RENT_TERM);
            $('.timepicker').wickedpicker(options);
        }
    });




//
//     let confirmed = $(".confirmed .submit_button");
// // Get the button that opens the modal
//
// // Get the modal
//     var modal = document.getElementById('myModal');
//
// // Get the <span> element that closes the modal
//     var span = document.getElementsByClassName("close")[0];
//
// // When the user clicks the button, open the modal
//
//     confirmed.on("click", function(e) {
//         e.preventDefault();
//         modal.show();
//         alert("CONFIRM CLICKED")
//
//     });
//
// // When the user clicks on <span> (x), close the modal
//     span.on("click", function (e) {
//         modal.hide();
//     });
//
//
//
// // When the user clicks anywhere outside of the modal, close it
//     $(document).on("click" , function(e) {
//         e.preventDefault();
//         if (e.target == modal) {
//             $("#myModal").hide();
//         }
//     });
//
//     $(".triggerRemove").on("click", function(e) {
//         e.preventDefault();
//         $("#modalRemove .removeBtn").on("click", function (e) {
//
//             this.form.submit();
//         });
//     });

});


