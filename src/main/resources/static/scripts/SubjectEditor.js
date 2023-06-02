
//	Функция удаления элементов дисциплины
function deleteElement(id, elementType) {
	if (confirm("Вы действительно хотите удалить данный элемент?")) {
	    $.ajax({
	        type: "DELETE",
	        url: "/api/" + elementType + "?id=" + id,
	        success: function(data) { $("#" + elementType + id).remove(); },
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


//	Методические указания
//	Функция создания методического указания
function openManualCreateDialog(themeId) {
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
					data.append("themeId", themeId);
					data.append("displayName", $("#manual-create-name-" + themeId).val());
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
function openManualUpdateDialog(id) {
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
//	Функция создания стороннего ресурса
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
				var displayName = $("#external-create-name-" + themeId).val();
				var url = $("#external-create-url-" + themeId).val();
				var type = $("#external-create-type-" + themeId).val();
				$.ajax({
				    type: "POST",
				    url: "/api/external?displayName=" + displayName + "&url=" + url + "&typeId=" + type + "&themeId=" + themeId,
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
	    toolbar2: 'undo redo | outdent indent | styles fontsize | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist | forecolor backcolor emoticons | link image media',
	    toolbar3: 'table tabledelete | tableprops tablerowprops tablecellprops | tableinsertrowbefore tableinsertrowafter tabledeleterow | tableinsertcolbefore tableinsertcolafter tabledeletecol',
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
		    		var themeId = editor.id.split('-')[1];
		    		var data = new FormData();
					data.append("description", description);
					$.ajax({
					    type: "PUT",
					    url: "/api/theme/" + themeId + "/description",
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
});