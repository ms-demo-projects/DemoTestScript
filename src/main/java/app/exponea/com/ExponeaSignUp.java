package app.exponea.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utill.Constant;
import Utill.ExcelUtils;
import Utill.Log;
import Utill.Utils;

public class ExponeaSignUp {
	private WebDriver driver;
	private String baseUrl;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		Log.logSetup();
		System.setProperty("javax.xml.transform.TransformerFactory",
				"com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
		driver = new ChromeDriver();
		// System.setProperty("webdriver.gecko.driver",
		// "src/main/resources/geckodriver");
		// driver = new FirefoxDriver();
		driver.manage().window().maximize();
		baseUrl = Constant.URL;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		ExcelUtils.setExcelFile(Constant.FILE_FULL_PATH, Constant.SHEET_NAME);
	}

	@DataProvider(name = "SignUp")
	public static Object[][] dataSignUp() throws Exception {
		return new Object[][] { { ExcelUtils.getCellData(1, 1), ExcelUtils.getCellData(1, 2),
				ExcelUtils.getCellData(1, 3), ExcelUtils.getCellData(1, 4), ExcelUtils.getCellData(1, 5) } };
	}

	@Test(priority=1,dataProvider = "SignUp")
	public void exponeaSignUp(String emailId, String password, String companyName, String telephoneNo, String promoCode)
			throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.linkText("Log In")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//html/body/div[1]/div/div/logged-out/div/div[1]/div/div/div/div[1]/ul/li[2]/a"))
				.click();
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(emailId);
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys(password);
		driver.findElement(By
				.xpath("//html/body/div[1]/div/div/logged-out/div/div[1]/div/div/div/div[1]/div/sign-up/form/fieldset/div[4]/span"))
				.click();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(companyName);
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys(telephoneNo);
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys(promoCode);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		Utils.saveScreenshot(driver, ExponeaSignUp.class);
		driver.findElement(By.cssSelector("i.fi-caret-down")).click();
		driver.findElement(By.cssSelector("i.fi-logout")).click();
		ExcelUtils.setCellData("Pass", 1, 6);
	}

	@DataProvider(name = "Login")
	public static Object[][] dataLogin() throws Exception {
		return new Object[][] { { ExcelUtils.getCellData(2, 1), ExcelUtils.getCellData(2, 2)} };
	}

	@Test(priority=2,dataProvider = "Login")
	public void exponeaLogin(String emailId, String password)
			throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.linkText("Log In")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(emailId);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		Utils.saveScreenshot(driver, ExponeaSignUp.class);
		driver.findElement(By.cssSelector("i.fi-caret-down")).click();
		driver.findElement(By.cssSelector("i.fi-logout")).click();
		ExcelUtils.setCellData("Pass", 2, 6);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();

	}
}
