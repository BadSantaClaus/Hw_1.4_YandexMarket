package helpers;

import org.aeonbits.owner.Config;
/**
 * Интерфейс для получения доступа к файлу настроек
 *
 * @author Горячев Роман Юрьевич
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/test/resources/tests.properties"
})
public interface TestsProperties extends Config{
    /**
     * Абстрактный метод для получения доступа к url страницы
     *
     * @author Горячев Роман Юрьевич
     */
    @Config.Key("yandexmarket.url")
    String yandexmarketUrl();
    /**
     * Абстрактный метод для получения доступа к максимальному числу попыток ожидания обновления элемента старницы
     *
     * @author Горячев Роман Юрьевич
     */
    @Config.Key("maxWaitAttempts")
    int maxWaitAttempts();
}
