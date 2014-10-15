package testscripts;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.Option;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Equivalence.Wrapper;


public class Keywords_Driver extends Driverscript {
static int counter=1;
static boolean flag=false;
	public static String openbrowser(){
	
	
		try{
			
		
		if (ENV.getProperty(data_column_name).equalsIgnoreCase("FireFox")){
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			logs.debug("Launched "+ENV.getProperty("browsername")+" browser ");
			return "Pass";
		}else if (ENV.getProperty(data_column_name).equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\browsers\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			return "Pass";
		}else if (ENV.getProperty(data_column_name).equalsIgnoreCase("internet explorer")){
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\browsers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			return "Pass";
		}else{
			return "Fail";
		}
		}catch(Exception e){
			e.printStackTrace();
			return "Fail";
		}
		
		
	}	
		
	
	public static String navigate(){
		   try{
			   
			   driver.get(ENV.getProperty(data_column_name));
			   logs.debug("naviagted to the URL-"+ ENV.getProperty(data_column_name));
			   return "Pass"; 
		   }catch(Exception e){
			   e.printStackTrace();
			   logs.debug("Error while navigating to the URL-"+ object + e.getMessage());
			   return "Fail"; 
		   }
		  
		  
	   }
	
	public static String navigatetoEOL(){
		
		   try{
			   driver.navigate().to(ENV.getProperty(data_column_name));
				if (driver.switchTo().frame(0).findElements(By.tagName("a")).size()>0){
					driver.switchTo().defaultContent();
				}else{
					while(true){
						driver.navigate().refresh();
						  Thread.sleep(30000L);
						  driver.switchTo().frame(0);
						if (driver.findElements(By.tagName("a")).size()>0){
							driver.switchTo().defaultContent();
						    break;
						 }
						driver.switchTo().defaultContent();
					  }
					
				}
				  
			   logs.debug("naviagted to the URL-"+ ENV.getProperty(data_column_name));
			   return "Pass"; 
		   }catch(Exception e){
			   e.printStackTrace();
			   logs.debug("Error while navigating to the URL-"+ object + e.getMessage());
			   return "Fail"; 
		   }
		  
		  
	   }

	public static String waitproperty(){
		   try{
			   System.out.println(data_column_name);
			   driver.manage().timeouts().implicitlyWait(Integer.parseInt(data_column_name),TimeUnit.SECONDS);
			   logs.debug("wait for the application to load-"+ data_column_name);
			   return "Pass"; 
		   }catch(Exception e){
			   e.printStackTrace();
			   logs.debug("Error on page to load-"+Driverscript.data_column_name + e.getMessage());
			   return "Fail"; 
		   }
		  
		  
	   }
	public static String sleep(){
		   try{
			 
			   Thread.sleep(Long.valueOf(data_column_name));
			   logs.debug("wait for the application to load-"+ data_column_name);
			   return "Pass"; 
		   }catch(Exception e){
			   e.printStackTrace();
			   logs.debug("Error on page to load-"+Driverscript.data_column_name + e.getMessage());
			   return "Fail"; 
		   }
		  
		  
	   }
	
	public static String switchToframebyindex(){
		   try{
			   System.out.println(data_column_name);
			   driver.switchTo().frame(Integer.parseInt(data_column_name));
			   logs.debug("Switched to the frame-"+ Integer.parseInt(data_column_name));
			   return "Pass"; 
		   }catch(Exception e){
			   e.printStackTrace();
			   logs.debug("Error on Switching to the frame -"+ Integer.parseInt(data_column_name) + e.getMessage());
			   return "Fail"; 
		   }
		   
		 
	}
	
	  public static String getinnerText(){
		   try{
			   System.out.println(data_column_name);
			   System.out.println(data_column_name.replaceAll("#","").trim());
			   String innertext=driver.findElement(By.xpath(OR.getProperty(object))).getText();
			   if (innertext.length()>0){
				   Odic.put(data_column_name.replaceAll("#","").trim(), innertext);
				      logs.debug("Stored text "+ innertext);
				      return "Pass"; 
			   }else{
				   logs.debug("does not contain text for the object "+OR.getProperty(object));
					  return "Fail"; 
			   }		   
				 
		   }catch(Exception e){
			   e.printStackTrace();
			   logs.debug("Element not exist -"+ OR.getProperty(object) + e.getMessage());
			   return "Fail"; 
		   }
	  }
	  
	  public static String checkinnerText(){
		   try{
			   System.out.println(data_column_name);
			   System.out.println(data_column_name.replaceAll("#",""));
			   String innertext=driver.findElement(By.xpath(OR.getProperty(object))).getText();
				  if(Odic.get(data_column_name.replaceAll("#","").trim()).equalsIgnoreCase(innertext)){
					  logs.debug("text"+ data_column_name.replaceAll("#","").trim()+" exist in the page");
					  return "Pass"; 
				  }	else{
					  logs.debug("text"+ Odic.get(data_column_name.replaceAll("#","").trim())+" not exist in the page");
						  return "Fail"; 
				   }	      
		   }catch(Exception e){
			   e.printStackTrace();
			   logs.debug("Element not exist -"+ OR.getProperty(object) + e.getMessage());
			   return "Fail"; 
		   }
	  }
	  
	 
	  
		   public static String selectdate(){
			  int flag=0;
			  String Actualmonth=null;
			   try{	
				String[] Arr=testdata.getCellData(currentTest,data_column_name, 2).split(" ");
			
		       
				 Actualmonth=driver.findElement(By.xpath(OR.getProperty(object).replaceAll("/tbody", "/parent::div/div/div/span[1]"))).getText();
				
				while(!Actualmonth.equalsIgnoreCase(Arr[1])){
					
					driver.findElement(By.xpath(OR.getProperty(object).replaceAll("/tbody", "/parent::div//div/a[2]"))).click();
					Actualmonth=driver.findElement(By.xpath(OR.getProperty(object).replaceAll("/tbody", "/parent::div/div/div/span[1]"))).getText();
				}
		
				WebElement Cal=driver.findElement(By.xpath(OR.getProperty(object)));
				List<WebElement> trElements=Cal.findElements(By.tagName("tr"));
				System.out.println(trElements.size());
				for(int i=1; i<=trElements.size();i++){	
				WebElement tr=	driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]"));
				List<WebElement>tdElements=tr.findElements(By.tagName("td"));
				System.out.println(tdElements.size());
				for(int j=1; j<=tdElements.size();j++){
					
					WebElement td=	driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td["+j+"]"));
					System.out.println(td.getAttribute("class"));
					if (!td.getAttribute("class").contains("ui-state-disabled")){
						String Actualdate=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td["+j+"]/a")).getText();
						if (Arr[0].equalsIgnoreCase(Actualdate)){
							driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td["+j+"]/a")).click(); 
							flag=1;
						   break;
						}
					}
					
						
					}
				  if (flag==1){
					 break;
				  }
				}
				 logs.debug("Selected date from the application-"+ testdata.getCellData(currentTest,data_column_name, 2));
				return "Pass";
			}catch(Exception e){
				logs.debug("Error on selecting the date from the application-"+testdata.getCellData(currentTest,data_column_name, 2) + e.getMessage());
				   return "Fail"; 
			}
	   }
		   
		   public static String selectbirthdate(){
				  int flag=0;
				   try{	
					WebElement Cal=driver.findElement(By.xpath(OR.getProperty(object)));
					List<WebElement> trElements=Cal.findElements(By.tagName("tr"));
					System.out.println(trElements.size());
					for(int i=1; i<=trElements.size();i++){	
					WebElement tr=	driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]"));
					List<WebElement>tdElements=tr.findElements(By.tagName("td"));
					System.out.println(tdElements.size());
					for(int j=1; j<=tdElements.size();j++){
						
						WebElement td=	driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td["+j+"]"));
						System.out.println(td.getAttribute("class"));
						if (!td.getAttribute("class").contains("ui-state-disabled")){
							String Actualdate=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td["+j+"]/a")).getText();
							if (testdata.getCellData(currentTest,data_column_name, 2).equalsIgnoreCase(Actualdate)){
								driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td["+j+"]/a")).click(); 
								flag=1;
							   break;
							}
						}
						
							
						}
					  if (flag==1){
						 break;
					  }
					}
					 logs.debug("Selected date from the application-"+ testdata.getCellData(currentTest,data_column_name, 2));
					return "Pass";
				}catch(Exception e){
					logs.debug("Error on selecting the date from the application-"+testdata.getCellData(currentTest,data_column_name, 2) + e.getMessage());
					   return "Fail"; 
				}
		   }
	   
	   public static String click(){
		   try{ 
		   driver.findElement(By.xpath(OR.getProperty(object))).click();
		   logs.debug("clicked on link -"+ object);
		   return "Pass";
	   }catch(Exception e){
		   StringWriter stack = new StringWriter();
		   e.printStackTrace(new PrintWriter(stack));
		   e.printStackTrace();
		   System.out.println(stack.toString());
		  logs.debug("Error while clicking on link -  "+ object + stack.toString());
		   return "Fail"; 
	   }
	 }	
	  
	   public static String clickonobjectusingcoordinates(){
		   try{ 
		   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Actions a=new Actions(driver);
		Point p= driver.findElement(By.xpath(OR.getProperty(object))).getLocation();
		System.out.println(p.x+" "+p.y);
		a.moveToElement(driver.findElement(By.xpath(OR.getProperty(object))),p.x,p.y).click().perform();
		
		   logs.debug("clicked on link -"+ object);
		   return "Pass";
	   }catch(Exception e){
		   StringWriter stack = new StringWriter();
		   e.printStackTrace(new PrintWriter(stack));
		   e.printStackTrace();
		   System.out.println(stack.toString());
		  logs.debug("Error while clicking on link -  "+ object + stack.toString());
		   return "Fail"; 
	   }
	 }	
	   
	   
	   
	   public static String sendkeys(){
		   try{ 
			   driver.findElement(By.xpath(OR.getProperty(object))).clear();  
		   driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(testdata.getCellData(currentTest,data_column_name, 2));
		   logs.debug("Entered Value on textbox -"+ object);
		   return "Pass";
	   }catch(Exception e){
		  logs.debug("Error while Entering Value on textbox -  "+ object);
		  e.printStackTrace();
		   return "Fail"; 
	   }
	 }	
	   
	   public static String selectItem(){
		   try{ 
		System.out.println(OR.getProperty(object));
		List<WebElement> Objoptionlist=driver.findElement(By.xpath(OR.getProperty(object))).findElements(By.tagName("li"));
		System.out.println(Objoptionlist.size());
		for(int i=1;i<=Objoptionlist.size();i++){
			String Actualval=driver.findElement(By.xpath(OR.getProperty(object)+"/li["+i+"]/a")).getText();
			System.out.println(Actualval);
			if (Actualval.equalsIgnoreCase(testdata.getCellData(currentTest,data_column_name, 2))){
			           driver.findElement(By.xpath(OR.getProperty(object)+"/li["+i+"]/a")).click();
				break;
			}
		}
		   return "Pass";
	   }catch(Exception e){
		   return "Fail"; 
	   }
	 }
	   
	   public static String selectajaxItem(){
		   try{ 
			  
		List<WebElement> Objoptionlist=driver.findElement(By.xpath(OR.getProperty(object))).findElements(By.tagName("li"));
		System.out.println(Objoptionlist.size());
		for(int i=1;i<=Objoptionlist.size();i++){
			String Actualval=driver.findElement(By.xpath(OR.getProperty(object)+"/li["+i+"]/a")).getText();
			if (Actualval.equalsIgnoreCase(testdata.getCellData(currentTest,data_column_name, 2))){
				 Actions a=new Actions(driver);
				a.moveToElement(driver.findElement(By.xpath(OR.getProperty(object)+"/li["+i+"]/a"))).click().build().perform();
			  
				break;
			}
		}
		   return "Pass";
	   }catch(Exception e){
		   return "Fail"; 
	   }
	 }
	   
	   
	   public static String selectItemfromList(){
		   try{ 
		System.out.println(OR.getProperty(object));
		System.out.println(testdata.getCellData(currentTest,data_column_name, 2));
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(testdata.getCellData(currentTest,data_column_name, 2).trim());
		return "Pass";
	   }catch(Exception e){
		   return "Fail"; 
	   }
	 }	
	   
	   public static String selectItembyOptiontag(){
		   try{ 
			
			   
		List<WebElement> itemscount=driver.findElement(By.xpath(OR.getProperty(object))).findElements(By.tagName("option"));
		for(int i=1;i<=itemscount.size();i++){
			String Actualval=driver.findElement(By.xpath(OR.getProperty(object)+"/option["+i+"]")).getAttribute("text");
			if(Actualval.equalsIgnoreCase(testdata.getCellData(currentTest,data_column_name, 2))){
				driver.findElement(By.xpath(OR.getProperty(object)+"/option["+i+"]")).click();
			  break;	
			}
		}
		return "Pass";
	   }catch(Exception e){
		   return "Fail"; 
	   }
	 }
	   
public static String close(){
		   try{
			   driver.close();
			   logs.debug("closed the browser -"+ object );
			   return "Pass";
		   }catch(Exception e){
			   StringWriter stack = new StringWriter();
			   e.printStackTrace(new PrintWriter(stack));
			   e.printStackTrace();
			   System.out.println(stack.toString());
			   logs.debug("Error while closing the browser -"+ object + stack.toString());
			   return "Fail"; 
		   }
		   
	   }

public static String quit(){
	   try{
		   driver.quit();
		   logs.debug("closed the browser -"+ object );
		   return "Pass";
	   }catch(Exception e){
		   StringWriter stack = new StringWriter();
		   e.printStackTrace(new PrintWriter(stack));
		   e.printStackTrace();
		   System.out.println(stack.toString());
		   logs.debug("Error while closing the browser -"+ object + stack.toString());
		   return "Fail"; 
	   }
	   
}

public static String maximize(){
	   try{
		   driver.manage().window().maximize();
		   logs.debug("Maximized the browser");
		   return "Pass";
	   }catch(Exception e){
		   StringWriter stack = new StringWriter();
		   e.printStackTrace(new PrintWriter(stack));
		   e.printStackTrace();
		   System.out.println(stack.toString());
		   logs.debug("Failed to maximize the browser");
		   return "Fail"; 
	   }
	   
}

public static String isElementpresent(){
	
	try{
	  int count= driver.findElements(By.xpath(OR.getProperty(object))).size(); 
	  if (count==0){
		  logs.debug("Error element does not present in the page"+ object);
		  return "Fail";
	  }else
		  logs.debug("element present in the page"+ object);
		  return "Pass";
 }catch(Exception e){
	 e.printStackTrace();
	 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
	 return "Fail";
 }
}
public static String navigateBack(){
	  try{
		  driver.navigate().back(); 
		  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  logs.debug("Navigated back to the previous page -"+ object);
		  return "Pass";
	  }catch(Exception e){
			 e.printStackTrace();
			 logs.debug("Error while navigating back to the previous page -"+ object + e.getMessage());
			 return "Fail";
		 }
	  
}

public static String sectionAvailableHomes(){
	
	 String actualUnit=null;
	    String[] Arr;
	    Arr=data_column_name.split(";");
	  try{
		  
	    WebElement homeSelection=driver.findElement(By.xpath(OR.getProperty(object)+"[starts-with(text(),'"+testdata.getCellData(currentTest,Arr[0], 2)+"')]/parent::section/parent::div/div[1]/table/tbody"));
		List<WebElement> rowList=homeSelection.findElements(By.tagName("tr"));
		if(rowList.size()>1){
			for(int i=1;i<=rowList.size();i++){
					 actualUnit=driver.findElement(By.xpath(OR.getProperty(object)+"[starts-with(text(),'"+testdata.getCellData(currentTest,Arr[0], 2)+"')]/parent::section/parent::div/div[1]/table/tbody/tr["+i+"]/td[2]")).getText();
					if(actualUnit.equalsIgnoreCase(testdata.getCellData(currentTest,Arr[1], 2))){
						flag=true;
						System.out.println(OR.getProperty(object)+"[starts-with(text(),'"+testdata.getCellData(currentTest,Arr[0],2)+"')]/parent::section/parent::div/div[1]/table/tbody/tr["+i+"]/td[8]");
						driver.findElement(By.xpath(OR.getProperty(object)+"[starts-with(text(),'"+testdata.getCellData(currentTest,Arr[0],2)+"')]/parent::section/parent::div/div[1]/table/tbody/tr["+i+"]/td[8]/button")).click();
						
						break;
						
					}
				}
		}else if(rowList.size()==1 && rowList.size()!=0){
				actualUnit=driver.findElement(By.xpath(OR.getProperty(object)+"[starts-with(text(),'"+testdata.getCellData(currentTest,Arr[0], 2)+"')]/parent::section/parent::div/div[1]/table/tbody/tr/td[2]")).getText();
					if(actualUnit.equalsIgnoreCase(testdata.getCellData(currentTest,Arr[1], 2))){
						flag=true;
						driver.findElement(By.xpath(OR.getProperty(object)+"[starts-with(text(),'"+testdata.getCellData(currentTest,Arr[0], 2)+"')]/parent::section/parent::div/div[1]/table/tbody/tr/td[8]/button")).click();
						
					}			
				} 
		if(flag==true){
			logs.debug("Clicked on select button of Expected unit -"+ testdata.getCellData(currentTest,Arr[1], 2));
			 return "Pass"; 
		}else{
			logs.debug("Expected unit not found on the page-"+ testdata.getCellData(currentTest,Arr[1], 2));
			 return "Fail"; 
		}
		
	  }catch(Exception e){
			 e.printStackTrace();
			 logs.debug("Error expected unit not found in the selection table of -"+ testdata.getCellData(currentTest,Arr[1], 2)+ e.getMessage());
			 return "Fail";
		 }
	   
}

public static String validateListItem(){
    int flag=0;
    String[] Arr;
    Arr=data_column_name.split(";");
   try{ 
	  List<WebElement> Elementlist= driver.findElement(By.xpath(OR.getProperty(object))).findElements(By.tagName(testdata.getCellData(currentTest, Arr[1], 2))); 
	  if (Elementlist.size()==0){
		  logs.debug("Error element does not contain any items"+ object);
		  return "Fail";
	  }else
		  for(int i=1;i<=Elementlist.size();i++){
			  if (driver.findElement(By.xpath(OR.getProperty(object)+"/"+testdata.getCellData(currentTest, Arr[1], 2)+"["+i+"]")).getText().trim().equals(testdata.getCellData(currentTest, Arr[0], 2))){
				  flag=1;
				  break;
			  }
		  }
		 if (flag!=0){
			 logs.debug("Item exist in the element"+ object);
			 return "Pass";
		 } else 
			 logs.debug("Error no such item"+testdata.getCellData(currentTest, Arr[0], 2)+" exist in the dropdown-"+ object);
			 return "Fail";
   }catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist-"+ object + e.getMessage());
		 return "Fail";
	 }
 }

public static String verifytext(){
	
	try{
		System.out.println(testdata.getCellData(currentTest,data_column_name, 2));
	  String Expected= driver.findElement(By.xpath(OR.getProperty(object))).getText(); 
	  if(!Expected.equalsIgnoreCase(testdata.getCellData(currentTest,data_column_name, 2))){
		  logs.debug("Error text does not exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
		  return "Fail";
	  }else
		  logs.debug("text exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
		  return "Pass";
 }catch(Exception e){
	 e.printStackTrace();
	 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
	 return "Fail";
 }
}

public static String randomMailId(){
	
	try{
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(testdata.getCellData(currentTest,data_column_name, 2)+System.currentTimeMillis()+"@gmail.com");
		logs.debug("Enter test in the  email textbox -"+testdata.getCellData(currentTest,data_column_name, 2)+System.currentTimeMillis()+"@gmail.com");
		 return "Pass";
	 }catch(Exception e){
	 e.printStackTrace();
	 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
	 return "Fail";
 }
}

public static String Randomtext(){
	
	try{
		long rndnum=System.currentTimeMillis();
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(testdata.getCellData(currentTest,data_column_name, 2)+rndnum);
		logs.debug("Enter test in the textbox -"+testdata.getCellData(currentTest,data_column_name, 2)+rndnum);
		 return "Pass";
	 }catch(Exception e){
	 e.printStackTrace();
	 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
	 return "Fail";
 }
}
// ********Modified By GopiManohar

public static String checkinnerValue(){
	   try{
		   System.out.println(data_column_name);
		   System.out.println(data_column_name.replaceAll("#",""));
		   String innertext=driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("value");
			  if(Odic.get(data_column_name.replaceAll("#","").trim()).equalsIgnoreCase(innertext)){
				  logs.debug("text"+ data_column_name.replaceAll("#","").trim()+" exist in the page");
				  return "Pass"; 
			  }	else{
				  logs.debug("text"+ Odic.get(data_column_name.replaceAll("#","").trim())+" not exist in the page");
					  return "Fail"; 
			   }	      
	   }catch(Exception e){
		   e.printStackTrace();
		   logs.debug("Element not exist -"+ OR.getProperty(object) + e.getMessage());
		   return "Fail"; 
	   }
}


public static  String selectValueByText(){
	String StrText=testdata.getCellData(currentTest,data_column_name, 2);
	try{
		Select sSelect = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
		sSelect.selectByVisibleText(StrText);
		logs.debug("Enter the element status - Element is  selected as "+testdata.getCellData(currentTest,data_column_name, 2));
		return "Pass";
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}

public static  String selectByValue(){
	try{
		Select sSelect = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
		sSelect.selectByValue(testdata.getCellData(currentTest,data_column_name, 2));
		logs.debug("Enter the element status - Element is  selected as "+testdata.getCellData(currentTest,data_column_name, 2));
		return "Pass";
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}




public static  String switchToAlert(){
	try{
		System.out.println("Switch to Alert");
		driver.switchTo().alert().getText();
		System.out.println(driver.switchTo().alert().getText());
		
		logs.debug("Enter the element status - Element is  Activated");
		return "Pass";
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}


public static  String activateElement(){
	try{
		//Thread.sleep(1000);
		driver.switchTo().activeElement().findElement(By.xpath(OR.getProperty(object))).click();
		logs.debug("Enter the element status - Element is  Activated");
		return "Pass";
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}

public static  String activateElementBySendkeys(){
	try{
		//Thread.sleep(1000);
		driver.switchTo().activeElement().findElement(By.xpath(OR.getProperty(object))).sendKeys(testdata.getCellData(currentTest,data_column_name, 2));
		logs.debug("Enter the element status - Element is  Activated");
		return "Pass";
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}

public static  String activateElementByclear(){
	try{
		//Thread.sleep(1000);
		driver.switchTo().activeElement().findElement(By.xpath(OR.getProperty(object))).clear();
		logs.debug("Enter the element status - Element is  Activated");
		return "Pass";
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}

public static String switchToAlertDoAccept(){
	try{
		driver.switchTo().alert().accept();
		logs.debug("Enter the element status - Alerts Accepted");
		return "Pass";
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}

public static String scleartext(){
	try{
		driver.findElement(By.xpath(OR.getProperty(object))).clear();;
		logs.debug("Enter the element status - Data Cleared");
		return "Pass";
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}

public static String isenabled(){
	try{
		driver.findElement(By.xpath(OR.getProperty(object))).isEnabled();
		logs.debug("Enter the element status -"+driver.findElement(By.xpath(OR.getProperty(object))).isEnabled());
		return "Pass";
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}

public static String capturecurrentwindow(){
	   try{
		   System.out.println(data_column_name);
		   String winHandleBefore = driver.getWindowHandle();
		   Odic.put(data_column_name.replaceAll("#","").trim(), winHandleBefore);
		   logs.debug("Captured the window Name as -"+ winHandleBefore);
		   return "Pass"; 
	   }catch(Exception e){
		   e.printStackTrace();
		   logs.debug("Error on Capturing  the windowname -" + e.getMessage());
		   return "Fail"; 
	   }
}


public static String newwindow(){
	//driver.switchTo().defaultContent();
	System.out.println(driver.getWindowHandles().size());
	  WebDriver driver2 =null;
	  String Window=null;
	   try{
		     Thread.sleep(30000);
	          Set<String> str1=driver.getWindowHandles();
	          System.out.println(driver.getWindowHandles().size());
	         Iterator<String> i = str1.iterator();
	          while (i.hasNext()){
	         	Window=i.next();
	          }
	          driver2=driver.switchTo().window(Window);
	          //driver = driver2;
	         driver2.findElement(By.xpath("//a[text()='Conventional Model - Mod. Thresholds']")).click();
	         driver = driver2;
		   logs.debug("Switched to the new window-");
		   return "Pass"; 
	   }catch(Exception e){
		   e.printStackTrace();
		   logs.debug("Error on Switching to the windows -"+ e.getMessage());
		   return "Fail"; 
	   }
}



public static String switchtowindow(){
	   try{
		  
		Thread.sleep(10000);
		   for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			   
			}
		   System.out.println(driver.getTitle());
		   logs.debug("Switched to the new window-"+driver.getTitle());
		   return "Pass"; 
	   }catch(Exception e){
		   e.printStackTrace();
		   logs.debug("Error on Switching to the windows -"+ e.getMessage());
		   return "Fail"; 
	   }
}


public static String switchtodefaultwindow(){
	   try{
		   System.out.println(data_column_name);
		   if (data_column_name!=""){
			   driver.switchTo().window(Odic.get(data_column_name));   
		   }else{
			   driver.switchTo().defaultContent();   
		   }
		  // driver.switchTo().window(Odic.get(data_column_name));
		   logs.debug("Switched to defaulte window-"+ Odic.get(data_column_name));
		   return "Pass"; 
	   }catch(Exception e){
		   e.printStackTrace();
		   logs.debug("Error on Switching to default window -"+ Odic.get(data_column_name) + e.getMessage());
		   return "Fail"; 
	   }
}
	   
public static String isrefreshed(){
	try{
		//driver.get(driver.getCurrentUrl());
		driver.navigate().refresh();
		logs.debug("Driver Got refreshed");
		return "Pass";
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}

/*public static String keyActions(){
	try{
		
		switch (data_column_name.substring(1, data_column_name.length()-1).toLowerCase()){
		 
		case "enter":
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Keys.ENTER);
			logs.debug("Key action "+data_column_name+" is performed");
			return "Pass";
		default:
			logs.debug("No such key found");
			return "Fail";
		}
	}catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
	
	}	
}*/

public static String dblclick(){
	   try{ 
	  Actions a=new Actions(driver);
	   WebElement e=driver.findElement(By.xpath(OR.getProperty(object)));
	  // a.moveToElement(e,e.getLocation().getX(),e.getLocation().getY()).doubleClick().build().perform();
	   a.moveToElement(e).doubleClick().build().perform();
	   
	   logs.debug("clicked on link -"+ object);
	   return "Pass";
}catch(Exception e){
	   StringWriter stack = new StringWriter();
	   e.printStackTrace(new PrintWriter(stack));
	   e.printStackTrace();
	   System.out.println(stack.toString());
	  logs.debug("Error while clicking on link -  "+ object + stack.toString());
	   return "Fail"; 
}
}	


public static String extendClick(){
	   try{ 
	  Actions a=new Actions(driver);
	   WebElement e=driver.findElement(By.xpath(OR.getProperty(object)));
	  // a.moveToElement(e,e.getLocation().getX(),e.getLocation().getY()).doubleClick().build().perform();
	   a.moveToElement(e).doubleClick().build().perform();
	   
	   logs.debug("clicked on link -"+ object);
	   return "Pass";
}catch(Exception e){
	   StringWriter stack = new StringWriter();
	   e.printStackTrace(new PrintWriter(stack));
	   e.printStackTrace();
	   System.out.println(stack.toString());
	  logs.debug("Error while clicking on link -  "+ object + stack.toString());
	   return "Fail"; 
}
}	


public static String waittillobjectExistance(){
	
	try{
		System.out.println(OR.getProperty(object));
	 while(true){
		 int count= driver.findElements(By.xpath(OR.getProperty(object))).size();
	     if(count!=0 || counter==100){
	     if(count!=0){
	    	 flag=true;
	     }
		 break;
	  }	
	     Thread.sleep(1000);
	     counter=counter+1;
	 }
	 if(flag==true){
    	 logs.debug("Object exist in the page"+ OR.getProperty(object));
    	 return "Pass";
     }else{
    	 logs.debug("Object not exist in the page"+ OR.getProperty(object));
    	 return "Fail";
     }
	 }catch(Exception e){
	 e.printStackTrace();
	 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
	 return "Fail";
 }
}

public static String clickElementByExistance(){
   try{
	   System.out.println(Integer.parseInt(data_column_name));
	WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(data_column_name));
	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	element.click();
	return "Pass";
   }catch(Exception e){
	 return "Fail"; 
   }
}



public static String waitTillVisibilityofElement(){
	   try{
		 
	   
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(data_column_name));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
		return "Pass";
	   }catch(Exception e){
		   return "Fail";
	   }
	
	}
public static String fluentWaitVisibilityOfElementLocated(){
	   try{
		 
		   FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Integer.parseInt(data_column_name), TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
		return "Pass";
	   }catch(Exception e){
		   return "Fail";
	   }
	
	}



public static String waitFrameToLoadAndSwitchToIt(){
	   try{
		 
	   
		WebDriverWait wait = new WebDriverWait(driver,180);
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(data_column_name));
		return "Pass";
	   }catch(Exception e){
		   return "Fail";
	   }
	
	}

public static String moveToElementAndClick(){
	   try{
		 
		   Actions a=new Actions(driver);
		   WebElement e=driver.findElement(By.xpath(OR.getProperty(object)));
		   a.moveToElement(e).click(e).build().perform();
		return "Pass";
	   }catch(Exception e){
		   return "Fail";
	   }
	
	}

public static String mouseHover(){
	   try{
		 
		   Actions a=new Actions(driver);
		   WebElement e=driver.findElement(By.xpath(OR.getProperty(object)));
		   a.moveToElement(e).build().perform();
		return "Pass";
	   }catch(Exception e){
		   return "Fail";
	   }
	
	}
public static String javaScriptExecutorToEnterText(){
	   try{
		   //String sObjectValue = OR.getProperty(object);
		   System.out.println("document.getElementById('"+OR.getProperty(object)+"').value = '"+testdata.getCellData(currentTest,data_column_name, 2)+"';");
		   JavascriptExecutor jse = (JavascriptExecutor) driver;
		   jse.executeScript("document.getElementById('"+OR.getProperty(object)+"').value = '"+testdata.getCellData(currentTest,data_column_name, 2)+"';");
		return "Pass";
	   }catch(Exception e){
		   return "Fail";
	   }
	
	}

public static String javaScriptExecutorToClick(){
	   WebDriver newdriver=driver;
	   try{
		   //String sObjectValue = OR.getProperty(object);
		System.out.println("murali");
		   JavascriptExecutor jse = (JavascriptExecutor) driver;
		   jse.executeScript("document.getElementById('"+OR.getProperty(object)+"').click();");
		   driver=newdriver;
		return "Pass";
	   }catch(Exception e){
		   return "Fail";
	   }
	
	}

public static String containstext(){
	
	try{
		System.out.println(testdata.getCellData(currentTest,data_column_name, 2));
		System.out.println(OR.getProperty(object));
	  String Expected= driver.findElement(By.xpath(OR.getProperty(object))).getText(); 
	  if(Expected.contains("$")){
		 Expected= Expected.substring(1,(Expected.length()-3));
		 System.out.println(Expected);
		 Expected=Expected.replaceAll(",","");
		 System.out.println(Expected);
	  }
	  if(!Expected.contains(testdata.getCellData(currentTest,data_column_name, 2))){
		  logs.debug("Error text does not exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
		  return "Fail";
	  }else
		  logs.debug("text exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
		  return "Pass";
 }catch(Exception e){
	 e.printStackTrace();
	 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
	 return "Fail";
 }
}


public static String verifytablecelldata(){
	flag=false;
	try{
		System.out.println(testdata.getCellData(currentTest,data_column_name, 2));
		List<WebElement> rows=driver.findElement(By.xpath(OR.getProperty(object))).findElements(By.tagName("tr"));
		for (int i = 1; i <=rows.size(); i++) {
			List<WebElement> cols=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]")).findElements(By.tagName("td"));
			for (int j = 1; j <=cols.size(); j++) {
				String Actualvalue=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td["+j+"]")).getText();
				
				if (Actualvalue.equals(testdata.getCellData(currentTest,data_column_name, 2))){
					flag=true;
					  break;	 
				}
			}
			if(flag==true){
				break;
			}
		}	
		if (flag==true){
			logs.debug("text exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
			  return "Pass";
		}
			  else{
				  logs.debug("text not exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
				  return "Fail"; 
			  }
	
	 }catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
     }
}
	
public static String getattribute(){

try{

System.out.println(data_column_name);

System.out.println(data_column_name.replaceAll("#","").trim());

String stext=driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("value");

if (stext.length()>0){

Odic.put(data_column_name.replaceAll("#","").trim(), stext);

logs.debug("Stored text "+ stext);

return "Pass"; 

}else{

logs.debug("does not contain text for the object "+OR.getProperty(object));

return "Fail"; 

} 

}catch(Exception e){

e.printStackTrace();

logs.debug("Element not exist -"+ OR.getProperty(object) + e.getMessage());

return "Fail"; 

}

}

public static String ExportedSetData(){

try{

String sExportedData = Odic.get(data_column_name);
System.out.println(sExportedData);

if (sExportedData !=""){

driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(sExportedData);

logs.debug("Enterd the Exported text as "+ sExportedData);

return "Pass"; 

}else{

logs.debug("does not contain text for the object "+Odic.get(data_column_name));

return "Fail"; 

} 

}catch(Exception e){

e.printStackTrace();

logs.debug("Element not exist -"+ OR.getProperty(object) + e.getMessage());

return "Fail"; 

}

}

public static String selectajaxItemdropdown(){
    try{ 
          
 List<WebElement> Objoptionlist=driver.findElement(By.xpath(OR.getProperty(object))).findElements(By.tagName("li"));
 for(int i=1;i<=Objoptionlist.size();i++){
        String Actualval=driver.findElement(By.xpath(OR.getProperty(object)+"/li["+i+"]/a")).getText();
        String Excelvalue = testdata.getCellData(currentTest,data_column_name, 2); 
        if (Excelvalue == ""){
               if (Actualval.equalsIgnoreCase(Odic.get(data_column_name))){
                     Actions a=new Actions(driver);
                      a.moveToElement(driver.findElement(By.xpath(OR.getProperty(object)+"/li["+i+"]/a"))).click().build().perform();
                     break;
               }             
        }
        else {
               if (Actualval.equalsIgnoreCase(testdata.getCellData(currentTest,data_column_name, 2))){
                     Actions a=new Actions(driver);
                      a.moveToElement(driver.findElement(By.xpath(OR.getProperty(object)+"/li["+i+"]/a"))).click().build().perform();
                 
                     break;
               }      
        }
 }
 logs.debug("AjaxItem is selected "+ Odic.get(data_column_name));
return "Pass"; 
    
}catch(Exception e){
 e.printStackTrace();
    logs.debug("Element not exist -"+ OR.getProperty(object) + e.getMessage());
    return "Fail"; 
}
}


public static String switchToframebyName(){
	   try{
		  /* int i;
		   int flag=0;
		  List<WebElement> elements=driver.findElements(By.tagName("iframe"));
		  for(i=0;i<=elements.size()-1;i++){
			  System.out.println(elements.get(i).getAttribute("name"));
			  if(elements.get(i).getAttribute("name").equalsIgnoreCase(data_column_name)){
				  flag=1;
				  break;
			  }
		  }
		  if(flag==1){*/
			  driver.switchTo().frame(data_column_name);
			  logs.debug("Switched to the frame-"+data_column_name);
			   return "Pass";
		  /*}else{
			  logs.debug("No Such frame found-"+data_column_name);
			   return "Fail";
		  }*/
		 
		    
	   }catch(Exception e){
		   e.printStackTrace();
		   logs.debug("Error on Switching to the frame -"+data_column_name + e.getMessage());
		   return "Fail"; 
	   }
	   
	 
}

public static String newwindow1(){
	int flag=0;
	WebDriver newdriver=null;
	   try{ 
	   Set<String> newwindows=driver.getWindowHandles();
	   System.out.println(newwindows.size());
	   if(newwindows.size()==1 && newwindows.size()!=0 ){
		  newdriver= driver.switchTo().window(newwindows.iterator().next());
		  System.out.println(newdriver.getTitle());
		   driver=newdriver;
		   logs.debug("switched to new window -  "+ driver.getTitle());
		   return "Pass";
	   }else{
		   Iterator<String> i=newwindows.iterator();
		   while(i.hasNext()){
		   	String it=i.next();
		   	System.out.println(driver.switchTo().window(it).getTitle());
		   if(driver.switchTo().window(it).getTitle().contains(data_column_name)){
			   newdriver= driver.switchTo().window(it);
			   driver=newdriver;
			   flag=1;
		   	break;
		   }
		   }
		   if(flag==1){
			   logs.debug("switched to new window -  "+ newdriver.getTitle());
			   return "Pass";
		   }else{
			   logs.debug("no such window exist");
			   return "Fail";   
			   
		   }
	   }
	  
}catch(Exception e){
	   StringWriter stack = new StringWriter();
	   e.printStackTrace(new PrintWriter(stack));
	   e.printStackTrace();
	   System.out.println(stack.toString());
	  logs.debug("Error while clicking on link -  "+ object + stack.toString());
	   return "Fail"; 
}
}	 
public static String switchToDefaultContent(){
	try{
		driver.switchTo().defaultContent();
		 logs.debug("switched to default window");
		return "Pass";
	}catch(Exception e){
		 StringWriter stack = new StringWriter();
		   e.printStackTrace(new PrintWriter(stack));
		   e.printStackTrace();
		   System.out.println(stack.toString());
		  logs.debug("window not exist -  "+ object + stack.toString());
		return "Fail";
	}
	   
   }

public static String selectRandomContractFromTable(){
	int i;
	StringWriter stack = new StringWriter();
	Random r=new Random();
	i=r.nextInt(10)+1;
	try{
	  driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[4]/a")).click();
	  logs.debug("Selected contract randomly");
	return "Pass";
	}catch(Exception e){
		   e.printStackTrace(new PrintWriter(stack));
		  logs.debug("Failed in contract selection randomly");
		  return "Fail";
	}
}

public static String selectContactFromTable(){
	int i,rc;
	boolean flag=false;
	String Actualval=null;
	StringWriter stack = new StringWriter();
	try{
		rc=driver.findElements(By.xpath(OR.getProperty(object))).size();
		for(i=1;i<=rc;i++){
			Actualval=driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[1]/a")).getText();
			if(Actualval.equals(testdata.getCellData(currentTest,data_column_name, 2))){
				flag=true;
				break;
			}
		}
			if(flag==true){
				driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[5]/ul/li/a")).click();
				logs.debug("Selected contact successfully");
				return "Pass";
			}else{
				logs.debug("contact not found in the contacts table");
				return "Fail";
			}
	}catch(Exception e){
		   e.printStackTrace(new PrintWriter(stack));
		  logs.debug("Failed in contact selection randomly");
		  return "Fail";
	}
}

public static String editSpecialsFromTable(){
	int i,rc;
	boolean flag=false;
	String Actualval=null;
	StringWriter stack = new StringWriter();
	try{
		rc=driver.findElements(By.xpath(OR.getProperty(object))).size();
		for(i=1;i<=rc;i++){
			Actualval=driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[1]")).getText();
			if(Actualval.equals(testdata.getCellData(currentTest,data_column_name, 2))){
				flag=true;
				break;
			}
		}
			if(flag==true){
				driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[6]/span[1]/a")).click();
				logs.debug("clicked on edit successfully");
				return "Pass";
			}else{
				logs.debug("Added special is not found in the specials table");
				return "Fail";
			}
	}catch(Exception e){
		   e.printStackTrace(new PrintWriter(stack));
		  logs.debug("No such element found on the page");
		  return "Fail";
	}
}

public static String deleteSpecialsFromTable(){
	int i,rc;
	boolean flag=false;
	String Actualval=null;
	StringWriter stack = new StringWriter();
	try{
		rc=driver.findElements(By.xpath(OR.getProperty(object))).size();
		for(i=1;i<=rc;i++){
			Actualval=driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[1]")).getText();
			if(Actualval.equals(testdata.getCellData(currentTest,data_column_name, 2))){
				flag=true;
				break;
			}
		}
			if(flag==true){
				driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[6]/span[2]/a")).click();
				logs.debug("clicked on delete successfully");
				return "Pass";
			}else{
				logs.debug("Added special is not found in the specials table");
				return "Fail";
			}
	}catch(Exception e){
		   e.printStackTrace(new PrintWriter(stack));
		  logs.debug("No such element found on the page");
		  return "Fail";
	}
}


public static String selectRandomContactFromTable(){
	int i;
	StringWriter stack = new StringWriter();
	Random r=new Random();
	i=r.nextInt(10)+1;
	try{
	  driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[5]/ul/li/a")).click();
	  logs.debug("Selected contact randomly");
	return "Pass";
	}catch(Exception e){
		   e.printStackTrace(new PrintWriter(stack));
		  logs.debug("Failed in contact selection randomly");
		  return "Fail";
	}
}

public static String selectRandompropertyFromTable(){
	int i;
	StringWriter stack = new StringWriter();
	Random r=new Random();
	i=r.nextInt(10)+1;
	try{
	  driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[1]/a")).click();
	  logs.debug("Selected Property randomly");
	return "Pass";
	}catch(Exception e){
		   e.printStackTrace(new PrintWriter(stack));
		  logs.debug("Failed in property selection randomly");
		  return "Fail";
	}
}

public static String uploadFile(){
	StringWriter stack = new StringWriter();
	System.out.println(testdata.getCellData(currentTest,data_column_name, 2));
	try{
		ProcessBuilder p=new ProcessBuilder(System.getProperty("user.dir")+"\\Resources\\temp.exe",testdata.getCellData(currentTest,data_column_name, 2),"Open");
		p.start();
	  logs.debug("Uploaded selected file");
	return "Pass";
	}catch(Exception e){
		   e.printStackTrace(new PrintWriter(stack));
		  logs.debug("Failed to upload file");
		  return "Fail";
	}
}

public static String verifyAmenitiesDataTable(){
	flag=false;
	try{
		System.out.println(testdata.getCellData(currentTest,data_column_name, 2));
		List<WebElement> rows=driver.findElement(By.xpath(OR.getProperty(object))).findElements(By.tagName("tr"));
		for (int i = 2; i <=rows.size(); i++) {
			List<WebElement> cols=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]")).findElements(By.tagName("td"));
			for (int j = 1; j <=cols.size(); j++) {
				String Actualvalue=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td["+j+"]")).getText();
				
				if (Actualvalue.equals(testdata.getCellData(currentTest,data_column_name, 2))){
					flag=true;
					  break;	 
				}
			}
			if(flag==true){
				break;
			}
		}	
		if (flag==true){
			logs.debug("text exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
			  return "Pass";
		}
			  else{
				  logs.debug("text not exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
				  return "Fail"; 
			  }
	
	 }catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
     }
}

/*public static String verifyLeadDataTable(){
	flag=false;
	try{
		
		List<WebElement> rows=driver.findElement(By.xpath(OR.getProperty(object))).findElements(By.tagName("tr"));
		for (int i =1; i <=rows.size(); i++) {
			
				String ActualvaluePID=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td[4]/a")).getText();
				String ActualvaluePID=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td[4]/a")).getText();
				String ActualvaluePID=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td[4]/a")).getText();
				String ActualvaluePID=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td[4]/a")).getText();
				
				if (Actualvalue.equals(testdata.getCellData(currentTest,data_column_name, 2))){
					flag=true;
					  break;	 
				}
			}
			if(flag==true){
				break;
			}
		}	
		if (flag==true){
			logs.debug("text exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
			  return "Pass";
		}
			  else{
				  logs.debug("text not exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
				  return "Fail"; 
			  }
	
	 }catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
     }
}
*/
public static String selectFloorPlanFromDataTable(){
	flag=false;
	try{
		System.out.println(Odic.get(data_column_name));
		List<WebElement> rows=driver.findElement(By.xpath(OR.getProperty(object))).findElements(By.tagName("tr"));
		for (int i = 2; i <=rows.size(); i++) {
				String Actualvalue=driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td[3]/a")).getText();
				if (Actualvalue.equals(Odic.get(data_column_name))){
					driver.findElement(By.xpath(OR.getProperty(object)+"/tr["+i+"]/td[3]/a")).click();
					flag=true;
					  break;	 
				}
			}	

		if (flag==true){
			logs.debug("text exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
			  return "Pass";
		}
			  else{
				  logs.debug("text not exist in the page"+ testdata.getCellData(currentTest,data_column_name, 2));
				  return "Fail"; 
			  }
	
	 }catch(Exception e){
		 e.printStackTrace();
		 logs.debug("Error no such element exist in the page -"+ object + e.getMessage());
		 return "Fail";
     }
}

public static String editFloorFromTable(){
	int i,rc;
	boolean flag=false;
	String Actualval=null;
	String Attributeval=null;
	StringWriter stack = new StringWriter();
	try{
		rc=driver.findElements(By.xpath(OR.getProperty(object))).size();
		for(i=1;i<=rc;i++){
			Attributeval=driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]")).getAttribute("class");
			if(!(Attributeval.contains("more-info-floorplan") ||Attributeval.contains("total"))){
				Actualval=driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[2]")).getText();
				if(Actualval.equals(testdata.getCellData(currentTest,data_column_name, 2))){
					flag=true;
					break;
				}
				
			}
			
		}
			if(flag==true){
				driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[8]/span[1]/a")).click();
				logs.debug("clicked on edit successfully");
				return "Pass";
			}else{
				logs.debug("Added special is not found in the specials table");
				return "Fail";
			}
	}catch(Exception e){
		   e.printStackTrace(new PrintWriter(stack));
		  logs.debug("No such element found on the page");
		  return "Fail";
	}
}


public static String deleteFloorFromTable(){
	int i,rc;
	boolean flag=false;
	String Actualval=null;
	String Attributeval=null;
	StringWriter stack = new StringWriter();
	try{
		rc=driver.findElements(By.xpath(OR.getProperty(object))).size();
		for(i=1;i<=rc;i++){
			Attributeval=driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]")).getAttribute("class");
			if(!(Attributeval.contains("more-info-floorplan") ||Attributeval.contains("total"))){
				Actualval=driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[2]")).getText();
				if(Actualval.equals(testdata.getCellData(currentTest,data_column_name, 2))){
					flag=true;
					break;
				}
				
			}
			
		}
			if(flag==true){
				driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[8]/span[2]/a")).click();
				logs.debug("clicked on delete successfully");
				return "Pass";
			}else{
				logs.debug("Added special is not found in the specials table");
				return "Fail";
			}
	}catch(Exception e){
		   e.printStackTrace(new PrintWriter(stack));
		  logs.debug("No such element found on the page");
		  return "Fail";
	}
}


public static String deleteAmenityFromTable(){
	int i,rc;
	boolean flag=false;
	String Actualval=null;
	StringWriter stack = new StringWriter();
	try{
		rc=driver.findElements(By.xpath(OR.getProperty(object))).size();
		for(i=1;i<=rc;i++){
			Actualval=driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[3]/input")).getAttribute("value");
			if(Actualval.equals(testdata.getCellData(currentTest,data_column_name, 2))){
				flag=true;
				break;
			}
		}
			if(flag==true){
				driver.findElement(By.xpath(OR.getProperty(object)+"["+i+"]/td[7]/a")).click();
				logs.debug("clicked on delete successfully");
				return "Pass";
			}else{
				logs.debug("Added special is not found in the specials table");
				return "Fail";
			}
	}catch(Exception e){
		   e.printStackTrace(new PrintWriter(stack));
		  logs.debug("No such element found on the page");
		  return "Fail";
	}
}



}




