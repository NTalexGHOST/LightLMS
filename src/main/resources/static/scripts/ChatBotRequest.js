document.querySelector("#input-message").addEventListener("keyup", event => {
    if(event.key !== "Enter") return;
    var request = $("#input-message").val();
    var chatHistory = document.querySelector('#chat-history');
    $('#input-message').val("");
    $("#chat-history").append("<div class='message right'><img src='/images/Студент.png'><p>" + request + "</p></div>");
    chatHistory.lastChild.scrollIntoView();
    $.ajax({
        type: "GET",
        url: "/api/openai/chat?request=" + request,
        success: function(data) {
            $("#chat-history").append("<div class='message left'><img src='/images/chatgpt.webp'><p>" + data + "</p></div>");
            chatHistory.lastChild.scrollIntoView();
        },
        error: function() {
            $("#chat-history").append("<div class='message left'><img src='/images/chatgpt.webp'><p>" +
            "К сожалению, время ожидания было превышено, попробуйте еще раз" +
            "</p></div>");
            chatHistory.lastChild.scrollIntoView();
        },
        timeout: 30000
    });
});