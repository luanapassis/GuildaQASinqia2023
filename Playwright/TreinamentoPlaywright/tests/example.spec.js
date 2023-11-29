// @ts-check
const { test, expect } = require('@playwright/test');

test('Login com sucesso', async ({ page }) => {
  await page.goto('https://the-internet.herokuapp.com/login');

  await page.getByRole('textbox',{name:'Username'}).fill("tomsmith");
  await page.getByLabel('Password').fill('SuperSecretPassword!');
  await page.locator('[type="submit"]').click();
  await expect(page.locator('h2')).toHaveText('Secure Area');
  //outra forma de se validar 
  await expect(page.getByRole('heading', { name: 'Secure Area', exact: true })).toBeVisible();
});

test('Validando checkbox marcado', async ({ page }) => {
  await page.goto('https://the-internet.herokuapp.com/checkboxes');

  const locator = await page.locator("//*[@id='checkboxes']/input[2]");
  await expect(locator).toBeChecked();  
});

test('Validando checkbox não marcado', async ({ page }) => {
  await page.goto('https://the-internet.herokuapp.com/checkboxes');

  const locator = await page.locator("//*[@id='checkboxes']/input[1]");
  await expect(locator).not.toBeChecked();  
});


test('Validando elemento hidden', async ({ page }) => {
  await page.goto('https://the-internet.herokuapp.com/dynamic_loading/1');

  await page.getByRole('button', { name: 'Start' }).click();
  const locator = await page.getByText('Hello World!')
  await expect(locator).toBeVisible();
});

test('Utilizando base url', async ({ page }) => {
  await page.goto('/');
});



