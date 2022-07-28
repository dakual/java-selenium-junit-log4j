package com.dakual;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Elements {
    public static WebDriver driver;

    public Elements(WebDriver webDriver) {
        driver = webDriver;
    }

    public WebElement txtEmail() {
        return driver.findElement(By.id("username"));
    }

    public WebElement txtPassword() {
        return driver.findElement(By.id("password"));
    }

    public WebElement btnLogin() {
        return driver.findElement(By.xpath("//button[@type='submit']"));
    }
}
