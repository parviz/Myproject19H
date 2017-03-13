package com.pnt.qa;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
//import org.openqa.selenium.HasInputDevices;
//import org.openqa.selenium.Mouse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

import com.sun.xml.internal.ws.util.StringUtils;

public class BarnesNoble {
  
  //private static final int NOOKBooks = 0;
WebDriver driver = null;
//private int menue;
private boolean acceptNextAlert = true;
  @BeforeMethod
  public void beforeMethod() {
	  //System.setProperty("webdriver.gecko.driver", "C:/DevTools/firefoxDriver/geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.navigate().to("http://www.bn.com");
	  //Thread.sleep(4000);
  }
  
  /*
  @Test
  public void test6(){
  String st ="Upgrade Your NOOK®   Get $50 toward a new Samsung Galaxy NOOK® for a limited time.   Shop Now";
  String[]  parts = st.split("   ",0);
  System.out.println(parts[0]);
  for (String temp: parts){
      System.out.println(temp);
  }
  }
  */
  
/*
  @Test
  public void test() throws InterruptedException {
	 												
	  WebElement element = driver.findElement(By.xpath(".//*[@id='topNav1']"));   
	  //Locatable hoverItem = (Locatable) element;
	 // Mouse mouse = ((HasInputDevices) driver).getMouse();
	// mouse.mouseMove(hoverItem.getCoordinates());
	 new Actions(driver).moveToElement(element).perform();
	 WebElement elemen = driver.findElement(By.xpath(".//*[@id='subMenu1']"));
	 List<WebElement> list1 =elemen.findElements(By.tagName("a"));
	 System.out.println("Menu size =" + list1.size());
	 ArrayList<String> arraylist =new ArrayList<String>();
	 
	 for(WebElement elem: list1){
		 String text = elem.getText().trim();
		 text = text.split("\n")[0];
			System.out.println("Item Found: " + text);
				arraylist.add(text);
			}
	 
			for(String item: arraylist){
				WebElement element4 = driver.findElement(By.xpath(".//*[@id='topNav1']"));   
				 new Actions(driver).moveToElement(element4).perform();
				Thread.sleep(2000);
				WebElement elemen2 = driver.findElement(By.xpath(".//*[@id='subMenu1']//a[contains(text(), \""+item+"\")]"));
				elemen2.click();
				String hometitle1 = driver.getTitle();
				Thread.sleep(2000);
				driver.navigate().back();
				Thread.sleep(2000);
				String hometitle2 = driver.getTitle();
				if(hometitle1 !=hometitle2){
					driver.navigate().back();
				}
			}
  }
  
  @Test
  public void test2() throws InterruptedException {
	 												
	 WebElement element = driver.findElement(By.xpath(".//*[@id='topNav1']"));   
	 // ArrayList<String> arraylist =new ArrayList<String>();
	 new Actions(driver).moveToElement(element).perform();
	 WebElement elemen =driver.findElement(By.xpath(".//*[@id='subMenu1']"));//.getAttribute("href"));
	 List<WebElement> list1 =elemen.findElements(By.tagName("a"));
	 Hashtable<String, String> ht =new Hashtable<String, String>();
	 for(WebElement item: list1){
		  ht.put(item.getText(), item.getAttribute("href")); 
	 } 
	 Enumeration<String> keys =ht.keys();
	 while(keys.hasMoreElements()){
		 
		 String key =keys.nextElement();
		 System.out.println(key);
		 String linkHref =ht.get(key);
		 System.out.println(linkHref); 
		 
		 driver.get(linkHref);//inestead of click 
		 
		 Thread.sleep(4000);
		 String cUrl = driver.getCurrentUrl();
		
		 System.out.println(cUrl);
		AssertJUnit.assertEquals(linkHref, cUrl);
		driver.navigate().back();   
	 }
  
		}
  */
  @Test
  public void test3() throws InterruptedException {
	  
	  verifySubMenu(3);  //NOOK BOOks (menue)
	  
  }
  int lastindex =0;
  public void verifySubMenu(int menue) throws InterruptedException{
	  
	 // if(!driver.findElements(By.xpath("//div/section/header/a")).isEmpty()){
		//  driver.findElement(By.xpath("//div/section/header/a")).click();//close.click();//THEN CLICK ON close
	  //}
	 
	WebElement element = driver.findElement(By.xpath(".//*[@id='topNav"+menue+"']"));
	  new Actions(driver).moveToElement(element).perform();
		//List<WebElement> list = driver.findElements(By.xpath(".//*[@id='topNav"+menue+"']//a"));//(".//*[@id='subMenu"+menue+"']//a"));
		 //System.out.println(list.size());
		ArrayList<String> arrayList = new ArrayList<String>();
		for(WebElement webElement : GetItem(menue)){
			String text = webElement.getAttribute("textContent");
			System.out.println("Item Found: " + text);
			if(text.trim() != ""){
				text=text.trim().replaceAll("\t", "");//replace("[\n]{2,}"," ");//replaceAll("[\\r\\n]+", " ");//replaceAll("\\r\\n|\\r|\\n", " ");//replaceAll("(\\r|\\n)+", " ");
				//text = text.replace("\n", "").replace("\r", "");
			//text =text.replaceAll("\n",".").trim();
			System.out.println("Item : " + text);
			//String[] parts = text.split(".",0);
			//for (String temp: parts){
		       //   System.out.println("temp  " +temp);
			//}
				//text=text.split("\n")[0].replace("\n", "");
				System.out.println("Item Adding: " + text);
				arrayList.add(text);
			}
		
		}
		
		for(String item : arrayList){
			System.out.println(arrayList.size());
			WebElement element1 = driver.findElement(By.xpath(".//*[@id='topNav"+menue+"']"));
			new Actions(driver).moveToElement(element1).perform(); 
			lastindex++;
			if(lastindex!=29){
				
			
			//System.out.println("Sub Menu: " + item);
			WebElement element2 = driver.findElement(By.xpath(".//*[@id='subMenu"+menue+"']//a[contains(text(), \""+item+"\")]"));
			String home =driver.getCurrentUrl();
			System.out.println("home is"+ home);
			String href = element2.getAttribute("href");
			System.out.println("href is"+href);
			if(lastindex==28){
				GetItem(menue).get(28).click();
			}else
			if(item.equals("My NOOK Library")){
				String curl = driver.getCurrentUrl();
				System.out.println("curl1 is "+ curl);
				element2.click();
				Thread.sleep(3000);
				driver.switchTo().frame(2);
				WebElement login = driver.findElement(By.xpath("//div/*[@id='dialog-title']"));
				String log =login.getText();
				System.out.println("log");
				AssertJUnit.assertEquals(log,"Sign In or Create Account");
				//String cur2 = driver.getCurrentUrl();
				//System.out.println("curl2  is "+cur2);
				WebElement frm =driver.findElement(By.tagName("a"));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", frm);
 				String cur3 = driver.getCurrentUrl();
				System.out.println("curl3  is "+cur3);
				driver.switchTo().defaultContent();
			}else{
			element2.click();
			}
			Thread.sleep(2000);
			
				driver.navigate().back();
				Thread.sleep(2000);
				String curl2 = driver.getCurrentUrl();
				System.out.println(curl2);
				  if(curl2 !=home){
					Thread.sleep(500);
					driver.navigate().back();
					curl2 = driver.getCurrentUrl();
					Thread.sleep(500);
				}	
				  driver.navigate().back(); 
			//Thread.sleep(3000);
			//AssertJUnit.assertEquals(href,curl);
			Thread.sleep(1000);
			}
			
		}
		//driver.close();
}
  
  public List<WebElement> GetItem(int menue){
	  List<WebElement> list = driver.findElements(By.xpath(".//*[@id='topNav"+menue+"']//a"));
	return list;
  }
   
  public void clickLinkByHref(String href) {
	  WebElement element = driver.findElement(By.xpath(".//*[@id='topNav1']")); 
	  new Actions(driver).moveToElement(element).perform();
	  WebElement anch = driver.findElement(By.xpath(".//*[@id='subMenu1']"));
	    List<WebElement> anchors = anch.findElements(By.tagName("a"));
	  java.util.Iterator<WebElement> i = anchors.iterator();
	 
	    while(i.hasNext()) {
	        WebElement anchor = i.next();
	     if(anchor.getAttribute("href").contains(href)) {
	         anchor.click();
	         break;
	        }
	    }
	}
  /*
  @Test
  public void test4() throws InterruptedException{
	  
	  //WebElement close = driver.findElement(By.xpath("//div/section/header/a"));
	  
	  if(!driver.findElements(By.xpath("//div/section/header/a")).isEmpty()){
		  driver.findElement(By.xpath("//div/section/header/a")).click();//close.click();//THEN CLICK ON close
	  }
	        //DO SOMETHING ELSE AS SUBMIT BUTTON IS NOT THERE
	    
	  
	  
	  //WebElement close = driver.findElement(By.xpath("//div/section/header/a"));
	 // close.click();
	  
	  WebElement element = driver.findElement(By.xpath(".//*[@id='topNav2']"));
	  new Actions(driver).moveToElement(element).perform();
	  WebElement element2 = driver.findElement(By.xpath(".//*[@id='subMenu2']//a[contains(text(), 'My NOOK Library')]"));
	  element2.click();
	  Thread.sleep(5000);
	  //int size = driver.findElements(By.tagName("iframe")).size();
	 // System.out.println(size);
	  driver.switchTo().frame(2);
	  	 // JavascriptExecutor jse = (JavascriptExecutor)driver;
	  WebElement login = driver.findElement(By.xpath("//h1[contains(@id,'dialog-title')]"));
	  
	  //String log = (String) jse.executeScript("return arguments[0].text", login);
	  
		//WebElement login = driver.findElement(By.xpath("//div[3]/header/div/h1"));
		String log =login.getText();
		System.out.println(log);
	    
  //}
*/
  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
  
  
  
  
  
  
  @AfterMethod
  public void afterMethod() {
  }

}
