import React, { useState, useEffect } from 'react';
import '../../styles/fridges.css';
import { Link } from 'react-router-dom';
import { v4 as uuidv4 } from 'uuid';
import { useTranslation } from 'react-i18next';

function FridgesForm() {

  const { t} = useTranslation();

  const [fridges, setFridges] = useState([]);

  useEffect(() => {

    /**  Call the function that will receive the list of refrigerators from the server
    and update the state of the component. */
    fetchFridges();
  }, []); // An empty array means that the effect will be called only after the first rendering.

  const fetchFridges = async () => {
    try {
      const response = await fetch(`http://localhost:8080/user/fridges/${localStorage.getItem("email")}`, {
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

      console.log(data);
  
      setFridges(data);
    } catch (error) {
      console.error('Error fetching fridges:', error.message);
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
          <h2>{t('footerUser.fridge')}</h2>
          <ul>
            {fridges.map((fridge) => (
              <Link to={`./food-in-fridge/${fridge.fridgeId}`} className="custom-link">
              <li key={uuidv4()
                    }><h3>ID of fridge {fridge.fridgeId
                    }, Temperature: {fridge.temperature}, Humidity: {fridge.humidity}</h3>
                    
                    </li>  
              </Link>
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

export default FridgesForm;
