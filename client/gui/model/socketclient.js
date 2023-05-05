ws = new WebSocket("ws://localhost:8080/nectar/");

ws.onopen = function(){
    alert("Connection Established");
}

ws.onmessage = function(event){
    alert(event.data);
}

window.onbeforeunload = function(){
    ws.close();
}

var sendJSONRequest = function (request){
    ws.send(JSON.stringify(request));
}

module.exports(sendJSONRequest);