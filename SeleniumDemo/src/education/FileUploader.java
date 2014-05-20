package education;
//主要是“我的资源”的上传
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FileUploader {
//上传文件的具体实现方法
	public void handleUpload(String browser,String filepath) {
		String execute_file = "C:\\Upload.exe";
		String cmd = "\"" + execute_file + "\"" + " " + "\"" + browser + "\""
					+ " " + "\"" + filepath + "\""; //with arguments
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			//wait for the Upload.exe to complete
			p.waitFor(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
}
	
	public static void waitForPageToLoad(int waitTime){
		//等待页面加载
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:" + File.separator
				+ "chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		driver.get("http://192.168.11.136:8080/edu/login.html");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		username.clear();
		username.sendKeys("18872573204");
		password.clear();
		password.sendKeys("123456");
		WebElement login = driver.findElement(By.id("loginBtn"));
		login.click();
		waitForPageToLoad(1500);
		List<WebElement> navigation = driver.findElements(By.className("incito-panel-icon"));
		navigation.get(0).click();
		waitForPageToLoad(1000);
		WebElement mySource = driver.findElement(By.id("incito-my-resource"));
		mySource.click();
		waitForPageToLoad(1000);
		for (int i = 0; i < 5; i++) {
		WebElement newAdd = driver.findElement(By.id("resource-btn-newfile"));
		newAdd.click();
		waitForPageToLoad(1000);
		WebElement upload = driver.findElement(By.id("file_upload"));
		upload.click();
		//调用AutoIt上传文件
		new FileUploader().handleUpload("", "C:\\Users\\Stephen\\Desktop\\文档\\Bug书写规范说明V1.1.docx");
		//文件分享范围
		Select share = new Select(driver.findElement(By.id("resource_scope_name")));
		Random r1 = new Random();
		share.selectByIndex(r1.nextInt(2));
		//文件名字
		WebElement sourceName = driver.findElement(By.id("resource_upload_name"));
		sourceName.clear();
		String num = String.valueOf(i);
		sourceName.sendKeys("HelloWorld"+num);
		//文件类别
		Select file1 = new Select(driver.findElement(By.id("sourcelevel1"))) ;
		file1.selectByVisibleText("课件");
		waitForPageToLoad(1000);
		Select file2 = new Select(driver.findElement(By.id("sourcelevel2"))) ;
		file2.selectByVisibleText("网络课件");
		waitForPageToLoad(1000);
		Select file3 = new Select(driver.findElement(By.id("sourcelevel3"))) ;
		file3.selectByVisibleText("互联网");
		//文件介绍
		WebElement sourceComment = driver.findElement(By.id("resource_description"));
		sourceComment.clear();
		sourceComment.sendKeys("这里是文档的介绍文字。。。。。。");
		//保存
		WebElement save = driver.findElement(By.id("myRes_btn_upload_save"));
		save.click();
		//接受alert
		waitForPageToLoad(1000);
		Alert confirm = driver.switchTo().alert();
		System.out.println("Alert的文本内容："+confirm.getText());
		confirm.accept();
		waitForPageToLoad(1000);
		//
	
	}
		driver.quit();
		}
}
