package part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Doctor {
    WebDriver driver;
    @BeforeClass
    public void setup () {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/clinic/");
    }
    @AfterClass
    public void teardown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void testDoctorDashboardButtons() throws InterruptedException {

        WebElement loginButton = driver.findElement(By.linkText("LogIn"));
        loginButton.click();

        WebElement username = driver.findElement(By.name("username"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));

        username.sendKeys("p1");
        email.sendKeys("p1@gmail.com");
        password.sendKeys("1234p1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("View Doctors")).click();
        WebElement search = driver.findElement(By.name("search"));
        search.sendKeys("Faradiva Haque Tithi");
        driver.findElement(By.className("search-btn")).click();
        Thread.sleep(10000);
        driver.findElement(By.linkText("Dashboard")).click();


    }
    @Test(priority = 2)
    public void bookAppointment() throws InterruptedException {
        driver.findElement(
                By.xpath("//a[contains(@href,'docrequ')]")
        ).click();
        WebElement contactNumber = driver.findElement(By.name("patient_contact"));
        contactNumber.sendKeys("+01316575013");

        WebElement doctorDropdown = driver.findElement(By.name("doctor"));
        Select doctor = new Select(doctorDropdown);
        doctor.selectByVisibleText("Dr. Faradiva Haque Tithi - Neurology");

        driver.findElement(By.name("appointment_date"))
                .sendKeys("2026-04-10");


        driver.findElement(By.name("appointment_time"))
                .sendKeys("10:30");


        driver.findElement(By.name("reason"))
                .sendKeys("Severe headache and dizziness");

        Thread.sleep(1000);

        // Submit Appointment
        driver.findElement(By.tagName("button")).click();

        Thread.sleep(3000);





    }

}



