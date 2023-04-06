let elements = document.getElementsByClassName('can-copied');
for (i = 0; i < elements.length; i++) {
    tippy(elements[i], { 
        content: 'Текст успешно сохранен в буфер обмена', 
        trigger: 'click', 
        duration: 500, 
        animation: 'fade', 
        onShow(instance) {
            setTimeout(() => {
                instance.hide();
            }, 1000);
        }
    });
    elements[i].addEventListener('click', (e)=> {
        navigator.clipboard.writeText(e.target.innerText);
    })
}