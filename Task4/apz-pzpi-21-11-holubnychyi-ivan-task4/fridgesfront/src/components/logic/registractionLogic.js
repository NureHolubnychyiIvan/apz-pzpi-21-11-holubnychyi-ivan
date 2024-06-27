import {validateFormRegister} from '../../pages/utils/RegisterValidation.js'

export async function register(e) {
    e.preventDefault();
    const isValid = validateFormRegister();
   
    if (isValid) {
        try {
            await sendRequestRegister();

        } catch (error) {
            console.error('Помилка при відправці запиту на бекенд:', error);
            return false;
        }
    }
}

async function sendRequestRegister() {
    let formData = new FormData();

    console.log(document.getElementById("password_r").value);
    formData.append('userId', 1);
    formData.append('name', document.getElementById("name_r").value);
    formData.append('surname', document.getElementById("surname_r").value);
    formData.append('patronymic', document.getElementById("fatherName_r").value);
    formData.append('passwordHash', document.getElementById("password_r").value);
    formData.append('email', document.getElementById("username_r").value);
    formData.append('numberContract', document.getElementById("numberContract_r").value);
    formData.append('photo', ""); 
    formData.append('phoneNumber', document.getElementById("phoneNumber_r").value);
    formData.append('type', "REGULAR_USER");
    
    let imageFile = document.getElementById("image_r").files[0];
    formData.append('file', imageFile);

    console.log(formData.values);
    const response = await fetch('http://localhost:8080/authentication/register', {
        method: 'POST',
        body: formData,
    });

    if (response.ok) {
        alert('Користувач успішно зареєстрований');
    } else {
        console.error('Помилка при реєстрації:', response.statusText);
        alert("Помилка");
    }
}