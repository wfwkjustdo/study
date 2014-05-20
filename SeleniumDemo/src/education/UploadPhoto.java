package education;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class UploadPhoto {
	// 上传头像的具体实现方法：借助autoit工具实现
	public void handleUpload(String browser, String filepath) {
		String execute_file = "C:\\Upload.exe";
		String cmd = "\"" + execute_file + "\"" + " " + "\"" + browser + "\""
				+ " " + "\"" + filepath + "\"";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			// wait for the Upload.exe to complete
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void waitForPageToLoad(int waitTime) {
		// 等待页面加载
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public static WebDriver driver=null;
	public static void main(String[] args) {
        try{
		// 设置chrome driver
		System.setProperty("webdriver.chrome.driver", "C:" + File.separator
				+ "chromedriver.exe");
		// 实例化driver
		 driver = new ChromeDriver();
		// 打开此地址
		driver.get("http://192.168.11.136:8080/edu/login.html");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// 找到用户名和密码元素进行登陆
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		username.clear();
		username.sendKeys("18986100888");
		password.clear();
		password.sendKeys("123456");
		WebElement login = driver.findElement(By.id("loginBtn"));
		login.click();
		waitForPageToLoad(1500);
		// 点击数据维护
		List<WebElement> navigation = driver.findElements(By
				.className("incito-panel-icon"));
		navigation.get(2).click();
		waitForPageToLoad(1000);
		// 学生管理
		WebElement stuMange = driver.findElement(By.id("incito-student"));
		stuMange.click();
		waitForPageToLoad(1000);
		// 查找年纪班级的下拉元素
		Select school_grade = new Select(driver.findElement(By
				.id("student_grade")));
		Select class_name = new Select(driver.findElement(By
				.id("student_grade")));

		// 循环设置年级
		school_grade.selectByIndex(2);
		// 设置班级
		class_name.selectByIndex(2);
		// 点击查询按钮
		WebElement searchButton = driver.findElement(By.id("student_search"));
		searchButton.click();
		// 每页学生循环
		waitForPageToLoad(1000);

		// 定义页数（数组长度）和每页的有多少数据（学生）
		String str[] = { "8", "8", "8", "8", "8", "8", "2" };
		int count = 0;
		for (int j = 0; j < str.length; j++) {// 第一层循环是循环页码

			for (int i = 0; i < Integer.parseInt(str[j]); i++) {// 第二层循环是循环每页上的学生
				// 找到页面上的修改按钮
				List<WebElement> modifyButton = driver.findElements(By
						.className("stu-td-edit"));
				// 点击此按钮
				modifyButton.get(i).click();
				// 等待1秒钟
				waitForPageToLoad(1000);
				// 点击上传按钮
				WebElement uploadButton = driver.findElement(By
						.id("file_upload"));
				uploadButton.click();
				// 调用AutoIt上传文件
				new UploadPhoto().handleUpload("", "D:\\photo.jpg");
				waitForPageToLoad(1000);
				// 保存
				WebElement saveButton = driver.findElement(By
						.id("stuEdit_save"));
				waitForPageToLoad(1000);
				saveButton.click();

			}
			count++;

			// 当该页循环完成以后点击下一页进行下一页的循环
			if (count < 7) {
				WebElement nextPage = driver.findElement(By.linkText("下一页"));
				nextPage.click();
			}
			
		}
		
        }catch(Exception e){
        	e.printStackTrace();
        	
        }finally{		
        	// chrome驱动退出，释放内存
    		driver.quit();
    		}
   

}
	}
