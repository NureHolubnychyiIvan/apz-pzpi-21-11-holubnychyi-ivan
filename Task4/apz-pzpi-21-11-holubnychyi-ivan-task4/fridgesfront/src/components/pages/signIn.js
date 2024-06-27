import React from 'react';
import '../styles/sign.css';
import { useTranslation } from 'react-i18next';
import { useNavigate } from 'react-router-dom';

import { TOKENS, USER } from '../../pages/utils/GlobalValue.js';
import { validateForm } from '../../pages/utils/Validating.js';

function SignInForm() {

  const { t} = useTranslation();
  const navigate = useNavigate();

  const signIn = async (e) => {
    e.preventDefault();
    
    const isValid = validateForm(); 
    if (isValid) {
        try {
            await sendRequestSignIn();                      
        } catch (error) {
            console.error('Помилка при відправці запиту на бекенд:', error);
            return false;
        }
    }
  }

  const saveInfoUser = async(responseData) => {
    TOKENS.accessToken = responseData.token;
    TOKENS.refreshToken = responseData.refreshToken;
  
    USER.email = document.getElementById('username').value;

    localStorage.setItem("accessToken", responseData.token);
    localStorage.setItem("refreshToken", responseData.refreshToken);
    localStorage.setItem("email", document.getElementById('username').value);
    return true;
  }
  
  const sendRequestSignIn = async() => {
    const email = document.getElementById('username').value;
    const password = document.getElementById('password').value;
  
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
    },
    ).then(r => { return r.json()}
    ).then(response => {saveInfoUser(response)}
    ).then( () => {
       return sendRequestUser();
    }
    ).then( responseTypeUser => {
       return responseTypeUser.json()}
    ).then(resultUser => {
      openCorrectAccount(resultUser)
    });
  }
  
  const sendRequestUser = async () => {
    return await fetch(`http://localhost:8080/user/account/${document.getElementById('username').value}`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${TOKENS.accessToken}`,
        'Content-Type': 'application/json',
      },
    });
  }
  
  const openCorrectAccount = async(responseUser) => {
  
    switch (responseUser.type) {
      case 'DATABASE_ADMIN':
        navigate('/admin/mainForm');
        break;
      case 'REGULAR_USER':
        navigate('/user/main');
        break;
  
      default:
        console.error('Unknown user type:', responseUser.type);
        break;
    }
  
    return true;
  }

  return (
    <div>
      <header className="header">
        <div className="conteiner">
          <a href="/sign-in" className="logo">
            <img src="./logo.jpg" alt="logo" />
          </a>
          <a href="/sign-in" className="nav">
            {t('footerUser.sign')}
          </a>
        </div>
      </header>

      <div className="main-content">
        <div className="formBlock">
          <h2 className="form-title">{t('signIn.form-title')}</h2>
          <form onSubmit={signIn} className="form">
            <input
              type="email"
              id="username"
              name="username"
              placeholder={t('settingsPage.email')}
              required
            />
            <br />
            <br />
            <input
              type="password"
              id="password"
              name="password"
              placeholder={t('settingsPage.password')}
              required
            />
            <br />
            <br />
            <input type="submit" value="Увійти" />
          </form>
          <a href="./register" className="login-button">
            {t('signIn.login-button')}
          </a>
        </div>
      </div>

      <footer className="footer">
        <p style={{ fontSize: '20pt' }}>{t('bar.project')}</p>
        <div className="contact-info">
          <p>{t('bar.contacts')}</p>
          <ul>
            <li>pavlo.kokhanevych@nure.ua</li>
          </ul>
        </div>
      </footer>
    </div>
  );
}

export default SignInForm;
