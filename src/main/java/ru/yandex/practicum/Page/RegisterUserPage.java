package ru.yandex.practicum.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.practicum.Api.RegisterUserApi;
import ru.yandex.practicum.EnvConfig;

public class RegisterUserPage extends BasePage{
    private final WebDriver DRIVER = getDRIVER();
    private final RegisterUserApi REGISTERUSERAPI;

    /*---------------------------------- ЛОКАТОРЫ ------------------------*/
    private final By COME_REGISTER_BUTTON = By.xpath("//a[@href='/register']");
    private final By INPUT_NAME = By.xpath("//fieldset[1]//input[@name='name']");
    private final By INPUT_MAIL = By.xpath("//fieldset[2]//input[@name='name']");
    private final By INPUT_PASS = By.xpath("//input[@type='password']");
    private final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private final By REGISTER_SUCCESS = By.xpath("//button[text()='Войти']");
    private final By REGISTER_ERROR = By.xpath("//p[text()='Некорректный пароль']");

    public RegisterUserPage(WebDriver driver, RegisterUserApi REGISTERUSERAPI) {
        super(driver);
        this.REGISTERUSERAPI = REGISTERUSERAPI;
    }

    /*---------------------------------- МЕТОДЫ -----------------------------------*/
    @Step("Переход на страницу регистрации")
    public void comeRegister() {
        openDriver(EnvConfig.URL_FOR_AUTH);
        DRIVER.findElement(COME_REGISTER_BUTTON).click();
    }

    @Step("Выполнить регистрацию")
    public void registerUser() {
        DRIVER.findElement(INPUT_NAME).sendKeys(REGISTERUSERAPI.getName());
        DRIVER.findElement(INPUT_MAIL).sendKeys(REGISTERUSERAPI.getEmail());
        DRIVER.findElement(INPUT_PASS).sendKeys(REGISTERUSERAPI.getPassword());
        DRIVER.findElement(REGISTER_BUTTON).click();
    }

    @Step("Вернуть результат успеха")
    public WebElement registerSuccess() {
        comeRegister();
        registerUser();
        waitingFor(REGISTER_SUCCESS);
        return DRIVER.findElement(REGISTER_SUCCESS);
    }

    @Step("Вернуть результат ошибки при коротком пароле")
    public WebElement registerError() {
        comeRegister();
        registerUser();
        waitingFor(REGISTER_ERROR);
        return DRIVER.findElement(REGISTER_ERROR);
    }
}
