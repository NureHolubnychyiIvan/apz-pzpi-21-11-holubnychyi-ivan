
import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import SignInForm from './components/pages/signIn';
import RegistrationForm from './components/pages/registration';
import MainForm from './components/pages/user/main';
import SubscriptionForm from './components/pages/user/subscription';
import CheckoutForm from './components/pages/user/checkout';
import FridgesForm from './components/pages/user/fridges';
import FoodUserForm from './components/pages/user/foodUser';
import HistoryUserForm from './components/pages/user/historyUser';
import FoodInFridgeForm from './components/pages/user/fridgesMain';
import HistoryFridgeForm from './components/pages/user/historyFridge';
import AutoOrderFridgeForm from './components/pages/user/autoOrderFridge';
import AutoOrderUserForm from './components/pages/user/autoOrderUser';
import SettingForm from './components/pages/user/settings';
import { I18nextProvider } from 'react-i18next';
import MainAdminForm from './components/pages/admin/mainAdmin';
import OfficeForm from './components/pages/admin/office';
import ModelForm from './components/pages/admin/model';
import ProductForm from './components/pages/admin/product';
import i18n from './i18n';


function App() {
  return (
    <I18nextProvider i18n={i18n}>
    <Router>
      <Routes>
        <Route path="/sign-in" element={<SignInForm />} />
        {/* Redirect замінено на Navigate */}
        <Route path="/user/main" element={<MainForm />} />
        <Route
          path="/user/checkout"
          element={ 
              <CheckoutForm />
          }
        />
        <Route path="/user/subscription" element={<SubscriptionForm />} />
        <Route path="/" element={<Navigate to="/sign-in" />} />
        <Route path="/register" element={<RegistrationForm />} />
        <Route path="/admin/mainForm" element={<MainAdminForm />} />
        <Route path="/admin/office" element={<OfficeForm />} />
        <Route path="/admin/model" element={<ModelForm />} />
        <Route path="/admin/product" element={<ProductForm />} />
        <Route path="/user/fridges" element={<FridgesForm />} />
        <Route path="/user/food" element={<FoodUserForm />} />
        <Route path="/user/history" element={<HistoryUserForm />} />
        <Route path="/user/autoOrder" element={<AutoOrderUserForm />} />
        <Route path="/user/settings" element={<SettingForm />} />
        <Route path="/user/fridges/food-in-fridge/:fridgeId" element={<FoodInFridgeForm />} />
        <Route path="/user/fridges/food-in-fridge/:fridgeId/:fridgeId/historyUsing" element={<HistoryFridgeForm />} />
        <Route path="/user/fridges/food-in-fridge/:fridgeId/:fridgeId/autoOrder" element={<AutoOrderFridgeForm />} />
      </Routes>
    </Router>
    </I18nextProvider>
  );
}

export default App;


