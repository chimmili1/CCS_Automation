package testscripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import datatable.Xls_Reader;
import reports.ReportUtil;
import utilities.TestUtil;


public class Driverscript {
	

	public static WebDriver driver=null;
	static Properties OR=null;
	static Properties ENV=null;
	static Properties p=null;
    static File f=null;
    static FileInputStream fis=null; 
	static Xls_Reader controller=null;
	static Xls_Reader testdata=null;
	static String currentTest;
	static String keyword;
	static String object;
	static String currentTSID;
	static String stepDescription;
	static String data_column_name;
	static String testStatus;
    static int  td;
	static String filename;
	static String currentTestName;
	static Hashtable<String,String>Odic= new Hashtable<String,String>();
    static Logger logs = Logger.getLogger("devpinoyLogger");
	static String Reportpath;
	static String sLogfile = System.getProperty("user.dir")+"\\Logfile.log";
	@BeforeSuite
	public static void startTesting() throws IOException{
		TestUtil.createfile(sLogfile);
		Reportpath=System.getProperty("user.dir")+"\\HTMLReports\\"+TestUtil.timestamp();
		TestUtil.createDir(Reportpath.replace("\\HTMLReports\\", "\\Logs\\"));	
		p=new Properties();
		FileInputStream f=new FileInputStream(System.getProperty("user.dir")+"\\src\\log4j.properties");
		p.load(f);
		p.setProperty("log4j.appender.dest1.File",Reportpath.replace("\\HTMLReports\\", "\\Logs\\")+"\\Logfile.log");
		PropertyConfigurator.configure(p); 
    	 TestUtil.createDir(Reportpath);
		logs.info("Test started time "+TestUtil.now("hh:mm:ss aaa"));
		ReportUtil.startTesting(Reportpath+"\\index.html", TestUtil.now("hh:mm:ss aaa"), "Test", "1.1.3");
		
	}
	
	
	@Test
	public void AppTest() throws IOException{
		String startTime=null;
		ReportUtil.startSuite("TestSuite");
		System.out.println(controller.getRowCount("TestSuite"));
		for(int tcid=2;tcid<=controller.getRowCount("TestSuite");tcid++){
			
			currentTest = controller.getCellData("TestSuite", "TCID", tcid);
			currentTestName = controller.getCellData("TestSuite", "TCName", tcid);
			System.out.println(currentTest);
			System.out.println(currentTestName);
			System.out.println(controller.getCellData("TestSuite", "Run Mode", tcid));
			if(controller.getCellData("TestSuite", "Run Mode", tcid).equals("Y")){

				int totalSets=testdata.getRowCount(currentTest);
				if(totalSets<=1){
					totalSets=2; 
				}
			
			for( td=2; td<=totalSets;td++){	
				startTime=TestUtil.now("hh:mm:ss aaa");
				
				logs.debug("Executing the test *************"+ currentTest + "  ***********************");
					for(int tsid=2;tsid<=controller.getRowCount(currentTest);tsid++){
						keyword=controller.getCellData(currentTest, "Keyword", tsid);
						object=controller.getCellData(currentTest, "Object", tsid);
						currentTSID=controller.getCellData(currentTest, "TSID", tsid);
						stepDescription=controller.getCellData(currentTest, "Description", tsid);
						data_column_name=controller.getCellData(currentTest, "Data_Value", tsid);
						logs.debug(keyword);						
						try{
							Method method= Keywords_Driver.class.getMethod(keyword);
						   String result= (String) method.invoke(method);
						  // System.out.println(result);
						   logs.debug("************Result of execution************** "+result);
							if(result.startsWith("Fail")){
								testStatus=result;
								// take screenshot - only on error
							// filename="TestSuite_TC"+(tcid-1)+"_TS"+(tsid-1)+"_"+keyword+".jpg";
								filename=currentTestName+(tcid-1)+"_TS"+(tsid-1)+"_"+keyword+".jpg";
																
							 filename=ENV.getProperty("screenshotPath")+filename;
							TestUtil.takeScreenShot(filename);
							logs.debug(stepDescription+"---- "+keyword+"---- "+result+"---- "+filename);
							 ReportUtil.addKeyword(stepDescription, keyword, result, filename);	
							 filename=null;
							}else{
								logs.debug(stepDescription+"----- "+keyword+"----- "+result);
								ReportUtil.addKeyword(stepDescription, keyword, result, filename);
							}
						
						}catch(Exception e){
						 logs.debug(e);
						}
						//System.out.println(testStatus);
						if(testStatus == "Fail"){
							//driver.switchTo().defaultContent();
							//driver.close();
							driver.quit();
							break;
							//System.out.println(driver.switchTo().alert().getText());
							/*if (driver.switchTo().alert().getText().contains("want to leave")){
								driver.switchTo().alert().accept();
							}
							break;*/
						}
					}	
					
					if(testStatus == null){
						testStatus="Pass";
					}
					logs.debug("*************************************** "+currentTest+" **************************************  " +testStatus);
					ReportUtil.addTestCase(currentTestName, 
											startTime, TestUtil.now("hh:mm:ss aaa"),
							            	testStatus );	
					
			}
					
					
			}else{
			
					testStatus="Skip";
					logs.debug("*************************************** "+currentTest+" **************************************  " +testStatus);
					ReportUtil.addTestCase(currentTestName, 
											TestUtil.now("hh:mm:ss aaa"), 
											TestUtil.now("hh:mm:ss aaa"),
											testStatus );
					
				}
				
					testStatus=null;
				
				
			}
		     
			ReportUtil.endSuite();
						
							
			}	
	
						
	
	 @BeforeClass
	 public void Intilaize_Properties() throws IOException{
		  
			try{
				
				ENV=new Properties();
			
				FileInputStream f=new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\QA.properties");
			    ENV.load(f);
			    logs.debug("loaded QA properties file");
			    OR=new Properties();
				FileInputStream frep=new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\OR.properties");
			    OR.load(frep);
			    logs.debug("loaded Object repository properties file");
			    controller=new Xls_Reader(System.getProperty("user.dir")+"\\src\\config\\TestScripts.xls");
			    testdata=new Xls_Reader(System.getProperty("user.dir")+"\\src\\config\\Testdata.xls");
			    logs.info("loaded test repository and data repository files");
			}catch(Exception e){
				
				e.printStackTrace();
				logs.debug("Files are not loaded check the file is located in the given path",e);
				
			}
			
			
		}
	 
	 @AfterSuite
		public static void endScript(){
		 logs.debug("Test suite end time "+TestUtil.now("hh:mm:ss aaa"));
			ReportUtil.updateEndTime(TestUtil.now("hh:mm:ss aaa"));
				
			

		}
}
	 
	
