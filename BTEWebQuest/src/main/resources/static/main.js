function deleteItem(id)
{
    $.get("delete/"+id, function(fragment){
        $("#userTable").replaceWith(fragment);
    })
}