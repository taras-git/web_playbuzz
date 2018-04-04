package com.app.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.app.tests.pages.basepage.BasePage;
import com.app.tests.utils.Utils;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
	super(driver);
    }

    String iniFile = "/resources/config/testStation.ini";
    /** Url for the main page. */
    String baseUrl = Utils.getIniFileValue("base.url", iniFile);

    /**
     * Maximize the browser window, set url, and open this url
     */
    public void openMainPage() {
	maximizeBrowserScreen();
	driver.get(baseUrl);
    }

    public void logIn() {
	By logIn = By.cssSelector("#pb-navbar-login-btn > button");
	waitForElement(logIn).click();
    }

    public void enterCredentials() {
	By email = By.cssSelector("#elogin_email_field");
	waitForElement(email).sendKeys("taraspol@gmail.com");

	By pass = By.cssSelector("#elogin_pass_field");
	waitForElement(pass).sendKeys("Front End Tester");

	By loginButton = By.cssSelector("input[value='Login'][type='submit']");
	waitForElement(loginButton).click();

    }
}
