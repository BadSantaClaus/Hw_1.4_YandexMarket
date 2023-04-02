package helpers;

import org.aeonbits.owner.Config;
/**
 * Интерфейс для поолучения доступа к файлу настроек
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
     * Абстрактный метод для получения доступа к заданному аттрибуту из настроек
     *
     * @author Горячев Роман Юрьевич
     */
    @Config.Key("yandexmarket.url")
    String yandexmarketUrl();
}
