$('document').ready(function() {

    $.ajaxSetup({
        cache: false,
    });

    $('.messageModalClass').on('click',function(event){

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function(message, status){
            $('#messageId').val(message.message_id);
            $('#message').val(message.message);
        })

        $('#messageModal').modal('show');
    })

    $("#replyButton").on('click', function(event){

        event.preventDefault();

        $("#replyModal").modal('hide');

        /*$.post({
            url: "",
            data: $('#addUserForm').serialize(),
            success: function (fragment) {
                $("#userTable").replaceWith(fragment);
                $('.modal-backdrop').remove();
            }
        })*/
    })
})

/* Script for Datepicker */
$(document).ready(function(){
    var date_input=$('input[name="event.event_date"]'); //our date input has the name "date"
    var container=$('.bootstrap.min.css form').length>0 ? $('.bootstrap.min.css form').parent() : "body";
    var date = new Date();
    date.setDate(date.getDate()+1)
    var options={
        startDate: date,
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
    };
    date_input.datepicker(options);
})

/* Script for Toast */
$('#toastBtn').on('click', function(e){
    e.preventDefault()        //This stops page loading
    var form = document.getElementById('bookingForm');

    if (!formIsValid(form)){
        $('#toastHeader').text('Error')
        $('#toastBody').text('Please complete the form.');
        $("#liveToast").toast('show');
    }
    else{
        $.post({
            url: "/bookingSubmit",
            data: $('#bookingForm').serialize(),
            success: function () {
                $("#liveToast").toast('show'); //Show toast
                document.getElementsByName('bookingForm')[0].reset();
            }
        }).fail(function(){
            $('#toastHeader').text('Error')
            $('#toastBody').text('An error has occurred... Please try again.');
            $("#liveToast").toast('show'); //Show toast
        })
    }
})

// Checks if any of the form inputs are empty
function formIsValid(form){

    var inputs = form.getElementsByTagName("input");
    var textArea = document.getElementById('message')
    var select = document.getElementById('State');

    let size = inputs.length;

    for (var i = 0; i < size; i++){

        if(inputs[i].hasAttribute("required")) {
            if (inputs[i].value === "" || inputs[i].value == null) {
                // found an empty field that is required
                return false;
            }
        }
    }
    if (textArea.value === "" || textArea.value == null) {
        // found an empty field that is required
        return false;
    }
    if (select.value === "" || select.value == null) {
        // found an empty field that is required
        return false;
    }

    return true;
}