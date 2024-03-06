package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	static public WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups= {"sanity","regression"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		//Loading properties file
		FileReader file=new FileReader(".//src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		//Loading log4j2 file
		logger=LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
				
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os...");
				return;
			}
			switch(br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			default: System.out.println("No matching browser...");
			}
		   driver=new RemoteWebDriver(new URL("http://192.168.1.35:4444"),capabilities);
		}
		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			//Launching browser based on condition
			switch(br.toLowerCase()) {
			case "chrome":driver=new ChromeDriver(); break;
			case "edge":driver=new EdgeDriver();break;
			default: System.out.println("No matching browser...");
			return;
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();	
	}
	@AfterClass(groups= {"sanity","regression"})
	public void teardown() {
		driver.quit();
	}
	public String captureScreen(String tname) {
		String timeStamp=new SimpleDateFormat("yyyyMMddhhss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sourceFile=ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath=(System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png");
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);//copying ss to targetfile
		return targetFilePath;
	}
}
