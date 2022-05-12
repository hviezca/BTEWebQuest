$('document').ready(function (){
    $.ajaxSetup({
        cache: false,
    });

    $('.messageModalClass').on('click', function (event) {

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (message, status) {
            $('#messageId').val(message.message_id);
            $('#modalMessage').val(message.message);
        })

        $('#messageModal').modal('show');
    })

    $('#replyButton').on('click', function (event) {

        event.preventDefault();
        $('#messageModal').modal('hide');

        var date = $('#date').attr('href');

        $.get(date, function (response, status) {

            date = response.date;
            date = new Date(date);
            var d = date.getDate(date);
            var m = date.getMonth()+1;
            m += 1; // Javascript months are 0 - 11
            var y = date.getFullYear();

            $('#replySubject').val("Break the Earth RE: Your Contact Request on " + d+"/"+m+"/"+y)

            $("#replyModal").modal('show');

        });
    })

    // Reply Subject can't be empty
    $('#replySubject').on('input', function() {
        var input=$(this);
        var is_subject=input.text(input.val());  // .text() will sanitize the value of the element.
        if(is_subject && is_subject.text().length >= 3){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Message can't be empty
    $('#replyMessage').keyup(function(event) {
        var input=$(this);
        var message = $(this).text($(this).val());  // .tex() will sanitize input
        if(message && message.text().length >= 3){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    $("#sendButton").on('click', function (event) {

        event.preventDefault();

        var email = $('#email').attr('href');

        $.get(email, function (eResponse, status) {

            email = eResponse.contact.contact_email;

            let reply = {
                message_id: $('#messageId').val(),
                subject: $('#replySubject').val(),
                message: $('#replyMessage').val()
            }
            let contact_person = {

                contact_email: email
            }

            let contact_request ={
                message: reply,
                contact: contact_person
            }

            var form = $('#reply_form');
            form.validate();
            if(form.valid()) {

                $.post({
                    url: "contactRequest/reply",
                    data: JSON.stringify(contact_request),
                    contentType: 'application/json'
                }).done(function (fragment) {
                    $('#replyModal').modal('hide');
                    $("#contactRequestTable").replaceWith(fragment);
                    $('#replyMessage').val("");
                    //document.getElementById('replyMessage').reset();
                })
            }
        });
    })

    $('.deleteClass').on('click', function(event){

        event.preventDefault();

        var contactRequestId = $(this).attr('href');

        $.get(contactRequestId, function(request, status){

            deleteBooking(request.contact_request_id);
        })
    })
})
function deleteBooking(id)
{
    $.ajax({
        url: 'contactRequest/delete/'+id,
        type: 'DELETE',
        contentType:'application/json',
        success: function(fragment) {
            $("#contactRequestTable").replaceWith(fragment);
        }
    });
}
