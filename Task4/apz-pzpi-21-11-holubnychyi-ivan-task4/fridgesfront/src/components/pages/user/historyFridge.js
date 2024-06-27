import React, { useState, useEffect } from 'react';
import '../../styles/fridges.css';
import { v4 as uuidv4 } from 'uuid';
import { useParams } from 'react-router-dom';
import { useTranslation } from 'react-i18next';


function HistoryFridgeForm() {

  const { t} = useTranslation();

   const [historyFridge, setHistoryFridge] = useState([]);
   const { fridgeId } = useParams();

  useEffect(() => {
    
    /**  Call the function that will receive the list of refrigerators from the server
    and update the state of the component. */
    fetchHistoryFridge();
  }, []); // An empty array means that the effect will be called only after the first rendering.

  const fetchHistoryFridge = async () => {
    try {
      const response = await fetch(`http://localhost:8080/fridge/transactions/${fridgeId}`, {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
            'Content-Type': 'application/json',
          },
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();
  
      setHistoryFridge(data);
    } catch (error) {
      console.error('Error fetching fridges:', error.message);
    }
  };

  return (
    <div>
      <header className="header">
        <div className="conteiner">
          <a href="./main" className="logo">
            <img src="../../../../../logo.jpg" alt="logo" />
          </a>
          <a href="#" className="nav">
            {t('mainFridge.autoOrder')}
          </a>
          <a href="#" className="nav">
            {t('mainFridge.history')}  
          </a>
          <div className="dropdown">
                <img src="../../../../../menu.png" alt="menu" className = "menu"/>
                <div className="dropdown-content">
                    <a href="./settings">{t('footerUser.settings')}</a>
                    <a href="/sign-in">{t('footerUser.exist')}</a>
                </div>
          </div>
        </div>
      </header>

      <div className="main-content">
        <div className="fridges-list_l">
          <h2>{t('mainFridge.history')}</h2>
          <ul>
            {historyFridge.map((historyFridge) => (
              <li key={uuidv4()
            }><h3>Date transaction: {historyFridge.end_date}, User name: {historyFridge.name}, Surname: {historyFridge.surname}, Email: {historyFridge.email}</h3></li>
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

export default HistoryFridgeForm;
