$('document').ready(function() {

    $.ajaxSetup({
        cache: false,
    });

    $('.trackclass').on('click',function(event){

        event.preventDefault();

        var href= $(this).attr('href');

        $.get(href, function(track, status){
            $('#songTitle').val(track.trackName);
            $('#updateTrackId').val(track.id);
            $('#updateTrackNumber').val(track.trackNumber);
            $('#updateAlbumId').val(track.albumId);
            document.getElementById('editVocalCheck').checked = track.vocals;
            document.getElementById('editGuitarCheck').checked = track.guitar;
            document.getElementById('editDrumsCheck').checked = track.drums;
            document.getElementById('editBassCheck').checked = track.bass;
        })

        $('#editTrackModal').modal('show');
    })

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

        let id = $('#updateUserId').val();
        let oldPassword = $("#oldPassword").val();
        let newPassword = $("#newPassword").val();

        let jsonData = {id: id, firstName: "test" , lastName: "test", userName: oldPassword, password: newPassword};

        $.post({
            url: "users/update",
            data: JSON.stringify(jsonData),
            contentType: 'application/json'
        }).done(function(fragment){
            $("#userTable").replaceWith(fragment);
            $('.modal-backdrop').remove();
        })
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

        $("#addModal").modal('hide');

        $.post({
            url: '',
            data: $('#addUserForm').serialize(),
            success: function (fragment) {
                $("#userTable").replaceWith(fragment);
                $('.modal-backdrop').remove();
            }
        })
    })

    $("#editButton").on('click', function(event){

        event.preventDefault();

        $("#editModal").modal('hide');

        $.ajax({
            type: "PUT",
            url: "",
            data: $('#editUserForm').serialize(),
        }).done(function(fragment){
            $("#userTable").replaceWith(fragment);
            $('.modal-backdrop').remove();
        })
    })

    $("#updateTrackButton").on('click', function(event){

        event.preventDefault();
        let id = $('#updateTrackId').val();
        let trackName = $("#songTitle").val();
        let trackNumber = $("#updateTrackNumber").val();
        let albumId = $("#updateAlbumId").val();
        let vocals;
        let guitar;
        let drums;
        let bass;
        if (document.getElementById('editVocalCheck').checked)
        {
            vocals = true;
        }
        else
        {
            vocals = false;
        }
        if (document.getElementById('editGuitarCheck').checked)
        {
            guitar = true;
        }
        else
        {
            guitar = false;
        }
        if (document.getElementById('editBassCheck').checked)
        {
            bass = true;
        }
        else
        {
            bass = false;
        }
        if (document.getElementById('editDrumsCheck').checked)
        {
            drums = true;
        }
        else
        {
            drums = false;
        }

        let jsonData = {id: id, trackName: trackName, trackNumber: trackNumber, albumId: albumId, vocals: vocals, guitar: guitar, bass: bass, drums: drums};

        alert(JSON.stringify(jsonData));

        $.post({
            url: "comingsoon/trackupdate",
            data: JSON.stringify(jsonData),
            contentType: 'application/json'
        }).done(function(fragment){
            $("#comingSoonMenu").replaceWith(fragment);
            $('.modal-backdrop').remove();
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