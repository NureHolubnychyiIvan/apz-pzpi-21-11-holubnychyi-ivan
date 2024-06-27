import React from 'react';
import '../styles/registration.css';
import { register } from '../logic/registractionLogic.js'
import { useTranslation } from 'react-i18next';

function RegistrationForm() {

  const {t} = useTranslation();

  return (
    <div>
      <header className="header_r">
          <div className="conteiner_r">
            <a href="./" className="logo_r">
              <img src="./logo.jpg" alt="logo" />
            </a>
            <a href="/sign-in" className="nav_r">
              {t('footerUser.sign')}
            </a>
          </div>
        </header>

        <div className="main-content_r">
          <div className="formBlock_r">
            <h2 className="form-title_r">{t('register.label')}</h2>
            <form onSubmit={register} className="form" encType="multipart/form-data">
              <input type="text" id="name_r" name="name" placeholder={t('settingsPage.name')} required />
              <br />
              <br />
              <input type="text" id="surname_r" name="surname" placeholder={t('settingsPage.surname')} required />
              <br />
              <br />
              <input type="text" id="fatherName_r" name="fatherName" placeholder= {t('settingsPage.fatherName')} />
              <br />
              <br />
              <input type="email" id="username_r" name="username" placeholder={t('settingsPage.email')} required />
              <br />
              <br />
              <input type="password" id="password_r" name="password" placeholder={t('settingsPage.password')} required />
              <br />
              <br />
              <input type="password" id="passwordAgain_r" name="passwordAgain" placeholder={t('settingsPage.passwordAgain')} required />
              <br />
              <br />
              <input type="text" id="numberContract_r" name="numberContract" placeholder={t('settingsPage.numberContract')} required />
              <br />
              <br />
              <input
              type="tel"
              id="phoneNumber_r"
              name="phoneNumber"
              placeholder={t('settingsPage.numberPhone')}
              pattern="[0-9]{9}"
              required
            />
            <br />
              <br />
              <p>{t('register.photo')}</p>
              <input type="file" id="image_r" name="image" accept="image/*" required />
              <br />
              <br />                  
              <input type="submit" value={t('register.buttonSend')} />
            </form>
          </div>
        </div>

        <footer className="footer_r">
          <p style={{ fontSize: '20pt' }}>{t('bar.project')}</p>
          <div className="contact-info_r">
            <p>{t('bar.contacts')}</p>
            <ul>
              <li>pavlo.kokhanevych@nure.ua</li>
            </ul>
          </div>
        </footer>
    </div>
  );
}

export default RegistrationForm;
