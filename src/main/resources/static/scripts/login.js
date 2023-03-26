function openTab(evt, tabName) {
    var i, tabContent, tabLinks;

    tabContent = document.getElementsByClassName("tab-content");
    for (i = 0; i < tabContent.length; i++) {
        tabContent[i].style.display = "none";
    }

    tabLinks = document.getElementsByClassName("tab-links");
    for (i = 0; i < tabLinks.length; i++) {
        tabLinks[i].className = tabLinks[i].className.replace(" active", "");
    }

    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

function getStudentsByGroup() {
    var group = $("#groups").val();
    $.get("/students?group=" + group, function(data) {
        $("#students").empty();
        data.forEach(function(item, i) {
            var option = "<option>" + item + "</option>";
            $("#students").append(option);
        });
    });
}