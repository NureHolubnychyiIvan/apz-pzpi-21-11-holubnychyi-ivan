import React, { useState, useEffect } from 'react';
import '../../styles/registration.css';
import { saveChanges } from '../../logic/settingsSaver.js'
import { useTranslation } from 'react-i18next';
import { getUser } from '../../logic/settingsSaver.js';
import { USER } from '../../../pages/utils/GlobalValue';

function SettingForm() {
    const { t, i18n } = useTranslation();

    const [userData, setUserData] = useState({
        name: '',
        surname: '',
        fatherName: '',
        email: '',
        password: '',
        numberContract: '',
        phoneNumber: '',
    });

    useEffect(() => {
        async function fetchUserData() {
          const user = await getUser();

          console.log(user);

          if (user) {
            setUserData({
              userId: user.userId,
              name: user.name,
              surname: user.surname,
              fatherName: user.patronymic,
              email: user.email,
              password: user.passwordHash,
              numberContract: user.numberContract,
              phoneNumber: user.phoneNumber,
              });
            }

          console.log(userData);
        }

      fetchUserData();
    }, []);

    const handleLanguageChange = (e) => {
        const selectedLanguage = e.target.value;
        USER.language = e.target.value;
        setUserData({ ...userData, language: selectedLanguage });
        i18n.changeLanguage(selectedLanguage);
    };
    
  return (
    <div>
    <header className="header">
        <div className="conteiner">
          <a href="./main" className="logo">
            <img src="../logo.jpg" alt="logo" />
          </a>
          <a href="./fridges" className="nav">
            {t('footerUser.fridge')}
          </a>
          <a href="./food" className="nav">
            {t('footerUser.food')}
          </a>
          <a href="./history" className="nav">
            {t('footerUser.history')}
          </a>
          <a href="./autoOrder" className="nav">
            {t('footerUser.autoOrder')}
          </a>
          <a href="./subscription" className="nav">
            {t('footerUser.subscription')}
          </a>
          <div className="dropdown">
                <img src="../menu.png" alt="menu" className = "menu"/>
                <div className="dropdown-content">
                    <a href="./settings">{t('footerUser.settings')}</a>
                    <a href="/sign-in">{t('footerUser.exist')}</a>
                </div>
            </div>
        </div>
      </header>

        <div className="main-content_r">
          <div className="formBlock_r">
            <h2 className="form-title_r">{t('settingsPage.title')}</h2>
            <form onSubmit={ saveChanges } className="form">
            <input type="text" id="id_s" name="id" placeholder="ID" required  value={userData.userId}/>
              <br />
              <br />
            <input type="text" id="name_s" name="name" placeholder={t('settingsPage.name')} required  value={userData.name}  onChange={(e) => setUserData({ ...userData, name: e.target.value })}/>
              <br />
              <br />
              <input type="text" id="surname_s" name="surname" placeholder={t('settingsPage.fatherName')} required  value={userData.surname}  onChange={(e) => setUserData({ ...userData, surname: e.target.value })}/>
              <br />
              <br />
              <input type="text" id="fatherName_s" name="fatherName" placeholder={t('settingsPage.fatherName')} value={userData.fatherName}  onChange={(e) => setUserData({ ...userData, fatherName: e.target.value })}/>
              <br />
              <br />
              <input type="email" id="username_s" name="username" placeholder={t('settingsPage.email')} required value={userData.email}  onChange={(e) => setUserData({ ...userData, email: e.target.value })}/>
              <br />
              <br />
              <input type="password" id="password_s" name="password" placeholder={t('settingsPage.password')} required value={userData.password}  onChange={(e) => setUserData({ ...userData, password: e.target.value })}/>
              <br />
              <br />
              <input type="text" id="numberContract_s" name="numberContract" placeholder={t('settingsPage.numberContract')} required value={userData.numberContract}  onChange={(e) => setUserData({ ...userData, numberContract: e.target.value })}/>
              <br />
              <br />
              <input
              type="tel"
              id="phoneNumber_s"
              name="phoneNumber"
              placeholder={t('settingsPage.numberPhone')}
              pattern="[0-9]{12}"
              required
              value={userData.phoneNumber}
              onChange={(e) => setUserData({ ...userData, phoneNumber: e.target.value })}
            />
            <br />
            <br /> 
              <div>
              <label style={{ fontSize: '16pt' }} className='labelSet'>{t('settingsPage.languageLabel')}</label>
              <div>
                <input
                  type="radio"
                  id="english"
                  name="language"
                  value="en"
                  checked={USER.language === 'en'}
                  onChange={handleLanguageChange}
                />
                <label style={{ fontSize: '16pt', marginRight: '10px' }} htmlFor="english" className='labelSet'>{t('settingsPage.english')}</label>

                <input
                  type="radio"
                  id="ukrainian"
                  name="language"
                  value="uk"
                   checked={USER.language === 'uk'}
                  onChange={handleLanguageChange}
                />
                <label style={{ fontSize: '16pt' }} htmlFor="ukrainian" className='labelSet'>{t('settingsPage.ukrainian')}</label>
              </div>
            </div>

              <input type="submit" value={t('settingsPage.saveChanges')} className ="button_s"/>           
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

export default SettingForm;
