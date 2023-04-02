package steps;

import static steps.StepsAll.*;
import static steps.StepsAssert.*;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.YandexMarketPageFactory;
/**
 * Класс для описания логики шагов прохождения теста
 *
 * @author Горячев Роман Юрьевич
 */
public class StepsForYandex {
    /**
     * Метод используется для перехода в заданную категорию
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
    }
    /**
     * Метод используется для перехода для задания фильтров поиска по ограничению минимальной и максимальной цены, а также двум производителям
     *
     * @author Горячев Роман Юрьевич
     * @param producer1 произовдитель
     * @param producer2 производитель
     * @param priceMin минимальная цена
     * @param priceMax максимальная цена
     * @param webDriver драйвер
     */
    @Step("Устаналвиваем цену от {minPrice} до {maxPrice} и производителей: {producer1}, {producer2}")
    public static void setFilter(String producer1, String producer2, int priceMin, int priceMax, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        Actions action = new Actions(webDriver);
        wait.until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getPriceFrom()));
        String attribute = yandexMarketPageFactory.getLoading().getAttribute("id");
        action.click(yandexMarketPageFactory.getPriceFrom()).sendKeys(String.valueOf(priceMin)).perform();
        action.click(yandexMarketPageFactory.getPriceTo()).sendKeys(String.valueOf(priceMax)).perform();
        wait.until(ExpectedConditions.elementToBeClickable( webDriver.findElement(By.xpath("//div[@data-grabber='SearchFilters']//span[text() = '" + producer1 + "']"))));
        webDriver.findElement(By.xpath("//div[@data-grabber='SearchFilters']//span[text() = '" + producer1 + "']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//div[@data-grabber='SearchFilters']//span[text() = '" + producer2 + "']"))));
        webDriver.findElement(By.xpath("//div[@data-grabber='SearchFilters']//span[text() = '" + producer2 + "']")).click();
        yandexMarketPageFactory.waitYandexResultsMarketPage(attribute, 600);
    }
    /**
     * Метод используется для возвращения на заданную страницу после поиска
     *
     * @author Горячев Роман Юрьевич
     * @param pageNumber номер страницы
     * @param webDriver драйвер
     */
    @Step("Переходим на странцицу: {pageNumber}")
    public static void returnToPage(int pageNumber, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        String attribute = yandexMarketPageFactory.getLoading().getAttribute("id");
        yandexMarketPageFactory.returnToPage(pageNumber,webDriver);
        yandexMarketPageFactory.waitYandexResultsMarketPage(attribute, 1000);
    }
    /**
     * Метод используется для ввода наименования из результатов поиска с заданным порядковым номером в поисковую строку
     *
     * @author Горячев Роман Юрьевич
     * @param numberSearchResult порядковый номер результата поиск
     * @param webDriver драйвер
     */
    @Step("Вводим название из резулататов поиска с порядковым номером: {numberSearchResult} в поискокую строку")
    public static void enterTitleToSearchFieldWithChecking(int numberSearchResult, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        scrollToTheBottom(webDriver);
        String title = yandexMarketPageFactory.getCurrentTitle(numberSearchResult);
        String attribute = yandexMarketPageFactory.getLoading().getAttribute("id");
        wait.until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getSearchField()));
        yandexMarketPageFactory.getAction().click(yandexMarketPageFactory.getSearchField()).sendKeys(title).perform();
        wait.until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getFindButton()));
        yandexMarketPageFactory.getFindButton().click();
        yandexMarketPageFactory.waitYandexResultsMarketPage(attribute, 1000);
        checkingResultsArticles(title, webDriver);
    }
}

