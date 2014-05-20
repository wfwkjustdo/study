package education;

/*
 * ���һ���༶û�пγ��Ժ��������δ���ʵ���Զ���ӿγ̵Ĺ��� 
 * */
import java.io.File;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class InputCourse {
	public static WebDriver driver = null;
	// ������̨��ַ������иı����޸�URL��ַ
	public static final String URL = "http://192.168.11.159:8080/edu/index.html";

	// �û���������б䶯���޸�
	public static final String USERNAME = "18986100345";

	// �û�����
	public static final String PASSWD = "123456";

	/*
	 * ��������ʾ��������2��˫���ţ���ʾ��ǰ�˺��ڰ༶����ģ����2ҳ������Ҫ������10��ʾ
	 * ��һҳ��10���༶��Ҫ�޸ģ�8��ʾ�ڶ�ҳ��8���༶��Ҫ�޸ģ�����Ҫ����������ҳ�˾������޸���public static final String
	 * STR[] = {"XX","XX","XX"} XX��ʾ ��ҳ�ж��ٸ��༶Ҫ�޸�
	 */
	public static final String STR[] = { "2" };

	// �����ж��ٿ�Ŀ�������˵��Ŀհײ�Ҫͳ�ƽ�ȥ��
	public static final int COURSES = 9;

	// ���嵱ǰ�༶����ҳ���ж���ҳ��
	public static final int PAGES = 1;

	public static void waitForPageToLoad(int waitTime) {
		// �ȴ�ҳ�����
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			System.setProperty("webdriver.chrome.driver", "C:" + File.separator
					+ "chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(URL);
			waitForPageToLoad(1000);
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
			navigation.get(2).click();
			waitForPageToLoad(1000);
			WebElement classMange = driver.findElement(By.id("incito-class"));
			classMange.click();
			waitForPageToLoad(1000);
			int count = 0;

			for (int n = 0; n < STR.length; n++) {
				for (int i = 0; i < Integer.parseInt(STR[n]); i++) {
					List<WebElement> itmes = driver.findElement(By.id("items")).findElements(By.tagName("tr"));
					List<WebElement> td = itmes.get(i).findElements(By.tagName("td"));
					td.get(8).click();
					waitForPageToLoad(1000);
					WebElement newAdd = driver.findElement(By.id("btn_schedule_modify"));
					newAdd.click();
					waitForPageToLoad(1000);
					WebElement table = driver.findElement(By.id("scheduleSave_table"));
					List<WebElement> course = table.findElements(By.className("incito-schedule-new"));
					for (int j = 0; j < course.size(); j++) {
						Select s = new Select(course.get(j));
						Random r = new Random();
						s.selectByIndex(r.nextInt(COURSES) + 1);
					}
					WebElement save = driver.findElement(By.id("scheduleSave_btnsave"));
					save.click();
					waitForPageToLoad(4000);
					Alert confirm = driver.switchTo().alert();
					confirm.accept();
					waitForPageToLoad(1000);
					WebElement back = driver.findElement(By.id("schedule_btnback"));
					back.click();
					waitForPageToLoad(1000);
				}
				count++;
				if (count < PAGES) {

					WebElement nextPage = driver.findElement(By.linkText("��һҳ"));
					nextPage.click();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
