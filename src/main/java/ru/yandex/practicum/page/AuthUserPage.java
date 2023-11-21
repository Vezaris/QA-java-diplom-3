package ru.yandex.practicum.page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.practicum.api.AuthUserApi;
import ru.yandex.practicum.EnvConfig;

public class AuthUserPage extends BasePage {
    private final WebDriver driver = getDriver();
    private final AuthUserApi authUserApi;

    /*---------------------------------- ЛОКАТОРЫ ------------------------*/
    private static final By MAIN_ENTRY_BUTTON = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By ACCOUNT_ENTRY_BUTTON = By.xpath("//p[text()='Личный Кабинет']");
    private static final By REGISTER_ENTRY_BUTTON = By.xpath("//a[text()='Войти']");
    private static final By RESTORE_ENTRY_BUTTON = REGISTER_ENTRY_BUTTON;
    private static final By ENTRY_CLICK = By.xpath("//button[text()='Войти']");
    private static final By INPUT_MAIL = By.xpath("//input[@name='name']");
    private static final By INPUT_PASS = By.xpath("//input[@type='password']");
    private static final By ORDER_FOR_RESULT = By.xpath("//button[text()='Оформить заказ']");

    public AuthUserPage(WebDriver driver, AuthUserApi authUserApi) {
        super(driver);
        this.authUserApi = authUserApi;
    }

    /*---------------------------------- МЕТОДЫ -----------------------------------*/

    @Step("Выполнить авторизацию")
    public void authClient() {
        driver.findElement(INPUT_MAIL).sendKeys(authUserApi.getEmail());
        driver.findElement(INPUT_PASS).sendKeys(authUserApi.getPassword());
        driver.findElement(ENTRY_CLICK).click();
    }

    @Step("Авторизация с главной")
    public WebElement checkAuthByMain() {
        openDriver(EnvConfig.URL_FOR_MAIN);
        driver.findElement(MAIN_ENTRY_BUTTON).click();
        authClient();
        return returnResult();
    }

    @Step("Авторизация с личного кабинета")
    public WebElement checkAuthByAccount() {
        openDriver(EnvConfig.URL_FOR_MAIN);
        driver.findElement(ACCOUNT_ENTRY_BUTTON).click();
        authClient();
        return returnResult();
    }

    @Step("Авторизация с экрана регистрации")
    public WebElement checkAuthByRegister() {
        openDriver(EnvConfig.URL_FOR_REGISTER);
        driver.findElement(REGISTER_ENTRY_BUTTON).click();
        authClient();
        return returnResult();
    }

    @Step("Авторизация с экрана восстановления пароля")
    public WebElement checkAuthByRestore() {
        openDriver(EnvConfig.URL_FOR_RESTORE);
        driver.findElement(RESTORE_ENTRY_BUTTON).click();
        authClient();
        return returnResult();
    }

    @Step("Вернуть результат")
    public WebElement returnResult() {
        waitingFor(ORDER_FOR_RESULT);
        return driver.findElement(ORDER_FOR_RESULT);
    }
}
