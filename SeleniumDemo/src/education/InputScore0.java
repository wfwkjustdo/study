package education;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class InputScore0 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:" + File.separator
				+ "chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
//		System.setProperty("webdriver.firefox.bin","D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//		WebDriver driver =new FirefoxDriver();
		driver.get("http://192.168.11.159:8080/edu/index.html");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		username.clear();
		username.sendKeys("18986100118");
		password.clear();
		password.sendKeys("123456");
		WebElement login = driver.findElement(By.id("loginBtn"));
		login.click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> navigation = driver.findElements(By.className("incito-panel-icon"));
		navigation.get(1).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement scoreInput = driver.findElement(By.id("incito-score"));
		scoreInput.click();
		//�꼶
		Select gradeName = new Select(driver.findElement(By.id("score_gradename")));
		gradeName.selectByVisibleText("��2012��");
		//�༶
		Select className = new Select(driver.findElement(By.id("score_classname")));
		className.selectByVisibleText("1��");
		//��Ŀ
		Select subject = new Select(driver.findElement(By.id("score_subject")));
		subject.selectByVisibleText("����");
		
	try {
		Thread.sleep(10);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		//���Ƴɼ�����
//		WebElement totalScore = driver.findElement(By.id("score_fullmark"));
//		totalScore.clear();
//		totalScore.sendKeys("120");
	
		//��������
		Select examType = new Select(driver.findElement(By.id("score_testtype")));
		examType.selectByVisibleText("����");
		//����ʱ��
		WebElement examTime = driver.findElement(By.id("score_examDate"));
		examTime.click();
		
		//�ȴ��û�ȥѡ��ʱ��
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement search = driver.findElement(By.id("score_search"));
		search.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement scoreTable = driver.findElement(By.id("scoreInput_table")).findElement(By.tagName("tbody"));
		
	   List<WebElement> score = scoreTable.findElements(By.name("incito_score"));
	   //����ר��
	   System.out.println(score.size());
	   for (int i = 0; i < score.size(); i++) {
		Random r = new Random();
		String s = String.valueOf(r.nextInt(61)+60);
		score.clear();
		score.get(i).sendKeys(s);
	}
	   WebElement save = driver.findElement(By.id("score_save"));
	   save.click();
	   
	   driver.quit();

	}

}
