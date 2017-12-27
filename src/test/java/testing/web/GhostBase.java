package testing.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GhostBase {
	
	WebDriver driver = null;
	
//	@BeforeClass public static void _beforeClass() throws Exception {}

	//if the phantomjs processes are not terminated, they are termniated first.
//	@AfterClass public static void _afterClass() throws Exception {
//		InputStreamReader isr = null;
//		InputStreamReader esr = null;
//		
//		BufferedReader br = null;
//		BufferedReader ebr = null;
//		
//		try {
//			
//			Process ps = Runtime.getRuntime().exec("ps -ef | grep phantom");
//			
//			isr = new InputStreamReader(ps.getInputStream());
//			esr = new InputStreamReader(ps.getErrorStream());
//			
//			br = new BufferedReader(isr);
//			ebr = new BufferedReader(esr);
//			
//			while(br.ready()) {
//				System.out.println(br.readLine());
//			}
//			
//			while(ebr.ready()) {
//				System.out.println(ebr.readLine());
//			}
//			
//		}catch (IOException ie) {
//			throw ie;
//		} finally {
//			if (isr!=null)isr.close();
//			if (esr!=null)esr.close();
//			if (br!=null)br.close();
//			if (ebr!=null)ebr.close();
//		}
//		
//	}
	
	@Before
	public void _beforeTest() throws Exception {

		java.io.File _file = new java.io.File("drivers/phantomjs");
		
		if ( _file == null || !_file.exists() ) {
			throw new FileNotFoundException("cannot found executable phantomjs");
		}
		
		String driverPath = _file.getAbsolutePath();
		DesiredCapabilities desireCaps = new DesiredCapabilities();
		desireCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);
		desireCaps.setPlatform(Platform.ANY);
		
		driver = new PhantomJSFixedDriver(desireCaps);
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
