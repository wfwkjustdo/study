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
	// �ϴ�ͷ��ľ���ʵ�ַ���������autoit����ʵ��
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
		// �ȴ�ҳ�����
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public static WebDriver driver=null;
	public static void main(String[] args) {
        try{
		// ����chrome driver
		System.setProperty("webdriver.chrome.driver", "C:" + File.separator
				+ "chromedriver.exe");
		// ʵ����driver
		 driver = new ChromeDriver();
		// �򿪴˵�ַ
		driver.get("http://192.168.11.136:8080/edu/login.html");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// �ҵ��û���������Ԫ�ؽ��е�½
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		username.clear();
		username.sendKeys("18986100888");
		password.clear();
		password.sendKeys("123456");
		WebElement login = driver.findElement(By.id("loginBtn"));
		login.click();
		waitForPageToLoad(1500);
		// �������ά��
		List<WebElement> navigation = driver.findElements(By
				.className("incito-panel-icon"));
		navigation.get(2).click();
		waitForPageToLoad(1000);
		// ѧ������
		WebElement stuMange = driver.findElement(By.id("incito-student"));
		stuMange.click();
		waitForPageToLoad(1000);
		// ������Ͱ༶������Ԫ��
		Select school_grade = new Select(driver.findElement(By
				.id("student_grade")));
		Select class_name = new Select(driver.findElement(By
				.id("student_grade")));

		// ѭ�������꼶
		school_grade.selectByIndex(2);
		// ���ð༶
		class_name.selectByIndex(2);
		// �����ѯ��ť
		WebElement searchButton = driver.findElement(By.id("student_search"));
		searchButton.click();
		// ÿҳѧ��ѭ��
		waitForPageToLoad(1000);

		// ����ҳ�������鳤�ȣ���ÿҳ���ж������ݣ�ѧ����
		String str[] = { "8", "8", "8", "8", "8", "8", "2" };
		int count = 0;
		for (int j = 0; j < str.length; j++) {// ��һ��ѭ����ѭ��ҳ��

			for (int i = 0; i < Integer.parseInt(str[j]); i++) {// �ڶ���ѭ����ѭ��ÿҳ�ϵ�ѧ��
				// �ҵ�ҳ���ϵ��޸İ�ť
				List<WebElement> modifyButton = driver.findElements(By
						.className("stu-td-edit"));
				// ����˰�ť
				modifyButton.get(i).click();
				// �ȴ�1����
				waitForPageToLoad(1000);
				// ����ϴ���ť
				WebElement uploadButton = driver.findElement(By
						.id("file_upload"));
				uploadButton.click();
				// ����AutoIt�ϴ��ļ�
				new UploadPhoto().handleUpload("", "D:\\photo.jpg");
				waitForPageToLoad(1000);
				// ����
				WebElement saveButton = driver.findElement(By
						.id("stuEdit_save"));
				waitForPageToLoad(1000);
				saveButton.click();

			}
			count++;

			// ����ҳѭ������Ժ�����һҳ������һҳ��ѭ��
			if (count < 7) {
				WebElement nextPage = driver.findElement(By.linkText("��һҳ"));
				nextPage.click();
			}
			
		}
		
        }catch(Exception e){
        	e.printStackTrace();
        	
        }finally{		
        	// chrome�����˳����ͷ��ڴ�
    		driver.quit();
    		}
   

}
	}
