$(document).ready(function () {
    $("#herbForm").on('submit', function (event) {
        event.preventDefault();
        let formData = new FormData(this);
        $.ajax({
            type: "POST",
            url: "/ajax/add-herb",
            data: formData,
            processData: false,
            contentType: false
        }).done(function (response) {
            window.location.href = "/";
            alert(response.name + 'has been added to DB!');
        });
    });
});