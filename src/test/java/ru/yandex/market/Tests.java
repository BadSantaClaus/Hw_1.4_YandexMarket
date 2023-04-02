package ru.yandex.market;

import static steps.StepsAll.*;
import static steps.StepsForYandex.*;
import static steps.StepsAssert.*;
import helpers.Properties;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Tests extends BaseTest{

    @ParameterizedTest
    @CsvSource({"Кирилл Филенков"})
    public void testYandexMarket() {
//        openSite(Properties.testsProperties.yandexmarketUrl(), webDriver);
//        moveToCategory("Ноутбуки и компьютеры", "Ноутбуки", webDriver);
//        checkingCurrentCategory("Ноутбуки", webDriver);
//        setFilter("Lenovo", "HUAWEI", "1000", "20000", webDriver);
//        checkResultsQuantity(12, webDriver);
//        checkResultsFilter(1000, 25000, "Lenovo", "HUAWEI", webDriver);
        openSite("https://market.yandex.ru/catalog--noutbuki/26895412/list?srnum=515&was_redir=1&rt=11&rs=eJwzesWo1MvIZXRh7oV9F5svNl3YeLH5wi4Fn9S8_LJ8Bc-U1MSAxBQFYwVDU093HwNTBQvD8EADAyNTR2-BY48eMiuxcDAIMABJDgE-DYYsMoypYjM0NbK0MGxgbD3O2sXIxMEQwFjFwgHkzGIkw7hVjFwc-14eERS4daaXC8yZ-odd4OzMHrYNjAwAwwpT8g%2C%2C&parsed-glfilter=7893318%3A152981&text=%D0%9D%D0%BE%D1%83%D1%82%D0%B1%D1%83%D0%BA%20Lenovo%20IdeaPad%203%2015IGL05%2081WQ0025AK&hid=91013&allowCollapsing=1&local-offers-first=0&glfilter=7893318%3A152981&page=3",webDriver);
        returnToPage(1,webDriver);
        enterTitleToSearchFieldWithChecking(1,webDriver);

    }



}
