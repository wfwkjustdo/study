
package education;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class InputScore_new3 {
	
	public static void waitForPageToLoad(int waitTime){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		
	}
	public static final String URL = "http://192.168.11.136:8080/edu/index.html";
	public static WebDriver driver = null;
	public static final String USERNAME = "18986100333";
	public static final String PASSWD = "123456";
	
	public static void main(String[] args) {
		try{
		System.setProperty("webdriver.chrome.driver", "C:" + File.separator
				+ "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		username.clear();
		username.sendKeys(USERNAME);
		password.clear();
		password.sendKeys(PASSWD);
		WebElement login = driver.findElement(By.id("loginBtn"));
		login.click();
		waitForPageToLoad(1000);
		List<WebElement> navigation = driver.findElements(By.className("incito-panel-icon"));
		navigation.get(1).click();
		waitForPageToLoad(1000);
		WebElement scoreInput = driver.findElement(By.id("incito-score"));
		scoreInput.click();
		//�꼶
		Select gradeName = new Select(driver.findElement(By.id("score_gradename")));
		gradeName.selectByVisibleText("С2013��");
		//�༶
		Select className = new Select(driver.findElement(By.id("score_classname")));
		className.selectByVisibleText("1��");
		//��Ŀ
		Select subject = new Select(driver.findElement(By.id("score_subject")));
		subject.selectByVisibleText("��ѧ");

		//��������
		Select examType = new Select(driver.findElement(By.id("score_testtype")));
		examType.selectByVisibleText("����");
		
		//���� �������趨�������2012-10-25 �ĳ�����Ҫ��ʱ��

		String js="document.getElementById('score_examDate').removeAttribute('readonly');document.getElementById('score_examDate').setAttribute('value','2014-02-06');";
		((JavascriptExecutor) driver).executeScript(js);


		WebElement search = driver.findElement(By.id("score_search"));
		search.click();
		waitForPageToLoad(1000);
		
		WebElement scoreTable = driver.findElement(By.id("scoreInput_table")).findElement(By.tagName("tbody"));
		
	   List<WebElement> score = scoreTable.findElements(By.name("incito_score"));
	   //����nextInt()Բ��������������Ҫ����ĳɼ�
	    for (int i = 0; i < score.size(); i++) {
		Random r = new Random();
		String s = String.valueOf(r.nextInt(51));
		score.get(i).sendKeys(s);
	}
	   WebElement save = driver.findElement(By.id("score_save"));
	   save.click();
	   
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
	   
	   driver.quit();

	}
	}

}
