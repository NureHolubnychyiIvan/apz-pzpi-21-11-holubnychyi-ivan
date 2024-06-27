export const TOKENS = {
    accessToken: null,
    refreshToken: null,
  };
  
  export const USER = {
    email: null,
    language: "en",
    getEmail: () => {
      return USER.email;
    },
};

export const SECRET = {
  clientSecret: "pi_3OMRpMHDvywGQE0c0zq0AbrF_secret_PDq7wTBCVJiCjGxAjTTW7JKGm",
  getSecret: function() {
    return  this.clientSecret
  },

  setSecret: function(secret) {
    this.clientSecret = secret;
  },
}