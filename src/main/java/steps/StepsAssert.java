package steps;

import helpers.Assertions;
import helpers.Properties;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.YandexMarketPageFactory;

import java.util.ArrayList;
import java.util.List;

import static steps.StepsAll.*;

/**
 * Класс для описания логики шагов прохождения проверки
 *
 * @author Горячев Роман Юрьевич
 */
public class StepsAssert {
    /**
     * Метод для проверки, что мы находимся в заданной категории
     *
     * @author Горячев Роман Юрьевич
     * @param categoryName наименование категории
     * @param webDriver драйвер
     */
    @Step("Проверяем, что мы находимся в категории: {categoryName}")
    public static void checkingCurrentCategory(String categoryName, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        Assertions.assertTrue(yandexMarketPageFactory.getCurrentCategory().contains(categoryName),
                "Текущая категория не - " + categoryName);
    }
    /**
     * Метод для проверки, что количество результатов поиска больше заданного количества
     *
     * @author Горячев Роман Юрьевич
     * @param resultsQuantity количество найденных результатов
     * @param webDriver драйвер
     */
    @Step("Проверяем, что количество результатов поиска больше: {resultsQuantity}")
    public static void checkResultsQuantity(int resultsQuantity, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        scrollToTheBottom(webDriver);
        Assertions.assertTrue((long) yandexMarketPageFactory.getResultsArticleList().size() > resultsQuantity,
                "Количество результатов поиска менее: " + resultsQuantity);
    }
    /**
     * Метод для проверки, что результаты поиска содержат заданное наименование
     *
     * @author Горячев Роман Юрьевич
     * @param title наименование поискового запроса
     * @param webDriver драйвер
     */
    @Step("Проверка наличия наименования: {title} в результатах поиска")
    public static void checkingResultsArticles(String title, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        scrollToTheBottom(webDriver);
        checkingNoSuchGoods(yandexMarketPageFactory);
        Assertions.assertTrue(yandexMarketPageFactory.getResultsArticleList().stream().anyMatch(x -> x.getText().contains(title)),
                "Страница результатов поиска не содержит поисковый запрос");
    }
    /**
     * Метод для проверки, что результаты поиска содержат заданных производителей
     *
     * @author Горячев Роман Юрьевич
     * @param producer1 произовдитель
     * @param producer2 производитель
     * @param webDriver драйвер
     */
    @Step("Проверяем, что результаты поиска содержат: {producer1}, {producer2}")
    public static void checkingResultsArticles(String producer1, String producer2, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        List<WebElement> articles = new ArrayList<>();
        for (int i = 0; i < yandexMarketPageFactory.getResultsArticleList().size(); i++) {
            Assertions.assertTrue(articles.get(i).getText().toLowerCase().contains(producer1.toLowerCase())
                            || articles.get(i).getText().toLowerCase().contains(producer2.toLowerCase()),
                    "Результаты поиска не содержат: " + producer1 + " или " + producer2 + " в статье номер " + i);
        }
    }
    /**
     * Метод для проверки, что результаты поиска находятся в заданном диапазоне цен
     *
     * @author Горячев Роман Юрьевич
     * @param priceMin минимальная цена
     * @param priceMax максимальная цена
     * @param webDriver драйвер
     */
    @Step("Проверяем, что результаты поиска находятся в диапазоне цен: {priceMin}, {priceMax}")
    public static void checkingResultsPrices(int priceMin, int priceMax, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        List <Integer> prices = yandexMarketPageFactory.getResultsPrices();
        for (int i = 0; i < yandexMarketPageFactory.getResultsPrices().size(); i++) {
            Assertions.assertTrue(prices.get(i)
                            >= priceMin && prices.get(i) <= priceMax,
                    "Результаты поиска выходят из диапазона цен: от " + priceMin + " до " + priceMax + " в статье номер " + i);
        }
    }
    /**
     * Метод для проверки, что результаты поиска находятся в заданном диапазоне цен и содержат заданных производителей
     *
     * @author Горячев Роман Юрьевич
     * @param producer1 произовдитель
     * @param producer2 производитель
     * @param priceMin минимальная цена
     * @param priceMax максимальная цена
     * @param webDriver драйвер
     */
    @Step("Проверяем, что результаты поиска находятся в диапазоне цен: от {priceMin} до {priceMax} и содержат производителей: {producer1}, {producer2}")
    public static void checkResultsFilter(int priceMin, int priceMax, String producer1, String producer2, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        checkingNoSuchGoods(yandexMarketPageFactory);
        while (true) {
            checkingResultsArticles(producer1, producer2, webDriver);
            checkingResultsPrices(priceMin, priceMax, webDriver);
            if (yandexMarketPageFactory.getPager().getText().contains("Вперёд")) {
                yandexMarketPageFactory.getWait().until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getForward()));
                String attribute = yandexMarketPageFactory.getChangeableElement().getAttribute("id");
                yandexMarketPageFactory.getAction().moveToElement(yandexMarketPageFactory.getForward()).click(yandexMarketPageFactory.getForward()).perform();
                scrollToTheBottom(webDriver);
                yandexMarketPageFactory.waitYandexResultsMarketPage(attribute, Properties.testsProperties.maxWaitAttempts());
                scrollToTheBottom(webDriver);
            } else break;
        }
    }
    /**
     * Метод для мониторинга видимости элемента, когда на странице нет ни одногоподходящего подходящего фильтра товара
     *
     * @author Горячев Роман Юрьевич
     * @return XPath с заголовками результатов поиска
     */
    public static void checkingNoSuchGoods(YandexMarketPageFactory yandexMarketPageFactory){
        //
        System.out.println("noSuchGoods");
        //
        if(yandexMarketPageFactory.getNoSuchGoods().size() > 0){
            Assertions.assertTrue(false, "На странице нет ни одного подходящего под фильтры товара");
        }
    }
}