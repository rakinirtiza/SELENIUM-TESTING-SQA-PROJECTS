package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstSeleniumTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();


        driver.get("http://localhost/clinic/log.php");
    }

    @Test
    public void testAdminUpdate() throws InterruptedException {

        Thread.sleep(2000);


        driver.findElement(By.name("username")).sendKeys("admin1");
        driver.findElement(By.name("email")).sendKeys("admin1@gmail.com");
        driver.findElement(By.name("password")).sendKeys("1234a1");

        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        Thread.sleep(3000);


        driver.get("http://localhost/clinic/admin/adsetting.php");

        Thread.sleep(2000);

        driver.findElement(By.name("admin_name")).clear();
        driver.findElement(By.name("admin_name")).sendKeys("admin_test");

        driver.findElement(By.name("admin_email")).clear();
        driver.findElement(By.name("admin_email")).sendKeys("test@gmail.com");

        driver.findElement(By.name("admin_pass")).clear();
        driver.findElement(By.name("admin_pass")).sendKeys("123456");

        driver.findElement(By.xpath("//button[contains(text(),'Update Profile')]")).click();

        Thread.sleep(2000);

        String page = driver.getPageSource();

        if (page.toLowerCase().contains("profile updated")) {
            System.out.println("TEST PASSED ");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}