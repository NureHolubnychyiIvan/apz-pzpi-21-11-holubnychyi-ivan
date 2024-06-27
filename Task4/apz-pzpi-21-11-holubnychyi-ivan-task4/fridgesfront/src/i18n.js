// i18n.js
import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';

import enTranslation from './languages/en.json';
import ukTranslation from './languages/uk.json';

// ініціалізація і18next
i18n
  .use(initReactI18next)
  .use(LanguageDetector)
  .init({
    resources: {
      en: {
        translation: enTranslation,
      },
      uk: {
        translation: ukTranslation,
      },
    },
    fallbackLng: 'en',
    debug: true,

    interpolation: {
      escapeValue: false,
    },
  });

export default i18n;
