// @ts-check
const { defineConfig, devices } = require('@playwright/test');


module.exports = defineConfig({
  testDir: './tests',
  
  timeout: 100000,
  expect: {
    timeout: 100000
  },
  //workers: 4,
  //retries: 2,
  fullyParallel: true,  
  reporter: 'html',

  /* Configurações globais */
  use: {
    baseURL: 'https://www.google.com.br/?hl=pt-BR',
    headless: false,
    browserName : 'chromium', //chromium / firefox  / webkit
    trace: 'on-first-retry',
  },

  /* Configuração de projeto */
  projects: [

    {
      name: 'configPersonalizada',
      use: {
        //baseURL: 'https://www.google.com.br/?hl=pt-BR',
        browserName : 'chromium', //chromium / firefox  / webkit
        headless: false,
        video: 'on', //// on / off / only-on-failure / retain-on-failure
        screenshot: 'on', // on / off / only-on-failure
        trace: 'on',//'on' /  off / 'retain-on-failure'
      },
    },

    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] },
    },

    {
      name: 'firefox',
      use: { ...devices['Desktop Firefox'] },
    },

    {
      name: 'webkit',
      use: { ...devices['Desktop Safari'] },
    },

  ],


});

