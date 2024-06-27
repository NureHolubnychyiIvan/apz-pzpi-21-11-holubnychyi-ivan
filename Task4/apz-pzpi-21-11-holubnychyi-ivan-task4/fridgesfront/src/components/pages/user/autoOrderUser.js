import React, { useState, useEffect } from 'react';
import '../../styles/fridges.css';
import { v4 as uuidv4 } from 'uuid';
import { useTranslation } from 'react-i18next';

function AutoOrderUserForm() {

  const { t} = useTranslation();

   const [autoOrderUser, setAutoOrderUser] = useState([]);

  useEffect(() => {

    /**  Call the function that will receive the list of refrigerators from the server
    and update the state of the component. */
    fetchOrder();
  }, []); // An empty array means that the effect will be called only after the first rendering.

  const fetchOrder = async () => {
    try {
      const email = localStorage.getItem("email");
      const token = localStorage.getItem("accessToken");

      const response = await fetch(`http://localhost:8080/user/history/orders/${email}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
        },
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();

      setAutoOrderUser(data);
    } catch (error) {
      console.error('Error fetching order:', error.message);
    }
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

      <div className="main-content">
        <div className="fridges-list_l">
          <h2>{t('autoOrderFridge.title_user')}</h2>
          <ul>
            {autoOrderUser.map((autoOrderUser) => (
              <li key={uuidv4()
            }><h3>
            Date delivery: {`${autoOrderUser.date_delivery}`}, Amount of product: {`${autoOrderUser.number}`},
            ID of fridge: {autoOrderUser.fridge_access}, Name: {autoOrderUser.name}, Weight: {autoOrderUser.weight}
          </h3></li>
            ))}
          </ul>
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

export default AutoOrderUserForm;
