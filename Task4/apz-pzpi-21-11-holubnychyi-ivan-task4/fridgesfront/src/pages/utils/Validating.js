export function validateForm() {
    let password = document.getElementById("password").value;
    let email = document.getElementById("username").value;

    if (password.length > 16) {
        alert("Пароль не може бути більше 16 символів");
        return false;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert("Невірний формат електронної пошти");
        return false;
    }

    return true;
}