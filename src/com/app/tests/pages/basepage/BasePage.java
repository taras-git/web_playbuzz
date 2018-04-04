package com.app.tests.pages.basepage;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
	this.driver = driver;
    }

    /**
     * Gets the current web page title.
     *
     * @return the title
     */
    public String getActualTitle() {
	return driver.getTitle();
    }

    /**
     * Maximize browser screen.
     */
    public void maximizeBrowserScreen() {
	driver.manage().window().maximize();
    }

    /**
     * Checks if element can be found on the web page. The webpage is checked every
     * 500 milliseconds until the element is found or timeout is reached Timeout is
     * set in ini file as amount of seconds to implicitly wait for an element
     *
     * @param locator
     *            the locator
     * @return true, if element is present
     */
    public boolean isElementPresent(By locator) {
	List<WebElement> webElements = driver.findElements(locator);
	if (webElements.size() == 0) {
	    return false;
	}
	return true;
    }

    /**
     * Checks if element can be found by its id on the web page. The webpage is
     * checked every 500 milliseconds until the element is found or timeout is
     * reached Timeout is set in ini file as amount of seconds to implicitly wait
     * for an element
     *
     * @param id
     *            the id of the webelement
     * @return true, if element is found
     */
    public boolean isElementPresentById(String id) {
	return isElementPresent(By.id(id));
    }

    /**
     * Checks if element can be found by its xpath on the web page. The webpage is
     * checked every 500 milliseconds until the element is found or timeout is
     * reached Timeout is set in ini file as amount of seconds to implicitly wait
     * for an element
     *
     * @param xpath
     *            the xpath of the element
     * @return true, if element is found
     */
    public boolean isElementPresentByXpath(String xpath) {
	return isElementPresent(By.xpath(xpath));
    }

    /**
     * Wait fot the webelement and if it is found, send the text to webelent.
     * 
     *
     * @param text
     *            the text to be sent to the webelement
     * @param locator
     *            the locator of the webelement
     * @return true, if successful
     */
    public boolean sendText(String text, By locator) {
	if (isElementPresent(locator)) {
	    driver.findElement(locator).sendKeys(text);
	    return true;
	}
	return false;
    }

    public void sendTextByAction(String text, By locator) {
	Actions actions = new Actions(driver);
	actions.moveToElement(driver.findElement(locator));
	actions.click();
	actions.sendKeys(text);
	actions.build().perform();
    }

    /**
     * Find the element and click on it.
     *
     * @param locator
     *            the locator of the webelement.
     */
    public void clickElement(By locator) {
	driver.findElement(locator).click();
    }

    public void waitAndClickElement(By locator) {
	waitForElementClickable(locator);
	driver.findElement(locator).click();
    }

    /**
     * Open link in new tab of the current window, and switch the focus to it
     *
     * @param locator
     *            the locator of the link to be clicked
     * @throws InterruptedException
     *             the interrupted exception
     */
    public void openLinkInNewTab(By locator) throws InterruptedException {
	WebElement link = driver.findElement(locator);
	Actions newTab = new Actions(driver);

	newTab//
		.keyDown(Keys.CONTROL)//
		.keyDown(Keys.SHIFT)//
		.click(link)//
		.keyUp(Keys.CONTROL)//
		.keyUp(Keys.SHIFT)//
		.build()//
		.perform();

	Set<String> windowsSet = driver.getWindowHandles();
	driver//
		.switchTo()//
		.window((String) windowsSet.toArray()[0]);
    }

    /**
     * Wait for element.
     *
     * @param by
     *            the element
     * @return the web element
     */
    protected WebElement waitForElementClickable(By by) {
	return waitForElementClickable(by, 20);
    }

    protected WebElement waitForElementClickable(By by, long timeoutSeconds) {
	WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
	WebElement element = wait.until((ExpectedConditions.elementToBeClickable(by)));
	try {
	    Thread.sleep(50);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	return element;
    }

    protected WebElement waitForElementVisible(By by) {
	WebDriverWait wait = new WebDriverWait(driver, 15);
	WebElement element = wait.until((ExpectedConditions.visibilityOfElementLocated(by)));
	try {
	    Thread.sleep(50);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	return element;
    }

    protected WebElement waitForElement(By by) {
	return driver.findElement(by);
    }

    protected boolean isElementDisplayed(By by) {
	WebDriverWait wait = new WebDriverWait(driver, 15);
	wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	try {
	    Thread.sleep(50);
	} catch (TimeoutException e) {
	    e.printStackTrace();
	    return false;
	} catch (InterruptedException e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    protected boolean waitForUrlChanged(String titleContains) {
	WebDriverWait wait = new WebDriverWait(driver, 15);
	return wait.until(ExpectedConditions.titleContains(titleContains));
    }

    /**
     * Click on element by using action.
     *
     * @param by
     *            the element
     */
    protected void clickByAction(By by) {
	WebElement element = driver.findElement(by);
	clickByAction(element);
    }

    protected void clickByAction(WebElement e) {
	Actions actions = new Actions(driver);
	actions.moveToElement(e).click().perform();
    }

    /**
     * Checks if an element is present on the main page by its span text.
     *
     * @param text
     *            the text for span value of the element
     * @return true, if element is present by its span text
     */
    public boolean isLinkPresentBySpanText(String text) {// try {
	String xpath = ".//span[text()=\"" + text + "\"]";
	return isElementPresentByXpath(xpath);
    }

    /**
     * Element by span text.
     *
     * @param text
     *            the text inside span tag
     * @return the element
     */
    protected By elementBySpanText(String text) {
	String xpath = ".//span[text()=\"" + text + "\"]";
	return By.xpath(xpath);
    }

    protected void hoverAndClick(By by1, By by2) {
	Actions actions = new Actions(driver);
	actions.moveToElement(driver.findElement(by1))//
		.moveToElement(driver.findElement(by2))//
		.click()//
		.build()//
		.perform();
    }

    protected void scrollIntoView(WebElement e) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView();", e);

    }

    protected void scrollByPixels(int pixels) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0," + pixels + ")");
    }

}
