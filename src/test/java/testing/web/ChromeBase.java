package testing.web;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class ChromeBase {
	
	private static ChromeDriverService service = null;
	private static WebDriver driver = null;
	
	private File _dir = new File("/tmp");
	
	public boolean screenshot() throws Exception {
		
		try {
			
		    if (!_dir.exists()) _dir.mkdirs();
		    
		    File scr = new File(_dir, "screenshot" + System.currentTimeMillis() + ".png");
		    
		    if(scr.exists()) scr.delete();
		    
		    FileUtils.copyFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), scr);
		    
		}catch(Exception e) {
			return false;
			
		}
	    return true;
	}
	
	@BeforeClass
	public static void _beforeClass() throws Exception {
		
		File driverFile = new File("drivers"
								.concat(File.separator)
								.concat("chromedriver.exe"));
	
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(driverFile)
				.usingAnyFreePort()
				.build();
		
		service.start();			
	}
	
	@AfterClass
	public static void _afterClass() {
		if (service!=null)service.stop();		
	}
	
	@Before
	public void _beforeTest() {
		if (service != null && service.isRunning()) {			
			driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
		}
	}
	
	@After
	public void _afterTest() {
		if (driver!= null) {
			driver.quit();
		}
	}
		
	public WebDriver getDriver() {
		return driver;
	}
	
}
