package Utill;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SelectBrowserUtil {
	public static enum Driver {
		CHROME, IE, ANDROID, SAFARI, FIREFOX
	}
	
	public static WebDriver newInstance(Driver selectedDriver) throws ClassNotFoundException {
		if(selectedDriver.equals(Driver.CHROME)) return newChromeDriver();
		else if(selectedDriver.equals(Driver.IE)) return newInternetExplorerDriver();
		else if(selectedDriver.equals(Driver.SAFARI)) return safariDriver();
		else if(selectedDriver.equals(Driver.FIREFOX)) return newFirefoxDriver();
		else throw new ClassNotFoundException();
	}
	
	public static FirefoxDriver newFirefoxDriver(){ return new FirefoxDriver(); }
	
	public static SafariDriver safariDriver(){ return new SafariDriver(); }
	
	public static ChromeDriver newChromeDriver(){
//		System.setProperty("webdriver.chrome.driver", ProjectInfo.absolutePath + "/drivers/chromedriver.exe");
		return new ChromeDriver();
	}
	
	public static InternetExplorerDriver newInternetExplorerDriver(){
//		System.setProperty("webdriver.ie.driver", ProjectInfo.absolutePath + "/drivers/IEDriverServer.exe");
		return new InternetExplorerDriver();
	}

}
