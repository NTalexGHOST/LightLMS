function sendRequest() {
    var group = $("#groups").val();
    $.get("/students?group=" + group, function(data) {
        $("#students").empty();
        data.forEach(function(item, i) {
            var option = "<option>" + item + "</option>";
            $("#students").append(option);
        });
    });
}