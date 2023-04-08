package steps;

import static steps.StepsAll.scrollToTheBottom;
import static steps.StepsAssert.*;
import helpers.Properties;
import helpers.Screenshoter;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.YandexMarketPageFactory;
/**
 * Класс для описания логики шагов прохождения теста для страниц ЯндексМаркета
 *
 * @author Горячев Роман Юрьевич
 */
public class StepsForYandex {
    /**
     * Метод для перехода в заданную категорию
     *
     * @author Горячев Роман Юрьевич
     * @param catalogueSection наименование раздела каталога
     * @param catalogueSection наименование категории
     * @param webDriver драйвер
     */
    @Step("Переходим в категорию : {categoryName}")
    public static void moveToCategory(String catalogueSection, String categoryName, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        yandexMarketPageFactory.moveToCategoryByName(catalogueSection, categoryName, webDriver);
        Screenshoter.getScreen(webDriver);
    }
    /**
     * Метод для задания фильтров поиска по ограничению минимальной и максимальной цены, а также двум производителям
     *
     * @author Горячев Роман Юрьевич
     * @param producer1 производитель
     * @param producer2 производитель
     * @param priceMin минимальная цена
     * @param priceMax максимальная цена
     * @param webDriver драйвер
     */
    @Step("Устаналвиваем цену от {minPrice} до {maxPrice} и производителей: {producer1}, {producer2}")
    public static void setFilter(String producer1, String producer2, int priceMin, int priceMax, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        yandexMarketPageFactory.getWait().until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getPriceFrom()));
        String attribute = yandexMarketPageFactory.getChangeableElement().getAttribute("id");
        yandexMarketPageFactory.getAction().click(yandexMarketPageFactory.getPriceFrom()).sendKeys(String.valueOf(priceMin)).perform();
        yandexMarketPageFactory.getAction().click(yandexMarketPageFactory.getPriceTo()).sendKeys(String.valueOf(priceMax)).perform();
        yandexMarketPageFactory.getAction().click(webDriver.findElement(By.xpath("//div[@data-grabber='SearchFilters']//span[translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'," +
                "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')='" + producer1.toLowerCase() + "']")))
                .click(webDriver.findElement(By.xpath("//div[@data-grabber='SearchFilters']//span[translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'," +
                        "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')='" + producer2.toLowerCase() + "']"))).perform();
        Screenshoter.getScreen(webDriver);
        yandexMarketPageFactory.waitYandexResultsMarketPage(attribute, Properties.testsProperties.maxWaitAttempts());
    }
    /**
     * Метод для возвращения на заданную страницу после поиска
     *
     * @author Горячев Роман Юрьевич
     * @param pageNumber номер страницы
     * @param webDriver драйвер
     */
    @Step("Переходим на странцицу: {pageNumber}")
    public static void returnToPage(int pageNumber, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        String attribute = yandexMarketPageFactory.getChangeableElement().getAttribute("id");
        if(yandexMarketPageFactory.returnToPage(pageNumber, webDriver)){
            yandexMarketPageFactory.waitYandexResultsMarketPage(attribute, Properties.testsProperties.maxWaitAttempts());
        }
        Screenshoter.getScreen(webDriver, yandexMarketPageFactory.getPager());
    }
    /**
     * Метод для ввода наименования из результатов поиска с заданным порядковым номером в поисковую строку
     * и последующей проверкой на содержание данного наименования в результатах поиска
     *
     * @author Горячев Роман Юрьевич
     * @param numberSearchResult порядковый номер результата поиск
     * @param webDriver драйвер
     */
    @Step("Вводим название из резулататов поиска с порядковым номером: {numberSearchResult} в поискокую строку")
    public static void enterTitleToSearchFieldWithChecking(int numberSearchResult, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        scrollToTheBottom(webDriver);
        String title = yandexMarketPageFactory.getTitles().get(numberSearchResult - 1).getText();
        Screenshoter.getScreen(webDriver, yandexMarketPageFactory.getTitles().get(numberSearchResult - 1));
        String attribute = yandexMarketPageFactory.getChangeableElement().getAttribute("id");
        yandexMarketPageFactory.getWait().until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getSearchField()));
        yandexMarketPageFactory.getAction().click(yandexMarketPageFactory.getSearchField()).sendKeys(title).perform();
        yandexMarketPageFactory.getWait().until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getFindButton()));
        yandexMarketPageFactory.getFindButton().click();
        yandexMarketPageFactory.waitYandexResultsMarketPage(attribute, Properties.testsProperties.maxWaitAttempts());
        checkTitles(title, webDriver);
    }
}

