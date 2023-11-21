package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.page.RegisterUserPage;

public class RegisterUserTest extends BaseTest {

  private RegisterUserPage registerUserPage;

  @Before
  public void setUpForRegister() {
    registerUserPage = new RegisterUserPage(driverRule.getDriver(), registerUserApi);
  }

  @Test
  @DisplayName("Успешная регистрация")
  @Description("После успешной регистрации открывается страница авторизации")
  public void registerUser() {
    Assert.assertTrue(registerUserPage.registerSuccess().isEnabled());
  }

  @Test
  @DisplayName("Ошибка при регистрации с коротким паролем")
  @Description("При регистрации с маленьким паролем отображается ожидаемая ошибка")
  public void registerUserWithSmallPass() {
    registerUserApi.setPassword("pass");
    Assert.assertTrue(registerUserPage.registerError().isEnabled());
  }
}
