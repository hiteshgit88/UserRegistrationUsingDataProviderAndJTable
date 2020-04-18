package Test;

import POM.ActionMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegisterUser extends ActionMethods {

    @DataProvider(name = "RegisterUserDataProvider")
    Object[][] getRegisterUserData()
    {
        return excelhelper.readDataFromExcel(strPathToDataSheet + _hashmapAppPro.get("TestDocDataFileName"), "Sheet1" );
    }

    @Test(dataProvider = "RegisterUserDataProvider")
    public void registerUserTest(String fname, String lname, String email, String pswd, String cpswd) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.get("http://tutorialsninja.com/demo/");
        WebElement myAccount = driver.findElement(By.xpath("//span[contains(text(),'My Account')]"));
        myAccount.click();
        Thread.sleep(5000);
        WebElement registerbtn = driver.findElement(By.linkText("Register"));
        registerbtn.click();
        Thread.sleep(5000);
        driver.findElement(By.id("input-firstname")).sendKeys(fname);
        Thread.sleep(2000);
        driver.findElement(By.id("input-lastname")).sendKeys(lname);
        Thread.sleep(2000);
        driver.findElement(By.id("input-email")).sendKeys(email);
        Thread.sleep(2000);
        driver.findElement(By.id("input-telephone")).sendKeys("1010101010");
        Thread.sleep(2000);
        driver.findElement(By.id("input-password")).sendKeys(pswd);
        Thread.sleep(2000);
        driver.findElement(By.id("input-confirm")).sendKeys(cpswd);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
        Thread.sleep(5000);
        Assert.assertTrue(driver.getTitle().contains("Your Account Has Been Created!"));

    }

}
