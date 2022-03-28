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

