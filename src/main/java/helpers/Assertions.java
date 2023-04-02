package helpers;

import io.qameta.allure.Step;
/**
 * Класс используется для переопределения ассертов
 *
 * @author Горячев Роман Юрьевич
 */
public class Assertions {
    /**
     * Метод для проверки условия и выдачи сообщения о проверяемой ошибке
     *
     * @author Горячев Роман Юрьевич
     */
    @Step("Проверяем, что нет ошибки: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
}
