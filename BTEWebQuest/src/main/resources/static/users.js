$('document').ready(function() {

    $.ajaxSetup({
        cache: false,
    });

    $('.testclass').on('click',function(event){

        event.preventDefault();

        var href= $(this).attr('href');

        $.get(href, function(user, status){
            $('#editId').val(user.id);
            $('#editFirstName').val(user.firstName);
            $('#editLastName').val(user.lastName);
            $('#editUserName').val(user.userName);
        })

        $('#editModal').modal('show');
    })

    $('.deleteClass').on('click', function(event){

        event.preventDefault();

        $('#deleteUserId').val($(this).data('id'))

        $('#deleteModal').modal('show');
    })

    $('.updateClass').on('click', function(event){

        event.preventDefault();

        $('#updateUserId').val($(this).data('id'))

        $('#changeModal').modal('show');
    })

    $("#updateButton").on('click', function(event){

        event.preventDefault();
        $('input').next('span').remove();

        let id = $('#updateUserId').val();
        let oldPassword = $("#oldPassword").val();
        let newPassword = $("#newPassword").val();

        let jsonData = {id: id, firstName: "test" , lastName: "test", userName: oldPassword, password: newPassword};

        $.post({
            url: "users/passwordvalidation",
            data: jsonData,
            success: function (response) {
                if(response.validated) {
                    $.post({
                        url: "users/update",
                        data: JSON.stringify(jsonData),
                        contentType: 'application/json'
                    }).done(function(fragment){
                        $("#userTable").replaceWith(fragment);
                        $('.modal-backdrop').remove();
                    })
                } else {
                    $.each(response.errorMessages,function(key,value) {
                        $('input[name='+key+']').after('<span class="help-block">'+value+'</span>');
                    });
                }
            }
        })


        /*
        $.post({
            url: "users/update",
            data: JSON.stringify(jsonData),
            contentType: 'application/json'
        }).done(function(fragment){
            $("#userTable").replaceWith(fragment);
            $('.modal-backdrop').remove();
        })*/
    })



    $("#passwordButton").on('click', function(event){

        event.preventDefault();

        let id = $('#deleteUserId').val();
        let firstName = $("#deleteFirstName").val();
        let lastName = $("#deleteLastName").val();
        let userName = $("#deleteUserName").val();
        let password = $("#deletePassword").val();

        let jsonData = {id: id, firstName: firstName, lastName: lastName, userName: userName, password: password};

        $.post({
            url: "users/verify",
            data: JSON.stringify(jsonData),
            contentType: 'application/json'
        }).done(function(fragment){
            $("#userTable").replaceWith(fragment);
            $('.modal-backdrop').remove();
        })
    })

    $("#addButton").on('click', function(event){

        event.preventDefault();
        $('input').next('span').remove();

        $.post({
            url: "users/validation",
            data: $('#addUserForm').serialize(),
            success: function (response) {

                if(response.validated) {
                    $.post({
                        url: "users",
                        data: $('#addUserForm').serialize(),
                        success: function (fragment) {
                            $("#userTable").replaceWith(fragment);
                            $('.modal-backdrop').remove();
                        }
                    })
                } else {
                    $.each(response.errorMessages,function(key,value) {
                        $('input[name='+key+']').after('<span class="help-block">'+value+'</span>');
                    });
                }
            }
        })
    })

    $("#editButton").on('click', function(event){

        event.preventDefault();
        $('input').next('span').remove();

        $.post({
            url: "users/validation",
            data: $('#editUserForm').serialize(),
            success: function (response) {

                if(response.validated) {
                    $.ajax({
                        type: "PUT",
                        url: "",
                        data: $('#editUserForm').serialize(),
                    }).done(function(fragment){
                        $("#userTable").replaceWith(fragment);
                        $('.modal-backdrop').remove();
                    })
                } else {
                    $.each(response.errorMessages,function(key,value) {
                        $('input[name='+key+']').after('<span class="help-block">'+value+'</span>');
                    });
                }
            }
        })




    })

})

function deleteItem(id)
{
    $.ajax({
        url: 'users/'+id,
        type: 'DELETE',
        contentType:'application/json',
        success: function(fragment) {$("#userTable").replaceWith(fragment);}
    });
}


