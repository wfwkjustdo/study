	package education;
	/*
	 * ��������ϴ���̨����ѧ�����Ĵ��룬������Ҫ��PATH·���·���Ҫ�ŵ��ĵ��������Ǻ�̨֧�ֵ����ͣ�
	 * Ȼ�������û������룬վ���ַ��driver·���Լ�autoit �ű���·���������д˴������ȫ�Զ��ϴ����Զ���
	 * �ļ��������е���Դ��
	 * version    author   date
	 * v1.0.0       ����         2014/03/11
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
	
	public class EduManagementUploader {
	    public static WebDriver driver = null;
	    //�����ĵ���ŵ�·�������û�����Լ������������㽫Ҫ�ϴ���֧�ֵ���Դ
		public static final String PATH = "E:\\TestDocument\\EduManagement";
		//������̨�ĵ�ַ������䶯�������޸�
		public static final String URL = "http://192.168.11.136:8080/edu/index.html";
		//Chrome�������driver��ַ������䶯�������޸�
		public static final String chromeDriverPath =  "E:\\DevEnvironment\\WebDriver\\chrome\\chromedriver.exe";
		//autoit�ű��ĵ�ַ������䶯�������޸�
		public static final String autoItScriptPath =  "E:\\DevEnvironment\\Autoit Script\\Upload.exe";
		//�û���
		public static final String USERNAME = "18872573204";
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
			//�����ѧ����
			WebElement mySource = driver.findElement(By.id("incito-edu-maneger"));
			mySource.click();
			waitForPageToLoad(shortWaitTime);
	
			//����File�� ��ȡ�ļ����ڵ�������Դ
			File f = new File(PATH);
			File file[] = f.listFiles();
			for (int j = 0; j < file.length; j++) {
			COUNT++;
			String filePath= file[j].getPath();//�õ��ļ��ľ���·��
			String fileName = file[j].getName().substring(0, file[j].getName().lastIndexOf("."));//�õ��ļ���
			//����½����ϴ���ť
			WebElement newAdd = driver.findElement(By.id("btn_new_upload"));
			newAdd.click();
			waitForPageToLoad(shortWaitTime);
			WebElement upload = driver.findElement(By.id("file_upload"));
			upload.click();
			waitForPageToLoad(shortWaitTime);
				
				//����autoit�ϴ���Դ
				new EduManagementUploader().handleUpload("", filePath);
				
				//����ļ����Ǻ��С��ƻ���2���֣����Զ�ѡ�񡰽�ѧ�ƻ�����������С����Ρ�2������ѡ�񡰱��Ρ�������������У������ѡ������
				Select type = new Select(driver.findElement(By.id("upload_type")));
	            if(fileName.contains("�ƻ�")){
	            	type.selectByVisibleText("��ѧ�ƻ�");
	            }else if(fileName.contains("����")){
	            	type.selectByVisibleText("����");
	            }else{
	            	Random r = new Random();
	            	int i = r.nextInt(2);
	            	type.selectByIndex(i);
	            }
	
				
			   //ѡ���꼶
				Select gradeName = new Select(driver.findElement(By.id("upload_grade"))) ;
				gradeName.selectByIndex(0);
				waitForPageToLoad(shortWaitTime);
				//ѡ��༶
				Select className = new Select(driver.findElement(By.id("upload_class"))) ;
				className.selectByIndex(0);
				waitForPageToLoad(shortWaitTime);
				//���ÿ�Ŀ
				Select courseName = new Select(driver.findElement(By.id("upload_subject"))) ;
				//����ĵ������к������ľ��ڿ�Ŀ������ѡ�����ġ����������ƣ��������������Щ��Ŀ�������ѡ��һ����Ŀ
	            if(fileName.contains("����")){
	            	courseName.selectByVisibleText("����");
	            }else if(fileName.contains("��ѧ")){
	            	courseName.selectByVisibleText("��ѧ");
	            }else if(fileName.contains("����")){
	            	courseName.selectByVisibleText("����");
	            }else if(fileName.contains("����")){
	            	courseName.selectByVisibleText("����");
	            }else if(fileName.contains("��ʷ")){
	            	courseName.selectByVisibleText("��ʷ");
	            }else if(fileName.contains("����")){
	            	courseName.selectByVisibleText("����");
	            }else if(fileName.contains("����")){
	            	courseName.selectByVisibleText("����");
	            }else if(fileName.contains("����")){
	            	courseName.selectByVisibleText("����");
	            }else if(fileName.contains("��Ϣ����")){
	            	courseName.selectByVisibleText("��Ϣ����");
	            }else{
	            	Random r = new Random();
	            	int i = r.nextInt(9);
	            	courseName.selectByIndex(i);
	            	
	            }
			
				//������Դ���֣��Զ���ȡ�ļ������ֲ���ȥ����׺��
				WebElement sourceName = driver.findElement(By.id("upload_filename"));
				sourceName.clear();
				sourceName.sendKeys(fileName);
				
			//��Դ����
				WebElement sourceComment = driver.findElement(By.id("upload_filetext"));
				sourceComment.clear();
				sourceComment.sendKeys("����"+fileName+"������");
			//������水ť
				WebElement save = driver.findElement(By.id("btn_list_update"));
				save.click();
				//�ڵ����ϵ��ȷ����ť
				waitForPageToLoad(normalWaitTime);
				Alert confirm = driver.switchTo().alert();
				confirm.accept();
				waitForPageToLoad(shortWaitTime);
				System.out.println("�ļ���"+fileName+"�Ѿ��ϴ��ɹ���");
				if(COUNT==file.length){
					System.out.println("���н�ѧ��Դ�Ѿ��ϴ���ϣ������ϴ���"+COUNT+"����Դ��");
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
