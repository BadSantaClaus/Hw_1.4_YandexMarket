package helpers;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Класс для создания скриншотов
 *
 * @author Горячев Роман Юрьевич
 */
public class Screenshoter {
    /**
     * Метод для создания скриншотов
     *
     * @author Горячев Роман Юрьевич
     */
    @Attachment
    public static byte[] getScreen(WebDriver driver){
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String name = "src/main/resources/screen_" + new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss.SSS").format(new Date()) + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(name));
            return Files.readAllBytes(Paths.get(name));
        } catch (Exception e){
            e.printStackTrace();
            Assertions.fail("Не удалось сохранить файл на диск");
        }
        return new byte[0];
    }
    /**
     * Метод для создания скриншотов с прокруткой к заданному элементу
     *
     * @author Горячев Роман Юрьевич
     */
    @Attachment
    public static byte[] getScreen(WebDriver webDriver, WebElement element){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
        File screenshot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        String name = "src/main/resources/screen_" + new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss.SSS").format(new Date()) + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(name));
            return Files.readAllBytes(Paths.get(name));
        } catch (Exception e){
            e.printStackTrace();
            Assertions.fail("Не удалось сохранить файл на диск");
        }
        return new byte[0];
    }

}
