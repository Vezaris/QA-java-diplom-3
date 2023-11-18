package ru.yandex.practicum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import ru.yandex.practicum.Page.NavigationUserPage;

public class NavigationUserTest extends BaseTest{
    NavigationUserPage navigationUserPage;

    @Before
    public void setUpForNavigation () {
        navigationUserPage = new NavigationUserPage(driverRule.getDriver(), authUserApi);
        model.sendPostRegister();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Успешный переход в личный кабинет с главной страницы")
    public void comeInAccount() {
        navigationUserPage.authClient();
        Assert.assertTrue(navigationUserPage.comeInAccount().isDisplayed());
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    @Description("Успешный выход из личного кабинета")
    public void LogOut() {
        navigationUserPage.authClient();
        Assert.assertTrue(navigationUserPage.LogOut().isEnabled());
    }

    @Test
    @DisplayName("Переход в конструктор")
    @Description("Успешный переход из личного кабинета в конструктор")
    public void comeInBuilder() {
        navigationUserPage.authClient();
        Assert.assertTrue(navigationUserPage.comeInBuilder().isDisplayed());
    }

    @Test
    @DisplayName("Выбор раздела Соусы")
    @Description("Раздел Соусы выбирается и происходит скрол до нужных товаров")
    public void checkSauce() {
        Assert.assertTrue(navigationUserPage.selectSauce().isDisplayed());
    }


    @Test
    @DisplayName("Выбор раздела Булки")
    @Description("Раздел Булки выбирается и происходит скрол до нужных товаров")
    public void checkBun() {
        Assert.assertTrue(navigationUserPage.selectBun().isDisplayed());
    }

    @Test
    @DisplayName("Выбор раздела Начинки")
    @Description("Раздел Начинки выбирается и происходит скрол до нужных товаров")
    public void checkFilling() {
        Assert.assertTrue(navigationUserPage.selectFilling().isDisplayed());
    }

}
