package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.YandexMarketPageFactory;
/**
 * Класс используется для переопределения ассертов
 *
 * @author Горячев Роман Юрьевич
 */
public class Assertions {
    /**
     * Метод для проверки условия и выдачи сообщения о проверяемой ошибке
     *
     * @author Горячев Роман Юрьевич
     */
    @Step("Проверяем, что нет ошибки: {message}")
    public static void assertWithScreen(boolean condition, String message, WebDriver webDriver) {
        Screenshoter.getScreen(webDriver);
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
    /**
     * Метод для проверки условия и выдачи сообщения о проверяемой ошибке со снятием скриншота номера страницы
     *
     * @author Горячев Роман Юрьевич
     */
    @Step("{message}")
    public static void assertPage(boolean condition, String message, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        Screenshoter.getScreen(webDriver, yandexMarketPageFactory.getPager());
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
    /**
     * Метод для проверки условия и выдачи сообщения о проверяемой ошибке
     *
     * @author Горячев Роман Юрьевич
     */
    @Step("Проверяем, что нет ошибки: {message}")
    public static void assertResultTitle(boolean condition, String message, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        Screenshoter.getScreen(webDriver, yandexMarketPageFactory.getSearchField());
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
}
