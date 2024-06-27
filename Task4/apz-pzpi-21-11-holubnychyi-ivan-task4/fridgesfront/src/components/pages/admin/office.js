import React, { useState, useEffect } from 'react';
import '../../styles/fridges.css';
import { v4 as uuidv4 } from 'uuid';
import { useTranslation } from 'react-i18next';

function OfficeForm() {

  const { t} = useTranslation();

   const [office, setOffice] = useState([]);

  useEffect(() => {

    /**  Call the function that will receive the list of refrigerators from the server
    and update the state of the component. */
    fetchFood();
  }, []); // An empty array means that the effect will be called only after the first rendering.

  const fetchFood = async () => {
    try {
      const response = await fetch(`http://localhost:8080/databaseAdmin/allOffice`, {
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
  
      setOffice(data);
    } catch (error) {
      console.error('Error fetching fridges:', error.message);
    }
  };

  const handleDeleteUser = async (officeId) => {
    try {
      await fetch(`http://localhost:8080/databaseAdmin/deleteOffice`, {
        method: 'POST',
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify([officeId]),
      });

      // Update user list after deletion
      setOffice((prevOffice) => prevOffice.filter((office) => office.officeId !== officeId));
    } catch (error) {
      console.error('Error deleting user:', error.message);
    }
  };


  return (
    <div>
      <header className="header">
        <div className="conteiner">
          <a href="./mainAdmin" className="logo">
            <img src="../../../logo.jpg" alt="logo" />
          </a>
          <a href="./office" className="nav">
            Offices
          </a>
          <a href="./model" className="nav">
            Models
          </a>
          <a href="./product" className="nav">
            Products
          </a>
          
          <div className="dropdown">
                <img src="../../../menu.png" alt="menu" className = "menu"/>
                <div className="dropdown-content">
                    <a href="/sign-in">{t('footerUser.exist')}</a>
                </div>
            </div>
        </div>
      </header>

      <div className="main-content">
        <div className="fridges-list_l">
          <h2>Offices</h2>
          <ul>
            {office.map((office) => (
              <li key={uuidv4()
            }><h3>Office id: {office.officeId}, Country: {office.country}, City: {office.city}, District: {office.district}, Street: {office.street}, Number house: {office.houseNumber},  Name company: {office.nameCompany}</h3>

            <button onClick={() => handleDeleteUser(office.officeId)}  >
                <img src="../../../basket.png" alt="basket"  className = "basket"/>
            </button>

            </li>
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

export default OfficeForm;
