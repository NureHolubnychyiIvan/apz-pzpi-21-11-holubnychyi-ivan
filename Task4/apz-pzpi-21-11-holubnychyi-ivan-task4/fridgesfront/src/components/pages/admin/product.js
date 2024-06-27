import React, { useState, useEffect } from 'react';
import '../../styles/fridges.css';
import { v4 as uuidv4 } from 'uuid';
import { useTranslation } from 'react-i18next';

function ProductForm() {

  const {t} = useTranslation();

  const [product, setProduct] = useState([]);

  useEffect(() => {

    /**  Call the function that will receive the list of refrigerators from the server
    and update the state of the component. */
    fetchProduct();
  }, []); // An empty array means that the effect will be called only after the first rendering.

  const fetchProduct = async () => {
    try {

      const response = await fetch(`http://localhost:8080/databaseAdmin/allProduct`, {
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
  
      setProduct(data);
    } catch (error) {
      console.error('Error fetching model:', error.message);
    }
  };

  const handleDeleteUser = async (productId) => {
    try {
      await fetch(`http://localhost:8080/databaseAdmin/deleteProduct`, {
        method: 'POST',
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify([productId]),
      });

      // Update user list after deletion
      setProduct((prevProduct) => prevProduct.filter((product) => product.productId !== productId));
    } catch (error) {
      console.error('Error deleting product:', error.message);
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
          <h2>Products</h2>
          <ul>
            {product.map((product) => (
              <li key={uuidv4()
            }><h3>Product id: {product.productId}, Name: {product.name}, Weight: {product.weight}</h3>

            <button onClick={() => handleDeleteUser(product.productId)}  >
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

export default ProductForm;
