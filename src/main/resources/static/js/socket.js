$(function() {

    var ChatContext = function(endpoint, subscribePath) {
        this.endpoint = endpoint;
        this.subscribePath = subscribePath;
    };
    ChatContext.prototype = {
        connect : function(roomId) {
            this.stompClient = Stomp.over(new WebSocket(this.endpoint));
            this.stompClient.connect({}, this.onConnected.bind(this, roomId));
        },
        onConnected : function(roomId) {
            this.roomId = roomId;
            this.stompClient.subscribe("/res" + this.subscribePath + roomId, this.onAcceptMessage.bind(this));
        },
        onAcceptMessage : function(message) {
            $("#messages").append("<p/>" + message.body);
        },
        sendMessage : function(message) {
            if (!this.stompClient) {
                alert("接続されていません。");
                return false;
            }
            this.stompClient.send("/req" + this.subscribePath + this.roomId, {}, message);
        },
        close : function() {
            if (this.stompClient) {
                this.stompClient.disconnect();
                this.stompClient = null;
                this.roomId = null;
            }
            $("#messages").html("");
        }
    };
    var chatContext = new ChatContext('ws://' + location.host + '/chat', '/env/');
    $("#room").on("change", function() {
        if ($(this).val() == chatContext.roomId) {
            return;
        }
        if ($(this).val()) {
            chatContext.close();
            chatContext.connect($(this).val());
        } else {
            chatContext.close();
        }
    });
    $("#form").submit(function(){
        chatContext.sendMessage($("#message").val());
        $("#message").val("")
        return false;
    });
});