function openTab(evt, tabName, tabContent, tabLinks) {
    var i, tabContent, tabLinks;

    tabContent = document.getElementsByClassName(tabContent);
    for (i = 0; i < tabContent.length; i++) {
        tabContent[i].style.display = "none";
    }

    tabLinks = document.getElementsByClassName(tabLinks);
    for (i = 0; i < tabLinks.length; i++) {
        tabLinks[i].className = tabLinks[i].className.replace(" active", "");
    }

    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

let elements = document.getElementsByClassName('copied');
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
        if(e.target.nodeName =="TD") { 
            navigator.clipboard.writeText(e.target.innerText); 
        }
    })
}