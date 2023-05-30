function addTooltipToLastMessage() {
    var lastMessage = document.querySelector('#chat-history').lastChild;
    tippy(lastMessage, {
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
    lastMessage.addEventListener('click', (e)=> {
        navigator.clipboard.writeText(e.target.innerText);
    });
}

document.querySelector("#input-chat-message").addEventListener("keyup", event => {
    if(event.key !== "Enter") return;
    var request = $("#input-chat-message").val();
    var chatHistory = document.querySelector('#chat-history');
    $('#input-chat-message').val("");
    $("#chat-history").append("<div class='message right'><img src='/images/Студент.png'><p class='can-copied'>" + request + "</p></div>");
    chatHistory.lastChild.scrollIntoView();
    addTooltipToLastMessage();
    $.ajax({
        type: "GET",
        url: "/api/openai/chat?request=" + request,
        success: function(data) {
            $("#chat-history").append("<div class='message left'><img src='/images/chatgpt.webp'><p class='can-copied'>" + data + "</p></div>");
            chatHistory.lastChild.scrollIntoView();
            addTooltipToLastMessage();
        },
        error: function() {
            $("#chat-history").append("<div class='message left'><img src='/images/chatgpt.webp'><p class='can-copied'>" +
            "К сожалению, время ожидания было превышено, попробуйте еще раз" +
            "</p></div>");
            chatHistory.lastChild.scrollIntoView();
            addTooltipToLastMessage();
        },
        timeout: 60000
    });
});