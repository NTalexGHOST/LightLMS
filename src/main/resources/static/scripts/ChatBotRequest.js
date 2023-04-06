document.querySelector("#input-message").addEventListener("keyup", event => {
    if(event.key !== "Enter") return;
    var request = $("#input-message").val();
    var chatHistory = document.querySelector('#chat-history');
    $('#input-message').val("");
    $("#chat-history").append("<div class='message right'><img src='/images/Студент.png'><p class='can-copied'>" + request + "</p></div>");
    chatHistory.lastChild.scrollIntoView();
    sendRequest();
    $.ajax({
        type: "GET",
        url: "/api/openai/chat?request=" + request,
        success: function(data) {
            $("#chat-history").append("<div class='message left'><img src='/images/chatgpt.webp'><p class='can-copied'>" + data + "</p></div>");
            chatHistory.lastChild.scrollIntoView();
            sendRequest();
        },
        error: function() {
            $("#chat-history").append("<div class='message left'><img src='/images/chatgpt.webp'><p class='can-copied'>" +
            "К сожалению, время ожидания было превышено, попробуйте еще раз" +
            "</p></div>");
            chatHistory.lastChild.scrollIntoView();
            sendRequest();
        },
        timeout: 60000
    });
});