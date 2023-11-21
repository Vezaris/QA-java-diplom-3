package ru.yandex.practicum.page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.practicum.api.RegisterUserApi;
import ru.yandex.practicum.EnvConfig;

public class RegisterUserPage extends BasePage{
    private final WebDriver driver = getDriver();
    private final RegisterUserApi registeruserapi;

    /*---------------------------------- ЛОКАТОРЫ ------------------------*/
    private static final By COME_REGISTER_BUTTON = By.xpath("//a[@href='/register']");
    private static final By INPUT_NAME = By.xpath("//fieldset[1]//input[@name='name']");
    private static final By INPUT_MAIL = By.xpath("//fieldset[2]//input[@name='name']");
    private static final By INPUT_PASS = By.xpath("//input[@type='password']");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By REGISTER_SUCCESS = By.xpath("//button[text()='Войти']");
    private static final By REGISTER_ERROR = By.xpath("//p[text()='Некорректный пароль']");

    public RegisterUserPage(WebDriver driver, RegisterUserApi registeruserapi) {
        super(driver);
        this.registeruserapi = registeruserapi;
    }

    /*---------------------------------- МЕТОДЫ -----------------------------------*/
    @Step("Переход на страницу регистрации")
    public void comeRegister() {
        openDriver(EnvConfig.URL_FOR_AUTH);
        driver.findElement(COME_REGISTER_BUTTON).click();
    }

    @Step("Выполнить регистрацию")
    public void registerUser() {
        driver.findElement(INPUT_NAME).sendKeys(registeruserapi.getName());
        driver.findElement(INPUT_MAIL).sendKeys(registeruserapi.getEmail());
        driver.findElement(INPUT_PASS).sendKeys(registeruserapi.getPassword());
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Вернуть результат успеха")
    public WebElement registerSuccess() {
        comeRegister();
        registerUser();
        waitingFor(REGISTER_SUCCESS);
        return driver.findElement(REGISTER_SUCCESS);
    }

    @Step("Вернуть результат ошибки при коротком пароле")
    public WebElement registerError() {
        comeRegister();
        registerUser();
        waitingFor(REGISTER_ERROR);
        return driver.findElement(REGISTER_ERROR);
    }
}
