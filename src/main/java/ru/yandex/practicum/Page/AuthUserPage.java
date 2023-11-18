package ru.yandex.practicum.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.practicum.Api.AuthUserApi;
import ru.yandex.practicum.EnvConfig;

public class AuthUserPage extends ru.yandex.practicum.Page.BasePage {
    private final WebDriver DRIVER = getDRIVER();
    private final AuthUserApi AUTHUSERAPI;

    /*---------------------------------- ЛОКАТОРЫ ------------------------*/
    private final By MAIN_ENTRY_BUTTON = By.xpath("//button[text()='Войти в аккаунт']");
    private final By ACCOUNT_ENTRY_BUTTON = By.xpath("//p[text()='Личный Кабинет']");
    private final By REGISTER_ENTRY_BUTTON = By.xpath("//a[text()='Войти']");
    private final By RESTORE_ENTRY_BUTTON = REGISTER_ENTRY_BUTTON;
    private final By ENTRY_CLICK = By.xpath("//button[text()='Войти']");
    private final By INPUT_MAIL = By.xpath("//input[@name='name']");
    private final By INPUT_PASS = By.xpath("//input[@type='password']");
    private final By ORDER_FOR_RESULT = By.xpath("//button[text()='Оформить заказ']");

    public AuthUserPage(WebDriver driver, AuthUserApi AUTHUSERAPI) {
        super(driver);
        this.AUTHUSERAPI = AUTHUSERAPI;
    }

    /*---------------------------------- МЕТОДЫ -----------------------------------*/

    @Step("Выполнить авторизацию")
    public void authClient() {
        DRIVER.findElement(INPUT_MAIL).sendKeys(AUTHUSERAPI.getEmail());
        DRIVER.findElement(INPUT_PASS).sendKeys(AUTHUSERAPI.getPassword());
        DRIVER.findElement(ENTRY_CLICK).click();
    }

    @Step("Авторизация с главной")
    public WebElement checkAuthByMain() {
        openDriver(EnvConfig.URL_FOR_MAIN);
        DRIVER.findElement(MAIN_ENTRY_BUTTON).click();
        authClient();
        return returnResult();
    }

    @Step("Авторизация с личного кабинета")
    public WebElement checkAuthByAccount() {
        openDriver(EnvConfig.URL_FOR_MAIN);
        DRIVER.findElement(ACCOUNT_ENTRY_BUTTON).click();
        authClient();
        return returnResult();
    }

    @Step("Авторизация с экрана регистрации")
    public WebElement checkAuthByRegister() {
        openDriver(EnvConfig.URL_FOR_REGISTER);
        DRIVER.findElement(REGISTER_ENTRY_BUTTON).click();
        authClient();
        return returnResult();
    }

    @Step("Авторизация с экрана восстановления пароля")
    public WebElement checkAuthByRestore() {
        openDriver(EnvConfig.URL_FOR_RESTORE);
        DRIVER.findElement(RESTORE_ENTRY_BUTTON).click();
        authClient();
        return returnResult();
    }

    @Step("Вернуть результат")
    public WebElement returnResult() {
        waitingFor(ORDER_FOR_RESULT);
        return DRIVER.findElement(ORDER_FOR_RESULT);
    }
}
