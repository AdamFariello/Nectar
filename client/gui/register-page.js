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
        alert("You have successfully registered.");
        location.reload();
    } else {
        registerErrorMsg.style.opacity = 1;
    }
})