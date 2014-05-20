package education;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class InputCourse0 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:" + File.separator	+ "chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		driver.get("http://192.168.11.136:8080/edu/login.html");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		username.clear();
		username.sendKeys("18986100888");
		password.clear();
		password.sendKeys("123456");
		WebElement login = driver.findElement(By.id("loginBtn"));
		login.click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> navigation = driver.findElements(By.className("incito-panel-icon"));
		navigation.get(2).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement classMange = driver.findElement(By.id("incito-class"));
		classMange.click();
		
		WebElement targetModify = driver.findElement(By.id("92044c0f88f84d13b299d2cb0dbe2016"));
		List<WebElement> td = targetModify.findElements(By.tagName("td"));
		//调试专用
        System.out.println(td.get(8).getText());
		td.get(8).click();
		
		WebElement newAdd = driver.findElement(By.id("btn_schedule_modify"));
		newAdd.click();
		
		
		WebElement table = driver.findElement(By.id("scheduleSave_table"));
		List<WebElement> course = table.findElements(By.className("incito-schedule-new"));
	     System.out.println("一共有"+course.size()+"课");
	     for (int i = 0; i < course.size(); i++) {
	    	 Select s = new Select(course.get(i));
	    	 Random r = new Random();
	    	 s.selectByIndex(r.nextInt(3)+1);
	    
		}
	}
}
