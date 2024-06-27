import '../../styles/checkout.css';
import React from 'react';
import {PaymentElement} from '@stripe/react-stripe-js';
import { Elements } from '@stripe/react-stripe-js';
import { loadStripe } from '@stripe/stripe-js';
import { sendSubscription } from '../../logic/checkoutPayment.js'
import { useTranslation } from 'react-i18next';


function initializeAndSaveSecret(){
  return "pi_3OMRpMHDvywGQE0c0zq0AbrF_secret_PDq7wTBCVJiCjGxAjTTW7JKGm"
};

function CheckoutForm() {

  const { t} = useTranslation();

  const stripePromise = loadStripe('pk_test_51OFG3eHDvywGQE0cdWR2j05f9zmQc6vzcOIDunSASliezF4fAnzxX72goE0qdPotKaA4XT42M4KP4h1XDt4HDeaZ00nqGzE1WI');
  const options = {
      clientSecret: initializeAndSaveSecret(),
    };
  
  return (
    <div id="body_c">
      {/* Display a payment form */}
      <form id="payment-form">
        <h2>{t('checkout.content')}</h2>
        <span>{t('checkout.made')}</span>
        <span>19 </span>
        <span>USD</span>
        <Elements  options={options} stripe={stripePromise}>
          <PaymentElement />
        </Elements>
        
        <button id="submit" onSubmit = {sendSubscription}>
          <div className="spinner hidden" id="spinner"></div>
          <span id="button-text">{t('checkout.button')}</span>
        </button>
        <div id="payment-message" className="hidden"></div>
      </form>
    </div>
  );
}

export default CheckoutForm;


