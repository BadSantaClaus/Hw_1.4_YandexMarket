package steps;


import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class StepsAll {

    @Step("Переходим на сайт: {url}")
    public static void openSite(String url, WebDriver driver) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get(url);
    }

    @Step("Пролистываем страницу в самый низ")
    public static void scrollToTheBottom(WebDriver webDriver) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @Step("Пролистываем страницу в самый верх")
    public static void scrollToTheTop(WebDriver webDriver) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("window.scrollTo(0, 0)");
    }



}
