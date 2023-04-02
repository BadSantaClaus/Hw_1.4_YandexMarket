package steps;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.YandexMarketPageFactory;

import static steps.StepsAll.*;

public class StepsAssert {

    @Step("Проверяем, что мы находимся в категории: {category}")
    public static void checkingCurrentCategory(String category, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        Assertions.assertTrue(yandexMarketPageFactory.getCurrentCategory().contains(category),
                "Текущая категория не - " + category);
    }


    @Step("Проверяем, что количество результатов поиска больше: {quantity}")
    public static void checkResultsQuantity(int quantity, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        scrollToTheBottom(webDriver);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getFindButton()));
        Assertions.assertTrue((long) yandexMarketPageFactory.getResultsArticleList().size() > quantity,
                "Количество результатов поиска менее: " + quantity);
    }


    @Step("Проверяем, что результаты поиска содержат: {producer1}, {producer2}")
    public static void checkingResultsArticles(String producer1, String producer2, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        for (int i = 0; i < 5; i++) {
            System.out.println("checkResultsArticle - " + i);
            Assertions.assertTrue(yandexMarketPageFactory.getResultsArticleList().get(i).getText().toLowerCase().contains(producer1.toLowerCase())
                            || yandexMarketPageFactory.getResultsArticleList().get(i).getText().toLowerCase().contains(producer2.toLowerCase()),
                    "Результаты поиска не содержат: " + producer1 + " или " + producer2);
        }
    }

    @Step("Проверяем, что результаты поиска находятся в диапазоне цен: {priceMin}, {priceMax}")
    public static void checkingResultsPrices(int priceMin, int priceMax, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        for (int i = 0; i < 2; i++) {
            System.out.println("checkResultsPrices - " + i + " - " + yandexMarketPageFactory.getResultsPrices().get(i));
            Assertions.assertTrue(yandexMarketPageFactory.getResultsPrices().get(i)
                            >= priceMin && yandexMarketPageFactory.getResultsPrices().get(i) <= priceMax,
                    "Результаты поиска выходят из диапазона цен: от " + priceMin + " до " + priceMax);
        }
    }


    @Step("Проверяем, что результаты поиска находятся в диапазоне цен: {priceMin}, {priceMax} и что результаты поиска содержат производителей: {producer1}, {producer2}")
    public static void checkResultsFilter(int priceMin, int priceMax, String producer1, String producer2, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        Actions actions = new Actions(webDriver);
        while (true) {
            System.out.println(yandexMarketPageFactory.getResultsArticleList().size());
            System.out.println(yandexMarketPageFactory.getResultsPrices().size());
            checkingResultsArticles(producer1, producer2, webDriver);
            checkingResultsPrices(priceMin, priceMax, webDriver);

            if (yandexMarketPageFactory.getPager().getText().contains("Вперёд")) {
                wait.until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getForward()));
                String attribute = yandexMarketPageFactory.getLoading().getAttribute("data-zone-data");
                //Click
//                yandexMarketPageFactory.getForward().click();
                actions.moveToElement(yandexMarketPageFactory.getForward()).click(yandexMarketPageFactory.getForward()).perform();
                waitPage(attribute, webDriver);
                scrollToTheBottom(webDriver);

            } else break;
        }
    }

    @Step("Проверка наличия наименования: {title} в результатах поиска")
    public static void checkingSearchTitleResults(String title, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);

        scrollToTheBottom(webDriver);
        Assertions.assertTrue(yandexMarketPageFactory.getResultsArticleList().stream().anyMatch(x -> x.getText().contains(title)),
                "Страница результатов поиска не содержит поисковый запрос");
    }

    private static void waitPage(String attribute, WebDriver webDriver) {
        for (int i = 0; i < 100; i++) {
            if (attribute.equals(webDriver.findElement(By.xpath("//div[contains(@data-zone-data, 'market:list') and @data-zone-name='SearchSerp']")).getAttribute("data-zone-data"))) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else break;
        }
    }

}




