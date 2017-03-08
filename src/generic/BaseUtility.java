package generic; /**
 * Created with IntelliJ IDEA.
 * User: mrahman
 * Date: 4/26/13
 * Time: 11:25 PM
 * To change this template use File | Settings | File Templates.
 */
//core java API
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


//WebDriver API
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.touch.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
//import org.openqa.selenium.Mouse;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
//TestNg API
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class BaseUtility {

    public static final String  SAUCE_LAB_USER_NAME = "";
    public static final String  SAUCE_LAB_USER_KEY = "";
    public static final String  ORGANIZATION_GRID = "";
    WebDriver driver = null;
    String url = "http://www.cnn.com";

    @BeforeClass
    public void beforeClass() {
        getBrowser("firefox");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.navigate().to(url);
        driver.manage().window().maximize();

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    //Utiltiy Methods.................................Start Here

    //OS methods

    //Browser Method
    public void getBrowser(String browser){
        if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "/Users/mrahman/Documents/chromedriver");
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        }else if(browser.equalsIgnoreCase("internetexplorer")){
            System.setProperty("webdriver.ie.driver", "/Users/mrahman/Documents/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
    }

    //Navigate Methods
    public void navigateBack(){
        driver.navigate().back();
    }

    //click utility methods
    public void clickByCss(String locator){
        driver.findElement(By.cssSelector(locator)).click();
    }

    public void clickByXpath(String locator){
        driver.findElement(By.xpath(locator)).click();
    }

    public void clickByName(String locator){
        driver.findElement(By.name(locator)).click();
    }

    public void clickById(String locator){
        driver.findElement(By.id(locator)).click();
    }

    //find WebElement utility methods
    public WebElement getWebElementByCss(String locator){
        WebElement element = driver.findElement(By.cssSelector(locator));
        return element;
    }

    public WebElement getWebElementByXpath(String locator){
        WebElement element = driver.findElement(By.xpath(locator));
        return element;
    }

    public WebElement getWebElementByName(String locator){
        WebElement element = driver.findElement(By.name(locator));
        return element;
    }

    public WebElement getWebElementById(String locator){
        WebElement element = driver.findElement(By.id(locator));
        return element;
    }

    //find Multiple WebElement utility methods
    public List<WebElement> getWebElementsByCss(String locator1, String locator2){
        List<WebElement> element = driver.findElement(By.cssSelector(locator1)).findElements(By.cssSelector(locator2));
        return element;
    }

    public List<WebElement> getWebElementsByCss(String locator){
        List<WebElement> element = driver.findElements(By.cssSelector(locator));
        List<String> list = new ArrayList<String> ();
        return element;
    }

    public List<WebElement> getWebElementsByXpath(String locator1, String locator2){
        List<WebElement> element = driver.findElement(By.xpath(locator1)).findElements(By.xpath(locator2));
        return element;
    }

    //get Text from the single WebElement, utility Methods
    public String getTextByCss(String locator){
        String text = driver.findElement(By.cssSelector(locator)).getText();
        return text;
    }

    public String getTextByXpath(String locator){
        String text = driver.findElement(By.xpath(locator)).getText();
        return text;
    }

    //get list of text from the multiple WebElements, utility methods
    public List<String> getListOfTextByCss(String locator1, String locator2){
        List<WebElement> element = driver.findElement(By.cssSelector(locator1)).findElements(By.cssSelector(locator2));
        List<String> text = new ArrayList<String>();
        for(WebElement web:element){
            text.add(web.getText());
        }
        return text;
    }

    public List<String> getListOfTextByXpath(String locator1, String locator2){
        List<WebElement> element = driver.findElement(By.xpath(locator1)).findElements(By.xpath(locator2));
        List<String> text = new ArrayList<String>();
        for(WebElement web:element){
            text.add(web.getText());
        }
        return text;
    }

    //Drop Down menu selection
    public List<String> getDropDownMenuList(String locator1, String locator2){
        List<WebElement> element = driver.findElement(By.cssSelector(locator1)).findElements(By.cssSelector(locator2));
        List<String> text = new ArrayList<String>();
        for(WebElement web:element){
            // System.out.println(web.getText());
            text.add(web.getText());
        }
        return text;
    }

    public void selectItemsFromDropDownMenu(String locator, String item){
        WebElement option = driver.findElement(By.cssSelector(locator));
        Select select = new Select(option);
        //select.selectByVisibleText(item);
        select.selectByValue(item);
    }

    public void setNChooseValue(String locator, String value){
        clickByCss(locator);
        typeByCss(locator, value);
    }

    //Synchronization methods
    public void waitUntil(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 40);
        WebElement waitFor = wait.until(ExpectedConditions.elementToBeClickable((locator)));
    }

    public By findBy(String locator){
        By by = (By) driver.findElement(By.cssSelector(locator));
        return by;
    }

    //Alert Handling
    public void acceptTheAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void dismissTheAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    //PopUp WIndow Handling

    //Type methods
    public void typeByCss(String locator, String value){
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }

    public void typeByXpath(String locator, String value){
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    //DragAndDrop methods
    public void dragNDrop(WebElement src, WebElement dest){
        Actions action = new Actions(driver);
        Action dragNdrop = action.clickAndHold(src).moveToElement(dest).release(dest).build();
    }

    //DoubleClick Methods

    //MouseHover Methods
    public void hoverOn(WebElement element){
        Actions build = new Actions(driver);
        Actions hover = build.moveToElement(element);
        hover.perform();
    }

    public void hoverOnNClick(String locator){
        try{
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions build = new Actions(driver);
            Actions hover = build.moveToElement(element);
            hover.perform();
            clickByCss(locator);
        }catch(Exception e){
            WebElement element = driver.findElement(By.cssSelector(locator));
            Locatable hover = (Locatable) element;
            Mouse mouse = ((HasInputDevices) driver).getMouse();
            mouse.mouseMove(hover.getCoordinates());
            clickByCss(locator);
        }
    }

    //Sleep Methods
    public void sleep(int time) throws InterruptedException{
        try{
            Thread.sleep(time*1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    //Scroll By Methods
    public void scrollBy(int number){
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0," + number+");");
    }

    public void findByTagName(String locator){
        driver.findElement(By.tagName(locator));
    }

    public String captureScreenShot(String file){
        String path;
        try{
            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            if(file.length() > 0){
                path = file;
            }else{
                path = source.getName();
            }
            FileUtils.copyFile(source, new File(path));
        }catch(IOException e){
            path = "Failed to capture screenshot" + e.getMessage();
        }
        return path;
    }

    //back space
    public void backSpace(int numberOfTimes){
        Keyboard key = null;
        if(driver instanceof HasInputDevices){
            key = ((HasInputDevices)driver).getKeyboard();
        }else{
            Assert.fail();
        }
        for(int i = 0; i<numberOfTimes; i++){
            key.sendKeys(Keys.BACK_SPACE);
        }
    }

    //Utiltiy Methods.................................End Here
}