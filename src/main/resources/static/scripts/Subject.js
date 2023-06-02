
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


//	Функция фиксирования навигации по темам при скролле страницы
jQuery(function($) {
	$(window).scroll(function() {
		if($(this).scrollTop() > 197) { $('#themes-container').addClass('fixed'); }
		else if ($(this).scrollTop() < 197) { $('#themes-container').removeClass('fixed'); }
	});
});