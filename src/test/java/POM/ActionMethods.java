package POM;

import Config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActionMethods extends SeleniumConfig {

    @Test
    public void actionsToDo() throws InterruptedException
    {
        WebElement myAccount = driver.findElement(By.xpath("//span[contains(text(),'My Account')]"));
        myAccount.click();
        Thread.sleep(1000);

        WebElement registerbtn = driver.findElement(By.linkText("Register"));
        registerbtn.click();
        Thread.sleep(1000);


    }
}
