import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class AppOrderTest {

    private WebDriver driver;

    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldTestV1() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Иваныч");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+78005553535");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[type=button]")).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test-id=order-success]")).isDisplayed());
    }

    @Test
    public void shouldTestV2() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Gleb");
        driver.findElement(By.cssSelector("[type=button]")).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test-id=name].input_invalid")).isDisplayed());
    }

    @Test
    public void shouldTestV3() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Глеб");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("Gleb");
        driver.findElement(By.cssSelector("[type=button]")).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid")).isDisplayed());
    }

    @Test
    public void shouldTestV4() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Глеб");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+78005553535");
        driver.findElement(By.cssSelector("[type=button]")).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid")).isDisplayed());
    }
}
