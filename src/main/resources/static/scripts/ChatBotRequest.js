document.querySelector("#input-message").addEventListener("keyup", event => {
    if(event.key !== "Enter") return;
    var request = $("#input-message").val();
    $('#input-message').val("");
    $("#chat-history").append("<div class='message right'><img src='/images/profile-user.png'><p>" + request + "</p></div>");
    $.ajax({
        type: "GET",
        url: "/api/chat-bot?request=" + request,
        success: function(data) {
            $("#chat-history").append("<div class='message left'><img src='/images/chatgpt.webp'><p>" + data + "</p></div>");
        },
        error: function() {
            $("#chat-history").append("<div class='message left'><img src='/images/chatgpt.webp'><p>" +
            "К сожалению, время ожидания было превышено, попробуйте еще раз" +
            "</p></div>");
        },
        timeout: 10000
    });
});