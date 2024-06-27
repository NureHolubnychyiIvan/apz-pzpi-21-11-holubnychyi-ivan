import React, { useState, useEffect } from 'react';
import '../../styles/fridges.css';
import { v4 as uuidv4 } from 'uuid';
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';

function FoodInFridgeForm() {

  const { t} = useTranslation();

   const [foodInFridge, setFoodInFridge] = useState([]);
   const { fridgeId } = useParams();

  useEffect(() => {

    /**  Call the function that will receive the list of refrigerators from the server
    and update the state of the component. */
    fetchFood();
  }, []); // An empty array means that the effect will be called only after the first rendering.

  const fetchFood = async () => {
    try {
      const response = await fetch(`http://localhost:8080/fridge/foodInside/${fridgeId}`, {
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
  
      setFoodInFridge(data);
    } catch (error) {
      console.error('Error fetching fridges:', error.message);
    }
  };


  return (
    <div>
      <header className="header">
        <div className="conteiner">
          <a href="./main" className="logo">
            <img src="../../../logo.jpg" alt="logo" />
          </a>
          <Link to={`./${fridgeId}/autoOrder`} className="nav">
              {t('mainFridge.autoOrder')}
          </Link>
          <Link to={`./${fridgeId}/historyUsing`} className="nav">
              {t('mainFridge.history')} 
          </Link>

          <div className="dropdown">
                <img src="../../../menu.png" alt="menu" className = "menu"/>
                <div className="dropdown-content">
                    <a href="./settings">{t('footerUser.settings')}</a>
                    <a href="/sign-in">{t('footerUser.exist')}</a>
                </div>
            </div>
        </div>
      </header>

      <div className="main-content">
        <div className="fridges-list_l">
          <h2>{t('mainFridge.title')} - {fridgeId}</h2>
          <ul>
            {foodInFridge.map((foodInFridge) => (
              <li key={uuidv4()
            }><h3>Name: {foodInFridge.name
            }, Number boxes: {foodInFridge.number_boxes}, Date validity: {foodInFridge.date_validity}, Date transaction: {foodInFridge.end_date}, User name: {foodInFridge.user_name}, Surname: {foodInFridge.surname}, Email: {foodInFridge.email}</h3></li>
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

export default FoodInFridgeForm;
