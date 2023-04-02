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

import java.util.concurrent.ExecutionException;


public class StepsForYandex {

    @Step("Переходим в категорию : {categoryName}")
    public static void moveToCategory(String catalogueSection, String categoryName, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        yandexMarketPageFactory.moveToCategoryByName(catalogueSection, categoryName, webDriver);
    }

    @Step("Устаналвиваем цену от {minPrice} до {maxPrice} и производителей: {producer1}, {producer2}")
    public static void setFilter(String producer1, String producer2, String minPrice, String maxPrice, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        Actions action = new Actions(webDriver);
        wait.until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getPriceFrom()));
        action.click(yandexMarketPageFactory.getPriceFrom()).sendKeys(minPrice).perform();
        wait.until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getPriceTo()));
        action.click(yandexMarketPageFactory.getPriceTo()).sendKeys(maxPrice).perform();
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//span[text() = '" + producer1 + "']"))));
        webDriver.findElement(By.xpath("//span[text() = '" + producer1 + "']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//span[text() = '" + producer2 + "']"))));
        webDriver.findElement(By.xpath("//span[text() = '" + producer2 + "']")).click();
        waitPage(yandexMarketPageFactory, yandexMarketPageFactory.getLoading().getAttribute("data-zone-data"), webDriver);
    }

    @Step("Переходим на странцицу: {pageNumber}")
    public static void returnToPage(int pageNumber, WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        webDriver.get(webDriver.getCurrentUrl().substring(0, webDriver.getCurrentUrl().length() - 2) + pageNumber);
    }

    @Step("Вводим название из резулататов поиска номер: {numberSearchResult} в поискокую строку")
    public static void enterTitleToSearchFieldWithChecking(int numberSearchResult, WebDriver webDriver) {
        YandexMarketPageFactory yandexMarketPageFactory = PageFactory.initElements(webDriver, YandexMarketPageFactory.class);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        scrollToTheBottom(webDriver);
        String title = yandexMarketPageFactory.getResultsArticleList().stream().limit(numberSearchResult).reduce((first, second) -> second).get().getText();
        wait.until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getSearchField()));
        yandexMarketPageFactory.getAction().click(yandexMarketPageFactory.getSearchField()).sendKeys(title).perform();
        wait.until(ExpectedConditions.elementToBeClickable(yandexMarketPageFactory.getFindButton()));
        yandexMarketPageFactory.getFindButton().click();
        checkingSearchTitleResults(title, webDriver);
    }

    private static void waitPage(YandexMarketPageFactory yandexMarketPageFactory, String attribute, WebDriver webDriver) {
        for (int i = 0; i < 100; i++) {
            if (attribute.equals(yandexMarketPageFactory.getLoading().getAttribute("data-zone-data"))) {
                try { Thread.sleep(5); } catch (InterruptedException e) {e.printStackTrace();}
            } else break;
        }
    }
}

