package testPackageTestOperationCheck;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.TestNG;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.logging.Level;
import java.util.logging.Logger;

public class seleniumtest1 {

    private static final String PREFERRED_DATE = "04/15/2026";
    private static final String PREFERRED_TIME = "10:30 AM";
    private static final String PREFERRED_TIME_NATIVE = "10:30";

    public static void main(String[] args) {

        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { seleniumtest1.class });
        testng.run();
    }

    WebDriver driver;

    @BeforeClass
    public void setup() {

        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/clinic/log.php");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testPatientTestOperation() throws InterruptedException {
        Thread.sleep(2000);

        driver.findElement(By.name("username")).sendKeys("Anu");
        driver.findElement(By.name("email")).sendKeys("anu@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123456");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Thread.sleep(3000);

        driver.get("http://localhost/clinic/patient/testop.php");
        Thread.sleep(2000);

        WebElement patient_name = driver.findElement(By.name("patient_name"));
        patient_name.sendKeys("ariful");

        WebElement patient_email = driver.findElement(By.name("patient_email"));
        patient_email.sendKeys("ariful@gmail.com");

        fillIfPresent(
                PREFERRED_DATE,
                By.name("preferred_date"),
                By.id("preferred_date"),
                By.xpath("//input[@type='date']"),
                By.xpath("//label[contains(normalize-space(.), 'Preferred Date')]/following::input[1]")
        );

        fillIfPresent(
                PREFERRED_TIME,
                By.name("preferred_time"),
                By.id("preferred_time"),
                By.xpath("//input[@type='time']"),
                By.xpath("//label[contains(normalize-space(.), 'Preferred Time')]/following::input[1]")
        );

        driver.findElement(By.xpath("//input[@name='test_type[]' and @value='Lipid Profile']")).click();
        driver.findElement(By.xpath("//input[@name='test_type[]' and @value='CT-scan']")).click();

        driver.findElement(By.xpath("//button[text()='Submit Request'] | //input[@value='Submit Request']"))
                .click();

        Thread.sleep(5000);
    }

    private void fillIfPresent(String value, By... locators) {
        for (By locator : locators) {
            try {
                WebElement field = driver.findElement(locator);
                if (field.isDisplayed() && field.isEnabled()) {
                    field.clear();
                    if ("time".equalsIgnoreCase(field.getAttribute("type"))) {
                        field.sendKeys(PREFERRED_TIME_NATIVE);
                    } else if (isPreferredTimeField(field)) {
                        field.sendKeys(PREFERRED_TIME);
                    } else {
                        field.sendKeys(value);
                    }
                    return;
                }
            } catch (Exception ignored) {
            }
        }
    }

    private boolean isPreferredTimeField(WebElement field) {
        String name = field.getAttribute("name");
        String id = field.getAttribute("id");
        String placeholder = field.getAttribute("placeholder");

        return containsTime(name) || containsTime(id) || containsTime(placeholder);
    }

    private boolean containsTime(String value) {
        return value != null && value.toLowerCase().contains("time");
    }
}
