const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {

    alert("message sent");
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
    var loginInfo = { Username: username, Password: password }
    var request = { message: "login", data: loginInfo }
    chrome.runtime.sendMessage(request, function (response) {
        console.log(response);
        var jresponse = JSON.parse(response, function(key, value)){
            
        }
        if (jresponse.key === "message" && jresponse.value === "Login Error") {
            loginErrorMsg.style.opacity = 1;
        }
        else {
            ws.send("Logged in: user pass");
            alert("You have successfully logged in.");
            location.href = "../view/home.html";
        }
    });

})
