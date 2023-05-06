const registerForm = document.getElementById("register-form");
const registerButton = document.getElementById("register-form-submit");
const registerErrorMsg = document.getElementById("register-error-msg");

registerButton.addEventListener("click", (e) => {
    e.preventDefault();
    const username = registerForm.username.value;
    const username2 = registerForm.usernameTwo.value;
    const password = registerForm.password.value;
    const password2 = registerForm.passwordTwo.value;

    if (username === username2 && password === password2) {
        var registerInfo = { Username: username, Password: password }
        var request = { message: "register", data: registerInfo }
        chrome.runtime.sendMessage(request, function (response) {
            console.log(response);
            var jresponse = JSON.parse(response)
            if (jresponse.message === "Login Error") {
                loginErrorMsg.style.opacity = 1;
            }
            else {
                ws.send("Logged in: user pass");
                alert("You have successfully logged in.");
                location.href = "../view/home.html";
            }
        });
        alert("You have successfully registered.");
        location.href = "../view/home.html";
    } else {
        registerErrorMsg.style.opacity = 1;
    }
})