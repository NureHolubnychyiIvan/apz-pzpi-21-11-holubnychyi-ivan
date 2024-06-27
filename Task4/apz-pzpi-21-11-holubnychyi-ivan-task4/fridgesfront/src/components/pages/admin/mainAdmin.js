import React, { useState, useEffect } from 'react';
import '../../styles/fridges.css';
import { v4 as uuidv4 } from 'uuid';
import { useTranslation } from 'react-i18next';

function MainAdminForm() {

  const { t} = useTranslation();

   const [users, setUser] = useState([]);

  useEffect(() => {

    /**  Call the function that will receive the list of refrigerators from the server
    and update the state of the component. */
    fetchFood();
  }, []); // An empty array means that the effect will be called only after the first rendering.

  const fetchFood = async () => {
    try {
      const response = await fetch(`http://localhost:8080/databaseAdmin/allUsers`, {
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
  
      setUser(data);
    } catch (error) {
      console.error('Error fetching fridges:', error.message);
    }
  };

  const handleDeleteUser = async (userId) => {
    try {
      await fetch(`http://localhost:8080/databaseAdmin/deleteUsers`, {
        method: 'POST',
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify([userId]),
      });

      // Update user list after deletion
      setUser((prevUsers) => prevUsers.filter((user) => user.userid !== userId));
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
          <h2>Users</h2>
          <ul>
            {users.map((users) => (
              <li key={uuidv4()
            }><h3>Name: {users.name}, Surname: {users.surname}, Patronymic: {users.patronymic}, Email: {users.email}, Number contract: {users.numberContract}, Photo number: {users.photoNumber}, Type: {users.type}</h3>

            <button onClick={() => handleDeleteUser(users.userId)}  >
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

export default MainAdminForm;
