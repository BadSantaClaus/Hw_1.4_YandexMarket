package pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class YandexMarketPageFactory {


    private WebDriver webDriver;
    private WebDriverWait wait;
    private Actions action;

    @FindBy(how = How.XPATH, using = "//div[contains(@data-zone-data, 'market:list') and @data-zone-name='SearchSerp']")
    protected WebElement loading;
    @FindBy(how = How.XPATH, using = "//button[@aria-controls='catalogPopup']")
    protected WebElement catalogue;
    @FindBy(how = How.XPATH, using = "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'min')]")
    private WebElement priceFrom;
    @FindBy(how = How.XPATH, using = "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'max')]")
    private WebElement priceTo;
    @FindBy(how = How.XPATH, using = "//div[@data-baobab-name='next']")
    private WebElement forward;
    @FindBy(how = How.XPATH, using = "//div[@data-baobab-name='pager']")
    private WebElement pager;
    @FindBy(how = How.XPATH, using = "//div[@data-zone-name='search-input']")
    private WebElement searchField;
    @FindBy(how = How.XPATH, using = "//span[text()='Найти']")
    private WebElement findButton;
    @FindBy(how = How.XPATH, using = "//nav[@aria-label='Вы здесь']")
    private List<WebElement> currentCategoryList;
    @FindBy(how = How.XPATH, using = "//article")
    private List<WebElement> resultsArticleList;
    @FindBy(how = How.XPATH, using = "//article//div[@data-zone-name='price']")
    private List<WebElement> resultsPriceList;
    private List<String> historyUrls;
    private String priceMin = "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'min')]";
    private String priceMax = "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'max')]";

    public YandexMarketPageFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 30);
        action = new Actions(webDriver);
        historyUrls = new ArrayList<>();
    }

    public void moveToCategoryByName(String catalogueSection, String categoryName, WebDriver webDriver) {
        catalogue.click();
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//span[text() = '" + catalogueSection + "']"))));
        action.moveToElement(webDriver.findElement(By.xpath("//span[text() = '" + catalogueSection + "']"))).perform();
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//a[text() = '" + categoryName + "']"))));
        webDriver.findElement(By.xpath("//a[text() = '" + categoryName + "']")).click();

    }

    public String getCurrentCategory() {
        String currentCategory = Arrays.asList(currentCategoryList.get(0).getText().split("\\r?\\n")).stream()
                .reduce((first, second) -> second).toString();
        return currentCategory;
    }

    public List<Integer> getResultsPrices() {
        List<Integer> priceList = new ArrayList<>();
        for (int i = 0; i < resultsPriceList.size(); i++) {
            List<String> priceListString = Arrays.asList(resultsPriceList.get(i).getText().split("\\r?\\n"));
            priceList.add(Integer.parseInt(priceListString.get(0).replaceAll("[^a-zA-Z0-9]+", "")));
        }
        return priceList;
    }

    private static void waitPage(WebDriver webDriver) {
        String test = webDriver.findElement(By.xpath("//div[contains(@data-zone-data, 'market:list') and @data-zone-name='SearchSerp']")).getAttribute("data-zone-data");
        for (int i = 0; i < 100; i++) {
            if (test.equals(webDriver.findElement(By.xpath("//div[contains(@data-zone-data, 'market:list') and @data-zone-name='SearchSerp']")).getAttribute("data-zone-data"))) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {

                break;
            }
        }
    }


}













