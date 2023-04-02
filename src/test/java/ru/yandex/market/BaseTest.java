package ru.yandex.market;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
/**
 * ����� ������������ ��� ��������� ��������� ��������
 * @author ������� ����� �������
 */
public class BaseTest {
    /**
     * ����������
     *
     * @author ������� ����� �������
     */
    protected WebDriver webDriver;
    /**
     * ����� ������������ ��� ������� ��������� �������� ����� �������� ������� �����
     * @author ������� ����� �������
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
     * ����� ������������ ��� ������� ��������� �������� ����� ������� ������� �����
     * @author ������� ����� �������
     */
    @AfterEach
    public void afterEachTest() {
//        webDriver.quit();
    }
}
