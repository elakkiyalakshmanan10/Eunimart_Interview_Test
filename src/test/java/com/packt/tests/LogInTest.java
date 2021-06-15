package com.packt.tests;

/*import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LogInTest {
	@Test
	public void logInTest() {
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		System.out.println("Page opened");
		driver.quit();
	}

}
*/

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Set;
import java.util.concurrent.TimeUnit;
 
public class LogInTest {
    
	//System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	boolean status = false;
    
    //Opening browser with the given URL and navigate to Registration Page
    @BeforeMethod
    public void openBrowser()
    {
    	
    	driver.manage().deleteAllCookies();
    	
		driver.get("https://go-testing-v2.vdezi.com/");
        
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        
        WebElement signUpButton = driver.findElement(By.name("Sign Up"));
        signUpButton.click(); 
        
    }
    
    //Verifying elements on Registration page
    @Test
    public void verifyElemntsOnPageTest()
    {
    	
    	WebElement signInButton = driver.findElement(By.name("Sign In"));
    	signInButton.isDisplayed();
    	
    	WebElement signUpButton = driver.findElement(By.name("Sign Up"));
    	signUpButton.isDisplayed();
    	
    	WebElement termsText = driver.findElement(By.id("vdezi-login-register-1-input"));
    	termsText.isDisplayed();
    	
    }
    
    //Verifying redirection to the terms and conditions page
    @Test
    public void termsRedirectionTest()
    {
    	String expectedURL = "https://go-testing-v2.vdezi.com/signup";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
        
    	WebElement termsLink = driver.findElement(By.xpath("//a[contains(text(),'Terms')]"));
    	termsLink.click();    	
    	
    	Set <String> allWindows = driver.getWindowHandles();
    	
    	for(String handle : allWindows)
    	{
    		driver.switchTo().window(handle);
    	}    	        	
    	
    	String expectedTitle = " Terms & Conditions ";
    	String actualTitle = driver.getTitle();
    	//System.out.println(actualTitle);
    	Assert.assertEquals(actualTitle, expectedTitle); 
    	
    	WebElement closeButton = driver.findElement(By.className("mat-button-wrapper"));
    	closeButton.click();    	
    }
    
    
  //Verifying redirection to the Privacy Policy
    @Test
    public void privacyPolicy()
    {
    	String expectedURL = "https://go-testing-v2.vdezi.com/signup";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    	
    	WebElement privacyLink = driver.findElement(By.xpath("//a[contains(text(),'Privacy')]"));
    	privacyLink.click();    	
    	
    	Set <String> allWindows = driver.getWindowHandles();
    	
    	for(String handle : allWindows)
    	{
    		driver.switchTo().window(handle);
    	}    	        	
    	
    	String expectedTitle = " Privacy Policy ";
    	String actualTitle = driver.getTitle();
    	//System.out.println(actualTitle);
    	Assert.assertEquals(actualTitle, expectedTitle);  
    	
    	WebElement closeButton = driver.findElement(By.className("mat-button-wrapper"));
    	closeButton.click();
    }

  
    // Sign Up with all valid data
    @Test
    public void validRegistrationTest(){            
             
    	String expectedURL = "https://go-testing-v2.vdezi.com/signup";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    	
    	WebElement email = driver.findElement(By.id("mat-input-43"));
        email.sendKeys("abc@gmail.com");
         
        WebElement password = driver.findElement(By.id("mat-input-44"));
        password.sendKeys("Test@12345");
 
        WebElement confirmPassword = driver.findElement(By.id("mat-input-45"));
        confirmPassword.sendKeys("Test@12345");
            
        WebElement radioRegisterButton = driver.findElement(By.id("vdezi-login-register-1-input"));
        radioRegisterButton.click();
              
        WebElement registerButton = driver.findElement(By.className(" register"));
        registerButton.click();
        
        String expectedreURL = "https://go-testing-v2.vdezi.com/emailVerify";
        String actualreURL = driver.getCurrentUrl();
        Assert.assertEquals(actualreURL, expectedreURL);
        
        String expectedTitle = "Verify Your Email To Continue";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);  
        System.out.println("Registration Successful!");
        
    }
    
    
    // Registration without providing Email field
    @Test
    public void emptyEmailTest()
    {
    	String expectedURL = "https://go-testing-v2.vdezi.com/signup";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    	
    	WebElement email = driver.findElement(By.id("mat-input-43"));
        email.sendKeys("");
         
        WebElement password = driver.findElement(By.id("mat-input-44"));
        password.sendKeys("Test@12345");
 
        WebElement confirmPassword = driver.findElement(By.id("mat-input-45"));
        confirmPassword.sendKeys("Test@12345");
            
        WebElement radioRegisterButton = driver.findElement(By.id("vdezi-login-register-1-input"));
        radioRegisterButton.click();
              
        Boolean registerEnabled = driver.findElement(By.className(" register")).isEnabled();
        if(registerEnabled==false){
        	System.out.println("Please fill mandatory fields");
        }
        
    }
    
    // Registration without providing password field
    @Test
    public void emptyPasswordTest()
    {
    	String expectedURL = "https://go-testing-v2.vdezi.com/signup";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    	
    	WebElement email = driver.findElement(By.id("mat-input-43"));
        email.sendKeys("abc@gmail.com");
         
        WebElement password = driver.findElement(By.id("mat-input-44"));
        password.sendKeys("");
 
        WebElement confirmPassword = driver.findElement(By.id("mat-input-45"));
        confirmPassword.sendKeys("Test@12345");
            
        WebElement radioRegisterButton = driver.findElement(By.id("vdezi-login-register-1-input"));
        radioRegisterButton.click();
              
        Boolean registerEnabled = driver.findElement(By.className(" register")).isEnabled();
        if(registerEnabled==false){
        	System.out.println("Please fill mandatory fields");
        }
    }
    
    
 // Registration without providing confirm password field
    @Test
    public void emptyConfirmPwdTest()
    {
    	String expectedURL = "https://go-testing-v2.vdezi.com/signup";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    	
    	WebElement email = driver.findElement(By.id("mat-input-43"));
        email.sendKeys("abc@gmail.com");
         
        WebElement password = driver.findElement(By.id("mat-input-44"));
        password.sendKeys("Test@12345");
 
        WebElement confirmPassword = driver.findElement(By.id("mat-input-45"));
        confirmPassword.sendKeys("");
            
        WebElement radioRegisterButton = driver.findElement(By.id("vdezi-login-register-1-input"));
        radioRegisterButton.click();
              
        Boolean registerEnabled = driver.findElement(By.className(" register")).isEnabled();
        if(registerEnabled==false){
        	System.out.println("Please fill mandatory fields");
        }
    }
    
    
 // Signing in after valid registration
    @Test
    public void signIn()
    {
    	WebElement signInButton = driver.findElement(By.name("Sign In"));
    	signInButton.click();
    	
    	String expectedURL = "https://go-testing-v2.vdezi.com/login";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    	
    	WebElement email = driver.findElement(By.id("mat-input-36"));
        email.sendKeys("abc@gmail.com");
         
        WebElement password = driver.findElement(By.id("mat-input-37"));
        password.sendKeys("Test@12345");
            
        //WebElement radioLoginButton = driver.findElement(By.id("vdezi-login-login-1-input"));
        //radioLoginButton.click();
        
        WebElement logInButton = driver.findElement(By.className("sign-in"));
        logInButton.click();
              
        String expectedreURL = "https://go-testing-v2.vdezi.com/ecommerce/dashboard";
        String actualreURL = driver.getCurrentUrl();
        Assert.assertEquals(actualreURL, expectedreURL);
        System.out.println("Login Successful!");
    }
    
    
    //Signing In without user email input
    @Test
    public void signInNoEmail()
    {
    	WebElement signInButton = driver.findElement(By.name("Sign In"));
    	signInButton.click();
    	
    	String expectedURL = "https://go-testing-v2.vdezi.com/login";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    	
    	WebElement email = driver.findElement(By.id("mat-input-36"));
        email.sendKeys("");
         
        WebElement password = driver.findElement(By.id("mat-input-37"));
        password.sendKeys("Test@12345");
        
        Boolean logInButton = driver.findElement(By.className("sign-in")).isEnabled();
        if(logInButton==false){
        	System.out.println("Please fill mandatory fields");
        }
    }
    
    
  //Signing In without password input
    @Test
    public void signInNoPassword()
    {
    	WebElement signInButton = driver.findElement(By.name("Sign In"));
    	signInButton.click();
    	
    	String expectedURL = "https://go-testing-v2.vdezi.com/login";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    	
    	WebElement email = driver.findElement(By.id("mat-input-36"));
        email.sendKeys("abc@gmail.com");
         
        WebElement password = driver.findElement(By.id("mat-input-37"));
        password.sendKeys("");
        
        Boolean logInButton = driver.findElement(By.className("sign-in")).isEnabled();
        if(logInButton==false){
        	System.out.println("Please fill mandatory fields");
        }
    }
    
        
    // Closing the browser session after completing each test case
    @AfterClass
    public void tearDown() throws Exception {
       if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}