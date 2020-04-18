package Config;

import Util.ExcelHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Properties;

public abstract class SeleniumConfig {

    public WebDriver driver;
    public  Properties _properties = new Properties();
    public HashMap<String, String> _hashmapAppPro = new HashMap<>();
    public String strPathToDataSheet;
    public ExcelHelper excelhelper = new ExcelHelper();

    public String getDirPathToResources()
    {
        String strDecodedPath = null;

        try {
            String strPath = SeleniumConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            strDecodedPath = URLDecoder.decode(strPath, "UTF-8").substring(0, strPath.lastIndexOf("/") - 1);

            if(strDecodedPath.startsWith("/"))
            {
                strDecodedPath = strDecodedPath.substring(1);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return strDecodedPath;
    }
    enum eBrowserType
    {
        Chrome,
        Firefox
    }

    public SeleniumConfig(){

        try
        {
            _hashmapAppPro = loadPropertiesFile("/app");
            strPathToDataSheet = getDirPathToResources() + "DataSheets/";
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    private HashMap loadPropertiesFile(String strPathToPropertiesFile) throws IOException {

        HashMap<String, String> _hashmap;

        try {

            _hashmap = new HashMap<String, String>();

            _properties.load(this.getClass().getResourceAsStream(strPathToPropertiesFile));

            for (String key : _properties.stringPropertyNames()) {
                String value = _properties.getProperty(key);
                _hashmap.put(key, value);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
            _hashmap = null;
        }

        return _hashmap;
    }

    @BeforeMethod
    @Parameters("browser")
    public void browserInit(@Optional("Chrome")String strBrowserName)
    {
        if(strBrowserName.equalsIgnoreCase(eBrowserType.Chrome.toString()))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (strBrowserName.equalsIgnoreCase(eBrowserType.Firefox.toString()))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.navigate().to(_hashmapAppPro.get("appURL"));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void browserQuit()
    {
        driver.quit();
    }
}