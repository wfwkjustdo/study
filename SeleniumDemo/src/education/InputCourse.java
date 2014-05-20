package education;

/*
 * 如果一个班级没有课程以后可以用这段代码实现自动添加课程的功能 
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
	// 教育后台地址，如果有改变请修改URL地址
	public static final String URL = "http://192.168.11.159:8080/edu/index.html";

	// 用户名，如果有变动请修改
	public static final String USERNAME = "18986100345";

	// 用户密码
	public static final String PASSWD = "123456";

	/*
	 * 这个数组表示：这里有2个双引号，表示当前账号在班级管理模块有2页数据需要处理，而10表示
	 * 第一页有10个班级需要修改，8表示第二页有8个班级需要修改，比如要是现在有三页了就这样修改下public static final String
	 * STR[] = {"XX","XX","XX"} XX表示 该页有多少个班级要修改
	 */
	public static final String STR[] = { "2" };

	// 定义有多少科目（下拉菜单的空白不要统计进去）
	public static final int COURSES = 9;

	// 定义当前班级管理页面有多少页码
	public static final int PAGES = 1;

	public static void waitForPageToLoad(int waitTime) {
		// 等待页面加载
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

					WebElement nextPage = driver.findElement(By.linkText("下一页"));
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
