ws = new WebSocket("ws://localhost:8994/nectar/");

const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const username = loginForm.username.value;
    const password = loginForm.password.value;
    /*if (username === "user" && password === "pass") {
        ws.send("Logged in: user pass");
        alert("You have successfully logged in.");
        location.reload();
    } else {
        loginErrorMsg.style.opacity = 1;
    }*/
    var loginInfo = {Username:username, Password:password}
    var request = {message:"login", data:loginInfo}
    ws.send(JSON.stringify(request));
})

ws.onopen = function(){
    alert("Connection Established");
}

ws.onmessage = function(event){
    alert(event.data);
}

window.onbeforeunload = function(){
    ws.close();
}