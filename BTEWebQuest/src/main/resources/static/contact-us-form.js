$(document).ready(function () {

    // Subject can't be empty
    $('#contact_subject').on('input', function() {
        var input=$(this);
        var is_subject=input.val();
        if(is_subject && is_subject.length >= 3){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Message can't be empty
    $('#contact_message').keyup(function(event) {
        var input=$(this);
        var message = $(this).val();
        //console.log(message);
        if(message && message.length >= 3){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Name can't be empty
    $('#contact_name').on('input', function() {
        var input=$(this);
        var is_name=input.val();
        if(is_name && is_name.length >= 3){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Phone must be a valid phone number
    $('#contact_phone').on('input', function() {
        var input=$(this);
        var re = /^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/;
        var is_phone=re.test(input.val());
        if(is_phone){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Email must be a valid email
    $('#contact_email').on('input', function() {
        var input=$(this);
        var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        var is_email=re.test(input.val());
        if(is_email){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });



    $("#contact_submit button").click(function(event){
        event.preventDefault();

        var form = $("#contact_form");

        form.validate();

        if (!form.valid()){
            $('#toastHeader').text('Error')
            $('#toastBody').text('Please complete the form.');
            $("#liveToast").toast('show');
        }
        else{
            $.post({
                url: "/contactSubmit",
                data: form.serialize(),
                success: function () {
                    $('#toastHeader').text('Thank you!')
                    $('#toastBody').text('One of us will get back to you soon.');
                    $("#liveToast").toast('show'); //Show toast
                    document.getElementsByName('contactForm')[0].reset();
                }
            }).fail(function(){
                $('#toastHeader').text('Error')
                $('#toastBody').text('An error has occurred... Please try again.');
                $("#liveToast").toast('show'); //Show toast
            })
        }
    });
});