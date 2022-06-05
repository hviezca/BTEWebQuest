$('document').ready(function () {

    $.ajaxSetup({
        cache: false,
    });

    $('.messageModalClass').on('click', function (event) {

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (message, status) {
            $('#messageId').val(message.message_id);
            $('#message').val(message.message);
        })

        $('#messageModal').modal('show');
    })

    $("#replyButton").on('click', function (event) {

        event.preventDefault();
        $('#messageModal').modal('hide');

        var date = $('.eventDateClass').attr('href');
        var venue = $('.venueNameClass').attr('href');

        $.get(venue, function (vResponse, status) {

            $.get(date, function (eResponse, status) {

                getVenueName(eResponse, vResponse);

            });
        });

        function getVenueName(eResponse, vResponse) {

            var date = new Date(eResponse.event_date).toLocaleDateString("en-US");
            var venue = vResponse.venue_name;

            console.log(date + " " + venue);

            $('#subject').val("Break the Earth Booking - Event on " + date + " at " + venue);
            $("#replyModal").modal('show');
        }
    })

    // Reply Subject can't be empty
    $('#subject').on('input', function() {
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

            email = eResponse.contact_email;

            let reply = {
                message_id: $('#messageId').val(),
                subject: $('#subject').val(),
                message: $('#replyMessage').val()
            }
            let booking_contact = {

                contact_email: email
            }

            let booking_venue = {
                contact: booking_contact
            }

            let booking_event = {
                venue: booking_venue
            }

            let booking ={
                message: reply,
                event: booking_event
            }

            var form = $('#reply_form');
            form.validate();

            if(form.valid()){
                $.post({
                    url: "message/reply",
                    data: JSON.stringify(booking),
                    contentType: 'application/json'
                }).done(function (fragment) {
                    $("#bookingTable").replaceWith(fragment);
                    $('#replyModal').modal('hide');
                    $('#replyMessage').val("");
                    //document.getElementById('replyMessage').reset();
                })
            }
        });
    })

    $('.deleteClass').on('click', function(event){

        event.preventDefault();

        var bookingId = $(this).attr('href');

        $.get(bookingId, function(contactRequest, status){

            deleteBooking(contactRequest.booking_id);
        })
    })
})
function deleteBooking(id)
{
    $.ajax({
        url: 'booking/delete/'+id,
        type: 'DELETE',
        contentType:'application/json',
        success: function(fragment) {
            $("#bookingTable").replaceWith(fragment);
        }
    });
}