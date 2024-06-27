export function validateFormRegister() {
    let firstPassword = document.getElementById("password_r").value;
    let secondPassword = document.getElementById("passwordAgain_r").value;
    let name = document.getElementById("name_r").value;
    let surname = document.getElementById("surname_r").value;
    let fatherName = document.getElementById("fatherName_r").value;
    let numberContract = document.getElementById("numberContract_r").value;
    let email = document.getElementById("username_r").value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (firstPassword !== secondPassword) {
        alert("Не правильно введені паролі");
        return false;
    } else if (name.length > 30 || surname.length > 30 || fatherName.length > 30 || numberContract.length > 20) {
        alert(" Поля: Ім'я, прізвище, по-батькові не більше 30 символів");
        return false;
    } else if (!emailRegex.test(email)) {
        alert("Невірний формат електронної пошти");
        return false;
    }
    return true;
}