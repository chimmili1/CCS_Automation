package utilities;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import testscripts.Driverscript;







public class TestUtil extends Driverscript{

//	static String image="D:\\Screenshots\\image.jpg";
	// returns current date and time
	public static String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());

	}
	
	public static String timestamp() {
		 Date date = new Date();
    	 SimpleDateFormat sdf = new SimpleDateFormat("MM:dd:yyyy h:mm:ss a");
    	 String formattedDate = sdf.format(date).replaceAll(":","").replaceAll(" ", "").substring(1, 13);
         return formattedDate;
	}
	 public static void createfile(String sfile) throws IOException{
			File fs = new File(sfile);
			if(!fs.exists()){
				fs.createNewFile();
			}
	 }
	public static  void createDir(String dirname) {
		
		try{
			File dir = new File(dirname);
			if(!dir.exists()){
		   		 dir.mkdir();
		
		   	 }
		}catch(Exception e){
			
		}
	}
	// store screenshots
	
	public static void takeScreenShot(String filepath) {
		System.out.println(filepath);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
			FileUtils.copyFile(scrFile, new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
	    
	}

	

}
