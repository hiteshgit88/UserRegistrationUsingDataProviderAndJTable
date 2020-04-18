import Config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Practice1 extends SeleniumConfig {

    @Test
    public void testLoadDsktopPage() throws InterruptedException
    {
        WebElement anchorDesktopElem = driver.findElement(By.linkText("Desktops"));
        anchorDesktopElem.click();

        Thread.sleep(1000);

        WebElement anchorMacElem = driver.findElement(By.linkText("Mac (1)"));
        anchorMacElem.click();

        Thread.sleep(1000);

        WebElement divMacHeaderElem = driver.findElement(By.cssSelector("div#content > h2"));

        Thread.sleep(1000);

        Assert.assertEquals(divMacHeaderElem.getText(), "Mac");
    }
}
