function openTab(evt, tabName, tabContent, tabLinks) {
    var i, tabContent, tabLinks;

    tabContent = document.getElementsByClassName(tabContent);
    for (i = 0; i < tabContent.length; i++) { tabContent[i].style.display = "none"; }

    tabLinks = document.getElementsByClassName(tabLinks);
    for (i = 0; i < tabLinks.length; i++) { tabLinks[i].className = tabLinks[i].className.replace(" active", ""); }

    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

// Переключение блокировки заднего фона при открытии и закрытии всплывающего окна
function blockBody() { document.body.style.overflowY="hidden"; }
function unlockBody() { document.body.style.overflowY="auto"; }


//  Группы
//  Поиск по группам
function groupSearch() {
    if(event.key !== "Enter") return;

}
//  Создание группы
function openGroupCreateDialog() {
    $('#group-create-dialog').dialog({
        draggable: false,
        resizable: false,
        modal: true,
        width: "auto",
        show: { effect: "fade", duration: 600 },
        hide: { effect: "fade", duration: 500 },
        position: { my: "center", at: "center", of: window },
        buttons: [{
            text: "Создать",
            click: function() {
                var name = $("#group-create-name").val();
                var year = $("#group-create-year").val();
                var type = $("#group-create-type").val();
                $.ajax({
                    type: "POST",
                    url: "/api/group?name=" + name + "&year=" + year + "&typeId=" + type,
                    success: function(data) { location.reload(); },
                    error: function() { alert("Произошла ошибка при создании"); },
                    timeout: 10000
                });
            }
        }]
    });
}


//  Пользователи
//  Поиск по пользователям
function userSearch() {
    if(event.key !== "Enter") return;

}
//  Создание пользователя
function openUserCreateDialog() {
    $('#user-create-dialog').dialog({
        draggable: false,
        resizable: false,
        modal: true,
        width: "auto",
        show: { effect: "fade", duration: 600 },
        hide: { effect: "fade", duration: 500 },
        position: { my: "center", at: "center", of: window },
        buttons: [{
            text: "Создать",
            click: function() {
                var data = new FormData();
                data.append("username", $("#user-create-username").val());
                data.append("password", $("#user-create-password").val());
                data.append("fullname", $("#user-create-fullname").val());
                data.append("roleId", $("#user-create-role").val());
                $.ajax({
                    type: "POST",
                    url: "/api/user",
                    data: data,
                    enctype: 'multipart/form-data',
                    contentType: false,
                    processData: false,
                    success: function(data) { location.reload(); },
                    error: function() { alert("Произошла ошибка при создании"); },
                    timeout: 10000
                });
            }
        }]
    });
}


//  Дисциплины
//  Поиск по дисциплинам
function subjectSearch() {
    if(event.key !== "Enter") return;

    var searchKey = $("#subject-search-input").val();
}
//  Создание дисциплины
function openSubjectCreateDialog() {
    $('#subject-create-dialog').dialog({
        draggable: false,
        resizable: false,
        modal: true,
        width: "auto",
        show: { effect: "fade", duration: 600 },
        hide: { effect: "fade", duration: 500 },
        position: { my: "center", at: "center", of: window },
        buttons: [{
            text: "Создать",
            click: function() {
                var name = $("#subject-create-name").val();
                var course = $("#subject-create-course").val();
                var semester = $("#subject-create-semester").val();
                $.ajax({
                    type: "POST",
                    url: "/api/subject?name=" + name + "&course=" + course + "&semester=" + semester,
                    success: function(data) { location.reload(); },
                    error: function() { alert("Произошла ошибка при создании"); },
                    timeout: 10000
                });
            }
        }]
    });
}
//  Назначение преподаватель для дисциплины
function openSubjectTeacherDialog(id) {
    $('#subject-teacher-dialog-' + id).dialog({
        draggable: false,
        resizable: false,
        modal: true,
        width: "auto",
        show: { effect: "fade", duration: 600 },
        hide: { effect: "fade", duration: 500 },
        position: { my: "center", at: "center", of: window }
    });
}