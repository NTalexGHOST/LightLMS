
//	Функция удаления элементов дисциплины
function deleteElement(id, elementType) {
	if (confirm("Вы действительно хотите удалить данный элемент?")) {
	    $.ajax({
	        type: "DELETE",
	        url: "/api/" + elementType + "?id=" + id,
	        success: function(data) { location.reload(); },
	        error: function() { alert("Произошла ошибка при удалении"); },
	        timeout: 10000
	    });
	}
}


//	Темы дисциплины
//	Функция создания темы
function openThemeCreateDialog(subjectId) {
	$('#theme-create-dialog').dialog({
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
				var name = $("#theme-create-name").val();
				$.ajax({
				    type: "POST",
				    url: "/api/theme?subjectId=" + subjectId + "&name=" + name,
				    success: function(data) { location.reload(); },
				    error: function() { alert("Произошла ошибка при создании"); },
				    timeout: 10000
				});
			}
		}]
	});
}
//	Функция изменения темы по ее id
function updateTheme(id) {
	var newName = $("#themeInput" + id).val();
	$.ajax({
	    type: "PUT",
	    url: "/api/theme?id=" + id + "&name=" + newName,
	    success: function(data) { },
	    error: function() { alert("Произошла ошибка при изменении"); },
	    timeout: 10000
	});
}


//	Экзамен
//	Функция изменения экзамена по его id
function updateExam(id) {
	var newName = $("#examInput").val();
	$.ajax({
	    type: "PUT",
	    url: "/api/exam?id=" + id + "&name=" + newName,
	    success: function(data) { },
	    error: function() { alert("Произошла ошибка при изменении"); },
	    timeout: 10000
	});
}


//	Методические указания
//	Функция создания методического указания для темы
function openManualCreateDialog(subjectId, themeId) {
	$('#manual-create-dialog-' + themeId).dialog({
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
				if ($('#manual-create-file-' + themeId).val()) {
					var data = new FormData();
					var file = document.getElementById("manual-create-file-" + themeId).files[0];
					data.append("subjectId", subjectId);
					data.append("displayName", $("#manual-create-name-" + themeId).val());
					data.append("themeId", themeId);
					data.append("file", file);
					$.ajax({
					    type: "POST",
					    url: "/api/manual",
					    data: data,
					    enctype: 'multipart/form-data',
					    contentType: false,
				  		processData: false,
					    success: function(response) { location.reload(); },
					    error: function() { alert("Произошла ошибка при создании"); },
					    timeout: 10000
					});
				} else { alert("Вы не выбрали файл"); }
			}
		}]
	});
}
//	Функция создания методического указания для экзамена
function openExamManualCreateDialog(subjectId, examId) {
	$('#exam-manual-create-dialog').dialog({
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
				if ($('#exam-manual-create-file').val()) {
					var data = new FormData();
					var file = document.getElementById("exam-manual-create-file").files[0];
					data.append("subjectId", subjectId);
					data.append("displayName", $("#exam-manual-create-name").val());
					data.append("examId", examId);
					data.append("file", file);
					$.ajax({
					    type: "POST",
					    url: "/api/manual",
					    data: data,
					    enctype: 'multipart/form-data',
					    contentType: false,
				  		processData: false,
					    success: function(response) { location.reload(); },
					    error: function() { alert("Произошла ошибка при создании"); },
					    timeout: 10000
					});
				} else { alert("Вы не выбрали файл"); }
			}
		}]
	});
}
//	Функция изменения методического указания
function openManualUpdateDialog(id, subjectId) {
	$('#manual-update-dialog-' + id).dialog({
		draggable: false,
		resizable: false,
		modal: true,
		width: "auto",
		show: { effect: "fade", duration: 600 },
		hide: { effect: "fade", duration: 500 },
		position: { my: "center", at: "center", of: window },
		buttons: [{
			text: "Изменить",
			click: function() {
				if ($('#manual-update-file-' + id).val()) { 
					var data = new FormData();
					var file = document.getElementById("manual-update-file-" + id).files[0];
					data.append("id", id);
					data.append("subjectId", subjectId)
					data.append("displayName", $("#manual-update-name-" + id).val());
					data.append("file", file);
					$.ajax({
						type: "PUT",
						url: "/api/manual/" + id,
						data: data,
						enctype: 'multipart/form-data',
						contentType: false,
					  	processData: false,
						success: function(response) { location.reload(); },
						error: function() { alert("Произошла ошибка при изменении"); },
						timeout: 10000
					});
				} else {
					var displayName = $("#manual-update-name-" + id).val();
					$.ajax({
					    type: "PUT",
					    url: "/api/manual?id=" + id + "&displayName=" + displayName,
					    success: function(data) { },
					    error: function() { alert("Произошла ошибка при изменении"); },
					    timeout: 10000
					});
				}
			}
		}]
	});
}


//	Сторонние ресурсы
//	Функция создания стороннего ресурса для темы
function openExternalCreateDialog(themeId) {
	$('#external-create-dialog-' + themeId).dialog({
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
				data.append("displayName", $("#external-create-name-" + themeId).val());
				data.append("url", $("#external-create-url-" + themeId).val());
				data.append("typeId", $("#external-create-type-" + themeId).val());
				data.append("themeId", themeId);
				$.ajax({
				    type: "POST",
				    url: "/api/external",
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
//	Функция создания стороннего ресурса для экзамена
function openExamExternalCreateDialog(examId) {
	$('#exam-external-create-dialog').dialog({
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
				data.append("displayName", $("#exam-external-create-name").val());
				data.append("url", $("#exam-external-create-url-").val());
				data.append("typeId", $("#exam-external-create-type-").val());
				data.append("examId", examId);
				$.ajax({
				    type: "POST",
				    url: "/api/external",
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
//	Функция изменения стороннего ресурса
function openExternalUpdateDialog(id) {
	$('#external-update-dialog-' + id).dialog({
		draggable: false,
		resizable: false,
		modal: true,
		width: "auto",
		show: { effect: "fade", duration: 600 },
		hide: { effect: "fade", duration: 500 },
		position: { my: "center", at: "center", of: window },
		buttons: [{
			text: "Изменить",
			click: function() {
				var displayName = $("#external-update-name-" + id).val();
				var url = $("#external-update-url-" + id).val();
				var type = $("#external-update-type-" + id).val();
				$.ajax({
					type: "PUT",
					url: "/api/external?id=" + id + "&displayName=" + displayName + "&url=" + url + "&typeId=" + type,
					success: function(data) { location.reload(); },
					error: function() { alert("Произошла ошибка при изменении данных"); },
					timeout: 10000
				});
			}
		}]
	});
}


//	Практические работы
//	Функция создания практической работы для темы
function openPracticeCreateDialog(themeId) {
	$('#practice-create-dialog-' + themeId).dialog({
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
				if ($('#practice-create-opening-' + themeId).val() && $('#practice-create-closing-' + themeId).val()) {
					var data = new FormData();
					data.append("displayName", $("#practice-create-name-" + themeId).val());
					data.append("openingDate", $("#practice-create-opening-" + themeId).val());
					data.append("closingDate", $("#practice-create-closing-" + themeId).val());
					var typeId = $("#practice-create-type-" + themeId).val();
					if (typeId == 5 || (typeId == 6 && $("#practice-create-duration-" + themeId).val())) {
						data.append("duration", $("#practice-create-duration-" + themeId).val());
						data.append("typeId", typeId);
						data.append("themeId", themeId);
						$.ajax({
						    type: "POST",
						    url: "/api/manual",
						    data: data,
						    enctype: 'multipart/form-data',
						    contentType: false,
					  		processData: false,
						    success: function(response) { location.reload(); },
						    error: function() { alert("Произошла ошибка при создании"); },
						    timeout: 10000
						});
					} else { alert("Вы выбрали тест, но не указали его продолжительность"); }
				} else { alert("Вы не выбрали время открытия или закрытия доступа к практической работе"); }
			}
		}]
	});
}
//	Функция создания практической работы для экзамена
function openExamPracticeCreateDialog(examId) {
	$('#exam-practice-create-dialog').dialog({
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
				if ($('#exam-practice-create-opening').val() && $('#exam-practice-create-closing').val()) {
					var data = new FormData();
					var file = document.getElementById("manual-create-file-" + themeId).files[0];
					data.append("subjectId", subjectId);
					data.append("displayName", $("#manual-create-name-" + themeId).val());
					data.append("themeId", themeId);
					data.append("file", file);
					$.ajax({
					    type: "POST",
					    url: "/api/manual",
					    data: data,
					    enctype: 'multipart/form-data',
					    contentType: false,
				  		processData: false,
					    success: function(response) { location.reload(); },
					    error: function() { alert("Произошла ошибка при создании"); },
					    timeout: 10000
					});
				} else { alert("Вы не выбрали время открытия или закрытия доступа к практической работе"); }
			}
		}]
	});
}


//	Функция фиксирования навигации по темам при скролле страницы
jQuery(function($) {
	$(window).scroll(function() {
		if($(this).scrollTop() > 197) { $('#themes-container').addClass('fixed'); }
		else if ($(this).scrollTop() < 197) { $('#themes-container').removeClass('fixed'); }
	});
});


//	Функция, вызываемая при загрузке документа
$(document).ready(function() {
	//	Функция реализации перетаскивания элементов тем
	$(".resource-items").sortable({
		items: ".resource-item",
		handle: ".movable-item",
		cursor: "move",
		axis: "y",
		stop: function(event, ui) {
			var items = ui.item.parent().children();
			var elementType = ui.item.attr("id").split('-')[0];
			var positions = [];
			for (item of items) { positions.push(parseInt(item.id.split('-')[1])); }
			var data = new FormData();
			data.append("positions", positions);
			$.ajax({
			    type: "PUT",
			    url: "/api/" + elementType + "/position",
			    data: data,
			    enctype: 'multipart/form-data',
			    contentType: false,
		  		processData: false,
			    timeout: 10000
			});
		}
	});
	//	Функция реализации перетаскивания тем
	$(".subject-chapters").sortable({
		items: ".subject-chapter",
		handle: ".movable-chapter",
		cancel: ".disabled",
		cursor: "move",
		axis: "y",
		tolerance: "pointer",
		stop: function(event, ui) {
			var items = ui.item.parent().children();
			var positions = [];
			for (item of items) { positions.push(parseInt(item.id.split('-')[1])); }
			var data = new FormData();
			data.append("positions", positions);
			$.ajax({
			    type: "PUT",
			    url: "/api/theme/position",
			    data: data,
			    enctype: 'multipart/form-data',
			    contentType: false,
		  		processData: false,
			    timeout: 10000
			});
		}
	});
	tinymce.init({
	    selector: 'textarea',
	    min_height: 500, 
	    plugins: 'link lists media image emoticons table',
	    toolbar1: 'customSaveButton',
	    toolbar2: 'undo redo | outdent indent | styles fontsize | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist table | forecolor backcolor emoticons | link image media',
	    toolbar_mode: 'wrap',
	    menubar: false,
	    language_url: '/libs/tinymce/langs/ru.js',
	    language: 'ru',
	    setup: (editor) => {
		    editor.ui.registry.addButton('customSaveButton', {
		      	text: 'Сохранить текст',
		      	icon: 'save',
		      	onAction: function() { 
		    		var description = editor.getContent();
		    		var elementId = editor.id.split('-');
		    		var elementType = elementId[1];
		    		var id = elementId[2];
		    		var data = new FormData();
					data.append("description", description);
					$.ajax({
					    type: "PUT",
					    url: "/api/" + elementType + "/" + id + "/description",
					    data: data,
					    enctype: 'multipart/form-data',
					    contentType: false,
				  		processData: false,
					    timeout: 10000
					});
		    	}
		    });
		}    
	});
	$(".datetimepicker").datetimepicker({
		dateFormat: "dd.mm.yy"
	});
	$(".timepicker").timepicker({

	});
});