package Utill;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class Utils {

	public static final String absolutePath = System.getProperty("user.dir");

	public static void saveScreenshot(WebDriver driver,Class name) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String date = sdf.format(new Date());
		String url = driver.getCurrentUrl().replaceAll("[\\/:*\\?\"<>\\|]", "_");
		String ext = ".png";
		String path = getScreenshotSavePath(name) + "/" + date + "_" + url + ext;

		try {
			if (driver instanceof TakesScreenshot) {
				File tmpFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				org.openqa.selenium.io.FileHandler.copy(tmpFile, new File(path));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getScreenshotSavePath(Class className) {
		String packageName = className.getClass().getPackage().getName();
		File dir = new File(screenshotSavePath + "/" + packageName + "/");
		dir.mkdirs();
		return dir.getAbsolutePath();
	}

	public static String screenshotSavePath = absolutePath + "/logs/screenshot/";

	public static String isLinkBroken(URL url) throws Exception {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			connection.connect();
			String response = connection.getResponseMessage();
			connection.disconnect();
			return response;
		}

		catch (Exception exp) {
			return exp.getMessage();
		}
	}

	public static List<WebElement> findAllLinks(WebDriver driver) {
		List<WebElement> elementList = new ArrayList();
		elementList = driver.findElements(By.tagName("a"));
		elementList.addAll(driver.findElements(By.tagName("img")));
		List<WebElement> finalList = new ArrayList();
		
		for (WebElement element : elementList) {
			if (element.getAttribute("href") != null) {
				finalList.add(element);
			}
		}
		return finalList;
	}
	
//	public static void scrollToWebElement(WebDriver driver,String id){
//		driver.executeScript("document.getElementById('text-8').scrollIntoView(true);");
//	}

}
