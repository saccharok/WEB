$(document).ready(function() {

    var id = document.getElementById("herbId").getAttribute("value");
    $.ajax({
        type: "GET",
        url: "/ajax/herb/" + id
    }).done(function (response) {
        var data = JSON.parse(response);

        $("#name").text(data.name);
        $("#description").text(data.description);
        $("#researchers").text(data.researchers);
        $("#status").text(data.status);
        $("#preserves").text(data.preserves);
        alert("Page was load by ajax!");
    });
});