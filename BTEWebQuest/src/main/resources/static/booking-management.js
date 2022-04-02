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
        $('#messageModal').modal('hide');

        var date = $('#eventDate').attr('href');

        var re = "RE: ";
        var eventDateResponse;
        $.get(date, function (message, status){

            eventDateResponse = message.event_id;
        })

        console.log(eventDateResponse);


        $('#subject').val(re.concat(eventDateResponse))
        $("#replyModal").modal('show');

    })
})