package au.com.ncs.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PizzaTests {
    private WebDriver driver;
    private WebDriverWait wait;

    private String url = "https://d3ovkzfkbrwp1z.cloudfront.net/";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        //Init driver and maximize it.
        driver = getWebDriver();
        driver.manage().window().maximize();
        driver.get(url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Verify Sign-up fields Step 1
    @Test
    public void pizzaSignUpFieldTest() throws InterruptedException {
        //TODO: make a Navbar class that will handle navigation from the homepage
        driver.findElement(By.linkText("person")).click();

        WebElement loginContainer = driver.findElement(By.cssSelector(".pt-4"));
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div > a")));
        Thread.sleep(1500);

        //TODO: make a Signup class that will handle all inputs tests
        loginContainer.findElement(By.tagName("a")).click();

        driver.findElement(By.cssSelector("#input-91")).sendKeys("abc");
        driver.findElement(By.cssSelector("#input-94")).sendKeys("abc");
        driver.findElement(By.cssSelector("#input-97")).sendKeys("def");

        driver.findElement(By.cssSelector("button[aria-label=signup]")).click();


        //Assertions
        Assertions.assertEquals("Username must be minimum of 6 characters" , driver.findElement(By.cssSelector("#username-err")).getText());
        Assertions.assertEquals("Password must be minimum of 8 characters" , driver.findElement(By.cssSelector("#password-err")).getText());
        Assertions.assertEquals("Your passwords do not match" , driver.findElement(By.cssSelector("#confirm-err")).getText());
    }

    //Verify Sign-up fields Step 3
    @Test
    public void pizzaSignUpUsernameTakenTest() throws InterruptedException {
        //TODO: make a Navbar class that will handle navigation from the homepage
        driver.findElement(By.linkText("person")).click();

        WebElement loginContainer = driver.findElement(By.cssSelector(".pt-4"));
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div > a")));
        Thread.sleep(1500);
        loginContainer.findElement(By.tagName("a")).click();

        //TODO: make a Signup class that will handle all inputs tests
        driver.findElement(By.cssSelector("#input-91")).sendKeys("donaldtrump");
        driver.findElement(By.cssSelector("#input-94")).sendKeys("Password123123");
        driver.findElement(By.cssSelector("#input-97")).sendKeys("Password123123");

        //Assertions
        Assertions.assertEquals("" , driver.findElement(By.cssSelector("#username-err")).getText());
        Assertions.assertEquals("" , driver.findElement(By.cssSelector("#password-err")).getText());
        Assertions.assertEquals("" , driver.findElement(By.cssSelector("#confirm-err")).getText());
        driver.findElement(By.cssSelector("button[aria-label=signup]")).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".snackbar")));
        Assertions.assertEquals("Thanks robinhood, you can now login." , driver.findElement(By.cssSelector("snackbar")).getText());

    }

    //Verify Sign-up fields Step 3
    @Test
    public void pizzaSignUpTest() throws InterruptedException {
        //TODO: make a Navbar class that will handle navigation from the homepage
        driver.findElement(By.linkText("person")).click();

        WebElement loginContainer = driver.findElement(By.cssSelector(".pt-4"));
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div > a")));
        Thread.sleep(1500);
        loginContainer.findElement(By.tagName("a")).click();

        //TODO: make a Signup class that will handle all inputs tests
        driver.findElement(By.cssSelector("#input-91")).sendKeys("robinhood");
        driver.findElement(By.cssSelector("#input-94")).sendKeys("letmein2019");
        driver.findElement(By.cssSelector("#input-97")).sendKeys("letmein2019");

        //TODO: Find assertion that will verify element isnt visible, used a wait instead.
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#username-err")));

        driver.findElement(By.cssSelector("button[aria-label=signup]")).click();

        //Assertion
        Assertions.assertEquals("Username already exists" , driver.findElement(By.cssSelector("#username-err")).getText());

    }

    @Test
    public void menuItemTest() throws InterruptedException {
        driver.findElement(By.cssSelector("a[aria-label=menu]")).click();
        driver.findElement(By.cssSelector(".v-slide-group__content")).click();
        System.out.println("TEst");

    }

    @AfterEach
    public void cleanUp() {
        driver.quit();
    }

    private static WebDriver getWebDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}
