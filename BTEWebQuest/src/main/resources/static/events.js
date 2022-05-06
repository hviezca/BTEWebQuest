jQuery(document).ready(function($) {

    // Handle click on Event table row
    $(".clickable-row").click(function() {
        var href= "/BTE/event/json/" + $(this).data('id');

        $.get(href, function(event, status){

            $('#editVenueName').val(event.venue.venue_name);
            $('#editVenueAddress').val(event.venue.venue_address);
            $('#editVenueCity').val(event.venue.venue_city);
            $('#editVenueState').val(event.venue.venue_state);
            $('#editEventPrice').val(event.event_price);
            $('#updateUserId').val(event.event_id);
            let date = new Date(event.event_date);
            date.setDate(date.getDate()+1)
            $('input[name="editEventDate"]').datepicker("update", date);

            if(event.all_ages)
            {
                $('#editEventAge').val(1);
            }else {
                $('#editEventAge').val(0);
            }

            document.getElementById('editEventBooked').checked = event.booked;

        })

        $('#editModal').modal('show');
    });

    // Update Event via Ajax
    $("#updateButton").on('click', function(event){

        event.preventDefault();

        let eventID = $('#updateUserId').val();
        var href= "/BTE/event/json/" + eventID;

        let venueName = $('#editVenueName').val();
        let venueAddress = $('#editVenueAddress').val();
        let venueCity = $('#editVenueCity').val();
        let venueState = $('#editVenueState').val();
        let eventPrice = $('#editEventPrice').val();
        let eventDate = new Date($('#editEventDate').val());
        eventDate.setDate(eventDate.getDate()+1);
        let allAges = false;
        let eventBooked = false;
        if (document.getElementById('editEventBooked').checked)
        {
            eventBooked = true;
        }

        if($('#editEventAge').val() == 1)
        {
            allAges = true;
        }

        $.get(href, function(event, status){

            event.venue.venue_name = venueName;
            event.venue.venue_address = venueAddress;
            event.venue.venue_city = venueCity;
            event.venue.venue_state = venueState;
            event.event_price = eventPrice;
            event.all_ages = allAges;
            event.booked = eventBooked;
            event.event_date = eventDate;

            $.post({
                url: "event",
                data: JSON.stringify(event),
                contentType: 'application/json'
            }).done(function(fragment){
                $("#eventFrag").replaceWith(fragment);
                $('.modal-backdrop').remove();
            })
        })
    })

    // Delete Event button
    $("#deleteButton").on('click', function(event){

        event.preventDefault();
        let eventID = $('#updateUserId').val();
        var href= "/BTE/event/" + eventID;

        $.ajax({
            url: href,
            type: 'DELETE',
            contentType:'application/json',
            success: function(fragment) {
                $("#eventFrag").replaceWith(fragment);
                $('.modal-backdrop').remove();
            }
        });

    })

    // Add Event button
    $("#addButton").on('click', function(event){

        event.preventDefault();
        var href= "/BTE/event/neweventjson/";

        let venueName = $('#addVenueName').val();
        let venueAddress = $('#addVenueAddress').val();
        let venueCity = $('#addVenueCity').val();
        let venueState = $('#addVenueState').val();
        let venueZip = $('#addVenueZip').val();
        let contactName = $('#addContactName').val();
        let contactEmail = $('#addContactEmail').val();
        let contactPhone = $('#addContactNumber').val();
        let newVenue = $('#dropDownList').val();
        let eventPrice = $('#addEventPrice').val();
        let eventBooked = false;
        if (document.getElementById('addEventBooked').checked)
        {
            eventBooked = true;
        }
        let venueID = $('#dropDownList').val();
        let allAges = false;
        if($('#addEventAge').val() == 1)
        {
            allAges = true;
        }
        let eventDate = new Date($('#addEventDate').val());
        eventDate.setDate(eventDate.getDate()+1);

        $.get(href, function(event, status){

            event.event_price = eventPrice;
            event.all_ages = allAges;
            event.event_date = eventDate;
            event.booked = eventBooked;


            if(newVenue === "new") {
                event.venue.venue_name = venueName;
                event.venue.venue_address = venueAddress;
                event.venue.venue_city = venueCity;
                event.venue.venue_state = venueState;
                event.venue.venue_zip = venueZip;
                event.venue.contact.contact_email = contactEmail;
                event.venue.contact.contact_phone = contactPhone;
                event.venue.contact.contact_name = contactName;
                $.post({
                    url: "event/addevent",
                    data: JSON.stringify(event),
                    contentType: 'application/json'
                }).done(function(fragment){
                    $("#eventFrag").replaceWith(fragment);
                    $('.modal-backdrop').remove();
                });

            } else {
                event.venue.venue_id = venueID;
                $.post({
                    url: "event/addevent",
                    data: JSON.stringify(event),
                    contentType: 'application/json'
                }).done(function(fragment){
                    $("#eventFrag").replaceWith(fragment);
                    $('.modal-backdrop').remove();
                })
            }

        })

    })

    // Populate Date picker
    var edit_date_input=$('input[name="editEventDate"]'); //our date input has the name "date"
    let add_date_input=$('input[name="addEventDate"]');
    var container=$('.bootstrap.min.css form').length>0 ? $('.bootstrap.min.css form').parent() : "body";
    var date = new Date();
    date.setDate(date.getDate())
    var options={
        startDate: date,
        format: 'yyyy-mm-dd',
        container: container,
        todayHighlight: true,
        autoclose: true,
    };
    edit_date_input.datepicker(options);
    add_date_input.datepicker(options);

});

// Toggle new Event div
function selectedOption(chosen) {
    var x = document.getElementById("venueToggle");
    if(chosen === "new")
    {
        x.style.display = "inline";
    } else {
        x.style.display = "none";
    }
}
