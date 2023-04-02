package ru.yandex.market;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
/**
 * Класс используется для настройки парметров драйвера
 * @author Горячев Роман Юрьевич
 */
public class BaseTest {
    /**
     * Вебдрайвер
     *
     * @author Горячев Роман Юрьевич
     */
    protected WebDriver webDriver;
    /**
     * Метод используется для задания парметров драйвера перед запуском каждого теста
     * @author Горячев Роман Юрьевич
     */
    @BeforeEach
    public void beforeEachTest() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "disable-infobars", "--start-maximized", "--single-process'",
                "--disable-dev-shm-usage", "--disable-blink-features=AutomationControlled", "log-level=3");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    /**
     * Метод используется для задания парметров драйвера после запуска каждого теста
     * @author Горячев Роман Юрьевич
     */
    @AfterEach
    public void afterEachTest() {
//        webDriver.quit();
    }
}
