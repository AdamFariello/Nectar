var websocket =  new WebSocket("ws://localhost:8080/test");
websocket.onmessage = function () {
    alert('test');
}