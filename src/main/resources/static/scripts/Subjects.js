
//	Функция удаления элементов дисциплины
function deleteElement(id, elementType) {
	if (confirm("Вы действительно хотите удалить данный элемент?")) {
	    $.ajax({
	        type: "DELETE",
	        url: "/api/" + elementType + "?id=" + id,
	        success: function(data) {
	            $("#" + elementType + id).remove();
	        },
	        error: function() {
	            alert("Произошла ошибка при удалении");
	        },
	        timeout: 10000
	    });
	}
}

//	Функция показа Word-документа во всплывающем окне
function openDoc(docId) {
	$('#doc-dialog-' + docId).dialog({
		dialogClass: "document-viewer",
		draggable: false,
		resizable: false,
		modal: true,
		width: "auto",
		show: { effect: "fade", duration: 600 },
		hide: { effect: "fade", duration: 500 },
		position: { my: "center", at: "center", of: window }
	});
}

//	Функция показа Youtube-видео во всплывающем окне
function openVideo(videoId) {
	$('#video-dialog-' + videoId).dialog({
		dialogClass: "document-viewer",
		draggable: false,
		resizable: false,
		modal: true,
		width: "auto",
		show: { effect: "fade", duration: 600 },
		hide: { effect: "fade", duration: 500 },
		position: { my: "center", at: "center", of: window }
	});
}


//	Темы дисциплины
//	Функция показа окна для создания темы
function openThemeCreateDialog(subjectId) {
	$('#theme-create-dialog').dialog({
		draggable: false,
		resizable: false,
		modal: true,
		width: "auto",
		show: { effect: "fade", duration: 600 },
		hide: { effect: "fade", duration: 500 },
		position: { my: "center", at: "center", of: window },
		buttons: [
			{
				text: "Создать",
				click: function() {
					createTheme(subjectId);
				}
			}
		]
	});
}
//	Функция создания темы
function createTheme(subjectId) {
	var name = $("#theme-create-name").val();
	$.ajax({
	    type: "POST",
	    url: "/api/theme?subjectId=" + subjectId + "&name=" + name,
	    success: function(data) {
	        location.reload();
	    },
	    error: function() {
	        alert("Произошла ошибка при создании");
	    },
	    timeout: 10000
	});
}

//	Функция изменения темы по ее id
function updateTheme(id) {
	var newName = $("#themeInput" + id).val();
	$.ajax({
	    type: "PUT",
	    url: "/api/theme?id=" + id + "&name=" + newName,
	    success: function(data) {
	        
	    },
	    error: function() {
	        alert("Произошла ошибка при изменении");
	    },
	    timeout: 10000
	});
}


//	Методические указания
//	Функция показа окна для создания методического указания
function openManualCreateDialog(themeId) {
	$('#manual-create-dialog-' + themeId).dialog({
		draggable: false,
		resizable: false,
		modal: true,
		width: "auto",
		show: { effect: "fade", duration: 600 },
		hide: { effect: "fade", duration: 500 },
		position: { my: "center", at: "center", of: window },
		buttons: [
			{
				text: "Создать",
				click: function() {
					createManual(themeId);
				}
			}
		]
	});
}
//	Функция создания методического указания
function createManual(themeId) {
	var data = new FormData();
	data.append("themeId", themeId);
	data.append("displayName", $("#manual-create-name-" + themeId).val());
	data.append("file", document.getElementById("manual-create-file-" + themeId).files[0]);
	$.ajax({
	    type: "POST",
	    url: "/api/manual",
	    data: data,
	    enctype: 'multipart/form-data',
	    contentType: false,
  		processData: false,
	    success: function(response) {
	        location.reload();
	    },
	    error: function() {
	        alert("Произошла ошибка при создании");
	    },
	    timeout: 10000
	});
}

//	Функция показа окна для изменения методического указания
function openManualUpdateDialog(id) {
	$('#manual-update-dialog-' + id).dialog({
		draggable: false,
		resizable: false,
		modal: true,
		width: "auto",
		show: { effect: "fade", duration: 600 },
		hide: { effect: "fade", duration: 500 },
		position: { my: "center", at: "center", of: window },
		buttons: [
			{
				text: "Изменить",
				click: function() {
					updateManual(id);
				}
			}
		]
	});
}
//	Функция изменения методического указания
function updateManual(id) {
	var displayName = $("#manual-update-name-" + id).val();
	var url = $("#manual-update-url-" + id).val();
	var type = $("#manual-update-type-" + id).val();
	$.ajax({
	    type: "PUT",
	    url: "/api/manual?id=" + id + "&displayName=" + displayName + "&url=" + url + "&typeId=" + type,
	    success: function(data) {
	        
	    },
	    error: function() {
	        alert("Произошла ошибка при изменении");
	    },
	    timeout: 10000
	});
}

//	Сторонние ресурсы
//	Функция показа окна для создания стороннего ресурса
function openExternalCreateDialog(themeId) {
	$('#external-create-dialog-' + themeId).dialog({
		draggable: false,
		resizable: false,
		modal: true,
		width: "auto",
		show: { effect: "fade", duration: 600 },
		hide: { effect: "fade", duration: 500 },
		position: { my: "center", at: "center", of: window },
		buttons: [
			{
				text: "Создать",
				click: function() {
					createExternal(themeId);
				}
			}
		]
	});
}
//	Функция создания стороннего ресурса
function createExternal(themeId) {
	var displayName = $("#external-create-name-" + themeId).val();
	var url = $("#external-create-url-" + themeId).val();
	var type = $("#external-create-type-" + themeId).val();
	$.ajax({
	    type: "POST",
	    url: "/api/external?displayName=" + displayName + "&url=" + url + "&typeId=" + type + "&themeId=" + themeId,
	    success: function(data) {
	        location.reload();
	    },
	    error: function() {
	        alert("Произошла ошибка при создании");
	    },
	    timeout: 10000
	});
}

//	Функция показа окна для изменения стороннего ресурса
function openExternalUpdateDialog(id) {
	$('#external-update-dialog-' + id).dialog({
		draggable: false,
		resizable: false,
		modal: true,
		width: "auto",
		show: { effect: "fade", duration: 600 },
		hide: { effect: "fade", duration: 500 },
		position: { my: "center", at: "center", of: window },
		buttons: [
			{
				text: "Изменить",
				click: function() {
					updateExternal(id);
				}
			}
		]
	});
}
//	Функция изменения стороннего ресурса
function updateExternal(id) {
	var displayName = $("#external-update-name-" + id).val();
	var url = $("#external-update-url-" + id).val();
	var type = $("#external-update-type-" + id).val();
	$.ajax({
	    type: "PUT",
	    url: "/api/external?id=" + id + "&displayName=" + displayName + "&url=" + url + "&typeId=" + type,
	    success: function(data) {
	        $("#external-update-name-" + id).value = displayName;
	        $("#external-update-url-" + id).value = url;
	    },
	    error: function() {
	        alert("Произошла ошибка при изменении данных");
	    },
	    timeout: 10000
	});
}


//	Функция фиксирования навигации по темам при скролле страницы
jQuery(function($) {
	$(window).scroll(function() {
		if($(this).scrollTop() > 197) {
			$('#themes-container').addClass('fixed');
		}
		else if ($(this).scrollTop() < 197) {
			$('#themes-container').removeClass('fixed');
		}
	});
});