$(document).ready(function() {

    // Subject can't be empty or less than 3 characters
    $('#venue').on('input', function() {
        var input=$(this);
        var is_venue=input.val();
        if(is_venue && is_venue.length >= 3){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Event Date can't be empty
    $('#eventDate').on('input', function() {
        var input=$(this);
        var is_date=input.val();
        if(is_date){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Address can't be empty or less than 3 characters
    $('#address').on('input', function() {
        var input=$(this);
        var is_address=input.val();
        if(is_address && is_address.length >= 3){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // City can't be empty or less than 3 characters
    $('#city').on('input', function() {
        var input=$(this);
        var is_city=input.val();
        if(is_city && is_city.length >= 3){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Zip can't be empty and must be 5 chars
    $('#zip').on('input', function() {
        var input=$(this);
        var is_zip=input.val();
        if(is_zip && is_zip.length === 5){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });
    
    // Message can't be empty
    $('#message').keyup(function(event) {
        var input=$(this);
        var message = $(this).val();
        if(message && message.length >= 5 && message.length <= 9){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Name can't be empty or less than 3 characters
    $('#name').on('input', function() {
        var input=$(this);
        var is_name=input.val();
        if(is_name && is_name.length >= 3){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Phone must be a valid phone number
    $('#phone').on('input', function() {
        var input=$(this);
        var re = /^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/;
        var is_phone=re.test(input.val());
        if(is_phone){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    // Email must be a valid email
    $('#email').on('input', function() {
        var input=$(this);
        var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        var is_email=re.test(input.val());
        if(is_email){input.removeClass("invalid").addClass("valid");}
        else{input.removeClass("valid").addClass("invalid");}
    });

    $('#toastBtn').on('click', function(e){
        e.preventDefault()        //This stops page loading

        var form = $('#booking_form');
        form.validate();

        if (!form.valid()){
            $('#toastHeader').text('Error')
            $('#toastBody').text('Please complete the form.');
            $("#liveToast").toast('show');
        }
        else{
            $.post({
                url: "/bookingSubmit",
                data: form.serialize(),
                success: function () {
                    $('#toastHeader').text('Thank you!')
                    $('#toastBody').text('One of us will get back to you soon.');
                    $("#liveToast").toast('show'); //Show toast
                    document.getElementsByName('bookingForm')[0].reset();
                    Array.from(form.elements).forEach((input) => {
                       input.removeClass("valid");
                       input.removeClass("invalid");
                    });
                }
            }).fail(function(){
                $('#toastHeader').text('Error')
                $('#toastBody').text('An error has occurred... Please try again.');
                $("#liveToast").toast('show'); //Show toast
            })
        }
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