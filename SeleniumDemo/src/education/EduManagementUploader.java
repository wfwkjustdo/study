	package education;
	/*
	 * 这个内是上传后台“教学管理”的代码，首先需要在PATH路径下放入要放的文档，必须是后台支持的类型，
	 * 然后配置用户名密码，站点地址，driver路径以及autoit 脚本的路径即可运行此代码可以全自动上传您自定的
	 * 文件夹中所有的资源。
	 * version    author   date
	 * v1.0.0       王阳         2014/03/11
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
	    //定义文档存放的路径，如果没有请自己建，并放入你将要上传的支持的资源
		public static final String PATH = "E:\\TestDocument\\EduManagement";
		//教育后台的地址，如果变动请自行修改
		public static final String URL = "http://192.168.11.136:8080/edu/index.html";
		//Chrome浏览器的driver地址，如果变动请自行修改
		public static final String chromeDriverPath =  "E:\\DevEnvironment\\WebDriver\\chrome\\chromedriver.exe";
		//autoit脚本的地址，如果变动请自行修改
		public static final String autoItScriptPath =  "E:\\DevEnvironment\\Autoit Script\\Upload.exe";
		//用户名
		public static final String USERNAME = "18872573204";
		//密码
		public static final String PASSWD = "123456";
		public static final int minWaitTime = 500;
		public static final int shortWaitTime = 1000;
		public static final int normalWaitTime = 3000;
		public static final int longWaitTime = 5000;
		public static int COUNT = 0;
		
		//上传文件方法定义
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
		//设置等待页面加载的方法
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
			//点击第一个小箭头
			List<WebElement> navigation = driver.findElements(By.className("incito-panel-icon"));
			navigation.get(0).click();
			waitForPageToLoad(shortWaitTime);
			//点击教学管理
			WebElement mySource = driver.findElement(By.id("incito-edu-maneger"));
			mySource.click();
			waitForPageToLoad(shortWaitTime);
	
			//利用File类 读取文件夹内的所有资源
			File f = new File(PATH);
			File file[] = f.listFiles();
			for (int j = 0; j < file.length; j++) {
			COUNT++;
			String filePath= file[j].getPath();//得到文件的绝对路径
			String fileName = file[j].getName().substring(0, file[j].getName().lastIndexOf("."));//得到文件名
			//点击新建和上传按钮
			WebElement newAdd = driver.findElement(By.id("btn_new_upload"));
			newAdd.click();
			waitForPageToLoad(shortWaitTime);
			WebElement upload = driver.findElement(By.id("file_upload"));
			upload.click();
			waitForPageToLoad(shortWaitTime);
				
				//调用autoit上传资源
				new EduManagementUploader().handleUpload("", filePath);
				
				//如果文件名是含有“计划”2个字，则自动选择“教学计划”，如果含有“备课”2个字则选择“备课”，如果都不含有，则随机选择类型
				Select type = new Select(driver.findElement(By.id("upload_type")));
	            if(fileName.contains("计划")){
	            	type.selectByVisibleText("教学计划");
	            }else if(fileName.contains("备课")){
	            	type.selectByVisibleText("备课");
	            }else{
	            	Random r = new Random();
	            	int i = r.nextInt(2);
	            	type.selectByIndex(i);
	            }
	
				
			   //选择年级
				Select gradeName = new Select(driver.findElement(By.id("upload_grade"))) ;
				gradeName.selectByIndex(0);
				waitForPageToLoad(shortWaitTime);
				//选择班级
				Select className = new Select(driver.findElement(By.id("upload_class"))) ;
				className.selectByIndex(0);
				waitForPageToLoad(shortWaitTime);
				//设置科目
				Select courseName = new Select(driver.findElement(By.id("upload_subject"))) ;
				//如果文档名字中含有语文就在科目下拉中选择“语文”，依次类推，如果都不含有这些科目的则，随机选择一个科目
	            if(fileName.contains("语文")){
	            	courseName.selectByVisibleText("语文");
	            }else if(fileName.contains("数学")){
	            	courseName.selectByVisibleText("数学");
	            }else if(fileName.contains("外语")){
	            	courseName.selectByVisibleText("外语");
	            }else if(fileName.contains("政治")){
	            	courseName.selectByVisibleText("政治");
	            }else if(fileName.contains("历史")){
	            	courseName.selectByVisibleText("历史");
	            }else if(fileName.contains("地理")){
	            	courseName.selectByVisibleText("地理");
	            }else if(fileName.contains("物理")){
	            	courseName.selectByVisibleText("物理");
	            }else if(fileName.contains("生物")){
	            	courseName.selectByVisibleText("生物");
	            }else if(fileName.contains("信息技术")){
	            	courseName.selectByVisibleText("信息技术");
	            }else{
	            	Random r = new Random();
	            	int i = r.nextInt(9);
	            	courseName.selectByIndex(i);
	            	
	            }
			
				//输入资源名字，自动读取文件的名字并且去掉后缀名
				WebElement sourceName = driver.findElement(By.id("upload_filename"));
				sourceName.clear();
				sourceName.sendKeys(fileName);
				
			//资源描述
				WebElement sourceComment = driver.findElement(By.id("upload_filetext"));
				sourceComment.clear();
				sourceComment.sendKeys("这是"+fileName+"的描述");
			//点击保存按钮
				WebElement save = driver.findElement(By.id("btn_list_update"));
				save.click();
				//在弹窗上点击确定按钮
				waitForPageToLoad(normalWaitTime);
				Alert confirm = driver.switchTo().alert();
				confirm.accept();
				waitForPageToLoad(shortWaitTime);
				System.out.println("文件："+fileName+"已经上传成功！");
				if(COUNT==file.length){
					System.out.println("所有教学资源已经上传完毕！！共上传了"+COUNT+"份资源。");
				}
				
			}
		
			
			}catch(Exception e){
				
			e.printStackTrace();
			
		}finally{
			
			//driver退出，释放内存
			driver.quit();
			
			}
			
			}
	}
