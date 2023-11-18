package ru.yandex.practicum.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.practicum.Api.AuthUserApi;
import ru.yandex.practicum.EnvConfig;

public class NavigationUserPage extends BasePage{
    private final WebDriver DRIVER = getDRIVER();
    private final AuthUserApi AUTHUSERAPI;

    /*---------------------------------- ЛОКАТОРЫ ------------------------*/
    private final By INPUT_MAIL = By.xpath("//input[@name='name']");
    private final By INPUT_PASS = By.xpath("//input[@type='password']");
    private final By ENTRY_BUTTON = By.xpath("//button[text()='Войти']");
    private final By ACCOUNT = By.xpath("//p[text()='Личный Кабинет']");
    private final By BUILDER = By.xpath("//p[text()='Конструктор']");
    private final By EXIT = By.xpath("//button[text()='Выход']");
    private final By PROFILE = By.xpath("//a[text()='Профиль']");
    private final By ORDER_BUTTON = By.xpath("//button[text()='Оформить заказ']");
    private final By BUN = By.xpath("//span[text()='Булки']");
    private final By SAUCE = By.xpath("//span[text()='Соусы']");
    private final By FILLING = By.xpath("//span[text()='Начинки']");
    private final By BUN_RESULT = By.xpath("//h2[text()='Булки']");
    private final By SAUCE_RESULT = By.xpath("//h2[text()='Соусы']");
    private final By FILLING_RESULT = By.xpath("//h2[text()='Начинки']");

    public NavigationUserPage(WebDriver driver, AuthUserApi AUTHUSERAPI) {
        super(driver);
        this.AUTHUSERAPI = AUTHUSERAPI;
    }

    /*---------------------------------- МЕТОДЫ -----------------------------------*/

    @Step("Пройти авторизацию")
    public void authClient() {
        openDriver(EnvConfig.URL_FOR_AUTH);
        DRIVER.findElement(INPUT_MAIL).sendKeys(AUTHUSERAPI.getEmail());
        DRIVER.findElement(INPUT_PASS).sendKeys(AUTHUSERAPI.getPassword());
        DRIVER.findElement(ENTRY_BUTTON).click();
    }

    @Step("Переход в личный кабинет")
    public WebElement comeInAccount() {
        waitingFor(ACCOUNT);
        DRIVER.findElement(ACCOUNT).click();
        waitingFor(PROFILE);
        return DRIVER.findElement(PROFILE);
    }

    @Step("Переход в конструктор из личного кабинета")
    public WebElement comeInBuilder() {
        waitingFor(ACCOUNT);
        DRIVER.findElement(ACCOUNT).click();
        waitingFor(PROFILE);
        DRIVER.findElement(BUILDER).click();
        return DRIVER.findElement(ORDER_BUTTON);
    }

    @Step("Выход из личного кабинета")
    public WebElement LogOut() {
        waitingFor(ACCOUNT);
        DRIVER.findElement(ACCOUNT).click();
        waitingFor(PROFILE);
        DRIVER.findElement(EXIT).click();
        waitingFor(ENTRY_BUTTON);
        return DRIVER.findElement(ENTRY_BUTTON);
    }

    @Step("Выбор раздела соуса")
    public WebElement selectSauce() {
        openDriver(EnvConfig.URL_FOR_MAIN);
        waitingFor(SAUCE);
        DRIVER.findElement(SAUCE).click();
        return DRIVER.findElement(SAUCE_RESULT);
    }

    @Step("Выбор раздела булки")
    public WebElement selectBun() {
        openDriver(EnvConfig.URL_FOR_MAIN);
        waitingFor(BUN);
        DRIVER.findElement(SAUCE).click();
        DRIVER.findElement(BUN).click();
        return DRIVER.findElement(BUN_RESULT);
    }

    @Step("Выбор раздела начинки")
    public WebElement selectFilling() {
        openDriver(EnvConfig.URL_FOR_MAIN);
        waitingFor(FILLING);
        DRIVER.findElement(FILLING).click();
        return DRIVER.findElement(FILLING_RESULT);
    }
}
