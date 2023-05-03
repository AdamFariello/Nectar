const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {
    ws.send("attempt login")
    e.preventDefault();
    const username = loginForm.username.value;
    const password = loginForm.password.value;
    if (username === "user" && password === "pass") {
        ws.send("Logged in: user pass");
        alert("You have successfully logged in.");
        location.reload();
    } else {
        loginErrorMsg.style.opacity = 1;
    }
})

window.onload = function() {
    ws = new WebSocket("ws://localhost:8080/nectar/");
}

window.onbeforeunload = function(){
    ws.close();
}