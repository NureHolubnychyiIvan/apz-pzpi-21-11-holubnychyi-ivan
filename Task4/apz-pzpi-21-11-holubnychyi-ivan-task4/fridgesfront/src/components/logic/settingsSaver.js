
export async function saveChanges(e) {
    e.preventDefault();
   
    try {
        await sendRequestSave();
    } catch (error) {
        console.error('Помилка при відправці запиту на бекенд:', error);
        return false;
    }
}

async function sendRequestSave() {
    let passwordHash = document.getElementById("password_s").value;
    let id = document.getElementById("id_s").value;
    let name = document.getElementById("name_s").value;
    let surname = document.getElementById("surname_s").value;
    let patronymic = document.getElementById("fatherName_s").value;
    let phoneNumber = document.getElementById("phoneNumber_s").value;
    let numberContract = document.getElementById("numberContract_s").value;
    let email = document.getElementById("username_s").value;

    const data = {
        userId: id,
        name,
        surname,
        patronymic,
        passwordHash,
        email,
        numberContract,
        phoneNumber,
        type: "REGULAR_USER"
    };

    const response = await fetch(`http://localhost:8080/user/account/${localStorage.getItem("email")}/update`, {
        method: 'POST',
        headers: {
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });

    if (response.ok) {
        alert('Зміни прийняті');
    } else {
        console.error('Помилка при реєстрації:', response.statusText);
        alert("Помилка");
    }
}

export async function getUser() {
    try {
        const response = await fetch(`http://localhost:8080/user/account/${localStorage.getItem("email")}`, {
            method: "GET",
            headers: {
                Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            const user = await response.json();
            return user;
        } else {
            console.error('Failed to get user ID.');
            return null;
        }
    } catch (error) {
        console.error('Error:', error);
        return null;
    }
}


