package steps;


import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
/**
 * Класс для описания логики шагов, которые подходят для любой страницы
 *
 * @author Горячев Роман Юрьевич
 */
public class StepsAll {

    /**
     * Метод для перехода на заданный сайт
     *
     * @author Горячев Роман Юрьевич
     */
    @Step("Переходим на сайт: {url}")
    public static void openSite(String url, WebDriver driver) {
        driver.get(url);
    }
    /**
     * Метод для прокрутки страницы вниз
     *
     * @author Горячев Роман Юрьевич
     */
    public static void scrollToTheBottom(WebDriver webDriver) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("window.scrollTo(0, 11500)");
    }
}
