package com.app.tests.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.app.tests.pages.basepage.BasePage;

public class CreatePage extends BasePage {

    public CreatePage(WebDriver driver) {
	super(driver);
    }

    String flippyCat = "Flippy Cat";
    String flippyCard = "Flippy Card";

    public void createStory() {
	By title = By.cssSelector("div.intro-section--title");
	waitForElementClickable(title);

	By createButton = By.cssSelector("a#pb-navbar-dropdown-create-btn.btn.pb-navbar-btn-create");
	waitAndClickElement(createButton);

	By story = By.cssSelector("div[data-resource='story']");
	waitAndClickElement(story);

	By flipCard = By.xpath("//p[text()='Flip Card']");
	waitAndClickElement(flipCard);

	By card = By.cssSelector("div.source-select-title");
	waitForElementClickable(card);

	By chooseTextCard = By.cssSelector(".source-buttons-wrapper.align-vertical.landscape > button:nth-child(2)");
	clickByAction(chooseTextCard);

	By editor = By.cssSelector("#text-editor");
	waitForElementClickable(editor);
	sendText(flippyCard, editor);

	By editBack = By.cssSelector(".flip-card-toolbar > div:nth-child(2)");
	clickElement(editBack);

	By choosePictureCard = By
		.cssSelector(".source-buttons-wrapper.align-vertical.landscape > button.source-button.file");
	waitForElementClickable(choosePictureCard);
	clickByAction(choosePictureCard);

	By searchBar = By.cssSelector(".search-bar > input");
	try {
	    waitForElementClickable(searchBar, 3);
	    sendText("cat", searchBar);
	} catch (TimeoutException e) {
	    By source = By.cssSelector(".bg-indicators > button.media-button.source-button");
	    waitForElementClickable(source, 3);
	    clickElement(source);
	    sendText("cat", searchBar);
	}

	By searchButton = By.cssSelector(".search-bar > button.btn.btn-search");
	waitAndClickElement(searchButton);

	By results = By.cssSelector(".getty-results > div > div > div");
	waitAndClickElement(results);

	By delete = By.cssSelector("span[translate='MP.POPOVER_MENU.DELETE_LABEL']");
	waitForElementClickable(delete);

	By headerTitle = By.cssSelector(".ql-editor.ql-blank > p");
	waitForElementClickable(headerTitle);
	sendTextByAction(flippyCat, headerTitle);
    }

    public void publishStory() {
	By publishButton = By.cssSelector("span[translate='ACTION_PANE.PUBLISH_BUT']");
	waitAndClickElement(publishButton);

	By viewStory = By.xpath("//span[text()='View Story']");
	waitAndClickElement(viewStory);

	ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(tabs2.get(1));

	By avatar = By.cssSelector("#avatar");
	waitForElementClickable(avatar);

	// WebElement authorName = driver.findElement(By.xpath("//a[text()='Front
	// End']"));
	// scrollIntoView(authorName);

	scrollByPixels(600);

	By flipCardText = By.xpath("//div[text()='" + flippyCard + "']");
	waitForElementClickable(flipCardText);

	By clickToFlip = By.cssSelector("button.flip-action");
	clickElement(clickToFlip);

    }

    public void shareStory() {
	By share = By.xpath("//div[@class='bottom-share-bar-container']//a[@socialshare-provider='facebook']");
	clickElement(share);

	boolean hasFacebook = false;
	for (String winHandle : driver.getWindowHandles()) {
	    driver.switchTo().window(winHandle);
	    if (driver.getTitle().contains("Facebook")) {
		hasFacebook = true;
		break;
	    }
	}
	Assert.assertTrue(hasFacebook);
    }

}
