$('document').ready(function() {

    $.ajaxSetup({
        cache: false,
    });

    // Class to populate and open Track modal
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
            document.getElementById('deleteTrackButton').onclick = function() {deleteTrack(track.id); return false;}
        })

        $('#editTrackModal').modal('show');
    })

    // Class to open Edit Album modal
    $('.albumclass').on('click',function(event){

        event.preventDefault();

        $('#editAlbumModal').modal('show');
    })

    // Update Track via Ajax
    $("#updateTrackButton").on('click', function(event){
        event.preventDefault();

        const form = $('#editTrackForm');
        form.validate();

        let id = $('#updateTrackId').val();
        let trackName = $("#songTitle").val();
        let trackNumber = $("#updateTrackNumber").val();
        let albumId = $("#updateAlbumId").val();
        let vocals = false;
        let guitar = false;
        let drums = false;
        let bass = false;
        if (document.getElementById('editVocalCheck').checked)
        {
            vocals = true;
        }
        if (document.getElementById('editGuitarCheck').checked)
        {
            guitar = true;
        }
        if (document.getElementById('editBassCheck').checked)
        {
            bass = true;
        }
        if (document.getElementById('editDrumsCheck').checked)
        {
            drums = true;
        }

        let jsonData = {id: id, trackName: trackName, trackNumber: trackNumber, albumId: albumId, vocals: vocals, guitar: guitar, bass: bass, drums: drums};

        if (form.valid())
        {
            $.post({
                url: "comingsoon/trackupdate",
                data: JSON.stringify(jsonData),
                contentType: 'application/json'
            }).done(function(fragment){
                $("#comingSoonMenu").replaceWith(fragment);
                $("body").css("overflow", "auto");
                $('.modal-backdrop').remove();
            })
        }

    })

    // Subject can't be empty or less than 3 characters
    $('#albumTitle').on('input', function() {
        const album= document.getElementById('albumTitle');
        const input = $(this);
        if(album.validity.valid) {
            input.removeClass("invalid").addClass("valid");
        }
        else {
            input.removeClass("valid").addClass("invalid");
        }
    });

    // Update Album via Ajax
    $("#doUpdateAlbumButton").on('click', function(event){

        event.preventDefault();

        const form = $('#testForm');
        form.validate();

        let id = $('#updateAlbumAlbumId').val();
        let albumTitle = $("#albumTitle").val();
        let albumYear = $("#albumYear").val();
        let mixed = false;
        let mastered = false;
        if (document.getElementById('editIsMixedCheck').checked)
        {
            mixed = true;
        }
        if (document.getElementById('editIsMasteredCheck').checked)
        {
            mastered = true;
        }

        let jsonData = {id: id, albumName: albumTitle, albumYear: albumYear, mixed: mixed, mastered: mastered};

        if(form.valid())
        {
            $.post({
                url: "comingsoon/albumupdate",
                data: JSON.stringify(jsonData),
                contentType: 'application/json'
            }).done(function(fragment){
                $("#comingSoonMenu").replaceWith(fragment);
                $("body").css("overflow", "auto");
                $('.modal-backdrop').remove();
            })
        }
    })
})

// Delete track by ID
function deleteTrack(id)
{
    $.ajax({
        url: 'comingsoon/deletetrack/'+id,
        type: 'DELETE',
        contentType:'application/json',
        success: function(fragment) {
            $("#comingSoonMenu").replaceWith(fragment);
            $("body").css("overflow", "auto");
            $('.modal-backdrop').remove();
        }
    });
}

// Function to add Track via ID
function addTrack(id)
{
    $.ajax({
        url: 'comingsoon/addtrack/'+id,
        type: 'PUT',
        contentType:'application/json',
        success: function(fragment) {
            $("#comingSoonMenu").replaceWith(fragment);
        }
    });
}