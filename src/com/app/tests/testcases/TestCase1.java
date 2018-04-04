package com.app.tests.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.app.tests.basetests.BaseTestCase;
import com.app.tests.utils.Utils;

public class TestCase1 extends BaseTestCase {

    /** The ini file, with values for this testcase */
    String iniFile = "/resources/data/testcases/TestCase1.ini";

    @BeforeMethod
    public void setUp() {
	String browser = Utils.getIniFileValue("browser", iniFile);
	initDriver(browser);
	initWebPages();
    }

    @AfterMethod
    public void tearDown() {
	quit();
    }

    @Test(dependsOnGroups = "testng")
    public void createAndPublishStory() {
	mainPage.openMainPage();
	mainPage.logIn();
	mainPage.enterCredentials();

	createPage.createStory();
	createPage.publishStory();
	createPage.shareStory();
    }

}
