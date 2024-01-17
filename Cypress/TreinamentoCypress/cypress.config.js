const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {   
    },
    defaultCommandTimeout: 15000, //15 segundos
    pageLoadTimeout: 15000, //15 segundos
    baseUrl: 'https://the-internet.herokuapp.com',
    video: true,
    retries: { "runMode": 1, "openMode": 1 },
    
    
    
    //outras configurações
    //https://docs.cypress.io/guides/references/configuration
  },
});


