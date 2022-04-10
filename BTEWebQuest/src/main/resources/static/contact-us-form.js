/* Script for Toast */
$('#toastBtn').on('click', function(e){
    e.preventDefault()        //This stops page loading
    var form = document.getElementById('contactForm');

    if (!formIsValid(form)){
        $('#toastHeader').text('Error')
        $('#toastBody').text('Please complete the form.');
        $("#liveToast").toast('show');
    }
    else{
        $.post({
            url: "/contactSubmit",
            data: $('#contactForm').serialize(),
            success: function () {
                $("#liveToast").toast('show'); //Show toast
                document.getElementsByName('contactForm')[0].reset();
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
    return true;
}