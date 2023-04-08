package steps;

import helpers.Assertions;
import helpers.Properties;
import helpers.Screenshoter;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.YandexMarketPageFactory;
import java.util.List;
import static steps.StepsAll.scrollToTheBottom;
/**
 * Класс для описания логики шагов прохождения проверок
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
    public static void checkCurrentCategory(String categoryName, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        Screenshoter.getScreen(webDriver, yandexMarketPageFactory.getCurrentCategoryArea());
        Assertions.assertWithScreen(yandexMarketPageFactory.getCurrentCategory().contains(categoryName),
                "Текущая категория не - " + categoryName, webDriver);
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
        Screenshoter.getScreen(webDriver, yandexMarketPageFactory.getPager());
        Assertions.assertWithScreen((long) yandexMarketPageFactory.getResultsArticleList().size() > resultsQuantity,
                "Количество результатов поиска менее: " + resultsQuantity, webDriver);
    }
    /**
     * Метод для проверки, что результаты поиска содержат заданное наименование
     *
     * @author Горячев Роман Юрьевич
     * @param title наименование поискового запроса
     * @param webDriver драйвер
     */
    @Step("Проверка наличия наименования: {title} в результатах поиска")
    public static void checkTitles(String title, WebDriver webDriver) {
        scrollToTheBottom(webDriver);
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        Assertions.assertResultTitle(yandexMarketPageFactory.getResultsArticleList().stream().anyMatch(x -> x.getText().contains(title)),
                "Страница результатов поиска не содержит поисковый запрос", webDriver);
    }
    /**
     * Метод для проверки, что результаты поиска содержат заданных производителей
     *
     * @author Горячев Роман Юрьевич
     * @param producer1 производитель
     * @param producer2 производитель
     * @param webDriver драйвер
     */
    @Step("Проверяем, что результаты поиска содержат: {producer1}, {producer2}")
    public static void checkResultsArticles(String producer1, String producer2, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        scrollToTheBottom(webDriver);
        List<WebElement> articles = yandexMarketPageFactory.getResultsArticleList();
        for (int i = 0; i < articles.size(); i++) {
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", articles.get(i));
            Assertions.assertWithScreen(articles.get(i).getText().toLowerCase().contains(producer1.toLowerCase())
                            || articles.get(i).getText().toLowerCase().contains(producer2.toLowerCase()),
                    "Результаты поиска не содержат: " + producer1 + " или " + producer2 + " в статье номер " + (i+1), webDriver);
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
    public static void checkResultsPrices(int priceMin, int priceMax, WebDriver webDriver) {
        scrollToTheBottom(webDriver);
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        List <Integer> pricesInt = yandexMarketPageFactory.getIntResultsPrices();
        List<WebElement> pricesElem = yandexMarketPageFactory.getResultsPriceList();
        for (int i = 0; i < pricesElem.size(); i++) {
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", pricesElem.get(i));
            Assertions.assertWithScreen(pricesInt.get(i) >= priceMin
                            && pricesInt.get(i) <= priceMax,
                    "Результаты поиска выходят из диапазона цен: от " + priceMin + " до " + priceMax + " в статье номер " + (i + 1), webDriver);
        }
    }
    /**
     * Метод для проверки, что результаты поиска находятся в заданном диапазоне цен и содержат заданных производителей с перелистыванием страниц
     * если присутствует кнопка "Вперёд"
     *
     * @author Горячев Роман Юрьевич
     * @param producer1 производитель
     * @param producer2 производитель
     * @param priceMin минимальная цена
     * @param priceMax максимальная цена
     * @param webDriver драйвер
     */
    @Step("Проверяем, что результаты поиска находятся в диапазоне цен: от {priceMin} до {priceMax} и содержат производителей: {producer1}, {producer2}")
    public static void checkResultsFilter(int priceMin, int priceMax, String producer1, String producer2, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        int count = 1;
        while (true) {
            Assertions.assertPage(true, "Проверяем, что результаты поиска соответствуют фильтрам на странице " + count, webDriver);
            checkResultsArticles(producer1, producer2, webDriver);
            checkResultsPrices(priceMin, priceMax, webDriver);
            String attribute = yandexMarketPageFactory.getChangeableElement().getAttribute("id");
            count++;
            if (yandexMarketPageFactory.getPager().getText().contains("Вперёд")) {
                yandexMarketPageFactory.getWait().until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getForward()));
                yandexMarketPageFactory.getForward().click();
                yandexMarketPageFactory.waitYandexResultsMarketPage(attribute, Properties.testsProperties.maxWaitAttempts());
            } else break;
        }
    }
    /**
     * Метод для мониторинга видимости элемента, когда на странице нет ни одного подходящего под фильтры товара
     *
     * @author Горячев Роман Юрьевич
     */
    public static void checkNoSuchGoods(YandexMarketPageFactory yandexMarketPageFactory){
        if(yandexMarketPageFactory.getNoSuchGoods().size() > 0){
            Assertions.assertWithScreen(false, "На странице нет ни одного подходящего под фильтры товара", yandexMarketPageFactory.getWebDriver());
        }
    }
}