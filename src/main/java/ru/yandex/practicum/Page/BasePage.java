package ru.yandex.practicum.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.EnvConfig;
import java.time.Duration;

public class BasePage {
    private final WebDriver DRIVER;

    public BasePage (WebDriver driver) {
        this.DRIVER = driver;
    }

    /*---------------------------------- МЕТОДЫ -----------------------------------*/

    public WebDriver getDRIVER() {
        return DRIVER;
    }

    @Step("Ожидание прогрузки элементов")
    public void waitingFor(By by) {
        new WebDriverWait(DRIVER, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    @Step("Переход на сайт")
    public void openDriver(String url) {
        DRIVER.get(url);
    }
}
