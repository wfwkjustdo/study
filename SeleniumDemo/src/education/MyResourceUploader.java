	package education;
	/*
	 * 这个内是上传后台“我的资源”的代码，首先需要在PATH路径下放入要放的文档，必须是后台支持的类型，
	 * 然后配置用户名密码，站点地址，driver路径以及autoit 脚本的路径即可运行此代码可以全自动上传您自定的
	 * 文件夹中所有的资源。资源名字绝对不会重复，除非你的文件夹中含有相同的资源名。
	 * version    author                 date                        comments
	 * v1.0.0       王阳         2014/03/10              初次创建
	 * v1.0.1       王阳         2014/04/05              针对上传大文件时上传失败bug进行修复
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
	  //定义文档存放的路径，如果没有请自己建，并放入你将要上传的支持的资源
		public static final String PATH = "E:\\TestDocument\\MyResource";
		//教育后台的地址，如果变动请自行修改
		public static final String URL = "http://61.155.215.91:9080/edu/index.html";
		//Chrome浏览器的driver地址，如果变动请自行修改
		public static final String chromeDriverPath =  "E:\\DevEnvironment\\WebDriver\\chrome\\chromedriver.exe";
		//autoit脚本的地址，如果变动请自行修改
		public static final String autoItScriptPath =  "E:\\DevEnvironment\\Autoit Script\\Upload.exe";
		//用户名
		public static final String USERNAME = "13890344533";
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
			//点击我的资源
			WebElement mySource = driver.findElement(By.id("incito-my-resource"));
			mySource.click();
			waitForPageToLoad(shortWaitTime);
	
			//利用File类 读取文件夹内的所有资源
			File f = new File(PATH);
			File file[] = f.listFiles();
			for (int j = 0; j < file.length; j++) {
			COUNT++;
			String filePath= file[j].getPath();
			String fileName = file[j].getName().substring(0, file[j].getName().lastIndexOf("."));
			//点击新建和上传按钮
			WebElement newAdd = driver.findElement(By.id("resource-btn-newfile"));
			newAdd.click();
			waitForPageToLoad(shortWaitTime);
			WebElement upload = driver.findElement(By.id("file_upload"));
			upload.click();
			waitForPageToLoad(shortWaitTime);
				
				//调用autoit上传资源
				new MyResourceUploader().handleUpload("", filePath);
				
				//定义计数器之后，如果是奇数则选择分享范围是：本校，如果是偶数则分享范围是：本县
				Select share = new Select(driver.findElement(By.id("resource_scope_name")));
				if(COUNT%2==0){
				share.selectByIndex(0);//全县
				}else
				{
					share.selectByIndex(1);//本校
					
					}
				//输入资源名字，自动读取文件的名字并且去掉后缀名
				WebElement sourceName = driver.findElement(By.id("resource_upload_name"));
				sourceName.clear();
				sourceName.sendKeys(fileName);
				
			//选择一级二级三级分类
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
				
			//资源描述
				WebElement sourceComment = driver.findElement(By.id("resource_description"));
				sourceComment.clear();
				sourceComment.sendKeys("这是资源"+fileName+"描述");
			//等待资源上传成功 再点击保存按钮
				for (int i = 0; i <  Integer.MAX_VALUE; i++) {
					Thread.sleep(1000);
					WebElement findUploadStatusUpdate = driver.findElement(By.id("file_upload_status"));
					//System.out.println(findUploadStatusUpdate.isDisplayed());
                     if(findUploadStatusUpdate.getText().trim().contains("文件上传成功")){
     					WebElement save = driver.findElement(By.id("myRes_btn_upload_save"));
    					save.click(); 
    					break;
                        }
                     
				}
					
			

				//在弹窗上点击确定按钮
				waitForPageToLoad(longWaitTime);
				Alert confirm = driver.switchTo().alert();
				confirm.accept();
				waitForPageToLoad(shortWaitTime);
				System.out.println("资源："+fileName+"已经上传成功！");
				if(COUNT==file.length){
					System.out.println("所有资源已经上传完毕！！共上传了"+COUNT+"份资源。");
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
