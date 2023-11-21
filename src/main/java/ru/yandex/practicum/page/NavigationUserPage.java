package ru.yandex.practicum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.practicum.api.AuthUserApi;
import ru.yandex.practicum.EnvConfig;

public class NavigationUserPage extends BasePage {

  private final WebDriver driver = getDriver();
  private final AuthUserApi authUserApi;

  /*---------------------------------- ЛОКАТОРЫ ------------------------*/
  private static final By INPUT_MAIL = By.xpath("//input[@name='name']");
  private static final By INPUT_PASS = By.xpath("//input[@type='password']");
  private static final By ENTRY_BUTTON = By.xpath("//button[text()='Войти']");
  private static final By ACCOUNT = By.xpath("//p[text()='Личный Кабинет']");
  private static final By BUILDER = By.xpath("//p[text()='Конструктор']");
  private static final By EXIT = By.xpath("//button[text()='Выход']");
  private static final By PROFILE = By.xpath("//a[text()='Профиль']");
  private static final By ORDER_BUTTON = By.xpath("//button[text()='Оформить заказ']");
  private static final By BUN = By.xpath("//span[text()='Булки']");
  private static final By SAUCE = By.xpath("//span[text()='Соусы']");
  private static final By FILLING = By.xpath("//span[text()='Начинки']");

  public NavigationUserPage(WebDriver driver, AuthUserApi authUserApi) {
    super(driver);
    this.authUserApi = authUserApi;
  }

  /*---------------------------------- МЕТОДЫ -----------------------------------*/

  @Step("Пройти авторизацию")
  public void authClient() {
    openDriver(EnvConfig.URL_FOR_AUTH);
    driver.findElement(INPUT_MAIL).sendKeys(authUserApi.getEmail());
    driver.findElement(INPUT_PASS).sendKeys(authUserApi.getPassword());
    driver.findElement(ENTRY_BUTTON).click();
  }

  @Step("Переход в личный кабинет")
  public WebElement comeInAccount() {
    waitingFor(ACCOUNT);
    driver.findElement(ACCOUNT).click();
    waitingFor(PROFILE);
    return driver.findElement(PROFILE);
  }

  @Step("Переход в конструктор из личного кабинета")
  public WebElement comeInBuilder() {
    waitingFor(ACCOUNT);
    driver.findElement(ACCOUNT).click();
    waitingFor(PROFILE);
    driver.findElement(BUILDER).click();
    return driver.findElement(ORDER_BUTTON);
  }

  @Step("Выход из личного кабинета")
  public WebElement logOut() {
    waitingFor(ACCOUNT);
    driver.findElement(ACCOUNT).click();
    waitingFor(PROFILE);
    driver.findElement(EXIT).click();
    waitingFor(ENTRY_BUTTON);
    return driver.findElement(ENTRY_BUTTON);
  }

  @Step("Выбор элемента - родителя")
  public boolean checkEnter(By by) {
    String parentElement = by.toString().substring(10) + "/parent::div";
    return driver.findElement(By.xpath(parentElement)).getAttribute("class")
        .contains("tab_tab_type_current");
  }

  @Step("Выбор раздела соуса")
  public boolean selectSauce() {
    openDriver(EnvConfig.URL_FOR_MAIN);
    waitingFor(SAUCE);
    driver.findElement(SAUCE).click();
    return checkEnter(SAUCE);
  }

  @Step("Выбор раздела булки")
  public boolean selectBun() {
    openDriver(EnvConfig.URL_FOR_MAIN);
    waitingFor(BUN);
    driver.findElement(SAUCE).click();
    driver.findElement(BUN).click();
    return checkEnter(BUN);
  }

  @Step("Выбор раздела начинки")
  public boolean selectFilling() {
    openDriver(EnvConfig.URL_FOR_MAIN);
    waitingFor(FILLING);
    driver.findElement(FILLING).click();
    return checkEnter(FILLING);
  }

}
