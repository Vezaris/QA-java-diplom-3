package ru.yandex.practicum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import ru.yandex.practicum.Page.AuthUserPage;

public class AuthUserTest extends BaseTest{
    AuthUserPage authUserPage;

    @Before
    public void setUpForAuth () {
        authUserPage = new AuthUserPage(driverRule.getDriver(), authUserApi);
        model.sendPostRegister();
    }

    @Test
    @DisplayName("Проверка авторизации с главной страницы")
    @Description("Пользователь может перейти на страницу входа с главной страницы и выполнить авторизацию")
    public void authUserMain() {
        Assert.assertTrue(authUserPage.checkAuthByMain().isEnabled());
    }

    @Test
    @DisplayName("Проверка авторизации с личного кабинета")
    @Description("Пользователь может перейти на страницу входа со страницы личного кабинета и выполнить авторизацию")
    public void authUserAccount() {
        Assert.assertTrue(authUserPage.checkAuthByAccount().isEnabled());
    }

    @Test
    @DisplayName("Проверка авторизации со страницы регистрации")
    @Description("Пользователь может перейти на страницу входа со страницы регистрации и выполнить авторизацию ")
    public void authUserRegister() {
        Assert.assertTrue(authUserPage.checkAuthByRegister().isEnabled());
    }

    @Test
    @DisplayName("Проверка авторизации со страницы восстановления пароля")
    @Description("Пользователь может перейти на страницу входа со страницы восстановления пароля и выполнить авторизацию ")
    public void authUserRestore() {
        Assert.assertTrue(authUserPage.checkAuthByRestore().isEnabled());
    }
}
