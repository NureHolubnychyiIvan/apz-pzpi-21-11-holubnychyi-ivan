import React from 'react';
import '../../styles/sign.css';
import { useTranslation } from 'react-i18next';


function MainForm() {

  const { t} = useTranslation();
  
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

      <div className="main-content">
        <h1>{t('footerUser.welcome')}</h1>
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

export default MainForm;
