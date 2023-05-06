var port = prompt("Enter port number")
if (port != null){
    ws = new WebSocket("ws://localhost:" + port + "/nectar/");
}else{
    ws = new WebSocket("ws://localhost:8993/nectar/");
}


ws.onopen = function(){
    alert("Connection Established");
}

ws.onmessage = function(event){
    alert(event.data);
}

var sendJSONRequest = function (request){
    ws.send(JSON.stringify(request));
}


chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse){
        alert("received");
        sendJSONRequest(request);
        //alert(request);
        sendResponse({result: "received"});
    }
)