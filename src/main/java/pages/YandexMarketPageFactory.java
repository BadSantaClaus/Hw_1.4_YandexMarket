package pages;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private WebElement currentCategoryArea;
    /**
     * xpath названий результатов поиска
     *
     * @author Горячев Роман Юрьевич
     */
    private String titlesXpathVar1 = "//a[@data-baobab-name='title']";
    /**
     * xpath названий результатов поиска
     *
     * @author Горячев Роман Юрьевич
     */
    private String titlesXpathVar2 = "//h3[@data-baobab-name='title']";
    /**
     * xpath названий результатов поиска
     *
     * @author Горячев Роман Юрьевич
     */
    private String titlesXpathVar3 = "//h3[@data-zone-name='title']";
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
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
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
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//span[translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'," +
                "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')='" + catalogueSection.toLowerCase() + "']"))));
        action.moveToElement(webDriver.findElement(By.xpath("//span[translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'," +
                "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')='" + catalogueSection.toLowerCase() + "']"))).perform();
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//a[translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'," +
                "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')='" + categoryName.toLowerCase() + "']"))));
        webDriver.findElement(By.xpath("//a[text() = '" + categoryName + "']")).click();
    }
    /**
     * Метод для получения текущей категории
     *
     * @author Горячев Роман Юрьевич
     * @return строку с текущей категорией
     */
    public String getCurrentCategory() {
        String currentCategory = Arrays.asList(currentCategoryArea.getText().split("\\r?\\n")).stream()
                .reduce((first, second) -> second).toString();
        return currentCategory;
    }
    /**
     * Метод для получения списка цен из результатов поиска
     *
     * @author Горячев Роман Юрьевич
     * @return список цен результатов поиска в формате int
     */
    public List<Integer> getIntResultsPrices() {
        List<Integer> priceList = new ArrayList<>();
        List<WebElement> beforePrices = resultsPriceList;
        for (int i = 0; i < beforePrices.size(); i++) {
            List<String> priceListString = Arrays.asList(beforePrices.get(i).getText().split("\\r?\\n"));
            priceList.add(Integer.parseInt(priceListString.get(0).replaceAll("[^a-zA-Z0-9]+", "")));
        }
        return priceList;
    }
    /**
     * Метод для возвращения на страницу с заданным номером. Если мы находимся на первой странице, то ничего не проиходит
     *
     * @author Горячев Роман Юрьевич
     * @param pageNumber номер страницы для возвращения
     * @param webDriver драйвер
     * @return возвращает false если находимся на первой странице
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
     * Метод для получения списка заголовков
     *
     * @author Горячев Роман Юрьевич
     * @return список заголовков результатов поиска
     */
    public List<WebElement> getTitles() {
        return webDriver.findElements(By.xpath(getTitleXpath()));
    }
    /**
     * Метод для получения xpath элемента с заголовками результатов поиска
     *
     * @author Горячев Роман Юрьевич
     * @return xpath с заголовками результатов поиска
     */
    public String getTitleXpath(){
        StringBuilder xPath = new StringBuilder();
        if(webDriver.findElements(By.xpath(titlesXpathVar1)).size() > 0) {
            return xPath.append(titlesXpathVar1).toString();
        } else if (webDriver.findElements(By.xpath(titlesXpathVar2)).size() > 0){
            return xPath.append(titlesXpathVar2).toString();
        } else return xPath.append(titlesXpathVar3).toString();
    }
    /**
     * Метод для ожидания прогрузки страницы при обновлении фильтров или переходе на следующую страницу
     *
     * @author Горячев Роман Юрьевич
     * @param attribute Элемент до загрузки страницы для сравнения с элементом после
     * @param maxWaitAttempts Максимальное количество попыток ожидания
     */
    public void waitYandexResultsMarketPage(String attribute, int maxWaitAttempts) {
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













