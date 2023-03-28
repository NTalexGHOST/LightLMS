function activeButton(evt) {

    var currentButton = document.getElementById(evt.currentTarget.id);
    if (currentButton.classList.contains("active")) currentButton.classList.remove("active");
    else evt.currentTarget.className += "active";
}