package testing.web;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebTest extends GhostBase {
//public class WebTest extends ChromeBase {
	
	@Test
	public void test1() throws Exception {
		
		WebDriver driver = getDriver();
		
		driver.get("http://192.168.1.50:9999");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		wait.withTimeout(10, TimeUnit.SECONDS);
		
		driver.findElement(By.linkText("Login")).click();
		
		wait.withTimeout(10, TimeUnit.SECONDS);
		
		driver.findElement(By.name("id")).sendKeys("test1");
		driver.findElement(By.name("passwd")).sendKeys("test1");
		
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		
		wait.withTimeout(30, TimeUnit.SECONDS);
		
		System.out.println(driver.getPageSource());
		
	}
	
}
