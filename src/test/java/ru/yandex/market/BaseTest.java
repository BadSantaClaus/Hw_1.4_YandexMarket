package ru.yandex.market;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver webDriver;

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "disable-infobars", "--start-maximized", "--single-process'", "--disable-dev-shm-usage",
                "--disable-blink-features=AutomationControlled", "log-level=3");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterEach
    public void closeBellTest() {
//        webDriver.quit();
    }

}
