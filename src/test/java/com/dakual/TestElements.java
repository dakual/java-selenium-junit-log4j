package com.dakual;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class TestElements {
    private static Logger Log = Logger.getLogger(TestElements.class.getName());
    private static WebDriver driver;
    public static Elements elementPage;

    @Before
    public void testBefore() {
        Log.info("Test  is Starting");

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver  = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        new FluentWait(driver)
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(60));

        elementPage = new Elements(driver);
    }

    @Test
    public void test() {
        Log.info("Login testing...");

        try {
            String email        = "";
            String userPassword = "";
            String actualUrl    = "https://www.linkedin.com/login/";
            String expectedUrl  = "https://www.linkedin.com/feed/";

            driver.get(actualUrl);
            Log.info("Opening Page : " + actualUrl);

            elementPage.txtEmail().clear();
            elementPage.txtEmail().sendKeys(email);
            Log.info("Email : " + email);

            elementPage.txtPassword().clear();
            elementPage.txtPassword().sendKeys(userPassword);
            Log.info("Password : *********");

            elementPage.btnLogin().click();

            String currentUrl= driver.getCurrentUrl();
            if(expectedUrl.equalsIgnoreCase(currentUrl)) {
                Log.info("Test passed");
            } else {
                Log.error("Test failed");
            }

            Assert.assertEquals(expectedUrl, currentUrl);
        } catch (Exception ex) {
            Log.fatal("Test failed");
            Log.fatal(ex.getMessage());
        }
    }

    @After
    public void testAfter() {
        Log.info("Test is Ending");
        driver.quit();
    }
}
