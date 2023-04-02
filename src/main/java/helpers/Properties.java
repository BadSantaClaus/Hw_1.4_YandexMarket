package helpers;

import org.aeonbits.owner.ConfigFactory;
/**
 * Класс для описания переменной, с помощью которой можно получить доступ к файлу настроек
 *
 * @author Горячев Роман Юрьевич
 */
public class Properties {
    /**
     * Переменная, с помощью которой можно получить доступ к файлу настроек
     *
     * @author Горячев Роман Юрьевич
     */
    public static TestsProperties testsProperties = ConfigFactory.create(TestsProperties.class);
}
