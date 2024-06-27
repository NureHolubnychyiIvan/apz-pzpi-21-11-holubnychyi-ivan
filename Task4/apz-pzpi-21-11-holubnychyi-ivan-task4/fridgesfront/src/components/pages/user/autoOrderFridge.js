import React, { useState } from 'react';
import '../../styles/registration.css';
import { addOrder } from '../../logic/autoOrderAdd.js'
import { useTranslation } from 'react-i18next';

function AutoOrderFridgeForm() {

  const { t} = useTranslation();
  const [productName, setProductName] = useState('');
  const [productWeight, setProductWeight] = useState('');
  const [productCount, setProductCount] = useState('');
  const [deliveryDate, setDeliveryDate] = useState('');
  const [productList, setProductList] = useState([]);

  const addProduct = () => {
    if (!productName || !productWeight || !productCount || !deliveryDate) {
      alert('Please fill in all fields.');
      return;
    }

    const newProduct = {
      name: productName,
      weight: parseFloat(productWeight),
      count: parseInt(productCount),
      date: deliveryDate,
    };

    setProductList([...productList, newProduct]);
    setProductName('');
    setProductWeight('');
    setProductCount('');
    setDeliveryDate('');
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
                    <a href="#">{t('footerUser.settings')}</a>
                    <a href="#">{t('footerUser.exist')}</a>
                </div>
          </div>
        </div>
      </header>

      <div className="main-content_r">
        <div className="formBlock_r">
          <h2 className="form-title_r">{t('autoOrderFridge.title')}</h2>
          <form className="form_order">
            <label htmlFor="productName">{t('autoOrderFridge.productName')}:</label>
            <input
              type="text"
              id="productName"
              name="productName"
              value={productName}
              onChange={(e) => setProductName(e.target.value)}
              required
            />

            <label htmlFor="productWeight">{t('autoOrderFridge.productWeight')} (kg):</label>
            <input
              type="number"
              id="productWeight"
              name="productWeight"
              value={productWeight}
              onChange={(e) => setProductWeight(e.target.value)}
              required
            />

            <label htmlFor="productCount">{t('autoOrderFridge.productCount')}:</label>
            <input
              type="number"
              id="productCount"
              name="productCount"
              value={productCount}
              onChange={(e) => setProductCount(e.target.value)}
              required
            />

            <label htmlFor="deliveryDate">{t('autoOrderFridge.deliveryDate')}:</label>
            <input
              type="date"
              id="deliveryDate"
              name="deliveryDate"
              value={deliveryDate}
              onChange={(e) => setDeliveryDate(e.target.value)}
              required
            />

            <button type="button" className='button_p' onClick={addProduct}>
              {t('autoOrderFridge.add')}
            </button>
          </form>

          <h2 className = "h2_p">{t('autoOrderFridge.productList')}</h2>

          <ul>
            {productList.map((product, index) => (
              <li key={index}>
                <span className='span_p'>{product.name} (Weight: {product.weight} kg, Count:  {product.count}, Delivery Date: {product.date})</span>
              </li>
            ))}
          </ul>
          <a href="#" className="login-button" onClick={() => addOrder(productList)}>
              {t('autoOrderFridge.order')}
          </a>
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

export default AutoOrderFridgeForm;
