package part2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.time.Duration;

public class DoctorApprovalTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://localhost/clinic/log.php");
    }

    @Test
    public void testDoctorApproval() throws InterruptedException {

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                .sendKeys("admin1");

        Thread.sleep(1000);

        driver.findElement(By.name("email"))
                .sendKeys("admin1@gmail.com");

        Thread.sleep(1000);

        driver.findElement(By.name("password"))
                .sendKeys("1234a1");

        Thread.sleep(1500);

        driver.findElement(By.xpath("//input[@value='Log In']"))
                .click();

        Thread.sleep(3000);


        wait.until(ExpectedConditions.urlContains("admin"));

        driver.get("http://localhost/clinic/admin/doctorApp.php");

        Thread.sleep(3000);


        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(text(),'Review')])[1]")
        )).click();

        Thread.sleep(3000);


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("status")));

        Select statusDropdown = new Select(driver.findElement(By.name("status")));
        statusDropdown.selectByVisibleText("Approved");

        Thread.sleep(2000);


        driver.findElement(By.xpath("//button[contains(text(),'Update Application')]"))
                .click();

        Thread.sleep(3000);


        wait.until(ExpectedConditions.urlContains("doctorApp"));

        String page = driver.getPageSource().toLowerCase();

        if (page.contains("approved")) {
            System.out.println("TEST PASSED Doctor Approved Successfully");
        } else {
            throw new RuntimeException("TEST FAILED Approval not working");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}