/*
 * ���������¼���ͳ���ɼ�����Ŀ������͵ĳɼ����Զ������룬����һ��¼�������꼶�༶��Ŀ����ͳ���Ŀ��Գɼ��������ζ���ȽϺ���
 * ֻ�趨λ�������94���޸�¼��ʱ�伴��
 * Ŀǰ��ȱ�ݾ��ǣ�ʱ����Ψһ��Ҳ����˵�����п������͵�ʱ����һ�µģ�����ʱ����������ں�̨���ݿ�ıȽϿ�
 * ���ȱ����ʱ�޷�����
 * 
 * */
package education;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class InputScore_AllExam {
	
	public static WebDriver driver = null;
	//������̨��ַ������иı����޸�URL��ַ
	public static final String URL = "http://192.168.11.159:8080/edu/login.html";
	//�û���������б䶯���޸�
	public static final String USERNAME = "18986100888";
	//�û�����
	public static final String PASSWD = "123456";
	public static int singleScore = 0;
	public static void waitForPageToLoad(int waitTime){
		
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

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
		waitForPageToLoad(1500);
		List<WebElement> navigation = driver.findElements(By.className("incito-panel-icon"));
		navigation.get(1).click();
		waitForPageToLoad(1000);
		WebElement scoreInput = driver.findElement(By.id("incito-score"));
		scoreInput.click();
	
	    //�꼶����
		Select gradeName = new Select(driver.findElement(By.id("score_gradename")));
		List<WebElement>  gradeOptions    = gradeName.getOptions();
        for (int i = 0; i < gradeOptions.size(); i++) {
        	gradeName.selectByIndex(i);
        	waitForPageToLoad(500);
        	//�༶����
    		Select className = new Select(driver.findElement(By.id("score_classname")));
    		List<WebElement>  classOptions    = className.getOptions();
        	for (int j = 0; j < classOptions.size(); j++) {
        		className.selectByIndex(j);
        		waitForPageToLoad(500);
        		//��Ŀ����
        		Select subjectName = new Select(driver.findElement(By.id("score_subject")));
        		List<WebElement>  subjectOptions    = subjectName.getOptions();
        		for (int j2 = 0; j2 < subjectOptions.size(); j2++) {
        			subjectName.selectByIndex(j2);
        			WebElement subjectScore = driver.findElement(By.id("score_fullmark"));
        			System.out.println("������"+subjectScore.getAttribute("value"));
        			singleScore = Integer.parseInt(subjectScore.getAttribute("value"));
        			
        			waitForPageToLoad(500);
        			//������������
        			Select typeName = new Select(driver.findElement(By.id("score_testtype")));
        			List<WebElement>  typeOptions    = typeName.getOptions();
					for (int k = 0; k < typeOptions.size(); k++) {
						typeName.selectByIndex(k);
					waitForPageToLoad(500);
					//ͨ��JS����ʱ��
					String js="document.getElementById('score_examDate').removeAttribute('readonly');document.getElementById('score_examDate').setAttribute('value','2014-03-03');";
					((JavascriptExecutor) driver).executeScript(js);
					WebElement search = driver.findElement(By.id("score_search"));
					search.click();
			        waitForPageToLoad(1000);
					
					WebElement scoreTable = driver.findElement(By.id("scoreInput_table")).findElement(By.tagName("tbody"));
					
				   List<WebElement> score = scoreTable.findElements(By.name("incito_score"));

				   for (int l = 0; l < score.size(); l++) {
					Random r = new Random();
					String s = String.valueOf(r.nextInt(singleScore-(singleScore/2-1))+singleScore/2);
					score.get(l).sendKeys(s);
				}
				   WebElement save = driver.findElement(By.id("score_save"));
				   save.click();
				   waitForPageToLoad(6000);
					Alert ok  = driver.switchTo().alert();
					ok.accept();					
					}
				}	
			}
		}
        }catch(Exception e){
        	e.printStackTrace();
        	
        }finally{
        	
        	driver.quit();
        }
	}
}
