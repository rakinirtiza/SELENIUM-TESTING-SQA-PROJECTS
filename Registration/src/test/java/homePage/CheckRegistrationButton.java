package homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckRegistrationButton {

    WebDriver driver;
    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/clinic/");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void login() throws InterruptedException {
        Thread.sleep(1000);
        WebElement loginButton = driver.findElement(By.linkText("LogIn"));
        loginButton.click();
        Thread.sleep(10000);
        driver.findElement(By.linkText("Go Home")).click();
    }

    @Test(priority = 2)
    public void userRegistration() throws InterruptedException {

        WebElement registrationButton = driver.findElement(By.linkText("Sign Up"));
        registrationButton.click();

        driver.findElement(By.name("name")).sendKeys("Anuuu");

        driver.findElement(By.name("email")).sendKeys("anup@gmail.com");

        driver.findElement(By.name("password")).sendKeys("123456");

        driver.findElement(By.name("contact")).sendKeys("01634306997");

        driver.findElement(By.name("age")).sendKeys("24");

        Select gender = new Select(driver.findElement(By.name("gender")));
        gender.selectByVisibleText("Male");

        driver.findElement(By.name("blood")).sendKeys("B+");

        driver.findElement(By.name("dob")).sendKeys("2002-31-01");

        driver.findElement(By.name("address")).sendKeys("Dhaka, Bangladesh");

        driver.findElement(By.name("emergency")).sendKeys("01812345678");

        Thread.sleep(10000);

        driver.findElement(By.tagName("button")).click();

        Thread.sleep(10000);

        driver.findElement(By.linkText("Go Home")).click();

    }





}







