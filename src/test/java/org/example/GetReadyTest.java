package org.example;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class GetReadyTest {
    WebDriver driver;
    WebDriverWait wait;


    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.trendyol.com");
    }

    @Test
    public void closePop() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        WebElement product = driver.findElement(By.id("Combined-Shape"));
        product.click();
        TimeUnit.SECONDS.sleep(5);
    }


    @Test

    public void login() throws InterruptedException {


        Actions actions = new Actions(driver);
        //  actions.moveToElement(driver.findElement(By.id(""))).build().perform();
        actions.moveToElement(driver.findElement(By.cssSelector("div[class='account-nav-item user-login-container']"))).click().build().perform();
        driver.findElement(By.cssSelector("div[class='login-button']")).click();
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void writePasswordAndEmail() throws InterruptedException {
        login();


        WebElement email = driver.findElement(By.id("login-email"));
        email.sendKeys("yagmurerkk@gmail.com");


        WebElement password = driver.findElement(By.id("login-password-input"));
        TimeUnit.SECONDS.sleep(5);


        password.sendKeys("Yagmur1997" + Keys.ENTER);

        TimeUnit.SECONDS.sleep(5);

    }

    @Test
    public void searchKazak() throws InterruptedException {
        writePasswordAndEmail();
        WebElement product = driver.findElement(By.className("search-box"));
        TimeUnit.SECONDS.sleep(5);


        product.sendKeys("Kazak" + Keys.ENTER);
        //findElement(By.cssSelector("div[class='overlay']")).click();


        TimeUnit.SECONDS.sleep(5);

    }
    @Test
    public void addFavorite() throws InterruptedException{
        searchKazak();
        List<WebElement> imageList = driver.findElements(By.className("fvrt-btn-wrppr"));
        System.out.println(imageList.size());
        WebElement item = imageList.get(9);
        item.click();
       // List<WebElement> imageList = driver.findElements(By.className("prc-box-dscntd"));
        //System.out.println(imageList.size());
        // WebElement item2 = imageList.get(9);



    }

    @Test
    public void selectProduct() throws InterruptedException {

        addFavorite();
        List<WebElement> imageList = driver.findElements(By.className("p-card-wrppr"));
        System.out.println(imageList.size());
        WebElement item = imageList.get(9);
        item.click();
        TimeUnit.SECONDS.sleep(10);
    }
    @Test
    public void addToCart () throws InterruptedException {
        selectProduct();
        String currentWindow = driver.getWindowHandle();
        //findElement(By.cssSelector("button[class='add-to-basket']")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window :
                windowHandles) {
            if (!currentWindow.equals(window)) {
                driver.switchTo().window(window);

            }
        }
        TimeUnit.SECONDS.sleep(5);
        findElement(By.cssSelector("button[class='add-to-basket'")).click();
        TimeUnit.SECONDS.sleep(5);
        driver.switchTo().window(currentWindow);
        findElement(By.cssSelector("button[class='add-to-basket'")).click();



    }
    public WebElement findElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Test
    public void deleteFavorite() throws InterruptedException {
        addToCart();
        WebElement favs = driver.findElement(By.cssSelector("div[class='account-nav-item account-favorites'"));
        favs.click();

    }




    @After
    public void after() {
        driver.quit();

    }

   }

