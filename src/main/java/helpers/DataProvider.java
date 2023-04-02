package helpers;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
/**
 * Класс для предоставления аргурментов методам
 *
 * @author Горячев Роман Юрьевич
 */
public class DataProvider {
    /**
     * Метод для задания аргументов, которые будут переданы в тест
     *
     * @author Горячев Роман Юрьевич
     * @return список аргументов для прохождения теста
     */
    public static Stream<Arguments> provideTestYandexMarket() {
        return Stream.of(
                Arguments.of("Ноутбуки и компьютеры", "Ноутбуки", "HUAWEI", "Lenovo", 10000, 20000, 12, 1, 1)
        );
    }
}
