package pages;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static steps.StepsAssert.checkingNoSuchGoods;

/**
 * Класс используется для задания переменных и методов на странице ЯндексМаркета в паттерне PageFactory
 *
 * @author Горячев Роман Юрьевич
 */
@Getter
public class YandexMarketPageFactory {

    /**
     * Вебдрайвер
     *
     * @author Горячев Роман Юрьевич
     */
    private WebDriver webDriver;
    /**
     * Переменная для задания явных ожиданий
     *
     * @author Горячев Роман Юрьевич
     */
    private WebDriverWait wait;
    /**
     * Переменная для выполнения последовательности действий
     *
     * @author Горячев Роман Юрьевич
     */
    private Actions action;
    /**
     * Элемент страницы, который меняет значени атрибута "id" при изменении фильтров или переходе на другую страницу результатов поиска
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//main[@id='searchResults']/div")
    private WebElement changeableElement;

    /**
     * Элемент страницы  - каталог
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//button[@aria-controls='catalogPopup']")
    private WebElement catalogue;
    /**
     * Элемент страницы  - минимальная цена
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'min')]")
    private WebElement priceFrom;
    /**
     * Элемент страницы  - максимальная цена
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'max')]")
    private WebElement priceTo;
    /**
     * Элемент страницы  - кнопка "Вперед"
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//div[@data-baobab-name='next']")
    private WebElement forward;
    /**
     * Элемент страницы, содержащий кнопку вперед
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//div[@data-baobab-name='pager']")
    private WebElement pager;
    /**
     * Элемент страницы  - поисковая строка
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//div[@data-zone-name='search-input']")
    private WebElement searchField;
    /**
     * Элемент страницы  - кнопка "Найти"
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//span[text()='Найти']")
    private WebElement findButton;
    /**
     * Элемент страницы, содержащий текущую категорию
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//nav[@aria-label='Вы здесь']")
    private List<WebElement> currentCategoryList;
    /**
     * Список элементов, содержащий блок "Нет походящих товаров"
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//h2[text()='Нет подходящих товаров']")
    private List <WebElement> noSuchGoods;
    /**
     * Список результатов поиска
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//article")
    private List<WebElement> resultsArticleList;
    /**
     * Список цен результатов поиска
     *
     * @author Горячев Роман Юрьевич
     */
    @FindBy(how = How.XPATH, using = "//article//div[@data-zone-name='price']")
    private List<WebElement> resultsPriceList;
    /**
     * Конструктор создания обьектов YandexMarketPageFactory
     *
     * @author Горячев Роман Юрьевич
     * @param webDriver драйвер
     */
    public YandexMarketPageFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 30);
        action = new Actions(webDriver);
    }
    /**
     * Метод для перехода в заданную категорию
     *
     * @author Горячев Роман Юрьевич
     * @param catalogueSection наименование раздела каталога
     * @param categoryName наименование категории
     * @param webDriver драйвер
     */
    public void moveToCategoryByName(String catalogueSection, String categoryName, WebDriver webDriver) {
        catalogue.click();
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//span[text() = '" + catalogueSection + "']"))));
        action.moveToElement(webDriver.findElement(By.xpath("//span[text() = '" + catalogueSection + "']"))).perform();
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//a[text() = '" + categoryName + "']"))));
        webDriver.findElement(By.xpath("//a[text() = '" + categoryName + "']")).click();
    }
    /**
     * Метод для получения текущей категории
     *
     * @author Горячев Роман Юрьевич
     * @return строку с текущей категорией
     */
    public String getCurrentCategory() {
        String currentCategory = Arrays.asList(currentCategoryList.get(0).getText().split("\\r?\\n")).stream()
                .reduce((first, second) -> second).toString();
        return currentCategory;
    }
    /**
     * Метод для получения списка цен из результатов поиска
     *
     * @author Горячев Роман Юрьевич
     * @return обьект список цен из блоков результатов поиска
     */
    public List<Integer> getResultsPrices() {
        List<Integer> priceList = new ArrayList<>();
        for (int i = 0; i < resultsPriceList.size(); i++) {
            List<String> priceListString = Arrays.asList(resultsPriceList.get(i).getText().split("\\r?\\n"));
            priceList.add(Integer.parseInt(priceListString.get(0).replaceAll("[^a-zA-Z0-9]+", "")));
        }
        return priceList;
    }
    /**
     * Метод для возвращения на страницу с заданным номером. Если мы находимся на первой странице, то ничего не проиходит
     *
     * @author Горячев Роман Юрьеви
     * @param pageNumber номер страницы для возвращения после проверки результатов поиска
     * @param webDriver драйвер
     */
    public boolean returnToPage(int pageNumber, WebDriver webDriver) {
        String currentUrl = webDriver.getCurrentUrl();
        if(webDriver.getCurrentUrl().contains("page")){
            String newUrl = currentUrl.substring(0, webDriver.getCurrentUrl().lastIndexOf("=") + 1);
            webDriver.get(newUrl + pageNumber);
            return true;
        }
        return false;
    }
    /**
     * Метод для получения списка заголовков результатов поиска
     *
     * @author Горячев Роман Юрьевич
     * @param numberOfArticle Порядковый номер результатов поиска
     */
    public String getCurrentTitle(int numberOfArticle) {
        return webDriver.findElements(By.xpath(getTitleXpath())).get(numberOfArticle - 1).getText();
    }
    /**
     * Метод для получения XPath элемента с заголовками результатов поиска
     *
     * @author Горячев Роман Юрьевич
     * @return XPath с заголовками результатов поиска
     */
    public String getTitleXpath(){
        StringBuilder xPath = new StringBuilder();
        if(webDriver.findElements(By.xpath("//a[@data-baobab-name='title']")).size() > 0) {
            return xPath.append("//a[@data-baobab-name='title']").toString();
        } else if (webDriver.findElements(By.xpath("//h3[@data-baobab-name='title']")).size() > 0){
            return xPath.append("//h3[@data-baobab-name='title']").toString();
        } else return xPath.append("//h3[@data-zone-name='title']").toString();
    }
    /**
     * Метод для ожидания прогрузки страницы, основанный на изименении id элемента страницы
     *
     * @author Горячев Роман Юрьевич
     * @param attribute Элемент до перезагрузки страницы для сравнения с с элементом после
     * @param maxWaitAttempts Максимальное количество попыток ожидания
     */
    public void waitYandexResultsMarketPage(String attribute, int maxWaitAttempts) {
        checkingNoSuchGoods(PageFactory.initElements(webDriver, YandexMarketPageFactory.class));
        for (int i = 0; i <= maxWaitAttempts; i++) {
            if (i != maxWaitAttempts) {
                    StringBuilder waiting = new StringBuilder();
                    try {
                        waiting.append(changeableElement.getAttribute("id"));
                    } catch (StaleElementReferenceException staleElementReferenceException) {
                    }
                    if (attribute.contains(waiting.toString())) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    } else break;
            } else throw new TimeoutException();
        }
    }
}













