import { TOKENS, USER } from '../../pages/utils/GlobalValue.js';
import {validateForm} from '../../pages/utils/Validating.js'


export async function signIn(e, history) {
    e.preventDefault();
    
    const isValid = validateForm(); 
    if (isValid) {
        try {
            const response = await sendRequestsignIn();

            saveInfoUser(response);

            const responseTypeUser = await sendRequestUser(); 
            openCorrectAccount(responseTypeUser, history);
            
        } catch (error) {
            console.error('Помилка при відправці запиту на бекенд:', error);
            return false;
        }
    }
}

async function sendRequestsignIn() {
    const email = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    
    const data = {
        email: email,
        password: password,
    };

    return await fetch('http://localhost:8080/authentication/signIn', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
}


async function sendRequestUser() {
    return await fetch(`http://localhost:8080/account/${USER.email}`, {
        method: 'GET',
        headers: {
            Authorization: `Bearer ${TOKENS.accessToken}`,
            'Content-Type': 'application/json',
        },
    });
}


async function saveInfoUser(response) {
    const responseData = await response.json();

    TOKENS.accessToken = responseData.token;
    TOKENS.refreshToken = responseData.refreshToken;

    USER.email = document.getElementById("username").value;
    return true;
}

async function openCorrectAccount(responseTypeUser, history) {
   const responseUser = await responseTypeUser.json();
   

  switch (responseUser.type) {
    case 'DATABASE_ADMIN':
      history.push('./mainForm'); 
      break;
    case 'REGULAR_USER':
      history.push('./main'); 
      break;
    
    default:
      console.error('Unknown user type:', responseUser.type);
      break;
  }

  return true;
}
