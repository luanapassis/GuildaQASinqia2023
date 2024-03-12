package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppTest
{
    public static String diretorioLocal = System.getProperty("user.dir");

    @Test
    public void primeiroTeste() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setAppPackage("com.android.settings")
                .setAppActivity(".Settings");
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );
        WebElement battery = driver.findElement(AppiumBy.xpath("//*[@text='Battery']"));
        battery.click();
        WebElement usageBattery = driver.findElement(AppiumBy.id("com.android.settings:id/usage_summary"));

        String usoBateria = usageBattery.getText();
        System.out.println(usoBateria);
        driver.quit();

        Assert.assertEquals(usoBateria,"100 %");
    }

    @Test
    public void testeComEsperas() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setAppPackage("com.android.settings")
                .setAppActivity(".Settings");
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );

        //wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //
        WebElement battery = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//*[@text='Battery']"))) ;
        battery.click();
        //
        WebElement usageBattery = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("com.android.settings:id/usage_summary"))) ;
        //
        String usoBateria = usageBattery.getText();
        System.out.println(usoBateria);
        driver.quit();

        Assert.assertEquals(usoBateria,"100 %");
    }

    //Abre menu Preference
    //Abre menu 3. Preference dependencies
    //Clica no check box wifi
    //Valida se está marcado
    @Test
    public void validandoCheckBoxMarcado() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setApp(diretorioLocal+"\\src\\test\\java\\app\\ApiDemos-debug.apk");
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );

        //wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //
        WebElement preferenceMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Preference"))) ;
        preferenceMenu.click();
        //
        WebElement preferenceDependenciesMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("3. Preference dependencies")));
        preferenceDependenciesMenu.click();
        //
        WebElement wifiCheckBox = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("android:id/checkbox")));
        wifiCheckBox.click();
        //
        wifiCheckBox = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("android:id/checkbox")));
        String attribute = wifiCheckBox.getAttribute("checked");

        driver.quit();

        Assert.assertEquals(attribute, "true");
    }

    //Abre menu Preference
    //Abre menu 3. Preference dependencies
    //Valida se está desmarcado
    @Test
    public void validandoCheckBoxDesmarcado() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setApp(diretorioLocal+"\\src\\test\\java\\app\\ApiDemos-debug.apk");
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );

        //wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //
        WebElement preferenceMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Preference"))) ;
        preferenceMenu.click();
        //
        WebElement preferenceDependenciesMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("3. Preference dependencies")));
        preferenceDependenciesMenu.click();
        //
        WebElement wifiCheckBox = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("android:id/checkbox")));
        String attribute = wifiCheckBox.getAttribute("checked");

        driver.quit();

        Assert.assertEquals(attribute, "false");
    }

    //Abre menu Preference
    //Abre menu 3. Preference dependencies
    //Clica no check box wifi
    //Verifica que o campo Wifi settings foi habilitado
    @Test
    public void validandoCampoHabilitado() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setApp(diretorioLocal+"\\src\\test\\java\\app\\ApiDemos-debug.apk");
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );

        //wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //
        WebElement preferenceMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Preference"))) ;
        preferenceMenu.click();
        //
        WebElement preferenceDependenciesMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("3. Preference dependencies")));
        preferenceDependenciesMenu.click();
        //
        WebElement wifiCheckBox = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("android:id/checkbox")));
        wifiCheckBox.click();
        //
        WebElement wifiCheckSettings = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//*[@text='WiFi settings']")));
        String attribute = wifiCheckSettings.getAttribute("enabled");

        driver.quit();

        Assert.assertEquals(attribute, "true");
    }

    //Abre menu Preference
    //Abre menu 3. Preference dependencies
    //Verifica que o campo Wifi settings está desabilitado
    @Test
    public void validandoCampoDesabilitado() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setApp(diretorioLocal+"\\src\\test\\java\\app\\ApiDemos-debug.apk");
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );

        //wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //
        WebElement preferenceMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Preference"))) ;
        preferenceMenu.click();
        //
        WebElement preferenceDependenciesMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("3. Preference dependencies")));
        preferenceDependenciesMenu.click();
        //
        WebElement wifiCheckSettings = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//*[@text='WiFi settings']")));
        String attribute = wifiCheckSettings.getAttribute("enabled");

        driver.quit();

        Assert.assertEquals(attribute, "false");
    }

    //Abre menu Preference
    //Abre menu 3. Preference dependencies
    //Clica no check box wifi
    //Clica em WiFi settings
    //Digita um texto no popUp
    //Clica em ok do popUp
    //Clica novamente em WiFi settings
    //Obtem o texto do campo field do popUp
    //Valida se o texto está igual ao que foi digitado
    @Test
    public void digitandoValorEmPopUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setApp(diretorioLocal+"\\src\\test\\java\\app\\ApiDemos-debug.apk");
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );

        //wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //
        WebElement preferenceMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Preference"))) ;
        preferenceMenu.click();
        //
        WebElement preferenceDependenciesMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("3. Preference dependencies")));
        preferenceDependenciesMenu.click();
        //
        WebElement wifiCheckBox = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("android:id/checkbox")));
        wifiCheckBox.click();
        //
        WebElement wifiCheckSettings = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//*[@text='WiFi settings']")));
        wifiCheckSettings.click();
        //
        WebElement wifiCheckSettingsField = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("android:id/edit")));
        wifiCheckSettingsField.sendKeys("Guilda Sinqia Appium");
        //
        WebElement wifiCheckSettingsButtonOk = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("android:id/button1")));
        wifiCheckSettingsButtonOk.click();

        //clica novamente e obtem o texto
        wifiCheckSettings = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//*[@text='WiFi settings']")));
        wifiCheckSettings.click();
        //
        wifiCheckSettingsField = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("android:id/edit")));
        String textoObtido = wifiCheckSettingsField.getText();

        driver.quit();

        Assert.assertEquals(textoObtido, "Guilda Sinqia Appium");
    }

    //Clica no menu Views
    //Clica no menu Expandable Lists
    //Clica no menu 1. Custom Adapter
    //Long press no item People Names
    //Verifica se o menu "Sample menu", será exibido após o long press
    @Test
    public void utilizandoGesture() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setApp(diretorioLocal+"\\src\\test\\java\\app\\ApiDemos-debug.apk");
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );

        //wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //
        WebElement viewsMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Views"))) ;
        viewsMenu.click();
        //
        WebElement expendableListMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Expandable Lists")));
        expendableListMenu.click();
        //
        WebElement customAdapterMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("1. Custom Adapter")));
        customAdapterMenu.click();
        //long press
        WebElement peopleNames = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//*[@text='People Names']")));
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of
                ("elementId", ((RemoteWebElement)peopleNames).getId(), "duration", 2000));

        WebElement sampleMenu = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//*[@text='Sample menu']")));
        String valorEsperado = sampleMenu.getAttribute("enabled");

        driver.quit();

        Assert.assertEquals(valorEsperado, "true");

    }


}