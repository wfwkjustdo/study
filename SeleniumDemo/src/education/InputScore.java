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

public class InputScore {
	public static final String URL = "http://192.168.11.136:8080/edu/index.html";

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:" + File.separator
				+ "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		username.clear();
		username.sendKeys("18986100222");
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
		// 年级
		Select gradeName = new Select(driver.findElement(By.id("score_gradename")));
		gradeName.selectByVisibleText("初2013级");
		// 班级
		Select className = new Select(driver.findElement(By
				.id("score_classname")));
		className.selectByVisibleText("1班");
		// 科目
		Select subject = new Select(driver.findElement(By.id("score_subject")));
		subject.selectByVisibleText("语文");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 考试类型
		Select examType = new Select(
				driver.findElement(By.id("score_testtype")));
		examType.selectByVisibleText("期中");

		// 日期 请自行设定！！请把2012-10-25 改成你想要的时间
		String js = "document.getElementById('score_examDate').removeAttribute('readonly');document.getElementById('score_examDate').setAttribute('value','2012-02-03');";
		((JavascriptExecutor) driver).executeScript(js);

		WebElement search = driver.findElement(By.id("score_search"));
		search.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement scoreTable = driver.findElement(By.id("scoreInput_table"))
				.findElement(By.tagName("tbody"));

		List<WebElement> score = scoreTable.findElements(By
				.name("incito_score"));
		// 这里nextInt()圆括号中输入你想要随机的成绩
		for (int i = 0; i < score.size(); i++) {
			Random r = new Random();
			String s = String.valueOf(r.nextInt(51)+50);
//			score.get(i).clear();
//			try {
//				Thread.sleep(1500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Integer.parseInt(score.get(i).sendKeys(s));
			score.get(i).sendKeys(s);
		}
		WebElement save = driver.findElement(By.id("score_save"));
		save.click();

		driver.quit();

	}

}
