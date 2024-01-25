$(document).ready(function () {
    $(".btn-danger").click(function(event) {
        event.preventDefault();
        var id = document.getElementById('herbId').getAttribute('value');
        $.ajax({
            type: "DELETE",
            url: "/herb/" + id
        }).done(function (response) {
            window.location.href = "/index-admin";
            alert(response);
        });
    });
});
