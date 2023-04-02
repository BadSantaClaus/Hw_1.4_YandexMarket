package ru.yandex.market;

import static steps.StepsAll.*;
import static steps.StepsForYandex.*;
import static steps.StepsAssert.*;

import helpers.Properties;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Класс используется для проведения тестов
 *
 * @author Горячев Роман Юрьевич
 */
public class Tests extends BaseTest {
    /**
     * Метод используется для запуска теста "Проверка сайта ЯндексМаркет"
     *
     * @param catalogueSection наименование раздела каталога
     * @param categoryName наименование категории
     * @param producer1 произовдитель
     * @param producer2 производитель
     * @param priceMin минимальная цена
     * @param priceMax максимальная цена
     * @param resultsQuantity количество найденных результатов
     * @param pageNumber номер страницы для возвращения после проверки результатов поиска
     * @param numberSearchResult порядковый номер результатов поиска
     * @author Горячев Роман Юрьевич
     */
    @Feature(("Проверка сайта ЯндексМаркет"))
    @DisplayName("Проверка результатов поиска на сайте ЯндексМаркет")
    @ParameterizedTest(name = "{displayName} c параметрами: {arguments}")
    @MethodSource("helpers.DataProvider#provideTestYandexMarket")
    public void testYandexMarket(String catalogueSection, String categoryName, String producer1, String producer2,
                                 int priceMin, int priceMax, int resultsQuantity, int pageNumber, int numberSearchResult) {
        openSite(Properties.testsProperties.yandexmarketUrl(), webDriver);
        moveToCategory(catalogueSection, categoryName, webDriver);
        checkingCurrentCategory(categoryName, webDriver);
        setFilter(producer1, producer2, priceMin, priceMax, webDriver);
        checkResultsQuantity(resultsQuantity, webDriver);
        checkResultsFilter(priceMin, priceMax, producer1, producer2, webDriver);
        returnToPage(pageNumber, webDriver);
        enterTitleToSearchFieldWithChecking(numberSearchResult, webDriver);
    }
}
