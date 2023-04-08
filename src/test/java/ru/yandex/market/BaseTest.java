package ru.yandex.market;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
/**
 * Класс используется для настройки параметров драйвера
 * @author Горячев Роман Юрьевич
 */
public class BaseTest {
    /**
     * Драйвер
     *
     * @author Горячев Роман Юрьевич
     */
    protected WebDriver webDriver;
    /**
     * Метод для задания параметров драйвера перед запуском каждого теста
     * @author Горячев Роман Юрьевич
     */
    @BeforeEach
    public void beforeEachTest() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "disable-infobars", "--start-maximized", "--single-process'",
                "--disable-dev-shm-usage", "--disable-blink-features=AutomationControlled", "log-level=3", "--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    /**
     * Метод для задания параметров драйвера после запуска каждого теста
     * @author Горячев Роман Юрьевич
     */
    @AfterEach
    public void afterEachTest() {
        webDriver.quit();
    }
}
