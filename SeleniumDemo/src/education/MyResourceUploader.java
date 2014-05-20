	package education;
	/*
	 * ��������ϴ���̨���ҵ���Դ���Ĵ��룬������Ҫ��PATH·���·���Ҫ�ŵ��ĵ��������Ǻ�̨֧�ֵ����ͣ�
	 * Ȼ�������û������룬վ���ַ��driver·���Լ�autoit �ű���·���������д˴������ȫ�Զ��ϴ����Զ���
	 * �ļ��������е���Դ����Դ���־��Բ����ظ�����������ļ����к�����ͬ����Դ����
	 * version    author                 date                        comments
	 * v1.0.0       ����         2014/03/10              ���δ���
	 * v1.0.1       ����         2014/04/05              ����ϴ����ļ�ʱ�ϴ�ʧ��bug�����޸�
	 * 
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
	
	public class MyResourceUploader {
	    public static WebDriver driver = null;
	  //�����ĵ���ŵ�·�������û�����Լ������������㽫Ҫ�ϴ���֧�ֵ���Դ
		public static final String PATH = "E:\\TestDocument\\MyResource";
		//������̨�ĵ�ַ������䶯�������޸�
		public static final String URL = "http://61.155.215.91:9080/edu/index.html";
		//Chrome�������driver��ַ������䶯�������޸�
		public static final String chromeDriverPath =  "E:\\DevEnvironment\\WebDriver\\chrome\\chromedriver.exe";
		//autoit�ű��ĵ�ַ������䶯�������޸�
		public static final String autoItScriptPath =  "E:\\DevEnvironment\\Autoit Script\\Upload.exe";
		//�û���
		public static final String USERNAME = "13890344533";
		//����
		public static final String PASSWD = "123456";
		public static final int minWaitTime = 500;
		public static final int shortWaitTime = 1000;
		public static final int normalWaitTime = 3000;
		public static final int longWaitTime = 5000;
		public static int COUNT = 0;
		
	//�ϴ��ļ���������
		public void handleUpload(String browser,String filepath) {
			String execute_file = autoItScriptPath;
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
		//���õȴ�ҳ����صķ���
		public static void waitForPageToLoad(int waitTime){
			
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
		
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			try{
			System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		   driver = new ChromeDriver();
			driver.get(URL);
			waitForPageToLoad(shortWaitTime);
			WebElement username = driver.findElement(By.id("username"));
			WebElement password = driver.findElement(By.id("password"));
			username.clear();
			username.sendKeys(USERNAME);
			password.clear();
			password.sendKeys(PASSWD);
			WebElement login = driver.findElement(By.id("loginBtn"));
			login.click();
			waitForPageToLoad(shortWaitTime);
			//�����һ��С��ͷ
			List<WebElement> navigation = driver.findElements(By.className("incito-panel-icon"));
			navigation.get(0).click();
			waitForPageToLoad(shortWaitTime);
			//����ҵ���Դ
			WebElement mySource = driver.findElement(By.id("incito-my-resource"));
			mySource.click();
			waitForPageToLoad(shortWaitTime);
	
			//����File�� ��ȡ�ļ����ڵ�������Դ
			File f = new File(PATH);
			File file[] = f.listFiles();
			for (int j = 0; j < file.length; j++) {
			COUNT++;
			String filePath= file[j].getPath();
			String fileName = file[j].getName().substring(0, file[j].getName().lastIndexOf("."));
			//����½����ϴ���ť
			WebElement newAdd = driver.findElement(By.id("resource-btn-newfile"));
			newAdd.click();
			waitForPageToLoad(shortWaitTime);
			WebElement upload = driver.findElement(By.id("file_upload"));
			upload.click();
			waitForPageToLoad(shortWaitTime);
				
				//����autoit�ϴ���Դ
				new MyResourceUploader().handleUpload("", filePath);
				
				//���������֮�������������ѡ�����Χ�ǣ���У�������ż�������Χ�ǣ�����
				Select share = new Select(driver.findElement(By.id("resource_scope_name")));
				if(COUNT%2==0){
				share.selectByIndex(0);//ȫ��
				}else
				{
					share.selectByIndex(1);//��У
					
					}
				//������Դ���֣��Զ���ȡ�ļ������ֲ���ȥ����׺��
				WebElement sourceName = driver.findElement(By.id("resource_upload_name"));
				sourceName.clear();
				sourceName.sendKeys(fileName);
				
			//ѡ��һ��������������
				Select file1 = new Select(driver.findElement(By.id("sourcelevel1"))) ;
		 	    List<WebElement> file1Options = file1.getOptions();
		 	    int file1Num = file1Options.size();
		 	    file1.selectByIndex(new Random().nextInt(file1Num));
				waitForPageToLoad(shortWaitTime);
				
				Select file2 = new Select(driver.findElement(By.id("sourcelevel2"))) ;
		 	    List<WebElement> file2Options = file2.getOptions();
		 	    int file2Num = file2Options.size();
		 	    file2.selectByIndex(new Random().nextInt(file2Num));
				waitForPageToLoad(shortWaitTime);
				
				Select file3 = new Select(driver.findElement(By.id("sourcelevel3"))) ;
		 	    List<WebElement> file3Options = file3.getOptions();
		 	    int file3Num = file3Options.size();
		 	    file3.selectByIndex(new Random().nextInt(file3Num));
				
			//��Դ����
				WebElement sourceComment = driver.findElement(By.id("resource_description"));
				sourceComment.clear();
				sourceComment.sendKeys("������Դ"+fileName+"����");
			//�ȴ���Դ�ϴ��ɹ� �ٵ�����水ť
				for (int i = 0; i <  Integer.MAX_VALUE; i++) {
					Thread.sleep(1000);
					WebElement findUploadStatusUpdate = driver.findElement(By.id("file_upload_status"));
					//System.out.println(findUploadStatusUpdate.isDisplayed());
                     if(findUploadStatusUpdate.getText().trim().contains("�ļ��ϴ��ɹ�")){
     					WebElement save = driver.findElement(By.id("myRes_btn_upload_save"));
    					save.click(); 
    					break;
                        }
                     
				}
					
			

				//�ڵ����ϵ��ȷ����ť
				waitForPageToLoad(longWaitTime);
				Alert confirm = driver.switchTo().alert();
				confirm.accept();
				waitForPageToLoad(shortWaitTime);
				System.out.println("��Դ��"+fileName+"�Ѿ��ϴ��ɹ���");
				if(COUNT==file.length){
					System.out.println("������Դ�Ѿ��ϴ���ϣ������ϴ���"+COUNT+"����Դ��");
				}
				
			}
		
			
			}catch(Exception e){
				
		e.printStackTrace();
				
		}finally{

			//driver�˳����ͷ��ڴ�
			driver.quit();
			
			}
			}
	}
